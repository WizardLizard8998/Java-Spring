package kodlama.io.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.rentACar.entities.concretes.Brand;


public interface BrandRepository extends JpaRepository<Brand, Integer>{

	//List<Brand> getAll(); //extend etmeden önce bize getiriler 
	
	
	
}
