package view;

import config.Database;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
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
 * Transaksi Pembayaran: tampil, tambah, ubah, hapus.
 * Tabel: pembayaran(id_pembayaran, id_pendaftaran, tanggal_bayar,
 *                   nominal, metode, status).
 * Pendaftaran dipilih lewat combo box.
 */
public class FormPembayaran extends JFrame {

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

    private static final DateTimeFormatter FORMAT_TANGGAL =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // Kolom 8 (id_pendaftaran) disimpan di model tapi disembunyikan.
    private final DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID", "Tgl Bayar", "NIM", "Peserta", "Kelas",
                "Nominal", "Metode", "Status", "id_pendaftaran"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private final JTable tabel = new JTable(model);

    private final JComboBox<Item> cmbPendaftaran = new JComboBox<>();
    private final JTextField txtTanggal = new JTextField(13);
    private final JTextField txtNominal = new JTextField(9);
    private final JComboBox<String> cmbMetode = new JComboBox<>(
            new String[]{"Transfer", "Tunai", "E-Wallet"});
    private final JComboBox<String> cmbStatus = new JComboBox<>(
            new String[]{"Lunas", "Belum Lunas", "Pending"});

    private final NumberFormat formatRupiah =
            NumberFormat.getIntegerInstance(new Locale("id", "ID"));

    // null = mode tambah, selain itu = mode ubah data yang dipilih
    private Integer idTerpilih = null;

    public FormPembayaran() {
        setTitle("Pembayaran");
        setSize(1050, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel judul = new JLabel("PEMBAYARAN");
        judul.setFont(new Font("Arial", Font.BOLD, 22));
        judul.setHorizontalAlignment(SwingConstants.CENTER);

        cmbMetode.setEditable(true);
        cmbStatus.setEditable(true);
        txtTanggal.setText(LocalDateTime.now().format(FORMAT_TANGGAL));

        // ----- form input -----
        JPanel panelInput = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        panelInput.add(new JLabel("Pendaftaran:"));
        panelInput.add(cmbPendaftaran);
        panelInput.add(new JLabel("Tanggal (yyyy-MM-dd HH:mm):"));
        panelInput.add(txtTanggal);
        panelInput.add(new JLabel("Nominal (Rp):"));
        panelInput.add(txtNominal);
        panelInput.add(new JLabel("Metode:"));
        panelInput.add(cmbMetode);
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
        tabel.removeColumn(tabel.getColumnModel().getColumn(8));

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
        cmbPendaftaran.removeAllItems();

        String sql = "SELECT p.id_pendaftaran, ps.nim, ps.nama, kl.nama_kelas "
                + "FROM pendaftaran p "
                + "JOIN peserta ps ON ps.id_peserta = p.id_peserta "
                + "JOIN kelas kl ON kl.id_kelas = p.id_kelas "
                + "ORDER BY p.id_pendaftaran";

        try (Connection conn = Database.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                cmbPendaftaran.addItem(new Item(
                        rs.getInt("id_pendaftaran"),
                        "#" + rs.getInt("id_pendaftaran") + " - "
                        + rs.getString("nim") + " - "
                        + rs.getString("nama") + " ("
                        + rs.getString("nama_kelas") + ")"));
            }

        } catch (SQLException e) {
            tampilError("Gagal memuat pilihan pendaftaran", e);
        }
    }

    private void muatData() {
        model.setRowCount(0);

        String sql = "SELECT b.id_pembayaran, b.tanggal_bayar, ps.nim, "
                + "ps.nama AS nama_peserta, kl.nama_kelas, b.nominal, "
                + "b.metode, b.status, b.id_pendaftaran "
                + "FROM pembayaran b "
                + "JOIN pendaftaran p ON p.id_pendaftaran = b.id_pendaftaran "
                + "JOIN peserta ps ON ps.id_peserta = p.id_peserta "
                + "JOIN kelas kl ON kl.id_kelas = p.id_kelas "
                + "ORDER BY b.id_pembayaran";

        try (Connection conn = Database.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Timestamp waktu = rs.getTimestamp("tanggal_bayar");

                model.addRow(new Object[]{
                    rs.getInt("id_pembayaran"),
                    waktu == null ? "" : waktu.toLocalDateTime()
                            .format(FORMAT_TANGGAL),
                    rs.getString("nim"),
                    rs.getString("nama_peserta"),
                    rs.getString("nama_kelas"),
                    formatRupiah.format(rs.getBigDecimal("nominal").longValue()),
                    rs.getString("metode"),
                    rs.getString("status"),
                    rs.getInt("id_pendaftaran")
                });
            }

        } catch (SQLException e) {
            tampilError("Gagal memuat data pembayaran", e);
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

        // "500.000" -> "500000" (aman untuk format angka apa pun)
        txtNominal.setText(String.valueOf(model.getValueAt(baris, 5))
                .replaceAll("[^0-9]", ""));

        cmbMetode.setSelectedItem(String.valueOf(model.getValueAt(baris, 6)));
        cmbStatus.setSelectedItem(String.valueOf(model.getValueAt(baris, 7)));

        int idPendaftaran = (Integer) model.getValueAt(baris, 8);
        for (int i = 0; i < cmbPendaftaran.getItemCount(); i++) {
            if (cmbPendaftaran.getItemAt(i).id == idPendaftaran) {
                cmbPendaftaran.setSelectedIndex(i);
                break;
            }
        }
    }

