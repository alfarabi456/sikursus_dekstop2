
package util;

import util.PasswordUtil;

public class TesPassword {

    public static void main(String[] args) {

        String password = "admin123";

        String hash = PasswordUtil.hash(
            password.toCharArray()
        );

        System.out.println("HASH BARU:");
        System.out.println(hash);

        boolean hasil = PasswordUtil.verify(
            password.toCharArray(),
            hash
        );

        System.out.println("HASIL VERIFIKASI: " + hasil);
    }
}
