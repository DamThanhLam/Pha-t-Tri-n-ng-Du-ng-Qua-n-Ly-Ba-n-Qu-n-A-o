package gui_NhaCungCap;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.KhachHang_DAO;
import dao.NhaCungCap_DAO;
import entity.KhachHang;
import entity.NhaCungCap;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CapNhatNhaCungCap extends JPanel implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNhCungCp;
	private JTextField txtMaNhaCungCap;
	private JTextField txtTenNhaCungCap;
	private JTextField txtSoDienThoai;
	private JTextField txtEmail;
	private JTextField txtDiaChi;
	private JTable tblNhaCungCap;
	private DefaultTableModel dataModel;
	private JScrollPane scrTableNCC;
	private NhaCungCap_DAO dsNCC;
	private JButton btnThem, btnSa, btnXa, btnXaTrng;
	private JLabel lblRegexMessege;

	public CapNhatNhaCungCap() {
		dsNCC = new NhaCungCap_DAO();
		setSize(utils.Contains.WIDTH_PANEL_CONTENT, utils.Contains.HEIGHT_PANEL_CONTENT);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(new Color(255, 255, 255));
		setLayout(null);

		JPanel pnlTitle = new JPanel();
		pnlTitle.setBounds(10, 10, 211, 48);
		add(pnlTitle);
		pnlTitle.setLayout(null);

		txtNhCungCp = new JTextField();
		txtNhCungCp.setHorizontalAlignment(SwingConstants.CENTER);
		txtNhCungCp.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtNhCungCp.setText("Nhà Cung Cấp");
		txtNhCungCp.setBounds(0, 0, 211, 48);
		pnlTitle.add(txtNhCungCp);
		txtNhCungCp.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new TitledBorder(null, "Form Nhập Thông Tin Nhà Cung Cấp", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(10, 85, 1300, 156);
		add(panel);
		panel.setLayout(null);

		JLabel lblMaNhaCungCap = new JLabel("Mã nhà cung cấp:");
		lblMaNhaCungCap.setBounds(209, 24, 105, 20);
		panel.add(lblMaNhaCungCap);
		lblMaNhaCungCap.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		txtMaNhaCungCap = new JTextField();
		txtMaNhaCungCap.setBounds(334, 24, 122, 33);
		panel.add(txtMaNhaCungCap);
		txtMaNhaCungCap.setColumns(10);

		JLabel lblTnNhCung = new JLabel("Tên nhà cung cấp:");
		lblTnNhCung.setBounds(485, 24, 134, 20);
		panel.add(lblTnNhCung);
		lblTnNhCung.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		txtTenNhaCungCap = new JTextField();
		txtTenNhaCungCap.setBounds(629, 25, 122, 33);
		panel.add(txtTenNhaCungCap);
		txtTenNhaCungCap.setColumns(10);

		JLabel lblSinThoi = new JLabel("Số điện thoại");
		lblSinThoi.setBounds(787, 24, 105, 20);
		panel.add(lblSinThoi);
		lblSinThoi.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setBounds(902, 25, 156, 33);
		panel.add(txtSoDienThoai);
		txtSoDienThoai.setColumns(10);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblEmail.setBounds(209, 89, 105, 20);
		panel.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(334, 89, 257, 33);
		panel.add(txtEmail);

		JLabel lblaCh = new JLabel("Địa chỉ:");
		lblaCh.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblaCh.setBounds(629, 89, 97, 20);
		panel.add(lblaCh);

		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(752, 89, 304, 33);
		panel.add(txtDiaChi);

		btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnThem.setBounds(377, 302, 110, 30);
		add(btnThem);

		btnSa = new JButton("Sửa");
		btnSa.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnSa.setBounds(523, 302, 110, 30);
		add(btnSa);

		btnXa = new JButton("Xóa");
		btnXa.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnXa.setBounds(671, 302, 110, 30);
		add(btnXa);

		btnXaTrng = new JButton("Xóa Trắng");
		btnXaTrng.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnXaTrng.setBounds(823, 302, 110, 30);
		add(btnXaTrng);

		String colHeader[] = { "Mã nhà cung cấp", "Tên nhà cung cấp", "Số điện thoại", "Email", "Địa chỉ" };

		dataModel = new DefaultTableModel(colHeader, 0);
		tblNhaCungCap = new JTable(dataModel);
		scrTableNCC = new JScrollPane(tblNhaCungCap, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrTableNCC.setViewportView(tblNhaCungCap);
		scrTableNCC.setBounds(10, 394, 1300, 390);
		add(scrTableNCC);

		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		updateTableData();
		tblNhaCungCap.addMouseListener(this);
		btnThem.addActionListener(this);
		btnSa.addActionListener(this);
		btnXa.addActionListener(this);
		btnXaTrng.addActionListener(this);
		txtMaNhaCungCap.setEditable(false);

		lblRegexMessege = new JLabel("Nhập đầy đủ thông tin nhà cung cấp");
		lblRegexMessege.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		lblRegexMessege.setBounds(901, 133, 304, 13);
		panel.add(lblRegexMessege);
	}

	public boolean checkInput() {
		boolean checkedInput = true;
		String tenNCC = txtTenNhaCungCap.getText();
		String dc = txtDiaChi.getText();
		String email = txtEmail.getText();
		String sdt = txtSoDienThoai.getText();
		if (tenNCC.length() == 0 || (!(tenNCC.matches("([A-Z][a-z]*\\s?)*")))) {
			lblRegexMessege.setText("Tên nhà cung cấp phải viết hoa chữ cái đầu của mỗi từ!");
			lblRegexMessege.setForeground(Color.red);
			checkedInput = false;
		} else if (dc.length() == 0 || (!(dc.matches("^[0-9]+\\s[A-Za-z0-9\\s,]+")))) {
			lblRegexMessege.setText("Đia chỉ chứa các ký tự số, chữ và /");
			lblRegexMessege.setForeground(Color.red);
			checkedInput = false;
		} else if (email.length() == 0 || !(email.matches("^[A-Za-z0-9+_.-]+@(.+)$"))) {
			lblRegexMessege.setText("Email phải được nhập theo mẫu sau: EmaiA@example.com!");
			lblRegexMessege.setForeground(Color.red);
			checkedInput = false;
		} else if (sdt.length() == 0 || !(sdt.matches("^[0-9]{1,10}$"))) {
			lblRegexMessege.setText("Số điện thoại không quá 10 ký tự và không chứa các ký tự khác số!");
			lblRegexMessege.setForeground(Color.red);
			checkedInput = false;
		}
		return checkedInput;
	}

	public NhaCungCap reverSPFromTextField() {
		String maNCC = txtMaNhaCungCap.getText();
		String tenNCC = txtTenNhaCungCap.getText();
		String dc = txtDiaChi.getText();
		String email = txtEmail.getText();
		String sdt = txtSoDienThoai.getText();
		return new NhaCungCap(maNCC, tenNCC, dc, email, sdt);
	}

	public void xoaHetDuLieuTrenTableModel() {
		dataModel.getDataVector().removeAllElements();
	}

	public void updateTableData() {
		NhaCungCap_DAO ds = new NhaCungCap_DAO();
		for (NhaCungCap ncc : ds.getListNhaCungCap()) {
			Object[] row = { ncc.getMaNCC(), ncc.getTenNCC(), ncc.getSoDienThoai(), ncc.getEmail(), ncc.getDiaChi() };
			dataModel.addRow(row);
		}
		tblNhaCungCap.setModel(dataModel);
	}

	public void xoaTrang() {
		txtMaNhaCungCap.setText("");
		txtTenNhaCungCap.setText("");
		txtDiaChi.setText("");
		txtEmail.setText("");
		txtSoDienThoai.setText("");
		lblRegexMessege.setText("Nhập đầy đủ thông tin nhà cung cấp");
		lblRegexMessege.setForeground(Color.BLACK);
		txtTenNhaCungCap.requestFocus();
	}

	public void themAction() {
		if (checkInput()) {
			String hoTen = txtTenNhaCungCap.getText().toString();
			String dc = txtDiaChi.getText().toString();
			String email = txtEmail.getText().toString();
			String sdt = txtSoDienThoai.getText().toString();
			NhaCungCap NCC = new NhaCungCap(hoTen, dc, email, sdt);
			if (dsNCC.themNhaCungCap(NCC)) {
				NhaCungCap nccVuaThem = dsNCC.getNhaCungCapVuaCapNhat();
				Object dataRow[] = { nccVuaThem.getMaNCC(), nccVuaThem.getTenNCC(), nccVuaThem.getSoDienThoai(),
						nccVuaThem.getEmail(), nccVuaThem.getDiaChi() };
				dataModel.addRow(dataRow);
				dataModel.fireTableDataChanged();
				JOptionPane.showMessageDialog(null, "Thêm thành công!");
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
		
	}

	public void capNhatAction() {
		int row = tblNhaCungCap.getSelectedRow();
		if (row >= 0) {
			NhaCungCap NCC = reverSPFromTextField();
			if (dsNCC.capNhatNhaCungCap(NCC)) {
				tblNhaCungCap.setValueAt(txtTenNhaCungCap.getText().toString(), row, 1);
				tblNhaCungCap.setValueAt(txtSoDienThoai.getText().toString(), row, 2);
				tblNhaCungCap.setValueAt(txtEmail.getText().toString(), row, 3);
				tblNhaCungCap.setValueAt(txtDiaChi.getText().toString(), row, 4);
				JOptionPane.showMessageDialog(this, "Cập nhật nhà cung cấp thành công");
			} else {
				JOptionPane.showMessageDialog(this, "Cập nhật nhà cung cấp không thành công!");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Bạn phải chọn nhà cung cấp cần cập nhật!");
		}
	}

	public void xoaAction() {
		int row = tblNhaCungCap.getSelectedRow();
		if (row != -1) {
			String maNCC = tblNhaCungCap.getValueAt(row, 0).toString();
			int message = JOptionPane.showConfirmDialog(this, "Chắc chắn muốn xóa không", "Chú ý",
					JOptionPane.YES_NO_OPTION);

			if (message == JOptionPane.YES_OPTION) {
				if (dsNCC.xoaNhaCungCap(maNCC)) {
					dataModel.removeRow(row);
					dataModel.fireTableDataChanged();
					JOptionPane.showMessageDialog(null, "Xóa nhà cung cấp thành công");
				} else {
					JOptionPane.showMessageDialog(null, "Hủy thành công!");
					return;
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			btnThem.setText("Hủy");
			btnSa.setText("Lưu");
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
		int row = tblNhaCungCap.getSelectedRow();
		txtMaNhaCungCap.setText(dataModel.getValueAt(row, 0).toString());
		txtTenNhaCungCap.setText(dataModel.getValueAt(row, 1).toString());
		txtSoDienThoai.setText(dataModel.getValueAt(row, 2).toString());
		txtEmail.setText(dataModel.getValueAt(row, 3).toString());
		txtDiaChi.setText(dataModel.getValueAt(row, 4).toString());
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
