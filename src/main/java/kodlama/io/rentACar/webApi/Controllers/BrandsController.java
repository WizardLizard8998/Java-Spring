package kodlama.io.rentACar.webApi.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.rentACar.business.abstracts.BrandService;
import kodlama.io.rentACar.entities.concretes.Brand;

@RestController //annotations
@RequestMapping("/api/brands")  //endpoint route kısmı 
public class BrandsController {

	private BrandService brandService;

	// @Autowired  --> constructorun parametrelerine göz at oradan new obje getir 
	public BrandsController(BrandService brandService) {
		super();
		this.brandService = brandService;
	}
	
	@GetMapping("/getAll") // method endpoint 
	public List<Brand> getAll(){
		return brandService.getAll();
	}
	
}
