package entity;

import java.time.LocalDate;

public class BangPhanCa {
	private NhanVien nhanVien;
	private CaLam caLam;
	private LocalDate ngayBatDau;
	private LocalDate ngayKetThuc;
	private String kieuPhanCa;
	public BangPhanCa() {
	}
	
	public BangPhanCa(NhanVien nhanVien, CaLam caLam, LocalDate ngayBatDau,LocalDate ngayKetThuc, String kieuPhanCa) {
		super();
		this.nhanVien = nhanVien;
		this.caLam = caLam;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.kieuPhanCa = kieuPhanCa;
	}

	public LocalDate getNgayBatDau() {
		return ngayBatDau;
	}

	public void setNgayBatDau(LocalDate ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	public LocalDate getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(LocalDate ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public CaLam getCaLam() {
		return caLam;
	}
	public void setCaLam(CaLam caLam) {
		this.caLam = caLam;
	}
	public String getKieuPhanCa() {
		return kieuPhanCa;
	}
	public void setKieuPhanCa(String kieuPhanCa) {
		this.kieuPhanCa = kieuPhanCa;
	}
	
}
