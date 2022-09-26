package com.book.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column
	private String logo;
	@Column
	private String title;

	@Column
	@Enumerated(EnumType.STRING)
	private Category category;

	@Min(value = 1, message = "price cannot be less than 1")
	private BigDecimal price;

	@Column
	private String author;

	@Column
	private String publisher;

	@Column
	private String content;

	@Column
	private Boolean active;

	
	private String publishedDate;

}
