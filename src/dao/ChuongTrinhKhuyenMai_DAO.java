package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import connectDB.ConnectDB;
import entity.ChuongTrinhKhuyenMai;

public class ChuongTrinhKhuyenMai_DAO {
	private List<ChuongTrinhKhuyenMai> danhSachCTKM;

	public ChuongTrinhKhuyenMai_DAO() {
		super();
		this.danhSachCTKM = new ArrayList<ChuongTrinhKhuyenMai>();
	}

	public List<ChuongTrinhKhuyenMai> getListChuongTrinhKhuyenMai() {

		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "Select * from ChuongTrinhKhuyenMai";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String maKM = rs.getString(1);
				String tenKM = rs.getString(2);
				String moTa = rs.getString(3);
				Date ngayBatDau = rs.getDate(4);
				Date ngayKetThuc = rs.getDate(5);
				int soLuong = rs.getInt(6);
				double giaGiam = rs.getDouble(7);
				String maCap = rs.getString(8);

				ChuongTrinhKhuyenMai ctkm = new ChuongTrinhKhuyenMai(maKM, tenKM, moTa, ngayBatDau, ngayKetThuc,
						soLuong, giaGiam, maCap);
				danhSachCTKM.add(ctkm);
			}
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return danhSachCTKM;
	}

	public boolean themChuongTrinhKhuyenMai(ChuongTrinhKhuyenMai ctkm) throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		int n = 0;
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "INSERT INTO ChuongTrinhKhuyenMai (tenKM, moTa, ngayBatDau, ngayKetThuc, soLuong, giaGiam, maCaptcha) VALUES (?, ?, CONVERT(date, ?, 105), CONVERT(date, ?, 105), ?, ?, ?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ctkm.getTenKM());
			stmt.setString(2, ctkm.getMoTa());
			stmt.setString(3, sdf.format(ctkm.getNgayBatDau()));
			stmt.setString(4, sdf.format(ctkm.getNgayKetThuc()));
			stmt.setInt(5, ctkm.getSoLuong());
			stmt.setFloat(6, (float) ctkm.getGiaGiam());
			stmt.setString(7, ctkm.getMaCaptcha());
			n = stmt.executeUpdate();
			stmt.close();

		} catch (SQLException e) {
			throw e;
		}
		return n > 0;
	}

	public boolean xoaChuongTrinhKhuyenMai(String maKM) throws SQLException {
		int n = 0;
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "DELETE FROM ChuongTrinhKhuyenMai WHERE maKM = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maKM);
			n = stmt.executeUpdate();
			stmt.close();

		} catch (SQLException e) {
			throw e;
		}
		return n > 0;
	}

	public boolean capNhatChuongTrinhKhuyenMai(ChuongTrinhKhuyenMai ctkm) throws SQLException {
		int n = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "update ChuongTrinhKhuyenMai set tenKM = ?, moTa = ?, ngayBatDau = CONVERT(date, ?, 105), ngayKetThuc = CONVERT(date, ?, 105), soLuong = ?, giaGiam = ?, maCaptcha = ? where maKM=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ctkm.getTenKM());
			stmt.setString(2, ctkm.getMoTa());
			stmt.setString(3, sdf.format(ctkm.getNgayBatDau()));
			stmt.setString(4, sdf.format(ctkm.getNgayKetThuc()));
			stmt.setInt(5, ctkm.getSoLuong());
			stmt.setFloat(6, (float) ctkm.getGiaGiam());
			stmt.setString(7, ctkm.getMaCaptcha());
			stmt.setString(8, ctkm.getMaKM());
			n = stmt.executeUpdate();
			stmt.close();

		} catch (SQLException e) {
			throw e;
		}
		return n > 0;
	}

	public ChuongTrinhKhuyenMai getChuongTrinhKhuyenMaiVuaCapNhat() throws SQLException {
		ChuongTrinhKhuyenMai ctkm = null;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "SELECT TOP 1 * FROM ChuongTrinhKhuyenMai ORDER BY time_stamp DESC";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String maKM = rs.getString(1);
				String tenKM = rs.getString(2);
				String moTa = rs.getString(3);
				Date ngayBatDau = rs.getDate(4);
				Date ngayKetThuc = rs.getDate(5);
				int soLuong = rs.getInt(6);
				double giaGiam = rs.getDouble(7);
				String maCap = rs.getString(8);
				ctkm = new ChuongTrinhKhuyenMai(maKM, tenKM, moTa, ngayBatDau, ngayKetThuc, soLuong, giaGiam, maCap);
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO: handle exception
			throw e;
		}
		return ctkm;
	}
}
