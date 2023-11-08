package utils;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Function {
	public static Dimension getScreenSize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		return screenSize;
	}
	public static double getScreenHeight() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		return  screenSize.getHeight();
	}
	public static double getScreenWidth() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		return  screenSize.getWidth();
	}
	/**
	 * 
	 * @param ma
	 * @param obj
	 * @param pathFile
	 * @param xuatHoaDon
	 */
		
	public static void xuatHoaDonPDF(String ma, Object obj, String pathFile, Boolean xuatHoaDon) {
		String src = "src\\reports\\hoaDon.jrxml";
		
		try {
			Hashtable<String, Object> maHoaDon = new Hashtable<String, Object>();
			JasperReport report = JasperCompileManager.compileReport(src);
			maHoaDon.put(ma, obj);
			JasperPrint print = JasperFillManager.fillReport(report, maHoaDon, ConnectDB.con);
			JasperViewer.viewReport(print, false);
			
			if (xuatHoaDon) {
				JasperExportManager.exportReportToPdfFile(print, pathFile);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//		Xuất danh sách hóa đơn từ bảng thống kê sang file .csv
	public static boolean xuatExcel(String fileName, String sheetTitle, String cols[], DefaultTableModel data) {
		int rowCount = data.getRowCount();
		if (rowCount > 0) {
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet(sheetTitle);
			int colsLength = cols.length;
			int countOfCols = 0;

			// Tạo tiêu đề cho các cột
			Row headerRow = sheet.createRow(0);
			for (String col : cols) {
				headerRow.createCell(countOfCols).setCellValue(col);
				countOfCols++;
			}
			int rowNum = 1;
			for (int i = 0; i < rowCount; i++) {
				Row row = sheet.createRow(rowNum++);
				for (int j = 0; j < colsLength; j++) {
					row.createCell(j).setCellValue(data.getValueAt(i, j).toString());
				}
			}

			/**
			 * Lấy ngày hiện tại làm tên file khi xuất ra file excel File thống kê được lưu
			 * trong thư mục ReportFiles
			 */
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss"); // Định dạng thời gian
			String formattedDate = dateFormat.format(now);

			String filePath = Contains.getPathOfReportFiles() + File.separator + fileName + formattedDate + ".xlsx";

			try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
				workbook.write(fileOut);
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	 private Image scaleImage(Image image, int w, int h) {
	        Image scaled = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
	        return scaled;
	 }
}
