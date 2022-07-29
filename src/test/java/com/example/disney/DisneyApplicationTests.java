package com.example.disney;

import com.example.disney.exception.CharacterException;
import com.example.disney.exception.GenreException;
import com.example.disney.exception.MovieException;
import com.example.disney.model.DisneyCharacter;
import com.example.disney.model.Genre;
import com.example.disney.model.Movie;
import com.example.disney.service.CharacterService;
import com.example.disney.service.GenresService;
import com.example.disney.service.MoviesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DisneyApplicationTests {
	@Autowired
	CharacterService characterService;
	@Autowired
	MoviesService moviesService;
	@Autowired
	GenresService genresService;
	//	Creation tests
	@Test
	public void characterCreatedSuccessfully() {
		DisneyCharacter character = new DisneyCharacter("Mickey", 91, 10,
				"Walt Disney got the inspiration for Mickey Mouse from a tame mouse at his desk at Laugh-O-Gram Studio in Kansas City, Missouri." , 1L);
		DisneyCharacter characterResponse = characterService.loadCharacter(character);

		assertEquals("Mickey", characterResponse.getName());
		assertEquals(91, characterResponse.getAge());
		assertEquals(10, characterResponse.getWeight());
		assertEquals("Walt Disney got the inspiration for Mickey Mouse from a tame mouse at his desk at Laugh-O-Gram Studio in Kansas City, Missouri.",
				characterResponse.getHistory());
		assertEquals(1L, characterResponse.getMovies());
	}

	@Test
	public void movieCreatedSuccessfully() {
		Movie movie = new Movie("Fantasia", "1940-11-13", 4, 1L);

		Movie movieResponse = moviesService.loadMovie(movie);
		assertEquals("Fantasia", movieResponse.getTitle());
		assertEquals("1940-11-13", movieResponse.getDate().toString());
		assertEquals(4, movieResponse.getQualifications());
	}

	@Test
	public void genreCreatedSuccessfully() {
//		List<Movie> movies = new ArrayList<>();
//		Movie movie = new Movie("Fantasia", "1940-11-13", 4, null);
//		movies.add(movie);
		Genre genre = new Genre("Comedy", 1L);

		Genre genreResponse = genresService.loadGenre(genre);

		assertEquals("Comedy", genreResponse.getName());
		assertEquals(1L, genreResponse.getMovies());
	}

	//	Exceptions tests
	@Test
	public void characterFailedCreationWithoutNameAndNegativeAge() {
		DisneyCharacter characterEmptyName = new DisneyCharacter("", 91, 10,
				"Walt Disney got the inspiration for Mickey Mouse from a tame mouse at his desk at Laugh-O-Gram Studio in Kansas City, Missouri.",
				null);
		assertThrows(CharacterException.class, () -> characterService.loadCharacter(characterEmptyName));

		DisneyCharacter characterNegativeAge = new DisneyCharacter("Mickey", -1, 10,
				"Walt Disney got the inspiration for Mickey Mouse from a tame mouse at his desk at Laugh-O-Gram Studio in Kansas City, Missouri.",
				null);
		assertThrows(CharacterException.class, () -> characterService.loadCharacter(characterNegativeAge));
	}

	@Test
	public void movieFailedCreationWithoutTitleAndWrongQualification() {
		Movie movieEmptyName = new Movie("", "1940-11-13", 4, null);
		assertThrows(MovieException.class, () -> moviesService.loadMovie(movieEmptyName));

		Movie movieNegativeQualification = new Movie("Fantasia", "1940-11-13", -4, null);
		assertThrows(MovieException.class, () -> moviesService.loadMovie(movieNegativeQualification));

		Movie movieOverQualification = new Movie("Fantasia", "1940-11-13", 14, null);
		assertThrows(MovieException.class, () -> moviesService.loadMovie(movieOverQualification));
	}

	@Test
	public void genreFailedCreationWithoutName() {
		Genre genreEmptyName = new Genre("", null);
		assertThrows(GenreException.class, () -> genresService.loadGenre(genreEmptyName));
	}

	//	Edition tests
	@Test
	public void characterChangedSuccessfully() {
		DisneyCharacter character = new DisneyCharacter("Mickey", 91, 10,
				"Walt Disney got the inspiration for Mickey Mouse from a tame mouse at his desk at Laugh-O-Gram Studio in Kansas City, Missouri.",
				null);

		DisneyCharacter characterResponse = characterService.loadCharacter(character);

		assertEquals("Mickey", characterResponse.getName());
		assertEquals(91, characterResponse.getAge());
		assertEquals(10, characterResponse.getWeight());
		assertEquals("Walt Disney got the inspiration for Mickey Mouse from a tame mouse at his desk at Laugh-O-Gram Studio in Kansas City, Missouri.",
				characterResponse.getHistory());

		characterResponse.setMovies(1L);
		characterResponse.setAge(90);
		characterResponse.setName("Donald Duck");
		characterResponse.setHistory("Donald was created by Walt Disney when he heard Clarence Nash doing a peculiar" +
				" voice while reciting \"Mary Had a Little Lamb\".");
		characterResponse.setWeight(12);

		DisneyCharacter characterModifyResponse = characterService.modifyCharacter(characterResponse);
		assertEquals("Donald Duck", characterModifyResponse.getName());
		assertEquals(90, characterModifyResponse.getAge());
		assertEquals(12, characterModifyResponse.getWeight());
		assertEquals("Donald was created by Walt Disney when he heard Clarence Nash doing a peculiar" +
						" voice while reciting \"Mary Had a Little Lamb\".",
				characterModifyResponse.getHistory());
		assertEquals(1L, characterModifyResponse.getMovies());
	}

	@Test
	public void movieChangedSuccessfully() {

		Movie movie = new Movie("Fantasia", "1940-11-13", 4, null);

		Movie movieResponse = moviesService.loadMovie(movie);

		assertEquals("Fantasia", movieResponse.getTitle());
		assertEquals("1940-11-13", movieResponse.getDate().toString());
		assertEquals(4, movieResponse.getQualifications());

		movieResponse.setCharacters(1L);
		movieResponse.setDate("1999-12-17");
		movieResponse.setTitle("Fantasia 2000");
		movieResponse.setQualification(5);

		Movie movieModify = moviesService.modifyMovie(movieResponse);

		assertEquals("Fantasia 2000", movieModify.getTitle());
		assertEquals("1999-12-17", movieModify.getDate().toString());
		assertEquals(5, movieModify.getQualifications());
		assertEquals(1L, movieModify.getCharacters());
	}

	@Test
	public void genreChangedSuccessfully() {
		Genre genre = new Genre("Comedy", null);

		Genre genreResponse = genresService.loadGenre(genre);

		assertEquals("Comedy", genreResponse.getName());

		genreResponse.setName("Musical");
		genreResponse.setMovies(1L);

		Genre genreModify = genresService.modifyGenre(genreResponse);

		assertEquals("Musical", genreModify.getName());
		assertEquals(1L, genreModify.getMovies());
	}
}
