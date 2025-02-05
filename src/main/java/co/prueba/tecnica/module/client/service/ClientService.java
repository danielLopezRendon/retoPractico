package co.prueba.tecnica.module.client.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import co.prueba.tecnica.module.client.component.ClientComponent;
import co.prueba.tecnica.module.client.dto.ClientDto;
import co.prueba.tecnica.module.client.entity.Client;
import co.prueba.tecnica.module.client.utils.ConvertObjects;
import co.prueba.tecnica.module.client.utils.EcosystemException;
import co.prueba.tecnica.module.client.utils.EcosystemSuccessResponse;
import jakarta.validation.Valid;

@Service
public class ClientService extends ConvertObjects {
	
	@Autowired
	private ClientComponent clientComponent;
	
	public EcosystemSuccessResponse<String> registrarCliente( ClientDto clientDto)throws EcosystemException {
		return new EcosystemSuccessResponse<String>(null, clientComponent.registrarCliente(clientDto), HttpStatus.OK.value());
	}
	
	public EcosystemSuccessResponse<String> actualizarCliente( ClientDto clientDto)throws EcosystemException {
		
		return new EcosystemSuccessResponse<String>(null,clientComponent.actualizarCliente(clientDto), HttpStatus.ACCEPTED.value());
	}
	
	public ClientDto consultarCliente(Long identificacion) {
		return returnDto(clientComponent.consultarCliente(identificacion), ClientDto.class);
	}

	public List<ClientDto> consultarClientesActivos() {
		return returnListDto(clientComponent.consultarClientesActivos(), ClientDto.class);
		
	}
	
}
