package example.service;

import example.model.Customer;
import example.model.Rental;
import org.springframework.stereotype.Service;

@Service
public class StatementService {

    private final RentalService rentalService;

    public StatementService(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    public String generateStatement(Customer customer) {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + customer.getName() + "\n";
        
        for (Rental each : customer.getRentals()) {
            double thisAmount = rentalService.calculateAmount(each);
            frequentRenterPoints += rentalService.calculateFrequentRenterPoints(each);
            
            //show figures for this rental
            result += "\t" + each.getMovie().getTitle()+ "\t" + thisAmount + "\n";
            totalAmount += thisAmount;
        }
        
        //add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }
}
