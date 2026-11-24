package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import model.User;
import util.UserSession;
import util.WindowManager;

public class DashboardFrame extends JFrame {

    // =========================
    // KOMPONEN
    // =========================

    private JLabel lblUser;
    private JLabel lblRole;

    private JButton btnPeserta;
    private JButton btnInstruktur;
    private JButton btnKursus;
    private JButton btnKelas;
    private JButton btnPendaftaran;
    private JButton btnPembayaran;
    private JButton btnLaporan;
    private JButton btnLogout;
    private JButton btnTutup;

    // =========================
    // WARNA
    // =========================

    private final Color NAVY = new Color(20, 40, 75);
    private final Color BLUE = new Color(42, 91, 160);
    private final Color LIGHT = new Color(245, 247, 251);
    private final Color WHITE = Color.WHITE;
    private final Color MUTED = new Color(120, 130, 145);

    // =========================
    // CONSTRUCTOR
    // =========================

    public DashboardFrame() {
        initComponents();

        java.net.URL iconURL =
                getClass().getResource("/icons/icon_sikursus.png");

        if (iconURL != null) {
            setIconImage(new ImageIcon(iconURL).getImage());
        }

        setLocationRelativeTo(null);
        applySession();
    }

    // =========================
    // INIT COMPONENTS
    // =========================

