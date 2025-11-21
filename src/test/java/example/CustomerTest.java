package example;

import org.junit.Test;

import java.util.List;

import static example.Movie.MovieType.*;
import static org.junit.Assert.assertEquals;

public class CustomerTest {

    @Test
    public void testStatementRegularMovie() {
        Movie movie = new Movie("Regular Movie", Movie.MovieType.REGULAR);
        Rental rental = new Rental(movie, 1);
        Customer customer = new Customer("John Doe", List.of(rental));

        String expected = "Rental Record for John Doe\n" +
                "\tRegular Movie\t2.0\n" +
                "Amount owed is 2.0\n" +
                "You earned 1 frequent renter points";

        assertEquals(expected, customer.statement());
    }

    @Test
    public void testStatementRegularMovieMoreThanTwoDays() {
        Movie movie = new Movie("Regular Movie", Movie.MovieType.REGULAR);
        Rental rental = new Rental(movie, 3);
        Customer customer = new Customer("John Doe", List.of(rental));

        String expected = "Rental Record for John Doe\n" +
                "\tRegular Movie\t3.5\n" +
                "Amount owed is 3.5\n" +
                "You earned 1 frequent renter points";

        assertEquals(expected, customer.statement());
    }

    @Test
    public void testStatementNewReleaseMovie() {
        Movie movie = new Movie("New Release", Movie.MovieType.NEW_RELEASE);
        Rental rental = new Rental(movie, 1);
        Customer customer = new Customer("John Doe", List.of(rental));

        String expected = "Rental Record for John Doe\n" +
                "\tNew Release\t3.0\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter points";

        assertEquals(expected, customer.statement());
    }

    @Test
    public void testStatementNewReleaseMovieMoreThanOneDay() {
        Movie movie = new Movie("New Release", Movie.MovieType.NEW_RELEASE);
        Rental rental = new Rental(movie, 2);
        Customer customer = new Customer("John Doe", List.of(rental));

        String expected = "Rental Record for John Doe\n" +
                "\tNew Release\t6.0\n" +
                "Amount owed is 6.0\n" +
                "You earned 2 frequent renter points";

        assertEquals(expected, customer.statement());
    }

    @Test
    public void testStatementChildrensMovie() {
        Movie movie = new Movie("Children's Movie", Movie.MovieType.CHILDRENS);
        Rental rental = new Rental(movie, 1);
        Customer customer = new Customer("John Doe", List.of(rental));

        String expected = "Rental Record for John Doe\n" +
                "\tChildren's Movie\t1.5\n" +
                "Amount owed is 1.5\n" +
                "You earned 1 frequent renter points";

        assertEquals(expected, customer.statement());
    }

    @Test
    public void testStatementChildrensMovieMoreThanThreeDays() {
        Movie movie = new Movie("Children's Movie", Movie.MovieType.CHILDRENS);
        Rental rental = new Rental(movie, 4);
        Customer customer = new Customer("John Doe", List.of(rental));

        String expected = "Rental Record for John Doe\n" +
                "\tChildren's Movie\t3.0\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter points";

        assertEquals(expected, customer.statement());
    }

    @Test
    public void testStatementMultipleRentals() {
        Movie regular = new Movie("Regular", Movie.MovieType.REGULAR);
        Movie newRelease = new Movie("New Release", Movie.MovieType.NEW_RELEASE);
        Movie childrens = new Movie("Childrens", Movie.MovieType.CHILDRENS);

        Rental r1 = new Rental(regular, 2); // 2.0
        Rental r2 = new Rental(newRelease, 3); // 9.0, 2 points
        Rental r3 = new Rental(childrens, 3); // 1.5

        Customer customer = new Customer("John Doe", List.of(r1, r2, r3));

        String expected = "Rental Record for John Doe\n" +
                "\tRegular\t2.0\n" +
                "\tNew Release\t9.0\n" +
                "\tChildrens\t1.5\n" +
                "Amount owed is 12.5\n" +
                "You earned 4 frequent renter points";

        assertEquals(expected, customer.statement());
    }

    @Test
    public void testStatementNoRentals() {
        Customer customer = new Customer("John Doe", List.of());

        String expected = "Rental Record for John Doe\n" +
                "Amount owed is 0.0\n" +
                "You earned 0 frequent renter points";

        assertEquals(expected, customer.statement());
    }
}