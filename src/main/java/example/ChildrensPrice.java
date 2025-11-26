package example;

class ChildrensPrice extends Price {
    @Override
    int getPriceCode() {
        return Movie.MovieType.CHILDRENS.ordinal();
    }

    @Override
    double getCharge(int daysRented) {
        double result = 1.5;
        if (daysRented > 3)
            result += (daysRented - 3) * 1.5;
        return result;
    }
}
