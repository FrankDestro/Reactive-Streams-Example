package com.br.sysout.reactive_streams_example.reactstreams;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import java.util.ArrayList;
import java.util.List;

// Implementa o padrão Publisher (do Reactive Streams ou de um padrão Observer genérico)
public class YoutubeChannelPublisher implements Publisher<String> {

    // Lista que armazena os vídeos publicados no canal
    private List<String> videos = new ArrayList<>();

    // Método chamado quando um Subscriber se inscreve no canal
    @Override
    public void subscribe(Subscriber<? super String> subscriber) {
        // Cria uma nova "assinatura" (Subscription) entre o canal e o inscrito
        // Essa assinatura controla o fluxo de dados (vídeos) que o inscrito receberá
        subscriber.onSubscribe(new YoutubeSubscription(subscriber, videos) {
        });
    }

    // Adiciona um novo vídeo à lista de vídeos publicados
    // (em um caso real, isso também poderia acionar a notificação aos inscritos)
    public void addVideo(String title) {
        videos.add(title);
    }
}
