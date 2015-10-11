package hackathon.cultcoin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class InfoPlaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_place);
        ImageView iv = (ImageView)findViewById(R.id.imageView2);
        iv.setImageResource(R.drawable.mar);
    }
}
