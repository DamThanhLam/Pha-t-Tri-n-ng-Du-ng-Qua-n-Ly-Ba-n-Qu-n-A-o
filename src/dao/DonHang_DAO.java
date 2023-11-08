package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


import connectDB.ConnectDB;
import entity.DonHang;
import entity.KhachHang;
import entity.NhanVien;

public class DonHang_DAO {
	public boolean taoDonHang(DonHang donHang) {
		String sql = "INSERT INTO DonHang(maKH, maNV, ngayDat) VALUES(?,?,?)";
		
		try {
			Connection con = ConnectDB.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			Date ngayDat = Date.valueOf(donHang.getNgayDat());
			
			statement.setString(1, donHang.getKhachHang().getMaKH());
			statement.setString(2, donHang.getNhanVien().getMaNV());
			statement.setDate(3, ngayDat);
			
			int result = statement.executeUpdate();
			return result > 0? true:false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public String getMaHoaDonVuaTao() {
		// TODO Auto-generated method stub
		String sql = "SELECT TOP(1) maDH FROM DonHang ORDER BY maDH DESC";
		String maDH = null;
		try {
			Connection con = ConnectDB.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				maDH = result.getString("maDH");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maDH;
	}
	public List<DonHang> getListDonHang(){
		List<DonHang> dsDonHang=new ArrayList<DonHang>();
		try {
			Connection con=ConnectDB.getInstance().getConnection();
			String sql="select DonHang.maDH,"
					+ "NhanVien.maNV,"
					+ "KhachHang.maKH,"
					+ "DonHang.ngayDat"
					+ " from DonHang"
					+ " inner join NhanVien on NhanVien.maNV=NhanVien.maNV"
					+ " inner join KhachHang on KhachHang.maKH=DonHang.maKH";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String maDH=rs.getString(1);
				NhanVien tenNV=new NhanVien(null, rs.getString(2), true, null, null, null);
				KhachHang tenKH=new KhachHang(null, rs.getString(3), true, null, null);
				LocalDate ngayDat=rs.getDate(4).toLocalDate();
				
				DonHang donHang=new DonHang(maDH, tenNV, tenKH, ngayDat);
				dsDonHang.add(donHang);
			}
			
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsDonHang;
	}
	
	public List<DonHang> getListDonHangTheoDK(String maDH, String tenNV, String tenKH, String ngayDat){
		List<DonHang> dsDonHang=new ArrayList<DonHang>();
		
		try {
			Connection con=ConnectDB.getInstance().getConnection();
			
			String sql="select DonHang.maDH,"
					+ "NhanVien.ten,"
					+ "KhachHang.ten,"
					+ "DonHang.ngayDat"
					+ " from DonHang"
					+ " inner join NhanVien on NhanVien.maNV=DonHang.maNV"
					+ " inner join KhachHang on KhachHang.maKH=DonHang.maKH"
					+ " where DonHang.maDH LIKE ? and NhanVien.ten LIKE ? and KhachHang.ten LIKE ? and DonHang.ngayDat = ?";
			
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1, maDH + "%");
			stmt.setString(2, tenNV + "%");
			stmt.setString(3, tenKH + "%");
			//stmt.setString(4, ngayDat + "%");
			if (!ngayDat.isEmpty()) {
			    DateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
			    java.util.Date parsedDate = dateFormat.parse(ngayDat);
			    java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
			    stmt.setDate(4, sqlDate);
			} else {
			    stmt.setNull(4, java.sql.Types.DATE);
			}
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				String maDH2=rs.getString(1);
				NhanVien tenNV2=new NhanVien(null, rs.getString(2), true, null, null, null);
				KhachHang tenKH2=new KhachHang(null, rs.getString(3), true, null, null);
				LocalDate ngayDat2=rs.getDate(4).toLocalDate();
				
				DonHang donHang=new DonHang(maDH2, tenNV2, tenKH2, ngayDat2);
				dsDonHang.add(donHang);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsDonHang;
	}
	
	public List<DonHang> getListDonHangChoGui(){
		List<DonHang> dsDonHang=new ArrayList<DonHang>();
		try {
			Connection con=ConnectDB.getInstance().getConnection();
			
			String sql="select DonHang.maDH,"
					+ "NhanVien.ten,"
					+ "KhachHang.ten,"
					+ "KhachHang.soDienThoai,"
					+ "KhachHang.diaChi,"
					+ "DonHang.ngayDat"
					+ " from DonHang"
					+ " inner join NhanVien on NhanVien.maNV=DonHang.maNV"
					+ " inner join KhachHang on KhachHang.maKH=DonHang.maKH";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String maDH=rs.getString(1);
				NhanVien tenNV=new NhanVien(null, rs.getString(2), true, null, null, null);
				KhachHang tenKH=new KhachHang(null, rs.getString(3), true, null, null);
				KhachHang sdtKH=new KhachHang(null, null, true, rs.getString(4), null);
				KhachHang diaChi=new KhachHang(null, null, true, null, rs.getString(5));
				LocalDate ngayDat=rs.getDate(6).toLocalDate();
				
				DonHang donHang=new DonHang(maDH, tenNV, tenKH, ngayDat);
				dsDonHang.add(donHang);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsDonHang;
	}
	
	public List<String[]> getListDonHangChoGui2(){
		List<String[]> dsDonHang=new ArrayList<>();
		try {
			Connection con=ConnectDB.getInstance().getConnection();
			
			String sql="select DonHang.maDH,"
					+ "NhanVien.ten,"
					+ "KhachHang.ten,"
					+ "KhachHang.soDienThoai,"
					+ "KhachHang.diaChi,"
					+ "DonHang.ngayDat"
					+ " from DonHang"
					+ " inner join NhanVien on NhanVien.maNV=DonHang.maNV"
					+ " inner join KhachHang on KhachHang.maKH=DonHang.maKH";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String maDH=rs.getString(1);
//			
				
				String tenNVStr=rs.getString(2);
				String tenKHStr=rs.getString(3);
				String sdtKHStr = rs.getString(4); 
				String diaChiStr = rs.getString(5);
				String ngayDatStr =rs.getString(6);
				float total = calculateTotal(maDH); // Gọi hàm calculateTotal để tính tổng
	            String totalStr = Float.toString(total);
				String [] row= {maDH,tenNVStr,tenKHStr,sdtKHStr,diaChiStr,ngayDatStr,totalStr};
				dsDonHang.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsDonHang;
	}
	
	
	
	public float calculateTotal(String maDonHang) {
	    float total = 0;
	    try {
	        Connection con = ConnectDB.getInstance().getConnection();
	        String sql = "SELECT (ChiTietDonHang.soLuong * ChiTietDonHang.donGia) AS thanhTien FROM ChiTietDonHang WHERE maDH = ?";
	        PreparedStatement stmt = con.prepareStatement(sql);
	        stmt.setString(1, maDonHang);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            total += rs.getFloat("thanhTien");
	        }
	        stmt.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return total;
	}


	
	public boolean xoaDonHang(String maDH) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("DELETE FROM DonHang WHERE maDH = ?");
			stmt.setString(1, maDH);
			n = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0; 
	}
	
	
}
