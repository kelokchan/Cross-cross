package boochatech.cross_cross;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class JustDoItActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_just_do_it);

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.song);
        mediaPlayer.start();

    }
}
