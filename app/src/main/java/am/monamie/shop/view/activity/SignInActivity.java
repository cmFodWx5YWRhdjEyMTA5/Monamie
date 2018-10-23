package am.monamie.shop.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import am.monamie.shop.R;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = SignInActivity.class.getName();
    // Views
    private ScrollView scrollView;
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
        scrollView = findViewById(R.id.SignUpScrollViewID);
        email = findViewById(R.id.SignInEmailID);
        password = findViewById(R.id.SignInPasswordID);
        logIn = findViewById(R.id.SignInLogInID);
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
        switch (v.getId()){
            case R.id.SignInSignUpID:
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
                break;
        }
    }
}
