package com.photobuddy;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.photobuddy.http.BackupSystemClient;
import com.photobuddy.http.BackupSystemRestClient;
import com.photobuddy.spi.FileResource;

import java.util.Collections;
import java.util.LinkedList;

import static java.lang.String.format;

public class PhotoBuddyMainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView searchButton = findViewById(R.id.serverSearchButton);
        searchButton.setOnClickListener(v -> new Thread(this::tapDroid).start());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void tapDroid() {
        String serverUrl = ((EditText) findViewById(R.id.serverUrl)).getText().toString();
        BackupSystemClient client = new BackupSystemRestClient(serverUrl);
        TextView outputDisplay = findViewById(R.id.serverOutput);
        outputDisplay.setText(client.getFiles().orElse(new LinkedList<>()).toString());
    }


}
