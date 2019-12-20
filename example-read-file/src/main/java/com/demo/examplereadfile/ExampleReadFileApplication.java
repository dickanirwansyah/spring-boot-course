package com.demo.examplereadfile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ExampleReadFileApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleReadFileApplication.class, args);
	}

}


@Slf4j
@Component
class ExampleReadFileWithFormatTXT implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		/** read file .csv file by default **/
//		List<Person> peoples = new ArrayList<>();
//		String line = "";
//		try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/file/example.csv")))){
//			while ((line = br.readLine())!=null){
//				String[] person = line.split(",");
//				//System.out.println("Person[username="+person[0]+",phone="+person[1]+", street="+person[2]+"]");
//				Person dataPerson = new Person();
//				dataPerson.setUsername(String.valueOf(person[0]));
//				dataPerson.setPhone(String.valueOf(person[1]));
//				dataPerson.setStreet(String.valueOf(person[2]));
//				peoples.add(dataPerson);
//			}
//			log.info("Data From .csc : "+peoples.toString());
//		}catch (FileNotFoundException e){
//			log.error(e.getLocalizedMessage());
//		}catch (IOException e){
//			log.error(e.getLocalizedMessage());
//		}
		/** read file .txt file by default **/
		String line = "";
		List<Person> peoples = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/file/example.txt")))){
			while ((line = br.readLine())!=null){
				String[] person = line.split("\\|");
//				System.out.println("data 0 : "+person[0]);
//				System.out.println("data 1 : "+person[1]);
//				System.out.println("data 2 : "+person[2]);
//				System.out.println("data 3 : "+person[3]);
//				System.out.println("data 4 : "+person[4]);
				Person dataPerson = new Person();
				dataPerson.setUsername(String.valueOf(person[1]));
				dataPerson.setPhone(String.valueOf(person[4]));
				peoples.add(dataPerson);
			}
			log.info("Data From .txt : "+peoples.toString());
		}catch (FileNotFoundException e){
			log.info(e.getLocalizedMessage());
		}catch (IOException e){
			log.info(e.getLocalizedMessage());
		}

		/**action read file csv file **/
//		String locationFile = "/file/example.csv";
//		ArrayList<Person> personArrayList = readCsvFilePerson(locationFile);
	}

	/** read .txt file **/


	/** read .csv file **/
	private ArrayList<Person> readCsvFilePerson(String fileCsv){
		ArrayList<Person> personArrayList = new ArrayList<>();
		Person resultPersonFromCsv = null;
		String row = "";
		try (BufferedReader bufReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(fileCsv)))){
			while ((row = bufReader.readLine())!=null){
				String[] dataPersons = row.split(",");
				resultPersonFromCsv = createPerson(dataPersons);
				personArrayList.add(resultPersonFromCsv);
				/**log.info("Data Person : "+resultPersonFromCsv.toString());**/
			}
		}catch (FileNotFoundException e){
			log.error(e.getLocalizedMessage());
		}catch (Exception e){
			log.error(e.getLocalizedMessage());
		}
		/**System.out.println(personArrayList);**/
		return personArrayList;
	}

	private Person createPerson(String[] dataFromCsvFile){
		Person person = new Person();
		person.setUsername(dataFromCsvFile[0]);
		person.setPhone(dataFromCsvFile[1]);
		person.setStreet(dataFromCsvFile[2]);
		return person;
	}
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Person implements Serializable {

	private String username;
	private String phone;
	private String street;

}