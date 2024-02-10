package org.mains.Cine;

import org.mains.Cine.services.ICinemaInitService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor
public class CineApplication implements CommandLineRunner{

	private ICinemaInitService cinemaInitService;
	
	public static void main(String[] args) {
		SpringApplication.run(CineApplication.class, args);
	}
	
	@Override
	public void run(String... args) {
		cinemaInitService.initVilles();
		cinemaInitService.initCinemas();
		cinemaInitService.initSalles();
		cinemaInitService.initPlaces();
		cinemaInitService.initCategories();
		cinemaInitService.initFilms();
		cinemaInitService.initProjectionFilms();
		cinemaInitService.initTickets();
		cinemaInitService.initCustomers();
	}
}
