package newwest.mycity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

public class SelectionActivity extends AppCompatActivity {


    CheckBox test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        findViewById(R.id.Nextbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });

    }
    public void start() {

        Intent i = new Intent(SelectionActivity.this, exploreActivity.class);
        startActivity(i);
        //finish();
    }

}
