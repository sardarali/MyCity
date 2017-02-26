package newwest.mycity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import java.util.Random;

public class SelectionActivity extends AppCompatActivity {

    public static boolean isEventsChecked = false;
    public static boolean isPublicArtChecked = false;
    public static boolean isParkChecked = false;
    public static boolean isTreeChecked = false;
    public static boolean isHeritageHomeChecked= false;
    public static boolean isWaterFountainChecked = false;

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
        CheckBox eventCheckBox = (CheckBox) findViewById(R.id.eventsCheckBox);
        CheckBox publicArtCheckBox = (CheckBox) findViewById(R.id.publicArtCheckBox);
        CheckBox parkCheckBox = (CheckBox) findViewById(R.id.parkCheckBox);
        CheckBox treeCheckBox = (CheckBox) findViewById(R.id.treeCheckBox);
        CheckBox heritageHomeCheckBox = (CheckBox) findViewById(R.id.heritageHomeCheckBox);
        CheckBox waterFountainCheckBox = (CheckBox) findViewById(R.id.waterFountainCheckBox);


        //Checked status check
        if(eventCheckBox.isChecked()) {
            isEventsChecked = true;
        } else {
            isEventsChecked = false;
        }

        if(publicArtCheckBox.isChecked()) {
            isPublicArtChecked = true;
        } else {
            isPublicArtChecked = false;
        }

        if(parkCheckBox.isChecked()) {
            isParkChecked = true;
        } else {
            isParkChecked = false;
        }

        if(treeCheckBox.isChecked()) {
            isTreeChecked = true;
        } else {
            isTreeChecked = false;
        }

        if(heritageHomeCheckBox.isChecked()) {
            isHeritageHomeChecked = true;
        } else {
            isHeritageHomeChecked = false;
        }

        if(waterFountainCheckBox.isChecked()) {
            isWaterFountainChecked = true;
        } else {
            isWaterFountainChecked = false;
        }

        int viewChoice = 0;
        if(isParkChecked && isTreeChecked) {
            viewChoice = new Random().nextInt(2);
        }else{
            if (isTreeChecked)
                viewChoice = 1;
        }


        Intent intent = null;
        if (viewChoice == 0)
            intent = new Intent(SelectionActivity.this, exploreActivity.class);
        else
            intent = new Intent(SelectionActivity.this, exploreTreeActivity.class);
        startActivity(intent);
        //finish();
    }

}
