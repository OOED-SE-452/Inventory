package se452.project.shop.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String cid;

	private String username;
	private String password;
	private String verified;

	@OneToMany
	private List<CustomerItem> shoppingCart = new ArrayList<>();

}
