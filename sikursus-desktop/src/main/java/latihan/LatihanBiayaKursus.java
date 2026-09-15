/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latihan;

import java.util.Scanner;
// Scanner digunakan untuk menerima input dari keyboard

/**
 *
 * @author USER
 */
public class LatihanBiayaKursus {

    public static void main(String[] args) {

        // Membuat objek Scanner untuk membaca input dari pengguna
        Scanner input = new Scanner(System.in);

        // Menyimpan kode kursus
        String kode = "JAVA-BSC";

        // Menyimpan nama kursus
        String nama = "Java Desktop Fundamental";

        // Meminta pengguna memasukkan biaya kursus
        System.out.print("Masukkan biaya kursus: Rp");
        double biaya = input.nextDouble();

        // Menyimpan biaya registrasi sebesar Rp25.000
        double biayaRegistrasi = 25_000;

        // Menentukan status kursus, true berarti kursus aktif
        boolean aktif = true;

        // Menyimpan biaya kursus sebelum mendapatkan diskon
        double totalSebelumDiskon = biaya;

        // Variabel untuk menyimpan persentase diskon
        double diskon;

        // Jika biaya kursus Rp3.000.000 atau lebih,
        // maka mendapatkan diskon sebesar 15%
        if (totalSebelumDiskon >= 3_000_000) {
            diskon = 0.15;

        // Jika biaya kursus Rp1.500.000 sampai kurang dari Rp3.000.000,
        // maka mendapatkan diskon sebesar 10%
        } else if (totalSebelumDiskon >= 1_500_000) {
            diskon = 0.10;

        // Jika biaya kursus di bawah Rp1.500.000,
        // maka mendapatkan diskon sebesar 5%
        } else {
            diskon = 0.05;
        }

        // Menghitung jumlah uang yang mendapatkan potongan diskon
        double potongan = totalSebelumDiskon * diskon;

        // Menghitung total pembayaran setelah dikurangi diskon
        // kemudian ditambah biaya registrasi
        double total = totalSebelumDiskon - potongan + biayaRegistrasi;

        // Variabel untuk menyimpan status harga
        String status;

        // Jika total pembayaran Rp700.000 atau lebih,
        // maka statusnya MAHAL
        if (total >= 700_000) {
            status = "MAHAL";

        // Jika total pembayaran kurang dari Rp700.000,
        // maka statusnya TERJANGKAU
        } else {
            status = "TERJANGKAU";
        }

        // Menampilkan garis pembatas
        System.out.println();
        System.out.println("==============================================");

        // Menampilkan judul program
        System.out.println("           DATA BIAYA KURSUS");

        // Menampilkan garis pembatas
        System.out.println("==============================================");

        // Menampilkan kode kursus
        System.out.println("Kode              : " + kode);

        // Menampilkan nama kursus
        System.out.println("Kursus            : " + nama);

        // Menampilkan status aktif kursus
        System.out.println("Aktif             : " + aktif);

        // Menampilkan biaya kursus
        System.out.printf("Biaya Kursus      : Rp%,.0f%n", biaya);

        // Menampilkan persentase diskon
        System.out.printf("Diskon            : %.0f%%%n", diskon * 100);

        // Menampilkan jumlah potongan diskon
        System.out.printf("Potongan Diskon   : Rp%,.0f%n", potongan);

        // Menampilkan biaya registrasi
        System.out.printf("Biaya Registrasi  : Rp%,.0f%n", biayaRegistrasi);

        // Menampilkan total pembayaran
        System.out.printf("Total Pembayaran  : Rp%,.0f%n", total);

        // Menampilkan status harga
        System.out.println("Status            : " + status);

        // Menampilkan garis pembatas
        System.out.println("==============================================");

        // Menutup Scanner setelah selesai digunakan
        input.close();
    }
}