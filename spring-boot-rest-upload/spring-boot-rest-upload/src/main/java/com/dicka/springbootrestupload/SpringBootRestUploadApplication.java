package com.dicka.springbootrestupload;

import com.dicka.springbootrestupload.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class SpringBootRestUploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestUploadApplication.class, args);
	}

}
