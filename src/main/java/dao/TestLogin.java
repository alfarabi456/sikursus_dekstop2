package dao;

import model.User;

public class TestLogin {

    public static void main(String[] args) {

        UserDAO dao = new UserDAO();

        try {

            User user = dao.authenticate(
                "admin",
                "admin123".toCharArray()
            );

            if (user != null) {
                System.out.println("LOGIN BERHASIL!");
                System.out.println("Username: " + user.getUsername());
                System.out.println("Role: " + user.getRole());
            } else {
                System.out.println("LOGIN GAGAL!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}