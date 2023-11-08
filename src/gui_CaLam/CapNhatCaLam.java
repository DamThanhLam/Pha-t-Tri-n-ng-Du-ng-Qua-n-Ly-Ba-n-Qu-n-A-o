package gui_CaLam;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

public class CapNhatCaLam extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblTieuDe,lblMaCa,lblGioBatDau,lblGioKetThuc,lbltieuDe2;
	private JTextField txtMaCa;
	private JPanel pnCapNhatCalam;
	private JComboBox cbbGioBatDau,cbbGioKetThuc;
	private JButton btnXoaTrang,btnLuu,btnXoa,btnThem;
	private JTable caLamTable;
	private DefaultTableModel dataModel;
	private JScrollPane scPanel;
	/**
	 * Create the panel.
	 */
	public CapNhatCaLam() {
		setLayout(null);
		setBackground(Color.WHITE);
		setBounds(0, 0, 1332, 797);
		
		lblTieuDe = new JLabel("CẬP NHẬT CA LÀM");
		lblTieuDe.setForeground(new Color(0, 0, 255));
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblTieuDe.setBounds(566, 37, 250, 36);
		add(lblTieuDe);
		
		pnCapNhatCalam = new JPanel();
		pnCapNhatCalam.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnCapNhatCalam.setBackground(new Color(255, 255, 255));
		pnCapNhatCalam.setBounds(214, 105, 891, 164);
		add(pnCapNhatCalam);
		pnCapNhatCalam.setLayout(null);
		
		lblMaCa = new JLabel("Mã ca");
		lblMaCa.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMaCa.setBounds(42, 46, 66, 30);
		pnCapNhatCalam.add(lblMaCa);
		
		txtMaCa = new JTextField();
		txtMaCa.setBounds(101, 49, 113, 30);
		pnCapNhatCalam.add(txtMaCa);
		txtMaCa.setColumns(10);
		
		lblGioBatDau = new JLabel("Giờ bắt đầu");
		lblGioBatDau.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblGioBatDau.setBounds(347, 46, 100, 30);
		pnCapNhatCalam.add(lblGioBatDau);
		
		cbbGioBatDau = new JComboBox();
		cbbGioBatDau.setBounds(452, 49, 106, 28);
		pnCapNhatCalam.add(cbbGioBatDau);
		
		lblGioKetThuc = new JLabel("Giờ kết thúc");
		lblGioKetThuc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblGioKetThuc.setBounds(633, 46, 106, 30);
		pnCapNhatCalam.add(lblGioKetThuc);
		
		cbbGioKetThuc = new JComboBox();
		cbbGioKetThuc.setBounds(745, 48, 106, 28);
		pnCapNhatCalam.add(cbbGioKetThuc);
		
		btnLuu = new JButton("Lưu");
		btnLuu.setBounds(724, 111, 130, 33);
		btnLuu.setBackground(Color.decode("#FF008A"));
		pnCapNhatCalam.add(btnLuu);
		btnLuu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		btnThem = new JButton("Thêm");
		btnThem.setBounds(569, 111, 130, 33);
		btnThem.setBackground(Color.decode("#FF008A"));
		pnCapNhatCalam.add(btnThem);
		btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setBounds(415, 111, 130, 33);
		btnXoaTrang.setBackground(Color.decode("#FF008A"));
		pnCapNhatCalam.add(btnXoaTrang);
		btnXoaTrang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(260, 111, 130, 33);
		btnXoa.setBackground(Color.decode("#FF008A"));
		pnCapNhatCalam.add(btnXoa);
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		String columns[] = { "Mã ca làm", "Giờ bắt đầu", "Giờ kết thức"};
		dataModel = new DefaultTableModel(columns, 0);

		scPanel = new JScrollPane();

		caLamTable = new JTable(dataModel);
		scPanel = new JScrollPane(caLamTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scPanel.setViewportView(caLamTable);
		scPanel.setBounds(148, 427, 1053, 292);
		add(scPanel);
		
		lbltieuDe2 = new JLabel("Danh sách ca làm");
		lbltieuDe2.setForeground(new Color(0, 0, 0));
		lbltieuDe2.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbltieuDe2.setBounds(566, 387, 250, 36);
		add(lbltieuDe2);
		
		updateCombobox();
		
	}
	
	public void updateCombobox() {
		for (int i = 1; i < 25; i++) {
			if (i < 13) {
				cbbGioBatDau.addItem(i + ":00 AM");
				cbbGioKetThuc.addItem(i + ":00 AM");
			} else {
				cbbGioBatDau.addItem(i + ":00 PM");
				cbbGioKetThuc.addItem(i + ":00 PM");
			}
		}
	}
}
