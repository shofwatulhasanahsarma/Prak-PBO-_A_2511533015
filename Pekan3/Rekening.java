package Pekan3;
import java.util.ArrayList;

public class Rekening {
		private static final int MAKS_GAGAL = 3;
		private int gagalBerturut = 0;
		private boolean terblokir = false;
		
	    private String nomorRekening;
	    private String namaPemilik;
	    private double saldo;
	    private String pin;
	    private ArrayList<String> riwayatPin;
	    private ArrayList <Transaksi> riwayatTransaksi;
	   
	    // CONSTRUCTOR
	   
	    public Rekening(String nomor, String nama, double saldoAwal,String pinAwal) {
	    	String error = cekAturanPin (pinAwal);
	    	if (error != null) {
	    		throw new IllegalArgumentException (error);
	    	}

	        this.nomorRekening = nomor;
	        this.namaPemilik = nama;
	        this.saldo = saldoAwal;
	        this.pin = pinAwal;
	        this.riwayatPin = new ArrayList<>();
	        this.riwayatPin.add (pinAwal);
	        this.riwayatTransaksi = new ArrayList<>();
	        System.out.println ("Rekening atas nama " + namaPemilik + "berhasil dinuat");
	    }
	    
	 // Getter
	    public String getNomorRekening() { return nomorRekening; }
	    public String getNamaPemilik() { return namaPemilik; }
	    public boolean isTerblokir() { return terblokir; }
	    public int getSisaPercobaan() { return MAKS_GAGAL - gagalBerturut; }

	    // aturan pembentukan PIN. ch 3 dan 4
	    public static String cekAturanPin(String pin) {
	        if (pin == null || !pin.matches("\\d{6}")) {
	            return "PIN harus terdiri dari tepat 6 digit angka.";
	        }

	        boolean semuaSama = true;
	        boolean urutNaik = true;
	        boolean urutTurun = true;

	        for (int i = 1; i < pin.length(); i++) {
	            char sekarang = pin.charAt(i);
	            char sebelum = pin.charAt(i - 1);

	            if (sekarang != pin.charAt(0)) semuaSama = false;
	            if (sekarang != sebelum + 1) urutNaik = false;
	            if (sekarang != sebelum - 1) urutTurun = false;
	        }

	        if (semuaSama) return "PIN tidak boleh angka yang berulang semua (contoh 111111).";
	        if (urutNaik) return "PIN tidak boleh angka berurutan naik (contoh 123456).";
	        if (urutTurun) return "PIN tidak boleh angka berurutan turun (contoh 654321).";
	        return null;
	    }
	    
	    // menghitung salah pin + blokir. ch 1
	    public boolean otentikasi(String inputPin) {
	        if (terblokir) {
	            return false;
	        }
	        if (pin.equals(inputPin)) {
	            gagalBerturut = 0;
	            return true;
	        }
	        gagalBerturut++;
	        if (gagalBerturut >= MAKS_GAGAL) {
	            terblokir = true;
	        }
	        return false;
	    }
	    
	    // Ganti pin. Challange 2
	    public boolean gantiPin(String pinLama, String pinBaru) {
	        if (terblokir) {
	            System.out.println("Gagal: akun terblokir.");
	            return false;
	        }
	        if (!otentikasi(pinLama)) {
	            if (terblokir) {
	                System.out.println("PIN lama salah. Akun TERBLOKIR.");
	            } else {
	                System.out.println("PIN lama salah! Sisa percobaan: " + getSisaPercobaan());
	            }
	            return false;
	        }
	        String error = cekAturanPin(pinBaru);
	        if (error != null) {
	            System.out.println("Gagal: " + error);
	            return false;
	        }
	        if (riwayatPin.contains(pinBaru)) {
	            System.out.println("Gagal: PIN ini pernah digunakan. Pilih PIN yang benar-benar baru.");
	            return false;
	        }
	        this.pin = pinBaru;
	        riwayatPin.add(pinBaru);
	        System.out.println("PIN berhasil diganti.");
	        return true;
	    }
	  
	    // SETOR TUNAI
	   
	    public void setorTunai(double nominal) {

	        if (nominal > 0) {

	            saldo += nominal;
	            
	            String idTrx = "TRX-S-" + System.currentTimeMillis();
	            riwayatTransaksi.add(new Transaksi (idTrx, "Kredit", nominal));
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


	
	    // TARIK TUNAI

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
	            riwayatTransaksi.add(new Transaksi (idTrx, "Debit", nominal));
	            // ============================================

	            System.out.println(
	                "Tarik tunai Rp" + nominal
	                + " berhasil. Saldo saat ini: Rp" + saldo
	            );
	        }
	    }


	    // CEK INFORMASI
	    
	    public void cekInformasi() {

	        System.out.println("--- INFO REKENING ---");
	        System.out.println("No. Rekening : " + nomorRekening);
	        System.out.println("Nama Pemilik : " + namaPemilik);
	        System.out.println("Saldo Akhir  : Rp" + saldo);
	        System.out.println("Status       : " + (terblokir ? "TERBLOKIR" : "Aktif"));
	        System.out.println("---------------------");
	    }

	
	    // METHOD CEK TRANSAKSI TERBARU
	   
	    public void cekTransaksiTerbaru() {

	        if (riwayatTransaksi.size() > 3) {
	            Transaksi t = riwayatTransaksi.get(riwayatTransaksi.size() - 1);
	            System.out.println("--- TRANSAKSI TERBARU ---");
	            System.out.println("ID :" + t.getIdTransaksi());
	            System.out.println("Jenis : " + t.getJenis());
	            System.out.println("Nominal  : Rp" + t.getNominal());
	            System.out.println("---------------------");
	        } else {
	            System.out.println(
	                "Transaksi belum lebih dari 3");
	        }
	    }

	  
	    // METHOD CETAK MUTASI
	   
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
