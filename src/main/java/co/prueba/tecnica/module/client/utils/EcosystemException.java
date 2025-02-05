package co.prueba.tecnica.module.client.utils;

import java.util.Arrays;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper=false)

public class EcosystemException extends RuntimeException {
	
	private static final long serialVersionUID = 8689188155686006423L;
	

	private final List<String> errors;
	
	
	private final List<String> solutions;
	

	private final StringBuilder logProcess;


	public EcosystemException(String errorMessage, List<String> errors, List<String> solutions) {
		super(errorMessage);
		this.errors = (errors == null) ? Arrays.asList("") : errors;
		this.solutions = (solutions == null) ? Arrays.asList("") : solutions;
		this.logProcess = new StringBuilder();
	}	
	
	public EcosystemException(String errorMessage, List<String> errors, List<String> solutions, StringBuilder logProcess) {
		super(errorMessage);
		this.errors = (errors == null) ? Arrays.asList("") : errors;
		this.solutions = (solutions == null) ? Arrays.asList("") : solutions;
		this.logProcess = (logProcess == null) ? new StringBuilder() : logProcess;
	}	
	
	public List<String> getErrors() {
		return errors;
	}

	public List<String> getSolutions() {
		return solutions;
	}

	public StringBuilder getLogProcess() {
		return logProcess;
	}
	
}
