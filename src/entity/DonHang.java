package entity;

import java.time.LocalDate;

public class DonHang {
	private String maDH;
	private NhanVien nhanVien;
	private KhachHang khachHang;
	private LocalDate ngayDat;
	
	public DonHang() {
	
	}
	public DonHang(String maDH) {
		super();
		this.maDH=maDH;
	}
	
	public DonHang(String maDH, NhanVien nhanVien, KhachHang khachHang, LocalDate ngayDat) {
		super();
		this.maDH = maDH;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
		this.ngayDat = ngayDat;
	}
	
	
	public void setMaDH(String maDH) {
		this.maDH = maDH;
	}
	public String getMaDH() {
		return maDH;
	}
	public void setMaDDH(String maDH) {
		this.maDH = maDH;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public LocalDate getNgayDat() {
		return ngayDat;
	}
	public void setNgayDat(LocalDate ngayDat) {
		this.ngayDat = ngayDat;
	}
	
	
}
