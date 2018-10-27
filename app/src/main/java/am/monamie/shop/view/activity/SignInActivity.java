package am.monamie.shop.view.activity;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.Toast;

import am.monamie.shop.R;
import am.monamie.shop.model.get.UserLoginResponse;
import am.monamie.shop.model.post.UserLogin;
import am.monamie.shop.view.constants.MonAmieEnum;
import am.monamie.shop.view.helper.SharedPreferencesHelper;
import am.monamie.shop.view.util.MonamieAnimation;
import am.monamie.shop.viewmodel.UserLoginViewModel;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = SignInActivity.class.getName();
    // Views
    private EditText email, password;
    private Button logIn;
    private Button signUp;
    private TextView dialogText;
    private Button dialogButton;
    private ProgressBar progressBar;
    // Object
    private UserLogin userLogin;
    private UserLoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        windowConfiguration(getWindow(), getSupportActionBar());
        initViews();
    }

    private void initViews() {
        email = findViewById(R.id.SignInEmailID);
        password = findViewById(R.id.SignInPasswordID);
        logIn = findViewById(R.id.SignInLogInID);
        logIn.setOnClickListener(this);
        signUp = findViewById(R.id.SignInSignUpID);
        signUp.setOnClickListener(this);
        progressBar = findViewById(R.id.SignInProgressBarId);
    }

    private void initUserLoginDialogView(View view) {
        dialogText = view.findViewById(R.id.SignInLoginDialogMessageID);
        dialogButton = view.findViewById(R.id.SignInLoginDialogButtonID);
        dialogButton.setOnClickListener(this);
    }

    private void windowConfiguration(Window window, ActionBar actionBar) {
        if (actionBar != null)
            actionBar.hide();
        if (window != null) {
            //FIXME: need change window color
            Log.i(TAG, "windowConfiguration: ");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.SignInSignUpID:
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
                break;
            case R.id.SignInLogInID:
                progressBar.setVisibility(View.VISIBLE);
                userLogin = new UserLogin(email.getText().toString(), password.getText().toString());
                viewModel = ViewModelProviders.of(this).get(UserLoginViewModel.class);
                viewModel.loginUser(userLogin);
                // Created the observer which updates the UI.
                final Observer<UserLoginResponse> nameObserver = response -> {
                    // Update the UI.
                    if (response != null) {
                        if (!response.getSuccess()) {
                            Log.i(TAG, "onClick: Response Failed");
                            progressBar.setVisibility(View.GONE);
                            showUserLoginDialog();
                        } else {
                            SharedPreferencesHelper.putKey(this, MonAmieEnum.FIRST_NAME.getValue(), response.getData().getUser().getFirstName());
                            SharedPreferencesHelper.putKey(this, MonAmieEnum.LAST_NAME.getValue(), response.getData().getUser().getLastName());
                            SharedPreferencesHelper.putKey(this, MonAmieEnum.EMAIL.getValue(), response.getData().getUser().getEmail());
                            SharedPreferencesHelper.putKey(this, MonAmieEnum.PHONE.getValue(), response.getData().getUser().getPhone());
                            SharedPreferencesHelper.putKey(this, MonAmieEnum.PASSWORD.getValue(), password.getText().toString());
                            SharedPreferencesHelper.putKey(this, MonAmieEnum.FULL_NAME.getValue(), response.getData().getUser().getFullName());
                            SharedPreferencesHelper.putKey(this, MonAmieEnum.USER_TOKEN.getValue(), response.getData().getToken());
                            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        progressBar.setVisibility(View.GONE);
                        showUserLoginDialog();
                        Log.i(TAG, "onClick: Response Null");
                    }
                };
                viewModel.getLiveData().observe(this, nameObserver);
                break;
        }
    }

    private void showUserLoginDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.user_login_dialog, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        // create dialog animation
        MonamieAnimation.dialogShowAnimation(this, dialogView, R.anim.dialog_animation);
        //init views
        initUserLoginDialogView(dialogView);
        //show dialog
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
        // onClick
        dialogButton.setOnClickListener(v -> {
            email.setText("");
            password.setText("");
            alertDialog.dismiss();
        });
    }
}
