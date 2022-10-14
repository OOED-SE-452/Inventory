package se452.project.grocery.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import se452.project.grocery.Role;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
//by convention, the table name is usually plural. We can add @Table(name="Accounts") to fix this
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer uid;
	
	//Can add @Colum(name="em") to change the table column name
	private String email;
	private String password;
	
	
	@Enumerated(EnumType.STRING)
	private Role role;

}