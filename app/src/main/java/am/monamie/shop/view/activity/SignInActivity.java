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
import android.widget.TextView;
import android.widget.Toast;

import am.monamie.shop.R;
import am.monamie.shop.model.get.UserLoginResponse;
import am.monamie.shop.model.post.UserLogin;
import am.monamie.shop.view.helper.SharedPreferencesHelper;
import am.monamie.shop.view.util.MonamieAnimation;
import am.monamie.shop.viewmodel.UserLoginViewModel;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = SignInActivity.class.getName();
    // Views
    private EditText email, password;
    private Button logIn;
    private TextView signUp;
    private TextView dialogText;
    private Button dialogButton;
    // Object
    private UserLogin userLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        windowConfiguration(getWindow(), getSupportActionBar());
        initViews();
        Toast.makeText(this, SharedPreferencesHelper.getKey(this, "device_token"), Toast.LENGTH_SHORT).show();
    }

    private void initViews() {
        email = findViewById(R.id.SignInEmailID);
        password = findViewById(R.id.SignInPasswordID);
        logIn = findViewById(R.id.SignInLogInID);
        logIn.setOnClickListener(this);
        signUp = findViewById(R.id.SignInSignUpID);
        signUp.setOnClickListener(this);
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
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.SignInSignUpID:
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
                break;
            case R.id.SignInLogInID:
                userLogin = new UserLogin(email.getText().toString(), password.getText().toString());
                UserLoginViewModel viewModel = ViewModelProviders.of(this).get(UserLoginViewModel.class);
                viewModel.loginUser(userLogin);
                // Created the observer which updates the UI.
                final Observer<UserLoginResponse> nameObserver = response -> {
                    // Update the UI.
                    if (response != null) {
                        Log.i(TAG, "onClick: Response Successfully");
                        Toast.makeText(this, "Response Successfully", Toast.LENGTH_SHORT).show();
                    } else {
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
