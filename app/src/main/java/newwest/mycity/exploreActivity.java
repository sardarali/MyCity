package newwest.mycity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Random;

public class exploreActivity extends AppCompatActivity {
    public static int parkIndex;
    private static int TIME_OUT = 8000; //Time to launch the another activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        findViewById(R.id.Gobtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                go_rewards();
                launchMap();
            }
        });
        findViewById(R.id.Searchbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_activity(v);
            }
        });
        load_activity(null);
    }

    public void load_activity(View v){
        TextView t = (TextView) findViewById(R.id.parkName);

        int parksDataSize = MainActivity.parksDataset.size();
        Random rand = new Random();
        parkIndex = rand.nextInt(parksDataSize+1);
        ParkDataRow pdr = MainActivity.parksDataset.get(parkIndex);

        try
        {
            // get input stream
            InputStream ims = getAssets().open(pdr.getParkImageName());
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

        RatingBar rb = (RatingBar) findViewById(R.id.ratingBar);
        rb.setRating((float) pdr.getRating());

        TextView pd = (TextView) findViewById(R.id.parkDistance);
        double kmdist = MainActivity.computeDistance(pdr.getxCoord(), pdr.getyCoord(), MainActivity.currentXCoord, MainActivity.currentYCoord) / 1000;
        DecimalFormat df =  new DecimalFormat("##.###");
        pd.setText( df.format(kmdist) + " km away");

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

//        final View myLayout = findViewById(R.id.startscreen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(exploreActivity.this, RewardsActivity.class);
                startActivity(i);
                finish();
            }
        }, TIME_OUT);



        //NOTIFICATION after few seconds
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(exploreActivity.this);
//                mBuilder.setSmallIcon(R.mipmap.ic_launcher);
//                mBuilder.setContentTitle("My notification");
//                mBuilder.setContentText("Hello World!");
//                Notification notifcation = mBuilder.build();
//
//                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//
//                nm.notify(2, notifcation);
//            }
//        }, 6000);
    }


    public void go_rewards() {
        Intent i = new Intent(exploreActivity.this, RewardsActivity.class);
        startActivity(i);
        //finish();
    }
}
