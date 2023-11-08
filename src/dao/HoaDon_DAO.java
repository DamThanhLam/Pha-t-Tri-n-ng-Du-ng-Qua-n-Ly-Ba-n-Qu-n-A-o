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
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

public class HoaDon_DAO {
	private List<HoaDon> dsHoaDon;

	public HoaDon_DAO() {
		super();
		this.dsHoaDon = new ArrayList<HoaDon>();
	}

	public List<HoaDon> getListHoaDon() {
		NhanVien_DAO dsnv = new NhanVien_DAO();
		KhachHang_DAO dskh = new KhachHang_DAO();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "Select * from HoaDon";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String maHD = rs.getString(1);
				String maKH = rs.getString(2);
				String maNV = rs.getString(3);
				Date ngayLap = rs.getDate(4);
				NhanVien nv = dsnv.getNhanVienTheoMa(maNV);
				KhachHang kh = dskh.getKhachHangTheoMa(maKH);

				HoaDon HD = new HoaDon(maHD, nv, kh, ngayLap);
				dsHoaDon.add(HD);
			}
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsHoaDon;
	}

	public List<HoaDon> timKiemHoaDon(String maHD, String hoTenNV, String tenKH, String sdtKH, Date ngayLap) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		KhachHang_DAO dskh = new KhachHang_DAO();
		NhanVien_DAO dsnv = new NhanVien_DAO();
		List<HoaDon> dstk = new ArrayList<HoaDon>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "Select HoaDon.*, NhanVien.*, KhachHang.*" + " FROM HoaDon"
					+ " INNER JOIN KhachHang ON HoaDon.maKH = KhachHang.maKH"
					+ " INNER JOIN NhanVien ON HoaDon.maNV = NhanVien.maNV"
					+ " where HoaDon.maHD like ? and NhanVien.ten like ? and KhachHang.ten like ? and KhachHang.soDienThoai like ?"
					+ " and CONVERT(DATE, HoaDon.ngayLap, 120) like ?";
			
			String ngayLapFormat = "";
			if(ngayLap != null) {
				ngayLapFormat = sdf.format(ngayLap);
			}
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, maHD + "%");
			stmt.setString(2, hoTenNV + "%");
			stmt.setString(3, tenKH + "%");
			stmt.setString(4, sdtKH + "%");
			stmt.setString(5, ngayLapFormat + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maHoaDon = rs.getString(1);
				String maNV = rs.getString(3);
				String maKhachHang = rs.getString(2);
				Date ngayLapHD = rs.getDate(4);
				NhanVien nv = dsnv.getNhanVienTheoMa(maNV);
				KhachHang kh = dskh.getKhachHangTheoMa(maKhachHang);

				dstk.add(new HoaDon(maHoaDon, nv, kh, ngayLapHD));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dstk;
	}

