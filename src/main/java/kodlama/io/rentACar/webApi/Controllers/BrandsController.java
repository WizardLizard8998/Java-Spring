package kodlama.io.rentACar.webApi.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kodlama.io.rentACar.business.abstracts.BrandService;
import kodlama.io.rentACar.entities.concretes.Brand;
import kodlama.io.rentACar.requests.CreateBrandRequest;
import kodlama.io.rentACar.responses.GetAllBrandsResponse;

@RestController //annotations
@RequestMapping("/api/brands")  //endpoint route kısmı 
public class BrandsController {

	private BrandService brandService;

	@Autowired  // --> constructorun parametrelerine göz at oradan new obje getir 
	public BrandsController(BrandService brandService) {
		super();
		this.brandService = brandService;
	}
	
	@GetMapping() // method endpoint 
	public List<GetAllBrandsResponse> getAll(){
		return this.brandService.getAll();
	}
	
	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public void add(@RequestBody() CreateBrandRequest createBrandRequest) {
		this.brandService.add(createBrandRequest);
	}
	
}
