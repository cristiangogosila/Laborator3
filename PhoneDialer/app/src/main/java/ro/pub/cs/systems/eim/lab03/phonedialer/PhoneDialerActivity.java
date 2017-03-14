package ro.pub.cs.systems.eim.lab03.phonedialer;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PhoneDialerActivity extends AppCompatActivity {


    private EditText phoneNumberEditText = null;

    private NumberButtonClickListener numberButtonClickListener = new NumberButtonClickListener();
    private BackspaceButtonClickListener backspaceButtonClickListener = new BackspaceButtonClickListener();
    private CallButtonClickListener callButtonClickListener = new CallButtonClickListener();
    private HangupButtonClickListener hangupButtonClickListener = new HangupButtonClickListener();

    private class NumberButtonClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            phoneNumberEditText.setText(phoneNumberEditText.getText().toString()+ ((Button)v).getText().toString());
        }
    }

    private class BackspaceButtonClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            String phoneNumber = phoneNumberEditText.getText().toString();
            if(phoneNumber.length() > 0)
            {
                phoneNumber.substring(0,phoneNumber.length() - 1 );
                phoneNumberEditText.setText(phoneNumber);
            }
        }
    }

    private class CallButtonClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            String phoneNumber = phoneNumberEditText.getText().toString();
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                startActivity(intent);
            } else {
                ActivityCompat.requestPermissions((Activity) getApplicationContext(), new String[]{Manifest.permission.CALL_PHONE}, 1);
            }

        }
    }

    private class HangupButtonClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            phoneNumberEditText.setText("");
            finish();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_dialer);
    }
}
