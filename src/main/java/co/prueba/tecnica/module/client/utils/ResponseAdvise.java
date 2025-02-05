package co.prueba.tecnica.module.client.utils;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ResponseAdvise extends ResponseAdviceException implements ResponseBodyAdvice<Object> {

	private Boolean openAPI=false;
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		if(returnType.getMethod().getName().equalsIgnoreCase("openapiJson")) {
			openAPI=true;
			return false;
		}
		return true;
	}

	@Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
            ServerHttpResponse response) {
    	if (Boolean.TRUE.equals(openAPI)) {
			openAPI = false;
			return body.toString();
		}
    	else if (returnType.getContainingClass().isAnnotationPresent(RestController.class)) {            
        	var method = returnType.getMethod(); 
        	if (method != null) {
                if (!method.isAnnotationPresent(IgnoreResponse.class) 
                        &&  ((!(body instanceof EcosystemError)) 
                        		&& (!(body instanceof EcosystemSuccessResponse<?>)))) {
                        return new EcosystemSuccessResponse<>(body);
                }
        	}            
            else {
                return body;
            }
        }
        return body;
    }

}
