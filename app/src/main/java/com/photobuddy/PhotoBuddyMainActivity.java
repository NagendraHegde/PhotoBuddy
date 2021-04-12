package com.photobuddy;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import static java.lang.String.format;

public class PhotoBuddyMainActivity extends AppCompatActivity {
    private TextView message;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message = findViewById(R.id.serverUrl);
        ImageView droid = findViewById(R.id.cam_icon);

        //Define and attach click listener
        droid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tapDroid();
            }
        });
    }

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

        String output = "";

        message.setText(format("Json response from API: %s", output));
    }
}
