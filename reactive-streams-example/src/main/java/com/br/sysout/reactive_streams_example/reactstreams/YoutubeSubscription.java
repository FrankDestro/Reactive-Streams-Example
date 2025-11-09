package com.br.sysout.reactive_streams_example.reactstreams;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

// Representa a "assinatura" entre o canal (Publisher) e o assinante (Subscriber)
// Controla a entrega dos vídeos conforme as solicitações do assinante
public class YoutubeSubscription implements Subscription {

    // Referência ao assinante que receberá as notificações (vídeos)
    private Subscriber<? super String> subscriber;

    // Lista de vídeos disponíveis no canal
    private List<String> videos;

    // Construtor: vincula o assinante e a lista de vídeos do canal
    public YoutubeSubscription(Subscriber<? super String> subscriber, List<String> videos) {
        this.subscriber = subscriber;
        this.videos = videos;
    }

    /**
     * Chamado quando o assinante solicita novos itens (vídeos).
     * O parâmetro 'size' indica quantos vídeos o assinante quer receber.
     */
    @Override
    public void request(long size) {
        System.out.println("request(unbounded)");

        // Determina o número de vídeos que realmente podem ser enviados
        long requestNumberItems = getRequestNumberItemsSize(size);

        for (int i = 0; i < requestNumberItems; i++) {
            subscriber.onNext(videos.get(i));
        }

        // Indica que o envio foi concluído
        subscriber.onComplete();

    }

    /**
     * Calcula a quantidade de vídeos que será enviada,
     * garantindo que não ultrapasse o total disponível.
     */
    private long getRequestNumberItemsSize(long size) {
        if (size <= videos.size()) {
            return size; // envia apenas o que foi solicitado
        } else {
            return videos.size(); // ou o total disponível
        }
    }

    /**
     * Cancela a assinatura (não implementado neste exemplo).
     * Em um cenário real, aqui seriam liberados recursos
     * ou interrompido o envio de vídeos.
     */
    @Override
    public void cancel() {
        // Nenhuma ação implementada ainda
    }
}
