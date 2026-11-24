
package util;

public class BuatHash {

    public static void main(String[] args) {

        // =================================
        // HASH PASSWORD ADMIN
        // =================================

        String hashAdmin = PasswordUtil.hash(
                "admin123".toCharArray()
        );

        System.out.println("================================");
        System.out.println("HASH PASSWORD ADMIN");
        System.out.println("================================");
        System.out.println(hashAdmin);


        // =================================
        // HASH PASSWORD OPERATOR
        // =================================

        String hashOperator = PasswordUtil.hash(
                "operator123".toCharArray()
        );

        System.out.println();
        System.out.println("================================");
        System.out.println("HASH PASSWORD OPERATOR");
        System.out.println("================================");
        System.out.println(hashOperator);


        // =================================
        // SELESAI
        // =================================

        System.out.println();
        System.out.println("================================");
        System.out.println("PEMBUATAN HASH BERHASIL");
        System.out.println("================================");
    }
}
