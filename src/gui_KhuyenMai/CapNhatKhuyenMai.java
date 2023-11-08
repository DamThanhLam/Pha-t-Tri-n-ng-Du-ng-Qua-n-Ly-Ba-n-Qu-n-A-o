package gui_KhuyenMai;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.ChuongTrinhKhuyenMai_DAO;
import entity.ChuongTrinhKhuyenMai;
import utils.Function;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JButton;

public class CapNhatKhuyenMai extends JPanel implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMaKM;
	private JTextField txtTenKM;
	private JTextField txtGiaGiam;
	private JLabel lblTitleKhuyenMai, lblMKhuynMi, lblTnKhuynMi, lblGiGim, lblNgayBatDau, lblNgyKtThc, lblMT;
	private JPanel pnTitle, pnFormNhapThongTin;
	private JButton btnThem, btnSa, btnXa, btnXaTrng;
	private JTextArea txaMoTa;
	private JDateChooser ngayBatDau, ngayKetThuc;
	private JSpinner spnSoLuong;
	private DefaultTableModel dfKhuyenMai;
	private JTable tblKhuyenMai;
	private JLabel lblRegexMessege;
	private JTextField txtMaCaptcha;
	private ChuongTrinhKhuyenMai_DAO dsCTKM;

	public CapNhatKhuyenMai() {
		dsCTKM = new ChuongTrinhKhuyenMai_DAO();
		setSize(utils.Contains.WIDTH_PANEL_CONTENT, utils.Contains.HEIGHT_PANEL_CONTENT);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(new Color(255, 255, 255));
		setLayout(null);

		pnTitle = new JPanel();
		pnTitle.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnTitle.setBounds(10, 10, 276, 54);
		add(pnTitle);
		pnTitle.setLayout(null);

		lblTitleKhuyenMai = new JLabel("Cập nhật khuyến mãi");
		lblTitleKhuyenMai.setBounds(0, 0, 276, 54);
		pnTitle.add(lblTitleKhuyenMai);
		lblTitleKhuyenMai.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleKhuyenMai.setFont(new Font("Times New Roman", Font.BOLD, 20));

		pnFormNhapThongTin = new JPanel();
		pnFormNhapThongTin.setBorder(new TitledBorder(null, "Form Nhập Thông Tin Khuyến Mãi", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pnFormNhapThongTin.setBounds(10, 74, 1300, 160);
		add(pnFormNhapThongTin);
		pnFormNhapThongTin.setLayout(null);

		lblMKhuynMi = new JLabel("Mã Khuyến Mãi:");
		lblMKhuynMi.setBounds(10, 21, 105, 33);
		pnFormNhapThongTin.add(lblMKhuynMi);
		lblMKhuynMi.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		txtMaKM = new JTextField();
		txtMaKM.setBounds(125, 22, 88, 33);
		pnFormNhapThongTin.add(txtMaKM);
		txtMaKM.setEditable(false);
		txtMaKM.setColumns(10);

		lblTnKhuynMi = new JLabel("Tên Khuyến Mãi:");
		lblTnKhuynMi.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblTnKhuynMi.setBounds(235, 20, 105, 33);
		pnFormNhapThongTin.add(lblTnKhuynMi);

		txtTenKM = new JTextField();
		txtTenKM.setEditable(true);
		txtTenKM.setColumns(10);
		txtTenKM.setBounds(350, 21, 122, 33);
		pnFormNhapThongTin.add(txtTenKM);

		lblGiGim = new JLabel("Giá Giảm:");
		lblGiGim.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblGiGim.setBounds(724, 18, 64, 33);
		pnFormNhapThongTin.add(lblGiGim);

		txtGiaGiam = new JTextField();
		txtGiaGiam.setEditable(true);
		txtGiaGiam.setColumns(10);
		txtGiaGiam.setBounds(798, 20, 146, 33);
		pnFormNhapThongTin.add(txtGiaGiam);

		lblNgayBatDau = new JLabel("Ngày bắt đầu:");
		lblNgayBatDau.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNgayBatDau.setBounds(966, 25, 105, 13);
		pnFormNhapThongTin.add(lblNgayBatDau);

		ngayBatDau = new JDateChooser();
		ngayBatDau.setBounds(1104, 21, 116, 30);
		pnFormNhapThongTin.add(ngayBatDau);

		lblNgyKtThc = new JLabel("Ngày kết thúc:");
		lblNgyKtThc.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNgyKtThc.setBounds(966, 79, 105, 13);
		pnFormNhapThongTin.add(lblNgyKtThc);

		ngayKetThuc = new JDateChooser();
		ngayKetThuc.setBounds(1104, 75, 116, 30);
		pnFormNhapThongTin.add(ngayKetThuc);

		lblMT = new JLabel("Mô Tả:");
		lblMT.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblMT.setBounds(482, 69, 64, 33);
		pnFormNhapThongTin.add(lblMT);

		txaMoTa = new JTextArea();
		txaMoTa.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txaMoTa.setForeground(new Color(0, 0, 0));
		txaMoTa.setBounds(573, 73, 371, 51);
		pnFormNhapThongTin.add(txaMoTa);

		JLabel lblSLng = new JLabel("Số Lượng:");
		lblSLng.setBounds(10, 64, 68, 33);
		pnFormNhapThongTin.add(lblSLng);
		lblSLng.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		spnSoLuong = new JSpinner();
		spnSoLuong.setBounds(97, 65, 122, 33);
		pnFormNhapThongTin.add(spnSoLuong);

		lblRegexMessege = new JLabel("Nhập đầy đủ thông tin khuyến mãi");
		lblRegexMessege.setBounds(966, 137, 324, 13);
		pnFormNhapThongTin.add(lblRegexMessege);

		JLabel lblMCaptcha = new JLabel("Mã Captcha:");
		lblMCaptcha.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblMCaptcha.setBounds(482, 21, 77, 33);
		pnFormNhapThongTin.add(lblMCaptcha);

		txtMaCaptcha = new JTextField();
		txtMaCaptcha.setEditable(true);
		txtMaCaptcha.setColumns(10);
		txtMaCaptcha.setBounds(573, 22, 122, 33);
		pnFormNhapThongTin.add(txtMaCaptcha);
		String colKhuyenMai[] = { "Mã khuyến mãi", "Tên khuyến mãi", "Mô tả", "Ngày bắt đầu", "Ngày kết thúc",
				"Số Lượng", "Giá Giảm", "Mã Captcha" };

		dfKhuyenMai = new DefaultTableModel(colKhuyenMai, 0);

		tblKhuyenMai = new JTable(dfKhuyenMai);

		JScrollPane scKhuyenMai = new JScrollPane(tblKhuyenMai, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scKhuyenMai.setBounds(10, 330, 1300, 454);
		add(scKhuyenMai);

		btnThem = new JButton("Thêm");
		btnThem.setBounds(390, 265, 114, 31);
		add(btnThem);

		btnSa = new JButton("Sửa");
		btnSa.setBounds(536, 265, 114, 31);
		add(btnSa);

		btnXa = new JButton("Xóa");
		btnXa.setBounds(684, 265, 114, 31);
		add(btnXa);

		btnXaTrng = new JButton("Xóa Trắng");
		btnXaTrng.setBounds(836, 265, 114, 31);
		add(btnXaTrng);

		btnThem.addActionListener(this);
		btnSa.addActionListener(this);
		btnXaTrng.addActionListener(this);
		btnXa.addActionListener(this);
		tblKhuyenMai.addMouseListener(this);

		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		updateTableData();

	}

	private boolean checkInput() {
		boolean checkedInput = true;
		String tenKM = txtTenKM.getText().toString().trim();
		String moTa = txaMoTa.getText().toString().trim();
		Date ngayBD = ngayBatDau.getDate();
		Date ngayKT = ngayKetThuc.getDate();
		int soLuong = (int) spnSoLuong.getValue();
		String giaGiam = txtGiaGiam.getText().toString().trim();
		double giaGiamDouble = 0;
		if (giaGiam.equalsIgnoreCase("")) {
			lblRegexMessege.setText("Giá giảm không được để rỗng!");
			lblRegexMessege.setForeground(Color.red);
			checkedInput = false;
		} else {
//			Gán giá trị đã nhập cho textfield giá giảm
			giaGiamDouble = Double.parseDouble(giaGiam);
		}
		String maCaptcha = txtMaCaptcha.getText().toString().trim();
		if (tenKM.equalsIgnoreCase("") || !(tenKM.matches("^([A-Z][a-z]*\\\\s?)+$"))) {
			lblRegexMessege.setText("Tên chương trình khuyến mãi phải viết hoa chữ cái đầu mỗi từ!");
			lblRegexMessege.setForeground(Color.red);
			checkedInput = false;
		} else if (maCaptcha.equalsIgnoreCase("") || !(maCaptcha.matches("[A-Z]+"))) {
			lblRegexMessege.setText("Mã captcha phải là chuỗi ký tự in hoa!");
			lblRegexMessege.setForeground(Color.red);
			checkedInput = false;
		} else if (giaGiamDouble == 0 || !(giaGiam.matches("\\d+(\\.\\d)?"))) {
			lblRegexMessege.setText("Giá giảm phải lớn hơn 0 và là số");
			lblRegexMessege.setForeground(Color.red);
			checkedInput = false;
		} else if(ngayBD.compareTo(new Date())==-1) {
			lblRegexMessege.setText("Ngày bắt đầu khuyến mãi phải sau hoặc bắt đầu từ ngày hôm nay!");
			lblRegexMessege.setForeground(Color.red);
			checkedInput = false;
		}else if(ngayKT.compareTo(ngayBD)==-1) {
			lblRegexMessege.setText("Ngày kết thúc khuyến mãi phải sau ngày bắt đầu!");
			lblRegexMessege.setForeground(Color.red);
			checkedInput = false;
		}else if (moTa.length() == 0) {
			lblRegexMessege.setText("Mô tả không được đặt rỗng!");
			lblRegexMessege.setForeground(Color.red);
			checkedInput = false;
		} else if (soLuong == 0 || !(String.valueOf(soLuong).matches("[0-9]+"))) {
			lblRegexMessege.setText("Số lượng phải lớn hơn 0 và là số");
			lblRegexMessege.setForeground(Color.red);
			checkedInput = false;
		}
		return checkedInput;
	}

	private ChuongTrinhKhuyenMai reverSPFromTextField() {
		String maKM = txtMaKM.getText();
		String tenKM = txtTenKM.getText();
		String moTa = txaMoTa.getText();
		Date ngayBD = ngayBatDau.getDate();
		Date ngayKT = ngayKetThuc.getDate();
		int soLuong = (int) spnSoLuong.getValue();
		double giaGiam = Double.parseDouble(txtGiaGiam.getText());
		String maCap = txtMaCaptcha.getText();
		return new ChuongTrinhKhuyenMai(maKM, tenKM, moTa, ngayBD, ngayKT, soLuong, giaGiam, maCap);
	}

	private void xoaHetDuLieuTrenTableModel() {
		dfKhuyenMai.getDataVector().removeAllElements();
	}

	private void updateTableData() {
		ChuongTrinhKhuyenMai_DAO ds = new ChuongTrinhKhuyenMai_DAO();
		for (ChuongTrinhKhuyenMai ctkm : ds.getListChuongTrinhKhuyenMai()) {
			Object[] row = { ctkm.getMaKM(), ctkm.getTenKM(), ctkm.getMoTa(), ctkm.getNgayBatDau(),
					ctkm.getNgayKetThuc(), ctkm.getSoLuong(), ctkm.getGiaGiam(), ctkm.getMaCaptcha() };
			dfKhuyenMai.addRow(row);
		}
		tblKhuyenMai.setModel(dfKhuyenMai);
	}

	private void xoaTrang() {
		txtMaKM.setText("");
		txtTenKM.setText("");
		txaMoTa.setText("");
		spnSoLuong.setValue(0);
		txtGiaGiam.setText("");
		txtMaCaptcha.setText("");
		ngayBatDau.setDate(new Date());
		ngayKetThuc.setDate(new Date());
		lblRegexMessege.setText("Nhập đầy đủ thông tin khuyến mãi");
		lblRegexMessege.setForeground(Color.BLACK);
		txtTenKM.requestFocus();
	}

	private void themAction() {
		if (checkInput()) {
			String tenKM = txtTenKM.getText();
			String moTa = txaMoTa.getText();
			Date ngayBD = ngayBatDau.getDate();
			Date ngayKT = ngayKetThuc.getDate();
			int soLuong = (int) spnSoLuong.getValue();
			double giaGiam = Double.parseDouble(txtGiaGiam.getText());
			String maCap = txtMaCaptcha.getText();
			ChuongTrinhKhuyenMai ctkm = new ChuongTrinhKhuyenMai(tenKM, moTa, ngayBD, ngayKT, soLuong, giaGiam, maCap);
			try {
				dsCTKM.themChuongTrinhKhuyenMai(ctkm);
				ChuongTrinhKhuyenMai ctkmVuaThem = dsCTKM.getChuongTrinhKhuyenMaiVuaCapNhat();
				Object[] row = { ctkmVuaThem.getMaKM(), ctkmVuaThem.getTenKM(), ctkmVuaThem.getMoTa(),
						ctkmVuaThem.getNgayBatDau(), ctkmVuaThem.getNgayKetThuc(), ctkmVuaThem.getSoLuong(),
						ctkmVuaThem.getGiaGiam(), ctkmVuaThem.getMaCaptcha() };
				dfKhuyenMai.addRow(row);
				JOptionPane.showMessageDialog(null, "Thêm thành công!");
				xoaTrang();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		} else {
			int op = JOptionPane.showConfirmDialog(this, "Ban co muon thuc hien lai thao tac!", "Lỗi cú pháp!",
					JOptionPane.YES_NO_OPTION);
			if (op == 0) {
				xoaTrang();
			} else {
				return;
			}
		}

	}

	private void capNhatAction() {
		int row = tblKhuyenMai.getSelectedRow();
		if (row >= 0) {
			ChuongTrinhKhuyenMai ctkm = reverSPFromTextField();
			try {
				dsCTKM.capNhatChuongTrinhKhuyenMai(ctkm);
				tblKhuyenMai.setValueAt(txtTenKM.getText(), row, 1);
				tblKhuyenMai.setValueAt(txaMoTa.getText(), row, 2);
				tblKhuyenMai.setValueAt(ngayBatDau.getDate(), row, 3);
				tblKhuyenMai.setValueAt(ngayKetThuc.getDate(), row, 4);
				tblKhuyenMai.setValueAt(spnSoLuong.getValue(), row, 5);
				tblKhuyenMai.setValueAt(txtGiaGiam.getText(), row, 6);
				tblKhuyenMai.setValueAt(txtMaCaptcha.getText(), row, 7);
				JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(this, e.getMessage());
			}
		} else {
			int op = JOptionPane.showConfirmDialog(this, "Ban co muon thuc hien lai thao tac!",
					"Lỗi: Vui lòng chọn chương trình khuyến mãi cần cập nhật!", JOptionPane.YES_NO_OPTION);
			if (op == 0) {
				xoaTrang();
			} else {
				return;
			}
		}
	}

	private void xoaAction() {
		int row = tblKhuyenMai.getSelectedRow();
		if (row != -1) {
			String maKM = tblKhuyenMai.getValueAt(row, 0).toString();
			int message = JOptionPane.showConfirmDialog(this, "Chắc chắn muốn xóa không", "Chú ý",
					JOptionPane.YES_NO_OPTION);

			if (message == JOptionPane.YES_OPTION) {
				try {
					dsCTKM.xoaChuongTrinhKhuyenMai(maKM);
					dfKhuyenMai.removeRow(row);
					JOptionPane.showMessageDialog(null, "Xóa thành công!");
				} catch (SQLException e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e.getMessage());
				}

			} else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn chương trình khuyến mãi cần xóa!");
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			if (btnThem.getText().equalsIgnoreCase("Thêm")) {
				btnThem.setText("Hủy");
				btnSa.setText("Lưu");
			} else {
				btnThem.setText("Thêm");
				btnSa.setText("Sửa");
			}
		} else if (o.equals(btnSa)) {
			if (btnSa.getText().toString().equals("Lưu")) {
				themAction();
				btnThem.setText("Thêm");
				btnSa.setText("Sửa");
			} else {
				capNhatAction();
			}
		} else if (o.equals(btnXa)) {
			xoaAction();
			xoaTrang();
		} else if (o.equals(btnXaTrng)) {
			xoaTrang();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tblKhuyenMai.getSelectedRow();
		txtMaKM.setText(tblKhuyenMai.getValueAt(row, 0).toString());
		txtTenKM.setText(tblKhuyenMai.getValueAt(row, 1).toString());
		txaMoTa.setText(tblKhuyenMai.getValueAt(row, 2).toString());
		ngayBatDau.setDate((Date) tblKhuyenMai.getValueAt(row, 3));
		ngayKetThuc.setDate((Date) tblKhuyenMai.getValueAt(row, 4));
		spnSoLuong.setValue(tblKhuyenMai.getValueAt(row, 5));
		txtGiaGiam.setText(tblKhuyenMai.getValueAt(row, 6).toString());
		txtMaCaptcha.setText(tblKhuyenMai.getValueAt(row, 7).toString());
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
