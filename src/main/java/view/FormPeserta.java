package view;

import dao.PesertaDAO;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Peserta;

public class FormPeserta extends javax.swing.JFrame {

    public FormPeserta() {
     initComponents();
        pnlUtama.setBackground(new java.awt.Color(213, 217, 228));
        pnlUtama.setPreferredSize(new java.awt.Dimension(1100, 700));
        lblJudul.setOpaque(true);
        lblJudul.setBackground(java.awt.Color.BLACK);
        pack();
        setLocationRelativeTo(null);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupJenisKelamin = new javax.swing.ButtonGroup();
        pnlUtama = new javax.swing.JPanel();
        lblJudul = new javax.swing.JLabel();
        lblNim = new javax.swing.JLabel();
        lblNama = new javax.swing.JLabel();
        lblJk = new javax.swing.JLabel();
        lblProdi = new javax.swing.JLabel();
        lblNoHp = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtNim = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        rbLaki = new javax.swing.JRadioButton();
        rbPerempuan = new javax.swing.JRadioButton();
        txtProdi = new javax.swing.JTextField();
        txtNoHp = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Form Peserta - SIKURSUS");

        pnlUtama.setBackground(new java.awt.Color(153, 153, 153));
        pnlUtama.setPreferredSize(new java.awt.Dimension(1100, 700));

        lblJudul.setBackground(new java.awt.Color(153, 153, 153));
        lblJudul.setFont(new java.awt.Font("SansSerif", 0, 36)); // NOI18N
        lblJudul.setForeground(new java.awt.Color(255, 255, 255));
        lblJudul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJudul.setText("FORM PESERTA");
        lblJudul.setOpaque(true);

        lblNim.setText("NIM");

        lblNama.setText("Nama Peserta");

        lblJk.setText("Jenis Kelamin");

        lblProdi.setText("Prodi");

        lblNoHp.setText("No. HP");

        lblEmail.setText("Email");

        buttonGroupJenisKelamin.add(rbLaki);
        rbLaki.setText("Laki-laki");
        rbLaki.setOpaque(false);

        buttonGroupJenisKelamin.add(rbPerempuan);
        rbPerempuan.setText("Perempuan");
        rbPerempuan.setOpaque(false);

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlUtamaLayout = new javax.swing.GroupLayout(pnlUtama);
        pnlUtama.setLayout(pnlUtamaLayout);
        pnlUtamaLayout.setHorizontalGroup(
            pnlUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblJudul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlUtamaLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(pnlUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNim)
                    .addComponent(lblNama)
                    .addComponent(lblJk)
                    .addComponent(lblProdi)
                    .addComponent(lblNoHp)
                    .addComponent(lblEmail))
                .addGap(20, 20, 20)
                .addGroup(pnlUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNim)
                    .addComponent(txtNama)
                    .addGroup(pnlUtamaLayout.createSequentialGroup()
                        .addComponent(rbLaki)
                        .addGap(20, 20, 20)
                        .addComponent(rbPerempuan))
                    .addComponent(txtProdi)
                    .addComponent(txtNoHp)
                    .addComponent(txtEmail))
                .addGap(100, 100, 100))
            .addGroup(pnlUtamaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlUtamaLayout.setVerticalGroup(
            pnlUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUtamaLayout.createSequentialGroup()
                .addComponent(lblJudul, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(pnlUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNim)
                    .addComponent(txtNim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(pnlUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNama)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(pnlUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJk)
                    .addComponent(rbLaki)
                    .addComponent(rbPerempuan))
                .addGap(20, 20, 20)
                .addGroup(pnlUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProdi)
                    .addComponent(txtProdi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(pnlUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNoHp)
                    .addComponent(txtNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(pnlUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        getContentPane().add(pnlUtama);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        String nim = txtNim.getText().trim();
        String nama = txtNama.getText().trim();
        String prodi = txtProdi.getText().trim();
        String noHp = txtNoHp.getText().trim();
        String email = txtEmail.getText().trim();

        String jenisKelamin = "";
        if (rbLaki.isSelected()) {
            jenisKelamin = "L";
        } else if (rbPerempuan.isSelected()) {
            jenisKelamin = "P";
        }

        if (nim.isEmpty() || nama.isEmpty() || prodi.isEmpty()
                || jenisKelamin.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "NIM, nama, jenis kelamin, dan prodi wajib diisi.",
                    "Validasi",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        Peserta peserta;
        try {
            // id = 0 karena id_peserta diisi AUTO_INCREMENT oleh MySQL
            peserta = new Peserta(0, nama, noHp, nim, prodi, jenisKelamin, email);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Validasi",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        PesertaDAO dao = new PesertaDAO();

        try {
            boolean berhasil = dao.insert(peserta);
            if (berhasil) {
                JOptionPane.showMessageDialog(
                        this, "Data peserta berhasil disimpan."
                );
                clearForm();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Data tidak tersimpan.",
                        "Informasi",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Gagal menyimpan data:\n" + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void clearForm() {
        txtNim.setText("");
        txtNama.setText("");
        buttonGroupJenisKelamin.clearSelection();
        txtProdi.setText("");
        txtNoHp.setText("");
        txtEmail.setText("");
        txtNim.requestFocus();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPeserta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSimpan;
    private javax.swing.ButtonGroup buttonGroupJenisKelamin;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblJk;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblNim;
    private javax.swing.JLabel lblNoHp;
    private javax.swing.JLabel lblProdi;
    private javax.swing.JPanel pnlUtama;
    private javax.swing.JRadioButton rbLaki;
    private javax.swing.JRadioButton rbPerempuan;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNim;
    private javax.swing.JTextField txtNoHp;
    private javax.swing.JTextField txtProdi;
    // End of variables declaration//GEN-END:variables
}
