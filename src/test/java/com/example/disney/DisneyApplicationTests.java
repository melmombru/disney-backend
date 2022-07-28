package com.example.disney;

import com.example.disney.exception.CharacterException;
import com.example.disney.exception.GenreException;
import com.example.disney.exception.MovieException;
import com.example.disney.model.DisneyCharacter;
import com.example.disney.model.Genre;
import com.example.disney.model.Movie;
import com.example.disney.service.CharacterService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DisneyApplicationTests {

	CharacterService characterService;
	//	Creation tests
	@Test
	public void characterCreatedSuccessfully() {
//		List<Long> movies = new ArrayList<>();
//		movies.add(1L);
		DisneyCharacter character = new DisneyCharacter("Mickey", 91, 10,
				"Walt Disney got the inspiration for Mickey Mouse from a tame mouse at his desk at Laugh-O-Gram Studio in Kansas City, " +
						"Missouri. In 1925, Hugh Harman drew some sketches of mice around a photograph of Walt Disney. " +
						"These inspired Ub Iwerks to create a new mouse character for Disney.",
				1L);
		DisneyCharacter characterResponse = characterService.loadCharacter(character);

		assertEquals("Mickey", characterResponse.getName());
		assertEquals(91, characterResponse.getAge());
		assertEquals(10, characterResponse.getWeight());
		assertEquals("Walt Disney got the inspiration for Mickey Mouse from a tame mouse at his desk " +
						"at Laugh-O-Gram Studio in Kansas City, Missouri. In 1925, Hugh Harman drew some sketches of " +
						"mice around a photograph of Walt Disney. These inspired Ub Iwerks to create a new mouse character for Disney.",
				characterResponse.getHistory());
		assertEquals(1L, characterResponse.getMovies());
	}

	@Test
	public void movieCreatedSuccessfully() {

		List<DisneyCharacter> characters = new ArrayList<>();
		DisneyCharacter character = new DisneyCharacter("Mickey", 91, 10,
				"Walt Disney got the inspiration for Mickey Mouse from a tame mouse at his desk at Laugh-O-Gram Studio in Kansas City, " +
						"Missouri. In 1925, Hugh Harman drew some sketches of mice around a photograph of Walt Disney. " +
						"These inspired Ub Iwerks to create a new mouse character for Disney.",
				null);
		characters.add(character);
		Movie movie = new Movie("Fantasia", "1940-11-13", 4, characters);

		assertEquals("Fantasia", movie.getTitle());
		assertEquals("1940-11-13", movie.getDate().toString());
		assertEquals(4, movie.getQualifications());
		assertEquals("Mickey", movie.getCharacters().get(0).getName());
	}

	@Test
	public void genreCreatedSuccessfully() {
		List<Movie> movies = new ArrayList<>();
		Movie movie = new Movie("Fantasia", "1940-11-13", 4, null);
		movies.add(movie);
		Genre genre = new Genre("Comedy", movies);

		assertEquals("Comedy", genre.getName());
		assertEquals("Fantasia", genre.getMovies().get(0).getTitle());
	}

	//	Exceptions tests
	@Test
	public void characterFailedCreationWithoutNameAndNegativeAge() {
		assertThrows(CharacterException.class, () -> new DisneyCharacter("", 91, 10,
				"Walt Disney got the inspiration for Mickey Mouse from a tame mouse at his desk at Laugh-O-Gram Studio in Kansas City, " +
						"Missouri. In 1925, Hugh Harman drew some sketches of mice around a photograph of Walt Disney. " +
						"These inspired Ub Iwerks to create a new mouse character for Disney.",
				null));
		assertThrows(CharacterException.class, () -> new DisneyCharacter("Mickey", -1, 10,
				"Walt Disney got the inspiration for Mickey Mouse from a tame mouse at his desk at Laugh-O-Gram Studio in Kansas City, " +
						"Missouri. In 1925, Hugh Harman drew some sketches of mice around a photograph of Walt Disney. " +
						"These inspired Ub Iwerks to create a new mouse character for Disney.",
				null));
	}

	@Test
	public void movieFailedCreationWithoutTitleAndWrongQualification() {
		assertThrows(MovieException.class, () -> new Movie("", "1940-11-13", 4, null));
		assertThrows(MovieException.class, () -> new Movie("Fantasia", "1940-11-13", -4, null));
		assertThrows(MovieException.class, () -> new Movie("Fantasia", "1940-11-13", 14, null));
	}

	@Test
	public void genreFailedCreationWithoutName() {
		assertThrows(GenreException.class, () -> new Genre("", null));
	}

	//	Edition tests
	@Test
	public void characterChangedSuccessfully() {
		DisneyCharacter character = new DisneyCharacter("Mickey", 91, 10,
				"Walt Disney got the inspiration for Mickey Mouse from a tame mouse at his desk at Laugh-O-Gram Studio in Kansas City, " +
						"Missouri. In 1925, Hugh Harman drew some sketches of mice around a photograph of Walt Disney. " +
						"These inspired Ub Iwerks to create a new mouse character for Disney.",
				null);

		assertEquals("Mickey", character.getName());
		assertEquals(91, character.getAge());
		assertEquals(10, character.getWeight());
		assertEquals("Walt Disney got the inspiration for Mickey Mouse from a tame mouse at his desk " +
						"at Laugh-O-Gram Studio in Kansas City, Missouri. In 1925, Hugh Harman drew some sketches of " +
						"mice around a photograph of Walt Disney. These inspired Ub Iwerks to create a new mouse character for Disney.",
				character.getHistory());

//		List<Long> movies = new ArrayList<>();
//		movies.add(1L);
		character.setMovies(1L);
		character.setAge(90);
		character.setName("Donald Duck");
		character.setHistory("Donald was created by Walt Disney when he heard Clarence Nash doing a peculiar" +
				" voice while reciting \"Mary Had a Little Lamb\"." +
				" Nash described the voice as that of a baby goat. Walt, however, insisted that it was a duck.");
		character.setWeight(12);

		assertEquals("Donald Duck", character.getName());
		assertEquals(90, character.getAge());
		assertEquals(12, character.getWeight());
		assertEquals("Donald was created by Walt Disney when he heard Clarence Nash doing a peculiar" +
						" voice while reciting \"Mary Had a Little Lamb\"." +
						" Nash described the voice as that of a baby goat. Walt, however, insisted that it was a duck.",
				character.getHistory());
		assertEquals(1L, character.getMovies());
	}

	@Test
	public void movieChangedSuccessfully() {

		Movie movie = new Movie("Fantasia", "1940-11-13", 4, null);

		assertEquals("Fantasia", movie.getTitle());
		assertEquals("1940-11-13", movie.getDate().toString());
		assertEquals(4, movie.getQualifications());

		List<DisneyCharacter> characters = new ArrayList<>();
		DisneyCharacter character = new DisneyCharacter("Mickey", 91, 10,
				"Walt Disney got the inspiration for Mickey Mouse from a tame mouse at his desk at Laugh-O-Gram Studio in Kansas City, " +
						"Missouri. In 1925, Hugh Harman drew some sketches of mice around a photograph of Walt Disney. " +
						"These inspired Ub Iwerks to create a new mouse character for Disney.",
				null);
		characters.add(character);

		movie.setCharacters(characters);
		movie.setDate("1999-12-17");
		movie.setTitle("Fantasia 2000");
		movie.setQualification(5);

		assertEquals("Fantasia 2000", movie.getTitle());
		assertEquals("1999-12-17", movie.getDate().toString());
		assertEquals(5, movie.getQualifications());
		assertEquals("Mickey", movie.getCharacters().get(0).getName());
	}

	@Test
	public void genreChangedSuccessfully() {
		Genre genre = new Genre("Comedy", null);

		assertEquals("Comedy", genre.getName());

		List<Movie> movies = new ArrayList<>();
		Movie movie = new Movie("Fantasia", "1940-11-13", 4, null);
		movies.add(movie);

		genre.setName("Musical");
		genre.setMovies(movies);

		assertEquals("Musical", genre.getName());
		assertEquals("Fantasia", genre.getMovies().get(0).getTitle());
	}
}
