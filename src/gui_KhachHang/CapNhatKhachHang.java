package gui_KhachHang;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.CaLam_DAO;
import dao.KhachHang_DAO;
import entity.CaLam;
import entity.KhachHang;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;

public class CapNhatKhachHang extends JPanel implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable khachHangTB;
	private JTextField maKHTXT, hoTenTXT, sdtTXT, diaChiTXT;
	private JPanel titlePanel;
	private JLabel lblKhachHang, maKHLB, lblHoTen, lblGioiTinh, sdtLB, lblDiaChi;
	private JScrollPane scPanel;
	private JButton themBtn, suaBtn, xoaTrangBtn, xoaBtn;
	private JComboBox comboBox;
	private DefaultTableModel dataModel;
	private KhachHang_DAO dskh;
	private JPanel pnFormNhap;

	public CapNhatKhachHang() {
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
		scPanel.setBounds(10, 326, 1300, 404);
		add(scPanel);

		themBtn = new JButton("Thêm");
		themBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		themBtn.setBounds(412, 222, 90, 30);
		add(themBtn);

		suaBtn = new JButton("Sửa");
		suaBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		suaBtn.setBounds(527, 222, 90, 30);
		add(suaBtn);

		xoaTrangBtn = new JButton("Xóa trắng");
		xoaTrangBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		xoaTrangBtn.setBounds(642, 222, 110, 30);
		add(xoaTrangBtn);

		xoaBtn = new JButton("Xóa");
		xoaBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		xoaBtn.setBounds(776, 222, 90, 30);
		add(xoaBtn);

		themBtn.addActionListener(this);
		xoaTrangBtn.addActionListener(this);
		xoaBtn.addActionListener(this);
		suaBtn.addActionListener(this);

		khachHangTB.addMouseListener(this);

		pnFormNhap = new JPanel();
		pnFormNhap.setBorder(new TitledBorder(null, "Form Nhập Thông Tin Khách Hàng", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pnFormNhap.setBounds(10, 60, 1300, 114);
		add(pnFormNhap);
		pnFormNhap.setLayout(null);

		maKHLB = new JLabel("Mã khách hàng:");
		maKHLB.setHorizontalAlignment(SwingConstants.CENTER);
		maKHLB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		maKHLB.setBounds(39, 44, 107, 27);
		pnFormNhap.add(maKHLB);

		sdtLB = new JLabel("Số điện thoại:");
		sdtLB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sdtLB.setBounds(708, 48, 96, 18);
		pnFormNhap.add(sdtLB);

		maKHTXT = new JTextField();
		maKHTXT.setBounds(156, 49, 104, 21);
		pnFormNhap.add(maKHTXT);
		maKHTXT.setColumns(10);
		maKHTXT.setEditable(false);

		sdtTXT = new JTextField();
		sdtTXT.setBounds(814, 49, 143, 21);
		pnFormNhap.add(sdtTXT);
		sdtTXT.setColumns(10);

		lblHoTen = new JLabel("Họ tên:");
		lblHoTen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHoTen.setBounds(287, 48, 58, 18);
		pnFormNhap.add(lblHoTen);

		hoTenTXT = new JTextField();
		hoTenTXT.setBounds(355, 49, 115, 21);
		pnFormNhap.add(hoTenTXT);
		hoTenTXT.setColumns(10);

		diaChiTXT = new JTextField();
		diaChiTXT.setBounds(1062, 49, 156, 21);
		pnFormNhap.add(diaChiTXT);
		diaChiTXT.setColumns(10);

		lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDiaChi.setBounds(984, 48, 69, 18);
		pnFormNhap.add(lblDiaChi);

		lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGioiTinh.setBounds(499, 48, 71, 18);
		pnFormNhap.add(lblGioiTinh);

		comboBox = new JComboBox();
		comboBox.setBounds(580, 49, 96, 21);
		pnFormNhap.add(comboBox);
		comboBox.addItem("Nam");
		comboBox.addItem("Nu");
		comboBox.setSelectedItem("Nam");
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		updateTableData();
	}

	private boolean checkInput() {
		boolean checkedInput = true;
		String tenKH = hoTenTXT.getText().toString().trim();
		String diaChi = diaChiTXT.getText().toString().trim();
		String sdt = sdtTXT.getText().toString().trim();
		if (tenKH.length() == 0 || (!(tenKH.matches("([A-Z][a-z]*\\s?)*")))) {
			JOptionPane.showMessageDialog(null,
					"Lỗi: Tên khách hàng không được rỗng và phải viết hoa chữ cái đầu mỗi từ!");
			checkedInput = false;
		} else if (diaChi.length() == 0 || (!(diaChi.matches("^[0-9]+\\s[A-Za-z0-9\\s,]+")))) {
			JOptionPane.showMessageDialog(null, "Lỗi: địa chỉ phải nhập theo 'Số Nhà Tên đường, Tên Quận(Huyện), Tên Thành Phố(Tỉnh)'!");
			checkedInput = false;
		} else if (sdt.length() == 0 || !(sdt.matches("^[0-9]{1,10}$"))) {
			JOptionPane.showConfirmDialog(null,
					"Số điện thoại không được rỗng, không quá 10 ký tự và không chứa các ký tự khác số!");
			checkedInput = false;
		}
		return checkedInput;
	}

	public KhachHang reverSPFromTextField() {
		String maKH = maKHTXT.getText().toString();
		String hoTen = hoTenTXT.getText().toString();
		String gt = comboBox.getSelectedItem().toString();
		boolean gioiTinh;
		if (gt == "Nam") {
			gioiTinh = true;
		} else {
			gioiTinh = false;
		}
		String sdt = sdtTXT.getText();
		String diaChi = diaChiTXT.getText();
		return new KhachHang(maKH, hoTen, gioiTinh, sdt, diaChi);
	}

	public void xoaHetDuLieuTrenTableModel() {
		dataModel.getDataVector().removeAllElements();
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
		maKHTXT.setText("");
		hoTenTXT.setText("");
		comboBox.setSelectedItem("Nam");
		sdtTXT.setText("");
		diaChiTXT.setText("");
		hoTenTXT.requestFocus();
	}

	public void themAction() {
		if(checkInput()) {
			String hoTen = hoTenTXT.getText().toString();
			boolean gioiTinh;
			if (comboBox.getSelectedItem().toString().equalsIgnoreCase("Nam")) {
				gioiTinh = true;
			} else {
				gioiTinh = false;
			}
			String sdt = sdtTXT.getText().toString();
			String dc = diaChiTXT.getText().toString();
			KhachHang kh = new KhachHang(hoTen, gioiTinh, sdt, dc);
			if(dskh.themKhachHang(kh)) {
				/**
				 * Get khách hàng vừa thêm vào database
				 */
				
				KhachHang khUpdated = dskh.getKhachHangVuaCapNhat();
				Object dataRow[] = { khUpdated.getMaKH(), khUpdated.getHoTen(), comboBox.getSelectedItem().toString(), khUpdated.getSdt(),
						khUpdated.getDiaChi() };
				dataModel.addRow(dataRow);
				dataModel.fireTableDataChanged();
				JOptionPane.showMessageDialog(null, "Thêm Thành Công!");
				xoaTrang();
			}
			
			
				
		}else {
			int op = JOptionPane.showConfirmDialog(this, "Ban co muon thuc hien lai thao tac!",
					"Thong Bao loi nhap lieu", JOptionPane.YES_NO_OPTION);
			if (op == 0) {
				xoaTrang();
			} else {
				return;
			}
		}
	}

	public void capNhatAction() {
		int row = khachHangTB.getSelectedRow();
		if (row >= 0) {
			KhachHang kh = reverSPFromTextField();
			if (dskh.capNhatKhachHang(kh)) {
				khachHangTB.setValueAt(hoTenTXT.getText().toString(), row, 1);
				khachHangTB.setValueAt(comboBox.getSelectedItem().toString(), row, 2);
				khachHangTB.setValueAt(sdtTXT.getText().toString(), row, 3);
				khachHangTB.setValueAt(diaChiTXT.getText().toString(), row, 4);
			} else {
				JOptionPane.showMessageDialog(this, "Loi: Trung lap so dien thoai!");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Ban phai chon ca lam can cap nhat!");
		}
	}

	public void xoaAction() {
		int row = khachHangTB.getSelectedRow();
		if (row != -1) {
			String maKH = khachHangTB.getValueAt(row, 0).toString();
			int message = JOptionPane.showConfirmDialog(this, "Chắc chắn muốn xóa không", "Chú ý",
					JOptionPane.YES_NO_OPTION);

			if (message == JOptionPane.YES_OPTION) {
				if (dskh.xoaKhachHang(maKH)) {
					dataModel.removeRow(row);

				} else {
					JOptionPane.showMessageDialog(null, "Xoa khong thanh cong!");
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(themBtn)) {
			themBtn.setText("Hủy");
			suaBtn.setText("Lưu");
		} else if (o.equals(xoaBtn)) {
			xoaAction();
			xoaTrang();
		} else if (o.equals(suaBtn)) {
			if (suaBtn.getText().toString().equals("Lưu")) {
				themAction();
				themBtn.setText("Thêm");
				suaBtn.setText("Sửa");
			} else {
				capNhatAction();
			}
		} else if (o.equals(xoaTrangBtn)) {
			xoaTrang();
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
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = khachHangTB.getSelectedRow();
		maKHTXT.setText(khachHangTB.getValueAt(row, 0).toString());
		hoTenTXT.setText(khachHangTB.getValueAt(row, 1).toString());
		comboBox.setSelectedItem(khachHangTB.getValueAt(row, 2).toString());
		sdtTXT.setText(khachHangTB.getValueAt(row, 3).toString());
		diaChiTXT.setText(khachHangTB.getValueAt(row, 4).toString());
	}

}
