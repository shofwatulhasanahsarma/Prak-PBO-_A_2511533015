package TugasPekan1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

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
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");

            int pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {

                // ==========================================
                // 1. BUKA REKENING BARU
                // ==========================================
                case 1:

                    System.out.print("Masukkan No Rekening: ");
                    String no = input.nextLine();

                    // Mengecek apakah nomor rekening sudah digunakan
                    boolean sudahAda = false;

                    for (Rekening rekening : daftarRekening) {
                        if (rekening.nomorRekening.equals(no)) {
                            sudahAda = true;
                            break;
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

                    // Membuat objek Rekening baru
                    Rekening rekeningBaru = new Rekening(no, nama, saldo);

                    // Menambahkan objek ke ArrayList
                    daftarRekening.add(rekeningBaru);

                    // Menjadikan rekening baru sebagai akun aktif
                    akunAktif = rekeningBaru;

                    System.out.println("Rekening berhasil ditambahkan ke daftar.");

                    break;


                // ==========================================
                // 2. SETOR TUNAI
                // ==========================================
                case 2:

                    if (akunAktif == null) {

                        System.out.println(
                            "Error: Mohon maaf, Anda belum memiliki rekening!"
                        );

                    } else {

                        System.out.print("Masukkan nominal setor: ");
                        double setor = input.nextDouble();

                        // Memanggil method setorTunai()
                        akunAktif.setorTunai(setor);
                    }

                    break;


                // ==========================================
                // 3. TARIK TUNAI
                // ==========================================
                case 3:

                    if (akunAktif == null) {

                        System.out.println(
                            "Error: Mohon maaf, Anda belum memiliki rekening!"
                        );

                    } else {

                        System.out.print("Masukkan nominal tarik: ");
                        double tarik = input.nextDouble();

                        // Memanggil method tarikTunai()
                        akunAktif.tarikTunai(tarik);
                    }

                    break;


                // ==========================================
                // 4. CEK INFORMASI REKENING
                // ==========================================
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


                // ==========================================
                // 5. GANTI AKUN
                // ==========================================
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

                            if (rekening.nomorRekening.equals(nomorCari)) {
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
                                "Akun aktif: " + akunAktif.namaPemilik
                            );

                        } else {

                            System.out.println(
                                "Gagal: Nomor rekening tidak ditemukan!"
                            );
                        }
                    }

                    break;


                // ==========================================
                // 0. KELUAR
                // ==========================================
                case 0:

                    isRunning = false;

                    System.out.println(
                        "Terima kasih telah menggunakan Sistem Perbankan Mini."
                    );

                    break;


                // ==========================================
                // PILIHAN TIDAK VALID
                // ==========================================
                default:

                    System.out.println(
                        "Pilihan menu tidak tersedia!"
                    );
            }
        }

        input.close();
    }
}