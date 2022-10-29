package se452.project.grocery.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table
public class Item {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer uid;

	@Column(unique = true) //
	@NotEmpty
	private String name;

	@Column
	@NotNull
	@NumberFormat(style = Style.NUMBER)
	@Positive
	@Min(1)
	private Double price;

	@Column
	@NotNull
	@NumberFormat(style = Style.NUMBER)
	@Positive
	@Min(1)
	private Integer quantity;
}
