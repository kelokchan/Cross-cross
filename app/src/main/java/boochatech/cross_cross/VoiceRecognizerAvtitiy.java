package boochatech.cross_cross;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class VoiceRecognizerAvtitiy extends Activity implements TextToSpeech.OnInitListener {


    TextToSpeech tts;

    Button startRecognizer;
    Spinner spinnerResult;

    private static final int RQS_RECOGNITION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_recognizer_avtitiy);
        startRecognizer = (Button) findViewById(R.id.startrecognizer);
        startRecognizer.setEnabled(false);
        spinnerResult = (Spinner) findViewById(R.id.result);
        startRecognizer.setOnClickListener(startRecognizerOnClickListener);
        tts = new TextToSpeech(this, this);

    }

    private Button.OnClickListener startRecognizerOnClickListener = new Button.OnClickListener() {

        @Override
        public void onClick(View arg0) {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speech to Recognize");
            startActivityForResult(intent, RQS_RECOGNITION);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == RQS_RECOGNITION) & (resultCode == RESULT_OK)) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, result);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerResult.setAdapter(adapter);
            spinnerResult.setOnItemSelectedListener(spinnerResultOnItemSelectedListener);
        }
    }

    private Spinner.OnItemSelectedListener spinnerResultOnItemSelectedListener = new Spinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String selectedResult = parent.getItemAtPosition(position).toString();
            Toast.makeText(VoiceRecognizerAvtitiy.this, selectedResult, Toast.LENGTH_SHORT).show();
            tts.speak(selectedResult, TextToSpeech.QUEUE_ADD, null);
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    };

    @Override
    public void onInit(int arg0) {
        startRecognizer.setEnabled(true);
    }
}
