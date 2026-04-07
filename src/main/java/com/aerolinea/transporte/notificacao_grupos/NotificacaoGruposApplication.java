package com.aerolinea.transporte.notificacao_grupos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotificacaoGruposApplication {

	public static void main(String[] args) {
		System.out.println("Application starting with updated CORS config");
		SpringApplication.run(NotificacaoGruposApplication.class, args);
	}

}
