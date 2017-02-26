package newwest.mycity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class exploreActivity extends AppCompatActivity {

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
