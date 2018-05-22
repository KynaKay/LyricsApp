package sg.edu.rp.webservices.lyricsapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // for changes or use purpose only
    Button btnSearch;
    EditText etArtist, etSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // to request from the web service that's available in the internet
        Intent i = new Intent(MainActivity.this, HttpRequest.class);

        // bind
        btnSearch = (Button) findViewById(R.id.btnSearch);
        etArtist = (EditText) findViewById(R.id.etArtist);
        etSong = (EditText) findViewById(R.id.etSong);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                String[] info = {etArtist.getText().toString(), etSong.getText().toString()};
                i.putExtra("info", info);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Code for step 1 start
        HttpRequest request = new HttpRequest
                ("https://orion.apiseeds.com/api/music/lyric/BTS/Fake%20Love?apikey=gapD8IB0UEYFJDsCTZXDwV1tInAjsadFfWohI0JZzpPnpBNmLGTZ5GKYkvasFa1L");   // localhost // for local testing // to test on android emulator
        request.setOnHttpResponseListener(mHttpResponseListener);
        request.setMethod("GET");
        request.execute();
        // Code for step 1 end
    }

    private HttpRequest.OnHttpResponseListener mHttpResponseListener =
            new HttpRequest.OnHttpResponseListener() {
                @Override
                public void onResponse(String response){

                    // process response here
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i=0; i<jsonArray.length(); i++){
                            JSONObject jsonObj = jsonArray.getJSONObject(i);    // or use get(i)

                            String artist = jsonObj.getString("artist");
                            String songTitle = jsonObj.getString("songTitle");

                            String displayResults = "Artist: " + artist + "\n\nSong Title: "
                                    + songTitle + "\n";
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            };
}