package co.prueba.tecnica.module.client.component;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.prueba.tecnica.module.client.repository.ClientRepository;

class ClientComponentTest {

    @InjectMocks
    private ClientComponent clientComponent;

    @Mock
    private ClientRepository clientRepository;

    public ClientComponentTest() {
        MockitoAnnotations.openMocks(this);
    }

    
}