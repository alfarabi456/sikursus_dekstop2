
package view;

import javax.swing.*;
import java.awt.*;

public class FormKelas extends JFrame {

    public FormKelas() {
        setTitle("Master Data Kelas");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel judul = new JLabel("MASTER DATA KELAS");
        judul.setFont(new Font("Arial", Font.BOLD, 22));
        judul.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel keterangan = new JLabel(
                "Form pengelolaan data kelas");
        keterangan.setHorizontalAlignment(SwingConstants.CENTER);

        JButton btnTutup = new JButton("Tutup");
        btnTutup.addActionListener(e -> dispose());

        JPanel panel = new JPanel(new BorderLayout(10, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 30, 40, 30));
        panel.add(judul, BorderLayout.NORTH);
        panel.add(keterangan, BorderLayout.CENTER);
        panel.add(btnTutup, BorderLayout.SOUTH);

        add(panel);
    }
}
