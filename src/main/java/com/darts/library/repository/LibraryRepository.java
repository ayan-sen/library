package com.darts.library.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.darts.library.model.Book;

@Repository
public class LibraryRepository {

	private MongoTemplate mongotemplate;
	
	@Autowired
	public LibraryRepository(MongoTemplate mongotemplate) {
		super();
		this.mongotemplate = mongotemplate;
	}

	public boolean addBook(Book book) {
		try {
			mongotemplate.insert(book);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public List<Book> findByTitle(String title) {
		  Query query = new Query(Criteria.where("title").regex(title));
		  return mongotemplate.find(query, Book.class);
	}
	
	
}
