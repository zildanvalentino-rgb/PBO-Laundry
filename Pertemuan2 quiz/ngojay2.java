package Pertemuan2;

import java.util.Scanner;
  
public class ngojay2 {
    
    public static void main(String[] args) {
        // RUMUS L = 0.5 X ALAS X SEGITIGA

        double luas,alas,segitiga;

        Scanner input = new Scanner(System.in);

        System.out.println("MASUKKAN ALAS : ");
        alas = input.nextDouble();

        System.out.println("masukkan segitiga : ");
        segitiga = input.nextDouble();

        luas = 0.5 * alas * segitiga;

        System.out.println("HASIL : " + luas);

        input.close();

    }

}
