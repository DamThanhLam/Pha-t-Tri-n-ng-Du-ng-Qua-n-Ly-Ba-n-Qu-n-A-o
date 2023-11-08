package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.NhanVien;
import entity.TaiKhoan;
import utils.Contains;

public class TaiKhoan_DAO {
	public NhanVien dangNhap(String taiKhoan, String matKhau) {
		String sql = "SELECT * from TaiKhoan INNER JOIN NhanVien on TaiKhoan.maNV = NhanVien.maNV WHERE TaiKhoan.taiKhoan = ? and TaiKhoan.matKhau = ? and NhanVien.chucVu = ?";
		
		ConnectDB instance = ConnectDB.getInstance();
		try {
			instance.connect();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement;
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, taiKhoan);
			statement.setNString(2, matKhau);
			statement.setNString(3, Contains.getRole());
			ResultSet result = statement.executeQuery();
			result.next();
			if(result.getRow() > 0) {
				NhanVien n = new NhanVien(result.getString("maNV"),result.getString("ten"),result.getInt("gioiTinh") == 1 ? true:false,result.getString("chucVu"),result.getString("soDienThoai"),result.getString("diaChi") );
				con.close();
				return n;
			}else return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public List<TaiKhoan> getAllTaiKhoan() {
		String sql = "SELECT * from TaiKhoan INNER JOIN NhanVien on TaiKhoan.maNV = NhanVien.maNV ORDER BY TaiKhoan.maNV ASC";
		List<TaiKhoan> list = new ArrayList<TaiKhoan>();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement;
		try {
			statement = con.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				NhanVien nv = new NhanVien(result.getString("maNV"),result.getString("ten"),result.getInt("gioiTinh") == 1 ? true:false,result.getString("chucVu"),result.getString("soDienThoai"),result.getString("diaChi") );
				TaiKhoan tk = new TaiKhoan(result.getString("taiKhoan"), result.getString("matKhau"), nv);
				list.add(tk);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean themTaiKhoan(TaiKhoan tk) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO TaiKhoan(maNV,taiKhoan,matKhau) VALUES(?,?,?)";
		Connection con = ConnectDB.getConnection();
		
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, tk.getNhanVien().getMaNV());
			statement.setString(2, tk.getTaiKhoan());
			statement.setString(3, tk.getMatKhau());
			
			int result = statement.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean capNhatTaiKhoan(TaiKhoan tk) {
		// TODO Auto-generated method stub
		String sql = "UPDATE TaiKhoan set taiKhoan = ?, matKhau =? WHERE  maNV  = ?";
		Connection con = ConnectDB.getConnection();
		
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(3, tk.getNhanVien().getMaNV());
			statement.setString(1, tk.getTaiKhoan());
			statement.setString(2, tk.getMatKhau());
			
			int result = statement.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
