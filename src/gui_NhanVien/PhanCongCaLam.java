package gui_NhanVien;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.BangPhanCa_DAO;
import dao.CaLam_DAO;
import dao.NhanVien_DAO;
import entity.BangPhanCa;
import entity.CaLam;
import entity.NhanVien;


public class PhanCongCaLam extends JPanel implements ActionListener, MouseListener,PropertyChangeListener, FocusListener{
	private int WIDTH;
	private int HEIGHT;
	
	private JComboBox<String> cmbKieuPhanCa;
	private JComboBox<String> cmbCaLam;
	private JComboBox<String> cmbGioiTinh;
	private JComboBox<String> cmbChucVu;
	
	private DefaultComboBoxModel<String> modelCBOCaLam;
	private DefaultComboBoxModel<String> modelCBOKieuPhanCa;
	
	private JTextField txtTimKiemMaNhanVien;
	private JTextField txtTimKiemTenNhanVien;
	private JTextField txtTimKiemSoDienThoai;
	private JTextField txtTimKiemDiaChi;
	
	private JTextField txtPhanCaMaNhanVien;

	private JDateChooser dateChooserStart;
	private JDateChooser dateChooserEnd;
	
	private JButton btnXoa;
	private JButton btnPhanCa;
	private JButton btnCapNhat;
	private JButton btnTimKiem;
	
	private JTable tblPhanCaLam;
	private JTable tblTimKiemNhanVien;
	
	private DefaultTableModel modelTBLPhanCaLam;
	private DefaultTableModel modelTBLTimKiemNhanVien;

	
	private List<NhanVien> listNV;
	private List<CaLam> listCL;
	private List<BangPhanCa> listBPC;
	
	
	private NhanVien_DAO nhanVien_DAO;
	private CaLam_DAO caLam_DAO;
	private BangPhanCa_DAO bangPhanCa_DAO;
		
