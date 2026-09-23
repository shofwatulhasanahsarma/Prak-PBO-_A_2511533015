package Pekan2;

public class Transaksi {
	public String idTransaksi;
	public String jenisTransaksi;
	public double nominal;
	
	//Constructor
	public Transaksi (String id, String jenis, double nominal) {
	this.idTransaksi = id;
	this.jenisTransaksi = jenis;
	this.nominal = nominal;
	}
	
	 // Method cetak detail transaksi
    public void cetakDetail() {
        System.out.println(
            "ID: " + idTransaksi
            + " | Jenis: " + jenisTransaksi
            + " | Nominal: Rp" + nominal
        );
    }
}