    private void initComponents() {

        setTitle("Dashboard - SIKURSUS Desktop");
        setSize(1200, 750);
        setMinimumSize(new Dimension(950, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // PANEL ROOT
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(LIGHT);

        // SIDEBAR
        JPanel sidebar = buatSidebar();

        // PANEL KANAN
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(LIGHT);

        // HEADER
        JPanel header = buatHeader();

        // CONTENT
        JPanel content = buatContent();

        // FOOTER
        JLabel footer = new JLabel(
                "SIKURSUS Desktop | Sistem Informasi Kursus",
                SwingConstants.CENTER
        );

        footer.setFont(new Font("Arial", Font.PLAIN, 11));
        footer.setForeground(MUTED);
        footer.setBorder(
                new EmptyBorder(12, 10, 15, 10)
        );

        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(content, BorderLayout.CENTER);
        mainPanel.add(footer, BorderLayout.SOUTH);

        root.add(sidebar, BorderLayout.WEST);
        root.add(mainPanel, BorderLayout.CENTER);

        setContentPane(root);
    }

    // =========================
    // SIDEBAR
    // =========================

    private JPanel buatSidebar() {

        JPanel sidebar = new JPanel(new BorderLayout());

        sidebar.setPreferredSize(new Dimension(245, 0));
        sidebar.setBackground(NAVY);

        // =========================
        // PANEL LOGO
        // =========================

        JPanel logoPanel = new JPanel();

        logoPanel.setBackground(NAVY);
        logoPanel.setLayout(
                new BoxLayout(logoPanel, BoxLayout.Y_AXIS)
        );

        logoPanel.setBorder(
                new EmptyBorder(25, 20, 25, 20)
        );

        JLabel lblLogo = new JLabel();

        java.net.URL logoURL =
                getClass().getResource("/icons/icon_sikursus.png");

        if (logoURL != null) {

            ImageIcon icon = new ImageIcon(logoURL);

            Image image = icon.getImage().getScaledInstance(
                    75, 75, Image.SCALE_SMOOTH
            );

            lblLogo.setIcon(new ImageIcon(image));

        } else {

            lblLogo.setText("SIKURSUS");
            lblLogo.setForeground(WHITE);
            lblLogo.setFont(
                    new Font("Arial", Font.BOLD, 20)
            );
        }

        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblNama = new JLabel("SIKURSUS");

        lblNama.setForeground(WHITE);
        lblNama.setFont(
                new Font("Arial", Font.BOLD, 22)
        );
        lblNama.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubNama = new JLabel(
                "Sistem Informasi Kursus"
        );

        lblSubNama.setForeground(
                new Color(190, 205, 230)
        );

        lblSubNama.setFont(
                new Font("Arial", Font.PLAIN, 11)
        );

        lblSubNama.setAlignmentX(Component.CENTER_ALIGNMENT);

        logoPanel.add(lblLogo);
        logoPanel.add(Box.createVerticalStrut(10));
        logoPanel.add(lblNama);
        logoPanel.add(Box.createVerticalStrut(5));
        logoPanel.add(lblSubNama);

        // =========================
        // PANEL NAVIGASI
        // =========================

        JPanel navPanel = new JPanel();

        navPanel.setBackground(NAVY);
        navPanel.setLayout(
                new BoxLayout(navPanel, BoxLayout.Y_AXIS)
        );

        navPanel.setBorder(
                new EmptyBorder(10, 15, 10, 15)
        );

        JLabel lblMenu = new JLabel("MENU UTAMA");

        lblMenu.setForeground(
                new Color(160, 180, 210)
        );

        lblMenu.setFont(
                new Font("Arial", Font.BOLD, 11)
        );

        lblMenu.setBorder(
                new EmptyBorder(10, 10, 12, 10)
        );

        lblMenu.setAlignmentX(Component.LEFT_ALIGNMENT);

        navPanel.add(lblMenu);

        // MENU MASTER

        btnPeserta = buatTombolSidebar("Data Peserta");
        btnInstruktur = buatTombolSidebar("Data Instruktur");
        btnKursus = buatTombolSidebar("Data Kursus");
        btnKelas = buatTombolSidebar("Data Kelas");

        navPanel.add(btnPeserta);
        navPanel.add(Box.createVerticalStrut(5));

        navPanel.add(btnInstruktur);
        navPanel.add(Box.createVerticalStrut(5));

        navPanel.add(btnKursus);
        navPanel.add(Box.createVerticalStrut(5));

        navPanel.add(btnKelas);

        // MENU TRANSAKSI

        JLabel lblTransaksi = new JLabel("TRANSAKSI");

        lblTransaksi.setForeground(
                new Color(160, 180, 210)
        );

        lblTransaksi.setFont(
                new Font("Arial", Font.BOLD, 11)
        );

        lblTransaksi.setBorder(
                new EmptyBorder(25, 10, 12, 10)
        );

        lblTransaksi.setAlignmentX(Component.LEFT_ALIGNMENT);

        navPanel.add(lblTransaksi);

        btnPendaftaran = buatTombolSidebar("Pendaftaran");
        btnPembayaran = buatTombolSidebar("Pembayaran");

        navPanel.add(btnPendaftaran);
        navPanel.add(Box.createVerticalStrut(5));
        navPanel.add(btnPembayaran);

        // MENU LAPORAN

        JLabel lblLaporan = new JLabel("LAPORAN");

        lblLaporan.setForeground(
                new Color(160, 180, 210)
        );

        lblLaporan.setFont(
                new Font("Arial", Font.BOLD, 11)
        );

        lblLaporan.setBorder(
                new EmptyBorder(25, 10, 12, 10)
        );

        lblLaporan.setAlignmentX(Component.LEFT_ALIGNMENT);

        navPanel.add(lblLaporan);

        btnLaporan = buatTombolSidebar("Laporan");

        navPanel.add(btnLaporan);

        // =========================
        // PANEL BAWAH SIDEBAR
        // =========================

        JPanel bottomPanel = new JPanel();

        bottomPanel.setBackground(NAVY);
        bottomPanel.setLayout(
                new BoxLayout(bottomPanel, BoxLayout.Y_AXIS)
        );

        bottomPanel.setBorder(
                new EmptyBorder(15, 15, 20, 15)
        );

        // TOMBOL LOGOUT
        btnLogout = buatTombolSidebar("Logout");

        // TOMBOL TUTUP APLIKASI
        btnTutup = buatTombolSidebar("Tutup Aplikasi");

        bottomPanel.add(btnLogout);
        bottomPanel.add(Box.createVerticalStrut(8));
        bottomPanel.add(btnTutup);

        // =========================
        // EVENT SIDEBAR
        // =========================

        btnPeserta.addActionListener(e -> bukaPeserta());

        btnInstruktur.addActionListener(e -> bukaInstruktur());

        btnKursus.addActionListener(e -> bukaKursus());

        btnKelas.addActionListener(e -> bukaKelas());

        // Pendaftaran dan Pembayaran sekarang membuka form asli
        btnPendaftaran.addActionListener(e -> bukaPendaftaran());

        btnPembayaran.addActionListener(e -> bukaPembayaran());

        btnLaporan.addActionListener(e -> bukaMenuLaporan());

        btnLogout.addActionListener(e -> logout());

        btnTutup.addActionListener(e -> tutupAplikasi());

        // =========================
        // SUSUN SIDEBAR
        // =========================

        sidebar.add(logoPanel, BorderLayout.NORTH);
        // Menu dibungkus scroll agar tombol Laporan tetap bisa
        // diakses walau tinggi jendela kecil.
        JScrollPane navScroll = new JScrollPane(
                navPanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        navScroll.setBorder(null);
        navScroll.getViewport().setBackground(NAVY);
        navScroll.getVerticalScrollBar().setUnitIncrement(16);

        sidebar.add(navScroll, BorderLayout.CENTER);
        sidebar.add(bottomPanel, BorderLayout.SOUTH);

        return sidebar;
    }

    // =========================
    // TOMBOL SIDEBAR
    // =========================

    private JButton buatTombolSidebar(String teks) {

        JButton button = new JButton(teks);

        button.setFont(
                new Font("Arial", Font.PLAIN, 13)
        );

        button.setForeground(WHITE);
        button.setBackground(NAVY);

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        button.setBorder(
                new EmptyBorder(12, 15, 12, 10)
        );

        button.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 45)
        );

        button.setAlignmentX(Component.LEFT_ALIGNMENT);

        button.addMouseListener(
                new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(
                    java.awt.event.MouseEvent e
            ) {
                if (button.isEnabled()) {
                    button.setBackground(BLUE);
                }
            }

            @Override
            public void mouseExited(
                    java.awt.event.MouseEvent e
            ) {
                button.setBackground(NAVY);
            }
        });

        return button;
    }

