package kodlama.io.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import kodlama.io.rentACar.business.abstracts.BrandService;
import kodlama.io.rentACar.core.utilities.mappers.ModelMapperService;
import kodlama.io.rentACar.dataAccess.abstracts.BrandRepository;
import kodlama.io.rentACar.entities.concretes.Brand;
import kodlama.io.rentACar.requests.CreateBrandRequest;
import kodlama.io.rentACar.requests.DeleteBrandRequest;
import kodlama.io.rentACar.requests.UpdateBrandRequest;
import kodlama.io.rentACar.responses.GetAllBrandsResponse;
import kodlama.io.rentACar.responses.GetByIdBrandResponse;
import lombok.AllArgsConstructor;


//bu class bir servistir  ifadesini yükler
@Service 
@AllArgsConstructor


public class BrandManager  implements BrandService{

	private BrandRepository brandRepository;
	
	/*
	public BrandManager(BrandRepository brandRepository) {
		super();
		this.brandRepository = brandRepository;
	}
	 */
	
	private ModelMapperService modelMapperService;
	



	@Override
	public List<GetAllBrandsResponse> getAll() {
		
		List<Brand> brands = this.brandRepository.findAll();
		
		//brandleri stream et (dolaş ) map et arrow funct ile brand kolonlarını maple ve list olarak dön
		List<GetAllBrandsResponse> brandResponse = brands.stream()
				.map(brand -> this.modelMapperService.forResponse()
						.map(brand, GetAllBrandsResponse.class)).collect(Collectors.toList());
		
		
		/* MAPLEMEDEN ONCE 
		 * 
		 * List<Brand> brands = brandRepository.findAll();
		 * 
		 * List<GetAllBrandsResponse> brandResponse = new
		 * ArrayList<GetAllBrandsResponse>();
		 * 
		 * for(Brand brand : brands) {
		 * 
		 * GetAllBrandsResponse responseItem = new GetAllBrandsResponse();
		 * responseItem.setId(brand.getId()); responseItem.setName(brand.getName());
		 * 
		 * brandResponse.add(responseItem);
		 * 
		 * }
		 */
			return brandResponse;
		 
		
		
	}

	
	
	
	
	

	@Override
	public void add( CreateBrandRequest createBrandRequest) {
		
		Brand brand = this.modelMapperService.forRequest()
				.map(createBrandRequest, Brand.class); //mapledikten sonra 
		
		//brand.setName(createBrandRequest.getName()); maplenmeden önce
		
		this.brandRepository.save(brand);
		
		
	}







	@Override
	public GetByIdBrandResponse getById(int id) {
		// TODO Auto-generated method stub
		
		Brand brand=  this.brandRepository.findById(id).orElseThrow();
		
		GetByIdBrandResponse resp = this.modelMapperService.forResponse().map(brand, GetByIdBrandResponse.class);
		
		
		return resp;
	}







	@Override
	public void update(UpdateBrandRequest updateBrandRequest) {
		// TODO Auto-generated method stub
		
		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		
			this.brandRepository.save(brand);
		
	}







	@Override
	public void delete(DeleteBrandRequest deleteBrandRequest) {
		// TODO Auto-generated method stub
		
		Brand brand = this.modelMapperService.forRequest().map(deleteBrandRequest, Brand.class);
		
		this.brandRepository.delete(brand);

		
	}
	
	
	




}
