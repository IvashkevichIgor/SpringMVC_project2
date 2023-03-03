package ru.ivashkevich.springProject1.model;

import javax.validation.constraints.NotEmpty;

public class Book {
    private int bookId;
    private Person owner;
    @NotEmpty(message = "Название книги не должно быть пустым")
    private String title;
    @NotEmpty(message = "Имя автора не должно быть пустым")
    private String author;
    private int publicationYear;

    public Book(){}

    public Book(int bookId, Person owner, String title, String author, int publicationYear) {
        this.bookId = bookId;
        this.owner = owner;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
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

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", owner=" + owner +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                '}';
    }
}
