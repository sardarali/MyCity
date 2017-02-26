package newwest.mycity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

/**
 * Created by yzhang on 2017-02-25.
 */

public class RewardsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);
        findViewById(R.id.imageView11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go_feedback();
            }
        });

        ParkDataRow pdr = MainActivity.parksDataset.get(exploreActivity.parkIndex);
        TextView t = (TextView) findViewById(R.id.parkNameView);
        try
        {
            // get input stream
            InputStream ims = getAssets().open(pdr.getParkImageName());
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            ImageView iw = (ImageView) findViewById(R.id.parkView);
            iw.setImageDrawable(d);
            ims .close();
        }
        catch(IOException ex)
        {
            //return;
        }


        t.setText(pdr.getParkName());

        RatingBar rb = (RatingBar) findViewById(R.id.parkratingBar);
        rb.setRating((float) pdr.getRating());

        TextView pd = (TextView) findViewById(R.id.textView8);
        double kmdist = MainActivity.computeDistance(pdr.getxCoord(), pdr.getyCoord(), MainActivity.currentXCoord, MainActivity.currentYCoord) / 1000;
        DecimalFormat df =  new DecimalFormat("##.###");
        pd.setText( df.format(kmdist) + " km");
        TextView pd2 = (TextView) findViewById(R.id.textView10);
        pd2.setText("+" + df.format(kmdist) + " km");


    }
    public void go_feedback() {
        Intent i = new Intent(RewardsActivity.this, FeedbackActivity.class);
        startActivity(i);
        //finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
//            return true;
        }
        if (id == R.id.QuestedTreasure) {
            Intent i = new Intent(RewardsActivity.this, SettingActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}
