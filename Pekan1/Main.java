package Pekan1;
	import java.util.Scanner;
	
	public class Main {
	    public static void main (String[] args){
	    	Scanner input = new Scanner (System.in);
	        Rekening akunAktif = null;
	        boolean isRunning = true;

	        System.out.println ("=== SISTEM PERBANKAN MINI ===");
	        
	        while (isRunning) {
	            System.out.println ("\nMenu Utama:");
	            System.out.println ("1. Buka rekening Baru");
	            System.out.println ("2. Setor Tunai");
	            System.out.println ("3. Tarik Tunai");
	            System.out.println ("4. Cek Informasi Rekening");
	            System.out.println ("0. Keluar");
	            System.out.print ("Pilih menu: ");

	            int pilihan = input.nextInt ();
	            input.nextLine ();

	            switch (pilihan) {
	                case 1:
	                    System.out.print ("Masukkan No Rekening: ");
	                    String no = input.nextLine ();
	                    System.out.print ("Masukkan Nama Pemilik: ");
	                    String nama = input.nextLine ();
	                    System.out.print ("Masukkan Saldo Awal: ");
	                    double saldo = input.nextDouble ();

	                    //Instagram Object / Menjalankan Constructor
	                    akunAktif = new Rekening (no, nama, saldo);
	                    break;

	                case 2:
	                    if (akunAktif == null) {
	                        System.out.println ("Error: Mohon maaf, Anda belum memiliki nomor rekening!");
	                    } else {
	                        System.out.print ("Masukkan nominal setor: ");
	                        double setor = input.nextDouble();
	                        akunAktif.setorTunai (setor); //memanggil Behavior / method
	                    }
	                    break;

	                case 3:
	                    System.out.println ("Fitur ini akan kerjakan sebagai Tugas Mandiri.");
	                    break;

	                case 4:
	                    if (akunAktif == null){
	                        System.out.println ("Error: Anda belum membuka rekening");
	                    } else {
	                        akunAktif.cekInformasi ();

	                    }
	                    break;

	                case 0:
	            }
	        }
	        input.close();
	    }
	}
	