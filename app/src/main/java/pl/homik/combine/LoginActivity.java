package pl.homik.combine;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.homik.combine.chooseMenu.ChooseMenuActivity;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.editTextUser)
    public EditText loginText;
    @BindView(R.id.editTextPassword)
    public EditText passwordText;

    @BindView(R.id.loginButton)
    public Button buttonDupa;

    @BindView(R.id.progressBar)
    public ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        buttonDupa.setEnabled(true);

    }

    @OnClick(R.id.loginButton)
    public void loginClicked(View view)  {
        buttonDupa.setEnabled(false);

        String login;
        String password;

        try {
            login = DataValidation.textEdit2String(loginText);
            password = DataValidation.textEdit2String(passwordText);
        } catch (NullPointerException e) {
            DataValidation.showDialog(e.getMessage(), "wypełnij wszystkie pola", R.layout.dialog, this);
            Toast.makeText(LoginActivity.this, "wyjatek na poczatku " + e.getMessage(),Toast.LENGTH_SHORT).show();
            buttonDupa.setEnabled(true);
            return;
        }

        if (loginCheckOut(login, password)) {
            finish();
            progressBar.setVisibility(View.VISIBLE);
            Intent intent = new Intent(LoginActivity.this, ChooseMenuActivity.class);
            startActivity(intent);
        }
        else {
            DataValidation.showDialog("zle dane", "bledne dane logowania", R.layout.dialog, this);
            buttonDupa.setEnabled(true);
        }
    }


    public boolean loginCheckOut(String login, String password) {
        if (login.equals(getResources().getString(R.string.login)) && password.equals(getResources().getString(R.string.password))) {
            return true;
        }
        return false;
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
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("magicNumber",magicNumber);
            startActivity(intent);
        }

        @Override
        public void onProgressUpdate(Integer ... currentProgress) {
            progressBar.setProgress(currentProgress[0]);
        }
    }
}
