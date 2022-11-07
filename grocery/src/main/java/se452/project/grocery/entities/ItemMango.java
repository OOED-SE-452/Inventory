package se452.project.grocery.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document
public class ItemMango implements Serializable {
	
	@Id
	@Column
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
