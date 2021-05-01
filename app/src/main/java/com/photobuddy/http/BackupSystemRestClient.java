package com.photobuddy.http;


import android.os.Build;
import androidx.annotation.RequiresApi;
import com.photobuddy.http.api.FileService;
import com.photobuddy.spi.FileResource;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.time.Duration.ofSeconds;
import static java.util.Objects.isNull;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static retrofit2.converter.gson.GsonConverterFactory.create;

public class BackupSystemRestClient implements BackupSystemClient {

    private final String serverUrl;
    private final Retrofit retrofit;
    private final FileService fileService;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public BackupSystemRestClient(final String serverUrl) {
        this.serverUrl = serverUrl;
//        httpClient = HttpClient.newBuilder()
//                .version(HttpClient.Version.HTTP_1_1)
//                .connectTimeout(ofSeconds(10L))
//                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(serverUrl)
                .addConverterFactory(create())
                .build();
        fileService = retrofit.create(FileService.class);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Optional<List<FileResource>> getFiles() {
        try {
            final Response<List<FileResource>> response = fileService.getFiles().execute();
            if (200 != response.code()) {
                return empty();
            }
            return ofNullable(response.body());
        } catch (IOException e) {
            System.out.println("EXCEPTION RAISED");
            e.printStackTrace();
            return empty();
        }
    }

//    @Override
//    public List<FileResource> getFiles() {
//        HttpRequest request = HttpRequest.newBuilder()
//                .GET()
//                .uri(URI.create(serverUrl + ApiPaths.FILES))
//                .build();
//
//        HttpResponse<String> response = null;
//        try {
//            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//        } catch (IOException | InterruptedException e) {
//            throw new ServerHttpException("Error occurred while sending request to backup server", e);
//        }
//
//        return Collections.singletonList(new FileResource(response.body(), null));
//    }


}
