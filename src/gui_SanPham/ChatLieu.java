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
import java.util.List;
import java.awt.event.ActionEvent;

public class ChatLieu extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel pnQuanLyChatLieu;
	private JLabel lblTieuDe,lblTenDanhMuc,lblDanhSachChatLieu;
	private JTextField txtTenChatLieu;
	//private JButton btnXoa,btnXoaTrang;
	private JButton btnThem,btnLuu;
	private JTable chatLieuTable;
	private DefaultTableModel dataModel;
	private JScrollPane scPanel;
	
	private SanPham_DAO dssp;

	/**
	 * Create the panel.
	 */
	public ChatLieu() {
		
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dssp = new SanPham_DAO();
		
		setLayout(null);
		setBackground(Color.WHITE);
		setBounds(0, 0, 1332, 797);
		
		lblTieuDe = new JLabel("QUẢN LÝ CHẤT LIỆU");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblTieuDe.setOpaque(true);
		lblTieuDe.setBackground(Color.WHITE);
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setForeground(Color.BLUE);
		lblTieuDe.setBounds(468, 10, 338, 44);
		add(lblTieuDe);
		
		pnQuanLyChatLieu = new JPanel();
		pnQuanLyChatLieu.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnQuanLyChatLieu.setBackground(new Color(255, 255, 255));
		pnQuanLyChatLieu.setBounds(29, 80, 1214, 137);
		add(pnQuanLyChatLieu);
		pnQuanLyChatLieu.setLayout(null);
		
		
		
		lblTenDanhMuc = new JLabel("Tên chất liệu");
		lblTenDanhMuc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTenDanhMuc.setBounds(848, 20, 117, 24);
		pnQuanLyChatLieu.add(lblTenDanhMuc);
		
		txtTenChatLieu = new JTextField();
		txtTenChatLieu.setColumns(10);
		txtTenChatLieu.setBounds(971, 19, 196, 31);
		pnQuanLyChatLieu.add(txtTenChatLieu);
		
//		btnXoa = new JButton("Xóa");
//		//btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 20));
//		btnXoa.setBounds(571, 78, 123, 31);
//		//btnXoa.setBackground(Color.decode("#FF008A"));
//		pnQuanLyChatLieu.add(btnXoa);
//		
//		btnXoaTrang = new JButton("Xóa trắng");
//		//btnXoaTrang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
//		btnXoaTrang.setBounds(720, 78, 123, 31);
//		//btnXoaTrang.setBackground(Color.decode("#FF008A"));
//		pnQuanLyChatLieu.add(btnXoaTrang);
		
		btnThem = new JButton("Thêm");
		//btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnThem.setBounds(874, 78, 123, 31);
		//btnThem.setBackground(Color.decode("#FF008A"));
		pnQuanLyChatLieu.add(btnThem);
		
		btnLuu = new JButton("Lưu");	
		//btnLuu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnLuu.setBounds(1021, 78, 123, 31);
		//btnLuu.setBackground(Color.decode("#FF008A"));
		pnQuanLyChatLieu.add(btnLuu);
		
		lblDanhSachChatLieu = new JLabel("Danh sách chất liệu");
		lblDanhSachChatLieu.setOpaque(true);
		lblDanhSachChatLieu.setHorizontalAlignment(SwingConstants.CENTER);
		lblDanhSachChatLieu.setForeground(new Color(0, 0, 0));
		lblDanhSachChatLieu.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblDanhSachChatLieu.setBackground(Color.WHITE);
		lblDanhSachChatLieu.setBounds(468, 334, 338, 44);
		add(lblDanhSachChatLieu);
		
		String columns[] = { "Tên chất liệu" };
		dataModel = new DefaultTableModel(columns, 0);

		scPanel = new JScrollPane();

		chatLieuTable = new JTable(dataModel);
		scPanel = new JScrollPane(chatLieuTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scPanel.setViewportView(chatLieuTable);
		scPanel.setBounds(106, 388, 1075, 292);
		add(scPanel);
		
		utils.Format.setButtonEvent(btnLuu,btnThem);
		
		btnThem.addActionListener(this);
	//	btnXoaTrang.addActionListener(this);
	//	btnXoa.addActionListener(this);
		btnLuu.addActionListener(this);
		chatLieuTable.addMouseListener(this);
		
		docDuLieuTuDatabase();
	}

	public void docDuLieuTuDatabase() {
		for(String chatLieu : dssp.getDanhSachChatLieu()) {
			Object[] row = {chatLieu};
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
		int row= chatLieuTable.getSelectedRow();
		txtTenChatLieu.setText(chatLieuTable.getValueAt(row, 0).toString());
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
		String tenCL = txtTenChatLieu.getText().trim();
		
		if (tenCL.length() == 0){
			JOptionPane.showMessageDialog(null, "Tên không được bỏ trống");
			txtTenChatLieu.requestFocus();
			return false;
			
		} else if (!(tenCL.length() > 0 && tenCL.matches("^[A-ZÀÁẢẠÃĂẮẰẤẨẬẪĂÁÀẢẠÃẦẤẨẬẪẦĨÍÌỈỊĨÓÒỎỌÕÔỐỒỔỖỘỚỜỔỢỠỜƠỚỜỞỠỢỤỦŨỨỪỬỰỮÝỲỶỴỸĐ][a-zàáảạãăắằấẩậẫăáàảạãầấẩậẫẫỉíìỉịĩóòỏọõôốồổỗộơớờởỡợọụủũưứừửựữýỳỷỵỹđ ]*\\b$"))) {
			
			JOptionPane.showMessageDialog(null, "Tên chất liệu phải viết hoa chữ cái đầu ");
			txtTenChatLieu.requestFocus();
			return false;
		}
		return true;
	}
	
	private void xoaTrang() {
		txtTenChatLieu.setText("");
		txtTenChatLieu.requestFocus();
	}
	
	
	private void luuAction() {
	    String tenCL = txtTenChatLieu.getText().trim().toString();
	    if (kiemTraDuLieu()) {
	        if (dssp.themChatLieu(tenCL)) {
	    	    String ketQua = dssp.layDuLieuCotChatLieu();
	                Object[] row = { ketQua };
	                dataModel.addRow(row);
	                xoaTrang();
	            }
	        }
	 }

	
	private void capNhatDuLieu() {
	    String tenCLCu = chatLieuTable.getValueAt(chatLieuTable.getSelectedRow(), 0).toString().trim();
	    String tenCLMoi = txtTenChatLieu.getText().trim();
	    if (kiemTraDuLieu()) {
	        if (dssp.capNhatChatLieu(tenCLCu,tenCLMoi)) {
	            int row = chatLieuTable.getSelectedRow();
	            dataModel.setValueAt(tenCLMoi, row, 1);
	            xoaTrang();
	        }
	    }
	}
	
	
}
