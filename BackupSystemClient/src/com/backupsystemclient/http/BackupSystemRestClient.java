package com.backupsystemclient.http;

import com.backupsystemclient.exception.ServerHttpException;
import com.photobuddy.spi.ApiPaths;
import com.photobuddy.spi.FileResource;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class BackupSystemRestClient implements BackupSystemClient {

    final HttpClient httpClient;
    final String serverUrl;

    public BackupSystemRestClient(final String serverUrl) {
        this.serverUrl = serverUrl;
        httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }


    @Override
    public List<FileResource> getFiles() {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(serverUrl + ApiPaths.FILES))
                .build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new ServerHttpException("Error occurred while sending request to backup server", e);
        }

        return Collections.singletonList(new FileResource(response.body(), null));
    }
}
