package Pertemuan4;

import java.util.Scanner;

// Pertemuan 4: Function dan parameter (parameter, return value, constructor berparameter)
class Pesanan {
    String nama;
    String layanan;
    double berat;
    double total;
    String status;

    Pesanan(String nama, String layanan, double berat, double total, String status) {
        this.nama = nama;
        this.layanan = layanan;
        this.berat = berat;
        this.total = total;
        this.status = status;
    }

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
    static Scanner input = new Scanner(System.in);
    static Pesanan[] daftar = new Pesanan[50];
    static int jumlah = 0;

    static String getNamaLayanan(int jenis) {
        if (jenis == 1) return "Cuci Kering";
        else if (jenis == 2) return "Cuci Setrika";
        else if (jenis == 3) return "Setrika Saja";
        return "Tidak valid";
    }

    static double getHargaPerKg(int jenis) {
        switch (jenis) {
            case 1: return 5000;
            case 2: return 7000;
            case 3: return 4000;
            default: return 0;
        }
    }

    // Diskon 10% jika berat minimal 10 kg
    static double hitungHarga(int jenis, double berat) {
        double hasil = getHargaPerKg(jenis) * berat;
        if (berat >= 10) {
            hasil = hasil - (hasil * 0.1);
        }
        return hasil;
    }

    static void tampilMenu() {
        System.out.println("\n=== SISTEM LAUNDRY ===");
        System.out.println("1. Tambah Pesanan");
        System.out.println("2. Lihat Semua Pesanan");
        System.out.println("3. Ubah Status Pesanan");
        System.out.println("4. Total Pendapatan");
        System.out.println("0. Keluar");
        System.out.print("Pilih menu: ");
    }

    static void tambahPesanan() {
        if (jumlah >= 50) {
            System.out.println("Data sudah penuh!");
            return;
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
            return;
        }
        System.out.print("Berat (kg): ");
        double berat = input.nextDouble();
        input.nextLine();
        if (berat <= 0) {
            System.out.println("Berat harus lebih dari 0!");
            return;
        }
        Pesanan p = new Pesanan(nama, getNamaLayanan(jenis), berat, hitungHarga(jenis, berat), "Proses");
        daftar[jumlah] = p;
        jumlah++;
        System.out.println("Pesanan berhasil ditambahkan!");
        p.tampilkanInformasi();
    }

    static void lihatSemua() {
        if (jumlah == 0) {
            System.out.println("Belum ada pesanan.");
            return;
        }
        for (int i = 0; i < jumlah; i++) {
            daftar[i].tampilkanInformasi();
        }
    }

    static void ubahStatus(int no, int status) {
        switch (status) {
            case 1: daftar[no - 1].status = "Proses"; break;
            case 2: daftar[no - 1].status = "Selesai"; break;
            case 3: daftar[no - 1].status = "Diambil"; break;
            default:
                System.out.println("Status tidak valid!");
                return;
        }
        System.out.println("Status berhasil diubah!");
    }

    static void prosesUbahStatus() {
        if (jumlah == 0) {
            System.out.println("Belum ada pesanan.");
            return;
        }
        System.out.print("Nomor pesanan: ");
        int no = input.nextInt();
        if (no < 1 || no > jumlah) {
            input.nextLine();
            System.out.println("Nomor pesanan tidak ditemukan.");
            return;
        }
        System.out.println("1. Proses");
        System.out.println("2. Selesai");
        System.out.println("3. Diambil");
        System.out.print("Status baru: ");
        int status = input.nextInt();
        input.nextLine();
        ubahStatus(no, status);
    }

    static double hitungPendapatan() {
        double total = 0;
        for (int i = 0; i < jumlah; i++) {
            if (daftar[i].status.equals("Diambil")) {
                total += daftar[i].total;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        int menu;
        do {
            tampilMenu();
            menu = input.nextInt();
            input.nextLine();
            switch (menu) {
                case 1: tambahPesanan(); break;
                case 2: lihatSemua(); break;
                case 3: prosesUbahStatus(); break;
                case 4:
                    System.out.println("Total pendapatan (pesanan diambil): Rp" + (long) hitungPendapatan());
                    break;
                case 0: System.out.println("Terima kasih!"); break;
                default: System.out.println("Menu tidak tersedia.");
            }
        } while (menu != 0);
        input.close();
    }
}
