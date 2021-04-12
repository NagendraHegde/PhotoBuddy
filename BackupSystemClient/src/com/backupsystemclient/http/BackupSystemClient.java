package com.backupsystemclient.http;

import com.photobuddy.spi.FileResource;

import java.util.List;

public interface BackupSystemClient {
    public List<FileResource> getFiles();
}
