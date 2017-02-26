package newwest.mycity;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Random;

import static newwest.mycity.R.id.publicArtCheckBox;
import static newwest.mycity.R.id.treeCheckBox;
import static newwest.mycity.R.id.waterFountainCheckBox;

public class exploreActivity extends AppCompatActivity {
    public static int parkIndex;
    private static int TIME_OUT = 8000; //Time to launch the another activity
    private static String selection = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        load_selection();

        findViewById(R.id.Gobtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParkDataRow pdr = MainActivity.parksDataset.get(parkIndex);
                String parkName = pdr.getParkName();
                launchMap(parkName);
            }
        });
        findViewById(R.id.SearchMorebtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_activity(v);
            }
        });
        load_activity(null);

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            selection = "";
        }
        return super.onKeyDown(keyCode, event);
    }

    public void load_selection() {
        if(SelectionActivity.isEventsChecked) {
            if(selection == "")  {
                selection += "Events";
            } else {
                selection += "| Events";
            }
        }

        if(SelectionActivity.isPublicArtChecked) {
            if(selection == "")  {
                selection += "Public Arts";
            } else {
                selection += " | Public Arts";
            }
        }

        if(SelectionActivity.isParkChecked) {
            if(selection == "")  {
                selection += "Parks";
            } else {
                selection += " | Parks";
            }
        }

        if(SelectionActivity.isTreeChecked) {
            if(selection == "")  {
                selection += "Trees";
            } else {
                selection += " | Trees";
            }
        }

        if(SelectionActivity.isHeritageHomeChecked) {
            if(selection == "")  {
                selection += "Heritage Homes";
            } else {
                selection += " | Heritage Homes";
            }
        }

        if(SelectionActivity.isWaterFountainChecked) {
            if(selection == "")  {
                selection += "Water Fountains";
            } else {
                selection += " | Water Fountains";
            }
        }

        TextView selectionText = (TextView) findViewById(R.id.selectedText);
        selectionText.setText(selection);
    }

    public void load_activity(View v){
        TextView t = (TextView) findViewById(R.id.parkName);

        int parksDataSize = MainActivity.parksDataset.size();
        Random rand = new Random();
        parkIndex = rand.nextInt(parksDataSize);
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

    public void launchMap(String parkName) {
        String convertedParkName = parkName.replace(' ','+');

//        String uri = "https://www.google.ca/maps/place/Queens+Park,+New+Westminster,+BC/";
        String uri = "https://www.google.ca/maps/place/" + convertedParkName + ",+New+Westminster,+BC/";

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
