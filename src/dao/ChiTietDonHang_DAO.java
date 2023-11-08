package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.ChiTietDonHang;
import entity.DonHang;
import entity.HoaDon;
import entity.SanPham;

public class ChiTietDonHang_DAO {
	public List<ChiTietDonHang> getListChiTietDonHang(){
		List<ChiTietDonHang> dsChiTietDonHang=new ArrayList<ChiTietDonHang>();
		try {
			Connection con=ConnectDB.getInstance().getConnection();
			String sql = "select * from ChiTietDonHang" ;
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				SanPham maSP=new SanPham(rs.getString(1));
				DonHang maDH=new DonHang(rs.getString(2));
				float donGia= rs.getFloat(3);
				int soLuong=rs.getInt(4);
				
				ChiTietDonHang chiTietDonHang=new ChiTietDonHang(maSP, maDH, donGia, soLuong);
				dsChiTietDonHang.add(chiTietDonHang);
		}		
			statement.close();

		}catch (Exception e) {
			e.printStackTrace();
		}
		return dsChiTietDonHang;
	}
	
	public List<String[]> getListChiTietDonHangraGUI(String maDH){
		List<String[]> dsChiTietDonHang=new ArrayList<>();
		try {
			Connection con=ConnectDB.getInstance().getConnection();
			
			String sql="SELECT "
					+ " ChiTietDonHang.maSP, "
					+ " SanPham.tenSP, "
					+ " DanhMuc.tenDM, "
					+ " MauSac.tenMS, "
					+ " ChatLieu.tenCL, "
					+ "	KichCo.tenKC,"
					+ " NhaCungCap.tenNCC,"
					+ " ChiTietDonHang.soLuong, "
					+ " ChiTietDonHang.donGia"
					+ " FROM ChiTietDonHang"
					+ " INNER JOIN SanPham ON ChiTietDonHang.maSP = SanPham.maSP"
					+ " INNER JOIN DanhMuc ON SanPham.maDM = DanhMuc.maDM"
					+ " INNER JOIN MauSac ON SanPham.maMS = MauSac.maMS"
					+ " INNER JOIN ChatLieu ON SanPham.maCL = ChatLieu.maCL"
					+ " INNER JOIN KichCo on SanPham.maKC=KichCo.maKC"
					+ " INNER JOIN NhaCungCap ON SanPham.maNCC = NhaCungCap.maNCC"
					+ " WHERE ChiTietDonHang.maDH = ?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, maDH);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String maSP=rs.getString(1);
				System.out.println(maSP);
				String tenSP=rs.getString(2);
				String tenDM=rs.getString(3);
				String tenMS=rs.getString(4);
				String tenCL=rs.getString(5);
				String tenKC=rs.getString(6);
				String tenNCC=rs.getString(7);
				String soLuong=rs.getString(8);
				String donGia=rs.getString(9);
				
				String[] row= {maSP,tenSP,tenDM,tenMS,tenCL,tenKC,tenNCC,soLuong,donGia};
				dsChiTietDonHang.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsChiTietDonHang;
	} 
	public boolean themChiTietDonHang(ChiTietDonHang chiTietDonHang) {
		int n=0;
		try {
			Connection con=ConnectDB.getInstance().getConnection();
			
			String sql="insert into ChiTietDonHang ([maSP],[maDH],[donGia],[soLuong] values(?,?,?,?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, chiTietDonHang.getSanPham().getMaSP());
			statement.setString(2, chiTietDonHang.getDonHang().getMaDH());
			statement.setFloat(3, chiTietDonHang.getDonGia());
			statement.setInt(4, chiTietDonHang.getSoLuong());
			
			n = statement.executeUpdate();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n>0;
	}

	public boolean xoaChiTietDonHang(String maDH) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("DELETE FROM ChiTietDonHang WHERE maDH = ?");
			stmt.setString(1, maDH);
			n = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0; 
	}
	 
}
