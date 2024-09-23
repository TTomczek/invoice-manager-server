package net.tomczek.invoice.manager.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"net.tomczek.invoice.manager.server", "net.tomczek.invoice.manager.api.server.api"})
public class InvoiceManagerServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoiceManagerServerApplication.class, args);
	}

}
