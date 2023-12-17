package com.example.interestmarket;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
public class InterestmarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterestmarketApplication.class, args);
	}

	@PostConstruct
	public void init() {
		try {
			ClassPathResource serviceAccount = new ClassPathResource("serviceAccountKey.json");
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream()))
					.build();

			FirebaseApp.initializeApp(options);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
