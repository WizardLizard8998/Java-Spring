package kodlama.io.rentACar.core.utilities.mappers;

import org.modelmapper.ModelMapper;

public interface ModelMapperService  {

	public  ModelMapper forRequest();
	public  ModelMapper forResponse();
}
