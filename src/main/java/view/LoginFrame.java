
package view;

import dao.UserDAO;
import model.User;
import util.UserSession;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.Arrays;

public class LoginFrame extends JFrame {

    // =====================================
    // DEKLARASI KOMPONEN
    // =====================================

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    private final UserDAO userDAO = new UserDAO();

    // =====================================
    // WARNA APLIKASI
    // =====================================

    private final Color NAVY = new Color(20, 40, 75);
    private final Color BLUE = new Color(42, 91, 160);
    private final Color LIGHT = new Color(245, 247, 251);

    // =====================================
    // CONSTRUCTOR
    // =====================================

    public LoginFrame() {
        initComponents();

        // Mengatur ikon aplikasi
        java.net.URL iconURL =
                getClass().getResource("/icons/icon_sikursus.png");

        if (iconURL != null) {
            setIconImage(new ImageIcon(iconURL).getImage());
            System.out.println("IKON BERHASIL DIBACA: " + iconURL);
        } else {
            System.out.println("IKON TIDAK DITEMUKAN!");

            JOptionPane.showMessageDialog(
                    this,
                    "File icon_sikursus.png tidak ditemukan!",
                    "Pemeriksaan Ikon",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================
    // INISIALISASI KOMPONEN
    // =====================================

    private void initComponents() {

        setTitle("SIKURSUS - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 520);
        setLocationRelativeTo(null);
        setResizable(false);

        // PANEL UTAMA
        JPanel root = new JPanel(new GridLayout(1, 2));
        root.setBackground(Color.WHITE);

        // =====================================
        // PANEL KIRI
        // =====================================

        JPanel leftPanel = new JPanel();

        leftPanel.setBackground(NAVY);
        leftPanel.setLayout(
                new BoxLayout(leftPanel, BoxLayout.Y_AXIS)
        );
        leftPanel.setBorder(
                new EmptyBorder(35, 35, 35, 35)
        );

        // LOGO SIKURSUS
        java.net.URL logoURL =
                getClass().getResource("/icons/icon_sikursus.png");

        if (logoURL != null) {

            ImageIcon originalIcon = new ImageIcon(logoURL);

            Image scaledImage = originalIcon.getImage()
                    .getScaledInstance(
                            115,
                            115,
                            Image.SCALE_SMOOTH
                    );

            JLabel logoImage = new JLabel(
                    new ImageIcon(scaledImage)
            );

            logoImage.setAlignmentX(Component.CENTER_ALIGNMENT);

            leftPanel.add(logoImage);
            leftPanel.add(Box.createVerticalStrut(12));
        }

        // NAMA APLIKASI
        JLabel logo = new JLabel("SIKURSUS");

        logo.setFont(
                new Font("Arial", Font.BOLD, 34)
        );
        logo.setForeground(Color.WHITE);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setHorizontalAlignment(SwingConstants.CENTER);

        // SUBJUDUL
        JLabel subtitle = new JLabel(
                "Sistem Informasi Kursus"
        );

        subtitle.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );
        subtitle.setForeground(
                new Color(210, 220, 240)
        );
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);

        // JUDUL SAMBUTAN
        JLabel welcome = new JLabel(
                "<html><div style='text-align:center;'>"
                + "Selamat Datang<br>"
                + "di Sistem Kursus"
                + "</div></html>"
        );

        welcome.setFont(
                new Font("Arial", Font.BOLD, 24)
        );
        welcome.setForeground(Color.WHITE);
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcome.setHorizontalAlignment(SwingConstants.CENTER);

        // DESKRIPSI
        JLabel description = new JLabel(
                "<html><div style='text-align:center;'>"
                + "Kelola data kursus dengan mudah,<br>"
                + "cepat, dan terintegrasi."
                + "</div></html>"
        );

        description.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );
        description.setForeground(
                new Color(210, 220, 240)
        );
        description.setAlignmentX(Component.CENTER_ALIGNMENT);
        description.setHorizontalAlignment(SwingConstants.CENTER);

        // MENYUSUN PANEL KIRI
        leftPanel.add(logo);
        leftPanel.add(Box.createVerticalStrut(6));
        leftPanel.add(subtitle);
        leftPanel.add(Box.createVerticalStrut(55));
        leftPanel.add(welcome);
        leftPanel.add(Box.createVerticalStrut(15));
        leftPanel.add(description);

        // =====================================
        // PANEL KANAN
        // =====================================

        JPanel rightPanel = new JPanel();

        rightPanel.setBackground(LIGHT);
        rightPanel.setLayout(new GridBagLayout());

        // PANEL FORM LOGIN
        JPanel formPanel = new JPanel();

        formPanel.setBackground(Color.WHITE);
        formPanel.setLayout(
                new BoxLayout(formPanel, BoxLayout.Y_AXIS)
        );
        formPanel.setBorder(
                new EmptyBorder(35, 35, 35, 35)
        );
        formPanel.setPreferredSize(
                new Dimension(330, 370)
        );

        // JUDUL LOGIN
        JLabel title = new JLabel("Login Akun");

        title.setFont(
                new Font("Arial", Font.BOLD, 27)
        );
        title.setForeground(NAVY);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        // INFORMASI LOGIN
        JLabel info = new JLabel(
                "Silakan masuk ke akun Anda"
        );

        info.setFont(
                new Font("Arial", Font.PLAIN, 13)
        );
        info.setForeground(Color.GRAY);
        info.setAlignmentX(Component.CENTER_ALIGNMENT);
        info.setHorizontalAlignment(SwingConstants.CENTER);

        // LABEL USERNAME
        JLabel lblUsername = new JLabel("Username");

        lblUsername.setFont(
                new Font("Arial", Font.BOLD, 13)
        );
        lblUsername.setAlignmentX(Component.CENTER_ALIGNMENT);

        // INPUT USERNAME
        txtUsername = new JTextField();

        txtUsername.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );
        txtUsername.setMaximumSize(
                new Dimension(260, 38)
        );
        txtUsername.setPreferredSize(
                new Dimension(260, 38)
        );
        txtUsername.setAlignmentX(Component.CENTER_ALIGNMENT);

        // LABEL PASSWORD
        JLabel lblPassword = new JLabel("Password");

        lblPassword.setFont(
                new Font("Arial", Font.BOLD, 13)
        );
        lblPassword.setAlignmentX(Component.CENTER_ALIGNMENT);

        // INPUT PASSWORD
        txtPassword = new JPasswordField();

        txtPassword.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );
        txtPassword.setMaximumSize(
                new Dimension(260, 38)
        );
        txtPassword.setPreferredSize(
                new Dimension(260, 38)
        );
        txtPassword.setAlignmentX(Component.CENTER_ALIGNMENT);

