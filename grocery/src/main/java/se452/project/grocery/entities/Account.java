package se452.project.grocery.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
// by convention, the table name is usually plural. We can add
// @Table(name="Accounts") to fix this
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer uid;

	// Can add @Colum(name="em") to change the table column name
	@NotNull
	@NotBlank
	@Email(message = "Email not valid")
	private String email;

	// we will using a random 16 length str to replace user password
	@Size(min = 8, message = "Not Enough characters")
	private String password;

	// concate salt and input to generate a hashvalue, then concate hashvalue and
	// the random password to generate this verified hashvalue
	private String verified;

	@Enumerated(EnumType.STRING)
	private Role role;

}