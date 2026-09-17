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
	
public class transaksiTerbaru {
	static String idTransaksi;
	static String jenisTransaksi;
	static double nominal;
	
	public transaksiTerbaru (String ID, String Jenis, double Nominal) {
		this.idTransaksi = ID;
		this.jenisTransaksi = Jenis;
		this.nominal = Nominal;
		}
	
public void cetakDetail () {
	System.out.println ("id: " + idTransaksi + "| jenis: " + jenisTransaksi + "|nominal: Rp" + nominal);
	System.out.println ("ID: " + transaksiTerbaru.idTransaksi + "| Jenis: " + transaksiTerbaru.jenisTransaksi + "|Nominal: Rp" + transaksiTerbaru.nominal);
}
}
}