    // =========================
    // HEADER
    // =========================

    private JPanel buatHeader() {

        JPanel header = new JPanel(new BorderLayout());

        header.setBackground(WHITE);

        header.setBorder(
                new EmptyBorder(20, 30, 20, 30)
        );

        // JUDUL
        JPanel titlePanel = new JPanel();

        titlePanel.setOpaque(false);
        titlePanel.setLayout(
                new BoxLayout(titlePanel, BoxLayout.Y_AXIS)
        );

        JLabel title = new JLabel("Dashboard");

        title.setFont(
                new Font("Arial", Font.BOLD, 25)
        );

        title.setForeground(NAVY);

        JLabel subtitle = new JLabel(
                "Selamat datang di Sistem Informasi Kursus"
        );

        subtitle.setFont(
                new Font("Arial", Font.PLAIN, 12)
        );

        subtitle.setForeground(MUTED);

        titlePanel.add(title);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(subtitle);

        // INFORMASI USER
        JPanel userPanel = new JPanel();

        userPanel.setOpaque(false);
        userPanel.setLayout(
                new BoxLayout(userPanel, BoxLayout.Y_AXIS)
        );

        lblUser = new JLabel("User: -");
        lblRole = new JLabel("Role: -");

        lblUser.setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        lblUser.setForeground(NAVY);

        lblRole.setFont(
                new Font("Arial", Font.PLAIN, 12)
        );

        lblRole.setForeground(MUTED);

        lblUser.setAlignmentX(Component.RIGHT_ALIGNMENT);
        lblRole.setAlignmentX(Component.RIGHT_ALIGNMENT);

        userPanel.add(lblUser);
        userPanel.add(Box.createVerticalStrut(5));
        userPanel.add(lblRole);

        header.add(titlePanel, BorderLayout.WEST);
        header.add(userPanel, BorderLayout.EAST);

        return header;
    }

    // =========================
    // CONTENT DASHBOARD
    // =========================

