package pl.homik.combine.chooseMenu;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.homik.combine.DataValidation;
import pl.homik.combine.LoginActivity;
import pl.homik.combine.MainActivity;
import pl.homik.combine.R;
import pl.homik.combine.animation.AnimationActivity;
import pl.homik.combine.calculator.CalculatorActivity;
import pl.homik.combine.happyFace.HappyFaceActivity;

public class ChooseMenuActivity extends AppCompatActivity {

    @BindView(R.id.editTextMagicNumber)
    public EditText magicNumberText;

    @BindView(R.id.progressBar)
    public ProgressBar progressBar;

    @BindView(R.id.buttonSubmit)
    public Button buttonSubmit;

    @BindView(R.id.buttonCalculator)
    public Button Calculator;
    @BindView(R.id.buttonAnimation)
    public Button Animation;
    @BindView(R.id.buttonHappyFace)
    public Button HappyFace;

    int magicNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_menu);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        enableButtons(Calculator, HappyFace, Animation);
//        disableButtons(buttonSubmit);
        buttonSubmit.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        magicNumberText.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.buttonCalculator)
    public void clickedCalculator(View view) {
        disableButtons(Calculator, Animation, HappyFace);
        Intent intent = new Intent(ChooseMenuActivity.this, CalculatorActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.buttonAnimation)
    public void clickedAnimation(View view) {
        disableButtons(Calculator, Animation, HappyFace);
        Intent intent = new Intent(ChooseMenuActivity.this, AnimationActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.buttonHappyFace)
    public void clickedHappyFace(View view) {
        disableButtons(Calculator, Animation, HappyFace);
        magicNumberText.setVisibility(View.VISIBLE);
        buttonSubmit.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.buttonSubmit)
    public void clickedSubmit(View view){



        try {
            magicNumber = DataValidation.getMagicNumberFromTE(magicNumberText);
        } catch (IllegalArgumentException e) {
            DataValidation.showDialog("blad danych",e.getMessage(), R.layout.dialog, this);
            return;
        } catch (NullPointerException e) {
            DataValidation.showDialog("blad danych", e.getMessage(),R.layout.dialog, this);
            return;
        }

        disableButtons(Calculator, Animation, HappyFace);
        progressBar.setVisibility(View.VISIBLE);

        new ProcesingMagicNumber().execute(magicNumber);

//
//        Intent intent = new Intent(ChooseMenuActivity.this, HappyFaceActivity.class);
//        intent.putExtra("magicNumber",magicNumber);
//        startActivity(intent);
    }

    private class ProcesingMagicNumber extends AsyncTask<Integer, Integer, Integer> {
        @Override
        protected Integer doInBackground(Integer... magicNumber) {
            int progress = 0;
            while (progress <= getResources().getInteger(R.integer.progresBarMax)) {
                publishProgress(progress);
                try {
                    Thread.sleep(10 + (2*magicNumber[0]));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progress += getResources().getInteger(R.integer.progressBarStep);
            }

            return magicNumber[0];
        }

        @Override
        public void onPostExecute(Integer magicNumber) {
            Intent intent = new Intent(ChooseMenuActivity.this, HappyFaceActivity.class);
            intent.putExtra("magicNumber",magicNumber);
            startActivity(intent);


        }

        @Override
        public void onProgressUpdate(Integer ... currentProgress) {
            progressBar.setProgress(currentProgress[0]);
            //       Toast.makeText(LoginActivity.this, "INT " + currentProgress[0].toString(), Toast.LENGTH_SHORT).show();

        }
    }

    private void disableButtons(Button ... buttons) {
        for (Button button : buttons)
            button.setEnabled(false);
    }

    private void enableButtons(Button ... buttons) {
        for (Button button : buttons)
            button.setEnabled(true);
    }


}
