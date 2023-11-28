package kodlama.io.rentACar.entities.concretes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Car")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Car_id")
	private int id;

	 @Column(name="plate")
	 private String plate;
	 
	 @Column(name="modelYear")
	 private String modelYear;
	
	 @Column(name="state")
	 private int state;
	 
	 @Column(name="dailyPrice")
	 private int dailyPrice;
	 
	 
	 @OneToOne()
	 @JoinColumn(
			 name="model_id",
			 foreignKey= @ForeignKey(name="FK_model_car")
			 )
	 	 private Model model;
	 
	 
}
