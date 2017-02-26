package newwest.mycity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class exploreActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        findViewById(R.id.Gobtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go_rewards();
//                launchMap();
            }
        });
        findViewById(R.id.Searchbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_activity(v);
            }
        });
    }

    public void load_activity(View v){
        TextView t = (TextView) findViewById(R.id.parkName);

        int parksDataSize = MainActivity.parksDataset.size();
        Random rand = new Random();
        int  randomPark = rand.nextInt(parksDataSize+1);
        ParkDataRow pdr = MainActivity.parksDataset.get(randomPark);


        try
        {
            // get input stream
            InputStream ims = getAssets().open(pdr.parkImageName);
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            ImageView iw = (ImageView) findViewById(R.id.parkImage);
            iw.setImageDrawable(d);
            ims .close();
        }
        catch(IOException ex)
        {
            //return;
        }


        t.setText(pdr.getParkName());
    }

    public void launchMap() {
        String uri = "https://www.google.ca/maps/place/Queens+Park,+New+Westminster,+BC/";

        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse(uri));


        try {
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            try {
                Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(unrestrictedIntent);
            } catch (ActivityNotFoundException innerEx) {
                Toast.makeText(this, "Please install a maps application", Toast.LENGTH_LONG).show();
            }
        }
    }


    public void go_rewards() {
        Intent i = new Intent(exploreActivity.this, RewardsActivity.class);
        startActivity(i);
        //finish();
    }
}
