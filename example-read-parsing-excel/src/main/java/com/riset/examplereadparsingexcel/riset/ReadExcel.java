package com.riset.examplereadparsingexcel.riset;

import com.riset.examplereadparsingexcel.model.Agent;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ReadExcel {

//    public static void main(String[] args) throws IOException {
//
//        String excelFile = "data-parser.xlsx";
//        try {
//            //read location file
//            FileInputStream inputStream = new FileInputStream(new File("src/main/resources/"+excelFile));
//            Workbook workbook = WorkbookFactory.create(inputStream);
//
//            Sheet firstSheet = workbook.getSheetAt(0);
//            Iterator<Row> iterator = firstSheet.iterator();
//
//            while (iterator.hasNext()){
//                Row nextRow = iterator.next();
//                Iterator<Cell> cellIterator = nextRow.cellIterator();
//
//                while (cellIterator.hasNext()){
//                    Cell cell = cellIterator.next();
//
//                    switch (cell.getCellType()){
//                        case Cell.CELL_TYPE_STRING:
//                            System.out.print(cell.getStringCellValue());
//                            break;
//                        case Cell.CELL_TYPE_BOOLEAN:
//                            System.out.print(cell.getBooleanCellValue());
//                            break;
//                        case Cell.CELL_TYPE_NUMERIC:
//                            System.out.print(cell.getNumericCellValue());
//                            break;
//
//                    }
//
//                    System.out.print(" - ");
//                }
//                System.out.println("");
//            }
//
//            workbook.close();
//
//        } catch (InvalidFormatException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) throws IOException {
        String file = "data-parser.xlsx";
        List<Agent> agents = readAgentFromExcelFile(file);
        System.out.println(agents);
    }

    private static Object getCellValue(Cell cell){
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();
            case Cell.CELL_TYPE_NUMERIC:
                return cell.getNumericCellValue();
        }
        return null;
    }


    public static List<Agent> readAgentFromExcelFile(String excelFile) throws IOException{
        List<Agent> agents = new ArrayList<>();
        //read file excel by location
        String locationFile = "src/main/resources/"+excelFile;
        InputStream inputStream = new FileInputStream(new File(locationFile));
        Workbook workbook = null;
        Sheet firstSheet = null;
        Iterator<Row> iterator = null;

        try {
            workbook = WorkbookFactory.create(inputStream);
            firstSheet = workbook.getSheetAt(0);
            iterator = firstSheet.iterator();

            while (iterator.hasNext()){
                Row nextRow = iterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                Agent agent = new Agent();

                while (cellIterator.hasNext()){
                    Cell nextCell = cellIterator.next();
                    int columnIndex = nextCell.getColumnIndex();

                    switch (columnIndex){
                        case 0:
                            Double id = (Double) getCellValue(nextCell);
                            agent.setId(String.valueOf(id));
                            break;
                        case 1:
                            agent.setName((String) getCellValue(nextCell));
                            break;
                        case 2:
                            agent.setEmail((String) getCellValue(nextCell));
                            break;
                        case 3:
                            String registerDate = (String) getCellValue(nextCell);
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            Date dateRegister = null;
                            try {
                                dateRegister = dateFormat.parse(registerDate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            agent.setRegisterDate(dateRegister);
                            break;
                        case 4:
                            String trainingDate = (String) getCellValue(nextCell);
                            SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                            Date dateTraining = null;
                            try{
                                dateTraining = dateFormat1.parse(trainingDate);
                            }catch (ParseException e){
                                e.printStackTrace();
                            }
                            agent.setTrainingDate(dateTraining);
                            break;
                    }
                }
                agents.add(agent);
            }

        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

        workbook.close();
        inputStream.close();
        return agents;
    }
}
