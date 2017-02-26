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

    private void load_datasets() throws Exception{
        parksDataset = new ArrayList<ParkDataRow>();

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = getResources().openRawResource(R.raw.parks);

        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        br.readLine();//skip header row;
        String line = "";
        while((line = br.readLine()) != null){
            String[] splits = line.split(",");

            if(splits[2].length()>3) {
                ParkDataRow pdr = new ParkDataRow(splits[0], Integer.parseInt(splits[1]), splits[2], splits[3], splits[4], splits[5], splits[6]);
                if(!parksDataset.contains(pdr)) {
                    parksDataset.add(pdr);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
