package com.darts.library.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.darts.library.model.Book;
import com.darts.library.repository.LibraryRepository;

@RestController
public class LibraryController {

	private LibraryRepository libraryRepository;

	public LibraryController(LibraryRepository libraryRepository) {
		super();
		this.libraryRepository = libraryRepository;
	}

	@PostMapping("/book")
	public Map<String, String> addBook(@RequestBody Book book) {
		boolean status = libraryRepository.addBook(book);
		return status ? new HashMap<String, String>() {
			{
				put("status", "success");
			}
		} : new HashMap<String, String>() {
			{
				put("status", "fail");
			}
		};
	}
	
	@GetMapping("/books/title/{title}")
	public List<Book> getBooksByTitle(@PathVariable String title) {
		return libraryRepository.findByTitle(title);
	}

}
