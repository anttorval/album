package org.example.album.infrastructure.adapters.output.providers;

import org.springframework.web.reactive.function.client.WebClient;

public abstract class AbstractProvider {

    private final WebClient client;


    protected AbstractProvider(WebClient client) {
        this.client = client;
    }

    protected WebClient getClient(){
        return client;
    }
}
