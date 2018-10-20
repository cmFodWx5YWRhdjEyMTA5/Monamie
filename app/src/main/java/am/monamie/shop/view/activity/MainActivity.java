package am.monamie.shop.view.activity;

import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import am.monamie.shop.R;
import am.monamie.shop.model.ProductCategories;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        Window window = getWindow();
        configurationScreenWindow(actionBar, window);

    }

    private void configurationScreenWindow(ActionBar actionBar, Window window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && window != null) {
            window.setStatusBarColor(getResources().getColor(R.color.general_screen_action_bar_background_color));
            window.setNavigationBarColor(getResources().getColor(R.color.general_screen_action_bar_background_color));
        }
        if (actionBar !=null){
            actionBar.hide();
        }
    }
}
