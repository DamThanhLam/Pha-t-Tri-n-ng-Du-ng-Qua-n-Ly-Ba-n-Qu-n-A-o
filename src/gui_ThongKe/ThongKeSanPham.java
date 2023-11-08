package gui_ThongKe;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class ThongKeSanPham extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblTieuDe,lblTieuDeBieuDo;
	private JPanel pnBieuDo;
	private JTable sanPhamTable;
	private DefaultTableModel dataModel;
	private JScrollPane scPanel;

	/**
	 * Create the panel.
	 */
	public ThongKeSanPham() {
		setLayout(null);
		setBackground(Color.WHITE);
		setBounds(0, 0, 1332, 797);
		
		lblTieuDe = new JLabel("THỐNG KÊ SẢN PHẨM");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblTieuDe.setOpaque(true);
		lblTieuDe.setBackground(Color.WHITE);
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setForeground(Color.BLUE);
		lblTieuDe.setBounds(468, 0, 338, 44);
		add(lblTieuDe);
		
		pnBieuDo = new JPanel();
		pnBieuDo.setBounds(39, 45, 1201, 407);
		add(pnBieuDo);
		pnBieuDo.setLayout(null);
		
		lblTieuDeBieuDo = new JLabel("BIỂU ĐỒ THỂ HIỆN SỐ LƯỢNG SẢN PHẨM ĐÃ BÁN");
		lblTieuDeBieuDo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTieuDeBieuDo.setBounds(364, 10, 471, 45);
		pnBieuDo.add(lblTieuDeBieuDo);
		
		String columns[] = { "Mã sản phẩm","Tên sản phẩm","Số lượng bán","Số lượng tồn","Đơn giá","Danh mục","Kích cỡ","Chất liệu","Màu sắc", "Nhà cung cấp"};
		dataModel = new DefaultTableModel(columns, 0);

		scPanel = new JScrollPane();

		sanPhamTable = new JTable(dataModel);
		scPanel = new JScrollPane(sanPhamTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scPanel.setViewportView(sanPhamTable);
		scPanel.setBounds(10, 484, 1298, 292);
		add(scPanel);
	}
}
