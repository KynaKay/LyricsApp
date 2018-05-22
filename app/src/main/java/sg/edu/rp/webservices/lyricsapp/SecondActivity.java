package sg.edu.rp.webservices.lyricsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent i = getIntent();

        String[] info = i.getStringArrayExtra("info");

        TextView tvLyrics = (TextView) findViewById(R.id.tvLyrics);

        tvLyrics.setText(info[0] + info[1]);

        try {
            JSONArray jsonArray = new JSONArray();

            for (int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObj = jsonArray.getJSONObject(i);    // or use get(i)

                String result = jsonObj.getString("result");
                String artist = jsonObj.getString("artist");
                String name = jsonObj.getString("name");
                String track = jsonObj.getString("track");
                String text = jsonObj.getString("text");

                String displayResults = "Result: " + result + "\n\nArtist: "
                        + artist + "\n\nName: " + name + "\n\nTrack: " + track
                        + "\n\nName: " + name
                        + "\n\nText: " + text +   "\n";
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
