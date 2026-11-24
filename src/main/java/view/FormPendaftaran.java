package view;

import config.Database;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 * Transaksi Pendaftaran: tampil, tambah, ubah, hapus.
 * Tabel: pendaftaran(id_pendaftaran, id_peserta, id_kelas,
 *                    tanggal_daftar, status).
 * Peserta dan kelas dipilih lewat combo box.
 */
public class FormPendaftaran extends JFrame {

    /** Item combo box: menyimpan id, menampilkan label. */
    private static class Item {
        final int id;
        final String label;

        Item(int id, String label) {
            this.id = id;
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    // Kolom 7 dan 8 (id_peserta, id_kelas) disimpan di model tapi
    // disembunyikan dari tampilan tabel.
    private final DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID", "Tgl Daftar", "NIM", "Peserta", "Kelas",
                "Kursus", "Status", "id_peserta", "id_kelas"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private final JTable tabel = new JTable(model);

    private final JComboBox<Item> cmbPeserta = new JComboBox<>();
    private final JComboBox<Item> cmbKelas = new JComboBox<>();
    private final JTextField txtTanggal = new JTextField(9);
    private final JComboBox<String> cmbStatus = new JComboBox<>(
            new String[]{"Aktif", "Selesai", "Batal"});

    // null = mode tambah, selain itu = mode ubah data yang dipilih
    private Integer idTerpilih = null;

    public FormPendaftaran() {
        setTitle("Pendaftaran");
        setSize(1000, 540);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel judul = new JLabel("PENDAFTARAN PESERTA");
        judul.setFont(new Font("Arial", Font.BOLD, 22));
        judul.setHorizontalAlignment(SwingConstants.CENTER);

        cmbStatus.setEditable(true);
        txtTanggal.setText(LocalDate.now().toString());

        // ----- form input -----
        JPanel panelInput = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        panelInput.add(new JLabel("Peserta:"));
        panelInput.add(cmbPeserta);
        panelInput.add(new JLabel("Kelas:"));
        panelInput.add(cmbKelas);
        panelInput.add(new JLabel("Tanggal (yyyy-MM-dd):"));
        panelInput.add(txtTanggal);
        panelInput.add(new JLabel("Status:"));
        panelInput.add(cmbStatus);

        JButton btnSimpan = new JButton("Simpan");
        JButton btnBaru = new JButton("Baru");
        JButton btnHapus = new JButton("Hapus");
        JButton btnTutup = new JButton("Tutup");

        panelInput.add(btnSimpan);
        panelInput.add(btnBaru);
        panelInput.add(btnHapus);
        panelInput.add(btnTutup);

        // ----- tabel -----
        tabel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabel.setRowHeight(24);
        tabel.getColumnModel().getColumn(0).setMaxWidth(60);

        // Sembunyikan kolom dengan indeks lebih besar dulu.
        tabel.removeColumn(tabel.getColumnModel().getColumn(8));
        tabel.removeColumn(tabel.getColumnModel().getColumn(7));

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(judul, BorderLayout.NORTH);
        panel.add(new JScrollPane(tabel), BorderLayout.CENTER);
        panel.add(panelInput, BorderLayout.SOUTH);
        add(panel);

        // ----- event -----
        tabel.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                isiFormDariTabel();
            }
        });
        btnSimpan.addActionListener(e -> simpan());
        btnBaru.addActionListener(e -> bersihkan());
        btnHapus.addActionListener(e -> hapus());
        btnTutup.addActionListener(e -> dispose());

