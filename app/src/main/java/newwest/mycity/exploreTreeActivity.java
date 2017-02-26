package newwest.mycity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Random;

public class exploreTreeActivity extends AppCompatActivity {
    public static int treeIndex;
    private static int TIME_OUT = 8000; //Time to launch the another activity
    private static String selection = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_tree);

        load_selection();


        findViewById(R.id.Gobtn_tree).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TreeDataRow tdr = MainActivity.treesDataset.get(treeIndex);
                String treeName = tdr.getCommonName();
                launchMap(tdr.getxLoc(), tdr.getyLoc());
            }
        });

        findViewById(R.id.SearchMorebtn_tree).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_activity_random(v);
            }
        });

        findViewById(R.id.Closestbtn_tree).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_activity_closest(v);
            }
        });

        findViewById(R.id.Furthestbtn_tree).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_activity_farthest(v);
            }
        });

        load_activity_random(null);
    }

    public void load_activity_random(View v){
        load_activity(v, new Random().nextInt(MainActivity.treesDataset.size()));
    }

    public void load_activity_closest(View v){
        int closest_tree_idx = 0;
        double minDist = MainActivity.computeDistance(MainActivity.treesDataset.get(0).getxLoc(), MainActivity.treesDataset.get(0).getyLoc(), MainActivity.currentXCoord, MainActivity.currentYCoord);
        for(int i=1; i<MainActivity.treesDataset.size(); i++){
            double currDist = MainActivity.computeDistance(MainActivity.treesDataset.get(i).getxLoc(), MainActivity.treesDataset.get(i).getyLoc(), MainActivity.currentXCoord, MainActivity.currentYCoord);
            if (currDist < minDist ) {
                minDist = currDist;
                closest_tree_idx = i;
            }
        }
        load_activity(v, closest_tree_idx);
    }

    public void load_activity_farthest(View v){
        int farthest_tree_idx = 0;
        double maxDist = MainActivity.computeDistance(MainActivity.treesDataset.get(0).getxLoc(), MainActivity.treesDataset.get(0).getyLoc(), MainActivity.currentXCoord, MainActivity.currentYCoord);
        for(int i=1; i<MainActivity.treesDataset.size(); i++){
            double currDist = MainActivity.computeDistance(MainActivity.treesDataset.get(i).getxLoc(), MainActivity.treesDataset.get(i).getyLoc(), MainActivity.currentXCoord, MainActivity.currentYCoord);
            if (currDist > maxDist ) {
                maxDist = currDist;
                farthest_tree_idx = i;
            }
        }
        load_activity(v, farthest_tree_idx);
    }

    public void load_activity(View v, int tree_index){
        TextView t = (TextView) findViewById(R.id.treeName);
        treeIndex = tree_index;
        TreeDataRow tdr = MainActivity.treesDataset.get(treeIndex);
        try
        {
            InputStream ims = getAssets().open("trees/" + tdr.getImageName());
            Drawable d = Drawable.createFromStream(ims, null);
            ImageView iw = (ImageView) findViewById(R.id.parkImage);
            iw.setImageDrawable(d);
            ims .close();
        }
        catch(IOException ex)
        {
            Log.d("MEDIA_PLAYER", ex.getMessage());
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }


        t.setText(tdr.getCommonName());


        TextView td = (TextView) findViewById(R.id.treeDistance);
        double kmdist = MainActivity.computeDistance(tdr.getxLoc(), tdr.getyLoc(), MainActivity.currentXCoord, MainActivity.currentYCoord) / 1000;
        DecimalFormat df =  new DecimalFormat("##.###");
        td.setText( df.format(kmdist) + " km away");
        TextView ng = (TextView) findViewById(R.id.treeNeighborhood);
        ng.setText(tdr.getNeighName());
        TextView sc = (TextView) findViewById(R.id.treeSciName);
        sc.setText(tdr.getSciName());
    }

    public void launchMap(double xCoord, double yCoord) {
        double[] latLon = UTM2Deg.UTM2Deg(xCoord, yCoord);
//        double lat = latLon[0];
//        double lon = latLon[1];

        //Todo: convert utm_x and utm_y to lat and lon
        double lat = 49.196269;
        double lon = -122.92648878860766;
        Log.d("Status", lat + "");
        Log.d("Status", lon + "");
        String uri = "https://www.google.ca/maps/place/" + lat + "+" +lon;

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
                Intent i = new Intent(exploreTreeActivity.this, TreeActivity.class);
                startActivity(i);
                finish();
            }
        }, TIME_OUT);
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

        TextView selectionText = (TextView) findViewById(R.id.selectedText_tree);
        selectionText.setText(selection);
    }

}