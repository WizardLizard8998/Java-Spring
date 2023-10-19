package kodlama.io.rentACar.business.abstracts;

import java.util.*;

import kodlama.io.rentACar.requests.CreateBrandRequest;
import kodlama.io.rentACar.requests.UpdateBrandRequest;
import kodlama.io.rentACar.responses.GetAllBrandsResponse;
import kodlama.io.rentACar.responses.GetByIdBrandResponse;



public interface BrandService  {
	
	List<GetAllBrandsResponse> getAll();
	
	//GetByIdBrandResponse getById(int id);
	
	void add(CreateBrandRequest createBrandRequest);
	
///	void update(UpdateBrandRequest updateBrandRequest);
	
//	void delete(int id);
	
	

}
