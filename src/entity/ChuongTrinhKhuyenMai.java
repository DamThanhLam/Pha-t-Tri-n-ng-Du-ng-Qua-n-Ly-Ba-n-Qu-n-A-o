package entity;

import java.util.Date;

public class ChuongTrinhKhuyenMai {
	private String maKM;
	private String tenKM;
	private String moTa;
	private Date ngayBatDau;
	private Date ngayKetThuc;
	private int soLuong;
	private double giaGiam;
	private String maCaptcha;

	public String getMaKM() {
		return maKM;
	}

	public void setMaKM(String maKM) {
		this.maKM = maKM;
	}

	public String getTenKM() {
		return tenKM;
	}

	public void setTenKM(String tenKM) {
		this.tenKM = tenKM;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public Date getNgayBatDau() {
		return ngayBatDau;
	}

	public void setNgayBatDau(Date ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	public Date getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(Date ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getGiaGiam() {
		return giaGiam;
	}

	public void setGiaGiam(double giaGiam) {
		this.giaGiam = giaGiam;
	}

	public String getMaCaptcha() {
		return maCaptcha;
	}

	public void setMaCaptcha(String maCaptcha) {
		this.maCaptcha = maCaptcha;
	}

	public ChuongTrinhKhuyenMai(String maKM, String tenKM, String moTa, Date ngayBatDau, Date ngayKetThuc, int soLuong,
			double giaGiam, String maCaptcha) {
		super();
		this.maKM = maKM;
		this.tenKM = tenKM;
		this.moTa = moTa;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.soLuong = soLuong;
		this.giaGiam = giaGiam;
		this.maCaptcha = maCaptcha;
	}

	public ChuongTrinhKhuyenMai(String tenKM, String moTa, Date ngayBatDau, Date ngayKetThuc, int soLuong,
			double giaGiam, String maCaptcha) {
		super();
		this.tenKM = tenKM;
		this.moTa = moTa;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.soLuong = soLuong;
		this.giaGiam = giaGiam;
		this.maCaptcha = maCaptcha;
	}

}
