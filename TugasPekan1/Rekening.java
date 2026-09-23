package TugasPekan1;
import java.util.ArrayList;
import Pekan2.Transaksi;
public class Rekening {

    String nomorRekening;
    String namaPemilik;
    double saldo;
    
    // Implementasi Asosiasi (1-to-many)
    ArrayList<Transaksi> riwayatTransaksi;

    // ==========================================
    // CONSTRUCTOR
    // ==========================================
    public Rekening(String nomor, String nama, double saldoAwal) {

        this.nomorRekening = nomor;
        this.namaPemilik = nama;
        this.saldo = saldoAwal;
        
        //Wajib menginisialisasi ArrayList di dalam constructor agar tidak NullPointerException
        this.riwayatTransaksi = new ArrayList<>();

        System.out.println(
            "Rekening atas nama " + namaPemilik
            + " berhasil dibuat dengan saldo Rp" + saldo
        );
    }


    // ==========================================
    // METHOD SETOR TUNAI
    // ==========================================
    public void setorTunai(double nominal) {

        if (nominal > 0) {

            saldo += nominal;
            //Merekam riwayat (pembuatan objek transaksi di dalam method)
            String idTrx = "TRX-S-" + System.currentTimeMillis();
            Transaksi trxBaru = new Transaksi(idTrx, "Kredit", nominal);
            riwayatTransaksi.add(trxBaru);

            System.out.println(
                "Setor tunai Rp" + nominal
                + " berhasil. Saldo saat ini: Rp" + saldo
            );

        } else {

            System.out.println(
                "Gagal: Nominal setor harus lebih dari 0!"
            );
        }
    }


    // ==========================================
    // METHOD TARIK TUNAI (SUDAH DIPERBAIKI)
    // ==========================================
    public void tarikTunai(double nominal) {

        // Minimal penarikan Rp10.000
        if (nominal < 10000) {

            System.out.println(
                "Transaksi Gagal: Minimal nominal penarikan Rp10.000."
            );

        // Mengecek apakah saldo mencukupi
        } else if (nominal > saldo) {

            System.out.println(
                "Transaksi Gagal: Saldo tidak mencukupi. "
                + "Saldo Anda: Rp" + saldo
            );

        // Jika semua syarat terpenuhi -> penarikan berhasil
        } else {

            // Mengurangi saldo
            saldo -= nominal;

            // === Merekam riwayat transaksi penarikan ===
            String idTrx = "TRX-T-" + System.currentTimeMillis();
            Transaksi trxBaru = new Transaksi(idTrx, "Debit", nominal);
            riwayatTransaksi.add(trxBaru);
            // ============================================

            System.out.println(
                "Tarik tunai Rp" + nominal
                + " berhasil. Saldo saat ini: Rp" + saldo
            );
        }
    }


    // ==========================================
    // METHOD CEK INFORMASI
    // ==========================================
    public void cekInformasi() {

        System.out.println("--- INFO REKENING ---");
        System.out.println("No. Rekening : " + nomorRekening);
        System.out.println("Nama Pemilik : " + namaPemilik);
        System.out.println("Saldo Akhir  : Rp" + saldo);
        System.out.println("---------------------");
    }

    // ==========================================
    // METHOD CEK TRANSAKSI TERBARU
    // ==========================================
    public void cekTransaksiTerbaru() {

        if (riwayatTransaksi.size() > 3) {
            Transaksi transaksiTerbaru =
                riwayatTransaksi.get(riwayatTransaksi.size() - 1);
            System.out.println("--- TRANSAKSI TERBARU ---");
            System.out.println("ID :" + transaksiTerbaru.idTransaksi);
            System.out.println("Jenis : " + transaksiTerbaru.jenisTransaksi);
            System.out.println("Nominal  : Rp" + transaksiTerbaru.nominal);
            System.out.println("---------------------");
        } else {
            System.out.println(
                "Transaksi belum lebih dari 3");
        }
    }

    // ==========================================
    // METHOD CETAK MUTASI
    // ==========================================
    public void cetakMutasi() {

        System.out.println("--- MUTASI REKENING (" + nomorRekening + ") ---");

        if (riwayatTransaksi.isEmpty()) {
            System.out.println("Belum ada transaksi pada rekening ini");
        } else {
            for (Transaksi trx: riwayatTransaksi) {
                trx.cetakDetail();
            }
        }

        System.out.println("---------------------------------");
    }

} // <-- penutup class Rekening