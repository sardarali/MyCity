package newwest.mycity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
    }
    public void go_treeinter() {
        Intent i = new Intent(TreeActivity.this, TreeInterActivity.class);
        startActivity(i);
        //finish();
    }
}
