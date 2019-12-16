package com.idgenerali.backendmybatis;

import com.idgenerali.backendmybatis.mapper.AgentMapper;
import com.idgenerali.backendmybatis.mapper.ProductMapper;
import com.idgenerali.backendmybatis.model.Agent;
import com.idgenerali.backendmybatis.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class BackendMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendMybatisApplication.class, args);
	}
}

@Component
class CommandDataTestMapper implements CommandLineRunner {

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private AgentMapper agentMapper;

	@Override
	public void run(String... args) throws Exception {
		/** array **/
//		ArrayList<Product> products = new ArrayList<>();
//		List<Product> data = productMapper.listProduct();
//		for (Product product : data){
//			products.add(product);
//		}
//		if (products.size() > 0){
//			System.out.println("YES FOUND");
//			System.out.println("DATA = "+products.toString());
//		}else {
//			System.out.println("NOT FOUND");
//		}

		/** find by id result data**/
		Optional<Product> productId1 = productMapper.findProductById(1);
		/** find by id result quantity of product**/
		Optional<Product> productId2 = productMapper.findById(1);
		/** find by name result quantity **/
		Optional<Product> productName1 = productMapper.findByName("unit link");
		/** find by name result data **/
		Product product = new Product();
		product.setName("unit link");
		Optional<Product> productName2 = productMapper.findProductByName("uni");
		/** delete product example **/
//		productMapper.deleteProduct(1);
		/** save product **/
		if (productName2.isPresent()){
			System.out.println("YES");
		}else{
			System.out.println("NO");
		}
//		Product product = new Product();
//		product.setId(4);
//		product.setName("Smart Parent");
//		product.setCategory("Masa depan");
//		product.setPrice(400000);
//		product.setStock(12);
//		product.setCreatedAt(new Date());
//		product.setUpdatedAt(new Date());
//		productMapper.updateProduct(product);

//		if (productName2.isPresent()){
//			System.out.println("FOUND : "+productName2.toString());
//		}else{
//			System.out.println("NOT FOUND : "+null);
//		}
//		if (productId1.isPresent()){
//			System.out.println("FOUND : "+productId1.toString());
//		}else{
//			System.out.println("NOT FOUND : "+null);
//		}
		/** save agent **/
//		Agent agentEntity = new Agent();
//		agentEntity.setAgentCode("GEN003");
//		agentEntity.setFirstName("Sabrina");
//		agentEntity.setLastName("Tsaqila");
//		agentEntity.setEmail("sabrina@gmail.com");
//		agentEntity.setPhoneNumber("087899920202");
//		agentEntity.setHomePhoneNumber("787878");
//		agentEntity.setDateOfBirth(new Date());
//		agentEntity.setCreatedAt(new Date());
//		agentEntity.setUpdatedAt(new Date());
//		agentEntity.setCompanyName("Cilandak Jakarta");
//		agentEntity.setAddressName("jakarta selatan");
//		agentMapper.saveAgent(agentEntity);


		/** list agent **/
//		List<Agent> data = agentMapper.listAgent();
//		ArrayList<Agent> result = new ArrayList<>();
//		for (Agent agent : data){
//			result.add(agent);
//		}
//		System.out.println("AGENT DATA :"+result.toString());
	}
}
