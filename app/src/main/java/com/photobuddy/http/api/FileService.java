package com.photobuddy.http.api;

import com.photobuddy.spi.FileResource;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface FileService {

    @GET("/files")
    Call<List<FileResource>> getFiles();
}
