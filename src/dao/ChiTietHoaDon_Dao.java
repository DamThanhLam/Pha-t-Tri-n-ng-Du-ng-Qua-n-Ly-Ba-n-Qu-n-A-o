package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.SanPham;

public class ChiTietHoaDon_Dao {
	private List<ChiTietHoaDon> dsCTHD;
	
	
	
	public ChiTietHoaDon_Dao() {
		super();
		dsCTHD = new ArrayList<ChiTietHoaDon>();
	}



	public List<ChiTietHoaDon> getListSanPhamTuHoaDon(String maHD) {
		HoaDon_DAO dsHD = new HoaDon_DAO();
		SanPham_DAO dsSP = new SanPham_DAO();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "SELECT ChiTietHoaDon.*"
					+ " FROM ChiTietHoaDon"
					+ " inner join SanPham on ChiTietHoaDon.maSP = SanPham.maSP"
					+ " WHERE ChiTietHoaDon.maHD LIKE ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, maHD);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String maHDTimDuoc = rs.getString(1);
				String maSP = rs.getString(2);
				double donGia = rs.getDouble(3);
				int soLuong = rs.getInt(4);
				HoaDon HD = dsHD.getHoaDonTheoMa(maHDTimDuoc);
				SanPham SP = dsSP.timKiemSanPham(maSP);
				ChiTietHoaDon CTHD = new ChiTietHoaDon(HD, SP, donGia, soLuong);
				dsCTHD.add(CTHD);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dsCTHD;
	}
	

}
