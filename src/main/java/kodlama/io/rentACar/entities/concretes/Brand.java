package kodlama.io.rentACar.entities.concretes;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name= "brands") 		// jakarta persistance api  javax.persistance  sayesinde entity framework gibi oluyo işte
@Entity
@Getter						// lombok sayesinde senin yerine class getter setter vs getiriyor.
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Brand {

	private int id; 
	private String name;
	
	/*
	 * public Brand(int id, String name) {
	 * 
	 * this.id = id; this.name = name; }
	 * 
	 * public int getId() { return id; }
	 * 
	 * public void setId(int id) { this.id = id; }
	 * 
	 * public String getName() { return name; }
	 * 
	 * public void setName(String name) { this.name = name; }
	 */	
	
	
	
}
