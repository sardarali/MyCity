package newwest.mycity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yzhang on 2017-02-26.
 */

public class TreeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree);
        findViewById(R.id.goTreeInter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go_treeinter();
            }
        });
        TreeDataRow pdr = MainActivity.treesDataset.get(exploreTreeActivity.treeIndex);
        try
        {
            // get input stream
            InputStream ims = getAssets().open("trees/" + pdr.getImageName());
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            ImageView iw = (ImageView) findViewById(R.id.imageView18);
            iw.setImageDrawable(d);
            ims .close();
        }
        catch(IOException ex)
        {
            //return;
        }
        TextView cname = (TextView) findViewById(R.id.textView34);
        cname.setText("Common Name: " + pdr.getCommonName());

        TextView sname = (TextView) findViewById(R.id.textView35);
        sname.setText("Scientific Name: " + pdr.getSciName());

        TextView species = (TextView) findViewById(R.id.textView33);
        species.setText("Species: " + pdr.getSpecies());

        TextView genus = (TextView) findViewById(R.id.textView32);
        genus.setText("Genus: " + pdr.getGenus());

        TextView cult = (TextView) findViewById(R.id.textView31);
        cult.setText("Cultivar: " + pdr.getCultivar());

        TextView cult2 = (TextView) findViewById(R.id.textView21);
        if(pdr.getCommonName() == null || pdr.getCommonName().equals("")){
            cult2.setText("Oops! No one knows about this tree, why don't you tell us?");
        }else{
            cult2.setText("");
        }


    }
    public void go_treeinter() {
        Intent i = new Intent(TreeActivity.this, TreeInterActivity.class);
        startActivity(i);
        //finish();
    }
}
