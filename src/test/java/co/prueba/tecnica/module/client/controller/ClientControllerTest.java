package co.prueba.tecnica.module.client.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import co.prueba.tecnica.module.client.dto.ClientDto;
import co.prueba.tecnica.module.client.service.ClientService;
import co.prueba.tecnica.module.client.utils.EcosystemException;
import co.prueba.tecnica.module.client.utils.EcosystemSuccessResponse;

class ClientControllerTest {

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;

    public ClientControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearCliente() throws EcosystemException {
        
        ClientDto clientDto = new ClientDto();
        clientDto.setEmail("test@example.com");
        clientDto.setName("Juan");
        clientDto.setIdentificationNumber(123456L);
        clientDto.setAddress("Calle Ficticia 123");
        clientDto.setPhone(300123456L);
        clientDto.setStatus("A");

    
        EcosystemSuccessResponse<String> successResponse = new EcosystemSuccessResponse<>("Cliente creado con éxito", 200);

        
        when(clientService.registrarCliente(any(ClientDto.class))).thenReturn(successResponse);

        ResponseEntity<EcosystemSuccessResponse<String>> response = clientController.crearCliente(clientDto);

       
        verify(clientService, times(1)).registrarCliente(any(ClientDto.class));


    }

    @Test
    void testActualizarCliente() throws EcosystemException {
       
        ClientDto clientDto = new ClientDto();
        clientDto.setEmail("test@example.com");
        clientDto.setIdentificationNumber(123456L);
        clientDto.setName("Juan Actualizado");
        clientDto.setAddress("Calle Actualizada 123");
        clientDto.setPhone(300123457L);
        clientDto.setStatus("A");

   
        EcosystemSuccessResponse<String> successResponse = new EcosystemSuccessResponse<>("Cliente actualizado con éxito", 202);

       
        when(clientService.actualizarCliente(any(ClientDto.class))).thenReturn(successResponse);

   
        ResponseEntity<EcosystemSuccessResponse<String>> response = clientController.actualizarCliente(clientDto);

      
        verify(clientService, times(1)).actualizarCliente(any(ClientDto.class));

    }

    @Test
    void testConsultarCliente() {
       
        ClientDto clientDto = new ClientDto();
        clientDto.setEmail("test@example.com");
        clientDto.setIdentificationNumber(123456L);
        clientDto.setName("Juan");

        
        when(clientService.consultarCliente(anyLong())).thenReturn(clientDto);

        
        ResponseEntity<ClientDto> response = clientController.consultarCliente(123456L);

      
        verify(clientService, times(1)).consultarCliente(anyLong());

        
        assertEquals(HttpStatus.OK, response.getStatusCode());

    
        assertEquals("Juan", response.getBody().getName());
    }

    @Test
    void testConsultarClientesActivos() {
       
        List<ClientDto> clientDtos = List.of(new ClientDto("test@example.com", "Juan", 123456L, "Calle Ficticia", 300123456L, "A"));

       
        when(clientService.consultarClientesActivos()).thenReturn(clientDtos);

        ResponseEntity<List<ClientDto>> response = clientController.consultarClientesActivos();

        verify(clientService, times(1)).consultarClientesActivos();

        assertEquals(HttpStatus.OK, response.getStatusCode());

     
        assertFalse(response.getBody().isEmpty());
    }
}
