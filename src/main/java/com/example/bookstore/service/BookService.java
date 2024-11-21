package com.example.bookstore.service;

import com.example.bookstore.entity.Book;
import com.example.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RestTemplate restTemplate;

    // Fetch books from an external API and save them locally
    public List<Book> fetchBooksFromApi(String apiUrl) {
        ResponseEntity<Book[]> response = restTemplate.getForEntity(apiUrl, Book[].class);
        
        // Extract book data from the API response
        List<Book> books = Arrays.asList(response.getBody());
        
        // Save the fetched data to the H2 database
        bookRepository.saveAll(books);
        return books;
    }

    // Fetch all books from the local database
    public List<Book> getAllBooks() {
      return bookRepository.findAll();
    }
    
    public Book saveBook(Book book) {
      return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book bookDetails) {
    return bookRepository.findById(id).map(existingBook -> {
        existingBook.setTitle(bookDetails.getTitle());
        existingBook.setAuthor(bookDetails.getAuthor());
        existingBook.setPrice(bookDetails.getPrice());
        return bookRepository.save(existingBook);
    }).orElseThrow(() -> new IllegalArgumentException("Book with ID " + id + " not found."));
}
    
    @DeleteMapping("/delete/{id}")
public ResponseEntity<?> deleteBook(@PathVariable Long id) {
    try {
        bookRepository.deleteById(id);
        return ResponseEntity.ok("Book with ID " + id + " deleted successfully.");
    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
}
