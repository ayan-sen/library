package com.darts.library.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpMethod;

import com.darts.library.LibraryApplicationTests;
import com.darts.library.model.Book;

public class LibraryControllerTest extends LibraryApplicationTests {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Before
	public void before() {
		mongoTemplate.dropCollection("book");
	}
	
	@Test
	public void testShouldFindOneRecordWithTitle() {
		Book book = bookWithNoReview();
		mongoTemplate.save(book, "book");
		List<Book> books = restTemplate.exchange("/books/title/java", HttpMethod.GET, null, new ParameterizedTypeReference<List<Book>>() { }).getBody();
		MatcherAssert.assertThat(books.size(), Matchers.is(1));
		MatcherAssert.assertThat(books.get(0).getAuthor(), Matchers.is("Russell"));
	}
	
	
	@Test
	public void testShouldFindTwoRecordWithTitle() {
		Book book1 = bookWithNoReview();
		Book book2 = bookWithOneReview();
		mongoTemplate.save(book1, "book");
		mongoTemplate.save(book2, "book");
		List<Book> books = restTemplate.exchange("/books/title/java", HttpMethod.GET, null, new ParameterizedTypeReference<List<Book>>() { }).getBody();//.getForEntity("/books/title/java",  new TypeReference<List<Book>>() { });
		MatcherAssert.assertThat(books.size(), Matchers.is(2));
		MatcherAssert.assertThat(books.get(0).getAuthor(), Matchers.is("Russell"));
		MatcherAssert.assertThat(books.get(1).getReviews().size(), Matchers.is(1));
	}

	



	private Book bookWithNoReview() {
		Book book = Book.builder()
						.title("java")
						.author("Russell")
						.publishedDate(LocalDate.now())
						.isbn("123").build();
		return book;
	}
	
	private Book bookWithOneReview() {
		
		List<Map<String, Object>> review = new ArrayList<>();
		review.add(new HashMap<String, Object>(){{
			put("reviewerName", "John");
			put("content", "The content is really good");
			put("rating", "4.0");
			put("publishedDate", "2018-02-28");
		}});
		
		Book book = Book.builder()
						.title("java 8")
						.author("Andrew")
						.publishedDate(LocalDate.now())
						.isbn("456")
						.reviews(review)
						.build();
		return book;
	}
	
}
