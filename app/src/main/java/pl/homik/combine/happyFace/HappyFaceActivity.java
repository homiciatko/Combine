package pl.homik.combine.happyFace;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pl.homik.combine.R;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HappyFaceActivity extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy_face);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        int smileTest = intent.getIntExtra("magicNumber",0);
        Toast.makeText(this,String.valueOf(smileTest), Toast.LENGTH_SHORT).show();

        if(smileTest >=50 && smileTest < 100)
            Picasso.with(this).load(getResources().getString(R.string.happyFaceURl)).into(imageView);
        else if(smileTest >=0 && smileTest <50)
            Picasso.with(this).load(getResources().getString(R.string.sadFaceURL)).into(imageView);

    }
}
