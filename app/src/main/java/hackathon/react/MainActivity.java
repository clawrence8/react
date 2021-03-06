package hackathon.react;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    MediaRecorder mediaRecorder;
    boolean isRecording;
    MediaPlayer mediaPlayer;
    Button recordButton;
    Button stopButton;
    Button playButton;
    final static String FILENAME = "/data/data/hackathon.react/files/sample1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        recordButton = (Button) findViewById(R.id.record_button);
        stopButton = (Button) findViewById(R.id.stop_button);
        playButton = (Button) findViewById(R.id.play_button);

        mediaPlayer = new MediaPlayer();

        mediaRecorder = new MediaRecorder();


        try {
            mediaPlayer.setDataSource(FILENAME);
            mediaPlayer.prepare();
        } catch (IOException ioe) {
            Log.e("Error", "IOException occurred with the Media Player", ioe);
        }



        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
                mediaRecorder.setOutputFile(FILENAME);
                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);

                try {
                    mediaRecorder.prepare();
                } catch (IOException ioe) {
                    Log.e("Error", "IOException occurred with Media Recordr", ioe);
                }
                mediaRecorder.start();
                isRecording = true;
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isRecording) {
                    mediaRecorder.stop();
                    mediaRecorder.release();
                    isRecording = false;
                }

                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mediaPlayer.start();
            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
