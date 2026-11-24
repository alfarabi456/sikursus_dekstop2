
package view;

import util.PasswordUtil;

public class BuatHash {
    public static void main(String[] args) {

        char[] password = "12345".toCharArray();

        String hash = PasswordUtil.hash(password);

        System.out.println("HASH PASSWORD:");
        System.out.println(hash);

        java.util.Arrays.fill(password, '\0');
    }
}
