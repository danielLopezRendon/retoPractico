package co.prueba.tecnica.module.client.utils;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public abstract class ConvertObjects {
	
	public <D, E> List<D> returnListDto(List<E> listSource, Class<D> target) {
		var modelMapper = new ModelMapper();
        return listSource.stream().map(entidad -> modelMapper.map(entidad, target)).toList();
	}
	
	public <D, E> D returnDto(E source, Class<D> target) {
		var modelMapper = new ModelMapper();
        return modelMapper.map(source, target);
	}

}
