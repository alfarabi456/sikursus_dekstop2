package model;

public class Peserta extends Orang {
    private String nim;
    private String prodi;
    private String jenisKelamin;
    private String email;

    // Konstruktor lama: dipertahankan agar kode pertemuan sebelumnya tidak rusak.
    public Peserta(int id, String nama, String noHp, String nim, String prodi) {
        super(id, nama, noHp);
        setNim(nim);
        setProdi(prodi);
        this.email = "";
    }

    // Konstruktor baru: lengkap sesuai tabel peserta. Gunakan ini untuk DAO.
    public Peserta(int id, String nama, String noHp, String nim, String prodi,
                   String jenisKelamin, String email) {
        this(id, nama, noHp, nim, prodi);
        setJenisKelamin(jenisKelamin);
        setEmail(email);
    }

    public String getNim() { return nim; }
    public String getProdi() { return prodi; }
    public String getJenisKelamin() { return jenisKelamin; }
    public String getEmail() { return email; }

    public void setNim(String nim) {
        if (nim == null || nim.trim().isEmpty()) {
            throw new IllegalArgumentException("NIM wajib diisi");
        }
        this.nim = nim.trim();
    }

    public void setProdi(String prodi) {
        if (prodi == null || prodi.trim().isEmpty()) {
            throw new IllegalArgumentException("Prodi wajib diisi");
        }
        this.prodi = prodi.trim();
    }

    public void setJenisKelamin(String jenisKelamin) {
        if (jenisKelamin == null
                || !(jenisKelamin.equals("L") || jenisKelamin.equals("P"))) {
            throw new IllegalArgumentException("Jenis kelamin harus L atau P");
        }
        this.jenisKelamin = jenisKelamin;
    }

    public void setEmail(String email) {
        this.email = email == null ? "" : email.trim();
    }

    @Override
    public String getInfo() {
        return "PESERTA | " + super.getInfo() + " | NIM: " + getNim() + " | Prodi: " + getProdi();
    }
}