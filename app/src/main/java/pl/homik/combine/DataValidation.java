package pl.homik.combine;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by Pawel on 2016-09-26.
 */
public class DataValidation {
    public static String textEdit2String(EditText editText) throws NullPointerException {
        String string = editText.getText().toString();
        if (string.equals(""))
            throw new NullPointerException("brak danych");
        return editText.getText().toString();
    }

    public static Integer textEdit2Int(EditText editText) throws IllegalArgumentException, NumberFormatException{

        return Integer.parseInt(textEdit2String(editText));
    }

    public static Integer getMagicNumberFromTE(EditText editText)  {
        int magicNumber = textEdit2Int(editText);

        if(magicNumber < 1 || magicNumber > 100)
            throw new IllegalArgumentException("poprawny przedzial cyfry szczescia 1-100");
        return magicNumber;
    }

    public static void showDialog(String dialogTitle, String dialogText, int layoutResID, Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(layoutResID);
        TextView textInDialog = (TextView) dialog.findViewById(R.id.textViewInDialog);
        Button buttonInDialog = ButterKnife.findById(dialog, R.id.buttonInDialog);

        textInDialog.setText(dialogText);
        buttonInDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.setTitle(dialogTitle);
        dialog.show();

        Toast.makeText(activity,dialogText,Toast.LENGTH_SHORT).show();
    }
}
