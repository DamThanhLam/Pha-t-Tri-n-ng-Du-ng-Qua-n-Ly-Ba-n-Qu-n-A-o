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

public class MauSac extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel pnQuanLyMauSac;
	private JLabel lblTieuDe,lblTenMauSac,lblDanhSchDanh;
	private JTextField txtTenMauSac;
	//private JButton btnXoa,btnXoaTrang;
	private JButton btnThem,btnLuu;
	private JTable mauSacTable;
	private DefaultTableModel dataModel;
	private JScrollPane scPanel;
	
	private SanPham_DAO dssp;
	
	/**
	 * Create the panel.
	 */
	public MauSac() {
		
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dssp=new SanPham_DAO();
		
		setLayout(null);
		setBackground(Color.WHITE);
		setBounds(0, 0, 1332, 797);
		
		lblTieuDe = new JLabel("QUẢN LÝ MÀU SẮC");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblTieuDe.setOpaque(true);
		lblTieuDe.setBackground(Color.WHITE);
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setForeground(Color.BLUE);
		lblTieuDe.setBounds(468, 10, 338, 44);
		add(lblTieuDe);
		
		pnQuanLyMauSac = new JPanel();
		pnQuanLyMauSac.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnQuanLyMauSac.setBackground(new Color(255, 255, 255));
		pnQuanLyMauSac.setBounds(29, 80, 1214, 137);
		add(pnQuanLyMauSac);
		pnQuanLyMauSac.setLayout(null);
		
		lblTenMauSac = new JLabel("Tên màu sắc ");
		lblTenMauSac.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTenMauSac.setBounds(848, 20, 106, 24);
		pnQuanLyMauSac.add(lblTenMauSac);
		
		txtTenMauSac = new JTextField();
		txtTenMauSac.setColumns(10);
		txtTenMauSac.setBounds(964, 19, 196, 31);
		pnQuanLyMauSac.add(txtTenMauSac);
		
//		btnXoa = new JButton("Xóa");
//		//btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 20));
//		btnXoa.setBounds(557, 78, 123, 31);
//		//btnXoa.setBackground(Color.decode("#FF008A"));
//		pnQuanLyMauSac.add(btnXoa);
		
//		btnXoaTrang = new JButton("Xóa trắng");
//		//btnXoaTrang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
//		btnXoaTrang.setBounds(712, 78, 123, 31);
//		//btnXoaTrang.setBackground(Color.decode("#FF008A"));
//		pnQuanLyMauSac.add(btnXoaTrang);
		
		btnThem = new JButton("Thêm");
		//btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnThem.setBounds(864, 78, 123, 31);
		//btnThem.setBackground(Color.decode("#FF008A"));
		pnQuanLyMauSac.add(btnThem);
		
		btnLuu = new JButton("Lưu");
		//btnLuu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnLuu.setBounds(1025, 78, 123, 31);
		//btnLuu.setBackground(Color.decode("#FF008A"));
		pnQuanLyMauSac.add(btnLuu);
		
		lblDanhSchDanh = new JLabel("Danh sách màu sắc");
		lblDanhSchDanh.setOpaque(true);
		lblDanhSchDanh.setHorizontalAlignment(SwingConstants.CENTER);
		lblDanhSchDanh.setForeground(new Color(0, 0, 0));
		lblDanhSchDanh.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblDanhSchDanh.setBackground(Color.WHITE);
		lblDanhSchDanh.setBounds(468, 334, 338, 44);
		add(lblDanhSchDanh);
		
		String columns[] = {"Tên màu sắc" };
		dataModel = new DefaultTableModel(columns, 0);

		scPanel = new JScrollPane();

		mauSacTable = new JTable(dataModel);
		scPanel = new JScrollPane(mauSacTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scPanel.setViewportView(mauSacTable);
		scPanel.setBounds(106, 388, 1075, 292);
		add(scPanel);
		
		
		utils.Format.setButtonEvent(btnLuu,btnThem);
		
		btnThem.addActionListener(this);
		btnLuu.addActionListener(this);
		mauSacTable.addMouseListener(this);
		
		docDuLieuTuDatabase();
	}

	public void docDuLieuTuDatabase() {
		for(String mauSac : dssp.getDanhSachMauSac()) {
			Object[] row = {mauSac};
			
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
		int row= mauSacTable.getSelectedRow();
		txtTenMauSac.setText(mauSacTable.getValueAt(row, 1).toString());
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
		String tenMS = txtTenMauSac.getText().trim();
		
		if (tenMS.length() == 0){
			JOptionPane.showMessageDialog(null, "Tên không được bỏ trống");
			txtTenMauSac.requestFocus();
			return false;
			
		} else if (!(tenMS.length() > 0 && tenMS.matches("^[A-ZÀÁẢẠÃĂẮẰẤẨẬẪĂÁÀẢẠÃẦẤẨẬẪẦĨÍÌỈỊĨÓÒỎỌÕÔỐỒỔỖỘỚỜỔỢỠỜƠỚỜỞỠỢỤỦŨỨỪỬỰỮÝỲỶỴỸĐ][a-zàáảạãăắằấẩậẫăáàảạãầấẩậẫẫỉíìỉịĩóòỏọõôốồổỗộơớờởỡợọụủũưứừửựữýỳỷỵỹđ ]*\\b$"))) {
			
			JOptionPane.showMessageDialog(null, "Tên màu sắc phải viết hoa chữ cái đầu ");
			txtTenMauSac.requestFocus();
			return false;
		}
		return true;
	}
	
	private void xoaTrang() {
		txtTenMauSac.setText("");
		txtTenMauSac.requestFocus();
	}
	
	private void luuAction() {
	    String tenMS = txtTenMauSac.getText().trim().toString();
	    if (kiemTraDuLieu()) {
	        if (dssp.themMauSac(tenMS)) {
	    	    String ketQua = dssp.layDuLieuCotMauSac();
	                Object[] row = { ketQua};
	                dataModel.addRow(row);
	                xoaTrang();
	            }
	        }
	 }
	
	private void capNhatDuLieu() {
	    String tenMSCu = mauSacTable.getValueAt(mauSacTable.getSelectedRow(),0).toString().trim();
	    String tenMSMoi = txtTenMauSac.getText().trim();
	    if (kiemTraDuLieu()) {
	        if (dssp.capNhatMauSac(tenMSCu,tenMSMoi)) {
	            int row = mauSacTable.getSelectedRow();
	            dataModel.setValueAt(tenMSMoi, row, 1);
	            xoaTrang();
	        }
	    }
	}
}
