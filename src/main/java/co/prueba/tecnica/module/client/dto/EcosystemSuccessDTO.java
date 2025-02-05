package co.prueba.tecnica.module.client.dto;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class EcosystemSuccessDTO<T> implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3454499152716173436L;

    private transient T body;
   
    private int length = 1;
    
    private String message = null;
    
    private int statusCode;
    
    private StringBuilder logProcess;
    
    public EcosystemSuccessDTO() {
    }
    
    
    public T getBody() {
		return body;
	}


	public void setBody(T body) {
		this.body = body;
	}


	public int getLength() {
		return length;
	}


	public void setLength(int length) {
		this.length = length;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public int getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}


	public StringBuilder getLogProcess() {
		return logProcess;
	}


	public void setLogProcess(StringBuilder logProcess) {
		this.logProcess = logProcess;
	}


	public EcosystemSuccessDTO(T body, int length, String message) {
        this.body = body;
        this.length = length;
        this.message = message;
        if (length == 0) {
            if (this.body instanceof List) {
                this.length = ((List<?>) this.body).size();
            }

            if (this.body instanceof Map) {
                this.length = ((Map<?, ?>) this.body).size();
            }
        }

    }
     
    public EcosystemSuccessDTO(T body, String message) {
        this.body = body;
        this.message = message;

        if (this.body instanceof List) {
            this.length = ((List<?>) this.body).size();
        }

        if (this.body instanceof Map) {
            this.length = ((Map<?, ?>) this.body).size();
        }
    }
    
    public EcosystemSuccessDTO(T body, String message, int statusCode) {
    	this.body = body;
    	this.message = message;
    	this.statusCode = statusCode;
    	
    	if (this.body instanceof List) {
    		this.length = ((List<?>) this.body).size();
    	}
    	
    	if (this.body instanceof Map) {
    		this.length = ((Map<?, ?>) this.body).size();
    	}
    }
    
    public EcosystemSuccessDTO(T body, Integer length) {
        this.body = body;
        this.length = length;
    }
    
    public EcosystemSuccessDTO(T body) {
        this.body = body;
        if (this.body instanceof List) {
            this.length = ((List<?>) this.body).size();
        }

        if (this.body instanceof Map) {
            this.length = ((Map<?, ?>) this.body).size();
        }
    }
}
