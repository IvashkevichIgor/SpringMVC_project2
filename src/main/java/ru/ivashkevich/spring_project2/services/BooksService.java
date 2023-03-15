package ru.ivashkevich.spring_project2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivashkevich.spring_project2.models.Book;
import ru.ivashkevich.spring_project2.models.Person;
import ru.ivashkevich.spring_project2.repositories.BooksRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> getAllBooks(){
        return booksRepository.findAll();
    }

    public Book getBookById(int id){
        return booksRepository.findById(id).orElse(null);
    }

    @Transactional
    public void create(Book book){
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook){
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id){
        booksRepository.deleteById(id);
    }

    @Transactional
    public void assignOwner(int bookId, Person person){
        booksRepository.findById(bookId).ifPresent(book -> book.setOwner(person));
    }

    @Transactional
    public void releaseBookFromOwner(int id){
        booksRepository.findById(id).ifPresent(book -> book.setOwner(null));
    }
}
