import java.awt.event.KeyEvent;

/**
 * SIKURSUS Desktop - Form Pendaftaran Kursus (JFrame Form NetBeans).
 */
public class FormPendaftaranKursus extends javax.swing.JFrame {

    // Biaya dasar Java Desktop (500.000) dan Sertifikat (50.000) sesuai gambar.
    // Nilai lainnya hanya contoh, sesuaikan sendiri.
    private static final int BIAYA_MODUL = 75000;
    private static final int BIAYA_SERTIFIKAT = 50000;
    private static final int BIAYA_REKAMAN = 100000;

    public FormPendaftaranKursus() {
        initComponents();
        setLocationRelativeTo(null);
        updateRingkasan();
    }

    private int biayaDasar(String kursus) {
        switch (kursus) {
            case "Java Desktop":     return 500000;
            case "Java Web":         return 600000;
            case "Basis Data MySQL": return 450000;
            case "Python Dasar":     return 400000;
            default:                 return 0;
        }
    }

    private String rupiah(int nilai) {
        return "Rp" + String.format("%,d", nilai).replace(',', '.');
    }

    private void updateRingkasan() {
        String kursus = (String) cbKursus.getSelectedItem();
        int dasar = biayaDasar(kursus);
        int total = dasar;
        String garis = "==============================\n";

        StringBuilder sb = new StringBuilder();
        sb.append("PENDAFTARAN & PENYIMPANAN DATA\n").append(garis);
        sb.append(String.format("%-14s: %s%n", "NIM", txtNim.getText()));
        sb.append(String.format("%-14s: %s%n", "Nama", txtNama.getText()));
        sb.append(String.format("%-14s: %s%n", "Gender", rbLaki.isSelected() ? "Laki-laki" : "Perempuan"));
        sb.append(String.format("%-14s: %s%n", "Prodi", txtProdi.getText()));
        sb.append(String.format("%-14s: %s%n", "No HP", txtHp.getText()));
        sb.append(String.format("%-14s: %s%n", "Email", txtEmail.getText()));
        sb.append(String.format("%-14s: %s%n", "Kursus", kursus));
        sb.append(String.format("%-14s: %s%n", "Biaya Dasar", rupiah(dasar)));
        sb.append("Fasilitas:\n");

        if (chkModul.isSelected()) {
            sb.append("- Modul Cetak\n");
            total += BIAYA_MODUL;
        }
        if (chkSertifikat.isSelected()) {
            sb.append("- Sertifikat\n");
            total += BIAYA_SERTIFIKAT;
        }
        if (chkRekaman.isSelected()) {
            sb.append("- Akses Rekaman\n");
            total += BIAYA_REKAMAN;
        }

        sb.append(garis);
        sb.append(String.format("%-14s: %s", "TOTAL BIAYA", rupiah(total)));
        txtRingkasan.setText(sb.toString());
        txtRingkasan.setCaretPosition(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlHeader = new javax.swing.JPanel();
        lblJudul = new javax.swing.JLabel();
        lblNim = new javax.swing.JLabel();
        txtNim = new javax.swing.JTextField();
        lblNama = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        lblJk = new javax.swing.JLabel();
        rbLaki = new javax.swing.JRadioButton();
        rbPerempuan = new javax.swing.JRadioButton();
        lblProdi = new javax.swing.JLabel();
        txtProdi = new javax.swing.JTextField();
        lblHp = new javax.swing.JLabel();
        txtHp = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblKode = new javax.swing.JLabel();
        txtKode = new javax.swing.JPasswordField();
        lblKursus = new javax.swing.JLabel();
        cbKursus = new javax.swing.JComboBox<>();
        lblFasilitas = new javax.swing.JLabel();
        chkModul = new javax.swing.JCheckBox();
        chkSertifikat = new javax.swing.JCheckBox();
        chkRekaman = new javax.swing.JCheckBox();
        lblRingkasan = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtRingkasan = new javax.swing.JTextArea();
        btnSimpan = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SIKURSUS Desktop - Pendaftaran Kursus");
        setPreferredSize(new java.awt.Dimension(660, 720));
        setResizable(false);
        getContentPane().setLayout(null);

        pnlHeader.setBackground(new java.awt.Color(21, 101, 192));
        pnlHeader.setLayout(null);

        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblJudul.setForeground(new java.awt.Color(255, 255, 255));
        lblJudul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJudul.setText("Form Pendaftaran Kursus");
        pnlHeader.add(lblJudul);
        lblJudul.setBounds(0, 0, 660, 50);

        getContentPane().add(pnlHeader);
        pnlHeader.setBounds(0, 0, 660, 50);

        lblNim.setText("NIM");
        getContentPane().add(lblNim);
        lblNim.setBounds(30, 70, 95, 28);

        txtNim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fieldKeyReleased(evt);
            }
        });
        getContentPane().add(txtNim);
        txtNim.setBounds(130, 70, 490, 28);

        lblNama.setText("Nama Peserta");
        getContentPane().add(lblNama);
        lblNama.setBounds(30, 110, 95, 28);

        txtNama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fieldKeyReleased(evt);
            }
        });
        getContentPane().add(txtNama);
        txtNama.setBounds(130, 110, 490, 28);

        lblJk.setText("Jenis Kelamin");
        getContentPane().add(lblJk);
        lblJk.setBounds(30, 150, 95, 28);

        buttonGroup1.add(rbLaki);
        rbLaki.setSelected(true);
        rbLaki.setText("Laki-laki");
        rbLaki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputActionPerformed(evt);
            }
        });
        getContentPane().add(rbLaki);
        rbLaki.setBounds(130, 150, 90, 28);

        buttonGroup1.add(rbPerempuan);
        rbPerempuan.setText("Perempuan");
        rbPerempuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputActionPerformed(evt);
            }
        });
        getContentPane().add(rbPerempuan);
        rbPerempuan.setBounds(230, 150, 100, 28);

        lblProdi.setText("Program Studi");
        getContentPane().add(lblProdi);
        lblProdi.setBounds(30, 190, 95, 28);

        txtProdi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fieldKeyReleased(evt);
            }
        });
        getContentPane().add(txtProdi);
        txtProdi.setBounds(130, 190, 490, 28);

        lblHp.setText("Nomor HP");
        getContentPane().add(lblHp);
        lblHp.setBounds(30, 230, 95, 28);

        txtHp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fieldKeyReleased(evt);
            }
        });
        getContentPane().add(txtHp);
        txtHp.setBounds(130, 230, 490, 28);

        lblEmail.setText("Email");
        getContentPane().add(lblEmail);
        lblEmail.setBounds(30, 270, 95, 28);

        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fieldKeyReleased(evt);
            }
        });
        getContentPane().add(txtEmail);
        txtEmail.setBounds(130, 270, 490, 28);

        lblKode.setText("Kode Akses");
        getContentPane().add(lblKode);
        lblKode.setBounds(30, 310, 95, 28);
        getContentPane().add(txtKode);
        txtKode.setBounds(130, 310, 490, 28);

        lblKursus.setText("Kursus");
        getContentPane().add(lblKursus);
        lblKursus.setBounds(30, 350, 95, 28);

        cbKursus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Java Desktop", "Java Web", "Basis Data MySQL", "Python Dasar" }));
        cbKursus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputActionPerformed(evt);
            }
        });
        getContentPane().add(cbKursus);
        cbKursus.setBounds(130, 350, 490, 28);

        lblFasilitas.setText("Fasilitas");
        getContentPane().add(lblFasilitas);
        lblFasilitas.setBounds(30, 390, 95, 28);

        chkModul.setText("Modul Cetak");
        chkModul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputActionPerformed(evt);
            }
        });
        getContentPane().add(chkModul);
        chkModul.setBounds(130, 390, 100, 28);

        chkSertifikat.setText("Sertifikat");
        chkSertifikat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputActionPerformed(evt);
            }
        });
        getContentPane().add(chkSertifikat);
        chkSertifikat.setBounds(240, 390, 100, 28);

        chkRekaman.setText("Akses Rekaman");
        chkRekaman.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputActionPerformed(evt);
            }
        });
        getContentPane().add(chkRekaman);
        chkRekaman.setBounds(350, 390, 120, 28);

        lblRingkasan.setText("Ringkasan");
        getContentPane().add(lblRingkasan);
        lblRingkasan.setBounds(30, 435, 95, 28);

        txtRingkasan.setColumns(20);
        txtRingkasan.setEditable(false);
        txtRingkasan.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        txtRingkasan.setRows(5);
        jScrollPane1.setViewportView(txtRingkasan);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(130, 430, 490, 200);

        btnSimpan.setBackground(new java.awt.Color(33, 150, 243));
        btnSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        getContentPane().add(btnSimpan);
        btnSimpan.setBounds(190, 650, 100, 30);

        btnReset.setBackground(new java.awt.Color(239, 83, 80));
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        getContentPane().add(btnReset);
        btnReset.setBounds(370, 650, 100, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldKeyReleased
        updateRingkasan();
    }//GEN-LAST:event_fieldKeyReleased

    private void inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputActionPerformed
        updateRingkasan();
    }//GEN-LAST:event_inputActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        if (txtNim.getText().trim().isEmpty() || txtNama.getText().trim().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "NIM dan Nama Peserta wajib diisi.",
                    "Validasi", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        // TODO: simpan ke database db_sikursus (JDBC MySQL) di sini.
        javax.swing.JOptionPane.showMessageDialog(this, "Data berhasil disimpan.",
                "Sukses", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        txtNim.setText("");
        txtNama.setText("");
        rbLaki.setSelected(true);
        txtProdi.setText("");
        txtHp.setText("");
        txtEmail.setText("");
        txtKode.setText("");
        cbKursus.setSelectedIndex(0);
        chkModul.setSelected(false);
        chkSertifikat.setSelected(false);
        chkRekaman.setSelected(false);
        updateRingkasan();
    }//GEN-LAST:event_btnResetActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(FormPendaftaranKursus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPendaftaranKursus().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSimpan;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbKursus;
    private javax.swing.JCheckBox chkModul;
    private javax.swing.JCheckBox chkRekaman;
    private javax.swing.JCheckBox chkSertifikat;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFasilitas;
    private javax.swing.JLabel lblHp;
    private javax.swing.JLabel lblJk;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblKode;
    private javax.swing.JLabel lblKursus;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblNim;
    private javax.swing.JLabel lblProdi;
    private javax.swing.JLabel lblRingkasan;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JRadioButton rbLaki;
    private javax.swing.JRadioButton rbPerempuan;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHp;
    private javax.swing.JPasswordField txtKode;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNim;
    private javax.swing.JTextField txtProdi;
    private javax.swing.JTextArea txtRingkasan;
    // End of variables declaration//GEN-END:variables
}
