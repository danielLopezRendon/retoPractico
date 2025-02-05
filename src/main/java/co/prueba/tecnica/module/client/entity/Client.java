package co.prueba.tecnica.module.client.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="CLIENT",schema = "TEST")
public class Client {
	
	 @Id
	   @GeneratedValue(strategy =GenerationType.SEQUENCE,generator = "TEST.SEQ_CLIENT")
	   @SequenceGenerator(name="TEST.SEQ_CLIENT",sequenceName = "TEST.SEQ_CLIENT",allocationSize = 1)
	    @Column(name = "ID_CLIENT", nullable = false)
	    private Long idClient;

	    @Column(name = "EMAIL", length = 100, nullable = false)
	    private String email;

	    @Column(name = "NAME", length = 100, nullable = false)
	    private String name;

	    @Column(name = "IDENTIFICATION_NUMBER", nullable = false)
	    private Long identificationNumber;

	    @Column(name = "ADDRESS", length = 255, nullable = false)
	    private String address;

	    @Column(name = "PHONE", length = 15)
	    private Long phone;

	    @Column(name = "STATUS", length = 1, nullable = false)
	    private String status;

}
