package com.suresh;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;


public class Excel2DatabaseTest {

	public static void main(String[] args) throws ClassNotFoundException {
		
		String fileName="E:/Grievances Project/Feb 2012 data/22439-29-02-2012/22439-29-02-2012.xls";
		System.out.println(fileName.substring((fileName.lastIndexOf("/")+1), fileName.length()));

		String excelFilePath = "Students.xlsx";
		int batchSize = 20;

		Connection connection = null;

		try {
			long start = System.currentTimeMillis();
			
			FileInputStream inputStream = new FileInputStream(excelFilePath);
			
			Workbook workbook = new XSSFWorkbook(inputStream);
			
			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = firstSheet.iterator();
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 connection =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","sara");
			connection.setAutoCommit(false);
		
			String sql = "INSERT INTO students ( name, progress,salary) VALUES ( ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			int count=0;
		
			rowIterator.next(); // skip the header row
		
			while (rowIterator.hasNext()) {
			Row nextRow = rowIterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			
			while (cellIterator.hasNext()) {
			Cell nextCell = cellIterator.next();
			
			int columnIndex = nextCell.getColumnIndex();
			
			switch (columnIndex) {
			/*
			 * case 0: int id = (int) nextCell.getNumericCellValue(); statement.setInt(1,
			 * id);
			 */
			case 0:	
			String name = nextCell.getStringCellValue();
			statement.setString(1, name);
			break;
							
			//Date enrollDate = nextCell.getDateCellValue();
			//statement.setTimestamp(2, new Timestamp(enrollDate.getTime()));
						case 1:
			int progress = (int) nextCell.getNumericCellValue();
			statement.setInt(2, progress);
						case 2:
							int salary=(int) nextCell.getNumericCellValue();
							statement.setInt(3, salary);
			}
		}
			statement.addBatch();
			
			if (count % batchSize == 0) {
			statement.executeBatch();
			}
			
			}
			workbook.close();
	
			
			// execute the remaining queries
			statement.executeBatch();
			
			
			connection.commit();
				connection.close();
			
			long end = System.currentTimeMillis();
			System.out.printf("Import done in %d ms\n", (end - start));
			
			} catch (IOException ex1) {
				System.out.println("Error reading file");
				ex1.printStackTrace();
				} 
			catch (SQLException ex2) {
				System.out.println("Database error");
				ex2.printStackTrace();
			}
		

	}
	
					
			}
