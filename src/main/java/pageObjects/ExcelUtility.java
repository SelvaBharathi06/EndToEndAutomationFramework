package pageObjects;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.*;

public class ExcelUtility {

	public static void excel() throws IOException {

		String excelPath = "C:\\Users\\shank\\Downloads\\depotsBulkUpload.xlsx";
		String sheetName = "Depots";

		Map<String, Integer> columnMap = new LinkedHashMap<>();
		columnMap.put("Depot Name", 0);
		columnMap.put("Display Reference Name", 1);
		columnMap.put("Reference Id", 2);
		columnMap.put("City", 3);
		columnMap.put("State", 4);
		columnMap.put("Pincode", 5);
		columnMap.put("Region", 6);
		columnMap.put("Phone", 7);
		columnMap.put("Delivery Type", 8);
		columnMap.put("Short Code", 9);
		columnMap.put("Address", 10);
		columnMap.put("GST IN", 11);
		columnMap.put("Manager Email Ids", 12);
		columnMap.put("Depot type", 13);
		columnMap.put("Ports Associated", 14);
		columnMap.put("Associated Material Categories", 15);

		FileInputStream fis = new FileInputStream(excelPath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(sheetName);

		int rowIndex = 1;
		
		 int start = 577;

		for (int i = start; i <= start+9; i++) {

			XSSFRow row = sheet.getRow(rowIndex);
			if (row == null) {
				row = sheet.createRow(rowIndex);
			}

			setCell(row, columnMap.get("Depot Name"), "Depot_" + i);
			setCell(row, columnMap.get("Display Reference Name"), "DEP_" + i);
			setCell(row, columnMap.get("Reference Id"), "REF_" + "06" + i);
			setCell(row, columnMap.get("City"), "Chennai");
			setCell(row, columnMap.get("State"), "Tamil Nadu");
			setCell(row, columnMap.get("Pincode"), "600001");
			setCell(row, columnMap.get("Region"), "North");
			setCell(row, columnMap.get("Phone"), "9" + (800000000 + i));
			setCell(row, columnMap.get("Delivery Type"), "Shipper Site");
			setCell(row, columnMap.get("Short Code"), "SC" + i);
			setCell(row, columnMap.get("Address"), "OMR Road");
			setCell(row, columnMap.get("GST IN"), "33ABCDE1" + i);
			setCell(row, columnMap.get("Manager Email Ids"), "manager" + i + "@test.com");
			setCell(row, columnMap.get("Depot type"), "Depot");
			setCell(row, columnMap.get("Ports Associated"), "PORT_6");
			setCell(row, columnMap.get("Associated Material Categories"), "No");

			rowIndex++;
		}

		//fis.close();

		FileOutputStream fos = new FileOutputStream(excelPath);
		workbook.write(fos);
		fos.close();
		workbook.close();

		System.out.println("Excel updated successfully with 9 rows!");
	}

	private static void setCell(XSSFRow row, int colIndex, String value) {
		XSSFCell cell = row.getCell(colIndex);
		if (cell == null) {
			cell = row.createCell(colIndex);
		}
		cell.setCellValue(value);
	}

}
