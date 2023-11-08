package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.NhaCungCap;

public class NhaCungCap_DAO {
	private List<NhaCungCap> danhSachNhaCungCap;

	public NhaCungCap_DAO() {
		super();
		this.danhSachNhaCungCap = new ArrayList<NhaCungCap>();
	}
	public List<NhaCungCap> getListNhaCungCapTheoTenNCC(String ten){
		List<NhaCungCap> dsNhaCungCap = new ArrayList<NhaCungCap>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from NhaCungCap where tenNCC=?" ;
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, ten);
			ResultSet rs = statement.executeQuery();
				
			
			while (rs.next()) {
				String maNCC = rs.getString(1) ;
				String tenNCC = rs.getString(2) ;
				String diaChi = rs.getString(3) ;
				String email = rs.getString(4) ;
				String sdt = rs.getString(5) ;
				NhaCungCap nhaCungCap = new NhaCungCap(maNCC, tenNCC, diaChi, email, sdt);
				dsNhaCungCap.add(nhaCungCap);
			}
			statement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNhaCungCap;
	}
	public List<NhaCungCap> getListNhaCungCap() {

		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "Select * from NhaCungCap";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String maNCC = rs.getString(1);
				String tenNCC = rs.getString(2);
				String DC = rs.getString(3);
				String email = rs.getString(4);
				String sdt = rs.getString(5);

				NhaCungCap ncc = new NhaCungCap(maNCC, tenNCC, DC, email, sdt);
				danhSachNhaCungCap.add(ncc);
			}
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return danhSachNhaCungCap;
	}

	public boolean themNhaCungCap(NhaCungCap ncc){
		int n = 0;
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
			try {
				String sql = "INSERT INTO NhaCungCap (tenNCC, diaChi, email, soDienThoai) VALUES (?, ?, ?, ?)";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, ncc.getTenNCC());
				stmt.setString(2, ncc.getDiaChi());
				stmt.setString(3, ncc.getEmail());
				stmt.setString(4, ncc.getSoDienThoai());
				n = stmt.executeUpdate();
				stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		return n > 0;
	}

	public boolean xoaNhaCungCap(String maNCC) {
		int n = 0;
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "DELETE FROM NhaCungCap WHERE maNCC = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maNCC);
			n = stmt.executeUpdate();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}


	public boolean capNhatNhaCungCap(NhaCungCap ncc) {
		int n = 0;
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "update NhaCungCap set tenNCC = ?, diaChi = ?, email = ?, soDienThoai = ? where maNCC=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ncc.getTenNCC());
			stmt.setString(2, ncc.getDiaChi());
			stmt.setString(3, ncc.getEmail());
			stmt.setString(4, ncc.getSoDienThoai());
			stmt.setString(5, ncc.getMaNCC());
			n = stmt.executeUpdate();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public NhaCungCap getNhaCungCapVuaCapNhat() {
		NhaCungCap ncc = null;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "SELECT TOP 1 * FROM NhaCungCap ORDER BY time_stamp DESC";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maNCC = rs.getString(1);
				String tenNCC = rs.getString(2);
				String dc = rs.getString(3);
				String email = rs.getString(4);
				String sdt = rs.getString(5);
				ncc = new NhaCungCap(maNCC, tenNCC, dc, email, sdt);
			}
			stmt.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ncc;
	}
	
	public List<NhaCungCap> timKiemNhaCungCap(String maNCC, String tenNCC, String diaChi, String email, String sdt) {
		List<NhaCungCap> dsTK = new ArrayList<NhaCungCap>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "Select * from NhaCungCap where maNCC like ? and tenNCC like ? and diaChi like ? and email like ? and soDienThoai like ?  ";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" +maNCC + "%");
			stmt.setString(2, "%" +tenNCC + "%");
			stmt.setString(3, "%" +diaChi + "%");
			stmt.setString(4, "%" +email + "%");
			stmt.setString(5, "%" +sdt + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maTimDuoc = rs.getString(1);
				String tenTimDuoc = rs.getString(2);
				String diaChiTimDuoc = rs.getString(3);
				String emailTimDuoc = rs.getString(4);
				String sdtTimDuoc = rs.getString(5);

				NhaCungCap ncc = new NhaCungCap(maTimDuoc, tenTimDuoc, diaChiTimDuoc, emailTimDuoc, sdtTimDuoc);
				dsTK.add(ncc);
			}
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsTK;
	}
}
