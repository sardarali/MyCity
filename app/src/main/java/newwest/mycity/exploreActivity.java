package newwest.mycity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class exploreActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        findViewById(R.id.Gobtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go_rewards();
            }
        });
    }
    public void go_rewards() {
        Intent i = new Intent(exploreActivity.this, RewardsActivity.class);
        startActivity(i);
        //finish();
    }
}
