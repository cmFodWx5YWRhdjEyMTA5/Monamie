package am.monamie.shop.view.activity;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import am.monamie.shop.R;
import am.monamie.shop.model.network.get.UserRegistrationResponse;
import am.monamie.shop.model.network.post.UserRegistration;
import am.monamie.shop.view.fragment.DatePickerFragment;
import am.monamie.shop.view.util.DeviceUtils;
import am.monamie.shop.view.util.MonamieAnimation;
import am.monamie.shop.viewmodel.UserRegistrationViewModel;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = SignUpActivity.class.getName();
    // Views
    private EditText firstName, lastName, email, password, confirmPassword, phoneNumber;
    private TextView birdOfDay;
    private Button register;
    private TextView dialogText;
    private Button dialogButton;
    private Button signIn;
    private ProgressBar progressBar;
    // Object
    private UserRegistration userRegistration;
    private UserRegistrationViewModel viewModel;

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
        progressBar = findViewById(R.id.SignUpProgressBarId);
        signIn = findViewById(R.id.SignUpSignInID);
        signIn.setOnClickListener(this);
        phoneNumber = findViewById(R.id.SignUpPhoneNumberID);
        phoneNumber.setOnClickListener(this);
    }

    private void initUserRegistrationDialogView(View view) {
        dialogText = view.findViewById(R.id.SignUpDialogMessageID);
        dialogButton = view.findViewById(R.id.SignUpDialogButtonID);
        dialogButton.setOnClickListener(this);
    }

    private void windowConfiguration(Window window, ActionBar actionBar) {
        if (actionBar != null)
            actionBar.hide();
        if (window != null) {
            //FIXME: need change window color
        }
    }

    private void showDatePickerDialog() {
        DialogFragment newFragment = new DatePickerFragment(birdOfDay, this);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.SignUpBirdOfDayID:
                DeviceUtils.closeKeyboard(this);
                showDatePickerDialog();
                break;
            case R.id.SignUpSignInID:
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                break;
            case R.id.SignUpRegisterID:
                progressBar.setVisibility(View.VISIBLE);
                userRegistration = new UserRegistration(firstName.getText().toString(), lastName.getText().toString(), email.getText().toString(), phoneNumber.getText().toString(), password.getText().toString(), "2014-09-02T08:05:23.653Z");
                viewModel = ViewModelProviders.of(this).get(UserRegistrationViewModel.class);
                viewModel.registerUser(userRegistration);
                // Created Observe with update UI.
                final Observer<UserRegistrationResponse> nameObserver = response -> {
                    // Update the UI
                    if (response != null && response.getSuccess()) {
                        progressBar.setVisibility(View.GONE);
                        showUserRegistrationDialog(true);
                        Log.i(TAG, "onClick: Response Successfully");
                    } else {
                        progressBar.setVisibility(View.GONE);
                        showUserRegistrationDialog(false);
                        Log.i(TAG, "onClick: Response Null");
                    }
                };
                viewModel.getLiveData().observe(this, nameObserver);
                break;
        }
    }

    private void showUserRegistrationDialog(boolean isRegisterSuccess) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.user_registration_dialog, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        // create dialog animation
        MonamieAnimation.dialogShowAnimation(this, dialogView, R.anim.dialog_animation);
        //init views
        initUserRegistrationDialogView(dialogView);
        String dialogMessage = (isRegisterSuccess) ? getString(R.string.user_registration_dialog_success_message_text) : getString(R.string.user_registration_dialog_field_message_text);
        dialogText.setText(dialogMessage);
        //show dialog
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
        // onClick
        dialogButton.setOnClickListener(v -> {
            firstName.setText("");
            lastName.setText("");
            email.setText("");
            password.setText("");
            confirmPassword.setText("");
            alertDialog.dismiss();
            if (isRegisterSuccess) {
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
            }
        });
    }
}
