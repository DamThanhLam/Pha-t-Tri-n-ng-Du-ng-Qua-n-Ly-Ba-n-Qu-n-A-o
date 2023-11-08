package entity;

import java.util.Date;

public class HoaDon {
	private String maHD;
	private NhanVien nv;
	private KhachHang kh;
	private Date ngayLap;

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public NhanVien getNv() {
		return nv;
	}

	public void setNv(NhanVien nv) {
		this.nv = nv;
	}

	public KhachHang getKh() {
		return kh;
	}

	public void setKh(KhachHang kh) {
		this.kh = kh;
	}

	public Date getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}

	public HoaDon(String maHD, NhanVien nv, KhachHang kh, Date ngayLap) {
		super();
		this.maHD = maHD;
		this.nv = nv;
		this.kh = kh;
		this.ngayLap = ngayLap;
	}

	public HoaDon(NhanVien nv, KhachHang kh, Date ngayLap) {
		this.nv = nv;
		this.kh = kh;
		this.ngayLap = ngayLap;
	}
}
