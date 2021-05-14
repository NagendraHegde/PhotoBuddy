package com.photobuddy.http.api;

import com.photobuddy.spi.FileResource;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface FileService {

    @GET("/files")
    Call<List<FileResource>> getFiles();

    @Multipart
    @POST("/files")
    Call<FileResource> uploadFile(@Part MultipartBody.Part filePart);

}
