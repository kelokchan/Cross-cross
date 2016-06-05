package boochatech.cross_cross;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddToDoActivity extends AppCompatActivity {

    @Bind(R.id.editTxt)
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.color_picker);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new HorizontalColorPicker(this));

        ButterKnife.bind(this);
    }

    @OnClick(R.id.fab)
    public void goBack(){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", editText.getText().toString());
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}
