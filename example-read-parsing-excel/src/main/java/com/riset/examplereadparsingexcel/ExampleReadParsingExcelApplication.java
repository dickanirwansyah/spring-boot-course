package com.riset.examplereadparsingexcel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;

@SpringBootApplication
public class ExampleReadParsingExcelApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleReadParsingExcelApplication.class, args);
	}
}

/** test read file excel **/
@Component
class CommandLineRunnerReadExcelFile implements CommandLineRunner {

	@Autowired
	private ResourceLoader resourceLoader;

	@Override
	public void run(String... args) throws Exception {

		Workbook workbook = WorkbookFactory.create(new FileInputStream
				(new File("src/main/resources/data-parser.xlsx")));
		System.out.println("Workbook has "+workbook.getNumberOfSheets() + " Sheets : ");

		// 1. You can obtain a sheetIterator and iterate over it
		Iterator<Sheet> sheetIterator = workbook.sheetIterator();
		System.out.println("Retrieve sheets using iterator");
		while (sheetIterator.hasNext()){
			Sheet sheet = sheetIterator.next();
			System.out.println("=> "+ sheet.getSheetName());
		}

		// Getting the Sheet at index zero
		Sheet sheet = workbook.getSheetAt(0);
		DataFormatter dataFormatter = new DataFormatter();

		// 1. You can obtain a rowIterator and columnIterator and iterate over them
		System.out.println("\n\nIterating over Rows and Columns using Iterator\n");
		Iterator<Row> rowIterator = sheet.rowIterator();
		while (rowIterator.hasNext()){
			Row row = rowIterator.next();

			// Now let's iterate over the columns of the current row
			Iterator<Cell> cellIterator = row.cellIterator();

			while (cellIterator.hasNext()){
				Cell cell = cellIterator.next();

				String cellValue = dataFormatter.formatCellValue(cell);

				System.out.print(cellValue + "\t");
			}

			System.out.println();

		}
	}
}
