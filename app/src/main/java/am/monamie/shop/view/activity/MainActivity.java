package am.monamie.shop.view.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import am.monamie.shop.R;
import am.monamie.shop.view.constants.MonAmieEnum;
import am.monamie.shop.view.fragment.ContactUsFragment;
import am.monamie.shop.view.fragment.ProductCategoriesFragment;
import am.monamie.shop.view.helper.SharedPreferencesHelper;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private static final String TAG = MainActivity.class.getName();
    // Views
    private FragmentTransaction fragmentTransaction;
    private Toolbar toolbar;
    private ImageView fab;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private View headerView;
    private TextView userFullNameNavHeader;
    private TextView userEmailNavHeader;
    public TextView toolbarTitle;
    // Objects
    private ProductCategoriesFragment productCategoriesFragment = new ProductCategoriesFragment();
    private ContactUsFragment contactUsFragment = new ContactUsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setSupportActionBar(toolbar);
        Window window = getWindow();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        configurationScreenWindow(window, toggle);
        configurationNavigationViews(SharedPreferencesHelper.getKey(this, MonAmieEnum.FULL_NAME.getValue()), SharedPreferencesHelper.getKey(this, MonAmieEnum.EMAIL.getValue()));
        createFragment(R.id.fragment_Container, productCategoriesFragment);
    }

    private void configurationNavigationViews(String fullName, String email) {
        if (fullName != null && email != null) {
            userFullNameNavHeader.setText(fullName);
            userEmailNavHeader.setText(email);
        } else {
            Log.i(TAG, "configurationNavigationViews: could not get user data from SharedPreferences");
        }
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        toolbarTitle = toolbar.findViewById(R.id.toolbarTitleID);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        headerView = navigationView.getHeaderView(0);
        userFullNameNavHeader = headerView.findViewById(R.id.UserFullNameTextViewId);
        userEmailNavHeader = headerView.findViewById(R.id.UserEmailTextViewId);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.product_categories_menu_id:
                createFragment(R.id.fragment_Container, productCategoriesFragment);
                break;
            case R.id.contact_us_menu_id:
                createFragment(R.id.fragment_Container, contactUsFragment);
                break;
            case R.id.log_out_menu_id:
                SharedPreferencesHelper.removeData(this, MonAmieEnum.EMAIL.getValue());
                SharedPreferencesHelper.removeData(this, MonAmieEnum.PHONE.getValue());
                SharedPreferencesHelper.removeData(this, MonAmieEnum.USER_TOKEN.getValue());
                SharedPreferencesHelper.removeData(this, MonAmieEnum.PASSWORD.getValue());
                SharedPreferencesHelper.removeData(this, MonAmieEnum.FIRST_NAME.getValue());
                SharedPreferencesHelper.removeData(this, MonAmieEnum.LAST_NAME.getValue());
                SharedPreferencesHelper.removeData(this, MonAmieEnum.FULL_NAME.getValue());
                startActivity(new Intent(MainActivity.this, SignInActivity.class));
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void createFragment(int resId, Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (fragment.isAdded()) {
            fragmentTransaction.replace(resId, fragment, fragment.getClass().getName());
            fragmentTransaction.commit();
        } else {
            fragmentTransaction.replace(resId, fragment, fragment.getClass().getName()).addToBackStack("");
            fragmentTransaction.commit();
        }
    }

    private void configurationScreenWindow(Window window, ActionBarDrawerToggle actionBarDrawerToggle) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && window != null) {
            window.setStatusBarColor(getResources().getColor(R.color.general_screen_action_bar_background_color));
            window.setNavigationBarColor(getResources().getColor(R.color.transparent));
        }
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.general_screen_action_bar_title_color));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Toast.makeText(this, "Karzinka", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
