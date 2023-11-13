package kodlama.io.rentACar.webApi.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.rentACar.business.abstracts.BrandService;
import kodlama.io.rentACar.requests.CreateBrandRequest;
import kodlama.io.rentACar.requests.DeleteBrandRequest;
import kodlama.io.rentACar.requests.UpdateBrandRequest;
import kodlama.io.rentACar.responses.GetAllBrandsResponse;
import kodlama.io.rentACar.responses.GetByIdBrandResponse;

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
	public void add(@RequestBody CreateBrandRequest createBrandRequest) {
		this.brandService.add(createBrandRequest);
	}
	
	@GetMapping("/{id}")
	public GetByIdBrandResponse getById(@PathVariable int  id) {
		return this.brandService.getById(id);
		
	}
	
	@PutMapping()
	@ResponseStatus(code = HttpStatus.I_AM_A_TEAPOT) // çünkü ben bir çaydanlığıms
	public void update(@RequestBody UpdateBrandRequest updateBrandRequest) {
		
		this.brandService.update(updateBrandRequest);
		
	}
	
	
	@DeleteMapping
	public void delete(@RequestBody DeleteBrandRequest deleteBrandRequest) {
		this.brandService.delete(deleteBrandRequest);
	}
	
	
}
