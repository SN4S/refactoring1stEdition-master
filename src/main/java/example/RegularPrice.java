package example;

class RegularPrice extends Price {
    @Override
    int getPriceCode() {
        return Movie.MovieType.REGULAR.ordinal(); // Or just return the constant if I change Movie to use int
    }

    @Override
    double getCharge(int daysRented) {
        double result = 2;
        if (daysRented > 2)
            result += (daysRented - 2) * 1.5;
        return result;
    }
}
