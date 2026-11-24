package view;

import config.Database;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
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
 * Master Data Kelas: tampil, tambah, ubah, hapus.
 * Tabel: kelas(id_kelas, nama_kelas, id_kursus, id_instruktur).
 * Kursus dan instruktur dipilih lewat combo box.
 */
public class FormKelas extends JFrame {

    /** Item combo box: menyimpan id, menampilkan nama. */
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

    // Kolom 4 dan 5 (id_kursus, id_instruktur) disimpan di model tapi
    // disembunyikan dari tampilan tabel.
    private final DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID", "Nama Kelas", "Kursus", "Instruktur",
                "id_kursus", "id_instruktur"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private final JTable tabel = new JTable(model);

    private final JTextField txtNama = new JTextField(15);
    private final JComboBox<Item> cmbKursus = new JComboBox<>();
    private final JComboBox<Item> cmbInstruktur = new JComboBox<>();

    // null = mode tambah, selain itu = mode ubah data yang dipilih
    private Integer idTerpilih = null;

    public FormKelas() {
        setTitle("Master Data Kelas");
        setSize(900, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel judul = new JLabel("MASTER DATA KELAS");
        judul.setFont(new Font("Arial", Font.BOLD, 22));
        judul.setHorizontalAlignment(SwingConstants.CENTER);

        // ----- form input -----
        JPanel panelInput = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        panelInput.add(new JLabel("Nama Kelas:"));
        panelInput.add(txtNama);
        panelInput.add(new JLabel("Kursus:"));
        panelInput.add(cmbKursus);
        panelInput.add(new JLabel("Instruktur:"));
        panelInput.add(cmbInstruktur);

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

        // Sembunyikan kolom id_instruktur dulu (indeks lebih besar),
        // baru id_kursus, supaya indeks kolom tidak bergeser.
        tabel.removeColumn(tabel.getColumnModel().getColumn(5));
        tabel.removeColumn(tabel.getColumnModel().getColumn(4));

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
        cmbKursus.removeAllItems();
        cmbInstruktur.removeAllItems();

        try (Connection conn = Database.getConnection();
             Statement st = conn.createStatement()) {

            try (ResultSet rs = st.executeQuery(
                    "SELECT id_kursus, nama_kursus FROM kursus "
                    + "ORDER BY nama_kursus")) {
                while (rs.next()) {
                    cmbKursus.addItem(new Item(
                            rs.getInt("id_kursus"),
                            rs.getString("nama_kursus")));
                }
            }

            try (ResultSet rs = st.executeQuery(
                    "SELECT id_instruktur, nama FROM instruktur "
                    + "ORDER BY nama")) {
                while (rs.next()) {
                    cmbInstruktur.addItem(new Item(
                            rs.getInt("id_instruktur"),
                            rs.getString("nama")));
                }
            }

        } catch (SQLException e) {
            tampilError("Gagal memuat pilihan kursus/instruktur", e);
        }
    }

    private void muatData() {
        model.setRowCount(0);

        String sql = "SELECT kl.id_kelas, kl.nama_kelas, "
                + "k.nama_kursus, i.nama AS nama_instruktur, "
                + "kl.id_kursus, kl.id_instruktur "
                + "FROM kelas kl "
                + "JOIN kursus k ON k.id_kursus = kl.id_kursus "
                + "JOIN instruktur i ON i.id_instruktur = kl.id_instruktur "
                + "ORDER BY kl.id_kelas";

        try (Connection conn = Database.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id_kelas"),
                    rs.getString("nama_kelas"),
                    rs.getString("nama_kursus"),
                    rs.getString("nama_instruktur"),
                    rs.getInt("id_kursus"),
                    rs.getInt("id_instruktur")
                });
            }

        } catch (SQLException e) {
            tampilError("Gagal memuat data kelas", e);
        }
    }

    private void isiFormDariTabel() {
        int viewRow = tabel.getSelectedRow();
        if (viewRow < 0) {
            return;
        }
        int baris = tabel.convertRowIndexToModel(viewRow);

        idTerpilih = (Integer) model.getValueAt(baris, 0);
        txtNama.setText(String.valueOf(model.getValueAt(baris, 1)));
        pilihItem(cmbKursus, (Integer) model.getValueAt(baris, 4));
        pilihItem(cmbInstruktur, (Integer) model.getValueAt(baris, 5));
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
        txtNama.setText("");
        if (cmbKursus.getItemCount() > 0) {
            cmbKursus.setSelectedIndex(0);
        }
        if (cmbInstruktur.getItemCount() > 0) {
            cmbInstruktur.setSelectedIndex(0);
        }
        tabel.clearSelection();
        txtNama.requestFocus();
    }

    private void simpan() {
        String nama = txtNama.getText().trim();
        Item kursus = (Item) cmbKursus.getSelectedItem();
        Item instruktur = (Item) cmbInstruktur.getSelectedItem();

        if (nama.isEmpty()) {
            peringatan("Nama kelas tidak boleh kosong.");
            txtNama.requestFocus();
            return;
        }

        if (kursus == null || instruktur == null) {
            peringatan("Data kursus dan instruktur harus diisi dulu "
                    + "sebelum membuat kelas.");
            return;
        }

        String sql = (idTerpilih == null)
                ? "INSERT INTO kelas (nama_kelas, id_kursus, id_instruktur) "
                + "VALUES (?, ?, ?)"
                : "UPDATE kelas SET nama_kelas = ?, id_kursus = ?, "
                + "id_instruktur = ? WHERE id_kelas = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nama);
            ps.setInt(2, kursus.id);
            ps.setInt(3, instruktur.id);
            if (idTerpilih != null) {
                ps.setInt(4, idTerpilih);
            }
            ps.executeUpdate();

            bersihkan();
            muatData();

        } catch (SQLException e) {
            tampilError("Gagal menyimpan data kelas", e);
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
                "Hapus kelas \"" + txtNama.getText() + "\"?",
                "Konfirmasi", JOptionPane.YES_NO_OPTION);

        if (pilihan != JOptionPane.YES_OPTION) {
            return;
        }

        String sql = "DELETE FROM kelas WHERE id_kelas = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idTerpilih);
            ps.executeUpdate();

            bersihkan();
            muatData();

        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(this,
                    "Kelas ini sudah punya data pendaftaran, "
                    + "jadi tidak bisa dihapus.",
                    "Tidak bisa dihapus", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
            tampilError("Gagal menghapus data kelas", e);
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