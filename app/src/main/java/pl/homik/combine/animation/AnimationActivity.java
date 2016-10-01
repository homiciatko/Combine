package pl.homik.combine.animation;

import pl.homik.combine.R;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AnimationActivity extends AppCompatActivity {

    @BindView(R.id.imageH)
    public ImageView image;
    @BindView(R.id.layout)
    public RelativeLayout layout;

    public static final int RUN_ANIMATION = 1;
    public static final int STOP_ANIMATION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);

        final AnimationDrawable animation = (AnimationDrawable) layout.getBackground();
//        animation.start();

        Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case RUN_ANIMATION: {
                        animation.start();
                        break;
                    }
                    case STOP_ANIMATION: {
                        animation.stop();
                        break;
                    }
                }
                Log.e("wiadomosc ", "to sie nie powinno wyswietlic");
                return false; }
        });

        handler.sendEmptyMessageDelayed(RUN_ANIMATION, 3000);
        handler.sendEmptyMessageDelayed(STOP_ANIMATION, 8000);


    }
}
