package util;
import util.PasswordUtil;

public class CekPassword {

    public static void main(String[] args) {

        String passwordInput = "admin123";

        // Ambil password_hash dari database
        String storedHash =
            "120000:drm/CJnHtPr5heDyXdY0VQ==:ctwUhkUIbEXNpuh3nz...";

        boolean hasil = PasswordUtil.verify(
            passwordInput.toCharArray(),
            storedHash
        );

        if (hasil) {
            System.out.println("Password BENAR");
        } else {
            System.out.println("Password SALAH");
        }
    }
}
