package com.example.smsys;

import com.example.smsys.model.Smsys;
import com.example.smsys.repo.SmsysRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

import static com.example.smsys.enumeration.Status.STUDENT_DOWN;
import static com.example.smsys.enumeration.Status.STUDENT_UP;

@SpringBootApplication
public class SmsysApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmsysApplication.class, args);
	}

	@Bean
	CommandLineRunner run(SmsysRepo smsysRepo) {
		return args -> {
			smsysRepo.save(new Smsys(null, "johndoe@gmail.com", "172.146.1.179", "John Doe", "Sophomore", "HU201, SS201, NS201, MA201", "http://localhost:8080/smsys/img-stu/stu1.png", STUDENT_UP));
			smsysRepo.save(new Smsys(null, "khailabbas@yahoo.com", "172.146.1.196", "Khail Abbas", "Junior", "EN301, PS301, CH202, MA302", "http://localhost:8080/smsys/img-stu/stu2.png", STUDENT_UP));
			smsysRepo.save(new Smsys(null, "jillianlawrence@outlook.com", "172.146.1.182 ", "Jillian Lawrence", "Freshman", "SS101, MA102, EN101, PS101", "http://localhost:8080/smsys/img-stu/stu3.png", STUDENT_DOWN));
			smsysRepo.save(new Smsys(null, "ibrahimakodra@gmail.com", "172.146.1.176 ", "Ibrahim Kodra", "Senior", "HU402, PS401, EN302, CH401", "http://localhost:8080/smsys/img-stu/stu4.png", STUDENT_UP));
			smsysRepo.save(new Smsys(null, "darius_slayton@proton.me", "172.146.1.150 ", "Darius Slayton", "Sophomore", "HU202, EN201, MA102, FR301", "http://localhost:8080/smsys/img-stu/stu5.png", STUDENT_UP));
			smsysRepo.save(new Smsys(null, "fqadir@outlook.com,", "172.146.1.82", "Farah Qadir", "Freshman", "PS102, SP101, EN101, MA102", "http://localhost:8080/smsys/img-stu/stu6.png", STUDENT_DOWN));
			smsysRepo.save(new Smsys(null, "calridley@gmail.com", "172.46.1.113 ", "Calvin Ridley", "Senior", "EN302, CH402, MA401, FR401", "http://localhost:8080/smsys/img-stu/stu7.png", STUDENT_DOWN));
			smsysRepo.save(new Smsys(null, "@aimesullivan@aol.com", "172.46.1.130 ", "Aime Sullivan", "Junior", "SP301, SS301, MA302, NS302", "http://localhost:8080/smsys/img-stu/stu8.png", STUDENT_UP));
			smsysRepo.save(new Smsys(null, "dorothy_williams@proton.me", "172.46.1.42 ", "Dorothy Williams", "Sophomore", "EN201, CH201, PS202, SS202", "http://localhost:8080/smsys/img-stu/stu9.png", STUDENT_UP));
			// Note: When running on localhost, feel free to implement your own 10th Student! Using the form in the front, or in the back here using the above formatting.
			// Their default profile picture is stu10 in the corresponding image folder.
		};
	}
	// Configuration for connection between front & back
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}


}
