package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import javax.naming.ldap.LdapName;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.FontUIResource;

import gui_KhachHang.DonHang;
import gui_NhanVien.DatHang;
import gui_NhanVien.PhanCongCaLam;
import gui_TaiKhoan.CapNhatTaiKhoan;

public class FrameTrangChu extends JFrame implements ActionListener{
	//icon
	private final ImageIcon ICON_DOWN = new ImageIcon(getClass().getResource("/Images/Icons/down-arrow.png"));
	private final ImageIcon ICON_UPPER = new ImageIcon(getClass().getResource("/Images/Icons/upper-arrow.png"));
	private final ImageIcon ICON_BACKGROUND = new ImageIcon(getClass().getResource("/Images/Icons/back3.jpg"));
	//menu cha
	private CustomButton btnThongKe;
	private CustomButton btnCaLam;
	private CustomButton btnHoaDon;
	private CustomButton btnKhachHang;
	private CustomButton btnNhanVien;
	private CustomButton btnNhaCungCap;
	private CustomButton btnSanPham;
	private CustomButton btnTaiKhoan;
	
	//item
	//btnThongKe
	private CustomButton btnThongKeDoanhThu;
	private CustomButton btnThongKeChiTieuKhachHang;
	//btnCaLam
	private CustomButton btnCapNhatCaLam;
	//btnHoaDon
	private CustomButton btnTimKiemHoaDon;
	//btnKhachHang	
	private CustomButton btnDonHang;
	private CustomButton btnCapNhatKhachHang;
	private CustomButton btnTimKiemKhachHang;

	//btnNhanVien
	private CustomButton btnDatHang;
	private CustomButton btnCapNhatNhanVien;
	private CustomButton btnTimKiemNhanVien;
	private CustomButton btnTheoDoiLichLam;
	private CustomButton btnPhanCongCaLam;
	//btnNhaCungCap
	private CustomButton btnCapNhatNCC;
	private CustomButton btnTimKiemNCC;
	//btnSanPham
	private CustomButton btnCapNhatSanPham;
	private CustomButton btnTimKiemSanPham;
	private CustomButton btnThongKeSanPham;

	//btnTaiKhoan
	private CustomButton btnCapNhatTaiKhoan;
	//btn logout
	private JButton btnLogout;
	
	private JPanel pnlContent;
	
	private JButton btnChatLieu;
	private JButton btnMauSac;
	private JButton btnDanhMuc;
	private JButton btnKichCo;
	
	public FrameTrangChu() {
		 // Lấy thông tin về màn hình
		AnhXa();
		setTitle("Trang Chủ");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		createGUI();
		  // Lấy kích thước của màn hình
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
	}
	