//	public List<HoaDon> timKiemHoaDonTheoNgayLap(Date ngayLap) {
//		KhachHang_DAO dskh = new KhachHang_DAO();
//		NhanVien_DAO dsnv = new NhanVien_DAO();
//		List<HoaDon> dstk = new ArrayList<HoaDon>();
//		
//		try {
//			Connection con = ConnectDB.getInstance().getConnection();
//			String sql = "Select HoaDon.*, NhanVien.*, KhachHang.*" + " FROM HoaDon"
//					+ " INNER JOIN KhachHang ON HoaDon.maKH = KhachHang.maKH"
//					+ " INNER JOIN NhanVien ON HoaDon.maNV = NhanVien.maNV";
//			String ngayLapFormat = sdf.format(ngayLap);
//			sql += " where CONVERT(DATE, HoaDon.ngayLap) = CONVERT(DATE, '" + ngayLapFormat + "' , 105)";
//			PreparedStatement stmt = con.prepareStatement(sql);
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				String maHoaDon = rs.getString(1);
//				String maNV = rs.getString(3);
//				String maKhachHang = rs.getString(2);
//				Date ngayLapHD = rs.getDate(4);
//				NhanVien nv = dsnv.getNhanVienTheoMa(maNV);
//				KhachHang kh = dskh.getKhachHangTheoMa(maKhachHang);
//
//				dstk.add(new HoaDon(maHoaDon, nv, kh, ngayLapHD));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return dstk;
//	}

	public double tinhTongTien(String maHD) {
		double tongTien = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "SELECT ChiTietHoaDon.soLuong, ChiTietHoaDon.donGia" + " FROM ChiTietHoaDon"
					+ " INNER JOIN HoaDon ON ChiTietHoaDon.maHD = HoaDon.maHD" + " WHERE HoaDon.maHD = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, maHD);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				float donGia = rs.getFloat(1);
				int soLuong = rs.getInt(2);

				tongTien += donGia * soLuong;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tongTien;
	}

	public HoaDon getHoaDonTheoMa(String maHD) {
		NhanVien_DAO dsnv = new NhanVien_DAO();
		KhachHang_DAO dskh = new KhachHang_DAO();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "Select * from HoaDon where maHD = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, maHD);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maHoaDonTimDuoc = rs.getString(1);
				String maKH = rs.getString(2);
				String maNV = rs.getString(3);
				Date ngayLap = rs.getDate(4);
				NhanVien nv = dsnv.getNhanVienTheoMa(maNV);
				KhachHang kh = dskh.getKhachHangTheoMa(maKH);

				return new HoaDon(maHoaDonTimDuoc, nv, kh, ngayLap);
			}
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}



	public KhachHang getKhachHangTheoHoaDon(String maHD) {
		KhachHang_DAO dskh = new KhachHang_DAO();
		KhachHang KH = null;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "select HoaDon.maKH" + " from HoaDon" + " where HoaDon.maHD like ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, maHD + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maKH = rs.getString(1);
				KH = dskh.getKhachHangTheoMa(maKH);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return KH;
	}
	
	
	public String getLenhDieuKienTheoLoaiThongKe(String loaiThongKe, Date ngayBatDau, Date ngayKetThuc) {
		String condition = "";
		if (loaiThongKe.equalsIgnoreCase("Ngày hôm nay")) {
			condition += " WHERE CONVERT(DATE, HoaDon.ngayLap) = CONVERT(DATE, GETDATE() , 105) ";
		} else if (loaiThongKe.equalsIgnoreCase("Tháng này")) {
			condition += " WHERE DATEPART(MONTH, HoaDon.ngayLap) = DATEPART(MONTH, GETDATE()) ";
		} else if (loaiThongKe.equalsIgnoreCase("Năm này")) {
			condition += " WHERE DATEPART(YEAR, HoaDon.ngayLap) = DATEPART(YEAR, GETDATE()) ";
		} else if (loaiThongKe.toString().equalsIgnoreCase("Tùy chỉnh")) {
			SimpleDateFormat simpleDateFortmat = new SimpleDateFormat("dd-MM-yyyy");
			if (ngayBatDau != null && ngayKetThuc != null) {
				String ngayBatDauStr = simpleDateFortmat.format(ngayBatDau);
				String ngayKetThucStr = simpleDateFortmat.format(ngayKetThuc);
				condition += " WHERE CONVERT(DATE, HoaDon.ngayLap) >= CONVERT(DATE, '" + ngayBatDauStr + "' , 105) "
						+ " AND CONVERT(DATE, HoaDon.ngayLap) <= CONVERT(DATE, '" + ngayKetThucStr + "', 105) ";
			}
		}
		return condition;
	}
	
	/**
	 * Tìm hóa đơn theo thời gian
	 * @param loaiThongKe
	 * @param ngayBatDau
	 * @param ngayKetThuc
	 * @return
	 */
	
	public List<HoaDon> getDanhSachHoaDonTheoNgay(String loaiThongKe, Date ngayBatDau, Date ngayKetThuc){
		List<HoaDon> dsHD = new ArrayList<HoaDon>();
		NhanVien_DAO dsnv = new NhanVien_DAO();
		KhachHang_DAO dskh = new KhachHang_DAO();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "Select *" + " FROM HoaDon"
					+ getLenhDieuKienTheoLoaiThongKe(loaiThongKe, ngayBatDau, ngayKetThuc);
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maHoaDon = rs.getString(1);
				String maKhachHang = rs.getString(2);
				String maNV = rs.getString(3);
				Date ngayLapHD = rs.getDate(4);
				NhanVien nv = dsnv.getNhanVienTheoMa(maNV);
				KhachHang kh = dskh.getKhachHangTheoMa(maKhachHang);

				dsHD.add(new HoaDon(maHoaDon, nv, kh, ngayLapHD));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dsHD;
	}
	
	/**
	 * 
	 * @param maKH
	 * @param loaiThongKe
	 * @param ngayBatDau
	 * @param ngayKetThuc
	 * @return
	 */
	
	public double tinhTongSoTienChiCuaKhachHang(String maKH, String loaiThongKe, Date ngayBatDau, Date ngayKetThuc) {

		double tongTien = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "select HoaDon.maHD" + " from HoaDon" 
						+ getLenhDieuKienTheoLoaiThongKe(loaiThongKe, ngayBatDau, ngayKetThuc)
						+ " and HoaDon.maKH like ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, maKH + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maHD = rs.getString(1);
				tongTien += tinhTongTien(maHD);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tongTien;
	}
	
	public int getSoHoaDonTheoKhachHang(String maKH, String loaiThongKe, Date ngayBatDau, Date ngayKetThuc) {
		int soHD = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "select HoaDon.maHD, HoaDon.maKH" + " from HoaDon" 
					+ getLenhDieuKienTheoLoaiThongKe(loaiThongKe, ngayBatDau, ngayKetThuc)
					+ " and HoaDon.maKH like ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, maKH);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				soHD ++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return soHD;
	}
	
	public double tinhDoanhThuTheoThang(int month) {
		double tongTien = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "select *" + " from HoaDon" 
					+ " where datepart(YEAR, HoaDon.ngayLap) = datepart(YEAR, GETDATE())"
					+ " and DATEPART(month, HoaDon.ngayLap) = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, month);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String maHD = rs.getString(1);
				tongTien += tinhTongTien(maHD);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tongTien;
	}
	
	public boolean themHoaDon(HoaDon hoaDon, String maKM) {
		int n=0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			
			String sql="insert into HoaDon ([maKH],[maNV],[ngayLap],[maKM]) values (?,?,?,?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, hoaDon.getKh().getMaKH());
			statement.setString(2, hoaDon.getNv().getMaNV());
			statement.setDate(3, (java.sql.Date) hoaDon.getNgayLap());
			statement.setString(4, maKM);
			
			n = statement.executeUpdate();
			statement.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n>0;
		
	}
}
