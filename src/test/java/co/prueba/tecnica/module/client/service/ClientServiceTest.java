package co.prueba.tecnica.module.client.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.prueba.tecnica.module.client.component.ClientComponent;
import co.prueba.tecnica.module.client.dto.ClientDto;
import co.prueba.tecnica.module.client.utils.EcosystemException;
import co.prueba.tecnica.module.client.utils.EcosystemSuccessResponse;

class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientComponent clientComponent;

    public ClientServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegistrarCliente() throws EcosystemException {
  
        ClientDto clientDto = new ClientDto("test@example.com", "Juan", 123456L, "Calle Ficticia 123", 300123456L, "A");

        when(clientComponent.registrarCliente(any(ClientDto.class))).thenReturn("Cliente registrado");

        
        EcosystemSuccessResponse<String> response = clientService.registrarCliente(clientDto);

      
        verify(clientComponent, times(1)).registrarCliente(any(ClientDto.class));

      
        assertEquals("Cliente registrado", response.getResponse().getMessage());
    }
}