    private void bersihkan() {
        idTerpilih = null;
        if (cmbPendaftaran.getItemCount() > 0) {
            cmbPendaftaran.setSelectedIndex(0);
        }
        cmbMetode.setSelectedIndex(0);
        cmbStatus.setSelectedIndex(0);
        txtNominal.setText("");
        txtTanggal.setText(LocalDateTime.now().format(FORMAT_TANGGAL));
        tabel.clearSelection();
    }

    private void simpan() {
        Item pendaftaran = (Item) cmbPendaftaran.getSelectedItem();
        Object metodePilihan = cmbMetode.getSelectedItem();
        Object statusPilihan = cmbStatus.getSelectedItem();
        String metode = (metodePilihan == null)
                ? "" : metodePilihan.toString().trim();
        String status = (statusPilihan == null)
                ? "" : statusPilihan.toString().trim();

        if (pendaftaran == null) {
            peringatan("Belum ada data pendaftaran. "
                    + "Isi data pendaftaran terlebih dahulu.");
            return;
        }

        if (metode.isEmpty() || status.isEmpty()) {
            peringatan("Metode dan status wajib diisi.");
            return;
        }

        LocalDateTime waktu;
        try {
            waktu = LocalDateTime.parse(
                    txtTanggal.getText().trim(), FORMAT_TANGGAL);
        } catch (DateTimeParseException e) {
            peringatan("Tanggal harus berformat yyyy-MM-dd HH:mm, "
                    + "contoh: 2026-11-25 14:30.");
            txtTanggal.requestFocus();
            return;
        }

        String nominalTeks = txtNominal.getText().trim()
                .replace(".", "").replace(",", "");
        BigDecimal nominal;
        try {
            nominal = new BigDecimal(nominalTeks);
        } catch (NumberFormatException e) {
            peringatan("Nominal harus berupa angka, contoh: 500000.");
            txtNominal.requestFocus();
            return;
        }

        if (nominal.signum() <= 0) {
            peringatan("Nominal harus lebih besar dari 0.");
            txtNominal.requestFocus();
            return;
        }

        String sql = (idTerpilih == null)
                ? "INSERT INTO pembayaran "
                + "(id_pendaftaran, tanggal_bayar, nominal, metode, status) "
                + "VALUES (?, ?, ?, ?, ?)"
                : "UPDATE pembayaran SET id_pendaftaran = ?, "
                + "tanggal_bayar = ?, nominal = ?, metode = ?, status = ? "
                + "WHERE id_pembayaran = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, pendaftaran.id);
            ps.setTimestamp(2, Timestamp.valueOf(waktu));
            ps.setBigDecimal(3, nominal);
            ps.setString(4, metode);
            ps.setString(5, status);
            if (idTerpilih != null) {
                ps.setInt(6, idTerpilih);
            }
            ps.executeUpdate();

            bersihkan();
            muatData();

        } catch (SQLException e) {
            tampilError("Gagal menyimpan data pembayaran", e);
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
                "Hapus pembayaran dengan ID " + idTerpilih + "?",
                "Konfirmasi", JOptionPane.YES_NO_OPTION);

        if (pilihan != JOptionPane.YES_OPTION) {
            return;
        }

        String sql = "DELETE FROM pembayaran WHERE id_pembayaran = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idTerpilih);
            ps.executeUpdate();

            bersihkan();
            muatData();

        } catch (SQLException e) {
            tampilError("Gagal menghapus data pembayaran", e);
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