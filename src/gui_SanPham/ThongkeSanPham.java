package gui_SanPham;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.SanPham_DAO;
public class ThongkeSanPham extends JPanel {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane,pnSLSPDaBan,pnSLSPConLai;
	private JLabel lblTieuDe,lblTieuDe1,lblSLSPDaBan,lblSLSPConLai;
	private JTable sanPhamTB;
	private DefaultTableModel dataModel;
	private SanPham_DAO dssp;
	private JScrollPane scPanel;

	/**
	 * Create the panel.
	 */
	public ThongkeSanPham() {
		setLayout(null);
		setBackground(Color.WHITE);
		setBounds(0, 0, 979, 670);

		lblTieuDe = new JLabel("Thống kê số lượng sản phẩm");
		lblTieuDe.setOpaque(true);
		lblTieuDe.setBackground(new Color(255, 255, 255));
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setForeground(Color.BLACK);
		lblTieuDe.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTieuDe.setBorder(new LineBorder(Color.BLACK));
		lblTieuDe.setBounds(35, 10, 266, 44);
		add(lblTieuDe);

		lblTieuDe1 = new JLabel("Danh sách sản phẩm");
		lblTieuDe1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTieuDe1.setBounds(396, 386, 178, 27);
		add(lblTieuDe1);

		String columns[] = { "Mã sản phẩm", "Tên sản phẩm", "Giá bán","Loại sản phẩm","Màu","Size","Chất liệu", "Nhà cung cấp", "Số lượng bán","Số lượng" };
		dataModel = new DefaultTableModel(columns, 0);

		scPanel = new JScrollPane();

		sanPhamTB = new JTable(dataModel);
		scPanel = new JScrollPane(sanPhamTB, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scPanel.setViewportView(sanPhamTB);
		scPanel.setBounds(10, 414, 950, 177);
		add(scPanel);

		pnSLSPDaBan = new JPanel();
		pnSLSPDaBan.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnSLSPDaBan.setBackground(Color.WHITE);
		pnSLSPDaBan.setBounds(58, 107, 339, 260);
		add(pnSLSPDaBan);
		pnSLSPDaBan.setLayout(null);

		lblSLSPDaBan = new JLabel("Số lượng sản phẩm đã bán");
		lblSLSPDaBan.setBounds(61, 10, 214, 44);
		pnSLSPDaBan.add(lblSLSPDaBan);
		lblSLSPDaBan.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		pnSLSPConLai = new JPanel();
		pnSLSPConLai.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnSLSPConLai.setBackground(Color.WHITE);
		pnSLSPConLai.setBounds(565, 107, 339, 260);
		add(pnSLSPConLai);
		pnSLSPConLai.setLayout(null);

		lblSLSPConLai = new JLabel("Số lượng sản phẩm còn lại");
		lblSLSPConLai.setBounds(72, 10, 214, 44);
		pnSLSPConLai.add(lblSLSPConLai);
		lblSLSPConLai.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	}
}
