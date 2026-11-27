package Pertemuan8;

import java.util.Scanner;

// Pertemuan 8: Inheritance basic -> extends, parent-child, super
// Pesanan (parent) -> PesananLaundry (child)

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
}

class PesananLaundry extends Pesanan {
    private String jenisPaket;

    public PesananLaundry(String nama, String layanan, double berat,
                          double total, String status, String jenisPaket) {
        super(nama, layanan, berat, total, status);
        this.jenisPaket = jenisPaket;
    }

    public String getJenisPaket() { return jenisPaket; }
    public void setJenisPaket(String jenisPaket) { this.jenisPaket = jenisPaket; }

    public void tampilkanInformasi() {
        System.out.println("\n=== INFORMASI PESANAN ===");
        System.out.println("Nama        : " + getNama());
        System.out.println("Layanan     : " + getLayanan());
        System.out.println("Berat       : " + getBerat() + " kg");
        System.out.println("Total       : Rp" + (long) getTotal());
        System.out.println("Status      : " + getStatus());
        System.out.println("Jenis Paket : " + jenisPaket);
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
    private static PesananLaundry[] daftarPesanan = new PesananLaundry[50];
    private static int jumlah = 0;

    public static int getJumlah() {
        return jumlah;
    }

    public static boolean tambah(PesananLaundry p) {
        if (jumlah >= 50) {
            return false;
        }
        daftarPesanan[jumlah] = p;
        jumlah++;
        return true;
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
        PesananLaundry p = new PesananLaundry(nama, Layanan.getNamaLayanan(jenis), berat,
                                Layanan.hitungHarga(jenis, berat), "Proses", "Reguler");
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
                case 0: System.out.println("Terima kasih!"); break;
                default: System.out.println("Menu tidak tersedia.");
            }
        } while (menu != 0);
        input.close();
    }
}
