package com.br.sysout.reactive_streams_example;

import com.br.sysout.reactive_streams_example.reactstreams.UserSubscriber;
import com.br.sysout.reactive_streams_example.reactstreams.YoutubeChannelPublisher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactiveStreamsExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveStreamsExampleApplication.class, args);

		// ================================
		// 1️⃣ Criação do Publisher
		// ================================
		// O "canal" que publicará novos vídeos - Publisher
		YoutubeChannelPublisher channelPublisher = new YoutubeChannelPublisher();

		// Adicionando alguns vídeos ao canal - Publisher
		channelPublisher.addVideo("Reactive Programming with Java");
		channelPublisher.addVideo("Introduction to Machine Learning");
		channelPublisher.addVideo("Web development basic");


		// ================================
		// 2️⃣ Criação do Subscriber
		// ================================
		// Representa o usuário que vai receber os vídeos
		UserSubscriber userSubscriber = new UserSubscriber("Frank");

		// ================================
		// 3️⃣ Inscrição
		// ================================
		// Conecta o usuário ao canal — inicia o fluxo de dados
		channelPublisher.subscribe(userSubscriber);

	}

}
