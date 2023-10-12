package kodlama.io.rentACar.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import kodlama.io.rentACar.business.abstracts.BrandService;
import kodlama.io.rentACar.dataAccess.abstracts.BrandRepository;
import kodlama.io.rentACar.entities.concretes.Brand;
import kodlama.io.rentACar.requests.CreateBrandRequest;
import kodlama.io.rentACar.responses.GetAllBrandsResponse;



@Service // bu class bir servistir ifadesini yükler
public class BrandManager  implements BrandService{

	private BrandRepository brandRepository;
	
	
	
	public BrandManager(BrandRepository brandRepository) {
		super();
		this.brandRepository = brandRepository;
	}



	@Override
	public List<GetAllBrandsResponse> getAll() {
		
		
		  List<Brand> brands = brandRepository.findAll();
		  
		  List<GetAllBrandsResponse> brandResponse = new
		  ArrayList<GetAllBrandsResponse>();
		  
		  for(Brand brand : brands) {
		  
		  GetAllBrandsResponse responseItem = new GetAllBrandsResponse();
		  responseItem.setId(brand.getId()); 
		  responseItem.setName(brand.getName());
		  
		  brandResponse.add(responseItem);
		  
		  }
			return brandResponse;
		 
		
		
	}


	@Override
	public void add(CreateBrandRequest createBrandRequest) {
		
		Brand brand = new Brand();
		
		brand.setName(createBrandRequest.getName());
		
		this.brandRepository.save(brand);
		
	}




}
