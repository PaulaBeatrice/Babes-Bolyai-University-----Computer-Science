package domain;

public class Book implements  Entity<Integer>{
    private int id;
    private String title;
    private String author;
    private String publishing_house;
    private int year_of_publication;

    public Book(){}

    public Book(int id, String title, String author, String publishing_house, int year_of_publication) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishing_house = publishing_house;
        this.year_of_publication = year_of_publication;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer integer) {
        this.id = integer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishing_house() {
        return publishing_house;
    }

    public void setPublishing_house(String publishing_house) {
        this.publishing_house = publishing_house;
    }

    public int getYear_of_publication() {
        return year_of_publication;
    }

    public void setYear_of_publication(int year_of_publication) {
        this.year_of_publication = year_of_publication;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publishing_house='" + publishing_house + '\'' +
                ", year_of_publication=" + year_of_publication +
                '}';
    }
}
