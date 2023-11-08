package gui_ThongKe;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.HashSet;

import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import utils.Function;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JTextField;

public class ThongKeChiTieuKhachHang extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnFormNhap, pnHeaderTitle, pnChart;
	private JComboBox cmbLoaiThongKe;
	private JLabel thoiGianLB, lblNgayBatDau, lblNgyKtThc, lblThngKChi;
	private JDateChooser ngayBatDau, ngayKetThuc;
	private JScrollPane scPanel;
	private DefaultTableModel dfKhachHang;
	private JTable tblKhachHang;
	private JButton btnThongKe, btnXutExcel;
	private NhanVien_DAO dsnv;
	private HoaDon_DAO dsHD;
	private KhachHang_DAO dskh;
	private ChartPanel chartPN;
	private String cols[];
	private JTextField txtTongDoanhThu;

	public ThongKeChiTieuKhachHang() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		dsHD = new HoaDon_DAO();
		dskh = new KhachHang_DAO();
		dsnv = new NhanVien_DAO();
		setBackground(Color.WHITE);
		setSize(utils.Contains.WIDTH_PANEL_CONTENT, utils.Contains.HEIGHT_PANEL_CONTENT);
		setLayout(null);

		pnFormNhap = new JPanel();
		pnFormNhap.setLayout(null);
		pnFormNhap.setBorder(new TitledBorder(null, "Form Nhập Thời Gian Cần Thống Kê", TitledBorder.LEADING,

				TitledBorder.TOP, null, null));
		pnFormNhap.setBounds(10, 57, 1176, 79);
		add(pnFormNhap);

		thoiGianLB = new JLabel("Thời gian:");
		thoiGianLB.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		thoiGianLB.setBounds(209, 33, 70, 20);
		pnFormNhap.add(thoiGianLB);

		cmbLoaiThongKe = new JComboBox();
		cmbLoaiThongKe.setBounds(289, 34, 105, 20);
		cmbLoaiThongKe.addItem("Tùy chỉnh");
		cmbLoaiThongKe.addItem("Ngày hôm nay");
		cmbLoaiThongKe.addItem("Tháng này");
		cmbLoaiThongKe.addItem("Năm này");
		pnFormNhap.add(cmbLoaiThongKe);

		lblNgayBatDau = new JLabel("Ngày bắt đầu:");
		lblNgayBatDau.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNgayBatDau.setBounds(495, 33, 100, 20);
		pnFormNhap.add(lblNgayBatDau);

		ngayBatDau = new JDateChooser();
		ngayBatDau.setBounds(602, 32, 123, 21);
		pnFormNhap.add(ngayBatDau);

		lblNgyKtThc = new JLabel("Ngày kết thúc:");
		lblNgyKtThc.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNgyKtThc.setBounds(819, 33, 100, 20);
		pnFormNhap.add(lblNgyKtThc);

		ngayKetThuc = new JDateChooser();
		ngayKetThuc.setBounds(931, 32, 123, 21);
		pnFormNhap.add(ngayKetThuc);

		pnHeaderTitle = new JPanel();
		pnHeaderTitle.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnHeaderTitle.setBounds(10, 10, 268, 37);
		add(pnHeaderTitle);
		pnHeaderTitle.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblThngKChi = new JLabel("Thống Kê Chi Tiêu Khách Hàng");
		lblThngKChi.setHorizontalAlignment(SwingConstants.CENTER);
		lblThngKChi.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnHeaderTitle.add(lblThngKChi);

		cols = new String[] { "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Địa chỉ", "Số hóa đơn",
				"Tổng tiền chi" };

		dfKhachHang = new DefaultTableModel(cols, 0);
		tblKhachHang = new JTable(dfKhachHang);

		scPanel = new JScrollPane(tblKhachHang, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scPanel.setBounds(10, 186, 1300, 155);
		add(scPanel);

		btnThongKe = new JButton("Thống kê");
		btnThongKe.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnThongKe.setBounds(1196, 84, 100, 30);
		add(btnThongKe);

		pnChart = new JPanel();
		pnChart.setLayout(null);
		pnChart.setBounds(10, 363, 1300, 421);
		add(pnChart);

		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		btnThongKe.addActionListener(this);

		btnXutExcel = new JButton("Xuất Excel");
		btnXutExcel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnXutExcel.setBounds(10, 146, 100, 30);
		add(btnXutExcel);

		btnXutExcel.addActionListener(this);
		
		JLabel lblTongDoanhThu = new JLabel("Tổng doanh thu:");
		lblTongDoanhThu.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTongDoanhThu.setBounds(951, 154, 104, 20);
		add(lblTongDoanhThu);
		
		txtTongDoanhThu = new JTextField();
		txtTongDoanhThu.setEditable(false);
		txtTongDoanhThu.setColumns(10);
		txtTongDoanhThu.setBounds(1068, 156, 118, 20);
		add(txtTongDoanhThu);
	}

	public boolean checkInput() {
		boolean checked = true;
		Date ngayBD = ngayBatDau.getDate();
		Date ngayKT = ngayKetThuc.getDate();
		if (ngayBD == null || ngayBD.after(new Date())) {
			JOptionPane.showMessageDialog(null, "Lỗi: Ngày bắt đầu phải trước hoặc là ngày hôm nay!");
			checked = false;
		} else if (ngayKT == null || ngayKT.before(ngayBatDau.getDate()) || ngayKT.after(new Date())) {
			JOptionPane.showMessageDialog(null, "Lỗi: Ngày kết thúc phải từ ngày bắt đầu đến ngày hôm nay!");
			checked = false;
		}
		return checked;
	}

	public HashSet<String> getDanhSachKhachHangTheoThoiGianThongKe(String loaiThongKe, Date ngayBatDau,
			Date ngayKetThuc) {
		List<HoaDon> dsTimDuoc = dsHD.getDanhSachHoaDonTheoNgay(loaiThongKe, ngayBatDau, ngayKetThuc);
		// Tạo một HashSet để lưu trữ các giá trị "maKH" duy nhất
		HashSet<String> uniqueMaKHs = new HashSet<>();
		if (dsTimDuoc.size() > 0) {

			for (HoaDon hoaDon : dsTimDuoc) {
				String maKH = hoaDon.getKh().getMaKH();
				uniqueMaKHs.add(maKH); // Thêm giá trị "maKH" vào HashSet
			}

		}
		return uniqueMaKHs;

	}

	public void updateTable() {
		DecimalFormat currencyFormat = new DecimalFormat("###,###.##");
		String loaiThongKe = cmbLoaiThongKe.getSelectedItem().toString().trim();
		Date ngayBatDauD = ngayBatDau.getDate();
		Date ngayKetThucD = ngayKetThuc.getDate();

		HashSet<String> dsMaKH = getDanhSachKhachHangTheoThoiGianThongKe(loaiThongKe, ngayBatDauD, ngayKetThucD);
		if (dsMaKH.size() > 0) {
			for (String maKH : dsMaKH) {
				KhachHang kh = dskh.getKhachHangTheoMa(maKH);
				Object rowData[] = { kh.getMaKH(), kh.getHoTen(), kh.getSdt(), kh.getDiaChi(),
						dsHD.getSoHoaDonTheoKhachHang(maKH, loaiThongKe, ngayBatDauD, ngayKetThucD),
						currencyFormat.format(
								dsHD.tinhTongSoTienChiCuaKhachHang(maKH, loaiThongKe, ngayBatDauD, ngayKetThucD)) };
				dfKhachHang.addRow(rowData);
			}
			dfKhachHang.fireTableDataChanged();
		}

	}
	
	public void updateTxtTongDoanhThu() {
		DecimalFormat currencyFormat = new DecimalFormat("###,###.##");
		String loaiThongKe = cmbLoaiThongKe.getSelectedItem().toString().trim();
		Date ngayBatDauD = ngayBatDau.getDate();
		Date ngayKetThucD = ngayKetThuc.getDate();
		double tongDoanhThu = 0;
		List<HoaDon> dshdTimDuoc = dsHD.getDanhSachHoaDonTheoNgay(loaiThongKe, ngayBatDauD, ngayKetThucD);
			for (HoaDon hoaDon : dshdTimDuoc) {
				tongDoanhThu += dsHD.tinhTongTien(hoaDon.getMaHD());
		}
		txtTongDoanhThu.setText(currencyFormat.format(tongDoanhThu));
	}

	public CategoryDataset createDataset(String typeOfDatepart, String xLbl) {
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		String loaiThongKe = cmbLoaiThongKe.getSelectedItem().toString().trim();
		Date ngayBatDauD = ngayBatDau.getDate();
		Date ngayKetThucD = ngayKetThuc.getDate();
		HashSet<String> dsMaKH = getDanhSachKhachHangTheoThoiGianThongKe(loaiThongKe, ngayBatDauD, ngayKetThucD);
		System.out.println(dsMaKH.size());
		if (dsMaKH.size() > 0) {
			for (String maKH : dsMaKH) {
				double tongTien = dsHD.tinhTongSoTienChiCuaKhachHang(maKH, loaiThongKe, ngayBatDauD, ngayKetThucD);
				data.addValue(tongTien, xLbl, maKH);
			}
		}
		return data;
	}

	public JFreeChart createChart(String typeOfDatepart, String xLbl) {
		SimpleDateFormat simpleDateFortmat = new SimpleDateFormat("dd-MM-yyyy");
		JFreeChart barChart = null;
		String loaiTK = cmbLoaiThongKe.getSelectedItem().toString();
		Date now = new Date();
		String date = simpleDateFortmat.format(now);
		/**
		 * Tạo biểu đồ cho thống kê theo ngày hôm nay
		 */
		if (loaiTK.equalsIgnoreCase("Ngày hôm nay")) {
			barChart = ChartFactory.createBarChart("BIỂU ĐỒ DOANH THU NGÀY " + simpleDateFortmat.format(now), xLbl,
					"Tổng tiền chi", createDataset(typeOfDatepart, xLbl), PlotOrientation.HORIZONTAL, true, false,
					false);

		}
		/**
		 * Tạo FreeChart cho thống kê doanh thu tháng này
		 */
		else if (loaiTK.equalsIgnoreCase("Tháng này")) {
			barChart = ChartFactory.createBarChart(
					"BIỂU ĐỒ DOANH THU THÁNG " + date.split("-")[1] + " NĂM " + date.split("-")[2], xLbl,
					"Tổng tiền chi", createDataset(typeOfDatepart, xLbl), PlotOrientation.HORIZONTAL, true, false,
					false);
		}
		/**
		 * Tạo FreeChart cho thống kê doanh thu năm nay
		 */
		else if (loaiTK.equalsIgnoreCase("Năm này")) {
			barChart = ChartFactory.createBarChart("BIỂU ĐỒ DOANH THU NĂM " + date.split("-")[2], xLbl, "Tổng tiền chi",
					createDataset(typeOfDatepart, xLbl), PlotOrientation.HORIZONTAL, true, false, false);

		}
		/**
		 * Tạo FreeChart cho thống kê doanh thu tùy chỉnh
		 */
		else if (loaiTK.equalsIgnoreCase("Tùy chỉnh")) {
			Date startDay = ngayBatDau.getDate();
			Date closeDay = ngayKetThuc.getDate();
			if (startDay != null && closeDay != null) {
				String ngayBatDau = simpleDateFortmat.format(startDay);
				String ngayKetThuc = simpleDateFortmat.format(closeDay);
				barChart = ChartFactory.createBarChart("BIỂU ĐỒ DOANH THU TỪ " + ngayBatDau + " ĐẾN " + ngayKetThuc,
						xLbl, "Tổng tiền chi", createDataset(typeOfDatepart, xLbl), PlotOrientation.HORIZONTAL, true,
						false, false);
			}
		}

		return barChart;
	}

	public void refreshPanel(JPanel panel) {
		panel.validate();
		panel.repaint();
	}

	public void XoaHetDuLieuTrenTableModel() {
		dfKhachHang.getDataVector().removeAllElements();
		dfKhachHang.fireTableDataChanged();
	}

	public void thongKeAction() {
		String loaiTK = cmbLoaiThongKe.getSelectedItem().toString();
		if (pnChart.isAncestorOf(chartPN)) {
			pnChart.remove(chartPN);
		}
		String xlbl = "Khách hàng";
		String typeOfDatepart = null;
		if (loaiTK.equalsIgnoreCase("Tùy chỉnh")) {
			if (checkInput()) {
				SimpleDateFormat simpleDateFortmat = new SimpleDateFormat("dd-MM-yyyy");
				Date startDay = ngayBatDau.getDate();
				Date closeDay = ngayKetThuc.getDate();
				String ngayBatDau = simpleDateFortmat.format(startDay);
				String ngayKetThuc = simpleDateFortmat.format(closeDay);

				int thangBatDau = Integer.parseInt(ngayBatDau.split("-")[1]);
				int thangKetThuc = Integer.parseInt(ngayKetThuc.split("-")[1]);
				int namBatDau = Integer.parseInt(ngayBatDau.split("-")[2]);
				int namKetThuc = Integer.parseInt(ngayKetThuc.split("-")[2]);

				if (thangBatDau == thangKetThuc && namBatDau == namKetThuc) {
					typeOfDatepart = "day";

				} else if (thangBatDau != thangKetThuc && namBatDau == namKetThuc) {
					typeOfDatepart = "month";

				} else {
					typeOfDatepart = "year";
				}
			}
		} else if (loaiTK.equalsIgnoreCase("Ngày hôm nay")) {
			typeOfDatepart = "hour";
		} else if (loaiTK.equalsIgnoreCase("Tháng này")) {
			typeOfDatepart = "day";
		} else if (loaiTK.equalsIgnoreCase("Năm này")) {
			typeOfDatepart = "month";
		}
		chartPN = new ChartPanel(createChart(typeOfDatepart, xlbl));
		chartPN.setBounds(0, 0, 1300, 421);
		pnChart.add(chartPN);

		pnChart.revalidate();
		pnChart.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThongKe)) {
			updateTable();
			thongKeAction();
			updateTxtTongDoanhThu();
		} else if (o.equals(btnXutExcel)) {
			Function.xuatExcel("ThongKeChiTieuKhachHang", "Bao Cao Chi Tieu Khach Hang", cols, dfKhachHang);
		}
	}
}
