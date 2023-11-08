package entity;

import java.sql.Time;

public class CaLam {
	private String maCL;
	private Time gioBatDau;
	private Time gioKetThuc;
	
	public CaLam() {
		super();
	}
	public CaLam(String maCL, Time gioBatDau, Time gioKetThuc) {
		super();
		this.maCL = maCL;
		this.gioBatDau = gioBatDau;
		this.gioKetThuc = gioKetThuc;
	}
	public String getMaCL() {
		return maCL;
	}
	public void setMaCL(String maCL) {
		this.maCL = maCL;
	}
	public Time getGioBatDau() {
		return gioBatDau;
	}
	public void setGioBatDau(Time gioBatDau) {
		this.gioBatDau = gioBatDau;
	}
	public Time getGioKetThuc() {
		return gioKetThuc;
	}
	public void setGioKetThuc(Time gioKetThuc) {
		this.gioKetThuc = gioKetThuc;
	}
	
}
