package co.prueba.tecnica.module.client.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.prueba.tecnica.module.client.dto.ClientDto;
import co.prueba.tecnica.module.client.entity.Client;
import co.prueba.tecnica.module.client.repository.ClientRepository;
import co.prueba.tecnica.module.client.utils.ConvertObjects;
import co.prueba.tecnica.module.client.utils.EcosystemException;

@Component
public class ClientComponent  {
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ClientValidacionesComponent clientValidacionesComponent;
	
	private static final String MENSAJE_CREACION="Cliente registrado";
	private static final String MENSAJE_ACTUALIZACION="Cliente actualizado";
	private static final String ESTADO_ACTIVO="A";
	
	public String registrarCliente( ClientDto clientDto)  throws EcosystemException {
		List<String> soluciones =new ArrayList<String>();
		List<String> errores =new ArrayList<String>();		
		if(clientRepository.findByEmail(clientDto.getEmail()).isPresent() || clientRepository.findByIdentificationNumber(clientDto.getIdentificationNumber()).isPresent() ) {
			errores.add("No se puede registrar mas de una vez un cliente.");
				throw new EcosystemException("El cliente que esta intentando registrar ya existe.",
						errores, soluciones);		
		}
		clientValidacionesComponent.validarRegistroClient(clientDto);
		clientRepository.save(Client.builder()
				.email(clientDto.getEmail())
				.name(clientDto.getName())
				.identificationNumber(clientDto.getIdentificationNumber())
				.address(clientDto.getAddress())
				.phone(clientDto.getPhone())
				.status(clientDto.getStatus())
				.build());
		return MENSAJE_CREACION;
	}
	public String actualizarCliente(ClientDto clientDto) {
		List<String> soluciones =new ArrayList<String>();
		List<String> errores =new ArrayList<String>();	
		Optional<Client> clientOP=clientRepository.findByEmailAndIdentificationNumber(clientDto.getEmail(),clientDto.getIdentificationNumber());		
		if(clientOP.isPresent() ) {
			Client client=clientOP.get();
			clientRepository.save(client.builder()
					.name(client.getName())
					.address(client.getAddress())
					.phone(client.getPhone())
					.status(client.getStatus())
					.build());				
		}else {
			errores.add("La informacion ingresado no corresponde a un usuario registrado.");
			throw new EcosystemException("No se puede actualizar el cliente.",
					errores, soluciones);
		}
		return MENSAJE_ACTUALIZACION;
	}
	
	public Client consultarCliente(Long numeroIdentificacion) {	
		Optional<Client> clientOP=clientRepository.findByIdentificationNumber(numeroIdentificacion);		
		Client clientDto= new Client();
		if(clientOP.isPresent()) {	
			clientDto=clientOP.get();
		}else {
			throw new EcosystemException("No existe el cliente que intenta consultar.",
					null, null);
		}
		return clientDto;	
	}

	public List<Client> consultarClientesActivos() throws EcosystemException {
		Optional<List<Client>> clients=clientRepository.findByStatus(ESTADO_ACTIVO);
		if (clients.isEmpty()) {
			throw new EcosystemException("No existen  clientes activos para consultar.",
					null, null);
		}
		return clients.get();
		
	}
	
	
	
	
	

}
