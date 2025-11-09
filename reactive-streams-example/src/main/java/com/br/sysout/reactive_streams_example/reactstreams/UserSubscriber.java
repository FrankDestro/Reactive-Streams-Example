package com.br.sysout.reactive_streams_example.reactstreams;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

// Representa um "assinante" (Subscriber) que recebe notificações de novos vídeos do canal.
// Implementa o contrato do Reactive Streams (Subscriber).
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSubscriber implements Subscriber {

    // Nome do assinante (para identificar nas mensagens do console)
    private String name;

    /**
     * Chamado quando o assinante se inscreve em um Publisher.
     * Recebe a Subscription, que controla o fluxo dos dados recebidos.
     */
    @Override
    public void onSubscribe(Subscription subscription) {
        // Solicita todos os itens disponíveis (Long.MAX_VALUE = ilimitado)
        // Em um caso real, seria comum solicitar em lotes para controlar backpressure.
        System.out.println("onSubscribe("+name+")");
        subscription.request(Long.MAX_VALUE);

    }

    /**
     * Chamado toda vez que o Publisher envia um novo item (vídeo).
     */
    @Override
    public void onNext(Object video) {
        System.out.println("onNext("+video+")");

    }

    /**
     * Chamado caso ocorra algum erro durante o envio dos dados.
     */
    @Override
    public void onError(Throwable error) {
        System.out.println("onError"+error);

    }

    /**
     * Chamado quando o Publisher indica que terminou de enviar todos os vídeos.
     */
    @Override
    public void onComplete() {
        System.out.println("onComplete()");
    }
}
