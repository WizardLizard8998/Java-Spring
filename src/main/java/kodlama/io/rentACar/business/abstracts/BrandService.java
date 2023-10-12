package kodlama.io.rentACar.business.abstracts;

import java.util.*;

import kodlama.io.rentACar.requests.CreateBrandRequest;
import kodlama.io.rentACar.responses.GetAllBrandsResponse;

public interface BrandService  {
	
	List<GetAllBrandsResponse> getAll();
	
	void add(CreateBrandRequest createBrandRequest);

}