    private JPanel buatContent() {

        JPanel content = new JPanel(new BorderLayout());

        content.setBackground(LIGHT);

        content.setBorder(
                new EmptyBorder(25, 30, 20, 30)
        );

        JPanel isi = new JPanel();

        isi.setBackground(LIGHT);
        isi.setLayout(
                new BoxLayout(isi, BoxLayout.Y_AXIS)
        );

        // KARTU SELAMAT DATANG
        JPanel welcomeCard = new JPanel(
                new BorderLayout()
        );

        welcomeCard.setBackground(WHITE);

        welcomeCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225, 230, 238)
                        ),
                        new EmptyBorder(25, 30, 25, 30)
                )
        );

        JLabel welcome = new JLabel(
                "Selamat Datang di SIKURSUS!"
        );

        welcome.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        welcome.setForeground(NAVY);

        JLabel description = new JLabel(
                "<html>Kelola data peserta, instruktur, kursus, kelas, "
                + "dan transaksi melalui sistem informasi kursus "
                + "secara lebih mudah dan terstruktur.</html>"
        );

        description.setFont(
                new Font("Arial", Font.PLAIN, 13)
        );

        description.setForeground(MUTED);

        JPanel welcomeText = new JPanel();

        welcomeText.setOpaque(false);
        welcomeText.setLayout(
                new BoxLayout(welcomeText, BoxLayout.Y_AXIS)
        );

        welcomeText.add(welcome);
        welcomeText.add(Box.createVerticalStrut(12));
        welcomeText.add(description);

        welcomeCard.add(
                welcomeText, BorderLayout.CENTER
        );

        isi.add(welcomeCard);
        isi.add(Box.createVerticalStrut(25));

        // JUDUL MENU CEPAT
        JLabel menuTitle = new JLabel("Akses Menu");

        menuTitle.setFont(
                new Font("Arial", Font.BOLD, 19)
        );

        menuTitle.setForeground(NAVY);
        menuTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        isi.add(menuTitle);
        isi.add(Box.createVerticalStrut(15));

        // GRID KARTU MENU (semua kartu bisa diklik)
        JPanel cardPanel = new JPanel(
                new GridLayout(0, 2, 18, 18)
        );

        cardPanel.setBackground(LIGHT);
        cardPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        cardPanel.add(buatMenuCard(
                "Data Peserta",
                "Kelola informasi dan data peserta kursus.",
                this::bukaPeserta
        ));

        cardPanel.add(buatMenuCard(
                "Data Instruktur",
                "Kelola informasi instruktur kursus.",
                this::bukaInstruktur
        ));

        cardPanel.add(buatMenuCard(
                "Data Kursus",
                "Kelola daftar program dan jenis kursus.",
                this::bukaKursus
        ));

        cardPanel.add(buatMenuCard(
                "Data Kelas",
                "Kelola informasi kelas kursus.",
                this::bukaKelas
        ));

        cardPanel.add(buatMenuCard(
                "Pendaftaran",
                "Akses proses pendaftaran peserta.",
                this::bukaPendaftaran
        ));

        cardPanel.add(buatMenuCard(
                "Pembayaran",
                "Akses informasi dan proses pembayaran.",
                this::bukaPembayaran
        ));

        isi.add(cardPanel);

        content.add(isi, BorderLayout.NORTH);

        return content;
    }

    // =========================
    // KARTU MENU
    // =========================

    private JPanel buatMenuCard(
            String judul,
            String deskripsi,
            Runnable aksi
    ) {

        JPanel card = new JPanel(new BorderLayout());

        card.setBackground(WHITE);

        card.setPreferredSize(
                new Dimension(250, 115)
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225, 230, 238)
                        ),
                        new EmptyBorder(20, 20, 20, 20)
                )
        );

        JLabel title = new JLabel(judul);

        title.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        title.setForeground(BLUE);

        JLabel desc = new JLabel(
                "<html>" + deskripsi + "</html>"
        );

        desc.setFont(
                new Font("Arial", Font.PLAIN, 12)
        );

        desc.setForeground(MUTED);

        JPanel textPanel = new JPanel();

        textPanel.setOpaque(false);
        textPanel.setLayout(
                new BoxLayout(textPanel, BoxLayout.Y_AXIS)
        );

        textPanel.add(title);
        textPanel.add(Box.createVerticalStrut(10));
        textPanel.add(desc);

        card.add(textPanel, BorderLayout.CENTER);

        // Kartu bisa diklik. Pasang listener di kartu dan isinya,
        // karena label/panel anak menangkap klik sendiri.
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        java.awt.event.MouseAdapter klik =
                new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                aksi.run();
            }
        };

        card.addMouseListener(klik);
        textPanel.addMouseListener(klik);
        title.addMouseListener(klik);
        desc.addMouseListener(klik);

        return card;
    }

    // =========================
    // PEMANGGILAN FORM
    // =========================

    private void bukaPeserta() {

        WindowManager.open(
                FormPeserta.class,
                FormPeserta::new
        );
    }

    private void bukaInstruktur() {

        WindowManager.open(
                FormInstruktur.class,
                FormInstruktur::new
        );
    }

    private void bukaKursus() {

        WindowManager.open(
                FormKursus.class,
                FormKursus::new
        );
    }

    private void bukaKelas() {

        WindowManager.open(
                FormKelas.class,
                FormKelas::new
        );
    }

    private void bukaPendaftaran() {

        WindowManager.open(
                FormPendaftaran.class,
                FormPendaftaran::new
        );
    }

    private void bukaPembayaran() {

        WindowManager.open(
                FormPembayaran.class,
                FormPembayaran::new
        );
    }

    // =========================
    // SESSION DAN HAK AKSES
    // =========================

    private void applySession() {

        User user = UserSession.getCurrentUser();

        if (user == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Session tidak ditemukan. Silakan login ulang."
            );

            dispose();

            SwingUtilities.invokeLater(
                    () -> new LoginFrame().setVisible(true)
            );

            return;
        }

        lblUser.setText(
                "User: " + user.getUsername()
        );

        lblRole.setText(
                "Role: " + user.getRole()
        );

        boolean admin = user.isAdmin();

        // HAK AKSES ADMIN DAN OPERATOR

        btnPeserta.setEnabled(true);

        btnInstruktur.setEnabled(admin);
        btnKursus.setEnabled(admin);
        btnKelas.setEnabled(admin);

        btnPendaftaran.setEnabled(true);
        btnPembayaran.setEnabled(true);
        btnLaporan.setEnabled(true);

        btnLogout.setEnabled(true);
        btnTutup.setEnabled(true);
    }

    // =========================
    // MENU LAPORAN (PERTEMUAN 12 DAN 13)
    // =========================

    private void bukaMenuLaporan() {

        JPopupMenu menu = new JPopupMenu();

        JMenuItem miPeserta = new JMenuItem("Laporan Peserta");
        JMenuItem miPendaftaran = new JMenuItem("Laporan Pendaftaran");
        JMenuItem miPembayaran = new JMenuItem("Laporan Pembayaran");

        miPeserta.addActionListener(
                e -> report.ReportService.tampilkanLaporanPeserta()
        );
        miPendaftaran.addActionListener(
                e -> report.ReportService.tampilkanLaporanPendaftaran()
        );
        miPembayaran.addActionListener(
                e -> report.ReportService.tampilkanLaporanPembayaran()
        );

        menu.add(miPeserta);
        menu.add(miPendaftaran);
        menu.add(miPembayaran);

        menu.show(btnLaporan, btnLaporan.getWidth(), 0);
    }

    // =========================
    // LOGOUT
    // =========================

    private void logout() {

        int pilihan = JOptionPane.showConfirmDialog(
                this,
                "Apakah Anda yakin ingin logout?",
                "Konfirmasi Logout",
                JOptionPane.YES_NO_OPTION
        );

        if (pilihan == JOptionPane.YES_OPTION) {

            WindowManager.closeAll();

            UserSession.clear();

            dispose();

            SwingUtilities.invokeLater(
                    () -> new LoginFrame().setVisible(true)
            );
        }
    }

    // =========================
    // TUTUP APLIKASI
    // =========================

    private void tutupAplikasi() {

        int pilihan = JOptionPane.showConfirmDialog(
                this,
                "Apakah Anda yakin ingin menutup aplikasi?",
                "Konfirmasi Tutup Aplikasi",
                JOptionPane.YES_NO_OPTION
        );

        if (pilihan == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    // =========================
    // MAIN
    // =========================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new DashboardFrame().setVisible(true);
        });
    }
}