/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latihan;

/**
 *
 * @author USER
 */
import java.util.Scanner;
// Mengimport Scanner untuk menerima input dari keyboard

public class latihanterarah {     // class

    public static void main(String[] args) {

        // Membuat objek Scanner untuk membaca input pengguna
        Scanner input = new Scanner(System.in);

        // Menyimpan kode kursus
        String kode = "JAVA-BSC";

        // Menyimpan nama kursus
        String nama = "Java Desktop Fundamental";

        // Meminta pengguna memasukkan biaya kursus
        System.out.print("Masukkan biaya kursus: Rp");
        double biaya = input.nextDouble();

        // Menentukan diskon sebesar 10%
        double diskon = 0.10;

        // Menentukan biaya registrasi sebesar Rp25.000
        double biayaRegistrasi = 25_000;

        // Menentukan status kursus
        // true berarti kursus aktif
        boolean aktif = true;

        // Menghitung jumlah potongan diskon
        double potongan = biaya * diskon;

        // Menghitung total pembayaran
        // Biaya kursus dikurangi potongan kemudian ditambah biaya registrasi
        double total = biaya - potongan + biayaRegistrasi;

        // Menampilkan kode kursus
        System.out.println("Kode              : " + kode);

        // Menampilkan nama kursus
        System.out.println("Kursus            : " + nama);

        // Menampilkan status kursus
        System.out.println("Aktif              : " + aktif);

        // Menampilkan biaya kursus
        System.out.printf("Biaya Kursus      : Rp%,.0f%n", biaya);

        // Menampilkan potongan diskon
        System.out.printf("Potongan Diskon   : Rp%,.0f%n", potongan);

        // Menampilkan biaya registrasi
        System.out.printf("Biaya Registrasi  : Rp%,.0f%n", biayaRegistrasi);

        // Menampilkan total pembayaran
        System.out.printf("Total Pembayaran  : Rp%,.0f%n", total);

        // Menutup Scanner
        input.close();
    }
}