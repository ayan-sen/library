package com.darts.library.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Document(collection = "book")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Book implements Serializable {

	private static final long serialVersionUID = -1673167062038099712L;
	@Id
	@JsonSerialize(using = ToStringSerializer.class)
	private ObjectId id;
	private String title;
	private String author;
	private LocalDate publishedDate;
	private String isbn;
	private List<Map<String, Object>> reviews;
}

