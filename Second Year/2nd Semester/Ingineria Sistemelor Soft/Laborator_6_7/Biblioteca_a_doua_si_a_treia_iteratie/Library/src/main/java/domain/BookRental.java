package domain;

public class BookRental implements Entity<Integer>{
    private int id;
    private int client;
    private int book;
    private int returned;
    private int days;

    public BookRental(){}

    public BookRental(int client, int book, int returned, int days) {
        this.client = client;
        this.book = book;
        this.returned = returned;
        this.days = days;
    }

    public BookRental(int id, int client, int book, int returned, int days) {
        setId(id);
        this.client = client;
        this.book = book;
        this.returned = returned;
        this.days = days;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer integer) {
        this.id = integer;
    }
}
