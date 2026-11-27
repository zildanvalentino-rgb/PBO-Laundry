package Pertemuan8.Quiz;

public class soal3 {
	public static void main(String[] args) {
		Mahasiswa mahasiswa = new Mahasiswa("Ani", 20, "Informatika");

		mahasiswa.tampilkanData();
		mahasiswa.belajar();
		mahasiswa.berorganisasi();
	}
}

// Super class
class Person {
	protected String nama;
	protected int umur;

	public Person(String nama, int umur) {
		this.nama = nama;
		this.umur = umur;
	}

	public void tampilkanData() {
		System.out.println("Nama: " + nama + ", Umur: " + umur);
	}
}

// Multiple inheritance menggunakan interface, dengan Person sebagai superclass.
class Mahasiswa extends Person implements Akademik, Organisasi {
	private String jurusan;

	public Mahasiswa(String nama, int umur, String jurusan) {
		super(nama, umur);
		this.jurusan = jurusan;
	}

	@Override
	public void tampilkanData() {
		System.out.println("Mahasiswa: " + nama + ", Jurusan: " + jurusan);
	}

	@Override
	public void belajar() {
		System.out.println(nama + " sedang belajar.");
	}

	@Override
	public void berorganisasi() {
		System.out.println(nama + " mengikuti organisasi.");
	}
}

interface Akademik {
	void belajar();
}

interface Organisasi {
	void berorganisasi();
}
