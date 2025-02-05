package co.prueba.tecnica.module.client.utils;

import java.util.List;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;

public class EcosystemError {

	
	@Schema(description = "Código HTTP de devolución que genera en el error.")
    private HttpStatus status;
	
	
	@Schema(description = "Código HTTP de devolución que genera en el error.")
	private int statusCode;
    
	@Schema(description = "Mensaje principal que describe el error.")
    private String message;
    
  
	@Schema(description = "Lista de errores o validaciones fallidas que se generan.")
    private List<String> errors;
    
   
	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public List<String> getSolutions() {
		return solutions;
	}

	public void setSolutions(List<String> solutions) {
		this.solutions = solutions;
	}

	@Schema(description = "Lista de soluciones o recomendaciones para evitar la generación del error.")
    private List<String> solutions;
	
    public EcosystemError() {
        super();
    }

    public EcosystemError(final HttpStatus status, final String message, final List<String> errors, List<String> solutions) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.solutions = solutions;
       
    }
    




  
    

    

 


  
	
}
