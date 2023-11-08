package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import connectDB.ConnectDB;
import entity.BangPhanCa;
import entity.CaLam;
import entity.NhanVien;

public class BangPhanCa_DAO {
	public List<BangPhanCa> getAllBangPhanCa(){
		String sql = "SELECT * FROM BangPhanCa JOIN CaLam ON BangPhanCa.maCL = CaLam.maCL JOIN NhanVien ON NhanVien.maNV = BangPhanCa.maNV ORDER BY BangPhanCa.ngayBatDau ASC";
		List<BangPhanCa> list = new ArrayList<BangPhanCa>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				Date ngayBatDau = result.getDate("ngayBatDau");
				Date ngayKetThuc = result.getDate("ngayKetThuc");
				LocalDate localDateBatDau = ngayBatDau.toLocalDate();
				LocalDate localDateKetThuc = ngayKetThuc.toLocalDate();
				
				CaLam cl = new CaLam(result.getString("maCL"),result.getTime("gioBatDau"),result.getTime("gioKetThuc") );
				NhanVien nv = new NhanVien(result.getString("maNV"),result.getString("ten"),result.getInt("gioiTinh") == 1 ? utils.Contains.NAM:utils.Contains.NU,result.getString("chucVu"),result.getString("soDienThoai"),result.getString("diaChi") );
				BangPhanCa bpc = new BangPhanCa(nv, cl,localDateBatDau,localDateKetThuc,  result.getString("kieuPhanCa"));
				list.add(bpc);
			}
			statement.close();
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List<BangPhanCa> timKiemBangPhanCa(LocalDate n){
		String sqlKieuPhanCaTuyChinh = "SELECT * FROM BangPhanCa JOIN CaLam ON BangPhanCa.maCL = CaLam.maCL JOIN NhanVien ON NhanVien.maNV = BangPhanCa.maNV WHERE (ngayBatDau >= ? OR ngayKetThuc >= ? ) AND kieuPhanCa = ? ORDER BY BangPhanCa.ngayBatDau ASC";
		String sqlKieuPhanCaCoDinh = "SELECT * FROM  BangPhanCa JOIN CaLam ON BangPhanCa.maCL = CaLam.maCL JOIN NhanVien ON NhanVien.maNV = BangPhanCa.maNV WHERE ngayKetThuc IS NULL";
		List<BangPhanCa> list = new ArrayList<BangPhanCa>();
		
		Date dateNgayLam = Date.valueOf(n);
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			
			PreparedStatement statementKieuPhanCaTuyChinh = con.prepareStatement(sqlKieuPhanCaTuyChinh);
			statementKieuPhanCaTuyChinh.setDate(1, dateNgayLam);
			statementKieuPhanCaTuyChinh.setDate(2, dateNgayLam);
			statementKieuPhanCaTuyChinh.setString(3, utils.Contains.KIEU_PHAN_CA_TUY_CHINH);	
			ResultSet result = statementKieuPhanCaTuyChinh.executeQuery();
			while(result.next()) {
				Date ngayBatDau = result.getDate("ngayBatDau");
				Date ngayKetThuc = result.getDate("ngayKetThuc");
				LocalDate localDateBatDau = ngayBatDau.toLocalDate();
				LocalDate localDateKetThuc = ngayKetThuc.toLocalDate();
				
				
				CaLam cl = new CaLam(result.getString("maCL"),result.getTime("gioBatDau"),result.getTime("gioKetThuc") );
				NhanVien nv = new NhanVien(result.getString("maNV"),result.getString("ten"),result.getInt("gioiTinh") == 1 ? utils.Contains.NAM:utils.Contains.NU,result.getString("chucVu"),result.getString("soDienThoai"),result.getString("diaChi") );
				BangPhanCa bpc = new BangPhanCa(nv, cl,localDateBatDau,localDateKetThuc,  result.getString("kieuPhanCa"));
				list.add(bpc);
			}
			
			PreparedStatement statementKieuPhanCaCoDinh = con.prepareStatement(sqlKieuPhanCaCoDinh);
			result = statementKieuPhanCaCoDinh.executeQuery();
			while(result.next()) {
				Date ngayBatDau = result.getDate("ngayBatDau");
				Date ngayKetThuc = result.getDate("ngayKetThuc");
				LocalDate localDateBatDau = ngayBatDau.toLocalDate();
				LocalDate localDateKetThuc = ngayKetThuc!= null? ngayKetThuc.toLocalDate():null;
				
				
				CaLam cl = new CaLam(result.getString("maCL"),result.getTime("gioBatDau"),result.getTime("gioKetThuc") );
				NhanVien nv = new NhanVien(result.getString("maNV"),result.getString("ten"),result.getInt("gioiTinh") == 1 ? utils.Contains.NAM:utils.Contains.NU,result.getString("chucVu"),result.getString("soDienThoai"),result.getString("diaChi") );
				BangPhanCa bpc = new BangPhanCa(nv, cl,localDateBatDau,localDateKetThuc,  result.getString("kieuPhanCa"));
				list.add(bpc);
			}
			
			// Định nghĩa một bộ so sánh (Comparator) dựa trên thuộc tính NgayBatDau
			Comparator<BangPhanCa> ngayBatDauComparator = new Comparator<BangPhanCa>() {
			    @Override
			    public int compare(BangPhanCa itemI, BangPhanCa itemJ) {
			        return itemI.getNgayBatDau().compareTo(itemJ.getNgayBatDau());
			    }
			};

			// Sử dụng Collections.sort() để sắp xếp danh sách list
			Collections.sort(list, ngayBatDauComparator);
			
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public boolean themBangPhanCa(BangPhanCa bpc) {
		String sql ="INSERT INTO BangPhanCa(maNV,maCL,ngayBatDau,ngayKetThuc,kieuPhanCa) VALUES(?,?,?,?,?)";
		LocalDate ngayBatDau = bpc.getNgayBatDau();
		LocalDate ngayKetThuc = bpc.getNgayKetThuc();
		Date dateNgayBatDau =  Date.valueOf(ngayBatDau);
		Date dateNgayKetThuc = ngayKetThuc != null ? Date.valueOf(ngayKetThuc):null;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, bpc.getNhanVien().getMaNV());
			statement.setString(2, bpc.getCaLam().getMaCL());
			statement.setDate(3, dateNgayBatDau);
			statement.setDate(4, dateNgayKetThuc);
			statement.setString(5, bpc.getKieuPhanCa());
			
			int result = statement.executeUpdate();
			statement.close();
			
			return result > 0? true:false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/*
	 * được sử dụng để kiểm tra nhân viên có phải là kiểu phân ca mặc định hay không 
	 */
	public BangPhanCa kiemTraKieuPhanCaGanNhat(String maNV, String maCL) {
		String sql = "SELECT * FROM BangPhanCa JOIN CaLam ON BangPhanCa.maCL = CaLam.maCL JOIN NhanVien ON NhanVien.maNV = BangPhanCa.maNV  WHERE BangPhanCa.maNV = ? AND BangPhanCa.maCL = ? ORDER BY ngayBatDau DESC";
		BangPhanCa bpc = null;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maNV);
			statement.setString(2, maCL);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				Date ngayBatDau = result.getDate("ngayBatDau");
				Date ngayKetThuc = result.getDate("ngayBatDau");
				LocalDate localDateBatDau = ngayBatDau.toLocalDate();
				LocalDate localDateKetThuc = ngayKetThuc.toLocalDate();
				
				
				CaLam cl = new CaLam(result.getString("maCL"),result.getTime("gioBatDau"),result.getTime("gioKetThuc") );
				NhanVien nv = new NhanVien(result.getString("maNV"),result.getString("ten"),result.getInt("gioiTinh") == 1 ? utils.Contains.NAM:utils.Contains.NU,result.getString("chucVu"),result.getString("soDienThoai"),result.getString("diaChi") );
				bpc = new BangPhanCa(nv, cl,localDateBatDau,localDateKetThuc,  result.getString("kieuPhanCa"));
			}
			return bpc;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public boolean capNhatNgayKetThucChoKieuPhanCaMacDinh(BangPhanCa bpc) {
		String sql ="UPDATE BangPhanCa SET ngayKetThuc =? ,kieuPhanCa=? WHERE maNV = ? AND maCL = ? AND ngayBatDau = ? ";
		LocalDate ngayBatDau = bpc.getNgayBatDau();
		LocalDate ngayKetThuc = bpc.getNgayKetThuc();
		Date dateNgayBatDau =  Date.valueOf(ngayBatDau);
		Date dateNgayKetThuc = Date.valueOf(ngayKetThuc);
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setDate(1, dateNgayKetThuc);
			statement.setString(3, bpc.getNhanVien().getMaNV());
			statement.setString(4, bpc.getCaLam().getMaCL());
			statement.setDate(5, dateNgayBatDau);
			statement.setString(2, bpc.getKieuPhanCa());
			
			int result = statement.executeUpdate();
			statement.close();
			
			return result > 0? true:false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean xoaBangPhanCa(BangPhanCa bangPhanCa) {
		String sql = "DELETE FROM BangPhanCa WHERE maCL = ? AND kieuPhanCa = ? AND ngayBatDau = ? AND ngayKetThuc = ? AND maNV = ?";
		Connection con = ConnectDB.getConnection();
		try {
			Date ngayBatDau = Date.valueOf(bangPhanCa.getNgayBatDau());
			Date ngayKetThuc = Date.valueOf(bangPhanCa.getNgayKetThuc());
			
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, bangPhanCa.getCaLam().getMaCL());
			statement.setString(2, bangPhanCa.getKieuPhanCa());
			statement.setDate(3, ngayBatDau);
			statement.setDate(4, ngayKetThuc);
			statement.setString(5, bangPhanCa.getNhanVien().getMaNV());
			
			int result = statement.executeUpdate();
			
			return result > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public List<BangPhanCa> timKiemPhanCa(BangPhanCa bpc){
		String sql = "SELECT * FROM BangPhanCa JOIN CaLam ON BangPhanCa.maCL = CaLam.maCL JOIN NhanVien ON NhanVien.maNV = BangPhanCa.maNV WHERE ((BangPhanCa.ngayBatDau >= ? AND BangPhanCa.ngayBatDau <= ?  ) OR (BangPhanCa.ngayKetThuc >= ?  AND BangPhanCa.ngayKetThuc <= ?  ) OR (BangPhanCa.ngayBatDau <= ? AND BangPhanCa.ngayKetThuc >= ?)) ";
		List<BangPhanCa> list = new ArrayList<BangPhanCa>();
		
		if(bpc.getNhanVien()!= null) {
			if( !bpc.getNhanVien().getMaNV().equals("")) {
				sql +=" AND BangPhanCa.maNV = ? ";	
			}
			if(!bpc.getNhanVien().getSoDienThoai().equals("")) {
				sql += " AND NhanVien.soDienThoai = ? ";
			}
			if( !bpc.getNhanVien().getChucVu().equals("")) {
				sql += " AND NhanVien.chucVu = ? ";
			}
			if(!bpc.getNhanVien().getTen().trim().equals("")) {
				sql += " AND NhanVien.tenNhanVien LIKE  ?";
			}
		}
		sql +=" ORDER BY BangPhanCa.ngayBatDau ASC";
		
		System.out.println(sql);
		Connection con = ConnectDB.getConnection();
		try {
			Date ngayBatDauTimKiem = Date.valueOf(bpc.getNgayBatDau());
			Date ngayKetThucTimKiem = Date.valueOf(bpc.getNgayKetThuc());
			
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setDate(1, ngayBatDauTimKiem);
			statement.setDate(2, ngayKetThucTimKiem);
			statement.setDate(3, ngayBatDauTimKiem);
			statement.setDate(4, ngayKetThucTimKiem);
			statement.setDate(5, ngayBatDauTimKiem);
			statement.setDate(6, ngayKetThucTimKiem);
			if(bpc.getNhanVien()!= null) {
				int i = 7;
				if( !bpc.getNhanVien().getMaNV().equals("")) {
					statement.setString(i,bpc.getNhanVien().getMaNV() );
					i++;
				}
				if(!bpc.getNhanVien().getSoDienThoai().equals("")) {
					bpc.getNhanVien().getSoDienThoai();
					i++;
				}
				if( !bpc.getNhanVien().getChucVu().equals("")) {
					statement.setString(i,bpc.getNhanVien().getChucVu() );
					i++;
				}
				if(!bpc.getNhanVien().getTen().trim().equals("")) {
					statement.setString(i,"%"+bpc.getNhanVien().getTen()+"%");
				}
			}
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				Date ngayBatDau = result.getDate("ngayBatDau");
				Date ngayKetThuc = result.getDate("ngayKetThuc");
				LocalDate localDateBatDau = ngayBatDau.toLocalDate();
				LocalDate localDateKetThuc = ngayKetThuc!= null? ngayKetThuc.toLocalDate():null;
				
				
				CaLam cl = new CaLam(result.getString("maCL"),result.getTime("gioBatDau"),result.getTime("gioKetThuc") );
				NhanVien nv = new NhanVien(result.getString("maNV"),result.getString("ten"),result.getInt("gioiTinh") == 1 ? utils.Contains.NAM:utils.Contains.NU,result.getString("chucVu"),result.getString("soDienThoai"),result.getString("diaChi") );
				BangPhanCa bangPhanCa = new BangPhanCa(nv, cl,localDateBatDau,localDateKetThuc,  result.getString("kieuPhanCa"));
				list.add(bangPhanCa);
				
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
}