	private void createGUI() {
		// TODO Auto-generated method stub
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		//lbl
		JLabel lblMenu = new JLabel("Menu");
		JLabel lblQuanLyBanQuanAo = new JLabel("Quản Lý Bán Quần Áo");
		JLabel lblTenLogin = new JLabel(utils.Contains.getRole().endsWith("KH")?"Khách Hàng":"Họ và Tên: "+utils.Contains.getTenNV());
		JLabel lblChucVu = new JLabel(utils.Contains.getRole().equals("KH")?"Chức Vụ  : Khách Hàng":utils.Contains.getRole().equals("NV")?"Chức Vụ  : Nhân Viên Báng Hàng":"Chức Vụ  : Nhân Viên Quản Lý");
		//setLaybel
		lblMenu.setFont(new Font("",Font.BOLD,20));
		lblMenu.setForeground(Color.white);
		lblMenu.setBackground(Color.decode("#FF008A"));	
		lblMenu.setBounds(81, 11, 56, 24);
		
		lblQuanLyBanQuanAo.setFont(new Font("Times New Roman",Font.PLAIN,16));
		lblQuanLyBanQuanAo.setForeground(Color.white);
		lblQuanLyBanQuanAo.setBounds(35, 42, 164, 20);
		
		lblTenLogin.setFont(new Font("",Font.PLAIN,12));
		lblChucVu.setFont(new Font("",Font.PLAIN,12));
		
		lblTenLogin.setBounds(10, 5,217,20);
		lblChucVu.setBounds(10, 25,217,30);
		
		//pnl
		JPanel pnlTilteMenu = new JPanel();
		JPanel pnlWest = new JPanel();
		JPanel pnlMenu = new JPanel();
		JPanel pnlLogout = new JPanel();
		
		
		//set pnl
		pnlWest.setLayout(null);
		pnlWest.setPreferredSize(new Dimension(217,630));
		pnlWest.setBackground(Color.black);
		pnlWest.setBorder(BorderFactory.createMatteBorder(0,0,0,1,Color.black));
		
		pnlMenu.setLayout(new BoxLayout(pnlMenu, BoxLayout.Y_AXIS));
		pnlMenu.setBounds(0,0,216,700);
		
		pnlLogout.setBounds(0,700,216,95);
		pnlLogout.setLayout(null);
		
		pnlContent.setLayout(null);
		pnlContent.setBackground(Color.green);
		
		Image scaled = scaleImage(ICON_BACKGROUND.getImage(), utils.Contains.WIDTH_PANEL_CONTENT, utils.Contains.HEIGHT_PANEL_CONTENT);
		ImageIcon icon = new ImageIcon(scaled);
		JLabel lblImage = new JLabel(icon);
		lblImage.setBounds(0, 0, utils.Contains.WIDTH_PANEL_CONTENT, utils.Contains.HEIGHT_PANEL_CONTENT);
		pnlContent.add(lblImage);
		
		pnlTilteMenu.setLayout(null);
		pnlTilteMenu.setOpaque(true);
		pnlTilteMenu.setBackground(Color.decode("#FF008A"));
		pnlTilteMenu.setBorder(BorderFactory.createMatteBorder(0, 0,1,0,Color.black));
		pnlTilteMenu.setMaximumSize(new Dimension(434,84));
		
		//set btn
		btnLogout.setBounds(110,55,92,26);
		btnLogout.setBackground(Color.decode("#FF008A"));
		btnLogout.setFont(new Font("", Font.BOLD,12));
		btnLogout.setForeground(Color.white);
		btnLogout.setBorder(null);
		
		//add pnl
		pnlTilteMenu.add(lblMenu);
		pnlTilteMenu.add(lblQuanLyBanQuanAo);
		
		pnlWest.add(pnlMenu);
		pnlWest.add(pnlLogout);
		
		pnlLogout.add(lblTenLogin);
		pnlLogout.add(lblChucVu);
		pnlLogout.setBorder(BorderFactory.createMatteBorder(1,0,0,0,Color.decode("#616161")));
		pnlLogout.add(btnLogout);
		
		add(pnlWest,BorderLayout.WEST);
		add(pnlContent,BorderLayout.CENTER);
		
		
		
		
		
		if(utils.Contains.getRole().equals(utils.Contains.NHAN_VIEN_QUAN_LY)) {
			pnlMenu.add(pnlTilteMenu);
		
			pnlMenu.add(btnNhanVien);
			pnlMenu.add(btnDatHang);
			pnlMenu.add(btnCapNhatNhanVien);
			pnlMenu.add(btnTimKiemNhanVien);
			pnlMenu.add(btnTheoDoiLichLam);
			pnlMenu.add(btnPhanCongCaLam);
			
			pnlMenu.add(btnKhachHang);
			pnlMenu.add(btnDonHang);
			pnlMenu.add(btnCapNhatKhachHang);
			pnlMenu.add(btnTimKiemKhachHang);
			
			pnlMenu.add(btnCaLam);
			pnlMenu.add(btnCapNhatCaLam);
			
			pnlMenu.add(btnHoaDon);
			pnlMenu.add(btnTimKiemHoaDon);
			
			pnlMenu.add(btnNhaCungCap);
			pnlMenu.add(btnCapNhatNCC);
			pnlMenu.add(btnTimKiemNCC);
			
			pnlMenu.add(btnSanPham);
			pnlMenu.add(btnCapNhatSanPham);
			pnlMenu.add(btnTimKiemSanPham);
			pnlMenu.add(btnMauSac);
			pnlMenu.add(btnKichCo);
			pnlMenu.add(btnChatLieu);
			pnlMenu.add(btnDanhMuc);
			
			pnlMenu.add(btnThongKe);
			pnlMenu.add(btnThongKeChiTieuKhachHang);
			pnlMenu.add(btnThongKeDoanhThu);
			pnlMenu.add(btnThongKeSanPham);
			
			pnlMenu.add(btnTaiKhoan);
			pnlMenu.add(btnCapNhatTaiKhoan);
		}else if(utils.Contains.getRole().equals(utils.Contains.NHAN_VIEN_BAN_HANG)){
			pnlMenu.add(lblMenu);
			
		}else {
			pnlMenu.add(btnSanPham);
			pnlMenu.add(btnTimKiemSanPham);
		}
		
		
		 btnThongKe.addActionListener(this);
		 btnCaLam.addActionListener(this);
		 btnKhachHang.addActionListener(this);
		 btnNhanVien.addActionListener(this);
		 btnNhaCungCap.addActionListener(this);
		 btnSanPham.addActionListener(this);
		 btnTaiKhoan.addActionListener(this);
		 btnHoaDon.addActionListener(this);
		 
	
		 
		//add event
		btnCapNhatNhanVien.addActionListener(this);
		btnTimKiemNhanVien.addActionListener(this);
		btnTheoDoiLichLam.addActionListener(this);
		btnDatHang.addActionListener(this);
		btnPhanCongCaLam.addActionListener(this);
	
		//khach Hang
		btnDonHang.addActionListener(AddSwithPanel(new gui_KhachHang.DonHang()));
		btnCapNhatKhachHang.addActionListener(AddSwithPanel(new gui_KhachHang.CapNhatKhachHang()));
		btnTimKiemKhachHang.addActionListener(AddSwithPanel(new gui_KhachHang.TimKiemKhachHang()));
		
		//Ca Lam
		btnCapNhatCaLam.addActionListener(AddSwithPanel(new gui_CaLam.CapNhatCaLam()));
		
		//Nha cung cap
		btnCapNhatNCC.addActionListener(AddSwithPanel(new gui_NhaCungCap.CapNhatNhaCungCap()));
		btnTimKiemNCC.addActionListener(AddSwithPanel(new gui_NhaCungCap.TimKiemNhaCungCap()));
		
		//san pham
		btnCapNhatSanPham.addActionListener(AddSwithPanel(new gui_SanPham.CapNhatSanPham()));
		btnTimKiemSanPham.addActionListener(AddSwithPanel(new gui_SanPham.TimKiemSanPham()));
		btnMauSac.addActionListener(AddSwithPanel(new gui_SanPham.MauSac()));
		btnKichCo.addActionListener(AddSwithPanel(new gui_SanPham.KichCo()));
		btnDanhMuc.addActionListener(AddSwithPanel(new gui_SanPham.DanhMuc()));
		btnChatLieu.addActionListener(AddSwithPanel(new gui_SanPham.ChatLieu()));
		//thong ke
		btnThongKeChiTieuKhachHang.addActionListener(AddSwithPanel(new gui_ThongKe.ThongKeChiTieuKhachHang()));
		btnThongKeDoanhThu.addActionListener(AddSwithPanel(new gui_ThongKe.ThongKeDoanhThu()));
		btnThongKeSanPham.addActionListener(AddSwithPanel(new gui_ThongKe.ThongKeSanPham()));

		//tai khoan
		btnCapNhatTaiKhoan.addActionListener(this);
		
		//hoa don
		btnTimKiemHoaDon.addActionListener(AddSwithPanel(new gui_HoaDon.TimKiemHoaDon()));
	}

