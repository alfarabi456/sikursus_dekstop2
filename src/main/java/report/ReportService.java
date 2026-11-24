package report;

import config.Database;
import java.io.InputStream;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Pusat pemanggilan laporan Jaspersoft (Pertemuan 12 dan 13).
 * Jalur utama memakai file .jasper; jika belum ada, .jrxml
 * dikompilasi saat aplikasi berjalan.
 */
public final class ReportService {

    private ReportService() {}

    // =========================
    // PERTEMUAN 12
    // =========================

    public static void tampilkanLaporanPeserta() {
        tampilkan("laporan_peserta", new HashMap<>(), "Laporan Peserta");
    }

    // =========================
    // PERTEMUAN 13
    // =========================

    /** ID kosong = semua data; ID diisi = satu pendaftaran. */
    public static void tampilkanLaporanPendaftaran() {

        String input = JOptionPane.showInputDialog(
                null,
                "Masukkan ID Pendaftaran (angka).\n"
                + "Kosongkan untuk menampilkan semua data:",
                "Input",
                JOptionPane.QUESTION_MESSAGE);

        if (input == null) {
            return; // tombol Cancel
        }

        Map<String, Object> param = new HashMap<>();
        input = input.trim();

        if (input.isEmpty()) {
            param.put("P_ID_PENDAFTARAN", null);
        } else {
            try {
                param.put("P_ID_PENDAFTARAN", Integer.valueOf(input));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        null,
                        "ID harus berupa angka.",
                        "Input tidak valid",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        tampilkan("laporan_pendaftaran", param, "Laporan Pendaftaran");
    }

    /** Filter rentang tanggal pembayaran (yyyy-MM-dd). */
    public static void tampilkanLaporanPembayaran() {

        String awal = JOptionPane.showInputDialog(
                null,
                "Masukkan Tanggal Awal (yyyy-MM-dd):",
                LocalDate.now().withDayOfMonth(1).toString());
        if (awal == null) {
            return;
        }

        String akhir = JOptionPane.showInputDialog(
                null,
                "Masukkan Tanggal Akhir (yyyy-MM-dd):",
                LocalDate.now().toString());
        if (akhir == null) {
            return;
        }

        try {
            LocalDate d1 = LocalDate.parse(awal.trim());
            LocalDate d2 = LocalDate.parse(akhir.trim());

            if (d2.isBefore(d1)) {
                JOptionPane.showMessageDialog(
                        null,
                        "Tanggal akhir tidak boleh sebelum tanggal awal.",
                        "Input tidak valid",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            Map<String, Object> param = new HashMap<>();
            param.put("P_TGL_AWAL", d1.toString());
            param.put("P_TGL_AKHIR", d2.toString());

            tampilkan("laporan_pembayaran", param, "Laporan Pembayaran");

        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Format tanggal harus yyyy-MM-dd, contoh 2026-11-30.",
                    "Input tidak valid",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    // =========================
    // PROSES UMUM
    // =========================

    private static void tampilkan(String nama,
                                  Map<String, Object> param,
                                  String judul) {

        String jasperPath = "/report/" + nama + ".jasper";
        String jrxmlPath = "/report/" + nama + ".jrxml";

        try (Connection conn = Database.getConnection()) {

            JasperPrint print;

            try (InputStream jasper =
                         ReportService.class.getResourceAsStream(jasperPath)) {

                if (jasper != null) {
                    // Jalur utama: file .jasper hasil compile
                    print = JasperFillManager.fillReport(jasper, param, conn);
                } else {
                    // Cadangan: compile .jrxml saat aplikasi berjalan
                    try (InputStream jrxml =
                                 ReportService.class.getResourceAsStream(jrxmlPath)) {

                        if (jrxml == null) {
                            throw new IllegalStateException(
                                    nama + ".jasper/.jrxml tidak ditemukan di "
                                    + "src/main/resources/report/.");
                        }

                        JasperReport jr =
                                JasperCompileManager.compileReport(jrxml);
                        print = JasperFillManager.fillReport(jr, param, conn);
                    }
                }
            }

            JasperViewer.viewReport(print, false);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    "Gagal membuka laporan: " + e.getMessage(),
                    judul,
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
