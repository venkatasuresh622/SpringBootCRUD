package com.zensar.WriteDataToExcel;

import java.io.*;
import java.sql.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
public class SimpleDb2ExcelExporter {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
        new SimpleDb2ExcelExporter().export();
    }

	private void export() throws ClassNotFoundException, SQLException {
		
		String excelFilePath = "Reviews_export.xlsx";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","sara");
		
		try {
			
			  String sql = "SELECT * FROM review";
			  System.out.println(sql);
			  
	            Statement statement = connection.createStatement();
	 
	            ResultSet result = statement.executeQuery(sql);
	 
	            XSSFWorkbook workbook = new XSSFWorkbook();
	            XSSFSheet sheet = workbook.createSheet("Reviews");
	 
	            writeHeaderLine(sheet);
	 
	            writeDataLines(result, workbook, sheet);
	 
	            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
	            workbook.write(outputStream);
	            workbook.close();
	 
	            statement.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		
	}

	private void writeDataLines(ResultSet result, XSSFWorkbook workbook, XSSFSheet sheet) throws SQLException {
		int rowCount = 1;
		 
        while (result.next()) {
            String courseName = result.getString("course_name");
            System.out.println(courseName);
            String studentName = result.getString("student_name");
            float rating = result.getFloat("rating");
            Timestamp timestamp = result.getTimestamp("timestamp");
            System.out.println(timestamp);
 
            Row row = sheet.createRow(rowCount++);
 
            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(courseName);
 
            cell = row.createCell(columnCount++);
            cell.setCellValue(studentName);
 
            cell = row.createCell(columnCount++);
 
            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper creationHelper = workbook.getCreationHelper();
            cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
            cell.setCellStyle(cellStyle);
 
            cell.setCellValue(timestamp);
 
            cell = row.createCell(columnCount++);
            cell.setCellValue(rating);
 

        }
	}

	private void writeHeaderLine(XSSFSheet sheet) {
		Row headerRow = sheet.createRow(0);
		
		Cell headerCell = headerRow.createCell(0);
		headerCell.setCellValue("Course Name");
	
		headerCell = headerRow.createCell(1);
		headerCell.setCellValue("Student Name");
		
		headerCell = headerRow.createCell(2);
		headerCell.setCellValue("Timestamp");
		
		headerCell = headerRow.createCell(3);
		headerCell.setCellValue("Rating");

		
	}
}
