package co.prueba.tecnica.module.client.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import co.prueba.tecnica.module.client.dto.ClientDto;
import co.prueba.tecnica.module.client.utils.EcosystemException;

@Component
public class ClientValidacionesComponent {

	public void validarRegistrosClient(List<ClientDto> clientsDto) throws EcosystemException {
		clientsDto.forEach(client->this.validarRegistroClient(client));
	}
	
	public void validarRegistroClient(ClientDto clientDto) throws EcosystemException  {
		
		List<String> soluciones =new ArrayList<String>();
		List<String> errores =new ArrayList<String>();
		soluciones.add("Valide la información que esta enviando.");
		errores.add("No se permiten datos nulos,ceros o vacios en el campo mencionado.");
		if(clientDto.getIdentificationNumber() == null ||clientDto.getIdentificationNumber()==0) {
			throw new EcosystemException("El campo  identificationNumber no puede ser nulo o cero.",
					errores, soluciones);
		}
		if(clientDto.getEmail() == null ||clientDto.getEmail().isEmpty()) {
			throw new EcosystemException("El campo email no puede ser nulo o vacio.",
					errores, soluciones);
		}
		if(clientDto.getName() == null ||clientDto.getName().isEmpty()) {
			throw new EcosystemException("El campo name no puede ser nulo o vacio.",
					errores, soluciones);
		}
		if(clientDto.getAddress() == null ||clientDto.getAddress().isEmpty()) {
			throw new EcosystemException("El campo address no puede ser nulo o vacio.",
					errores, soluciones);
		}
		if(clientDto.getStatus() == null ||clientDto.getStatus().isEmpty()) {
			throw new EcosystemException("El campo status no puede ser nulo o vacio.",
					errores, soluciones);
		}
	}
}
