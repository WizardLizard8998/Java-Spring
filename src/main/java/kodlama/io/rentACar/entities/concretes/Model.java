package kodlama.io.rentACar.entities.concretes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="models")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="model_id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@ManyToOne
	@JoinColumn(
			name = "brand_id" ,
			foreignKey = @ForeignKey(name="FK_brand_model" )
	)
	private Brand brand; 
	
	
}
