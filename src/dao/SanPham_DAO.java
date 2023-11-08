package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.NhaCungCap;
import entity.SanPham;

public class SanPham_DAO {
	public List<String> getDanhSachDanhMuc(){
		String sql = "SELECT * FROM DanhMuc";
		List<String> list = new ArrayList<String>();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement statement  = con.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				String itemDanhMuc = resultSet.getString("tenDM");
				list.add(itemDanhMuc);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public List<String> getDanhSachMauSac(){
		String sql = "SELECT * FROM MauSac";
		List<String> list = new ArrayList<String>();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement statement  = con.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				String itemDauSac = resultSet.getString("tenMS");
				list.add(itemDauSac);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public List<String> getDanhSachKichCo(){
		String sql = "SELECT * FROM KichCo";
		List<String> list = new ArrayList<String>();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement statement  = con.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				String itemKichCo = resultSet.getString("tenKC");
				list.add(itemKichCo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public List<String> getDanhSachChatLieu(){
		String sql = "SELECT * FROM ChatLieu";
		List<String> list = new ArrayList<String>();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement statement  = con.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				String itemChatLieu = resultSet.getString("tenCL");
				list.add(itemChatLieu);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public String getMaDanhMuc(String tenDM) {
		String sql = "SELECT maDM FROM DanhMuc WHERE tenDM = ?";
		Connection con = ConnectDB.getConnection();
		String maDM = null;
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, tenDM);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				maDM = resultSet.getString("maDM");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maDM;
		
	}
	public String getMaMauSac(String tenMS) {
		String sql = "SELECT maMS FROM MauSac WHERE tenMS = ?";
		Connection con = ConnectDB.getConnection();
		String maMS = null;
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, tenMS);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				maMS = resultSet.getString("maMS");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maMS;
		
	}
	public String getMaChatLieu(String tenCL) {
		String sql = "SELECT maCL FROM ChatLieu WHERE tenCL = ?";
		Connection con = ConnectDB.getConnection();
		String maCL = null;
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, tenCL);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				maCL = resultSet.getString("maCL");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maCL;
		
	}
	public String getMaKichCo(String tenKC) {
		String sql = "SELECT maKC FROM KichCo WHERE tenKC = ?";
		Connection con = ConnectDB.getConnection();
		String maKC = null;
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maKC);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				maKC = resultSet.getString("maKC");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maKC;
		
	}
	
	public List<SanPham> getDanhSachSanPham(){
		List<SanPham> list = new ArrayList<SanPham>();
		String sql = "SELECT * FROM SanPham "
				+ " LEFT JOIN MauSac On SanPham.maMS = MauSac.maMS "
				+ " LEFT JOIN DanhMuc ON SanPham.maDM = DanhMuc.maDM "
				+ " LEFT JOIN KichCo ON SanPham.maKC = KichCo.maKC "
				+ " LEFT JOIN ChatLieu ON sanPham.maCL = ChatLieu.maCL "
				+ " LEFT JOIN NhaCungCap ON SanPham.maNCC = NhaCungCap.maNCC";
		
		try {
			Connection con = ConnectDB.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				String mauSac = result.getString("tenMS");
				String chatLieu = result.getString("tenCL");
				String kichCo = result.getString("tenKC");
				String danhMuc = result.getString("tenDM");
				String maSP = result.getString("maSP");
				String tenSP = result.getString("tenSP");
				float giaNhap = result.getFloat("giaNhap");
				float giaBan = result.getFloat("giaBan");
				int soLuongTon = result.getInt("soLuongTon");
				int soLuongBan = result.getInt("SoLuongBan");
				String urlAvt = result.getString("urlAvt");
				
				String maNCC = result.getString("maNCC");
				String tenNCC = result.getString("tenNCC");
				String DC = result.getString("diaChi");
				String email = result.getString("email");
				String sdt = result.getString("soDienThoai");
				

				NhaCungCap ncc = new NhaCungCap(maNCC, tenNCC, DC, email, sdt);
				
				SanPham sp = new SanPham(maSP, tenSP, giaNhap, giaBan, soLuongTon, soLuongBan, urlAvt, danhMuc, mauSac, chatLieu, kichCo, ncc);
				list.add(sp);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public boolean capNhatSanPham(SanPham sanPham){
		String sql = "UPDATE SanPham "
				+ " SET"
				+ " maNCC = ?,"
				+ " tenSP = ?,"
				+ " giaBan = ?,"
				+ " giaNhap = ?,"
				+ " urlAvt = ? ,"
				+ " soLuongTon = ?,"
				+ " soLuongBan = ?,"
				+ " maMS = (SELECT TOP 1 maMS FROM MauSac WHERE tenMS = ?),"
				+ " maDM = (SELECT TOP 1 maDM FROM DanhMuc WHERE tenDM = ?),"
				+ " maKC = (SELECT TOP 1 maKC FROM KichCo WHERE tenKC = ? ),"
				+ " maCL = (SELECT TOP 1 maCL FROM ChatLieu WHERE tenCL = ?)"
				+ " WHERE "
				+ " maSP = ?";
		
		String tenSP = sanPham.getTenSP();
		String mauSac = sanPham.getMauSac();
		String chatLieu = sanPham.getChatLieu();
		String kichCo = sanPham.getKichCo();
		String danhMuc = sanPham.getDanhMuc();
		float giaNhap = sanPham.getGiaNhap();
		float giaBan = sanPham.getGiaBan();
		int soLuongTon = sanPham.getSoLuongTon();
		int soLuongBan = sanPham.getSoLuongBan();
		String urlAvt = sanPham.getUrlAvt();
		String maNCC = sanPham.getNhaCungCap().getMaNCC();
		Connection con = ConnectDB.getConnection();
		
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maNCC);
			statement.setString(2, tenSP);
			statement.setFloat(3, giaBan);
			statement.setFloat(4, giaNhap);
			statement.setString(5, urlAvt);
			statement.setInt(6, soLuongTon);
			statement.setInt(7, soLuongBan);
			statement.setString(8, mauSac);
			statement.setString(9, danhMuc);
			statement.setString(10, kichCo);
			statement.setString(11, chatLieu);
			statement.setString(12, sanPham.getMaSP());
			
			int result = statement.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean themSanPham(SanPham sanPham) {
		String sql = "INSERT INTO SanPham (maNCC, tenSP, giaBan, giaNhap, urlAvt, soLuongTon, soLuongBan, maMS, maDM, maKC, maCL) "
	            + "VALUES (?, ?, ?, ?, ?, ?, ?, "
	            + "(SELECT TOP 1 maMS FROM MauSac WHERE tenMS = ?), "
	            + "(SELECT TOP 1 maDM FROM DanhMuc WHERE tenDM = ?), "
	            + "(SELECT TOP 1 maKC FROM KichCo WHERE tenKC = ?), "
	            + "(SELECT TOP 1 maCL FROM ChatLieu WHERE tenCL = ?))";
		
		String tenSP = sanPham.getTenSP();
		String mauSac = sanPham.getMauSac();
		String chatLieu = sanPham.getChatLieu();
		String kichCo = sanPham.getKichCo();
		String danhMuc = sanPham.getDanhMuc();
		float giaNhap = sanPham.getGiaNhap();
		float giaBan = sanPham.getGiaBan();
		int soLuongTon = sanPham.getSoLuongTon();
		int soLuongBan = sanPham.getSoLuongBan();
		String urlAvt = sanPham.getUrlAvt();
		String maNCC = sanPham.getNhaCungCap().getMaNCC();
		Connection con = ConnectDB.getConnection();
		
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maNCC);
			statement.setString(2, tenSP);
			statement.setFloat(3, giaBan);
			statement.setFloat(4, giaNhap);
			statement.setString(5, urlAvt);
			statement.setInt(6, soLuongTon);
			statement.setInt(7, soLuongBan);
			statement.setString(8, mauSac);
			statement.setString(9, danhMuc);
			statement.setString(10, kichCo);
			statement.setString(11, chatLieu);
			
			int result = statement.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	public boolean capNhatSoLuongSanPham(String maSP, int soLuongBan, int soLuongTon) {
		
		String sql = "UPDATE SanPham SET soLuongBan = ? , soLuongTon = ? WHERE maSP = ?";
		
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, soLuongBan);
			statement.setInt(2, soLuongTon);
			statement.setString(3, maSP);
			
			int result = statement.executeUpdate();
			
			return result > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}
	public String getAnhSanPham(String maSP) {
	    String pathImage = null;
	    try {
	        Connection con = ConnectDB.getInstance().getConnection();

	        String sql = "SELECT SanPham.urlAvt FROM SanPham WHERE maSP = ?";
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setString(1, maSP);
	        ResultSet rs = statement.executeQuery();

	        if (rs.next()) {
	            pathImage = rs.getString("urlAvt");
	        }
	        statement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return pathImage;
	}
	public String layDuLieuCotSanPham() {
		String ketQua=null;
		try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT TOP 1 maSP FROM SanPham ORDER BY maSP DESC";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                ketQua = rs.getString("maSP");
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
	}
	
	public String layDuLieuCotChatLieu() {
		String ketQua = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT TOP 1 maCL FROM ChatLieu ORDER BY maCL DESC";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				ketQua = rs.getString("maCL");
			}
			statement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}
	public boolean themChatLieu(String tenCL) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean capNhatChatLieu(String tenCLCu, String tenCLMoi) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean themDanhMuc(String tenDM) {
		// TODO Auto-generated method stub
		return false;
	}
	public String layDuLieuCotDanhMuc() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean capNhatDanhMuc(String tenDMCu, String tenDMMoi) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean themKichCo(String tenKC) {
		// TODO Auto-generated method stub
		return false;
	}
	public String layDuLieuCotKichCo() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean capNhatKichCo(String tenKCCu, String tenKCMoi) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean themMauSac(String tenMS) {
		// TODO Auto-generated method stub
		return false;
	}
	public String layDuLieuCotMauSac() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean capNhatMauSac(String tenMSCu, String tenMSMoi) {
		// TODO Auto-generated method stub
		return false;
	}
	public List<SanPham> getlistSanPhamTheoDK(String maSP, String tenSP, String giaNhap, String giaBan, String soLuongTon, String soLuongBan, String tenDM, String tenMS, String tenCL, String tenKC, String tenNCC){
		return null;
	}
	
	public SanPham timKiemSanPham(String maSP) {
		String sql ="SELECT * FROM SanPham "
				+ " LEFT JOIN MauSac On SanPham.maMS = MauSac.maMS "
				+ " LEFT JOIN DanhMuc ON SanPham.maDM = DanhMuc.maDM "
				+ " LEFT JOIN KichCo ON SanPham.maKC = KichCo.maKC "
				+ " LEFT JOIN ChatLieu ON sanPham.maCL = ChatLieu.maCL "
				+ " LEFT JOIN NhaCungCap ON SanPham.maNCC = NhaCungCap.maNCC "
				+ " WHERE maSP = ? ";
		SanPham sp = null;
		try {
			Connection con = ConnectDB.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maSP);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				String mauSac = result.getString("tenMS");
				String chatLieu = result.getString("tenCL");
				String kichCo = result.getString("tenKC");
				String danhMuc = result.getString("tenDM");
				String maSPReturn = result.getString("maSP");
				String tenSP = result.getString("tenSP");
				float giaNhap = result.getFloat("giaNhap");
				float giaBan = result.getFloat("giaBan");
				int soLuongTon = result.getInt("soLuongTon");
				int soLuongBan = result.getInt("SoLuongBan");
				String urlAvt = result.getString("urlAvt");
				
				String maNCC = result.getString("maNCC");
				String tenNCC = result.getString("tenNCC");
				String DC = result.getString("diaChi");
				String email = result.getString("email");
				String sdt = result.getString("soDienThoai");
				

				NhaCungCap ncc = new NhaCungCap(maNCC, tenNCC, DC, email, sdt);
				
				sp = new SanPham(maSPReturn, tenSP, giaNhap, giaBan, soLuongTon, soLuongBan, urlAvt, danhMuc, mauSac, chatLieu, kichCo, ncc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sp;
		
	}

}
