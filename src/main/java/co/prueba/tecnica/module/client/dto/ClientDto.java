package co.prueba.tecnica.module.client.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4767939417954152311L;

	
	private String email;


    private String name;

	
    private Long identificationNumber;

	
    private String address;

   
    private Long phone;

    private String status;

}
