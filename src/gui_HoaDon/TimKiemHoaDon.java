package gui_HoaDon;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.ChiTietHoaDon_Dao;
import dao.HoaDon_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import utils.Contains;
import utils.Function;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

public class TimKiemHoaDon extends JPanel implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField sDTKHtxt, tenNVtxt;
	private JLabel lblSDTKH, lblTenNV, lblTieuDeTBHD, lblTieuDeTBDH;
	private JButton btnTimKiem, btnXoaTrang, btnXemCT;
	private JTable donHangTB, hoaDonTB;
	private DefaultTableModel dmHoaDon, dmSanPham;
	private JScrollPane scPanel, scPanel2;
	private JTextField txtMaHoaDon;
	private JDateChooser dcsNgayLap;
	private JLabel lblNgayLap;
	private JLabel lblTnKhchHng;
	private JTextField txtTenKH;
	private HoaDon_DAO dshd;
	private ChiTietHoaDon_Dao listCTHD;

	/**
	 * Create the panel.
	 */
	public TimKiemHoaDon() {
		dshd = new HoaDon_DAO();
		listCTHD = new ChiTietHoaDon_Dao();
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		setBackground(Color.WHITE);
		setSize(utils.Contains.WIDTH_PANEL_CONTENT, utils.Contains.HEIGHT_PANEL_CONTENT);

		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnTimKiem.setBounds(1184, 69, 126, 38);
		add(btnTimKiem);

		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnXoaTrang.setBounds(1184, 117, 126, 38);
		add(btnXoaTrang);

		lblTieuDeTBHD = new JLabel("Hóa đơn");
		lblTieuDeTBHD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTieuDeTBHD.setBounds(8, 212, 73, 24);
		add(lblTieuDeTBHD);

		String[] columns2 = { "Mã hóa đơn", "Tên nhân viên", "Tên khách hàng", "Số điện thoại", "Ngày lập hóa đơn",
				"Tồng tiền" };
		dmHoaDon = new DefaultTableModel(columns2, 0);

		scPanel2 = new JScrollPane();
		hoaDonTB = new JTable(dmHoaDon);
		scPanel2 = new JScrollPane(hoaDonTB, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scPanel2.setViewportView(hoaDonTB);
		scPanel2.setBounds(8, 246, 1302, 203);
		add(scPanel2);

		String columns[] = { "Mã sản phẩm", "Tên sản phẩm", "Chất liệu", "Màu sắc", "Size", "Giá bán", "Số Lượng",
				"Thành tiền" };
		dmSanPham = new DefaultTableModel(columns, 0);

		scPanel = new JScrollPane();

		donHangTB = new JTable(dmSanPham);
		scPanel = new JScrollPane(donHangTB, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scPanel.setViewportView(donHangTB);
		scPanel.setBounds(10, 493, 1300, 291);
		add(scPanel);

		lblTieuDeTBDH = new JLabel("Chi tiết hóa đơn");
		lblTieuDeTBDH.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblTieuDeTBDH.setBounds(10, 459, 116, 24);
		add(lblTieuDeTBDH);

		JPanel pnTitle = new JPanel();
		pnTitle.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnTitle.setBounds(8, 10, 200, 49);
		add(pnTitle);
		pnTitle.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hóa Đơn");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 180, 30);
		pnTitle.add(lblNewLabel);

		JPanel pnFormNhap = new JPanel();
		pnFormNhap.setBorder(new TitledBorder(null, "Form Nhập Thông Tin Hóa Đơn", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pnFormNhap.setBounds(8, 69, 1166, 133);
		add(pnFormNhap);
		pnFormNhap.setLayout(null);

		lblSDTKH = new JLabel("Số điện thoại khách hàng :");
		lblSDTKH.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblSDTKH.setBounds(766, 27, 161, 24);
		pnFormNhap.add(lblSDTKH);

		sDTKHtxt = new JTextField();
		sDTKHtxt.setBounds(937, 27, 219, 24);
		pnFormNhap.add(sDTKHtxt);
		sDTKHtxt.setColumns(10);

		lblTenNV = new JLabel("Tên nhân viên :");
		lblTenNV.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblTenNV.setBounds(27, 73, 90, 24);
		pnFormNhap.add(lblTenNV);

		tenNVtxt = new JTextField();
		tenNVtxt.setBounds(141, 73, 209, 24);
		pnFormNhap.add(tenNVtxt);
		tenNVtxt.setColumns(10);

		JLabel lblMHan = new JLabel("Mã hóa đơn:");
		lblMHan.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblMHan.setBounds(27, 27, 90, 24);
		pnFormNhap.add(lblMHan);

		txtMaHoaDon = new JTextField();
		txtMaHoaDon.setColumns(10);
		txtMaHoaDon.setBounds(141, 28, 193, 24);
		pnFormNhap.add(txtMaHoaDon);

		dcsNgayLap = new JDateChooser();
		dcsNgayLap.setBounds(937, 73, 219, 24);
		pnFormNhap.add(dcsNgayLap);

		lblNgayLap = new JLabel("Ngày lập hóa đơn:");
		lblNgayLap.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNgayLap.setBounds(766, 73, 126, 24);
		pnFormNhap.add(lblNgayLap);

		lblTnKhchHng = new JLabel("Tên khách hàng:");
		lblTnKhchHng.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblTnKhchHng.setBounds(360, 27, 112, 24);
		pnFormNhap.add(lblTnKhchHng);

		txtTenKH = new JTextField();
		txtTenKH.setColumns(10);
		txtTenKH.setBounds(495, 28, 255, 24);
		pnFormNhap.add(txtTenKH);

		btnXemCT = new JButton("Xem chi tiết");
		btnXemCT.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnXemCT.setBounds(1184, 165, 126, 38);
		add(btnXemCT);

		updateHoaDonTable();
		hoaDonTB.addMouseListener(this);
		donHangTB.addMouseListener(this);
		btnTimKiem.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnXemCT.addActionListener(this);

	}

	public void updateHoaDonTable() {
		HoaDon_DAO ds = new HoaDon_DAO();
		for (HoaDon hd : ds.getListHoaDon()) {
			Object row[] = { hd.getMaHD(), hd.getNv().getTen(), hd.getKh().getHoTen(), hd.getKh().getSdt(),
					hd.getNgayLap(), ds.tinhTongTien(hd.getMaHD()) };
			dmHoaDon.addRow(row);
		}
		hoaDonTB.setModel(dmHoaDon);
	}

	public void updateSanPhamTable(String maHD) {
		ChiTietHoaDon_Dao ds = new ChiTietHoaDon_Dao();
		for (ChiTietHoaDon CTHD : ds.getListSanPhamTuHoaDon(maHD)) {
			Object row[] = { CTHD.getSp().getMaSP(), CTHD.getSp().getTenSP(), CTHD.getSp().getChatLieu(),
					CTHD.getSp().getMauSac(), CTHD.getSp().getKichCo(), CTHD.getDonGia(), CTHD.getSoLuong(),
					CTHD.getDonGia() * CTHD.getSoLuong() };
			dmSanPham.addRow(row);
		}
		donHangTB.setModel(dmSanPham);
	}
	
	private void removeAllRowsInSanPhamTable() {
	    int rowCount = dmSanPham.getRowCount();
	    if (rowCount > 0) {
	    	dmSanPham.getDataVector().removeAllElements(); // Xóa tất cả dữ liệu trong mô hình
	    	dmSanPham.fireTableDataChanged(); // Thông báo cho bảng cập nhật dữ liệu
	    }
	}

	public void xoaTrang() {
		txtMaHoaDon.setText("");
		txtTenKH.setText("");
		sDTKHtxt.setText("");
		tenNVtxt.setText("");
		dcsNgayLap.setDate(new Date());
		dmHoaDon.getDataVector().removeAllElements();
//		while(dmSanPham.getRowCount()>0) {
//		dmSanPham.removeRow(0);
//	}
		removeAllRowsInSanPhamTable();
		updateHoaDonTable();
	}

	private void timKiemAction() {

		String ma = txtMaHoaDon.getText().toString().trim();
		String tenKH = txtTenKH.getText().toString().trim();
		String sdt = sDTKHtxt.getText().toString().trim();
		String tenNV = tenNVtxt.getText().toString().trim();
		Date ngayLap = dcsNgayLap.getDate();
		List<HoaDon> dstk = dshd.timKiemHoaDon(ma, tenNV, tenKH, sdt, ngayLap);

		if (dstk.size() > 0) {
			dmHoaDon.getDataVector().removeAllElements();
			dmSanPham.getDataVector().removeAllElements();
			for (HoaDon hd : dstk) {
				Object row[] = { hd.getMaHD(), hd.getNv().getTen(), hd.getKh().getHoTen(), hd.getKh().getSdt(),
						hd.getNgayLap(), dshd.tinhTongTien(hd.getMaHD()) };
				dmHoaDon.addRow(row);
//				Cập nhật dữ liệu trên bảng 
				dmSanPham.fireTableDataChanged();
			}
		} else {
			int op = JOptionPane.showConfirmDialog(this, "Ban co muon thuc hien lai thao tac!",
					"Thong Bao loi nhap lieu", JOptionPane.YES_NO_OPTION);
			if (op == 0) {
				xoaTrang();
			} else {
				return;
			}
		}
	}
	
	public void xemChiTietAction(boolean isPrinted) {
		int row = hoaDonTB.getSelectedRow();
		
		if(row != -1) {
			String maHD = hoaDonTB.getValueAt(row, 0).toString().trim();
			HoaDon hoaDonTimDuoc =  dshd.getHoaDonTheoMa(maHD);
			if(hoaDonTB != null) {
				Function.xuatHoaDonPDF("sMaHD", hoaDonTimDuoc.getMaHD(), Contains.getPathOfReportFiles(), isPrinted);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn hóa đơn !");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnTimKiem)) {
			timKiemAction();
		} else if (o.equals(btnXoaTrang)) {
			xoaTrang();
		} else if(o.equals(btnXemCT)) {
			xemChiTietAction(false);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(hoaDonTB)) {
			int rowSelected = hoaDonTB.getSelectedRow();
			String maHD = hoaDonTB.getValueAt(rowSelected, 0).toString().trim();
			removeAllRowsInSanPhamTable();
			updateSanPhamTable(maHD);
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
}
