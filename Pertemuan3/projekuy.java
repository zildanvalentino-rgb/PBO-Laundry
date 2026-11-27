package Pertemuan3;

import java.util.Scanner;

// Pertemuan 3: Control statement (if, switch, for, do-while)
// Semua logika masih di main() karena function baru dipelajari di pertemuan 4
class Pesanan {
    String nama;
    String layanan;
    double berat;
    double total;
    String status;

    void tampilkanInformasi() {
        System.out.println("\n=== INFORMASI PESANAN ===");
        System.out.println("Nama    : " + nama);
        System.out.println("Layanan : " + layanan);
        System.out.println("Berat   : " + berat + " kg");
        System.out.println("Total   : Rp" + (long) total);
        System.out.println("Status  : " + status);
    }
}

public class projekuy {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Pesanan[] daftar = new Pesanan[50];
        int jumlah = 0;
        int menu;

        do {
            System.out.println("\n=== SISTEM LAUNDRY ===");
            System.out.println("1. Tambah Pesanan");
            System.out.println("2. Lihat Semua Pesanan");
            System.out.println("3. Ubah Status Pesanan");
            System.out.println("4. Total Pendapatan");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            menu = input.nextInt();
            input.nextLine();

            switch (menu) {
                case 1: {
                    if (jumlah >= 50) {
                        System.out.println("Data sudah penuh!");
                        break;
                    }
                    System.out.print("Nama pelanggan: ");
                    String nama = input.nextLine();
                    System.out.println("1. Cuci Kering  (Rp5.000/kg)");
                    System.out.println("2. Cuci Setrika (Rp7.000/kg)");
                    System.out.println("3. Setrika Saja (Rp4.000/kg)");
                    System.out.print("Pilih layanan: ");
                    int jenis = input.nextInt();
                    if (jenis < 1 || jenis > 3) {
                        input.nextLine();
                        System.out.println("Layanan tidak valid!");
                        break;
                    }
                    System.out.print("Berat (kg): ");
                    double berat = input.nextDouble();
                    input.nextLine();
                    if (berat <= 0) {
                        System.out.println("Berat harus lebih dari 0!");
                        break;
                    }

                    String namaLayanan;
                    double harga;
                    if (jenis == 1) {
                        namaLayanan = "Cuci Kering";
                        harga = 5000;
                    } else if (jenis == 2) {
                        namaLayanan = "Cuci Setrika";
                        harga = 7000;
                    } else {
                        namaLayanan = "Setrika Saja";
                        harga = 4000;
                    }
                    double total = harga * berat;
                    if (berat >= 10) {
                        total = total - (total * 0.1);
                    }

                    Pesanan p = new Pesanan();
                    p.nama = nama;
                    p.layanan = namaLayanan;
                    p.berat = berat;
                    p.total = total;
                    p.status = "Proses";
                    daftar[jumlah] = p;
                    jumlah++;
                    System.out.println("Pesanan berhasil ditambahkan!");
                    p.tampilkanInformasi();
                    break;
                }
                case 2:
                    if (jumlah == 0) {
                        System.out.println("Belum ada pesanan.");
                    } else {
                        for (int i = 0; i < jumlah; i++) {
                            daftar[i].tampilkanInformasi();
                        }
                    }
                    break;
                case 3: {
                    if (jumlah == 0) {
                        System.out.println("Belum ada pesanan.");
                        break;
                    }
                    System.out.print("Nomor pesanan: ");
                    int no = input.nextInt();
                    if (no < 1 || no > jumlah) {
                        input.nextLine();
                        System.out.println("Nomor pesanan tidak ditemukan.");
                        break;
                    }
                    System.out.println("1. Proses");
                    System.out.println("2. Selesai");
                    System.out.println("3. Diambil");
                    System.out.print("Status baru: ");
                    int st = input.nextInt();
                    input.nextLine();
                    if (st == 1) {
                        daftar[no - 1].status = "Proses";
                    } else if (st == 2) {
                        daftar[no - 1].status = "Selesai";
                    } else if (st == 3) {
                        daftar[no - 1].status = "Diambil";
                    } else {
                        System.out.println("Status tidak valid!");
                        break;
                    }
                    System.out.println("Status berhasil diubah!");
                    break;
                }
                case 4: {
                    double pendapatan = 0;
                    for (int i = 0; i < jumlah; i++) {
                        if (daftar[i].status.equals("Diambil")) {
                            pendapatan += daftar[i].total;
                        }
                    }
                    System.out.println("Total pendapatan (pesanan diambil): Rp" + (long) pendapatan);
                    break;
                }
                case 0:
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Menu tidak tersedia.");
            }
        } while (menu != 0);

        input.close();
    }
}
