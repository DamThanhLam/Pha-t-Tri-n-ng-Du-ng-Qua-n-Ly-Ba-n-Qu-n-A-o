package entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class ChiTietHoaDon implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HoaDon hd;
	private SanPham sp;
	private double donGia;
	private int soLuong;

	public HoaDon getHd() {
		return hd;
	}

	public void setHd(HoaDon hd) {
		this.hd = hd;
	}

	public SanPham getSp() {
		return sp;
	}

	public void setSp(SanPham sp) {
		this.sp = sp;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public ChiTietHoaDon(HoaDon hd, SanPham sp, double donGia, int soLuong) {
		super();
		this.hd = hd;
		this.sp = sp;
		this.donGia = donGia;
		this.soLuong = soLuong;
	}

	@Override
	public int hashCode() {
		return Objects.hash(hd);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietHoaDon other = (ChiTietHoaDon) obj;
		return Objects.equals(hd, other.hd);
	}

}
