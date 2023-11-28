package kodlama.io.rentACar.entities.concretes;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="brand_id")
	private int id; 
	
	
	@Column(name = "name")
	private String name;
	
	
	@OneToMany(mappedBy ="brand")
	List<Model> models;
	
	
	
	
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