	private void AnhXa() {
		String cha = "CHA";
		String con = "CON";
		Dimension dimensionCha = new Dimension(217,42);
		Dimension dimensionCon = new Dimension(217,28);
		
		pnlContent = new JPanel();
		btnLogout = new JButton("Đăng Xuất");
		//btn cha
		btnHoaDon = new CustomButton("  Hóa Đơn", cha, dimensionCha,ICON_DOWN);
		btnThongKe = new CustomButton("  Thống Kê",cha,dimensionCha,ICON_DOWN);
		btnCaLam = new CustomButton("  Ca Làm",cha,dimensionCha,ICON_DOWN);
		btnKhachHang = new CustomButton("  Khách Hàng",cha,dimensionCha,ICON_DOWN);
		btnNhanVien = new CustomButton("  Nhân Viên",cha,dimensionCha,ICON_DOWN);
		btnNhaCungCap = new CustomButton("  Nhà Cung Cấp",cha,dimensionCha,ICON_DOWN);
		btnSanPham = new CustomButton("  Sản Phẩm",cha,dimensionCha,ICON_DOWN);
		btnTaiKhoan = new CustomButton("  Tài Khoản",cha,dimensionCha,ICON_DOWN);
		
		//item
		//btnThongKe
		btnThongKeDoanhThu = new CustomButton("Thống Kê Doanh Thu",con,dimensionCon);
		btnThongKeChiTieuKhachHang = new CustomButton("Thống Kê Chi Tiêu Khách Hàng",con,dimensionCon);
		//btnCaLam
		btnCapNhatCaLam = new CustomButton("Cập Nhật Ca Làm",con,dimensionCon);
		btnTimKiemHoaDon = new CustomButton("Tìm Kiếm Hóa Đơn",con,dimensionCon);
		//btnKhachHang	
		btnDonHang = new CustomButton("Đơn Hàng",con,dimensionCon);
		btnCapNhatKhachHang = new CustomButton("Cập Nhât Khách Hàng",con,dimensionCon);
		btnTimKiemKhachHang = new CustomButton("Tìm Kiếm Khách Hàng",con,dimensionCon);
		//btnNhanVien
		btnDatHang = new CustomButton("Đặt Hàng",con,dimensionCon);
		btnCapNhatNhanVien = new CustomButton("Cập Nhật Nhân Viên",con,dimensionCon);
		btnTimKiemNhanVien = new CustomButton("Tìm Kiếm Nhân Viên",con,dimensionCon);
		btnTheoDoiLichLam = new CustomButton("Theo Dõi Lịch Làm",con,dimensionCon);
		btnPhanCongCaLam = new CustomButton("Phân Công Ca Làm",con,dimensionCon);
		//btnNhaCungCap
		btnCapNhatNCC = new CustomButton("Cập Nhật Nhà Cung Cấp",con,dimensionCon);
		btnTimKiemNCC = new CustomButton("Tìm Kiếm Nhà Cung Cấp",con,dimensionCon);
		//btnSanPham
		btnCapNhatSanPham = new CustomButton("Cập Nhật Sản Phẩm",con,dimensionCon);
		btnTimKiemSanPham = new CustomButton("Tìm Kiếm Sản Phẩm",con,dimensionCon);
		btnThongKeSanPham = new CustomButton("Thống Kê Sản Phẩm",con,dimensionCon);
		 //btn taiKhoan
		btnCapNhatTaiKhoan = new CustomButton("Cập nhật tài khoản",con,dimensionCon);
		
		btnChatLieu = new CustomButton("Chất Liệu",con,dimensionCon);
		btnMauSac = new CustomButton("Màu Sắc",con,dimensionCon);
		btnDanhMuc = new CustomButton("Danh Mục",con,dimensionCon);
		btnKichCo = new CustomButton("Kích Cỡ",con,dimensionCon);
	}
	private void closeItemMenu(JButton btn) {
		if(!btn.equals(btnThongKe)) {
			btnThongKe.setShowIcon(true);
			btnThongKe.setLBLIcon(new JLabel(ICON_DOWN));
			btnThongKeDoanhThu.setVisible(false);
			btnThongKeChiTieuKhachHang.setVisible(false);
			btnThongKeSanPham.setVisible(false);
		}
		if(!btn.equals(btnHoaDon)) {
			btnHoaDon.setShowIcon(true);
			btnHoaDon.setLBLIcon(new JLabel(ICON_DOWN));
			btnTimKiemHoaDon.setVisible(false);
		}
		if(!btn.equals(btnCaLam)) {
			btnCaLam.setShowIcon(true);
			btnCaLam.setLBLIcon(new JLabel(ICON_DOWN));
			btnCapNhatCaLam.setVisible(false);
		}
		if(!btn.equals(btnKhachHang)) {
			btnKhachHang.setShowIcon(true);
			btnKhachHang.setLBLIcon(new JLabel(ICON_DOWN));
			btnDonHang.setVisible(false);
			btnCapNhatKhachHang.setVisible(false);
			btnTimKiemKhachHang.setVisible(false);
		}
		if(!btn.equals(btnNhaCungCap)) {
			btnNhaCungCap.setShowIcon(true);
			btnNhaCungCap.setLBLIcon(new JLabel(ICON_DOWN));
			btnCapNhatNCC.setVisible(false);
			btnTimKiemNCC.setVisible(false);
		}
		if(!btn.equals(btnNhanVien)) {
			btnNhanVien.setShowIcon(true);
			btnNhanVien.setLBLIcon(new JLabel(ICON_DOWN));
			btnTimKiemNhanVien.setVisible(false);
			btnTheoDoiLichLam.setVisible(false);
			btnDatHang.setVisible(false);
			if(utils.Contains.getRole().equals("QL")) {
				btnPhanCongCaLam.setVisible(false);
				btnCapNhatNhanVien.setVisible(false);
			}
		}
		if(!btn.equals(btnSanPham)) {
			btnSanPham.setShowIcon(true);
			btnSanPham.setLBLIcon(new JLabel(ICON_DOWN));
			btnTimKiemSanPham.setVisible(false);
			btnThongKeSanPham.setVisible(false);
			if(utils.Contains.getRole().equals("QL")) {
				btnCapNhatSanPham.setVisible(false);
				btnMauSac.setVisible(false);
				btnKichCo.setVisible(false);
				btnChatLieu.setVisible(false);
				btnDanhMuc.setVisible(false);
			}
		}
		if(!btn.equals(btnTaiKhoan)) {
			btnTaiKhoan.setShowIcon(true);
			btnTaiKhoan.setLBLIcon(new JLabel(ICON_DOWN));
			btnCapNhatTaiKhoan.setVisible(false);
		}
		if(!btn.equals(btnHoaDon)) {
			btnHoaDon.setShowIcon(true);
			btnHoaDon.setLBLIcon(new JLabel(ICON_DOWN));
			btnTimKiemHoaDon.setVisible(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		
		if(o.equals(btnThongKe)) {
			if(btnThongKe.getShowIcon()) {
				closeItemMenu(btnThongKe);
				btnThongKeDoanhThu.setVisible(true);
				btnThongKeChiTieuKhachHang.setVisible(true);
				btnThongKeSanPham.setVisible(true);
				btnThongKe.setLBLIcon(new JLabel(ICON_UPPER));
			}else {
				btnThongKeDoanhThu.setVisible(false);
				btnThongKeChiTieuKhachHang.setVisible(false);
				btnThongKeSanPham.setVisible(false);
				btnThongKe.setLBLIcon(new JLabel(ICON_DOWN));
			}
			btnThongKe.setShowIcon(!btnThongKe.getShowIcon());
			
		}else if(o.equals(btnCaLam)) {
			if(btnCaLam.getShowIcon()) {
				closeItemMenu(btnCaLam);
				btnCapNhatCaLam.setVisible(true);
				btnCaLam.setLBLIcon(new JLabel(ICON_UPPER));
			}else {
				btnCapNhatCaLam.setVisible(false);
				btnCaLam.setLBLIcon(new JLabel(ICON_DOWN));
			}
			btnCaLam.setShowIcon(!btnCaLam.getShowIcon());
			
		}else if(o.equals(btnKhachHang)) {
			if(btnKhachHang.getShowIcon()) {
				closeItemMenu(btnKhachHang);
				btnDonHang.setVisible(true);
				btnCapNhatKhachHang.setVisible(true);
				btnTimKiemKhachHang.setVisible(true);
				btnKhachHang.setLBLIcon(new JLabel(ICON_UPPER));
			}else {
				btnDonHang.setVisible(false);
				btnCapNhatKhachHang.setVisible(false);
				btnTimKiemKhachHang.setVisible(false);
				btnKhachHang.setLBLIcon(new JLabel(ICON_DOWN));
			}
			btnKhachHang.setShowIcon(!btnKhachHang.getShowIcon());
			
		}else if(o.equals(btnNhanVien)) {
			if(btnNhanVien.getShowIcon()) {
				closeItemMenu(btnNhanVien);
				btnDatHang.setVisible(true);
				btnTimKiemNhanVien.setVisible(true);
				btnTheoDoiLichLam.setVisible(true);
				if(utils.Contains.getRole().equals("QL")) {
					btnPhanCongCaLam.setVisible(true);
					btnCapNhatNhanVien.setVisible(true);
				}
				btnNhanVien.setLBLIcon(new JLabel(ICON_UPPER));
			}else {
				btnDatHang.setVisible(false);
				btnTimKiemNhanVien.setVisible(false);
				btnTheoDoiLichLam.setVisible(false);
				if(utils.Contains.getRole().equals("QL")) {
					btnPhanCongCaLam.setVisible(false);
					btnCapNhatNhanVien.setVisible(false);
				}
				btnNhanVien.setLBLIcon(new JLabel(ICON_DOWN));
			}
			btnNhanVien.setShowIcon(!btnNhanVien.getShowIcon());
			
			
		}else if(o.equals(btnNhaCungCap)) {
			if(btnNhaCungCap.getShowIcon()) {
				closeItemMenu(btnNhaCungCap);
				btnCapNhatNCC.setVisible(true);
				btnTimKiemNCC.setVisible(true);
				btnNhaCungCap.setLBLIcon(new JLabel(ICON_UPPER));
			}else {
				btnCapNhatNCC.setVisible(false);
				btnTimKiemNCC.setVisible(false);
				btnNhaCungCap.setLBLIcon(new JLabel(ICON_DOWN));
			}
			btnNhaCungCap.setShowIcon(!btnNhaCungCap.getShowIcon());
			
		}else if(o.equals(btnSanPham)) {
			if(btnSanPham.getShowIcon()) {
				closeItemMenu(btnSanPham);
				btnTimKiemSanPham.setVisible(true);
				if(utils.Contains.getRole().equals("QL")) {
					btnCapNhatSanPham.setVisible(true);
					btnMauSac.setVisible(true);
					btnKichCo.setVisible(true);
					btnChatLieu.setVisible(true);
					btnDanhMuc.setVisible(true);
				}
				btnSanPham.setLBLIcon(new JLabel(ICON_UPPER));
			}else {
				btnTimKiemSanPham.setVisible(false);
				btnMauSac.setVisible(false);
				btnKichCo.setVisible(false);
				btnChatLieu.setVisible(false);
				btnDanhMuc.setVisible(false);
				if(utils.Contains.getRole().equals("QL")) {
					btnCapNhatSanPham.setVisible(false);
				}
				btnSanPham.setLBLIcon(new JLabel(ICON_DOWN));
			}
			btnSanPham.setShowIcon(!btnSanPham.getShowIcon());
			
		}else if(o.equals(btnTaiKhoan)) {
			if(btnTaiKhoan.getShowIcon()) {
				closeItemMenu(btnTaiKhoan);
				btnCapNhatTaiKhoan.setVisible(true);
				btnTaiKhoan.setLBLIcon(new JLabel(ICON_UPPER));
			}else {
				btnCapNhatTaiKhoan.setVisible(false);
				btnTaiKhoan.setLBLIcon(new JLabel(ICON_DOWN));
			}
			btnTaiKhoan.setShowIcon(!btnTaiKhoan.getShowIcon());
		}else if(o.equals(btnThongKe)) {
			if(btnThongKe.getShowIcon()) {
				closeItemMenu(btnThongKe);
				btnThongKeChiTieuKhachHang.setVisible(true);
				btnThongKeDoanhThu.setVisible(true);
				btnThongKeSanPham.setVisible(true);
				btnThongKe.setLBLIcon(new JLabel(ICON_UPPER));
			}else {
				btnThongKeChiTieuKhachHang.setVisible(false);
				btnThongKeDoanhThu.setVisible(false);
				btnThongKeSanPham.setVisible(false);
				btnThongKe.setLBLIcon(new JLabel(ICON_DOWN));
			}
			btnThongKe.setShowIcon(!btnThongKe.getShowIcon());
			
		}else if(o.equals(btnHoaDon)) {
			if(btnHoaDon.getShowIcon()) {
				closeItemMenu(btnHoaDon);
				btnTimKiemHoaDon.setVisible(true);
				btnHoaDon.setLBLIcon(new JLabel(ICON_UPPER));
			}else {
				btnTimKiemHoaDon.setVisible(false);
				btnHoaDon.setLBLIcon(new JLabel(ICON_DOWN));
			}
			btnHoaDon.setShowIcon(!btnHoaDon.getShowIcon());
			
		}else if(o.equals(btnCapNhatNhanVien)) {
			swithPanel(new gui_NhanVien.CapNhatNhanVien());
		}else if(o.equals(btnTimKiemNhanVien)) {
			swithPanel(new gui_NhanVien.TimKiemNhanVien());
		}else if(o.equals(btnDatHang)) {
			swithPanel(new gui_NhanVien.DatHang());
		}else if(o.equals(btnPhanCongCaLam)){
			swithPanel(new gui_NhanVien.PhanCongCaLam());
		}else if(o.equals(btnTheoDoiLichLam)) {
			swithPanel(new gui_NhanVien.TheoDoiLichLam());
		}else if(o.equals(btnCapNhatTaiKhoan)) {
			swithPanel(new gui_TaiKhoan.CapNhatTaiKhoan());
		}
		
	}
	
	public ActionListener AddSwithPanel(JPanel pnl) {
		ActionListener action = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				swithPanel(pnl);
			}
		};
		return action;
	}
	public void swithPanel(JPanel pnl) {
		pnlContent.removeAll();
		pnlContent.add(pnl);
		pnlContent.validate();
		pnlContent.repaint();
	}
	
