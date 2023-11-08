package utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;

public class Contains {
	private static String maNV;
	private static String tenNV;
	public static final boolean NAM = true;
	public static final boolean NU = false;
	public static final String NHAN_VIEN_QUAN_LY = "QL";
	public static final String NHAN_VIEN_BAN_HANG = "NV";
	public static final String KIEU_PHAN_CA_CO_DINH = "Cố Định";
	public static final String KIEU_PHAN_CA_TUY_CHINH = "Tùy Chỉnh";
	public static final int WIDTH_PANEL_CONTENT = 1320;
	public static final int HEIGHT_PANEL_CONTENT = 794;
	
	public static String getTenNV() {
		return tenNV;
	}
	public static void setTenNV(String tenNV) {
		Contains.tenNV = tenNV;
	}
	private static String role;

	public static String getMaNV() {
		return maNV;
	}
	public static void setMaNV(String maNV) {
		Contains.maNV = maNV;
	}
	public static String getRole() {
		return role;
	}
	public static void setRole(String role) {
		Contains.role = role;
	}
	
	private static String pathOfReportFiles = "ReportFiles\\";

	public static String getPathOfReportFiles() {
		return pathOfReportFiles;
	}

	public static void setPathOfReportFiles(String pathOfReportFiles) {
		Contains.pathOfReportFiles = pathOfReportFiles;
	}
	
	
}
