package gui_KhachHang;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.KhachHang_DAO;
import entity.KhachHang;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

public class TimKiemKhachHang extends JPanel implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable khachHangTB;
	private JTextField hoTenTXT, sdtTXT, diaChiTXT, txtMaKH;
	private JPanel titlePanel;
	private JLabel lblKhachHang, maKHLB, lblHoTen, lblGioiTinh, sdtLB, lblDiaChi;
	private JScrollPane scPanel;
	private JButton xoaTrangBtn, timKiemBtn;
	private JComboBox comboBox, maKhCB;
	private DefaultTableModel dataModel;
	private KhachHang_DAO dskh;
	private JPanel panel;

	public TimKiemKhachHang() {
		dskh = new KhachHang_DAO();

		setSize(utils.Contains.WIDTH_PANEL_CONTENT, utils.Contains.HEIGHT_PANEL_CONTENT);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(new Color(255, 255, 255));
		setLayout(null);

		titlePanel = new JPanel();
		titlePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		titlePanel.setBounds(10, 10, 156, 39);
		add(titlePanel);

		lblKhachHang = new JLabel("Khách Hàng");
		titlePanel.add(lblKhachHang);
		lblKhachHang.setHorizontalAlignment(SwingConstants.CENTER);
		lblKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		scPanel = new JScrollPane();
		scPanel.setBounds(114, 282, 746, 309);

		String columns[] = { "Mã khách hàng", "Họ tên", "Giới tính", "Số điện thoại", "Địa chỉ" };
		dataModel = new DefaultTableModel(columns, 0);

		khachHangTB = new JTable(dataModel);
		scPanel = new JScrollPane(khachHangTB, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scPanel.setViewportView(khachHangTB);
		scPanel.setBounds(10, 326, 1300, 377);
		add(scPanel);

		xoaTrangBtn = new JButton("Xóa Trắng");
		xoaTrangBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		xoaTrangBtn.setBounds(487, 229, 110, 30);
		add(xoaTrangBtn);

		timKiemBtn = new JButton("Tìm Kiếm");
		timKiemBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		timKiemBtn.setBounds(694, 229, 110, 30);
		add(timKiemBtn);
		xoaTrangBtn.addActionListener(this);
		timKiemBtn.addActionListener(this);

		khachHangTB.addMouseListener(this);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Form Nhập Thông Tin Khách Hàng", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel.setBounds(10, 59, 1300, 114);
		add(panel);
		panel.setLayout(null);

		maKHLB = new JLabel("Mã khách hàng");
		maKHLB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		maKHLB.setBounds(83, 44, 120, 20);
		panel.add(maKHLB);

		sdtLB = new JLabel("Số điện thoại:");
		sdtLB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sdtLB.setBounds(722, 47, 96, 20);
		panel.add(sdtLB);

		txtMaKH = new JTextField();
		txtMaKH.setBounds(213, 46, 96, 21);
		panel.add(txtMaKH);

		sdtTXT = new JTextField();
		sdtTXT.setBounds(828, 48, 138, 19);
		panel.add(sdtTXT);
		sdtTXT.setColumns(10);

		lblHoTen = new JLabel("Họ tên:");
		lblHoTen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHoTen.setBounds(347, 47, 52, 20);
		panel.add(lblHoTen);

		lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDiaChi.setBounds(999, 47, 52, 20);
		panel.add(lblDiaChi);

		hoTenTXT = new JTextField();
		hoTenTXT.setBounds(403, 47, 96, 19);
		panel.add(hoTenTXT);
		hoTenTXT.setColumns(10);

		diaChiTXT = new JTextField();
		diaChiTXT.setBounds(1061, 47, 156, 19);
		panel.add(diaChiTXT);
		diaChiTXT.setColumns(10);

		lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGioiTinh.setBounds(525, 47, 60, 20);
		panel.add(lblGioiTinh);

		comboBox = new JComboBox();
		comboBox.setBounds(595, 46, 96, 21);
		panel.add(comboBox);
		comboBox.addItem("Nam");
		comboBox.addItem("Nu");

		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		updateTableData();
	}

	public void updateTableData() {
		KhachHang_DAO ds = new KhachHang_DAO();
		String gt;
		for (KhachHang kh : ds.getListKhachHang()) {
			if (kh.isGioiTinh() == true) {
				gt = "Nam";
			} else {
				gt = "Nu";
			}
			Object[] row = { kh.getMaKH(), kh.getHoTen(), gt, kh.getSdt(), kh.getDiaChi() };
			dataModel.addRow(row);
		}
		khachHangTB.setModel(dataModel);
	}

	public void xoaTrang() {
		txtMaKH.setText("");
		hoTenTXT.setText("");
		comboBox.setSelectedItem("Nam");
		sdtTXT.setText("");
		diaChiTXT.setText("");
		hoTenTXT.requestFocus();
		dataModel.getDataVector().removeAllElements();
		updateTableData();
	}

	private void timKiemAction() {

		String ma = txtMaKH.getText().toString().trim();
		String ten = hoTenTXT.getText();
		boolean gioiTinh;
		if (comboBox.getSelectedItem().toString().equalsIgnoreCase("Nam")) {
			gioiTinh = true;
		} else {
			gioiTinh = false;
		}
		String soDienThoai = sdtTXT.getText().toString().trim();
		String diaChi = diaChiTXT.getText().toString().trim();

		List<KhachHang> dstk = dskh.timKiemKhachHang(ma, ten, gioiTinh, soDienThoai, diaChi);
		if (dstk.size() > 0) {
			dataModel.getDataVector().removeAllElements();
			String gt;
			for (KhachHang kh : dstk) {
				if (kh.isGioiTinh() == true) {
					gt = "Nam";
				} else {
					gt = "Nu";
				}
				Object[] row = { kh.getMaKH(), kh.getHoTen(), gt, kh.getSdt(), kh.getDiaChi() };
				dataModel.addRow(row);
			}
			khachHangTB.setModel(dataModel);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(xoaTrangBtn)) {
			xoaTrang();
		} else if (o.equals(timKiemBtn)) {
			timKiemAction();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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