	private	JTextField txtdateStart;
	private JTextField txtdateEnd;
	
	
	/*
	 * Chưa hoàn thiện cập nhật và xóa phân ca 
	 * */
	public PhanCongCaLam() {
		this.WIDTH = utils.Contains.WIDTH_PANEL_CONTENT;
		this.HEIGHT = utils.Contains.HEIGHT_PANEL_CONTENT;
		setSize(utils.Contains.WIDTH_PANEL_CONTENT,utils.Contains.HEIGHT_PANEL_CONTENT);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.white);
		anhXa();
		createGUI();
		initial();
	}
	private void createGUI() {
		// TODO Auto-generated method stub
		//create Panel
		JPanel pnlPhanCa = new JPanel();
		JPanel pnlRow4 = new JPanel();
		//create JScrollPane
		JScrollPane scrTBLPhanCaLam = new JScrollPane(tblPhanCaLam);
		JScrollPane scrTBLTimKiemNhanVien = new JScrollPane(tblTimKiemNhanVien);
		
		//set Panel
		pnlRow4.setMaximumSize(new Dimension(1182, 319));
		pnlRow4.setLayout(null);
		pnlRow4.setBackground(Color.white);
		
		pnlPhanCa.setBounds(899, 7, 273, 302);;
		pnlPhanCa.setBorder(BorderFactory.createLineBorder(Color.decode("#9B9B9B")));
		pnlPhanCa.setBackground(Color.WHITE);
		pnlPhanCa.setLayout(new BoxLayout(pnlPhanCa,BoxLayout.Y_AXIS));
		
		pnlRow4.add(scrTBLTimKiemNhanVien);
		pnlRow4.add(pnlPhanCa);
		
		//set JScrollPane
		scrTBLTimKiemNhanVien.setBounds(10, 10, 825, 302);;
		scrTBLPhanCaLam.setMaximumSize(new Dimension( 1162, 257));
		scrTBLPhanCaLam.setBackground(Color.white);
		scrTBLTimKiemNhanVien.setBackground(Color.white);
		
		//add column
		modelTBLTimKiemNhanVien.addColumn("Mã Nhân Viên");
		modelTBLTimKiemNhanVien.addColumn("Tên Nhân Viên");
		modelTBLTimKiemNhanVien.addColumn("Giới Tính");
		modelTBLTimKiemNhanVien.addColumn("Chức Vụ");
		modelTBLTimKiemNhanVien.addColumn("Số Điện Thoại");
		
		modelTBLPhanCaLam.addColumn("Mã Nhân Viên");
		modelTBLPhanCaLam.addColumn("Tên Nhân Viên");
		modelTBLPhanCaLam.addColumn("Số Điện Thoại");
		modelTBLPhanCaLam.addColumn("Chức Vụ");
		modelTBLPhanCaLam.addColumn("Ca Làm");
		modelTBLPhanCaLam.addColumn("Kiểu Phân Ca Làm");
		modelTBLPhanCaLam.addColumn("Ngày Bắt Đầu");
		modelTBLPhanCaLam.addColumn("Ngày Kết Thúc");
		
		//create label
		JLabel lblTitle = new JLabel("PHÂN CÔNG CA LÀM");
		JLabel lblPhanCa = new JLabel("Phân Ca");
		JLabel lblTimKiemMaNhanVien = new JLabel("Mã Nhân Viên");
		JLabel lblTimKiemTenNhanVien = new JLabel("Tên Nhân Viên");
		JLabel lblTimKiemSoDienThoai = new JLabel("Số Điện Thoại");
		JLabel lblTimKiemGioiTinh = new JLabel("Giới Tính");
		JLabel lblTimKiemDiaChi = new JLabel("Địa Chỉ");
		JLabel lblTimKiemChucVu = new JLabel("Chức Vụ");
		JLabel lblMaNhanVien = new JLabel("Mã Nhân Viên");
		JLabel lblKieuPhanCa = new JLabel("Kiểu Phân Ca");
		JLabel lblNgayBatDau = new JLabel("Ngày Bắt Đầu");
		JLabel lblNgayKetThuc = new JLabel("Ngày Kết Thúc");
		JLabel lblCaLam = new JLabel("Ca Làm");
		JLabel lblBanPhanCong = new JLabel("Bản Phân Công");
		
		
		//set label
		lblBanPhanCong.setFont(new Font("",Font.BOLD,22));
		
		lblTitle.setFont(new Font("", Font.BOLD, 22));
		lblTitle.setForeground(Color.decode("#0500FF"));
		
		lblPhanCa.setFont(new Font("",Font.BOLD, 16));
		
		lblTimKiemMaNhanVien.setMaximumSize(new Dimension(108, 20));
		lblTimKiemTenNhanVien.setMaximumSize(new Dimension(108, 20));
		lblTimKiemDiaChi.setMaximumSize(new Dimension(100,20) );
		lblTimKiemSoDienThoai.setMaximumSize(new Dimension(100,20) );
		lblTimKiemChucVu.setMaximumSize(new Dimension(70,20));
		lblTimKiemGioiTinh.setMaximumSize(new Dimension(70,20));
		
		lblCaLam.setMaximumSize(lblNgayKetThuc.getMaximumSize());
		lblMaNhanVien.setMaximumSize(lblNgayKetThuc.getMaximumSize());
		lblKieuPhanCa.setMaximumSize(lblNgayKetThuc.getMaximumSize());
		lblNgayBatDau.setMaximumSize(lblNgayKetThuc.getMaximumSize());
		
		//set Button
		btnTimKiem.setMaximumSize(new Dimension(89,31));
		btnCapNhat.setMaximumSize(new Dimension(99,31));
		btnPhanCa.setMaximumSize(new Dimension(99,31));
		btnXoa.setMaximumSize(new Dimension(99,31));
		
		//format button event
		utils.Format.setButtonEvent(btnCapNhat,btnXoa,btnPhanCa,btnTimKiem);
		
		//set textField
		txtTimKiemMaNhanVien.setMaximumSize(new Dimension(175,32));
		txtTimKiemTenNhanVien.setMaximumSize(new Dimension(175,32));
		txtTimKiemDiaChi.setMaximumSize(new Dimension(175,32));
		txtTimKiemSoDienThoai.setMaximumSize(new Dimension(175,32));
		cmbChucVu.setMaximumSize(new Dimension(175,32));
		cmbGioiTinh.setMaximumSize(new Dimension(175,32));
		
		
		txtPhanCaMaNhanVien.setMaximumSize(new Dimension(105,22));
		txtPhanCaMaNhanVien.setEditable(false);
		
		//set combobox
		cmbCaLam.setMaximumSize(new Dimension(100,22));
		cmbKieuPhanCa.setMaximumSize(new Dimension(103,22));
		
		//set JDateChooser
		dateChooserStart.setMaximumSize(new Dimension(105,22));
		dateChooserEnd.setMaximumSize(new Dimension(105,22));
		
		

		
		
		
		//row tile
		Box rowTiltle = Box.createHorizontalBox();
		rowTiltle.add(lblTitle);
		//row1
		Box row1 = Box.createHorizontalBox();
		row1.add(lblTimKiemMaNhanVien);
		row1.add(Box.createRigidArea(new Dimension(15, 0)));
		row1.add(txtTimKiemMaNhanVien);
		row1.add(Box.createRigidArea(new Dimension(30, 0)));
		row1.add(lblTimKiemSoDienThoai);
		row1.add(Box.createRigidArea(new Dimension(15, 0)));
		row1.add(txtTimKiemSoDienThoai);
		row1.add(Box.createRigidArea(new Dimension(30, 0)));
		row1.add(lblTimKiemGioiTinh);
		row1.add(Box.createRigidArea(new Dimension(15, 0)));
		row1.add(cmbGioiTinh);
		
		Box row2 = Box.createHorizontalBox();
		row2.add(lblTimKiemTenNhanVien);
		row2.add(Box.createRigidArea(new Dimension(15, 0)));
		row2.add(txtTimKiemTenNhanVien);
		row2.add(Box.createRigidArea(new Dimension(30, 0)));
		row2.add(lblTimKiemDiaChi);
		row2.add(Box.createRigidArea(new Dimension(15, 0)));
		row2.add(txtTimKiemDiaChi);
		row2.add(Box.createRigidArea(new Dimension(30, 0)));
		row2.add(lblTimKiemChucVu);
		row2.add(Box.createRigidArea(new Dimension(15, 0)));
		row2.add(cmbChucVu);
		
		Box row3 = Box.createHorizontalBox();
		row3.add(Box.createRigidArea(new Dimension(700, 0)));
		row3.add(btnTimKiem);
		
		Box row5 = Box.createHorizontalBox();
		row5.add(lblBanPhanCong);
		//add component vào panel phân công
		
		Box row1PNLPhanCa = Box.createHorizontalBox();
		row1PNLPhanCa.add(lblPhanCa);
		
		Box row2PNLPhanCa = Box.createHorizontalBox();
		row2PNLPhanCa.setMaximumSize(new Dimension(225,22));
		row2PNLPhanCa.add(lblMaNhanVien);
		row2PNLPhanCa.add(Box.createHorizontalStrut(33));
		row2PNLPhanCa.add(txtPhanCaMaNhanVien);
		
		Box row3PNLPhanCa = Box.createHorizontalBox();
		row3PNLPhanCa.setMaximumSize(new Dimension(225,22));
		row3PNLPhanCa.add(lblCaLam);
		row3PNLPhanCa.add(Box.createHorizontalStrut(33));
		row3PNLPhanCa.add(cmbCaLam);
		
		Box row4PNLPhanCa = Box.createHorizontalBox();
		row4PNLPhanCa.setMaximumSize(new Dimension(225,22));
		row4PNLPhanCa.add(lblKieuPhanCa);
		row4PNLPhanCa.add(Box.createHorizontalStrut(33));
		row4PNLPhanCa.add(cmbKieuPhanCa);
		
		Box row5PNLPhanCa= Box.createHorizontalBox();
		row5PNLPhanCa.setMaximumSize(new Dimension(225,22));
		row5PNLPhanCa.add(lblNgayBatDau);
		row5PNLPhanCa.add(Box.createHorizontalStrut(33));
		row5PNLPhanCa.add(dateChooserStart);
		
		Box row6PNLPhanCa = Box.createHorizontalBox();
		row6PNLPhanCa.setMaximumSize(new Dimension(225,22));
		row6PNLPhanCa.add(lblNgayKetThuc);
		row6PNLPhanCa.add(Box.createHorizontalStrut(33));
		row6PNLPhanCa.add(dateChooserEnd);
		
		Box row7PNLPhanCa = Box.createHorizontalBox();
		row7PNLPhanCa.setMaximumSize(new Dimension(225,33));
		row7PNLPhanCa.add(btnPhanCa);
		row7PNLPhanCa.add(Box.createHorizontalStrut(33));
		row7PNLPhanCa.add(btnCapNhat);
		
		Box row8PNLPhanCa = Box.createHorizontalBox();
		row8PNLPhanCa.setMaximumSize(new Dimension(225,33));
		row8PNLPhanCa.add(Box.createRigidArea(new Dimension(99,31)));
		row8PNLPhanCa.add(Box.createHorizontalStrut(30));
		row8PNLPhanCa.add(btnXoa);
		
		pnlPhanCa.add(row1PNLPhanCa);
		pnlPhanCa.add(Box.createVerticalStrut(25));
		pnlPhanCa.add(row2PNLPhanCa);
		pnlPhanCa.add(Box.createVerticalStrut(7));
		pnlPhanCa.add(row3PNLPhanCa);
		pnlPhanCa.add(Box.createVerticalStrut(7));
		pnlPhanCa.add(row4PNLPhanCa);
		pnlPhanCa.add(Box.createVerticalStrut(7));
		pnlPhanCa.add(row5PNLPhanCa);
		pnlPhanCa.add(Box.createVerticalStrut(7));
		pnlPhanCa.add(row6PNLPhanCa);
		pnlPhanCa.add(Box.createVerticalStrut(30));
		pnlPhanCa.add(row7PNLPhanCa);
		pnlPhanCa.add(Box.createVerticalStrut(10));
		pnlPhanCa.add(row8PNLPhanCa);
		
		//add component
		add(rowTiltle);
		add(Box.createRigidArea(new Dimension(0,17)));
		add(row1);
		add(Box.createRigidArea(new Dimension(0,8)));
		add(row2);
		add(Box.createRigidArea(new Dimension(0,10)));
		add(row3);
		add(Box.createRigidArea(new Dimension(0,15)));
		add(pnlRow4);
		add(row5);
		add(Box.createRigidArea(new Dimension(0,10)));
		add(scrTBLPhanCaLam);
		
		txtdateStart = (JTextField) dateChooserStart.getDateEditor().getUiComponent();
		txtdateEnd = (JTextField) dateChooserEnd.getDateEditor().getUiComponent();
		
		btnTimKiem.addActionListener(this);
		tblTimKiemNhanVien.addMouseListener(this);
		tblPhanCaLam.addMouseListener(this);
		
		
		dateChooserStart.addPropertyChangeListener(this);
		dateChooserEnd.addPropertyChangeListener(this);
		
		cmbKieuPhanCa.addActionListener(this);
		//add event
		btnCapNhat.addActionListener(this);
		btnPhanCa.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnXoa.addActionListener(this);
		
		txtdateStart.addFocusListener(this);
		txtdateEnd.addFocusListener(this);
	}
	/*
	 *được sử dụng để chạy hoàn thiện giao diện khi giao diện được render thành công
	 */
	private void initial() {
		// TODO Auto-generated method stub
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listNV = nhanVien_DAO.getAllNhanVien();
		loadTableNhanVien(listNV);
		listCL = caLam_DAO.getAllCaLam();
		
		for (CaLam caLam : listCL) {
			String strCL = utils.Format.formatDate(caLam.getGioBatDau()) +"-"+utils.Format.formatDate(caLam.getGioKetThuc());
			modelCBOCaLam.addElement(strCL);
		}
		modelCBOKieuPhanCa.addElement(utils.Contains.KIEU_PHAN_CA_TUY_CHINH);
		modelCBOKieuPhanCa.addElement(utils.Contains.KIEU_PHAN_CA_CO_DINH);
		
		dateChooserEnd.setEnabled(false);
		
		LocalDate localDateCurrent = LocalDate.now();
		localDateCurrent.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		listBPC = bangPhanCa_DAO.timKiemBangPhanCa(localDateCurrent);
		LoadTablePhanCaLam(listBPC);
	}

	/*
	 * được sử dụng để load 1 JTable, mà JTable này cần 1 table từ database
	 */
	private void loadTableNhanVien( List<NhanVien> list) {
		modelTBLTimKiemNhanVien.setRowCount(0);
		for (NhanVien nhanVien : list) {
			String[] row = {nhanVien.getMaNV(),nhanVien.getTen(),nhanVien.getGioiTinh().equals(utils.Contains.NAM)?"Nam":"Nữ",nhanVien.getChucVu().equals(utils.Contains.NHAN_VIEN_QUAN_LY)?"Nhân Viên Quản Lý":"Nhân Viên Bán Hàng",nhanVien.getSoDienThoai()};
			modelTBLTimKiemNhanVien.addRow(row);
		}
	}

	private void LoadTablePhanCaLam(List<BangPhanCa> list) {
		modelTBLPhanCaLam.setRowCount(0);
		for (BangPhanCa bpc : list) {
			NhanVien nv = bpc.getNhanVien();
			CaLam cl = bpc.getCaLam();
			String strCL = utils.Format.formatDate(cl.getGioBatDau())+"-"+utils.Format.formatDate(cl.getGioKetThuc());
			String[] row = {nv.getMaNV(),nv.getTen(),nv.getSoDienThoai(),nv.getChucVu().equals(utils.Contains.NHAN_VIEN_QUAN_LY)?"Nhân Viên Quản Lý":"Nhân Viên Bán Hàng",strCL,bpc.getKieuPhanCa(),bpc.getNgayBatDau().toString(),utils.Contains.KIEU_PHAN_CA_CO_DINH.equals(bpc.getKieuPhanCa()) ? "":bpc.getNgayKetThuc().toString()};
			modelTBLPhanCaLam.addRow(row);
		}
	}
	/*
	 * được sử dụng để ánh xạ các biến trong class
	 */
	private void anhXa() {
		// TODO Auto-generated method stub
		dateChooserStart = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');
		dateChooserEnd = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');
		
		txtPhanCaMaNhanVien = new JTextField();
		txtTimKiemMaNhanVien = new JTextField();
		txtTimKiemTenNhanVien = new JTextField();
		txtTimKiemDiaChi = new JTextField();
		txtTimKiemSoDienThoai = new JTextField();
		
		btnCapNhat = new JButton("Cập Nhật");
		btnXoa = new JButton("Xóa Phân Ca");
		btnPhanCa = new JButton("Phân Ca");
		btnTimKiem = new JButton("Tìm Kiếm");
		
		modelTBLPhanCaLam = new DefaultTableModel();
		modelTBLTimKiemNhanVien = new DefaultTableModel();
		modelCBOCaLam = new DefaultComboBoxModel<>();
		modelCBOKieuPhanCa = new DefaultComboBoxModel<>();
		
		cmbCaLam = new JComboBox(modelCBOCaLam);
		cmbKieuPhanCa = new JComboBox(modelCBOKieuPhanCa);
		cmbGioiTinh = new JComboBox<String>();
		cmbChucVu = new JComboBox<String>();
		
		tblPhanCaLam = new JTable(modelTBLPhanCaLam){
			@Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép chỉnh sửa dữ liệu trong JTable
            }
		};
		
		tblTimKiemNhanVien = new JTable(modelTBLTimKiemNhanVien){
			@Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép chỉnh sửa dữ liệu trong JTable
            }
		};;

		listNV = new ArrayList<NhanVien>();
		listCL = new ArrayList<CaLam>();
		listBPC = new ArrayList<BangPhanCa>();
		nhanVien_DAO = new NhanVien_DAO();
		caLam_DAO = new CaLam_DAO();
		bangPhanCa_DAO = new BangPhanCa_DAO();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnTimKiem)) {
			List<NhanVien> list = new ArrayList<NhanVien>();
			String maNV = txtTimKiemMaNhanVien.getText().toString().trim();
			String tenNV = txtTimKiemTenNhanVien.getText().toString().trim();
			for (NhanVien nhanVien : listNV) {
				if(nhanVien.getMaNV().contains(maNV) && nhanVien.getTen().contains(tenNV) ) {
					list.add(nhanVien);
				}
			}
			loadTableNhanVien(list);
		}else if(o.equals(btnPhanCa)) {
			boolean result = kiemTraThongTinTruocKhiPhanCa();
			if(result) {
				phanCa();
			}
		}else if(o.equals(cmbKieuPhanCa)) {
			String strItem = cmbKieuPhanCa.getSelectedItem().toString();
			if(strItem.equals(utils.Contains.KIEU_PHAN_CA_CO_DINH)) {
				dateChooserEnd.setEnabled(false);
			}else {
				dateChooserEnd.setEnabled(true);
			}
		}else if(o.equals(btnCapNhat)) {
			
			int row = tblPhanCaLam.getSelectedRow();
			if(row >= 0) {
				BangPhanCa bangPhanCaCu = listBPC.get(row);
				listBPC.remove(row);
				boolean resultkiemTraThongTinTruocKhiCapNhat = kiemTraThongTinTruocKhiCapNhat(bangPhanCaCu);
				
				if(resultkiemTraThongTinTruocKhiCapNhat) {
					boolean resultCapNhatPhanCa = phanCa();
					
					if(resultCapNhatPhanCa) {
						LocalDate localDateCurrent = LocalDate.now();
						localDateCurrent.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
						
						bangPhanCa_DAO.xoaBangPhanCa(bangPhanCaCu);
						listBPC.clear();
						listBPC = bangPhanCa_DAO.timKiemBangPhanCa(localDateCurrent);
						LoadTablePhanCaLam(listBPC);
					}else {
						listBPC.add(bangPhanCaCu);
					}
				}
				
			}else {
				JOptionPane.showMessageDialog(PhanCongCaLam.this, "Vui lòng chọn nhân viên trước khi cập nhật ca", "ERROR",JOptionPane.ERROR_MESSAGE);
			}
				
			
			
		}else if(o.equals(btnXoa)) {
			int row = tblPhanCaLam.getSelectedRow(); 
			if(row >= 0) {
				BangPhanCa bangPhanCaCu = listBPC.get(row);
				boolean resultkiemTraNgayBatDauCaLamDaLamChua = kiemTraNgayBatDauCaLamDaLamChua(bangPhanCaCu);
				if(resultkiemTraNgayBatDauCaLamDaLamChua) {
					int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa phân ca hay không?","WRONG", JOptionPane.YES_NO_OPTION);
					if(result == 0) {
						
						listBPC.remove(row);
						bangPhanCa_DAO.xoaBangPhanCa(bangPhanCaCu);
						
						LoadTablePhanCaLam(listBPC);
					}	
				}else {
					JOptionPane.showMessageDialog(PhanCongCaLam.this, "Không thể xóa phân ca, Khi ca làm đã được làm hoặc đang làm", "ERROR",JOptionPane.ERROR_MESSAGE);
				}
				
			}else {
				JOptionPane.showMessageDialog(PhanCongCaLam.this, "Vui lòng chọn nhân viên trước khi cập nhật ca", "ERROR",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		
	}
	private boolean kiemTraThongTinTruocKhiPhanCa() {
		String maNV = txtPhanCaMaNhanVien.getText().toString().trim();
		if(maNV.equals("")) {
			JOptionPane.showMessageDialog(PhanCongCaLam.this, "Vui lòng chọn nhân viên trước khi phân ca", "ERROR",JOptionPane.ERROR_MESSAGE);
			return false;
		}else {
			if(dateChooserStart.getDate() != null) {

				dateChooserStart.setBorder(null);
				if(dateChooserEnd.getDate() != null || cmbKieuPhanCa.getSelectedItem().equals(utils.Contains.KIEU_PHAN_CA_CO_DINH)) {
					dateChooserEnd.setBorder(null);
					
					LocalDate localDateCurrent = LocalDate.now();
					Date dateStart = dateChooserStart.getDate();
					
					String strDateStart = dateStart.toString();
					
					LocalDate localDateStart = LocalDate.parse(strDateStart, DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH));
					
					localDateCurrent.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
					localDateStart.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
					
					if(localDateStart.compareTo(localDateCurrent) == 0) {
						String strCaLam = cmbCaLam.getSelectedItem().toString();
						LocalTime gioBatDauCaLam = LocalTime.parse(strCaLam.substring(0, 5), DateTimeFormatter.ofPattern("HH:mm"));
						LocalTime gioHienTai = LocalTime.now();
						gioHienTai.format(DateTimeFormatter.ofPattern("HH:mm"));
						
						if(gioBatDauCaLam.compareTo(gioHienTai) >= 0) {
							return true;
						}else {
							JOptionPane.showMessageDialog(PhanCongCaLam.this, "Giờ cà làm bắt đầu nhỏ hơn giờ hiện tại", "ERROR",JOptionPane.ERROR_MESSAGE);
							return false;
						}
					}else {
						return true;
					}
				}else {
					dateChooserEnd.setBorder(BorderFactory.createLineBorder(Color.red));
					JOptionPane.showMessageDialog(PhanCongCaLam.this, "Vui lòng chọn ngày kết thúc", "ERROR",JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}else {
				dateChooserStart.setBorder(BorderFactory.createLineBorder(Color.red));
				JOptionPane.showMessageDialog(PhanCongCaLam.this, "Vui lòng chọn ngày bắt đầu", "ERROR",JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
		}
	}
	private boolean kiemTraThongTinTruocKhiCapNhat(BangPhanCa bpcCu) {
		String maNV = txtPhanCaMaNhanVien.getText().toString().trim();
		if(maNV.equals("")) {
			JOptionPane.showMessageDialog(PhanCongCaLam.this, "Vui lòng chọn nhân viên trước khi phân ca", "ERROR",JOptionPane.ERROR_MESSAGE);
			return false;
		}else {
			if(dateChooserStart.getDate() != null) {

				dateChooserStart.setBorder(null);
				if(dateChooserEnd.getDate() != null || cmbKieuPhanCa.getSelectedItem().equals(utils.Contains.KIEU_PHAN_CA_CO_DINH)) {
					dateChooserEnd.setBorder(null);
					
					boolean result = kiemTraNgayBatDauCaLamDaLamChua(bpcCu);
					
					//chưa làm vào if
					if(result) {
						return true;
					}else {
						JOptionPane.showMessageDialog(PhanCongCaLam.this, "Không thể cập nhật ca, Khi ca làm đã được làm hoặc đang làm", "ERROR",JOptionPane.ERROR_MESSAGE);
						return false;
					}
					
				}else {
					dateChooserEnd.setBorder(BorderFactory.createLineBorder(Color.red));
					JOptionPane.showMessageDialog(PhanCongCaLam.this, "Vui lòng chọn ngày kết thúc", "ERROR",JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}else {
				dateChooserStart.setBorder(BorderFactory.createLineBorder(Color.red));
				JOptionPane.showMessageDialog(PhanCongCaLam.this, "Vui lòng chọn ngày bắt đầu", "ERROR",JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
		}
	}
	
	/*Kiểm tra ngày bắt đầu ca làm đã qua giờ bắt đầu làm trong ngày hiện tại hay chưa
	 *chưa làm trả về true
	 *làm rồi trả về false */
	private boolean kiemTraNgayBatDauCaLamDaLamChua(BangPhanCa bpc) {
		
		LocalDate ngayBatDau = bpc.getNgayBatDau();
		LocalDate localDateCurrent = LocalDate.now();
		localDateCurrent.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		
		if(ngayBatDau.compareTo(localDateCurrent) == 0) {
			
			LocalTime gioBatDauCaLam = bpc.getCaLam().getGioBatDau().toLocalTime();
			LocalTime gioHienTai = LocalTime.now();
			gioHienTai.format(DateTimeFormatter.ofPattern("HH:mm"));
			
			if(gioBatDauCaLam.compareTo(gioHienTai) > 0) {
				return true;
			}else {
				return false;
			}
		}else if(ngayBatDau.compareTo(localDateCurrent) < 0) {
			return false;
		}
		return true;
	}
	private boolean phanCa() {
		NhanVien nv = new NhanVien();
		CaLam cl = new CaLam();
		String kieuCaLam = cmbKieuPhanCa.getSelectedItem().toString().trim();
		nv.setMaNV(txtPhanCaMaNhanVien.getText());
		
		LocalDate localDateCurrent = LocalDate.now();
		
		Date dateStart = dateChooserStart.getDate();
		String strDateStart = dateStart.toString();
		String strCaLam = cmbCaLam.getSelectedItem().toString();
		LocalDate localDateStart = LocalDate.parse(strDateStart, DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH));
		
		
		localDateCurrent.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		localDateStart.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		
		
		
		//lấy mã ca làm
		String maCL = timKiemMaCaLam(strCaLam);
		cl.setMaCL(maCL);
		
		//kiểm tra nếu phân ca kiểu tùy chỉnh thì thêm ngày kết thúc
		//nếu là phân ca cố định => ngày kết thúc = null
		BangPhanCa bpc = null;
		if(kieuCaLam.equals(utils.Contains.KIEU_PHAN_CA_TUY_CHINH)) {
			Date dateEnd = dateChooserEnd.getDate();
			String strDateEnd =  dateEnd.toString();
			LocalDate localDateEnd = LocalDate.parse(strDateEnd, DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH));
			localDateEnd.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
			
			//thêm row vào bảng phân ca  
			bpc = new BangPhanCa(nv, cl, localDateStart, localDateEnd, kieuCaLam);
		}else {
			//thêm row vào bảng phân ca  
			bpc = new BangPhanCa(nv, cl, localDateStart, null, kieuCaLam);
		}
		
		
		
		// kiểm tra có trùng lặp hoặc ngày bắt đầu và kết thúc nằm trong 1 bảng phân ca nào hay không
		boolean isTrungLap = kiemTraTrungLapPhanCaLam(bpc);
		if(isTrungLap) {
			JOptionPane.showMessageDialog(PhanCongCaLam.this, "Phân ca bị trùng lặp dữ liệu", "ERROR",JOptionPane.ERROR_MESSAGE);
		}else {
			//kiểm tra nếu nhân viên có kiểu ca mới nhất làm là mặc định thì cập nhật ngày kết thúc
			BangPhanCa bpcCu  = bangPhanCa_DAO.kiemTraKieuPhanCaGanNhat(nv.getMaNV(), cl.getMaCL());
			if(bpcCu != null) {
				if(bpcCu.getKieuPhanCa().equals(utils.Contains.KIEU_PHAN_CA_CO_DINH)) {
					LocalDate ngayKetThucCu = localDateStart.plusDays(-1);
					if(ngayKetThucCu.compareTo(bpcCu.getNgayBatDau()) < 0) {
						bpcCu.setNgayKetThuc(bpcCu.getNgayBatDau());
					}else {
						bpcCu.setNgayKetThuc(ngayKetThucCu);
					}
					
					bpcCu.setKieuPhanCa(utils.Contains.KIEU_PHAN_CA_TUY_CHINH);
					capNhatBangPhanCa(bpcCu);
				}
			}
			
			//thêm row vào bảng phân ca
			boolean result = bangPhanCa_DAO.themBangPhanCa(bpc);
			if(result) {
				//load lại bảng phân ca
				// bảng phân ca có ngày bắt đầu của nhân viên >= ngày hiện tại
				listBPC.clear();
				listBPC = bangPhanCa_DAO.timKiemBangPhanCa(localDateCurrent);
				
				LoadTablePhanCaLam(listBPC);
				JOptionPane.showMessageDialog(PhanCongCaLam.this, "Phân ca thành công", "SUCCESS",JOptionPane.INFORMATION_MESSAGE);
				return true;
			}else {
				JOptionPane.showMessageDialog(PhanCongCaLam.this, "Phân ca bị trùng lặp dữ liệu", "ERROR",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		return false;
		
			
		
		
	}
	public String timKiemMaCaLam(String strCaLam) {
		for (CaLam caLam : listCL) {
			if((utils.Format.formatDate(caLam.getGioBatDau()) +"-"+utils.Format.formatDate(caLam.getGioKetThuc())).equals(strCaLam)){
				return caLam.getMaCL();
			}
		}
		return null;
	}
	private boolean kiemTraTrungLapPhanCaLam(BangPhanCa bpc) {
		LocalDate ngayBatDauThem = bpc.getNgayBatDau();
		LocalDate ngayKetThucThem = bpc.getNgayKetThuc() != null ? bpc.getNgayKetThuc() : bpc.getNgayBatDau();
		
		for (BangPhanCa bangPhanCa : listBPC) {
			LocalDate ngayBatDauCu = bangPhanCa.getNgayBatDau();
			LocalDate ngayKetThucCu = bangPhanCa.getNgayKetThuc() != null ? bangPhanCa.getNgayKetThuc() : bangPhanCa.getNgayBatDau();

			if(bpc.getNhanVien().getMaNV().equals(bangPhanCa.getNhanVien().getMaNV()) && bangPhanCa.getCaLam().getMaCL().equals(bpc.getCaLam().getMaCL()) && 
					((ngayBatDauThem.compareTo(ngayBatDauCu)>=0 && ngayBatDauThem.compareTo(ngayKetThucCu) <= 0) ||
							(ngayKetThucThem.compareTo(ngayBatDauCu) >= 0 && ngayKetThucThem.compareTo(ngayKetThucCu)<=0) || 
							(ngayBatDauThem.compareTo(ngayBatDauCu) <=0 && ngayKetThucThem.compareTo(ngayKetThucCu) >=0)) ) {
				return true;
			}
			
		}
		return false;
	}
	private boolean capNhatBangPhanCa(BangPhanCa bpcCu) {
		// TODO Auto-generated method stub
		boolean result = bangPhanCa_DAO.capNhatNgayKetThucChoKieuPhanCaMacDinh(bpcCu);
//		if(result) {
//			JOptionPane.showMessageDialog(NV_PhanCongCaLam.this, "Cập nhật thành công","SUCCESS",JOptionPane.INFORMATION_MESSAGE);
//		}else {
//			JOptionPane.showMessageDialog(NV_PhanCongCaLam.this, "Cập nhật thất bại","ERROR",JOptionPane.ERROR_MESSAGE);
//		}
		return result;
	}
	private void kiemTraKhiNguoiDungCapNhatNgayBatDau() {
		System.out.println("a");
		if(dateChooserStart.getDate() != null) {
			LocalDate localDateCurrent = LocalDate.now();
			Date dateStart = dateChooserStart.getDate();
			String strDateStart = dateStart.toString();
			
			LocalDate localDateStart = LocalDate.parse(strDateStart, DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH));
			localDateCurrent.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
			localDateStart.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
			
			if( localDateStart.compareTo(localDateCurrent) >= 0) {
				dateChooserStart.setEnabled(true);
				if(cmbKieuPhanCa.getSelectedItem().equals(utils.Contains.KIEU_PHAN_CA_TUY_CHINH)) {
					dateChooserEnd.setEnabled(true);
					
				}
			}else {
				if(btnPhanCa.isEnabled()) {
					dateChooserStart.setDate(null);
					JOptionPane.showMessageDialog(PhanCongCaLam.this, "Ngày bắt đầu phải sau hoặc bằng ngày hiện tại", "ERROR",JOptionPane.ERROR_MESSAGE);
				}else {
					dateChooserStart.setEnabled(false);
					dateChooserEnd.setEnabled(true);
				}
			}
		}else {
			dateChooserEnd.setEnabled(false);	
		}
	}
	private void kiemTraKhiNguoiDungCapNhatNgayKetThuc() {
		System.out.println("b");
		if(dateChooserEnd.getDate() != null && dateChooserStart.getDate()!= null) {
			Date dateStart = dateChooserStart.getDate();
			Date dateEnd = dateChooserEnd.getDate();
			
			String strDateStart = dateStart.toString();
			String strDateEnd = dateEnd.toString();
			
			LocalDate localDateStart = LocalDate.parse(strDateStart, DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH));
			LocalDate localDateEnd = LocalDate.parse(strDateEnd, DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH));	
			
			localDateStart.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
			localDateEnd.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
			
			if(localDateStart.isBefore(localDateEnd) || localDateStart.compareTo(localDateEnd) == 0) {
				
			}else {
				dateChooserEnd.setDate(null);
				JOptionPane.showMessageDialog(PhanCongCaLam.this, "Ngày kế thúc phải sau hoặc bằng ngày bắt đầu", "ERROR",JOptionPane.ERROR_MESSAGE);
			}
		}	
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(tblTimKiemNhanVien)) {
			int row = tblTimKiemNhanVien.getSelectedRow();
			txtPhanCaMaNhanVien.setText((String) tblTimKiemNhanVien.getValueAt(row, 0));
			
			btnCapNhat.setEnabled(false);
			btnXoa.setEnabled(false);
			btnPhanCa.setEnabled(true);
			dateChooserStart.setEnabled(true);
		}else if(o.equals(tblPhanCaLam)) {
			
			btnCapNhat.setEnabled(true);
			btnXoa.setEnabled(true);
			btnPhanCa.setEnabled(false);
			
			String format = "yyyy-MM-dd";
	        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
	        
			int row = tblPhanCaLam.getSelectedRow();
			try {
				Date ngayBatDau = dateFormat.parse(tblPhanCaLam.getValueAt(row, 6).toString().trim());
				dateChooserStart.setDate(ngayBatDau);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			txtPhanCaMaNhanVien.setText(tblPhanCaLam.getValueAt(row, 0)+"");
			
			
			
			
			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		Object o  = evt.getSource();
		if(o.equals(dateChooserStart)) {
			kiemTraKhiNguoiDungCapNhatNgayBatDau();
		}else if(o.equals(dateChooserEnd)) {
			kiemTraKhiNguoiDungCapNhatNgayKetThuc();
		}
	}
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		Object o  = e.getSource();
		if(o.equals(txtdateStart)) {
			kiemTraKhiNguoiDungCapNhatNgayBatDau();
		}else if(o.equals(txtdateEnd)) {
			kiemTraKhiNguoiDungCapNhatNgayKetThuc();
		}
	}
	
}
