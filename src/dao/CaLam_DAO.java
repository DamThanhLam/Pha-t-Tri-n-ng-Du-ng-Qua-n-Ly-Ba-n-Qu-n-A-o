package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.CaLam;

public class CaLam_DAO {
	private List<CaLam> dsCaLam;

	public CaLam_DAO() {
		dsCaLam = new ArrayList<CaLam>();
	}
	public List<CaLam> getAllCaLam(){
		String sql = "SELECT * FROM CaLam";
		List<CaLam> list = new ArrayList<CaLam>();
			try {
				ConnectDB.getInstance().connect();
				Connection con = ConnectDB.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);
				ResultSet result = statement.executeQuery();
				while(result.next()) {
					CaLam cl = new CaLam(result.getString("maCL"),result.getTime("gioBatDau"),result.getTime("gioKetThuc"));
					list.add(cl);
				}
				statement.close();
				return list;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}
	

	public List<CaLam> getListCaLam(){
		
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "Select * from CaLam";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maCL = rs.getString(1);
				String gioBatDau = rs.getString(2);
				String gioKetThuc = rs.getString(3);
				
//				CaLam caLam = new CaLam(maCL, gioBatDau, gioKetThuc);
//				dsCaLam.add(caLam);
			}
			stmt.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsCaLam;
	}
	
	public boolean themCaLam (CaLam caLam){
		int n = 0 ;
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "INSERT INTO CaLam (gioBatDau, gioKetThuc) VALUES (?, ?)" ;
			stmt = con.prepareStatement(sql);
//			stmt.setString(1, caLam.getGioBatDau());
//			stmt.setString(2, caLam.getGioKetThuc());
//			n = stmt.executeUpdate();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return n > 0 ;
	}
	
	public boolean xoaCaLam (String maCL){
		int n = 0 ;
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "DELETE FROM CaLam WHERE maCL = ?" ;
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maCL);
			n = stmt.executeUpdate();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return n > 0 ;
	}
	
	public boolean capNhatCaLam (CaLam caLam){
		int n = 0 ;
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "update CaLam set gioBatDau=?, gioKetThuc =? where maCL=?" ;
			stmt = con.prepareStatement(sql);
//			stmt.setString(1, caLam.getGioBatDau());
//			stmt.setString(2, caLam.getGioKetThuc());
//			stmt.setString(3, caLam.getMaCaLam());
			n = stmt.executeUpdate();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return n > 0 ;
	}
	
	public CaLam getCaLamVuaCapNhat() {
		CaLam cl = null;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "SELECT TOP 1 * FROM CaLam ORDER BY time_stamp DESC";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maCL = rs.getString(1);
				String gioBatDau = rs.getString(2);
				String gioKetThuc = rs.getString(3);
//				cl = new CaLam(maCL, gioBatDau, gioKetThuc);
			}
			stmt.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cl;
	}
}
