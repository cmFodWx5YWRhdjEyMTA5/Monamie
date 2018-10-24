package am.monamie.shop.view.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import am.monamie.shop.R;
import am.monamie.shop.model.get.UserLoginResponse;
import am.monamie.shop.model.post.UserLogin;
import am.monamie.shop.viewmodel.UserLoginViewModel;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = SignInActivity.class.getName();
    // Views
    private EditText email, password;
    private Button logIn;
    private TextView signUp;
    // Object
    private UserLogin userLogin;

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
                    }else {
                        Log.i(TAG, "onClick: Response Null");
                    }

                };
                viewModel.getLiveData().observe(this, nameObserver);
                break;
        }
    }
}
