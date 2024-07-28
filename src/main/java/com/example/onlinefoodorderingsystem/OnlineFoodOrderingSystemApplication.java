package com.example.onlinefoodorderingsystem;

import com.example.onlinefoodorderingsystem.entity.Buddha_Bless;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineFoodOrderingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineFoodOrderingSystemApplication.class, args);
		Buddha_Bless buddha_Bless = new Buddha_Bless();
		Buddha_Bless.buddha_bless();

	}

}
