package se452.project.grocery.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import se452.project.grocery.Role;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
//by convention, the table name is usually plural. We can add @Table(name="Accounts") to fix this
@Document
public class AccountMango {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private String uid;
	
	//Can add @Colum(name="em") to change the table column name
	@NotNull
	@NotBlank
	@Email(message = "Email not valid")
	private String email;

	//we will using a random 16 length str to replace user password
	@Size(min = 8, message = "Not Enough characters")
	private String password;

	//concate salt and input to generate a hashvalue, then concate hashvalue and the random password to generate this verified hashvalue
	private String verified;
	
	@Enumerated(EnumType.STRING)
	private Role role;

}