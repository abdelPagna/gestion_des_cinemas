package org.mains.Cine.services;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.mains.Cine.domain.Categorie;
import org.mains.Cine.domain.Cinema;
import org.mains.Cine.domain.Customer;
import org.mains.Cine.domain.Film;
import org.mains.Cine.domain.Place;
import org.mains.Cine.domain.ProjectionFilm;
import org.mains.Cine.domain.Salle;
import org.mains.Cine.domain.Ticket;
import org.mains.Cine.domain.Ville;
import org.mains.Cine.repositories.CategorieRepository;
import org.mains.Cine.repositories.CinemaRepository;
import org.mains.Cine.repositories.CustomerRepository;
import org.mains.Cine.repositories.FilmRepository;
import org.mains.Cine.repositories.PlaceRepository;
import org.mains.Cine.repositories.ProjectionFilmRepository;
import org.mains.Cine.repositories.SalleRepository;
import org.mains.Cine.repositories.TicketRepository;
import org.mains.Cine.repositories.VilleRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class CinemaInitServiceImpl implements ICinemaInitService {

	private VilleRepository villeRepository;
	private CinemaRepository cinemaRepository;
	private PlaceRepository placeRepository;
	private CategorieRepository categorieRepository;
	private FilmRepository filmRepository;
	private ProjectionFilmRepository projectionRepository;
	private SalleRepository salleRepository;
	private TicketRepository ticketRepository;
	private CustomerRepository customerRepository;

	@Override
	public void initVilles() {
		Stream.of("Bafoussam", "Foumban", "Foumbot", "Douala", "Yaoundé", "Garoua").forEach(nameVille -> {
			Ville ville = new Ville();
			ville.setName(nameVille);
			villeRepository.save(ville);
		});

	}

	@Override
	public void initCustomers() {
		Stream.of("Vlaad", "Sydney", "Lamare").forEach(name -> {
            Customer customer=new Customer();
            customer.setName(name);
            customer.setEmail(name+"@apfk.org");
            customerRepository.save(customer);
        });
	}

	@Override
	public void initCinemas() {
		villeRepository.findAll().forEach(ville -> {
			Stream.of("Abbia", "Capitol", "Le Wourri", "Le Noun", "Empire", "ABC").forEach(nameCinema -> {
				Cinema cinema = new Cinema();
				cinema.setName(nameCinema);
				cinema.setNombreSalle(3 + (int) (Math.random() * 7));
				cinema.setVille(ville);
				cinemaRepository.save(cinema);
			});
		});

	}

	@Override
	public void initSalles() {
		cinemaRepository.findAll().forEach(cinema -> {
			for (int i = 0; i < cinema.getNombreSalle(); i++) {
				Salle salle = new Salle();
				salle.setName("Salle " + (i + 1));
				salle.setCinema(cinema);
				salle.setNombrePlace(15 + (int) (Math.random() * 20));
				salleRepository.save(salle);
			}
		});

	}

	@Override
	public void initPlaces() {
		salleRepository.findAll().forEach(salle -> {
			for (int i = 0; i < salle.getNombrePlace(); i++) {
				Place place = new Place();
				place.setNumero(i + 1);
				place.setSalle(salle);
				placeRepository.save(place);
			}
		});

	}

	@Override
	public void initFilms() {
		double[] durees = new double[] { 1, 1.5, 2, 2.5, 3 };
		List<Categorie> categories = categorieRepository.findAll();
		Stream.of("12 Hommes En Colere", "Forrest Gump", "Green Book", "La Ligne Verte", "Le Parrain",
				"Le Seigneur Des Anneaux").forEach(titreFilm -> {
					Film film = new Film();
					film.setTitre(titreFilm);
					film.setDuree(durees[new Random().nextInt(durees.length)]);
					film.setPhoto(titreFilm.replaceAll(" ", "") + ".jpg");
					film.setCategorie(categories.get(new Random().nextInt(categories.size())));
					filmRepository.save(film);
				});

	}

	@Override
	public void initCategories() {
		Stream.of("Fiction", "Action", "Drame", "Documentaire", "Espionnage").forEach(cat -> {
			Categorie categorie = new Categorie();
			categorie.setName(cat);
			categorieRepository.save(categorie);
		});

	}

	/*
	 * @Override public void initProjections() { double[] prices=new double[]
	 * {30,50,60,70,90,100}; villeRepository.findAll().forEach(ville->{
	 * ville.getCinemas().forEach(cinema->{ cinema.getSalles().forEach(salle->{
	 * filmRepository.findAll().forEach(film->{
	 *
	 * //seanceRepository.findAll().forEach(seance->{ ProjectionFilm
	 * projectionFilm=new ProjectionFilm(); projectionFilm.setDateProjection(new
	 * Date()); projectionFilm.setFilm(film); projectionFilm.setPrix(prices[new
	 * Random().nextInt(prices.length)]); projectionFilm.setSalle(salle);
	 * //projectionFilm.setSeance(seance);
	 * projectionRepository.save(projectionFilm); //}); }); }); }); });
	 *
	 * }
	 */

	@Override
	public void initTickets() {
		projectionRepository.findAll().forEach(projectionFilm -> {
			projectionFilm.getSalle().getPlaces().forEach(place -> {
				Ticket ticket = new Ticket();
				ticket.setPrix(projectionFilm.getPrix());
				ticket.setProjectionFilm(projectionFilm);
				ticket.setReservee(false);
				ticketRepository.save(ticket);
			});
		});

	}

	@Override
	public void initProjectionFilms() {
		double[] prices=new double[]{30,50,60,70,90,100};
        villeRepository.findAll().forEach(ville -> {
            ville.getCinemas().forEach(cinema -> {
                cinema.getSalles().forEach(salle ->{
                    filmRepository.findAll().forEach(film -> {
                            ProjectionFilm projectionFilm=new ProjectionFilm();
                            projectionFilm.setDateProjection(new Date());
                            projectionFilm.setFilm(film);
                            projectionFilm.setPrix(prices[new Random().nextInt(prices.length)]);
                            projectionFilm.setSalle(salle);
                            projectionRepository.save(projectionFilm);
                    });
                });
            });
        });

	}

}
