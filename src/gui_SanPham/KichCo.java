package gui_SanPham;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.SanPham_DAO;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class KichCo extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel pnQuanLyKichCo;
	private JLabel lblTieuDe,lblTenKichCo,lblDanhSachKichCo;
//	private JButton btnXoa,btnXoaTrang;
	private JButton btnThem,btnLuu;
	private JTextField txtTenKichCo;
	private JTable kichCoTable;
	private DefaultTableModel dataModel;
	private JScrollPane scPanel;
	
	private SanPham_DAO dssp;
	/**
	 * Create the panel.
	 */
	public KichCo() {
		
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dssp=new SanPham_DAO();
		
		setLayout(null);
		setBackground(Color.WHITE);
		setBounds(0, 0, 1332, 797);
		
		lblTieuDe = new JLabel("QUẢN LÝ KÍCH CỠ");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblTieuDe.setOpaque(true);
		lblTieuDe.setBackground(Color.WHITE);
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setForeground(Color.BLUE);
		lblTieuDe.setBounds(468, 10, 338, 44);
		add(lblTieuDe);
		
		pnQuanLyKichCo = new JPanel();
		pnQuanLyKichCo.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnQuanLyKichCo.setBackground(new Color(255, 255, 255));
		pnQuanLyKichCo.setBounds(29, 80, 1214, 137);
		add(pnQuanLyKichCo);
		pnQuanLyKichCo.setLayout(null);

		lblTenKichCo = new JLabel("Tên kích cỡ ");
		lblTenKichCo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTenKichCo.setBounds(848, 20, 116, 24);
		pnQuanLyKichCo.add(lblTenKichCo);
		
		txtTenKichCo = new JTextField();
		txtTenKichCo.setColumns(10);
		txtTenKichCo.setBounds(953, 19, 196, 31);
		pnQuanLyKichCo.add(txtTenKichCo);
		
//		btnXoa = new JButton("Xóa");
//		//btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 20));
//		btnXoa.setBounds(517, 78, 123, 31);
//		//btnXoa.setBackground(Color.decode("#FF008A"));
//		pnQuanLyKichCo.add(btnXoa);
//		
//		btnXoaTrang = new JButton("Xóa trắng");
//		//btnXoaTrang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
//		btnXoaTrang.setBounds(677, 78, 123, 31);
//		//btnXoaTrang.setBackground(Color.decode("#FF008A"));
//		pnQuanLyKichCo.add(btnXoaTrang);
		
		btnThem = new JButton("Thêm");
		//btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnThem.setBounds(841, 78, 123, 31);
		//btnThem.setBackground(Color.decode("#FF008A"));
		pnQuanLyKichCo.add(btnThem);
		
		btnLuu = new JButton("Lưu");
		//btnLuu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnLuu.setBounds(999, 78, 123, 31);
		//btnLuu.setBackground(Color.decode("#FF008A"));
		pnQuanLyKichCo.add(btnLuu);
		
		lblDanhSachKichCo = new JLabel("Danh sách kích cỡ");
		lblDanhSachKichCo.setOpaque(true);
		lblDanhSachKichCo.setHorizontalAlignment(SwingConstants.CENTER);
		lblDanhSachKichCo.setForeground(new Color(0, 0, 0));
		lblDanhSachKichCo.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblDanhSachKichCo.setBackground(Color.WHITE);
		lblDanhSachKichCo.setBounds(468, 334, 338, 44);
		add(lblDanhSachKichCo);
		
		String columns[] = { "Tên kích cỡ" };
		dataModel = new DefaultTableModel(columns, 0);

		scPanel = new JScrollPane();

		kichCoTable = new JTable(dataModel);
		scPanel = new JScrollPane(kichCoTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scPanel.setViewportView(kichCoTable);
		scPanel.setBounds(106, 388, 1075, 292);
		add(scPanel);
		
		
		utils.Format.setButtonEvent(btnLuu,btnThem);
		
		btnThem.addActionListener(this);
		btnLuu.addActionListener(this);
		kichCoTable.addMouseListener(this);
		
		docDuLieuTuDatabase();
	}
	
	public void docDuLieuTuDatabase() {
		for(String kichCo : dssp.getDanhSachKichCo()) {
			Object[] row= {kichCo};
			
			dataModel.addRow(row);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
	    if (o.equals(btnThem)) {
	        xoaTrang();
	    } else if (o.equals(btnLuu)) {
	    	luuAction(); // Nếu không, thực hiện lưu mới
	    }
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int row= kichCoTable.getSelectedRow();
		txtTenKichCo.setText(kichCoTable.getValueAt(row, 1).toString());
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
	
	private boolean kiemTraDuLieu() {
		String tenKC = txtTenKichCo.getText().trim();
		
		if (tenKC.length() == 0){
			JOptionPane.showMessageDialog(null, "Tên không được bỏ trống");
			txtTenKichCo.requestFocus();
			return false;
			
		} else if (!(tenKC.length() > 0 && tenKC.matches("^[A-Z0-9]+$"))) {
			
			JOptionPane.showMessageDialog(null, "Tên kích cỡ phải là chữ viết hoa hết hoặc số ");
			txtTenKichCo.requestFocus();
			return false;
		}
		return true;
	}
	
	private void xoaTrang() {
		txtTenKichCo.setText("");
		txtTenKichCo.requestFocus();
	}
	
	private void luuAction() {
	    String tenKC = txtTenKichCo.getText().trim().toString();
	    if (kiemTraDuLieu()) {
	        if(dssp.themKichCo(tenKC)) {
	    	    String ketQua = dssp.layDuLieuCotKichCo();
	                Object[] row = { ketQua };
	                dataModel.addRow(row);
	                xoaTrang();
	            }
	        }
	 }
	
	private void capNhatDuLieu() {
	    String tenKCCu = kichCoTable.getValueAt(kichCoTable.getSelectedRow(),0).toString().trim();
	    String tenKCMoi = txtTenKichCo.getText().trim();
	    if (kiemTraDuLieu()) {
	        if (dssp.capNhatKichCo(tenKCCu,tenKCMoi)) {
	            int row = kichCoTable.getSelectedRow();
	            dataModel.setValueAt(tenKCMoi, row, 1);
	            xoaTrang();
	        }
	    }
	}
}
