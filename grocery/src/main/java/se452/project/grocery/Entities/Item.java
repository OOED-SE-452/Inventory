package se452.project.grocery.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer uid;
	private String name;
	private double price;

}
