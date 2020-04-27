package id.dicka.springexceltojson.service;

import id.dicka.springexceltojson.model.Agent;
import id.dicka.springexceltojson.util.UploadUtil;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class UploadService {

    private final UploadUtil uploadUtil;

    public UploadService(UploadUtil uploadUtil) {
        this.uploadUtil = uploadUtil;
    }

    public List<Map<String, String>> upload(MultipartFile file) throws Exception{
        Path tempDir = Files.createTempDirectory("");

        File tempFile = tempDir.resolve(file.getOriginalFilename()).toFile();

        file.transferTo(tempFile);

        //excel execution
        Workbook workbook =  WorkbookFactory.create(tempFile);

        Sheet sheet = workbook.getSheetAt(0);

        Supplier<Stream<Row>> rowStreamSupplier = uploadUtil.getRowStreamSupplier(sheet);

        Row headerRow = rowStreamSupplier.get().findFirst().get();

        List<String> headerCells =  uploadUtil.getStream(headerRow)
                .map(Cell::getStringCellValue)
                .collect(Collectors.toList());

        int colCount = headerCells.size();

        System.out.println(headerCells);

        return rowStreamSupplier.get()
                .skip(1)
                .map(row -> {
            //given a row, get cellStream from it !
            List<String> cellList = uploadUtil.getStream(row)
                    .map(Cell::getStringCellValue)
                    .collect(Collectors.toList());

            //parsing excel to object model
            Agent agent = new Agent();
            agent.setFirstname(cellList.get(0));
            agent.setLastname(cellList.get(1));
            List<Agent> agents = new ArrayList<>();
            agents.add(agent);
            System.out.println(agents);

            //default return
             return uploadUtil.cellIteratorSupplier(colCount)
                     .get()
                     .collect(Collectors.toMap(headerCells::get, cellList::get));

        }).collect(Collectors.toList());
    }
}
