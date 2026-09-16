package TugasPekan1;

public class Rekening {

    String nomorRekening;
    String namaPemilik;
    double saldo;

    // ==========================================
    // CONSTRUCTOR
    // ==========================================
    public Rekening(String nomor, String nama, double saldoAwal) {

        nomorRekening = nomor;
        namaPemilik = nama;
        saldo = saldoAwal;

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
    // METHOD TARIK TUNAI
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

        // Jika semua syarat terpenuhi
        } else {

            saldo -= nominal;

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
}