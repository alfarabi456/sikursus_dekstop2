package view;

import dao.PesertaDAO;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import model.Peserta;

public class FormPeserta extends javax.swing.JFrame {

    private final PesertaDAO dao = new PesertaDAO();
    private int selectedId = -1;
    private DefaultTableModel tableModel;

    private final Color HIJAU_TUA = new Color(22, 101, 52);
    private final Color PUTIH = Color.WHITE;

    public FormPeserta() {
        initComponents();

        tblPeserta.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                loadSelectedRow();
            }
        });

        configureTable();
        loadTable();
        clearForm();

        setLocationRelativeTo(null);
    }

    /**
     * Bagian di antara GEN-BEGIN dan GEN-END dikelola NetBeans dari file .form.
     * Jangan diedit manual.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        bgJk = new javax.swing.ButtonGroup();
        panelRoot = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        panelCenter = new javax.swing.JPanel();
        panelForm = new javax.swing.JPanel();
        lblFormTitle = new javax.swing.JLabel();
        lblNim = new javax.swing.JLabel();
        txtNim = new javax.swing.JTextField();
        lblNama = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        lblJk = new javax.swing.JLabel();
        panelJk = new javax.swing.JPanel();
        rbLaki = new javax.swing.JRadioButton();
        rbPerempuan = new javax.swing.JRadioButton();
        lblProdi = new javax.swing.JLabel();
        txtProdi = new javax.swing.JTextField();
        lblNoHp = new javax.swing.JLabel();
        txtNoHp = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        panelButtons = new javax.swing.JPanel();
        btnSimpan = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        panelData = new javax.swing.JPanel();
        panelTopData = new javax.swing.JPanel();
        lblDataTitle = new javax.swing.JLabel();
        panelSearch = new javax.swing.JPanel();
        lblCari = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPeserta = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Master Peserta - SIKURSUS Desktop");
        setMinimumSize(new java.awt.Dimension(1050, 720));

        panelRoot.setBackground(new java.awt.Color(245, 247, 246));
        panelRoot.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 14, 14, 14));
        panelRoot.setLayout(new java.awt.BorderLayout(12, 12));

        lblTitle.setBackground(new java.awt.Color(22, 101, 52));
        lblTitle.setFont(new java.awt.Font("SansSerif", 1, 25)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("MASTER DATA PESERTA");
        lblTitle.setOpaque(true);
        lblTitle.setPreferredSize(new java.awt.Dimension(0, 62));
        panelRoot.add(lblTitle, java.awt.BorderLayout.NORTH);

        panelCenter.setOpaque(false);
        panelCenter.setLayout(new java.awt.BorderLayout(10, 10));

        panelForm.setBackground(new java.awt.Color(255, 255, 255));
        panelForm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 220, 205)));
        panelForm.setLayout(new java.awt.GridBagLayout());

        lblFormTitle.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblFormTitle.setForeground(new java.awt.Color(22, 101, 52));
        lblFormTitle.setText("FORM PESERTA");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 5, 12, 0);
        panelForm.add(lblFormTitle, gridBagConstraints);

        lblNim.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblNim.setText("NIM");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 5, 4, 12);
        panelForm.add(lblNim, gridBagConstraints);

        txtNim.setPreferredSize(new java.awt.Dimension(100, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 5, 4, 5);
        panelForm.add(txtNim, gridBagConstraints);

        lblNama.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblNama.setText("Nama Peserta");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 5, 4, 12);
        panelForm.add(lblNama, gridBagConstraints);

        txtNama.setPreferredSize(new java.awt.Dimension(100, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 5, 4, 5);
        panelForm.add(txtNama, gridBagConstraints);

        lblJk.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblJk.setText("Jenis Kelamin");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 5, 4, 12);
        panelForm.add(lblJk, gridBagConstraints);

        panelJk.setBackground(new java.awt.Color(255, 255, 255));
        panelJk.setLayout(new java.awt.GridLayout(1, 2, 8, 0));

        rbLaki.setBackground(new java.awt.Color(255, 255, 255));
        bgJk.add(rbLaki);
        rbLaki.setText("Laki-laki");
        panelJk.add(rbLaki);

        rbPerempuan.setBackground(new java.awt.Color(255, 255, 255));
        bgJk.add(rbPerempuan);
        rbPerempuan.setText("Perempuan");
        panelJk.add(rbPerempuan);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 5, 4, 5);
        panelForm.add(panelJk, gridBagConstraints);

        lblProdi.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblProdi.setText("Program Studi");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 5, 4, 12);
        panelForm.add(lblProdi, gridBagConstraints);

        txtProdi.setPreferredSize(new java.awt.Dimension(100, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 5, 4, 5);
        panelForm.add(txtProdi, gridBagConstraints);

        lblNoHp.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblNoHp.setText("Nomor HP");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 5, 4, 12);
        panelForm.add(lblNoHp, gridBagConstraints);

        txtNoHp.setPreferredSize(new java.awt.Dimension(100, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 5, 4, 5);
        panelForm.add(txtNoHp, gridBagConstraints);

        lblEmail.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblEmail.setText("Email");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 5, 4, 12);
        panelForm.add(lblEmail, gridBagConstraints);

        txtEmail.setPreferredSize(new java.awt.Dimension(100, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 5, 4, 5);
        panelForm.add(txtEmail, gridBagConstraints);

        panelButtons.setBackground(new java.awt.Color(255, 255, 255));
        panelButtons.setLayout(new java.awt.GridLayout(1, 4, 10, 0));

        btnSimpan.setBackground(new java.awt.Color(34, 197, 94));
        btnSimpan.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpan.setText("Simpan");
        btnSimpan.setFocusPainted(false);
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        panelButtons.add(btnSimpan);

        btnUbah.setBackground(new java.awt.Color(59, 130, 246));
        btnUbah.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnUbah.setForeground(new java.awt.Color(255, 255, 255));
        btnUbah.setText("Ubah");
        btnUbah.setFocusPainted(false);
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });
        panelButtons.add(btnUbah);

        btnHapus.setBackground(new java.awt.Color(239, 68, 68));
        btnHapus.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnHapus.setForeground(new java.awt.Color(255, 255, 255));
        btnHapus.setText("Hapus");
        btnHapus.setFocusPainted(false);
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        panelButtons.add(btnHapus);

        btnReset.setBackground(new java.awt.Color(107, 114, 128));
        btnReset.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("Reset");
        btnReset.setFocusPainted(false);
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        panelButtons.add(btnReset);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 5, 12, 5);
        panelForm.add(panelButtons, gridBagConstraints);

        panelCenter.add(panelForm, java.awt.BorderLayout.NORTH);

        panelData.setBackground(new java.awt.Color(255, 255, 255));
        panelData.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 220, 205)));
        panelData.setLayout(new java.awt.BorderLayout(8, 8));

        panelTopData.setBackground(new java.awt.Color(255, 255, 255));
        panelTopData.setLayout(new java.awt.BorderLayout(8, 8));

        lblDataTitle.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblDataTitle.setForeground(new java.awt.Color(22, 101, 52));
        lblDataTitle.setText("DATA PESERTA");
        panelTopData.add(lblDataTitle, java.awt.BorderLayout.NORTH);

        panelSearch.setBackground(new java.awt.Color(255, 255, 255));
        panelSearch.setLayout(new java.awt.BorderLayout(8, 0));

        lblCari.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblCari.setText("Pencarian");
        panelSearch.add(lblCari, java.awt.BorderLayout.WEST);

        txtCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariActionPerformed(evt);
            }
        });
        panelSearch.add(txtCari, java.awt.BorderLayout.CENTER);

        btnCari.setBackground(new java.awt.Color(22, 101, 52));
        btnCari.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnCari.setForeground(new java.awt.Color(255, 255, 255));
        btnCari.setText("Cari");
        btnCari.setFocusPainted(false);
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });
        panelSearch.add(btnCari, java.awt.BorderLayout.EAST);

        panelTopData.add(panelSearch, java.awt.BorderLayout.CENTER);

        panelData.add(panelTopData, java.awt.BorderLayout.NORTH);

        tblPeserta.setRowHeight(28);
        jScrollPane1.setViewportView(tblPeserta);

        panelData.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        panelCenter.add(panelData, java.awt.BorderLayout.CENTER);

        panelRoot.add(panelCenter, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelRoot, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        insertData();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        updateData();
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        deleteData();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        txtCari.setText("");
        loadTable();
        clearForm();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        searchData();
    }//GEN-LAST:event_btnCariActionPerformed

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        searchData();
    }//GEN-LAST:event_txtCariActionPerformed

    // ===================== LOGIKA (tidak diubah dari FormPeserta) =====================

    private void configureTable() {
        tableModel = new DefaultTableModel(
                new Object[][] {},
                new String[] {"ID", "NIM", "Nama", "JK", "Prodi", "No. HP", "Email"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblPeserta.setModel(tableModel);
        tblPeserta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblPeserta.setRowHeight(28);
        tblPeserta.getTableHeader().setBackground(HIJAU_TUA);
        tblPeserta.getTableHeader().setForeground(PUTIH);
        tblPeserta.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
    }

    private void loadTable() {
        try {
            fillTable(dao.findAll());
        } catch (SQLException e) {
            showError("Gagal mengambil data peserta:\n" + e.getMessage());
        }
    }

    private void fillTable(List<Peserta> list) {
        tableModel.setRowCount(0);

        for (Peserta p : list) {
            tableModel.addRow(new Object[]{
                p.getId(),
                p.getNim(),
                p.getNama(),
                p.getJenisKelamin(),
                p.getProdi(),
                p.getNoHp(),
                p.getEmail()
            });
        }
    }

    private void loadSelectedRow() {
        int row = tblPeserta.getSelectedRow();
        if (row < 0) return;

        selectedId = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
        txtNim.setText(value(row, 1));
        txtNama.setText(value(row, 2));

        String jk = value(row, 3);
        rbLaki.setSelected("L".equals(jk));
        rbPerempuan.setSelected("P".equals(jk));

        txtProdi.setText(value(row, 4));
        txtNoHp.setText(value(row, 5));
        txtEmail.setText(value(row, 6));

        btnUbah.setEnabled(true);
        btnHapus.setEnabled(true);
    }

    private String value(int row, int col) {
        Object value = tableModel.getValueAt(row, col);
        return value == null ? "" : value.toString();
    }

    private String getJenisKelamin() {
        if (rbLaki.isSelected()) return "L";
        if (rbPerempuan.isSelected()) return "P";
        return null;
    }

    private Peserta getFormData() {
        return new Peserta(
                Math.max(selectedId, 0),    
                txtNama.getText().trim(),
                txtNoHp.getText().trim(),
                txtNim.getText().trim(),
                txtProdi.getText().trim(),
                getJenisKelamin(),
                txtEmail.getText().trim()
        );
    }

    private boolean validateForm() {
        if (txtNim.getText().trim().isEmpty()) {
            warning("NIM wajib diisi.");
            txtNim.requestFocus();
            return false;
        }
        if (txtNama.getText().trim().isEmpty()) {
            warning("Nama wajib diisi.");
            txtNama.requestFocus();
            return false;
        }
        if (getJenisKelamin() == null) {
            warning("Jenis kelamin wajib dipilih.");
            return false;
        }
        if (txtProdi.getText().trim().isEmpty()) {
            warning("Program studi wajib diisi.");
            txtProdi.requestFocus();
            return false;
        }
        return true;
    }

    private void insertData() {
        if (!validateForm()) return;

        try {
            Peserta p = getFormData();
            p.setId(0);

            if (dao.insert(p)) {
                JOptionPane.showMessageDialog(this, "Data peserta berhasil disimpan.");
                refreshData();
            }
        } catch (IllegalArgumentException e) {
            warning(e.getMessage());
        } catch (SQLException e) {
            showError("Gagal menyimpan data:\n" + e.getMessage());
        }
    }

    private void updateData() {
        if (selectedId <= 0) {
            warning("Pilih data yang ingin diubah pada tabel.");
            return;
        }

        if (!validateForm()) return;

        try {
            if (dao.update(getFormData())) {
                JOptionPane.showMessageDialog(this, "Data peserta berhasil diubah.");
                refreshData();
            }
        } catch (IllegalArgumentException e) {
            warning(e.getMessage());
        } catch (SQLException e) {
            showError("Gagal mengubah data:\n" + e.getMessage());
        }
    }

    private void deleteData() {
        if (selectedId <= 0) {
            warning("Pilih data yang ingin dihapus pada tabel.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Yakin ingin menghapus data peserta ini?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm != JOptionPane.YES_OPTION) return;

        try {
            if (dao.delete(selectedId)) {
                JOptionPane.showMessageDialog(this, "Data peserta berhasil dihapus.");
                refreshData();
            }
        } catch (SQLException e) {
            showError("Gagal menghapus data:\n" + e.getMessage());
        }
    }

    private void searchData() {
        String keyword = txtCari.getText().trim();

        try {
            if (keyword.isEmpty()) {
                loadTable();
            } else {
                fillTable(dao.search(keyword));
            }
        } catch (SQLException e) {
            showError("Gagal melakukan pencarian:\n" + e.getMessage());
        }
    }

    private void refreshData() {
        loadTable();
        clearForm();
        txtCari.setText("");
    }

    private void clearForm() {
        selectedId = -1;
        txtNim.setText("");
        txtNama.setText("");
        bgJk.clearSelection();
        txtProdi.setText("");
        txtNoHp.setText("");
        txtEmail.setText("");
        tblPeserta.clearSelection();
        btnUbah.setEnabled(false);
        btnHapus.setEnabled(false);
        txtNim.requestFocus();
    }

    private void warning(String message) {
        JOptionPane.showMessageDialog(this, message, "Validasi",
                JOptionPane.WARNING_MESSAGE);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Database Error",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FormPeserta().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgJk;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUbah;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCari;
    private javax.swing.JLabel lblDataTitle;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFormTitle;
    private javax.swing.JLabel lblJk;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblNim;
    private javax.swing.JLabel lblNoHp;
    private javax.swing.JLabel lblProdi;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel panelButtons;
    private javax.swing.JPanel panelCenter;
    private javax.swing.JPanel panelData;
    private javax.swing.JPanel panelForm;
    private javax.swing.JPanel panelJk;
    private javax.swing.JPanel panelRoot;
    private javax.swing.JPanel panelSearch;
    private javax.swing.JPanel panelTopData;
    private javax.swing.JRadioButton rbLaki;
    private javax.swing.JRadioButton rbPerempuan;
    private javax.swing.JTable tblPeserta;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNim;
    private javax.swing.JTextField txtNoHp;
    private javax.swing.JTextField txtProdi;
    // End of variables declaration//GEN-END:variables
}
