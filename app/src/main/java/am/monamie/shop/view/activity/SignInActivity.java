package am.monamie.shop.view.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import am.monamie.shop.R;

public class SignInActivity extends AppCompatActivity {
    private static final String TAG = SignInActivity.class.getName();
    // Views
    private EditText email, password;
    private Button logIn;
    private TextView signUp;

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
        signUp = findViewById(R.id.SignInSignUpID);
    }

    private void windowConfiguration(Window window, ActionBar actionBar) {
        if (actionBar != null)
            actionBar.hide();
        if (window != null) {
            //FIXME: need change window color
        }
    }
}
