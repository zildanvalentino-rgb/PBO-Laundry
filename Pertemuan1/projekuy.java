package Pertemuan1;

// Pertemuan 1: Introduction to OOP -> class, object, attribute, method
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
        Pesanan p1 = new Pesanan();
        p1.nama = "Budi";
        p1.layanan = "Cuci Kering";
        p1.berat = 4;
        p1.total = 20000;
        p1.status = "Proses";

        Pesanan p2 = new Pesanan();
        p2.nama = "Siti";
        p2.layanan = "Cuci Setrika";
        p2.berat = 3;
        p2.total = 21000;
        p2.status = "Selesai";

        p1.tampilkanInformasi();
        p2.tampilkanInformasi();
    }
}
