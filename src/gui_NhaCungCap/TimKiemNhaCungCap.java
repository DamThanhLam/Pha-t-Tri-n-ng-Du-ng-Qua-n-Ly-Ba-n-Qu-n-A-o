package gui_NhaCungCap;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.KhachHang_DAO;
import dao.NhaCungCap_DAO;
import entity.KhachHang;
import entity.NhaCungCap;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

public class TimKiemNhaCungCap extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtTenNCC, txtDiaChi, txtEmail, txtSoDienThoai;
	private JLabel lblTieuDe, lblMaNCC, lblSDT, lblbTenNCC, lblEmail, lblDiaChi;
	private JButton btnXoaTrang, btnTimKiem;
	private JTable tblNhaCungCap;
	private DefaultTableModel dataModel;
	private JScrollPane scrNhaCungCap;
	private JPanel pnFormNhap;
	private JTextField txtMaNCC;
	private NhaCungCap_DAO dsNCC;
	private JLabel lblRegexMessage;

	/**
	 * Create the panel.
	 */
	public TimKiemNhaCungCap() {
		dsNCC = new NhaCungCap_DAO();
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		setBackground(Color.WHITE);
		setSize(utils.Contains.WIDTH_PANEL_CONTENT, utils.Contains.HEIGHT_PANEL_CONTENT);

		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnXoaTrang.setBounds(447, 311, 110, 30);
		add(btnXoaTrang);

		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnTimKiem.setBounds(682, 311, 110, 30);
		add(btnTimKiem);

		String columns[] = { "Mã nhà cung cấp", "Tên nhà cung cấp", "Số điện thoại", "Email", "Địa chỉ" };
		dataModel = new DefaultTableModel(columns, 0);
		tblNhaCungCap = new JTable(dataModel);
		scrNhaCungCap = new JScrollPane(tblNhaCungCap, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrNhaCungCap.setViewportView(tblNhaCungCap);
		scrNhaCungCap.setBounds(10, 393, 1300, 391);
		add(scrNhaCungCap);

		JPanel pnTitle = new JPanel();
		pnTitle.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnTitle.setBounds(10, 10, 275, 72);
		add(pnTitle);
		pnTitle.setLayout(null);

		lblTieuDe = new JLabel("Tìm Kiếm Nhà Cung Cấp");
		lblTieuDe.setBounds(10, 10, 255, 52);
		pnTitle.add(lblTieuDe);
		lblTieuDe.setOpaque(true);
		lblTieuDe.setBackground(new Color(255, 255, 255));
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setForeground(Color.BLACK);
		lblTieuDe.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTieuDe.setBorder(null);

		pnFormNhap = new JPanel();
		pnFormNhap.setBorder(new TitledBorder(null, "Form Nh\u1EADp Th\u00F4ng Tin Nh\u00E0 Cung C\u1EA5p",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnFormNhap.setBackground(new Color(255, 255, 255));
		pnFormNhap.setBounds(10, 92, 1300, 164);
		add(pnFormNhap);
		pnFormNhap.setLayout(null);

		lblMaNCC = new JLabel("Mã nhà cung cấp :");
		lblMaNCC.setBounds(164, 48, 109, 24);
		pnFormNhap.add(lblMaNCC);
		lblMaNCC.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		lblEmail = new JLabel("Email :");
		lblEmail.setBounds(164, 99, 64, 24);
		pnFormNhap.add(lblEmail);
		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		txtEmail = new JTextField();
		txtEmail.setBounds(283, 101, 291, 24);
		pnFormNhap.add(txtEmail);
		txtEmail.setColumns(10);

		lblbTenNCC = new JLabel("Tên nhà cung cấp :");
		lblbTenNCC.setBounds(449, 48, 125, 24);
		pnFormNhap.add(lblbTenNCC);
		lblbTenNCC.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		txtTenNCC = new JTextField();
		txtTenNCC.setBounds(584, 50, 170, 24);
		pnFormNhap.add(txtTenNCC);
		txtTenNCC.setColumns(10);

		lblDiaChi = new JLabel("Địa chỉ :");
		lblDiaChi.setBounds(807, 48, 70, 24);
		pnFormNhap.add(lblDiaChi);
		lblDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		txtDiaChi = new JTextField();
		txtDiaChi.setBounds(887, 50, 181, 24);
		pnFormNhap.add(txtDiaChi);
		txtDiaChi.setColumns(10);

		lblSDT = new JLabel("Số điện thoại :");
		lblSDT.setBounds(658, 99, 96, 24);
		pnFormNhap.add(lblSDT);
		lblSDT.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setBounds(781, 102, 287, 24);
		pnFormNhap.add(txtSoDienThoai);
		txtSoDienThoai.setColumns(10);

		txtMaNCC = new JTextField();
		txtMaNCC.setColumns(10);
		txtMaNCC.setBounds(283, 50, 132, 24);
		pnFormNhap.add(txtMaNCC);
		
		lblRegexMessage = new JLabel("Nhập đầy đủ thông tin nhà cung cấp");
		lblRegexMessage.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		lblRegexMessage.setBounds(971, 141, 319, 14);
		pnFormNhap.add(lblRegexMessage);

		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		updateTableData();
		
		btnTimKiem.addActionListener(this);
		btnXoaTrang.addActionListener(this);
	}

	public void updateTableData() {
		NhaCungCap_DAO ds = new NhaCungCap_DAO();
		for (NhaCungCap ncc : ds.getListNhaCungCap()) {
			Object rowData[] = { ncc.getMaNCC(), ncc.getTenNCC(), ncc.getDiaChi(), ncc.getEmail(),
					ncc.getSoDienThoai() };
			dataModel.addRow(rowData);
		}
		dataModel.fireTableDataChanged();
	}

	public void xoaTrang() {
		txtMaNCC.setText("");
		txtTenNCC.setText("");
		txtDiaChi.setText("");
		txtEmail.setText("");
		txtSoDienThoai.setText("");
		dataModel.getDataVector().removeAllElements();
		dataModel.fireTableDataChanged();
		txtMaNCC.requestFocus();
		updateTableData();
	}

	private void timKiemAction() {
		String maNCC = txtMaNCC.getText().toString().trim();
		String tenNCC = txtTenNCC.getText().toString().trim();
		String dc = txtDiaChi.getText().toString().trim();
		String email = txtEmail.getText().toString().trim();
		String sdt = txtSoDienThoai.getText().toString().trim();
		List<NhaCungCap> dstk = dsNCC.timKiemNhaCungCap(maNCC, tenNCC, dc, email, sdt);
		if (dstk.size()>0) {
			dataModel.getDataVector().removeAllElements();
			dataModel.fireTableDataChanged();
			for (NhaCungCap ncc : dstk) {

				Object[] row = { ncc.getMaNCC(), ncc.getTenNCC(), ncc.getDiaChi(), ncc.getEmail(),
						ncc.getSoDienThoai() };
				dataModel.addRow(row);
			}
			dataModel.fireTableDataChanged();
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
		if (o.equals(btnXoaTrang)) {
			xoaTrang();
		} else if (o.equals(btnTimKiem)) {
			timKiemAction();
		}
	}

}
