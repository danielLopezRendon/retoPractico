package co.prueba.tecnica.module.client.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

import co.prueba.tecnica.module.client.dto.EcosystemSuccessDTO;
import lombok.Data;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class EcosystemSuccessResponse<T> {
    
   
    private EcosystemSuccessDTO<T> response;

    public EcosystemSuccessResponse() {        
    }


    public EcosystemSuccessDTO<T> getResponse() {
        return response;
    }


    public void setResponse(EcosystemSuccessDTO<T> response) {
        this.response = response;
    }

    
    public EcosystemSuccessResponse(T object) {
        this.response = new EcosystemSuccessDTO<>(object);
    }

   
    public EcosystemSuccessResponse(T object, String message) {
        this.response = new EcosystemSuccessDTO<>(object, message);
    }
    
   
    public EcosystemSuccessResponse(T object, String message, int statusCode) {
    	this.response = new EcosystemSuccessDTO<>(object, message, statusCode);
    }


    public EcosystemSuccessResponse(T object, Integer length, String message) {
        this.response = new EcosystemSuccessDTO<>(object, length, message);
    }

    
    public EcosystemSuccessResponse(T object, Integer length) {
        this.response = new EcosystemSuccessDTO<>(object, length);
    }
}