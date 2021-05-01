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
    private ImageView searchButton;
    private int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchButton = findViewById(R.id.serverSearchButton);

        //Define and attach click listener
        searchButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                new Thread(() -> tapDroid()).start();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void tapDroid() {
//        counter++;
//        String countAsText;
//        /*
//         * In real applications you should not write switch like the one below.
//         * Use resource of type "Quantity strings (plurals)" instead.
//         * See https://developer.android.com/guide/topics/resources/string-resource#Plurals
//         */
//        switch (counter) {
//            case 1:
//                countAsText = "once";
//                break;
//            case 2:
//                countAsText = "twice";
//                break;
//            default:
//                countAsText = String.format("%d times", counter);
//        }

        String serverUrl = ((EditText) findViewById(R.id.serverUrl)).getText().toString();
        BackupSystemClient client = new BackupSystemRestClient(serverUrl);
        TextView outputDisplay = findViewById(R.id.serverOutput);
        outputDisplay.setText(client.getFiles().orElse(new LinkedList<FileResource>()).toString());




//        message.setText(format("Json response from API: %s", output));
    }


}
