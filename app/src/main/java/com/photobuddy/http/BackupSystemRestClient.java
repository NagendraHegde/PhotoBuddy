package com.photobuddy.http;


import android.os.Build;
import androidx.annotation.RequiresApi;
import com.photobuddy.http.api.FileService;
import com.photobuddy.spi.FileResource;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.MultipartBody.Part;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static okhttp3.MediaType.parse;
import static okhttp3.MultipartBody.Part.createFormData;
import static okhttp3.RequestBody.*;
import static retrofit2.converter.gson.GsonConverterFactory.create;

public class BackupSystemRestClient implements BackupSystemClient {

    private final Retrofit retrofit;
    private final FileService fileService;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public BackupSystemRestClient(final String serverUrl) {
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


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Optional<FileResource> postFile(File file) {
        try {
            Part filePart = createFormData("file", file.getName(), RequestBody.create(parse("image/*"), file));
            final Response<FileResource> response = fileService.uploadFile(filePart).execute();
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

}