        muatCombo();
        muatData();
    }

    private void muatCombo() {
        cmbPeserta.removeAllItems();
        cmbKelas.removeAllItems();

        try (Connection conn = Database.getConnection();
             Statement st = conn.createStatement()) {

            try (ResultSet rs = st.executeQuery(
                    "SELECT id_peserta, nim, nama FROM peserta "
                    + "ORDER BY nama")) {
                while (rs.next()) {
                    cmbPeserta.addItem(new Item(
                            rs.getInt("id_peserta"),
                            rs.getString("nim") + " - "
                            + rs.getString("nama")));
                }
            }

            try (ResultSet rs = st.executeQuery(
                    "SELECT kl.id_kelas, kl.nama_kelas, k.nama_kursus "
                    + "FROM kelas kl "
                    + "JOIN kursus k ON k.id_kursus = kl.id_kursus "
                    + "ORDER BY kl.nama_kelas")) {
                while (rs.next()) {
                    cmbKelas.addItem(new Item(
                            rs.getInt("id_kelas"),
                            rs.getString("nama_kelas") + " ("
                            + rs.getString("nama_kursus") + ")"));
                }
            }

        } catch (SQLException e) {
            tampilError("Gagal memuat pilihan peserta/kelas", e);
        }
    }

    private void muatData() {
        model.setRowCount(0);

        String sql = "SELECT p.id_pendaftaran, p.tanggal_daftar, ps.nim, "
                + "ps.nama AS nama_peserta, kl.nama_kelas, k.nama_kursus, "
                + "p.status, p.id_peserta, p.id_kelas "
                + "FROM pendaftaran p "
                + "JOIN peserta ps ON ps.id_peserta = p.id_peserta "
                + "JOIN kelas kl ON kl.id_kelas = p.id_kelas "
                + "JOIN kursus k ON k.id_kursus = kl.id_kursus "
                + "ORDER BY p.id_pendaftaran";

        try (Connection conn = Database.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id_pendaftaran"),
                    String.valueOf(rs.getDate("tanggal_daftar")),
                    rs.getString("nim"),
                    rs.getString("nama_peserta"),
                    rs.getString("nama_kelas"),
                    rs.getString("nama_kursus"),
                    rs.getString("status"),
                    rs.getInt("id_peserta"),
                    rs.getInt("id_kelas")
                });
            }

        } catch (SQLException e) {
            tampilError("Gagal memuat data pendaftaran", e);
        }
    }

    private void isiFormDariTabel() {
        int viewRow = tabel.getSelectedRow();
        if (viewRow < 0) {
            return;
        }
        int baris = tabel.convertRowIndexToModel(viewRow);

        idTerpilih = (Integer) model.getValueAt(baris, 0);
        txtTanggal.setText(String.valueOf(model.getValueAt(baris, 1)));
        cmbStatus.setSelectedItem(String.valueOf(model.getValueAt(baris, 6)));
        pilihItem(cmbPeserta, (Integer) model.getValueAt(baris, 7));
        pilihItem(cmbKelas, (Integer) model.getValueAt(baris, 8));
    }

    private void pilihItem(JComboBox<Item> combo, int id) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            if (combo.getItemAt(i).id == id) {
                combo.setSelectedIndex(i);
                return;
            }
        }
    }

    private void bersihkan() {
        idTerpilih = null;
        if (cmbPeserta.getItemCount() > 0) {
            cmbPeserta.setSelectedIndex(0);
        }
        if (cmbKelas.getItemCount() > 0) {
            cmbKelas.setSelectedIndex(0);
        }
        cmbStatus.setSelectedIndex(0);
        txtTanggal.setText(LocalDate.now().toString());
        tabel.clearSelection();
    }

    private void simpan() {
        Item peserta = (Item) cmbPeserta.getSelectedItem();
        Item kelas = (Item) cmbKelas.getSelectedItem();
        Object statusPilihan = cmbStatus.getSelectedItem();
        String status = (statusPilihan == null)
                ? "" : statusPilihan.toString().trim();

        if (peserta == null || kelas == null) {
            peringatan("Data peserta dan kelas harus tersedia dulu.");
            return;
        }

        if (status.isEmpty()) {
            peringatan("Status tidak boleh kosong.");
            return;
        }

        LocalDate tanggal;
        try {
            tanggal = LocalDate.parse(txtTanggal.getText().trim());
        } catch (DateTimeParseException e) {
            peringatan("Tanggal harus berformat yyyy-MM-dd, "
                    + "contoh: 2026-11-25.");
            txtTanggal.requestFocus();
            return;
        }

        String sql = (idTerpilih == null)
                ? "INSERT INTO pendaftaran "
                + "(id_peserta, id_kelas, tanggal_daftar, status) "
                + "VALUES (?, ?, ?, ?)"
                : "UPDATE pendaftaran SET id_peserta = ?, id_kelas = ?, "
                + "tanggal_daftar = ?, status = ? WHERE id_pendaftaran = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, peserta.id);
            ps.setInt(2, kelas.id);
            ps.setDate(3, Date.valueOf(tanggal));
            ps.setString(4, status);
            if (idTerpilih != null) {
                ps.setInt(5, idTerpilih);
            }
            ps.executeUpdate();

            bersihkan();
            muatData();

        } catch (SQLException e) {
            tampilError("Gagal menyimpan data pendaftaran", e);
        }
    }

    private void hapus() {
        if (idTerpilih == null) {
            JOptionPane.showMessageDialog(this,
                    "Pilih data di tabel terlebih dahulu.",
                    "Informasi", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int pilihan = JOptionPane.showConfirmDialog(this,
                "Hapus pendaftaran dengan ID " + idTerpilih + "?",
                "Konfirmasi", JOptionPane.YES_NO_OPTION);

        if (pilihan != JOptionPane.YES_OPTION) {
            return;
        }

        String sql = "DELETE FROM pendaftaran WHERE id_pendaftaran = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idTerpilih);
            ps.executeUpdate();

            bersihkan();
            muatData();

        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(this,
                    "Pendaftaran ini sudah punya data pembayaran, "
                    + "jadi tidak bisa dihapus.",
                    "Tidak bisa dihapus", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
            tampilError("Gagal menghapus data pendaftaran", e);
        }
    }

    private void peringatan(String pesan) {
        JOptionPane.showMessageDialog(this, pesan,
                "Validasi", JOptionPane.WARNING_MESSAGE);
    }

    private void tampilError(String pesan, SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this,
                pesan + ":\n" + e.getMessage(),
                "Kesalahan Database", JOptionPane.ERROR_MESSAGE);
    }
}