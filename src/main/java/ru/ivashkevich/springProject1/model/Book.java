package ru.ivashkevich.springProject1.model;

import javax.validation.constraints.NotEmpty;

public class Book {
    private int id;
    
    private String title;
    @NotEmpty(message = "Имя автора не должно быть пустым")
    private String author;
    private int publicationYear;

    public Book(){}

    public Book(int bookId, String title, String author, int publicationYear) {
        this.id = bookId;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public int getBookId() {
        return id;
    }

    public void setBookId(int bookId) {
        this.id = bookId;
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
                "bookId=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                '}';
    }
}
