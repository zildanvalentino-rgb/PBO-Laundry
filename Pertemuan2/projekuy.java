package Pertemuan2;

// Pertemuan 2: Data type, variable, method, expression
class Pesanan {
    String nama;          // String
    String layanan;
    double berat;         // double
    int hargaPerKg;       // int
    double total;
    String status;
    boolean diskon;       // boolean

    // method + expression (aritmatika, relasional, ternary)
    void hitungTotal() {
        diskon = berat >= 10;
        total = hargaPerKg * berat;
        total = diskon ? total - (total * 0.1) : total;
    }

    void tampilkanInformasi() {
        System.out.println("\n=== INFORMASI PESANAN ===");
        System.out.println("Nama    : " + nama);
        System.out.println("Layanan : " + layanan);
        System.out.println("Berat   : " + berat + " kg");
        System.out.println("Diskon  : " + (diskon ? "Ya (10%)" : "Tidak"));
        System.out.println("Total   : Rp" + (long) total);
        System.out.println("Status  : " + status);
    }
}

public class projekuy {
    public static void main(String[] args) {
        Pesanan p1 = new Pesanan();
        p1.nama = "Budi";
        p1.layanan = "Cuci Kering";
        p1.hargaPerKg = 5000;
        p1.berat = 4;
        p1.status = "Proses";
        p1.hitungTotal();

        Pesanan p2 = new Pesanan();
        p2.nama = "Siti";
        p2.layanan = "Cuci Setrika";
        p2.hargaPerKg = 7000;
        p2.berat = 12;
        p2.status = "Selesai";
        p2.hitungTotal();

        p1.tampilkanInformasi();
        p2.tampilkanInformasi();
    }
}
