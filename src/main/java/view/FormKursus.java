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
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.text.NumberFormat;
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
 * Master Data Kursus: tampil, tambah, ubah, hapus.
 * Tabel: kursus(id_kursus, kode_kursus, nama_kursus, level, biaya).
 */
public class FormKursus extends JFrame {

    private final DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID", "Kode", "Nama Kursus", "Level", "Biaya"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private final JTable tabel = new JTable(model);

    private final JTextField txtKode = new JTextField(8);
    private final JTextField txtNama = new JTextField(18);
    private final JComboBox<String> cmbLevel = new JComboBox<>(
            new String[]{"Beginner", "Intermediate", "Advanced"});
    private final JTextField txtBiaya = new JTextField(10);

    private final NumberFormat formatRupiah =
            NumberFormat.getIntegerInstance(new Locale("id", "ID"));

    // null = mode tambah, selain itu = mode ubah data yang dipilih
    private Integer idTerpilih = null;

    public FormKursus() {
        setTitle("Master Data Kursus");
        setSize(900, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel judul = new JLabel("MASTER DATA KURSUS");
        judul.setFont(new Font("Arial", Font.BOLD, 22));
        judul.setHorizontalAlignment(SwingConstants.CENTER);

        cmbLevel.setEditable(true);

        // ----- form input -----
        JPanel panelInput = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        panelInput.add(new JLabel("Kode:"));
        panelInput.add(txtKode);
        panelInput.add(new JLabel("Nama:"));
        panelInput.add(txtNama);
        panelInput.add(new JLabel("Level:"));
        panelInput.add(cmbLevel);
        panelInput.add(new JLabel("Biaya (Rp):"));
        panelInput.add(txtBiaya);

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

        muatData();
    }

    private void muatData() {
        model.setRowCount(0);

        String sql = "SELECT id_kursus, kode_kursus, nama_kursus, level, biaya "
                + "FROM kursus ORDER BY id_kursus";

        try (Connection conn = Database.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id_kursus"),
                    rs.getString("kode_kursus"),
                    rs.getString("nama_kursus"),
                    rs.getString("level"),
                    formatRupiah.format(rs.getBigDecimal("biaya").longValue())
                });
            }

        } catch (SQLException e) {
            tampilError("Gagal memuat data kursus", e);
        }
    }

    private void isiFormDariTabel() {
        int baris = tabel.getSelectedRow();
        if (baris < 0) {
            return;
        }
        idTerpilih = (Integer) model.getValueAt(baris, 0);
        txtKode.setText(String.valueOf(model.getValueAt(baris, 1)));
        txtNama.setText(String.valueOf(model.getValueAt(baris, 2)));
        cmbLevel.setSelectedItem(String.valueOf(model.getValueAt(baris, 3)));

        // "500.000" -> "500000" (aman untuk format angka apa pun)
        String biaya = String.valueOf(model.getValueAt(baris, 4))
                .replaceAll("[^0-9]", "");
        txtBiaya.setText(biaya);
    }

    private void bersihkan() {
        idTerpilih = null;
        txtKode.setText("");
        txtNama.setText("");
        cmbLevel.setSelectedIndex(0);
        txtBiaya.setText("");
        tabel.clearSelection();
        txtKode.requestFocus();
    }

    private void simpan() {
        String kode = txtKode.getText().trim();
        String nama = txtNama.getText().trim();
        Object levelPilihan = cmbLevel.getSelectedItem();
        String level = (levelPilihan == null)
                ? "" : levelPilihan.toString().trim();
        String biayaTeks = txtBiaya.getText().trim()
                .replace(".", "").replace(",", "");

        if (kode.isEmpty() || nama.isEmpty() || level.isEmpty()) {
            peringatan("Kode, nama, dan level wajib diisi.");
            return;
        }

        BigDecimal biaya;
        try {
            biaya = new BigDecimal(biayaTeks);
        } catch (NumberFormatException e) {
            peringatan("Biaya harus berupa angka, contoh: 500000.");
            txtBiaya.requestFocus();
            return;
        }

        if (biaya.signum() < 0) {
            peringatan("Biaya tidak boleh negatif.");
            txtBiaya.requestFocus();
            return;
        }

        String sql = (idTerpilih == null)
                ? "INSERT INTO kursus "
                + "(kode_kursus, nama_kursus, level, biaya) VALUES (?, ?, ?, ?)"
                : "UPDATE kursus SET kode_kursus = ?, nama_kursus = ?, "
                + "level = ?, biaya = ? WHERE id_kursus = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, kode);
            ps.setString(2, nama);
            ps.setString(3, level);
            ps.setBigDecimal(4, biaya);
            if (idTerpilih != null) {
                ps.setInt(5, idTerpilih);
            }
            ps.executeUpdate();

            bersihkan();
            muatData();

        } catch (SQLException e) {
            tampilError("Gagal menyimpan data kursus", e);
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
                "Hapus kursus \"" + txtNama.getText() + "\"?",
                "Konfirmasi", JOptionPane.YES_NO_OPTION);

        if (pilihan != JOptionPane.YES_OPTION) {
            return;
        }

        String sql = "DELETE FROM kursus WHERE id_kursus = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idTerpilih);
            ps.executeUpdate();

            bersihkan();
            muatData();

        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(this,
                    "Kursus ini masih dipakai di data kelas, "
                    + "jadi tidak bisa dihapus.",
                    "Tidak bisa dihapus", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
            tampilError("Gagal menghapus data kursus", e);
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