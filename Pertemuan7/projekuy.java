package Pertemuan7;

import java.util.Scanner;

// Pertemuan 7: Memory management -> reference, null, heap/stack, garbage collection, Runtime memory

class Pesanan {
    private String nama;
    private String layanan;
    private double berat;
    private double total;
    private String status;

    public Pesanan(String nama, String layanan, double berat, double total, String status) {
        this.nama = nama;
        this.layanan = layanan;
        this.berat = berat;
        this.total = total;
        this.status = status;
    }

    public String getNama() { return nama; }
    public String getLayanan() { return layanan; }
    public double getBerat() { return berat; }
    public double getTotal() { return total; }
    public String getStatus() { return status; }

    public void setNama(String nama) { this.nama = nama; }
    public void setLayanan(String layanan) { this.layanan = layanan; }

    public void setBerat(double berat) {
        if (berat > 0) {
            this.berat = berat;
        }
    }

    public void setTotal(double total) {
        if (total >= 0) {
            this.total = total;
        }
    }

    public void setStatus(String status) {
        if (status.equals("Proses") || status.equals("Selesai") || status.equals("Diambil")) {
            this.status = status;
        }
    }

    public void tampilkanInformasi() {
        System.out.println("\n=== INFORMASI PESANAN ===");
        System.out.println("Nama    : " + nama);
        System.out.println("Layanan : " + layanan);
        System.out.println("Berat   : " + berat + " kg");
        System.out.println("Total   : Rp" + (long) total);
        System.out.println("Status  : " + status);
    }
}

class Layanan {
    public static String getNamaLayanan(int jenis) {
        if (jenis == 1) return "Cuci Kering";
        else if (jenis == 2) return "Cuci Setrika";
        else if (jenis == 3) return "Setrika Saja";
        return "Tidak valid";
    }

    public static double getHargaPerKg(int jenis) {
        switch (jenis) {
            case 1: return 5000;
            case 2: return 7000;
            case 3: return 4000;
            default: return 0;
        }
    }

    // Diskon 10% jika berat minimal 10 kg
    public static double hitungHarga(int jenis, double berat) {
        double hasil = getHargaPerKg(jenis) * berat;
        if (berat >= 10) {
            hasil = hasil - (hasil * 0.1);
        }
        return hasil;
    }
}

class PengelolaLaundry {
    private static Pesanan[] daftarPesanan = new Pesanan[50];
    private static int jumlah = 0;

    public static int getJumlah() {
        return jumlah;
    }

    public static boolean tambah(Pesanan p) {
        if (jumlah >= 50) {
            return false;
        }
        daftarPesanan[jumlah] = p;
        jumlah++;
        return true;
    }

    // Menghapus pesanan: geser array lalu isi slot terakhir null
    // agar objek tidak punya reference lagi dan bisa dibersihkan Garbage Collector
    public static void hapus(int no) {
        for (int i = no - 1; i < jumlah - 1; i++) {
            daftarPesanan[i] = daftarPesanan[i + 1];
        }
        daftarPesanan[jumlah - 1] = null;
        jumlah--;
    }

    public static Pesanan get(int no) {
        return daftarPesanan[no - 1];
    }

    public static void lihatSemua() {
        if (jumlah == 0) {
            System.out.println("Belum ada pesanan.");
            return;
        }
        for (int i = 0; i < jumlah; i++) {
            daftarPesanan[i].tampilkanInformasi();
        }
    }

    public static void ubahStatus(int no, int status) {
        String statusBaru;
        switch (status) {
            case 1: statusBaru = "Proses"; break;
            case 2: statusBaru = "Selesai"; break;
            case 3: statusBaru = "Diambil"; break;
            default:
                System.out.println("Status tidak valid!");
                return;
        }
        daftarPesanan[no - 1].setStatus(statusBaru);
        System.out.println("Status berhasil diubah!");
    }

    public static double hitungPendapatan() {
        double total = 0;
        for (int i = 0; i < jumlah; i++) {
            if (daftarPesanan[i].getStatus().equalsIgnoreCase("Diambil")) {
                total += daftarPesanan[i].getTotal();
            }
        }
        return total;
    }
}

