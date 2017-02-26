package newwest.mycity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        ParkDataRow pdr = MainActivity.parksDataset.get(exploreActivity.parkIndex);
        TextView t = (TextView) findViewById(R.id.textView9);
        try
        {
            // get input stream
            InputStream ims = getAssets().open(pdr.getParkImageName());
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            ImageView iw = (ImageView) findViewById(R.id.imageView3);
            iw.setImageDrawable(d);
            ims .close();
        }
        catch(IOException ex)
        {
            //return;
        }


        t.setText(pdr.getParkName());


    }



}
