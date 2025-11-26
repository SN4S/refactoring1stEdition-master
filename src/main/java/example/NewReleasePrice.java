package example;

class NewReleasePrice extends Price {
    @Override
    int getPriceCode() {
        return Movie.MovieType.NEW_RELEASE.ordinal();
    }

    @Override
    double getCharge(int daysRented) {
        return daysRented * 3;
    }

    @Override
    int getFrequentRenterPoints(int daysRented) {
        return (daysRented > 1) ? 2 : 1;
    }
}
