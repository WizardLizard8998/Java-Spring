package kodlama.io.rentACar.core.utilities.mappers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor

@RequiredArgsConstructor
public class ModelMapperManager implements ModelMapperService{

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ModelMapper forRequest() {
		
		this.modelMapper.getConfiguration()
		.setAmbiguityIgnored(true)
		.setMatchingStrategy(MatchingStrategies.LOOSE); // gevşek olunca sadece var olan kolonları eşliyor.
		
		return this.modelMapper;
		
	}

	@Override
	public ModelMapper forResponse() {
		

		this.modelMapper.getConfiguration()
		.setAmbiguityIgnored(true)
		.setMatchingStrategy(MatchingStrategies.STANDARD); // standart olunca tam eşleme yapıyor.
		
		return this.modelMapper;
		
		
	}

}
