package sg.edu.rp.c346.id20046523.sa_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView img1, img2;
    TextView tvIntro, tvDesc;
    Button btnLink, btnTry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img1 = findViewById(R.id.problem);
        img2 = findViewById(R.id.solution);
        tvIntro = findViewById(R.id.intro);
        tvDesc = findViewById(R.id.short_description);
        btnLink = findViewById(R.id.more_info);
        btnTry = findViewById(R.id.experience);

        btnLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent web = new Intent(Intent.ACTION_VIEW, Uri.parse(" "));
                startActivity(web);
            }
        });

        btnTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listAct = new Intent(MainActivity.this, ItemListActivity.class);
                startActivity(listAct);
            }
        });

    }
}