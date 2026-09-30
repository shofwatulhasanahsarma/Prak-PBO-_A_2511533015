package Pekan3;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	// Input PIN sesuai aturan. ch 3
	 static String bacaPinBaru (Scanner input, Rekening akunAktif) {
     	while (true) {
     		System.out.print ("Masukkan PIN (6 digit angka): ");
     		String p = input.nextLine();
     		String error = Rekening.cekAturanPin (p);
     		if (error == null) {
     			return p;
     		}
     			System.out.println ("Gagal: " + error + " Silahkan ulangi.");
     	
     	}
     }
	 //verifikasi PIN sebelum transaksi. ch 1
	 static boolean verifikasiPin(Scanner input, Rekening r) {
	        if (r.isTerblokir()) {
	            System.out.println("Akun TERBLOKIR. Transaksi tidak dapat dilakukan.");
	            return false;
	        }
	        System.out.print("Masukkan PIN: ");
	        String p = input.nextLine();
	        if (r.otentikasi(p)) {
	            return true;
	        }
	        if (r.isTerblokir()) {
	            System.out.println("PIN salah 3 kali. Akun TERBLOKIR.");
	        } else {
	            System.out.println("PIN salah! Sisa percobaan: " + r.getSisaPercobaan());
	        }
	        System.out.println("Transaksi dibatalkan.");
	        return false;
	    }

	    public static void main(String[] args) {

	        Scanner input = new Scanner(System.in);

	        // Menyimpan banyak objek Rekening
	        ArrayList<Rekening> daftarRekening = new ArrayList<>();

	        // Menyimpan rekening yang sedang aktif
	        Rekening akunAktif = null;

	        boolean isRunning = true;

	        System.out.println("=== SISTEM PERBANKAN MINI ===");

	        while (isRunning) {

	            System.out.println("\nMenu Utama:");
	            System.out.println("1. Buka Rekening Baru");
	            System.out.println("2. Setor Tunai");
	            System.out.println("3. Tarik Tunai");
	            System.out.println("4. Cek Informasi Rekening");
	            System.out.println("5. Ganti Akun");
	            System.out.println("6. Cetak Mutasi (Riwayat)");
	            System.out.println("7. Ganti PIN");
	            System.out.println("0. Keluar");
	            System.out.print("Pilih menu: ");

	            int pilihan = input.nextInt();
	            input.nextLine();

	            switch (pilihan) {

	               
	                // 1. BUKA REKENING BARU
	               
	                case 1:

	                    System.out.print("Masukkan No Rekening: ");
	                    String no = input.nextLine();

	                    // Mengecek apakah nomor rekening sudah digunakan
	                    boolean sudahAda = false;

	                    for (Rekening rekening : daftarRekening) {
	                        if (rekening.getNomorRekening().equals(no)) {
	                            sudahAda = true;
	                        }
	                    }

	                    if (sudahAda) {
	                        System.out.println("Gagal: Nomor rekening sudah digunakan!");
	                        break;
	                    }

	                    System.out.print("Masukkan Nama Pemilik: ");
	                    String nama = input.nextLine();

	                    System.out.print("Masukkan Saldo Awal: ");
	                    double saldo = input.nextDouble();
	                    input.nextLine ();
	                    
	                    System.out.print("Masukkan PIN (6 digit):");
	                    String pin = bacaPinBaru(input, null);
	                    
	                    // Membuat objek Rekening baru
	                    Rekening rekeningBaru = new Rekening(no, nama, saldo, pin);

	                    // Menambahkan objek ke ArrayList
	                    daftarRekening.add(rekeningBaru);

	                    // Menjadikan rekening baru sebagai akun aktif
	                    akunAktif = rekeningBaru;

	                    System.out.println("Rekening berhasil ditambahkan ke daftar.");

	                    break;


	            
	                // 2. SETOR TUNAI
	              
	                case 2:

	                    if (akunAktif == null) {

	                        System.out.println(
	                            "Error: Mohon maaf, Anda belum memiliki rekening!"
	                        );

	                    } else {
	                    	 // Verifikasi PIN terlebih dahulu
	                        if (verifikasiPin(input, akunAktif)) {

	                            System.out.print("Masukkan nominal setor: ");
	                            double setor = input.nextDouble();
	                            input.nextLine();

	                            // Jika PIN benar, baru melakukan setor
	                            akunAktif.setorTunai(setor);
	                        }
	                    }

	                    break;
	        
	      
	                // 3. TARIK TUNAI
	               
	                case 3:

	                    if (akunAktif == null) {

	                        System.out.println(
	                            "Error: Mohon maaf, Anda belum memiliki rekening!"
	                        );

	                    } else {
	                    	 System.out.print("Masukkan PIN: ");
	                         String pinYangDiInput = input.nextLine();

	                    	 // Verifikasi PIN terlebih dahulu
	                        if (verifikasiPin(input, akunAktif)) {

	                            System.out.print("Masukkan nominal tarik: ");
	                            double tarik = input.nextDouble();
	                            input.nextLine();

	                            // Jika PIN benar, baru melakukan penarikan
	                            akunAktif.tarikTunai(tarik);
	                        } else {

	                            System.out.println(
	                                "Akses Ditolak: PIN yang Anda masukkan salah!"
	                            );
	                        }
	                    }

	                    break;


	       
	                // 4. CEK INFORMASI REKENING
	                
	                case 4:

	                    if (akunAktif == null) {

	                        System.out.println(
	                            "Error: Anda belum membuka rekening."
	                        );

	                    } else {

	                        // Memanggil method cekInformasi()
	                        akunAktif.cekInformasi();
	                    }

	                    break;


	             
	                // 5. GANTI AKUN
	              
	                case 5:

	                    if (daftarRekening.isEmpty()) {

	                        System.out.println(
	                            "Belum ada rekening yang terdaftar."
	                        );

	                    } else {

	                        System.out.print("Masukkan No Rekening yang ingin digunakan: ");
	                        String nomorCari = input.nextLine();

	                        Rekening rekeningDitemukan = null;

	                        // Mencari rekening berdasarkan nomor
	                        for (Rekening rekening : daftarRekening) {

	                            if (rekening.getNomorRekening().equals(nomorCari)) {
	                                rekeningDitemukan = rekening;
	                                break;
	                            }
	                        }

	                        if (rekeningDitemukan != null) {

	                            akunAktif = rekeningDitemukan;

	                            System.out.println(
	                                "Berhasil berganti akun."
	                            );

	                            System.out.println(
	                                "Akun aktif: " + akunAktif.getNamaPemilik()
	                            );
	                         if (akunAktif.isTerblokir ()) {
	                        	 System.out.println ("Peringatan: akun ini terblokir, transaksi tidak daapat dilakukan");
	                            	
	                            }

	                        } else {

	                            System.out.println(
	                                "Gagal: Nomor rekening tidak ditemukan!"
	                            );
	                        }
	                    }

	                    break;


	              
	                // 6. CETAK MUTASI (RIWAYAT)
	              
	                case 6:

	                    if (akunAktif == null) {

	                        System.out.println(
	                            "Error: Anda belum membuka rekening."
	                        );

	                    } else {
	                    	 System.out.print("Masukkan PIN: ");
	                         String pinYangDiInput = input.nextLine();

	                         if (akunAktif.otentikasi(pinYangDiInput)) {

	                        // Memanggil method cetakMutasi()
	                        akunAktif.cetakMutasi();
	                     }  else {

	                            System.out.println(
	                                "Akses Ditolak: PIN yang Anda masukkan salah!"
	                            );
	                        }
	                    }

	                    break;
	                    
	                 // 7. Ganti PIN. ch 2
	                    
	                case 7: {
	                    if (akunAktif == null) {
	                    	
	                        System.out.println("Error: Anda belum membuka rekening.");
	                    } else if (akunAktif.isTerblokir()) {
	                        System.out.println("Akun TERBLOKIR. Tidak dapat mengganti PIN.");
	                    } else {
	                        System.out.print("Masukkan PIN lama: ");
	                        String pinLama = input.nextLine();

	                        if (!akunAktif.otentikasi(pinLama)) {
	                            if (akunAktif.isTerblokir()) {
	                                System.out.println("PIN salah 3 kali. Akun TERBLOKIR.");
	                            } else {
	                                System.out.println("PIN lama salah! Sisa percobaan: " + akunAktif.getSisaPercobaan());
	                            }
	                        } else {
	                            String pinBaru = bacaPinBaru(input, akunAktif);
	                            akunAktif.gantiPin(pinLama, pinBaru);
	                        }
	                    }
	                    break;
	                    }

	                // 0. KELUAR
	              
	                case 0:

	                    isRunning = false;

	                    System.out.println(
	                        "Terima kasih telah menggunakan Sistem Perbankan Mini."
	                    );

	                    break;


	                // PILIHAN TIDAK VALID
	               
	                default:

	                    System.out.println(
	                        "Pilihan menu tidak tersedia!"
	                    );
	            }
	        }

	        input.close();
	    }
	}
