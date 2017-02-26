package newwest.mycity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<ParkDataRow> parksDataset;
    public static ArrayList<treeDataRow> treesDataset;

    public static double currentXCoord = 49.2067442;
    public static double currentYCoord = -122.91092950000001;

    public static double computeDistance(double x1, double y1, double x2, double y2){
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(x2-x1);
        double dLng = Math.toRadians(y2-y1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(x1)) * Math.cos(Math.toRadians(x2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return earthRadius * c;
    }

    private void load_datasets() throws Exception{
        loadParksDataset();
        loadTreesDataset();

    }

    private void loadParksDataset() throws Exception{
        parksDataset = new ArrayList<ParkDataRow>();
        InputStream is = getResources().openRawResource(R.raw.parks);
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        br.readLine();//skip header row;
        String line = "";

        while((line = br.readLine()) != null){
            String[] splits = line.split(",");

            if(splits[2].length()>3) {
                ParkDataRow pdr = new ParkDataRow(splits[0], Integer.parseInt(splits[1]), splits[2], splits[3], splits[4],
                        splits[5], splits[6], Double.parseDouble(splits[7]), Double.parseDouble(splits[8]), Double.parseDouble(splits[9]));
                if(!parksDataset.contains(pdr)) {
                    parksDataset.add(pdr);
                }
            }
        }
    }

    private void loadTreesDataset() throws Exception{
        treesDataset = new ArrayList<treeDataRow>();
        InputStream is = getResources().openRawResource(R.raw.trees_west);
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        br.readLine();//skip header row;
        String line = "";

        while((line = br.readLine()) != null){
            String[] splits = line.split(",");

            if(splits[2].length()>3) {
                treeDataRow tdr = new treeDataRow(splits[0], splits[1], splits[2], splits[3], splits[4],
                        splits[5], Double.parseDouble(splits[6]), Double.parseDouble(splits[7]), splits[8], splits[9]);
                if(!parksDataset.contains(tdr)) {
                    treesDataset.add(tdr);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
    }

    public void start() {
        try {
            load_datasets();
        }catch (Exception e){
            Log.d("MEDIA_PLAYER", e.getMessage());
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        Intent i = new Intent(MainActivity.this, SelectionActivity.class);
        startActivity(i);//benchActivity
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
            Intent i = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
