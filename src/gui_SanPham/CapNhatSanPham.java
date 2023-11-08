package gui_SanPham;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.NhaCungCap_DAO;
import dao.SanPham_DAO;
import entity.NhaCungCap;
import entity.SanPham;
import utils.Function;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;

public class CapNhatSanPham extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel pnQuanLySanPham;
	private JLabel lblTieuDe,lblMaSanPham,lblDanhMuc,lblTenSanPham,lblNhaCungCap,lblChatLieu,lblMauSac,
	lblKichCo,lblSoLuongTon,lblGiaBan,lblDanhSachSanPham,lblGiaNhap,lblSoLuongBan,lblHinhAnh;
	private JButton btnChonAnh,btnXoa,btnThem,btnLuu;
	private JTextField txtTenSanPham,txtMaSanPham,txtGiaBan,txtSoLuongBan,txtGiaNhap;
	private JTable sanPhamTable;
	private DefaultTableModel dataModel;
	private JScrollPane scPanel;
	private JComboBox cbbChatLieu,cbbDanhMuc,cbbMauSac,cbbKichCo,cbbNhaCungCap;
	private JSpinner spinnerSoLuongTon;
	private String pathImage;
	
	private SanPham_DAO dssp;

	private NhaCungCap_DAO dsncc;
	/**
	 * Create the panel.
	 */
	public CapNhatSanPham() {
		
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dssp=new SanPham_DAO();
		dsncc=new NhaCungCap_DAO();
		
		setLayout(null);
		setBackground(Color.WHITE);
		setBounds(0, 0, 1332, 797);
		
		lblTieuDe = new JLabel("QUẢN LÝ SẢN PHẨM");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblTieuDe.setOpaque(true);
		lblTieuDe.setBackground(Color.WHITE);
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setForeground(Color.BLUE);
		lblTieuDe.setBounds(468, 10, 338, 44);
		add(lblTieuDe);
		
		pnQuanLySanPham = new JPanel();
		pnQuanLySanPham.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnQuanLySanPham.setBackground(new Color(255, 255, 255));
		pnQuanLySanPham.setBounds(10, 64, 1298, 283);
		add(pnQuanLySanPham);
		pnQuanLySanPham.setLayout(null);
		
		lblMaSanPham = new JLabel("Mã sản phẩm ");
		lblMaSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMaSanPham.setBounds(20, 20, 123, 24);
		pnQuanLySanPham.add(lblMaSanPham);
		
		txtTenSanPham = new JTextField();
		txtTenSanPham.setBounds(141, 77, 190, 31);
		pnQuanLySanPham.add(txtTenSanPham);
		txtTenSanPham.setColumns(10);
		
		lblDanhMuc = new JLabel("Danh mục");
		lblDanhMuc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblDanhMuc.setBounds(413, 20, 93, 24);
		pnQuanLySanPham.add(lblDanhMuc);
		
		lblTenSanPham = new JLabel("Tên sản phẩm ");
		lblTenSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTenSanPham.setBounds(20, 78, 123, 24);
		pnQuanLySanPham.add(lblTenSanPham);
		
		lblNhaCungCap = new JLabel("Nhà cung cấp ");
		lblNhaCungCap.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNhaCungCap.setBounds(768, 138, 123, 24);
		pnQuanLySanPham.add(lblNhaCungCap);
		
		lblChatLieu = new JLabel("Chất liệu ");
		lblChatLieu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblChatLieu.setBounds(413, 78, 83, 24);
		pnQuanLySanPham.add(lblChatLieu);
		
		lblMauSac = new JLabel("Màu sắc");
		lblMauSac.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMauSac.setBounds(413, 138, 83, 24);
		pnQuanLySanPham.add(lblMauSac);
		
		lblKichCo = new JLabel("Kích cỡ");
		lblKichCo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblKichCo.setBounds(768, 20, 93, 24);
		pnQuanLySanPham.add(lblKichCo);
		
		lblSoLuongTon = new JLabel("Số lượng tồn");
		lblSoLuongTon.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblSoLuongTon.setBounds(768, 78, 114, 24);
		pnQuanLySanPham.add(lblSoLuongTon);
		
		lblGiaBan = new JLabel("Giá bán");
		lblGiaBan.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblGiaBan.setBounds(20, 138, 93, 24);
		pnQuanLySanPham.add(lblGiaBan);
		
		btnChonAnh = new JButton("Chọn ảnh");
		btnChonAnh.setBounds(1130, 240, 123, 31);
		pnQuanLySanPham.add(btnChonAnh);
		
		txtMaSanPham = new JTextField();
		txtMaSanPham.setColumns(10);
		txtMaSanPham.setBounds(141, 19, 190, 31);
		pnQuanLySanPham.add(txtMaSanPham);
		
		txtGiaBan = new JTextField();
		txtGiaBan.setColumns(10);
		txtGiaBan.setBounds(141, 131, 190, 31);
		pnQuanLySanPham.add(txtGiaBan);
		
		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(345, 242, 123, 31);
		pnQuanLySanPham.add(btnXoa);
		
		btnThem = new JButton("Thêm");
		btnThem.setBounds(622, 242, 123, 31);
		pnQuanLySanPham.add(btnThem);
		
		btnLuu = new JButton("Lưu");
		btnLuu.setBounds(768, 242, 123, 31);
		pnQuanLySanPham.add(btnLuu);
		
		lblDanhSachSanPham = new JLabel("Danh sách sản phẩm");
		lblDanhSachSanPham.setOpaque(true);
		lblDanhSachSanPham.setHorizontalAlignment(SwingConstants.CENTER);
		lblDanhSachSanPham.setForeground(new Color(0, 0, 0));
		lblDanhSachSanPham.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblDanhSachSanPham.setBackground(Color.WHITE);
		lblDanhSachSanPham.setBounds(468, 357, 338, 44);
		add(lblDanhSachSanPham);
		
		String columns[] = { "Mã sản phẩm","Tên sản phẩm","Giá bán","Danh mục","Chất liệu","Màu sắc","Kích cỡ","Số lượng tồn","Số lượng bán", "Nhà cung cấp","Gía nhập"};
		dataModel = new DefaultTableModel(columns, 0);

		scPanel = new JScrollPane();

		sanPhamTable = new JTable(dataModel);
		scPanel = new JScrollPane(sanPhamTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scPanel.setViewportView(sanPhamTable);
		scPanel.setBounds(10, 411, 1298, 365);
		add(scPanel);
		
		cbbDanhMuc = new JComboBox();
		cbbDanhMuc.setBounds(509, 18, 190, 32);
		pnQuanLySanPham.add(cbbDanhMuc);
		
		cbbChatLieu = new JComboBox();
		cbbChatLieu.setBounds(509, 76, 190, 32);
		pnQuanLySanPham.add(cbbChatLieu);
		
		cbbMauSac = new JComboBox();
		cbbMauSac.setBounds(509, 130, 190, 32);
		pnQuanLySanPham.add(cbbMauSac);
		
		cbbKichCo = new JComboBox();
		cbbKichCo.setBounds(884, 18, 190, 32);
		pnQuanLySanPham.add(cbbKichCo);
		
		cbbNhaCungCap = new JComboBox();
		cbbNhaCungCap.setBounds(884, 130, 190, 32);
		pnQuanLySanPham.add(cbbNhaCungCap);
		
		lblGiaNhap = new JLabel("Giá nhập");
		lblGiaNhap.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblGiaNhap.setBounds(413, 187, 93, 24);
		pnQuanLySanPham.add(lblGiaNhap);
		
		lblSoLuongBan = new JLabel("Số lượng bán");
		lblSoLuongBan.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblSoLuongBan.setBounds(20, 187, 114, 24);
		pnQuanLySanPham.add(lblSoLuongBan);
		
		txtSoLuongBan = new JTextField();
		txtSoLuongBan.setColumns(10);
		txtSoLuongBan.setBounds(141, 187, 190, 31);
		pnQuanLySanPham.add(txtSoLuongBan);
		
		txtGiaNhap = new JTextField();
		txtGiaNhap.setColumns(10);
		txtGiaNhap.setBounds(509, 187, 190, 31);
		pnQuanLySanPham.add(txtGiaNhap);
		
		utils.Format.setButtonEvent(btnXoa,btnThem,btnLuu,btnChonAnh);
		
		txtMaSanPham.setEditable(false);
		txtSoLuongBan.setEditable(false);
		
		lblHinhAnh = new JLabel("");
		lblHinhAnh.setBackground(new Color(255, 255, 255));
		lblHinhAnh.setBounds(1103, 10, 185, 202);
		lblHinhAnh.setBorder(LineBorder.createBlackLineBorder());
		pnQuanLySanPham.add(lblHinhAnh);
		
		spinnerSoLuongTon = new JSpinner();
		spinnerSoLuongTon.setBounds(884, 77, 190, 31);
		spinnerSoLuongTon.setModel(new SpinnerNumberModel(new Integer(0), 0, null, new Integer(1)));
		pnQuanLySanPham.add(spinnerSoLuongTon);
		
		//docuLieuDatabase(dssp.getListSanPhamChiTiet());
		docDuLieuTuComboBox();
		docuLieuDatabase();
		
		btnXoa.addActionListener(this);
		btnChonAnh.addActionListener(this);
		btnLuu.addActionListener(this);
		btnThem.addActionListener(this);
		sanPhamTable.addMouseListener(this);
	}
	
//	public void docuLieuDatabase(List<SanPham> ds) {
//		for(SanPham sanPham : ds) {
//			Object[] row= {
//					sanPham.getMaSP(),
//					sanPham.getTenSP(),
//					sanPham.getGiaBan(),
//					sanPham.getDanhMuc().getTenDM(),
//					sanPham.getChatLieu().getTenCL(),
//					sanPham.getMauSac().getTenMS(),
//					sanPham.getKichCo().getTenKC(),
//					sanPham.getSoLuongTon(),
//					sanPham.getSoLuongBan(),
//					sanPham.getNhaCungCap().getTenNCC(),
//					sanPham.getGiaNhap(),
//			};
//			dataModel.addRow(row);
//		}
//	}
	public void docuLieuDatabase() {
		for(SanPham sanPham : dssp.getDanhSachSanPham()) {
			Object[] row= {
					sanPham.getMaSP(),
					sanPham.getTenSP(),
					sanPham.getGiaBan(),
					sanPham.getDanhMuc(),
					sanPham.getChatLieu(),
					sanPham.getMauSac(),
					sanPham.getKichCo(),
					sanPham.getSoLuongTon(),
					sanPham.getSoLuongBan(),
					sanPham.getNhaCungCap().getTenNCC(),
					sanPham.getGiaNhap(),
			};
			dataModel.addRow(row);
		}
	}
	
	private void docDuLieuTuComboBox() {
		for(String danhMuc : dssp.getDanhSachDanhMuc()) {
			cbbDanhMuc.addItem(danhMuc);
		}
		for(String chatLieu : dssp.getDanhSachChatLieu()) {
			cbbChatLieu.addItem(chatLieu);
		}
		for(String mauSac : dssp.getDanhSachMauSac()) {
			cbbMauSac.addItem(mauSac);
		}
		for(String kichCo: dssp.getDanhSachKichCo()) {
			cbbKichCo.addItem(kichCo);
		}
		for(NhaCungCap ncc: dsncc.getListNhaCungCap()) {
			cbbNhaCungCap.addItem(ncc.getTenNCC());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnThem)) {
			xoaTrang();	
		} else if(o.equals(btnXoa)) {
			xoaAction();
			xoaTrang();			
		} else if(o.equals(btnLuu)) {
			if (txtMaSanPham.getText().trim().isEmpty()) { // Kiểm tra xem có mã hay không
	            luu();
			}
	            // Nếu không, thực hiện lưu mới
			 else {
	            capNhatSanPham(); // Nếu có, thực hiện cập nhật dữ liệu 
			}
		} else if(o.equals(btnChonAnh)) {
			chonAnh();
//		}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row=sanPhamTable.getSelectedRow();
		



		String value = sanPhamTable.getValueAt(row, 7).toString(); // Lấy giá trị từ bảng tại vị trí dòng và cột cụ thể
		int parsedValue = Integer.parseInt(value);

		
		txtMaSanPham.setText((String) sanPhamTable.getValueAt(row, 0).toString()+"");
		txtTenSanPham.setText((String) sanPhamTable.getValueAt(row, 1).toString()+"");
		cbbDanhMuc.setSelectedItem((String) sanPhamTable.getValueAt(row, 3) + "");
		cbbChatLieu.setSelectedItem((String) sanPhamTable.getValueAt(row, 4) + "");
		cbbMauSac.setSelectedItem((String) sanPhamTable.getValueAt(row, 5) + "");
		cbbKichCo.setSelectedItem((String) sanPhamTable.getValueAt(row, 6) + "");
		cbbNhaCungCap.setSelectedItem((String) sanPhamTable.getValueAt(row, 9) + "");
		txtGiaBan.setText((String) sanPhamTable.getValueAt(	row,2).toString()+"");
		txtSoLuongBan.setText((String) sanPhamTable.getValueAt(row, 8).toString()+"");
		txtGiaNhap.setText((String) sanPhamTable.getValueAt(row, 10).toString()+"");
		spinnerSoLuongTon.setValue(parsedValue);
		
		String maSP = sanPhamTable.getValueAt(row, 0).toString(); // Giả sử mã sản phẩm được lấy từ cột đầu tiên

	    // Gọi hàm DAO để lấy địa chỉ hình ảnh dựa trên mã sản phẩm
	    String pathImage = dssp.getAnhSanPham(maSP);
	    
	    // Xóa hình ảnh trước đó (nếu có)
	    lblHinhAnh.setIcon(null);
	    ImageIcon imic= new ImageIcon(pathImage, null);
	    lblHinhAnh.setIcon(imic);


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
	
	public void xoaTrang() {
		txtMaSanPham.setText("");
		txtTenSanPham.setText("");
		txtGiaBan.setText("");
		txtGiaNhap.setText("");
		txtSoLuongBan.setText("");
		cbbChatLieu.setSelectedIndex(0);
		cbbDanhMuc.setSelectedIndex(0);
		cbbKichCo.setSelectedIndex(0);
		cbbMauSac.setSelectedIndex(0);
		cbbNhaCungCap.setSelectedIndex(0);
		spinnerSoLuongTon.setValue(0);
		lblHinhAnh.setIcon(null);
		
	}

	
	public void xoaAction() {
		int row = sanPhamTable.getSelectedRow();
		if (row != -1) {
			String ma = (String) sanPhamTable.getValueAt(row, 0).toString() + "";
			int message = JOptionPane.showConfirmDialog(this, "Chắc chắn muốn xóa không", "Chú ý", JOptionPane.YES_NO_OPTION);
			
			if(message == JOptionPane.YES_OPTION ) {
				dataModel.removeRow(row);

			} 
		} else {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng để xóa");
		}
	}
	
	public boolean kiemTraDuLieu() {
		String tenSP= txtTenSanPham.getText().trim();
		String giaBan=txtGiaBan.getText().trim();
		String giaNhap=txtGiaNhap.getText().trim();
		
		if(tenSP.length()==0) {
			JOptionPane.showMessageDialog(null, "Tên sản phẩm không được để trống");
			txtTenSanPham.requestFocus();
			return false;
		} else if(giaBan.length()==0) {
			JOptionPane.showMessageDialog(null, "Giá bán không được để trống");
			txtGiaBan.requestFocus();
			return false;
		} else if(giaNhap.length()==0) {
			JOptionPane.showMessageDialog(null, "Giá bán không được để trống");
			return false;
//		} else if(!(tenSP.length()>0 && tenSP.matches("^[\\p{L}\\s]{2,}$\r\n"))) {
//			JOptionPane.showMessageDialog(null, "Tên sản phẩm phải nhiều hơn 2 từ và không chứa kỹ tự đặc biệt");
//			txtTenSanPham.requestFocus();
//			return false;
		} else if(!(giaBan.length() > 0 && giaBan.matches("^[1-9][0-9]{4,}$"))) {
			JOptionPane.showMessageDialog(null, "Nhập ít nhất 5 chữ số, không được nhập số 0 ở đầu");
			txtGiaBan.requestFocus();
			return false;
		} else if(!(giaNhap.length() > 0 && giaNhap.matches("^[1-9][0-9]{4,}$"))) {
			JOptionPane.showMessageDialog(null, "Nhập ít nhất 5 chữ số, không được nhập số 0 ở đầu");
			txtGiaNhap.requestFocus();
			return false;
		}
		return true;
	}
	
	public void chonAnh() {
		JFileChooser chooser = new JFileChooser();
        int returnValue = chooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            pathImage = selectedFile.getAbsolutePath();
            ImageIcon imageIcon = new ImageIcon(pathImage);
            lblHinhAnh.setIcon(imageIcon);
        }
	}
	
	public void luu() {
		String tenSP=txtTenSanPham.getText().trim().toString();
		float giaBan=Float.parseFloat((String)txtGiaBan.getText().trim());
		float giaNhap= Float.parseFloat((String)txtGiaNhap.getText().trim());
		int soLuongTon = (int) spinnerSoLuongTon.getValue();
		int soLuongBan=0;
		
		String tenDM= cbbDanhMuc.getSelectedItem().toString().trim();
		String tenMS = cbbMauSac.getSelectedItem().toString().trim();
		String tenCL= cbbChatLieu.getSelectedItem().toString().trim();
		String tenKC = cbbKichCo.getSelectedItem().toString().trim();
		
		String tenNCC = cbbNhaCungCap.getSelectedItem().toString().trim();
		List<NhaCungCap> lncc = dsncc.getListNhaCungCapTheoTenNCC(tenNCC);
		NhaCungCap nhaCungCap = new NhaCungCap(lncc.get(0).getMaNCC());

		
		SanPham sanPham=new SanPham(tenNCC, tenSP, giaNhap, giaBan, soLuongTon, soLuongBan, pathImage, tenDM, tenMS, tenCL, tenKC, nhaCungCap);
		
		if(kiemTraDuLieu()) {
			if(dssp.themSanPham(sanPham)) {
				String ketQua=dssp.layDuLieuCotSanPham();
//				Object[] row= {ketQua, sanPham.getTenSP(),sanPham.getGiaBan(),sanPham.getDanhMuc().getTenDM(), sanPham.getChatLieu().getTenCL(), sanPham.getMauSac().getTenMS(), sanPham.getKichCo().getTenKC(), sanPham.getSoLuongTon(), sanPham.getSoLuongBan(), sanPham.getNhaCungCap().getTenNCC(), sanPham.getGiaNhap()};
				Object[] row= {ketQua, sanPham.getTenSP(),sanPham.getGiaBan(),tenDM, tenCL, tenMS, tenKC, sanPham.getSoLuongTon(), sanPham.getSoLuongBan(), tenNCC, sanPham.getGiaNhap()};
				dataModel.addRow(row);
			}
			dataModel.fireTableDataChanged();
			xoaTrang();
		}
	}
	
	public void capNhatSanPham() {
		String maSP=txtMaSanPham.getText().trim();

	    //String pathImage = dssp.getAnhSanPham(maSP);
	   

	    String tenSP=txtTenSanPham.getText().trim().toString();
		float giaBan=Float.parseFloat((String)txtGiaBan.getText().trim());
		float giaNhap= Float.parseFloat((String)txtGiaNhap.getText().trim());
		int soLuongTon = (int) spinnerSoLuongTon.getValue();
		int soLuongBan=0;
		
		String tenDM= cbbDanhMuc.getSelectedItem().toString().trim();
		String tenMS = cbbMauSac.getSelectedItem().toString().trim();
		String tenCL= cbbChatLieu.getSelectedItem().toString().trim();
		String tenKC = cbbKichCo.getSelectedItem().toString().trim();

		
		String tenNCC = (String) cbbNhaCungCap.getSelectedItem();
		List<NhaCungCap> lncc = dsncc.getListNhaCungCapTheoTenNCC(tenNCC);
		NhaCungCap nhaCungCap = new NhaCungCap(lncc.get(0).getMaNCC());

		
		SanPham sanPham=new SanPham(maSP, tenSP, giaNhap, giaBan, soLuongTon, soLuongBan, pathImage, tenDM, tenMS, tenCL, tenKC, nhaCungCap);
		
		if(kiemTraDuLieu()) {
			if(dssp.capNhatSanPham(sanPham)) {
				int row=sanPhamTable.getSelectedRow();
				dataModel.setValueAt(sanPham.getTenSP(),row,1);
				dataModel.setValueAt(sanPham.getGiaBan(),row,2);
				dataModel.setValueAt(sanPham.getDanhMuc(),row,3);
				dataModel.setValueAt(sanPham.getChatLieu(),row,4);
				dataModel.setValueAt(sanPham.getMauSac(),row,5);
				dataModel.setValueAt(sanPham.getKichCo(),row,6);
				dataModel.setValueAt(sanPham.getSoLuongTon(),row,7);
				dataModel.setValueAt(sanPham.getSoLuongBan(),row,8);
				dataModel.setValueAt(sanPham.getNhaCungCap().getTenNCC(),row,9);
				dataModel.setValueAt(sanPham.getGiaNhap(),row,10);
				xoaTrang();
			}
		}
	}
}
