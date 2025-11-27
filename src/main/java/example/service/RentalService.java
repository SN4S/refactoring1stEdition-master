package example.service;

import example.model.Rental;
import org.springframework.stereotype.Service;

import static example.model.Movie.MovieType.NEW_RELEASE;

@Service
public class RentalService {

    public double calculateAmount(Rental rental) {
        double thisAmount = 0;
        switch (rental.getMovie().getPriceCode()) {
            case REGULAR -> {
                thisAmount += 2;
                if (rental.getDaysRented() > 2)
                    thisAmount += (rental.getDaysRented() - 2) * 1.5;
            }
            case NEW_RELEASE -> thisAmount += rental.getDaysRented() * 3;
            case CHILDRENS -> {
                thisAmount += 1.5;
                if (rental.getDaysRented() > 3)
                    thisAmount += (rental.getDaysRented() - 3) * 1.5;
            }
        }
        return thisAmount;
    }

    public int calculateFrequentRenterPoints(Rental rental) {
        int frequentRenterPoints = 1;
        // add bonus for a two day new release rental
        if ((rental.getMovie().getPriceCode() == NEW_RELEASE) && rental.getDaysRented() > 1)
            frequentRenterPoints++;
        return frequentRenterPoints;
    }
}
