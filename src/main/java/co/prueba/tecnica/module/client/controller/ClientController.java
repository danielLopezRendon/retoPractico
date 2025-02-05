package co.prueba.tecnica.module.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.prueba.tecnica.module.client.dto.ClientDto;
import co.prueba.tecnica.module.client.service.ClientService;
import co.prueba.tecnica.module.client.utils.EcosystemError;
import co.prueba.tecnica.module.client.utils.EcosystemException;
import co.prueba.tecnica.module.client.utils.EcosystemSuccessResponse;
import co.prueba.tecnica.module.client.utils.IgnoreResponse;
import co.prueba.tecnica.module.client.utils.ResponseAdvise;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("api/V1")
public class ClientController extends ResponseAdvise{

	@Autowired
	private ClientService clientService;
	
	@IgnoreResponse
	@Operation(summary = "Creación de clientes.", description = "Este metodo se usa para realizar la creación de clientes.")
	@ApiResponse(responseCode = "200", description = "Service available.", content = { @Content(schema = @Schema(implementation = EcosystemSuccessResponse.class)) })
	@ApiResponse(responseCode = "500", description = "Service not available.", content = { @Content(schema = @Schema(implementation = EcosystemError.class)) })
	@PostMapping(value = "/crear-cliente", produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<EcosystemSuccessResponse<String>> crearCliente (
			@RequestBody (required = true)
			ClientDto ClientDto)throws EcosystemException {
		EcosystemSuccessResponse<String>  ecosystemSuccessResponse=clientService.registrarCliente(ClientDto);
		HttpStatus httpStatus;
		if(ecosystemSuccessResponse.getResponse().getStatusCode()>0) {
			 httpStatus= HttpStatus.valueOf(ecosystemSuccessResponse.getResponse().getStatusCode());
		}else {
			httpStatus=HttpStatus.valueOf(500);
		}
		
		return new ResponseEntity<>(ecosystemSuccessResponse,httpStatus);	    	
	}
	
	@IgnoreResponse
	@Operation(summary = "Actualización de clientes.", description = "Este metodo se usa para actualizar clientes.")
	@ApiResponse(responseCode = "202", description = "Service available.", content = { @Content(schema = @Schema(implementation = EcosystemSuccessResponse.class)) })
	@ApiResponse(responseCode = "500", description = "Service not available.", content = { @Content(schema = @Schema(implementation = EcosystemError.class)) })
	@PutMapping(value = "/actualizar-cliente", produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<EcosystemSuccessResponse<String>> actualizarCliente (
			@RequestBody (required = true)
			ClientDto ClientDto)throws EcosystemException {
		EcosystemSuccessResponse<String>  ecosystemSuccessResponse=clientService.actualizarCliente(ClientDto);
		HttpStatus httpStatus;
		if(ecosystemSuccessResponse.getResponse().getStatusCode()>0) {
			 httpStatus= HttpStatus.valueOf(ecosystemSuccessResponse.getResponse().getStatusCode());
		}else {
			httpStatus=HttpStatus.valueOf(500);
		}
		
		return new ResponseEntity<>(ecosystemSuccessResponse,httpStatus );	    	
	}
	

	
	@Operation(summary = "Devuelve la informacion de un cliente.", description = "Este metodo se usa para consultar la informacion de un cliente.")
	@ApiResponse(responseCode = "200", description = "Consulta realizada correctamente.", content = { @Content(array = @ArraySchema(schema = @Schema(implementation = ClientDto.class)))})	
	@ApiResponse(responseCode = "500", description = "Error al procesar la consulta.", content = { @Content(schema = @Schema(implementation = EcosystemError.class)) })
	@GetMapping(value = "/consultar-cliente")	
	public ResponseEntity<ClientDto> consultarCliente(
			@RequestParam(name = "identificacion",  required = true) Long identificacion) {		
		return new ResponseEntity<>(clientService.consultarCliente(identificacion),HttpStatus.OK);	    	
	}
	
	
	
	@Operation(summary = "Devuelve una lista de los clientes activos.", description = "Este metodo se usa para consultar la informacion de los clientes actvios.")
	@ApiResponse(responseCode = "200", description = "Consulta realizada correctamente.", content = { @Content(array = @ArraySchema(schema = @Schema(implementation = ClientDto.class)))})	
	@ApiResponse(responseCode = "500", description = "Error al procesar la consulta.", content = { @Content(schema = @Schema(implementation = EcosystemError.class)) })
	@GetMapping(value = "/consultar-clientes-activos")	
	public ResponseEntity<List<ClientDto>> consultarClientesActivos() {		
		return new ResponseEntity<>(clientService.consultarClientesActivos(),HttpStatus.OK);	    	
	}
	
}
