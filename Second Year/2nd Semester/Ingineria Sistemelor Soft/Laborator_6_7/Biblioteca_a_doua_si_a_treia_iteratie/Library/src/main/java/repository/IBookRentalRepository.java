package repository;

import domain.BookRental;

public interface IBookRentalRepository extends IRepository<Integer, BookRental>{
    public void returnBook(BookRental bookRental);
}
