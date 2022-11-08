package se452.project.shop.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerMangoItem {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String uid;
	
	@Column
	private String name;

	@Column
	private Double price;

	@Column
	private Integer quantity; 

}
