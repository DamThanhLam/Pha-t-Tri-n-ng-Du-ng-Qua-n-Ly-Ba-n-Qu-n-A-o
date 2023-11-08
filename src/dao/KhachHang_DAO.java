package dao;

import java.io.Console;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import connectDB.ConnectDB;
import entity.CaLam;
import entity.KhachHang;

public class KhachHang_DAO {
	private List<KhachHang> dskh;

	public KhachHang_DAO() {
		this.dskh = new ArrayList<KhachHang>();
	}

	public List<KhachHang> getListKhachHang() {

		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "Select * from KhachHang";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String maKH = rs.getString(1);
				String hoTen = rs.getString(2);
				boolean gt = rs.getBoolean(3);
				String sdt = rs.getString(4);
				String diaChi = rs.getString(5);

				KhachHang kh = new KhachHang(maKH, hoTen, gt, sdt, diaChi);
				dskh.add(kh);
			}
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dskh;
	}

	public boolean themKhachHang(KhachHang kh) {
		int n = 0;
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
			try {
				String sql = "INSERT INTO KhachHang (ten, gioiTinh, soDienThoai, diaChi) VALUES (?, ?, ?, ?)";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, kh.getHoTen());
				stmt.setBoolean(2, kh.isGioiTinh());
				stmt.setString(3, kh.getSdt());
				stmt.setString(4, kh.getDiaChi());
				n = stmt.executeUpdate();
				stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		return n > 0;
	}

	public boolean xoaKhachHang(String maKH) {
		int n = 0;
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "DELETE FROM KhachHang WHERE maKH = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maKH);
			n = stmt.executeUpdate();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}


	public boolean capNhatKhachHang(KhachHang kh) {
		int n = 0;
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "update KhachHang set ten=?, gioiTinh=?, soDienThoai=?, diaChi=? where maKH=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, kh.getHoTen());
			stmt.setBoolean(2, kh.isGioiTinh());
			stmt.setString(3, kh.getSdt());
			stmt.setString(4, kh.getDiaChi());
			stmt.setString(5, kh.getMaKH());
			n = stmt.executeUpdate();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public KhachHang getKhachHangVuaCapNhat() {
		KhachHang kh = null;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "SELECT TOP 1 * FROM KhachHang ORDER BY time_stamp DESC";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				boolean gt = rs.getBoolean(3);
				String sdt = rs.getString(4);
				String dc = rs.getString(5);
				kh = new KhachHang(maKH, tenKH, gt, sdt, dc);
			}
			stmt.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kh;
	}
	
	public List<KhachHang> timKiemKhachHang(String maKH, String hoTen, boolean gt, String sdt, String dc){
		List<KhachHang> dstk = new ArrayList<KhachHang>();
		
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "select * from KhachHang where maKH like ? and ten like ? and gioiTinh = ? and diaChi like ? and soDienThoai like ?  " ;
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, maKH + "%");
			stmt.setString(2, hoTen + "%");
			stmt.setBoolean(3, gt);
			stmt.setString(4, sdt + "%");
			stmt.setString(5, dc + "%");
			String query = stmt.toString();
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				boolean gioiTinh = rs.getBoolean(3);
				String soDienThoai = rs.getString(4);
				String diaChi = rs.getString(5);
				
				dstk.add(new KhachHang(ma, ten, gioiTinh, soDienThoai, diaChi));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dstk;
	}
	public KhachHang timKiemKhachHang(String sdt){
		KhachHang kh = null;
		
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "select * from KhachHang where  soDienThoai = ?  " ;
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, sdt);
			String query = stmt.toString();
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				boolean gioiTinh = rs.getBoolean(3);
				String soDienThoai = rs.getString(4);
				String diaChi = rs.getString(5);
				
				kh = new KhachHang(ma, ten, gioiTinh, soDienThoai, diaChi);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return kh;
	}
	
	public KhachHang getKhachHangTheoMa(String maKH){
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "select * from KhachHang where maKH like ? " ;
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, maKH + "%");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				boolean gioiTinh = rs.getBoolean(3);
				String soDienThoai = rs.getString(4);
				String diaChi = rs.getString(5);
				
				return new KhachHang(ma, ten, gioiTinh, soDienThoai, diaChi);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
