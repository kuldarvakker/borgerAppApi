package com.qminder.borger;

import com.qminder.borger.app.domain.BurgerJoint;
import com.qminder.borger.app.domain.Photo;
import com.qminder.borger.app.repository.BurgerJointRepository;
import com.qminder.borger.app.repository.PhotoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class BorgerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BorgerApplication.class, args);
	}


	@Bean
	public CommandLineRunner demoBurgerJoint(BurgerJointRepository repository) {
		return (args) -> {
			repository.save(new BurgerJoint(1L,"","SIM*SIM", 26.694583, 58.385454, LocalDateTime.parse("2021-09-21T16:30:01.000Z", DateTimeFormatter.ISO_OFFSET_DATE_TIME)));
			repository.save(new BurgerJoint(2L,"","Grillburger", 26.703896, 58.366601, LocalDateTime.parse("2021-09-21T17:30:01.000Z", DateTimeFormatter.ISO_OFFSET_DATE_TIME)));
			repository.save(new BurgerJoint(3L,"","Ümera Hamburger", 26.701845, 58.365265, LocalDateTime.parse("2021-09-21T18:30:01.000Z", DateTimeFormatter.ISO_OFFSET_DATE_TIME)));
		};
	}

	@Bean
	public CommandLineRunner demoPhoto(PhotoRepository repository, BurgerJointRepository repository2) {
		return (args) -> {
			repository.save(
					new Photo(1L, "https://fastly.4sqi.net/img/general/original/39906_YHSUG-ygr-VYrwS3egUg__gKTBxeC5ysncYwCJfz_eA.jpg", LocalDateTime.parse("2021-09-21T16:05:01.000Z", DateTimeFormatter.ISO_OFFSET_DATE_TIME), repository2.findById(1L).get()));
			repository.save(
					new Photo(2L, "https://fastly.4sqi.net/img/general/original/32281188_z_qRpdGHOQX6cih3OAruCyDrN8hNwzr63c6KwRBzKgA.jpg", LocalDateTime.parse("2021-09-21T17:35:01.000Z", DateTimeFormatter.ISO_OFFSET_DATE_TIME), repository2.findById(2L).get()));
			repository.save(
					new Photo(3L, "https://fastly.4sqi.net/img/general/original/39906_3MloiWCNTR7dqHQXIevZ9rNrIln30HQqJ3TWEfDu4uw.jpg", LocalDateTime.parse("2021-09-21T18:35:01.000Z", DateTimeFormatter.ISO_OFFSET_DATE_TIME), repository2.findById(3L).get()));
		};
	}
}
