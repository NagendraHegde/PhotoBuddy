package com.photobuddy.http;

import com.photobuddy.spi.FileResource;

import java.util.List;
import java.util.Optional;

public interface BackupSystemClient {
    public Optional<List<FileResource>> getFiles();
}
