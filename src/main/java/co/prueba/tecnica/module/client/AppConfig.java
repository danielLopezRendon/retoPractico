package co.prueba.tecnica.module.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@EnableTransactionManagement
@Configuration
public class AppConfig {
	

	
	@Value("${openapi.url}")
	private String url;
	

	@Value("${application.title}")
	private String applicationTitle;
	
	
	@Value("${application.version}")
	private String applicationVersion;	
	
	
	@Primary
	@Bean
	OpenAPI myOpenAPI() {
		Server server = new Server();
		server.setUrl(url);
		server.setDescription("Server URL environment");

		Contact contact = new Contact();
		contact.email("danielfelopezrendon@gmail.com");
		contact.setName("Ing Daniel Lopez");

		Info info = new Info()
				.title(applicationTitle)
				.version("v" + applicationVersion)
				.summary("Microservicio client: Client.")		
				.description("Servicio Backend que gestiona los clientes.")
				.contact(contact);				

		return new OpenAPI().info(info).servers(List.of(server));		
	}

}