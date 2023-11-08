package gui_ThongKe;

import java.awt.Color;
import java.text.DecimalFormat;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import entity.HoaDon;
import utils.Function;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;

public class ThongKeDoanhThu extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnThongKe, btnXuatExcel;
	private ChartPanel chartPanel;
	private JComboBox cmbLoaiThongKe;
	private DefaultTableModel dataModel;
	private static JTable hoaDonTable;
	private JScrollPane scrPanel;
	private JPanel pnChart;
	private JDateChooser ngayBatDau, ngayKetThuc;
	private NhanVien_DAO dsnv;
	private HoaDon_DAO dsHD;
	private KhachHang_DAO dskh;
	private JTextField txtTongDoanhThu;
	private String[] columns;

	public ThongKeDoanhThu() {
		dsHD = new HoaDon_DAO();
		dskh = new KhachHang_DAO();
		dsnv = new NhanVien_DAO();
		setSize(utils.Contains.WIDTH_PANEL_CONTENT, utils.Contains.HEIGHT_PANEL_CONTENT);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(new Color(255, 255, 255));
		setLayout(null);

		columns = new String[] { "Mã hóa đơn", "Nhân viên lập", "Khách hàng", "Ngày lập", "Tổng tiền" };
		dataModel = new DefaultTableModel(columns, 0);
		hoaDonTable = new JTable(dataModel);
		scrPanel = new JScrollPane(hoaDonTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrPanel.setViewportView(hoaDonTable);
		scrPanel.setBounds(10, 186, 1300, 187);
		add(scrPanel);

		pnChart = new JPanel();
		pnChart.setBounds(10, 383, 1300, 401);
		add(pnChart);

		JPanel headerTitlePN = new JPanel();
		headerTitlePN.setBorder(new LineBorder(new Color(0, 0, 0)));
		headerTitlePN.setBounds(10, 10, 250, 37);
		add(headerTitlePN);
		headerTitlePN.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel titleHeader = new JLabel("Thống Kê Doanh Thu");
		titleHeader.setHorizontalAlignment(SwingConstants.CENTER);
		titleHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
		headerTitlePN.add(titleHeader);

		JPanel pnFormNhap = new JPanel();
		pnFormNhap.setBorder(new TitledBorder(null, "Form Nhập Thời Gian Cần Thống Kê", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pnFormNhap.setBounds(10, 57, 1147, 79);
		add(pnFormNhap);
		pnFormNhap.setLayout(null);

		JLabel thoiGianLB = new JLabel("Thời gian:");
		thoiGianLB.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		thoiGianLB.setBounds(99, 32, 70, 20);
		pnFormNhap.add(thoiGianLB);

		cmbLoaiThongKe = new JComboBox();
		cmbLoaiThongKe.setBounds(179, 33, 105, 20);
		pnFormNhap.add(cmbLoaiThongKe);
		cmbLoaiThongKe.addItem("Tùy chỉnh");
		cmbLoaiThongKe.addItem("Ngày hôm nay");
		cmbLoaiThongKe.addItem("Tháng này");
		cmbLoaiThongKe.addItem("Năm này");

		JLabel lblNgayBatDau = new JLabel("Ngày bắt đầu:");
		lblNgayBatDau.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNgayBatDau.setBounds(385, 32, 100, 20);
		pnFormNhap.add(lblNgayBatDau);

		ngayBatDau = new JDateChooser();
		ngayBatDau.setBounds(492, 31, 123, 21);
		pnFormNhap.add(ngayBatDau);

		JLabel lblNgyKtThc = new JLabel("Ngày kết thúc:");
		lblNgyKtThc.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNgyKtThc.setBounds(709, 32, 100, 20);
		pnFormNhap.add(lblNgyKtThc);

		ngayKetThuc = new JDateChooser();
		ngayKetThuc.setBounds(821, 31, 123, 21);
		pnFormNhap.add(ngayKetThuc);

		btnThongKe = new JButton("Thống kê");
		btnThongKe.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnThongKe.setBounds(1167, 82, 143, 30);
		add(btnThongKe);

		btnThongKe.addActionListener(this);
		
		JLabel lblTongDoanhThu = new JLabel("Tổng doanh thu:");
		lblTongDoanhThu.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTongDoanhThu.setBounds(922, 154, 104, 20);
		add(lblTongDoanhThu);
		
		txtTongDoanhThu = new JTextField();
		txtTongDoanhThu.setEditable(false);
		txtTongDoanhThu.setBounds(1039, 156, 118, 20);
		add(txtTongDoanhThu);
		txtTongDoanhThu.setColumns(10);
		
		btnXuatExcel = new JButton("Xuất Excel");
		btnXuatExcel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnXuatExcel.setBounds(10, 146, 143, 30);
		add(btnXuatExcel);
		btnXuatExcel.addActionListener(this);

		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
		}
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

	public void updateTable() {
		DecimalFormat currencyFormat = new DecimalFormat("###,###.##");
		String loaiThongKe = cmbLoaiThongKe.getSelectedItem().toString().trim();
		Date ngayBatDauD = ngayBatDau.getDate();
		Date ngayKetThucD = ngayKetThuc.getDate();

		List<HoaDon> dshdTimDuoc = dsHD.getDanhSachHoaDonTheoNgay(loaiThongKe, ngayBatDauD, ngayKetThucD);
		if (dshdTimDuoc.size() > 0) {
			for (HoaDon hoaDon : dshdTimDuoc) {
				Object rowData[] = { hoaDon.getMaHD(), hoaDon.getNv().getMaNV(), hoaDon.getKh().getMaKH(),
						hoaDon.getNgayLap(), currencyFormat.format(dsHD.tinhTongTien(hoaDon.getMaHD())) };
				dataModel.addRow(rowData);
			}
			dataModel.fireTableDataChanged();
		}else {
			JOptionPane.showMessageDialog(null, "Không tồn tại hóa đơn trong thời gian này!");
		}
	}
	
	public void updateTongDoanhThu() {
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
	
	public HashSet<Integer> getDanhSachThangCoTonTaiDoanhThu(String loaiThongKe, Date ngayBatDau,
			Date ngayKetThuc) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		List<HoaDon> dsTimDuoc = dsHD.getDanhSachHoaDonTheoNgay(loaiThongKe, ngayBatDau, ngayKetThuc);
		/**
		 *  Tạo một HashSet để lưu trữ các giá trị "maKH" duy nhất
		 */
		HashSet<Integer> uniqueMonths = new HashSet<>();
		if (dsTimDuoc.size() > 0) {
			for (HoaDon hoaDon : dsTimDuoc) {

				/**
				 * Xử lý chuỗi ngày lập của hóa đơn để lấy tháng
				 */
				
				int thang = Integer.parseInt(sdf.format(hoaDon.getNgayLap()).split("-")[1]);
				uniqueMonths.add(thang);
			}
		}
		return uniqueMonths;

	}
	
	/**
	 * 
	 * @param xLbl: Tiêu đề cho y label
	 * @return
	 */

	public CategoryDataset createDataset(String xLbl) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy HH:mm:ss");
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		String loaiThongKe = cmbLoaiThongKe.getSelectedItem().toString().trim();
		Date ngayBatDauD = ngayBatDau.getDate();
		Date ngayKetThucD = ngayKetThuc.getDate();
		List<HoaDon> dsHDTimDuoc = dsHD.getDanhSachHoaDonTheoNgay(loaiThongKe, ngayBatDauD, ngayKetThucD);
		double doanhThu;
		if(loaiThongKe.equalsIgnoreCase("Năm này")) {
			HashSet<Integer> dsThang = getDanhSachThangCoTonTaiDoanhThu(loaiThongKe, ngayBatDauD, ngayKetThucD);
			if(dsThang.size()>0) {
				for (Integer integer : dsThang) {
					int thang = integer;
					doanhThu = dsHD.tinhDoanhThuTheoThang(thang);
					data.addValue(doanhThu, xLbl, integer);
				}
			}
		}
		else if(loaiThongKe.equalsIgnoreCase("Ngày hôm nay")) {
			if(dsHDTimDuoc.size()>0) {
				for (HoaDon hoaDon : dsHDTimDuoc) {
					doanhThu = dsHD.tinhTongTien(hoaDon.getMaHD());
					data.addValue(doanhThu, xLbl, sdf.format(hoaDon.getNgayLap()).split(" ")[1]);
				}
			}
		}
		else if (dsHDTimDuoc.size() > 0) {
			for (HoaDon hoaDon : dsHDTimDuoc) {
				doanhThu = dsHD.tinhTongTien(hoaDon.getMaHD());
				data.addValue(doanhThu, xLbl, hoaDon.getNgayLap());
			}
		}
		return data;
	}

	public JFreeChart createChart(String xLbl) {
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
					"Doanh thu", createDataset(xLbl), PlotOrientation.VERTICAL, true, false, false);

		}
		/**
		 * Tạo FreeChart cho thống kê doanh thu tháng này
		 */
		else if (loaiTK.equalsIgnoreCase("Tháng này")) {
			barChart = ChartFactory.createBarChart(
					"BIỂU ĐỒ DOANH THU THÁNG " + date.split("-")[1] + " NĂM " + date.split("-")[2], xLbl, "Doanh thu",
					createDataset(xLbl), PlotOrientation.VERTICAL, true, false, false);
		}
		/**
		 * Tạo FreeChart cho thống kê doanh thu năm nay
		 */
		else if (loaiTK.equalsIgnoreCase("Năm này")) {
			barChart = ChartFactory.createBarChart("BIỂU ĐỒ DOANH THU NĂM " + date.split("-")[2], xLbl, "Doanh thu",
					createDataset(xLbl), PlotOrientation.VERTICAL, true, false, false);

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
						xLbl, "Doanh thu", createDataset(xLbl), PlotOrientation.VERTICAL, true, false,
						false);
			}
		}

		return barChart;
	}

	public void thongKeAction() {
		String loaiTK = cmbLoaiThongKe.getSelectedItem().toString();
		if (pnChart.isAncestorOf(chartPanel)) {
			pnChart.remove(chartPanel);
		}
		String xlbl = null;
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
					xlbl = "Giờ";

				} else if (thangBatDau != thangKetThuc && namBatDau == namKetThuc) {
					typeOfDatepart = "month";
					xlbl = "Ngày";
				} else {
					typeOfDatepart = "year";
					xlbl = "Tháng";
				}
			}
		} else if (loaiTK.equalsIgnoreCase("Ngày hôm nay")) {
			typeOfDatepart = "hour";
			xlbl = "Giờ";
		} else if (loaiTK.equalsIgnoreCase("Tháng này")) {
			typeOfDatepart = "day";
			xlbl = "Ngày";
		} else if (loaiTK.equalsIgnoreCase("Năm này")) {
			typeOfDatepart = "month";
			xlbl = "Tháng";
		}
		if(createChart(xlbl) == null) {
			JOptionPane.showMessageDialog(null, "Tạo biểu đồ thất bại!");
			return;
		}
		chartPanel = new ChartPanel(createChart(xlbl));
		chartPanel.setBounds(0, 0, 1300, 400);
		pnChart.add(chartPanel);

		pnChart.revalidate();
		pnChart.repaint();
	}

	public void refreshPanel(JPanel panel) {
		panel.validate();
		panel.repaint();
	}

	public void XoaHetDuLieuTrenTableModel() {
		DefaultTableModel dm = (DefaultTableModel) hoaDonTable.getModel();
		dm.getDataVector().removeAllElements();
		hoaDonTable.validate();
		hoaDonTable.repaint();
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThongKe)) {
			if (dataModel.getRowCount() > 0) {
				XoaHetDuLieuTrenTableModel();
			}
			updateTable();
			updateTongDoanhThu();
			thongKeAction();
		}else if(o.equals(btnXuatExcel)) {
			if(Function.xuatExcel("ThongKeDoanhThu", "Bao Cao", columns, dataModel)) {
				JOptionPane.showMessageDialog(null, "Xuất file excel thành công!");
			};
		}
	}
}