	private Image scaleImage(Image image, int w, int h) {
	        Image scaled = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
	        return scaled;
	}


}

class CustomButton extends JButton{
	private JLabel lblIcon;
	private boolean showIcon =true;
	/*Sử dụng tạo btn cho menu, với 4 tham số đầu vào
	 * text => text trong btn
	 * type => type nhận giá trị Cha và Con được sử dụng để cấu hình kích thước của btn
	 * bit => nhận giá trị 0,1 => 0 là màu trắng, 1 là màu xám mờ. được sử dụng để đổi màu giữa 2 btn liền kề cho không cùng màu
	 */
	public CustomButton(String text,String type,Dimension de) {
		Color colorOdd = Color.decode("#E3E3E3");
		Color colorEven = Color.WHITE;
		Font fontCha = new Font("`",Font.BOLD,16);
		Font fontCon = new FontUIResource("",Font.PLAIN, 14);
		
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#A7A7A7")));
		setBorder(null);
		setMaximumSize(de);
		setHorizontalAlignment(JLabel.LEFT);
		
		if(type.equals("CHA")) {
			setFont(fontCha);
			setText(text);
			
		}else {
			setText("      "+text);
			setFont(fontCon);
			setVisible(false);
		}
		 MouseListener mouseListener = new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent e) {
	                // Xử lý sự kiện khi chuột lướt vào JButton
	            	setBackground(Color.decode("#FF58B2"));
					setForeground(Color.white);
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	                // Xử lý sự kiện khi chuột lướt ra khỏi JButton
	            	setBackground(Color.white);
					setForeground(Color.BLACK);
	            }
	        };
		
		addMouseListener(mouseListener);
	}

	public CustomButton(String text, String type, Dimension de, ImageIcon iconDown) {
		Color colorOdd = Color.decode("#E3E3E3");
		Color colorEven = Color.WHITE;
		Font fontCha = new Font("",Font.BOLD,16);
		Font fontCon = new FontUIResource("",Font.PLAIN, 14);
		
		setBorder(null);
		setMaximumSize(de);
		setHorizontalAlignment(JLabel.LEFT);
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#616161")));
		
		JLabel labelText = new JLabel(text);
		lblIcon = new JLabel(iconDown);
	
		lblIcon.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblIcon,BorderLayout.EAST);
		add(labelText,BorderLayout.WEST);
		
		if(type.equals("CHA")) {
			labelText.setFont(fontCha);
			labelText.setText(text);
			
			
		}else {
			labelText.setText("      "+text);
			labelText.setFont(fontCon);
			setVisible(false);
		}
		
		 MouseListener mouseListener = new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent e) {
	                // Xử lý sự kiện khi chuột lướt vào JButton
	            	setBackground(Color.decode("#FF58B2"));
					labelText.setForeground(Color.white);
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	                // Xử lý sự kiện khi chuột lướt ra khỏi JButton
	            	setBackground(Color.white);
					labelText.setForeground(Color.BLACK);
	            }
	        };
		
		addMouseListener(mouseListener);
		
	}
	

	public JLabel getLBLIcon() {
		return lblIcon;
	}

	public void setLBLIcon(JLabel lblIcon) {
		remove(this.lblIcon);
		this.lblIcon = lblIcon;
		this.lblIcon.setHorizontalAlignment(SwingConstants.RIGHT);
		add(this.lblIcon,BorderLayout.EAST);
	}

	public boolean getShowIcon() {
		return showIcon;
	}

	public void setShowIcon(boolean showIcon) {
		this.showIcon = showIcon;
	}
	
	
}