        // TOMBOL LOGIN
        btnLogin = new JButton("MASUK");

        btnLogin.setFont(
                new Font("Arial", Font.BOLD, 14)
        );
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(BLUE);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorderPainted(false);
        btnLogin.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );
        btnLogin.setMaximumSize(
                new Dimension(260, 42)
        );
        btnLogin.setPreferredSize(
                new Dimension(260, 42)
        );
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);

        // FOOTER
        JLabel footer = new JLabel(
                "SIKURSUS - Sistem Informasi Kursus"
        );

        footer.setFont(
                new Font("Arial", Font.PLAIN, 10)
        );
        footer.setForeground(Color.GRAY);
        footer.setAlignmentX(Component.CENTER_ALIGNMENT);
        footer.setHorizontalAlignment(SwingConstants.CENTER);

        // =====================================
        // MENYUSUN FORM LOGIN
        // =====================================

        formPanel.add(title);
        formPanel.add(Box.createVerticalStrut(8));

        formPanel.add(info);
        formPanel.add(Box.createVerticalStrut(28));

        formPanel.add(lblUsername);
        formPanel.add(Box.createVerticalStrut(8));

        formPanel.add(txtUsername);
        formPanel.add(Box.createVerticalStrut(18));

        formPanel.add(lblPassword);
        formPanel.add(Box.createVerticalStrut(8));

        formPanel.add(txtPassword);
        formPanel.add(Box.createVerticalStrut(25));

        formPanel.add(btnLogin);
        formPanel.add(Box.createVerticalStrut(25));

        formPanel.add(footer);

        rightPanel.add(formPanel);

        // =====================================
        // PANEL UTAMA
        // =====================================

        root.add(leftPanel);
        root.add(rightPanel);

        setContentPane(root);

        // =====================================
        // EVENT LISTENER
        // =====================================

        btnLogin.addActionListener(
                e -> prosesLogin()
        );

        txtPassword.addActionListener(
                e -> prosesLogin()
        );
    }

    // =====================================
    // PROSES LOGIN
    // =====================================

    private void prosesLogin() {

        String username = txtUsername.getText().trim();
        char[] password = txtPassword.getPassword();

        // VALIDASI INPUT
        if (username.isEmpty() || password.length == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Username dan password harus diisi!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE
            );

            Arrays.fill(password, '\0');
            return;
        }

        try {

            // MEMERIKSA USERNAME DAN PASSWORD
            User user = userDAO.authenticate(
                    username,
                    password
            );

            if (user != null) {

                // MENYIMPAN SESSION USER
                UserSession.setCurrentUser(user);

                // MEMBUKA DASHBOARD
                dispose();

                new DashboardFrame().setVisible(true);

            } else {

                // LOGIN GAGAL
                JOptionPane.showMessageDialog(
                        this,
                        "Username atau password salah!",
                        "Login Gagal",
                        JOptionPane.ERROR_MESSAGE
                );

                txtPassword.setText("");
                txtPassword.requestFocus();
            }

        } catch (SQLException ex) {

            // ERROR DATABASE
            JOptionPane.showMessageDialog(
                    this,
                    "Terjadi kesalahan database:\n"
                    + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } finally {

            // MEMBERSIHKAN PASSWORD
            Arrays.fill(password, '\0');
        }
    }

    // =====================================
    // MAIN METHOD
    // =====================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}
