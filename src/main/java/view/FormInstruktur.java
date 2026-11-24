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
 * Master Data Instruktur: tampil, tambah, ubah, hapus.
 * Tabel: instruktur(id_instruktur, nama).
 */
public class FormInstruktur extends JFrame {

    private final DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID", "Nama Instruktur"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private final JTable tabel = new JTable(model);
    private final JTextField txtNama = new JTextField(25);

    // null = mode tambah, selain itu = mode ubah data yang dipilih
    private Integer idTerpilih = null;

    public FormInstruktur() {
        setTitle("Master Data Instruktur");
        setSize(700, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel judul = new JLabel("MASTER DATA INSTRUKTUR");
        judul.setFont(new Font("Arial", Font.BOLD, 22));
        judul.setHorizontalAlignment(SwingConstants.CENTER);

        // ----- form input -----
        JPanel panelInput = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        panelInput.add(new JLabel("Nama Instruktur:"));
        panelInput.add(txtNama);

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
        tabel.getColumnModel().getColumn(0).setMaxWidth(80);

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

        String sql = "SELECT id_instruktur, nama FROM instruktur "
                + "ORDER BY id_instruktur";

        try (Connection conn = Database.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id_instruktur"),
                    rs.getString("nama")
                });
            }

        } catch (SQLException e) {
            tampilError("Gagal memuat data instruktur", e);
        }
    }

    private void isiFormDariTabel() {
        int baris = tabel.getSelectedRow();
        if (baris < 0) {
            return;
        }
        idTerpilih = (Integer) model.getValueAt(baris, 0);
        txtNama.setText(String.valueOf(model.getValueAt(baris, 1)));
    }

    private void bersihkan() {
        idTerpilih = null;
        txtNama.setText("");
        tabel.clearSelection();
        txtNama.requestFocus();
    }

    private void simpan() {
        String nama = txtNama.getText().trim();

        if (nama.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Nama instruktur tidak boleh kosong.",
                    "Validasi", JOptionPane.WARNING_MESSAGE);
            txtNama.requestFocus();
            return;
        }

        String sql = (idTerpilih == null)
                ? "INSERT INTO instruktur (nama) VALUES (?)"
                : "UPDATE instruktur SET nama = ? WHERE id_instruktur = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nama);
            if (idTerpilih != null) {
                ps.setInt(2, idTerpilih);
            }
            ps.executeUpdate();

            bersihkan();
            muatData();

        } catch (SQLException e) {
            tampilError("Gagal menyimpan data instruktur", e);
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
                "Hapus instruktur \"" + txtNama.getText() + "\"?",
                "Konfirmasi", JOptionPane.YES_NO_OPTION);

        if (pilihan != JOptionPane.YES_OPTION) {
            return;
        }

        String sql = "DELETE FROM instruktur WHERE id_instruktur = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idTerpilih);
            ps.executeUpdate();

            bersihkan();
            muatData();

        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(this,
                    "Instruktur ini masih dipakai di data kelas, "
                    + "jadi tidak bisa dihapus.",
                    "Tidak bisa dihapus", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
            tampilError("Gagal menghapus data instruktur", e);
        }
    }

    private void tampilError(String pesan, SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this,
                pesan + ":\n" + e.getMessage(),
                "Kesalahan Database", JOptionPane.ERROR_MESSAGE);
    }
}