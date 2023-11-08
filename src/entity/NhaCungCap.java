package entity;

import java.io.Serializable;

public class NhaCungCap implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String maNCC;
	private String tenNCC;
	private String diaChi;
	private String email;
	private String soDienThoai;

	public String getMaNCC() {
		return maNCC;
	}

	public void setMaNCC(String maNCC) {
		this.maNCC = maNCC;
	}

	public String getTenNCC() {
		return tenNCC;
	}

	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public NhaCungCap(String maNhaCungCap, String tenNhaCungCap, String diaChi, String email, String soDienThoai) {
		super();
		this.maNCC = maNhaCungCap;
		this.tenNCC = tenNhaCungCap;
		this.diaChi = diaChi;
		this.email = email;
		this.soDienThoai = soDienThoai;
	}

	public NhaCungCap(String tenNhaCungCap, String diaChi, String email, String soDienThoai) {
		super();
		this.tenNCC = tenNhaCungCap;
		this.diaChi = diaChi;
		this.email = email;
		this.soDienThoai = soDienThoai;
	}

	public NhaCungCap(String maNCC) {
		super();
		this.maNCC = maNCC;
	}
	

}
