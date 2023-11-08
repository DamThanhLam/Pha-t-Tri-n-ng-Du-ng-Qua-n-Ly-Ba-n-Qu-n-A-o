package gui_SanPham;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.SanPham_DAO;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;

public class DanhMuc extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel pnQuanLyDanhMuc;
	private JLabel lblTieuDe,lblTenDanhMuc,lblDanhSachDanhMuc;
	private JTextField txtTenDanhMuc;
//	private JButton btnXoa,btnXoaTrang;
	private JButton btnThem,btnLuu;
	private JTable danhMucTable;
	private DefaultTableModel dataModel;
	private JScrollPane scPanel;
	
	private SanPham_DAO dssp;
	/**
	 * Create the panel.
	 */
	public DanhMuc() {

		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dssp=new SanPham_DAO();
		
		setLayout(null);
		setBackground(Color.WHITE);
		setBounds(0, 0, 1332, 797);
		
		lblTieuDe = new JLabel("QUẢN LÝ DANH MỤC");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblTieuDe.setOpaque(true);
		lblTieuDe.setBackground(Color.WHITE);
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setForeground(Color.BLUE);
		lblTieuDe.setBounds(468, 10, 338, 44);
		add(lblTieuDe);
		
		pnQuanLyDanhMuc = new JPanel();
		pnQuanLyDanhMuc.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnQuanLyDanhMuc.setBackground(new Color(255, 255, 255));
		pnQuanLyDanhMuc.setBounds(29, 80, 1214, 137);
		add(pnQuanLyDanhMuc);
		pnQuanLyDanhMuc.setLayout(null);
		
		lblTenDanhMuc = new JLabel("Tên danh mục ");
		lblTenDanhMuc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTenDanhMuc.setBounds(848, 20, 123, 24);
		pnQuanLyDanhMuc.add(lblTenDanhMuc);
		
		txtTenDanhMuc = new JTextField();
		txtTenDanhMuc.setColumns(10);
		txtTenDanhMuc.setBounds(967, 19, 196, 31);
		pnQuanLyDanhMuc.add(txtTenDanhMuc);
		
//		btnXoa = new JButton("Xóa");
//		//btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 20));
//		btnXoa.setBounds(562, 78, 123, 31);
//		//btnXoa.setBackground(Color.decode("#FF008A"));
//		pnQuanLyDanhMuc.add(btnXoa);
//		
//		btnXoaTrang = new JButton("Xóa trắng");
//		//btnXoaTrang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
//		btnXoaTrang.setBounds(716, 78, 123, 31);
//		//btnXoaTrang.setBackground(Color.decode("#FF008A"));
//		pnQuanLyDanhMuc.add(btnXoaTrang);
		
		btnThem = new JButton("Thêm");
		//btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnThem.setBounds(871, 78, 123, 31);
		//btnThem.setBackground(Color.decode("#FF008A"));
		pnQuanLyDanhMuc.add(btnThem);
		
		btnLuu = new JButton("Lưu");
		//btnLuu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnLuu.setBounds(1027, 78, 123, 31);
		//btnLuu.setBackground(Color.decode("#FF008A"));
		pnQuanLyDanhMuc.add(btnLuu);
		
		lblDanhSachDanhMuc = new JLabel("Danh sách danh mục");
		lblDanhSachDanhMuc.setOpaque(true);
		lblDanhSachDanhMuc.setHorizontalAlignment(SwingConstants.CENTER);
		lblDanhSachDanhMuc.setForeground(new Color(0, 0, 0));
		lblDanhSachDanhMuc.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblDanhSachDanhMuc.setBackground(Color.WHITE);
		lblDanhSachDanhMuc.setBounds(468, 334, 338, 44);
		add(lblDanhSachDanhMuc);
		
		String columns[] = {"Tên danh mục" };
		dataModel = new DefaultTableModel(columns, 0);

		scPanel = new JScrollPane();

		danhMucTable = new JTable(dataModel);
		scPanel = new JScrollPane(danhMucTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scPanel.setViewportView(danhMucTable);
		scPanel.setBounds(106, 388, 1075, 292);
		add(scPanel);
		
		
		utils.Format.setButtonEvent(btnLuu,btnThem);
		btnThem.addActionListener(this);
		btnLuu.addActionListener(this);
		danhMucTable.addMouseListener(this);
		docDuLieuTuDatabase();

	}
	
	public void docDuLieuTuDatabase() {
		for(String danhMuc : dssp.getDanhSachDanhMuc()) {
			Object[] row = {danhMuc};
			
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
		int row= danhMucTable.getSelectedRow();
		txtTenDanhMuc.setText(danhMucTable.getValueAt(row, 0).toString());
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
		String tenDM = txtTenDanhMuc.getText().trim();
		
		if (tenDM.length() == 0){
			JOptionPane.showMessageDialog(null, "Tên không được bỏ trống");
			txtTenDanhMuc.requestFocus();
			return false;
			
		} else if (!(tenDM.length() > 0 && tenDM.matches("^[A-ZÀÁẢẠÃĂẮẰẤẨẬẪĂÁÀẢẠÃẦẤẨẬẪẦĨÍÌỈỊĨÓÒỎỌÕÔỐỒỔỖỘỚỜỔỢỠỜƠỚỜỞỠỢỤỦŨỨỪỬỰỮÝỲỶỴỸĐ][a-zàáảạãăắằấẩậẫăáàảạãầấẩậẫẫỉíìỉịĩóòỏọõôốồổỗộơớờởỡợọụủũưứừửựữýỳỷỵỹđ ]*\\b$"))) {
			
			JOptionPane.showMessageDialog(null, "Tên danh mục phải viết hoa chữ cái đầu ");
			txtTenDanhMuc.requestFocus();
			return false;
		}
		return true;
	}
	
	private void xoaTrang() {
		txtTenDanhMuc.setText("");
		txtTenDanhMuc.requestFocus();
	}
	
	private void luuAction() {
	    String tenDM = txtTenDanhMuc.getText().trim().toString();
	    if (kiemTraDuLieu()) {
	        if (dssp.themDanhMuc(tenDM)) {
	    	    String ketQua = dssp.layDuLieuCotDanhMuc();
	                Object[] row = { ketQua };
	                dataModel.addRow(row);
	                xoaTrang();
	            }
	        }
	    }
	
	private void capNhatDuLieu() {
	    String tenDMCu = danhMucTable.getValueAt(danhMucTable.getSelectedRow(), 0).toString().trim();
	    String tenDMMoi = txtTenDanhMuc.getText().trim();
	    if (kiemTraDuLieu()) {
	        if (dssp.capNhatDanhMuc(tenDMCu,tenDMMoi)) {
	            int row = danhMucTable.getSelectedRow();
	            dataModel.setValueAt(tenDMCu, row, 1);
	            xoaTrang();
	        }
	    }
	}
	

}
