package example;

public class Movie {
    private String title;
    private Price price;

    public enum MovieType {
        REGULAR, NEW_RELEASE, CHILDRENS
    }

    public Movie(String title, MovieType priceCode) {
        this.title = title;
        setPriceCode(priceCode);
    }

    public MovieType getPriceCode() {
        return MovieType.values()[price.getPriceCode()];
    }

    public void setPriceCode(MovieType arg) {
        switch (arg) {
            case REGULAR -> price = new RegularPrice();
            case CHILDRENS -> price = new ChildrensPrice();
            case NEW_RELEASE -> price = new NewReleasePrice();
            default -> throw new IllegalArgumentException("Incorrect Price Code");
        }
    }

    public String getTitle (){
        return title;
    }

    public double getCharge(int daysRented) {
        return price.getCharge(daysRented);
    }

    public int getFrequentRenterPoints(int daysRented) {
        return price.getFrequentRenterPoints(daysRented);
    }
}