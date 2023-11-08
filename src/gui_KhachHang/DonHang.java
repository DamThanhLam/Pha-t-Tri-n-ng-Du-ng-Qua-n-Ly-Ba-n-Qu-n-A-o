package gui_KhachHang;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.ChiTietDonHang_DAO;
import dao.DonHang_DAO;
import dao.HoaDon_DAO;
import entity.ChiTietDonHang;
//import entity.DonHang;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;


import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class DonHang extends JPanel implements MouseListener, ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel pnTimKiemDatHang,pnThanhToan;
	private JLabel lblTimKiemDonDatHang,lblMaDonHang,lblTenNhanVien,lblTenKhachHang,lblNgayDat,lblThanhToan,lblTongTien,lblTienKhachDua,
	lblTienTraLai,lblDonViTongTien,lblDonViTienTraLai,lblDonViTienKhachDua,lblTieuDe1,lblTieuDe2,lblMaKhuyenMai;
	private JButton btnThanhToan,btnMuaThem,btnTimKiem,btnOK;
	private JTextField txtTenNhanVien,txtTenKhachHang,txtDiaChi,txtTongTien,txtTienKhachDua,txtTienTraLai,txtMaKM;
	private JCheckBox checkBoxXuatHoaDon;
	private JTable donHangTable, chiTietDonHangTable;
	private DefaultTableModel dataModel,dataModelNew;
	private JScrollPane scPanel,scrollPaneNew;
	private JDateChooser dateChooserNgayDat;
	private JComboBox cbbMaDonHang;
	private DonHang_DAO dsdh;
	private ChiTietDonHang_DAO dsctdh;
	private JButton btnXoaTrang;
	private HoaDon_DAO dshd;
	/**
	 * Create the panel.
	 */
	public DonHang() {
		
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dsdh=new DonHang_DAO();
		dsctdh=new ChiTietDonHang_DAO();
		dshd=new HoaDon_DAO();
		setLayout(null);
		setBackground(Color.WHITE);
		setBounds(0, 0, 1332, 797);
		
		pnTimKiemDatHang = new JPanel();
		pnTimKiemDatHang.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnTimKiemDatHang.setBackground(new Color(255, 255, 255));
		pnTimKiemDatHang.setBounds(10, 21, 650, 175);
		add(pnTimKiemDatHang);
		pnTimKiemDatHang.setLayout(null);
		
		lblTimKiemDonDatHang = new JLabel("Tìm kiếm đơn hàng");
		lblTimKiemDonDatHang.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTimKiemDonDatHang.setBounds(214, 10, 208, 34);
		pnTimKiemDatHang.add(lblTimKiemDonDatHang);
		
		lblMaDonHang = new JLabel("Mã đơn hàng");
		lblMaDonHang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMaDonHang.setBounds(10, 54, 127, 34);
		pnTimKiemDatHang.add(lblMaDonHang);
		
		lblTenNhanVien = new JLabel("Tên nhân viên");
		lblTenNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTenNhanVien.setBounds(10, 98, 127, 34);
		pnTimKiemDatHang.add(lblTenNhanVien);
		
		lblTenKhachHang = new JLabel("Tên khách hàng");
		lblTenKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTenKhachHang.setBounds(339, 54, 127, 34);
		pnTimKiemDatHang.add(lblTenKhachHang);
		
		lblNgayDat = new JLabel("Ngày đặt");
		lblNgayDat.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNgayDat.setBounds(339, 101, 88, 34);
		pnTimKiemDatHang.add(lblNgayDat);
		
		txtTenNhanVien = new JTextField();
		txtTenNhanVien.setColumns(10);
		txtTenNhanVien.setBounds(126, 103, 192, 29);
		pnTimKiemDatHang.add(txtTenNhanVien);
		
		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setColumns(10);
		txtTenKhachHang.setBounds(476, 59, 163, 29);
		pnTimKiemDatHang.add(txtTenKhachHang);
		
//		txtDiaChi = new JTextField();
//		txtDiaChi.setColumns(10);
//		txtDiaChi.setBounds(464, 101, 163, 29);
//		pnTimKiemDatHang.add(txtDiaChi);
		dateChooserNgayDat = new JDateChooser();
		dateChooserNgayDat.setBounds(476, 101, 163, 29);
		pnTimKiemDatHang.add(dateChooserNgayDat);
		
		btnTimKiem = new JButton("Tìm kiếm");
		//btnTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		//btnTimKiem.setBackground(new Color(255, 0, 138));
		btnTimKiem.setBounds(464, 134, 134, 34);
		pnTimKiemDatHang.add(btnTimKiem);
		
		pnThanhToan = new JPanel();
		pnThanhToan.setLayout(null);
		pnThanhToan.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnThanhToan.setBackground(Color.WHITE);
		pnThanhToan.setBounds(712, 21, 595, 168);
		add(pnThanhToan);
		
		lblThanhToan = new JLabel("Thanh toán");
		lblThanhToan.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblThanhToan.setBounds(274, 10, 105, 34);
		pnThanhToan.add(lblThanhToan);
		
		lblTongTien = new JLabel("Tổng tiền");
		lblTongTien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTongTien.setBounds(22, 42, 127, 34);
		pnThanhToan.add(lblTongTien);
		
		lblTienKhachDua = new JLabel("Tiền khách đưa");
		lblTienKhachDua.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTienKhachDua.setBounds(22, 86, 127, 34);
		pnThanhToan.add(lblTienKhachDua);
		
		lblTienTraLai = new JLabel("Tiền trả lại");
		lblTienTraLai.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTienTraLai.setBounds(22, 124, 127, 34);
		pnThanhToan.add(lblTienTraLai);
		
		btnThanhToan = new JButton("Thanh toán");
		//btnThanhToan.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnThanhToan.setBounds(439, 112, 134, 34);
		//btnThanhToan.setBackground(Color.decode("#FF008A"));
		pnThanhToan.add(btnThanhToan);
		
		checkBoxXuatHoaDon = new JCheckBox("Xuất hóa đơn");
		checkBoxXuatHoaDon.setBounds(439, 66, 134, 26);
		pnThanhToan.add(checkBoxXuatHoaDon);
		
		txtTongTien = new JTextField();
		txtTongTien.setColumns(10);
		txtTongTien.setBounds(159, 47, 120, 29);
		pnThanhToan.add(txtTongTien);
		txtTongTien.setEditable(false);
		
		txtTienKhachDua = new JTextField();
		txtTienKhachDua.setColumns(10);
		txtTienKhachDua.setBounds(159, 86, 120, 29);
		pnThanhToan.add(txtTienKhachDua);
		
		txtTienTraLai = new JTextField();
		txtTienTraLai.setColumns(10);
		txtTienTraLai.setBounds(159, 124, 120, 29);
		pnThanhToan.add(txtTienTraLai);
		txtTienTraLai.setEditable(false);
		
		lblDonViTongTien = new JLabel("VNĐ");
		lblDonViTongTien.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblDonViTongTien.setBounds(284, 54, 45, 26);
		pnThanhToan.add(lblDonViTongTien);
		
		lblDonViTienTraLai = new JLabel("VNĐ");
		lblDonViTienTraLai.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblDonViTienTraLai.setBounds(284, 128, 45, 26);
		pnThanhToan.add(lblDonViTienTraLai);
		
		lblDonViTienKhachDua = new JLabel("VNĐ");
		lblDonViTienKhachDua.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblDonViTienKhachDua.setBounds(284, 90, 45, 26);
		pnThanhToan.add(lblDonViTienKhachDua);
		
		lblTieuDe1 = new JLabel("Danh sách đơn đặt hàng");
		lblTieuDe1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblTieuDe1.setBounds(521, 206, 295, 41);
		add(lblTieuDe1);
		
		String columns[] = { "Mã đơn hàng","Tên nhân viên","Tên khách hàng","Số điện thoại","Địa chỉ","Ngày lập","Tổng tiền"};
		dataModel = new DefaultTableModel(columns, 0);

		scPanel = new JScrollPane();

		donHangTable = new JTable(dataModel);
		scPanel = new JScrollPane(donHangTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scPanel.setViewportView(donHangTable);
		scPanel.setBounds(10, 245, 1298, 234);
		add(scPanel);
		
		String[] columnsNew = {"Mã sản phẩm","Tên sản phẩm","Danh mục","Màu sắc","Chất liệu","Kích cỡ", "Nhà cung cấp","Số lượng","Đơn giá"};
		dataModelNew = new DefaultTableModel(columnsNew, 0);
		chiTietDonHangTable = new JTable(dataModelNew);
		scrollPaneNew = new JScrollPane(chiTietDonHangTable);
		scrollPaneNew.setBounds(10, 550, 1298, 237);
		add(scrollPaneNew);
		
		lblTieuDe2 = new JLabel("Chi tiết đơn hàng");
		lblTieuDe2.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblTieuDe2.setBounds(574, 499, 193, 41);
		add(lblTieuDe2);
		
//		btnMuaThem = new JButton("Mua thêm");
//		btnMuaThem.setBounds(993, 199, 134, 34);
//		btnMuaThem.setBackground(Color.decode("#FF008A"));
//		add(btnMuaThem);
//		btnMuaThem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		utils.Format.setButtonEvent(btnThanhToan,btnTimKiem);
		
		btnOK = new JButton("OK");
		btnOK.setBounds(344, 82, 52, 34);
		pnThanhToan.add(btnOK);
		
		cbbMaDonHang = new JComboBox();
		cbbMaDonHang.setBounds(126, 59, 192, 29);
		pnTimKiemDatHang.add(cbbMaDonHang);
		cbbMaDonHang.addItem("Tất cả");
		
		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setBounds(271, 134, 134, 34);
		pnTimKiemDatHang.add(btnXoaTrang);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.setBounds(1153, 199, 134, 34);
		add(btnXoa);
		
		lblMaKhuyenMai = new JLabel("Mã khuyến mãi");
		lblMaKhuyenMai.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMaKhuyenMai.setBounds(831, 201, 127, 34);
		add(lblMaKhuyenMai);
		
		txtMaKM = new JTextField();
		txtMaKM.setColumns(10);
		txtMaKM.setBounds(959, 199, 120, 29);
		add(txtMaKM);
		
		docDuLieuTuDatabase();
		updateCbbMaDonHang();
		donHangTable.addMouseListener(this);
		btnOK.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		
	}
	
	public void docDuLieuTuDatabase() {
		List<String[]> donHang=dsdh.getListDonHangChoGui2();
		for (String[] row : donHang) {
            dataModel.addRow(row);
        }
	}

	public void updateCbbMaDonHang() {
		for(entity.DonHang donHang : dsdh.getListDonHang()) {
			cbbMaDonHang.addItem(donHang.getMaDH()+"");
		}
	}
	
//	public void mouseClicked(MouseEvent e) {
//		int selectedRow=donHangTable.getSelectedRow();
//		String maDH=donHangTable.getValueAt(selectedRow, 0).toString()+"";
//		List<String[]> chiTietDonHang=dsctdh.getListChiTietDonHangraGUI(maDH);
//		for(String[] row : chiTietDonHang) {
//			xoaTrangTBChiTietDonHang();
//			dataModelNew.addRow(row);
//		}
//		
//
//	}
	@Override
	public void mouseClicked(MouseEvent e) {
	    int selectedRow = donHangTable.getSelectedRow();
	    String maDH = donHangTable.getValueAt(selectedRow, 0).toString() + "";

	    // Xóa toàn bộ dòng trong bảng chi tiết đơn hàng trước khi thêm dòng mới
	    int rowCount = dataModelNew.getRowCount();
	    for (int i = rowCount - 1; i >= 0; i--) {
	        dataModelNew.removeRow(i);
	    }

	    List<String[]> chiTietDonHang = dsctdh.getListChiTietDonHangraGUI(maDH);
	    for (String[] row : chiTietDonHang) {
	        dataModelNew.addRow(row); // Thêm dữ liệu mới vào bảng
	    }
	    
	    cbbMaDonHang.setSelectedItem((String) donHangTable.getValueAt(selectedRow, 0));
	    txtTenNhanVien.setText((String) donHangTable.getValueAt(selectedRow, 1));
	    txtTenKhachHang.setText((String) donHangTable.getValueAt(selectedRow, 2));
	  //  dateChooserNgayDat.setDateFormatString((String) donHangTable.getValueAt(selectedRow, 5));
	    txtTongTien.setText((String) donHangTable.getValueAt(selectedRow, 6));
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
	    String dateString =(String) donHangTable.getValueAt(selectedRow, 5);
	    Date date = null;
	    try {
	        date = sdf.parse(dateString);
	    } catch (ParseException e1) {
	        e1.printStackTrace();
	    }

	    if (date != null) {
	        dateChooserNgayDat.setDate(date);
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
	
	public void xoaTrangTBChiTietDonHang() {
		dataModelNew.getDataVector().removeAllElements();
	}
	
//	public void inHoaDon(Boolean inHoaDon) {
//		int row=donHangTable.getSelectedRow();
//		
//		if(row!=11) {
//			String maHD=(String) donHangTable.getValueAt(row, 0).toString();
//			List<HoaDon> listHoaDon=dsdh
//		}
//	}
	
	public void tinhTienTraLai() {
		if(txtTienTraLai.getText().toString() != null) {
			float soTienTraLai;
			float tongTien=Float.parseFloat(txtTongTien.getText().toString());
			float tienKhachDua=Float.parseFloat(txtTienKhachDua.getText().toString());
			soTienTraLai=tienKhachDua-tongTien;
			if(soTienTraLai>=0) {
				String sotienTraLaiString= Float.toString(soTienTraLai);
				txtTienTraLai.setText(sotienTraLaiString);
			} else {
				txtTienTraLai.setText("Chưa đủ");
			}
		} 
		
//		return soTienTraLai;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnOK)) {
			tinhTienTraLai();			
		}else if(o.equals(btnTimKiem)) {
			timKiem();
		}else if(o.equals(btnXoaTrang)) {
			xoaTrang();
		} else if(o.equals(btnThanhToan)) {
			thanhToan();
		}
	}
	
	public void timKiem() {
		String maDH=cbbMaDonHang.getSelectedItem().toString()+"";
		if(maDH.equals("Tất cả"))
			maDH="";
		String tenKH=txtTenKhachHang.getText().trim().toString()+"";
		String tenNV=txtTenNhanVien.getText().trim().toString()+"";
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
//        String strDate = dateFormat.format(dateChooserNgayDat.getDate());
		  String strDate ;

        if(dateChooserNgayDat.getDate()==null) {
        	strDate="";
        }else {
        	strDate=dateFormat.format(dateChooserNgayDat.getDate());
        }
        
        //System.out.println(strDate);
        List<entity.DonHang> listDonHang=dsdh.getListDonHangTheoDK(maDH, tenNV, tenKH, strDate);
        if(listDonHang.size()>0) {
        	xoaHetDuLieuTrenTableDonHang();
        	xoaHetDuLieuTrenTableChiTietDonHang();
//        	List<String[]> donHang=dsdh.getListDonHangChoGui2();
        	for (entity.DonHang donHang : listDonHang) {
                String[] row = new String[7];
                row[0] = donHang.getMaDH();
                row[1] = donHang.getNhanVien().getTen(); // Điều chỉnh theo cấu trúc của DonHang
                row[2] = donHang.getKhachHang().getHoTen(); // Điều chỉnh theo cấu trúc của DonHang
                row[3] = donHang.getKhachHang().getSdt(); // Điều chỉnh theo cấu trúc của KhachHang
                row[4] = donHang.getKhachHang().getDiaChi(); // Điều chỉnh theo cấu trúc của KhachHang
                row[5] = donHang.getNgayDat().toString(); // Điều chỉnh theo cấu trúc của DonHang
                float total = dsdh.calculateTotal(donHang.getMaDH()); // Gọi hàm calculateTotal để tính tổng
                row[6] = Float.toString(total);
                dataModel.addRow(row);
        	}
        }
	}
	
	public void xoaHetDuLieuTrenTableDonHang() {
		 DefaultTableModel model = (DefaultTableModel) donHangTable.getModel();
		    int rowCount = model.getRowCount();
		    for (int i = rowCount - 1; i >= 0; i--) {
		        model.removeRow(i);
		    }

	}

	public void xoaHetDuLieuTrenTableChiTietDonHang() {
		 DefaultTableModel model = (DefaultTableModel) chiTietDonHangTable.getModel();
		    int rowCount = model.getRowCount();
		    for (int i = rowCount - 1; i >= 0; i--) {
		        model.removeRow(i);
		    }

	}
	
	public void xoaTrang() {
		cbbMaDonHang.setSelectedItem("Tất cả");
		txtTenKhachHang.setText("");
		txtTenNhanVien.setText("");
		dateChooserNgayDat.setDate(null);
	}
	
	public void thanhToan() {
		
		String maDH=cbbMaDonHang.getSelectedItem().toString()+"";
		
		String tenKH=txtTenKhachHang.getText().trim().toString()+"";
		KhachHang khachHang=new KhachHang(null, tenKH, true, null, null);
		
		String tenNV=txtTenNhanVien.getText().trim().toString()+"";
		NhanVien nhanVien =new NhanVien(null, tenNV, null, null, null, null);
		
		String maKM=txtMaKM.getText().trim().toString()+"";
		
		HoaDon hoaDon=new HoaDon(maKM, nhanVien, khachHang, dateChooserNgayDat.getDate());
		dshd.themHoaDon(hoaDon, maKM);
		dsctdh.xoaChiTietDonHang(maDH);
		dsdh.xoaDonHang(maDH);
		
		int row=donHangTable.getSelectedRow();
		dataModel.removeRow(row);
		
		dataModelNew.getDataVector().removeAllElements();
		xoaTrang();

	}
}
