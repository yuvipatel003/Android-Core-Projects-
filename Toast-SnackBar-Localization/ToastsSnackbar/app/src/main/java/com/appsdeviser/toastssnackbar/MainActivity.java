package com.appsdeviser.toastssnackbar;

import android.content.res.Configuration;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private static String STR_LANGUAGE_ENGLISH = "en";
    private static String STR_LANGUAGE_FRENCH = "fr";
    private static String STR_LANGUAGE_HINDI = "hi";



    Button mBtn_Toast_Default, mBtn_Toast_Custom;
    Button mBtn_Snackbar_Default, mBtn_Snackbar_Custom;
    Button mBtn_Language_English, mBtn_Language_French, mBtn_Language_Hindi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtn_Toast_Default = findViewById(R.id.btn_toast_default);
        mBtn_Toast_Custom = findViewById(R.id.btn_toast_custom);
        mBtn_Snackbar_Default = findViewById(R.id.btn_snackbar_default);
        mBtn_Snackbar_Custom = findViewById(R.id.btn_snackbar_custom);
        mBtn_Language_English = findViewById(R.id.btn_language_english);
        mBtn_Language_French = findViewById(R.id.btn_language_French);
        mBtn_Language_Hindi = findViewById(R.id.btn_language_hindi);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mBtn_Toast_Default.setOnClickListener(this);
        mBtn_Toast_Custom.setOnClickListener(this);
        mBtn_Snackbar_Default.setOnClickListener(this);
        mBtn_Snackbar_Custom.setOnClickListener(this);
        mBtn_Language_English.setOnClickListener(this);
        mBtn_Language_French.setOnClickListener(this);
        mBtn_Language_Hindi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int viewId = v.getId();

        Snackbar snackbar = Snackbar.make(mBtn_Toast_Custom, getResources().getString(R.string.str_default_snackbar), Snackbar.LENGTH_LONG);

        switch (viewId) {

            case R.id.btn_toast_default:
                    Toast.makeText(this, getResources().getString(R.string.str_default_toast), Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_toast_custom:

                /**
                 *  This is custom toast using custom_toast_layout to display toast message
                 *  TextView text is displaying text of message, where image is displays image on toast
                 *  setGravity will adjust toast location on screen
                 */
                LayoutInflater inflaterToast = getLayoutInflater();
                View layoutToast = inflaterToast.inflate(R.layout.custom_toast_layout,
                        (ViewGroup) findViewById(R.id.custom_toast_container));

                ImageView imageToast = layoutToast.findViewById(R.id.image_custom_toast);
                imageToast.setImageDrawable(getBaseContext().getDrawable(R.drawable.info));

                TextView textToast = layoutToast.findViewById(R.id.text_custom_toast);
                textToast.setText(getResources().getString(R.string.str_custom_toast));

                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layoutToast);
                toast.show();
                break;

            case R.id.btn_snackbar_default:
                snackbar.show();
                break;

            case R.id.btn_snackbar_custom:
                snackbar = Snackbar
                        .make(mBtn_Snackbar_Custom, getResources().getString(R.string.str_custom_snackbar), Snackbar.LENGTH_LONG)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        });
                snackbar.setActionTextColor(Color.RED);
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);
                snackbar.show();


                break;


            case R.id.btn_language_english:
                setCustomLocale(STR_LANGUAGE_ENGLISH);
                break;

            case R.id.btn_language_French:
                setCustomLocale(STR_LANGUAGE_FRENCH);
                break;

            case R.id.btn_language_hindi:
                setCustomLocale(STR_LANGUAGE_HINDI);
                break;

        }
    }

    public void setCustomLocale(String language) {
        Locale locale_french = new Locale(language);
        Locale.setDefault(locale_french);
        Configuration config = new Configuration();
        config.locale = locale_french;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        recreate();
    }
}