public class projekuy {
    static Scanner input = new Scanner(System.in);

    static void tampilMenu() {
        System.out.println("\n=== SISTEM LAUNDRY ===");
        System.out.println("1. Tambah Pesanan");
        System.out.println("2. Lihat Semua Pesanan");
        System.out.println("3. Ubah Status Pesanan");
        System.out.println("4. Total Pendapatan");
        System.out.println("5. Hapus Pesanan");
        System.out.println("6. Info Memori & Demo Reference");
        System.out.println("0. Keluar");
        System.out.print("Pilih menu: ");
    }

    static void tambahPesanan() {
        if (PengelolaLaundry.getJumlah() >= 50) {
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
        Pesanan p = new Pesanan(nama, Layanan.getNamaLayanan(jenis), berat,
                                Layanan.hitungHarga(jenis, berat), "Proses");
        if (PengelolaLaundry.tambah(p)) {
            System.out.println("Pesanan berhasil ditambahkan!");
            p.tampilkanInformasi();
        } else {
            System.out.println("Data sudah penuh!");
        }
    }

    static void prosesUbahStatus() {
        if (PengelolaLaundry.getJumlah() == 0) {
            System.out.println("Belum ada pesanan.");
            return;
        }
        System.out.print("Nomor pesanan: ");
        int no = input.nextInt();
        if (no < 1 || no > PengelolaLaundry.getJumlah()) {
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
        PengelolaLaundry.ubahStatus(no, status);
    }

    static void prosesHapus() {
        if (PengelolaLaundry.getJumlah() == 0) {
            System.out.println("Belum ada pesanan.");
            return;
        }
        System.out.print("Nomor pesanan yang dihapus: ");
        int no = input.nextInt();
        input.nextLine();
        if (no < 1 || no > PengelolaLaundry.getJumlah()) {
            System.out.println("Nomor pesanan tidak ditemukan.");
            return;
        }
        PengelolaLaundry.hapus(no);
        System.gc(); // saran ke JVM untuk menjalankan Garbage Collector
        System.out.println("Pesanan dihapus.");
    }

    static void infoMemori() {
        Runtime rt = Runtime.getRuntime();
        long terpakai = (rt.totalMemory() - rt.freeMemory()) / 1024;
        System.out.println("\n--- INFO MEMORI (Heap) ---");
        System.out.println("Terpakai : " + terpakai + " KB");
        System.out.println("Total    : " + rt.totalMemory() / 1024 + " KB");
        System.out.println("Maksimum : " + rt.maxMemory() / 1024 + " KB");

        if (PengelolaLaundry.getJumlah() > 0) {
            System.out.println("\n--- DEMO REFERENCE ---");
            Pesanan a = PengelolaLaundry.get(1);   // a menunjuk objek di heap
            Pesanan b = a;                          // b menunjuk objek YANG SAMA
            System.out.println("a == b ? " + (a == b));
            b.setNama(b.getNama() + " (diubah lewat b)");
            System.out.println("Nama lewat a: " + a.getNama());
            b = null;                               // b tidak menunjuk apa-apa lagi
            System.out.println("b setelah null: " + b);
        }
    }

    public static void main(String[] args) {
        int menu;
        do {
            tampilMenu();
            menu = input.nextInt();
            input.nextLine();
            switch (menu) {
                case 1: tambahPesanan(); break;
                case 2: PengelolaLaundry.lihatSemua(); break;
                case 3: prosesUbahStatus(); break;
                case 4:
                    System.out.println("Total pendapatan (pesanan diambil): Rp"
                        + (long) PengelolaLaundry.hitungPendapatan());
                    break;
                case 5: prosesHapus(); break;
                case 6: infoMemori(); break;
                case 0: System.out.println("Terima kasih!"); break;
                default: System.out.println("Menu tidak tersedia.");
            }
        } while (menu != 0);
        input.close();
    }
}
