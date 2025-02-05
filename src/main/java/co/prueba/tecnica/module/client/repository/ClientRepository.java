package co.prueba.tecnica.module.client.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.prueba.tecnica.module.client.entity.Client;

public interface ClientRepository extends JpaRepository<Client,Long>{
	
	Optional<Client> findByEmail(String email);
	Optional<List<Client>> findByStatus(String status);
	Optional<Client>  findByIdentificationNumber(Long identificationNumber);
	Optional<Client> findByEmailAndIdentificationNumber(String email, Long identificationNumber);
}
