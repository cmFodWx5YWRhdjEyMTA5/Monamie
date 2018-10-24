package am.monamie.shop.view.activity;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import am.monamie.shop.R;
import am.monamie.shop.view.fragment.DatePickerFragment;
import am.monamie.shop.view.util.DeviceUtils;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = SignUpActivity.class.getName();
    // Views
    private EditText firstName, lastName, email, password, confirmPassword;
    private TextView birdOfDay;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        windowConfiguration(getWindow(), getSupportActionBar());
        initViews();
    }

    private void initViews() {
        firstName = findViewById(R.id.SignUpFirstNameID);
        lastName = findViewById(R.id.SignUpLastNameID);
        email = findViewById(R.id.SignUpEmailID);
        password = findViewById(R.id.SignUpPasswordID);
        confirmPassword = findViewById(R.id.SignUpConfirmPasswordID);
        birdOfDay = findViewById(R.id.SignUpBirdOfDayID);
        birdOfDay.setOnClickListener(this);
        register = findViewById(R.id.SignUpRegisterID);
        register.setOnClickListener(this);

    }

    private void windowConfiguration(Window window, ActionBar actionBar) {
        if (actionBar != null)
            actionBar.hide();
        if (window != null) {
            //FIXME: need change window color
        }
    }

    private void showDatePickerDialog() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.SignUpBirdOfDayID:
                DeviceUtils.closeKeyboard(this);
                showDatePickerDialog();
                break;
            case R.id.SignUpRegisterID:
                Toast.makeText(this, "Register", Toast.LENGTH_SHORT).show();
                break;
        }
    }


}
