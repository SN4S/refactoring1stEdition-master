# Refactoring

The original monolithic `Customer.statement()` method has been refactored into a more object-oriented design.

### 1. Extract and Move Methods
- **Logic Distribution**: Calculation logic was moved from `Customer` to `Rental` and then to `Movie`.
- **`getCharge()`**: Calculates the cost for a rental.
- **`getFrequentRenterPoints()`**: Calculates the frequent renter points.

### 2. Replace Conditional with Polymorphism
- **State/Strategy Pattern**: Introduced a `Price` abstract class to handle pricing rules.
- **Subclasses**:
    - `RegularPrice`
    - `NewReleasePrice`
    - `ChildrensPrice`
- **Benefit**: The `switch` statement in `Movie` was replaced with polymorphic calls to the `Price` object. This makes it easier to add new price codes or change pricing rules without modifying the `Movie` class.

## Project Structure

- **`Customer`**: Generates the statement.
- **`Rental`**: Represents a customer's rental of a movie.
- **`Movie`**: Represents the movie title and price code.
- **`Price`**: Abstract strategy for pricing.
