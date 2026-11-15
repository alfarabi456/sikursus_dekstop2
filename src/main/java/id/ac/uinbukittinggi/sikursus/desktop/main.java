package id.ac.uinbukittinggi.sikursus.desktop;

import dao.PesertaDAO;
import model.Peserta;
import java.sql.SQLException;

public class main {

    public static void main(String[] args) {

        try {
            PesertaDAO dao = new PesertaDAO();

            for (Peserta p : dao.findAll()) {
                System.out.println(
                    p.getId() + " - "
                    + p.getNim() + " - "
                    + p.getNama() + " - "
                    + p.getJenisKelamin() + " - "
                    + p.getProdi() + " - "
                    + p.getNoHp() + " - "
                    + p.getEmail()
                );
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil data peserta:");
            e.printStackTrace();
        }
    }
}