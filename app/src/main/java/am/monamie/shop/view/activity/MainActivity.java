package am.monamie.shop.view.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
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

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import am.monamie.shop.R;
import am.monamie.shop.view.constants.AppConstants;
import am.monamie.shop.view.constants.MonAmieEnum;
import am.monamie.shop.view.fragment.ProductCategoriesFragment;
import am.monamie.shop.view.google.activity.MapsActivity;
import am.monamie.shop.view.helper.SharedPreferencesHelper;

import static am.monamie.shop.view.constants.AppConstants.ERROR_DIALOG_REQUEST;

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
                drawerItemCliked(AppConstants.PRODUCT_CATEGORIES);
                break;
            case R.id.contact_us_menu_id:
                drawerItemCliked(AppConstants.CONTACT_US);
                break;
            case R.id.log_out_menu_id:
                drawerItemCliked(AppConstants.LOG_OUT);
                break;
            case R.id.google_maps_menu_id:
                drawerItemCliked(AppConstants.GOOGLE_MAPS);
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
        actionBarDrawerToggle.syncState();
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.general_screen_action_bar_title_color));
    }

    private void drawerItemCliked(String itemType) {
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                Log.i(TAG, "onDrawerSlide: ");
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                Log.i(TAG, "onDrawerOpened: ");
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                Log.i(TAG, "onDrawerClosed: ");
                if (itemType.equals(AppConstants.CONTACT_US)) {
                    startActivity(new Intent(MainActivity.this, ContactUsActivity.class));
                } else if (itemType.equals(AppConstants.PRODUCT_CATEGORIES)) {
                    createFragment(R.id.fragment_Container, productCategoriesFragment);
                } else if (itemType.equals(AppConstants.LOG_OUT)) {
                    SharedPreferencesHelper.removeData(MainActivity.this, MonAmieEnum.EMAIL.getValue());
                    SharedPreferencesHelper.removeData(MainActivity.this, MonAmieEnum.PHONE.getValue());
                    SharedPreferencesHelper.removeData(MainActivity.this, MonAmieEnum.USER_TOKEN.getValue());
                    SharedPreferencesHelper.removeData(MainActivity.this, MonAmieEnum.PASSWORD.getValue());
                    SharedPreferencesHelper.removeData(MainActivity.this, MonAmieEnum.FIRST_NAME.getValue());
                    SharedPreferencesHelper.removeData(MainActivity.this, MonAmieEnum.LAST_NAME.getValue());
                    SharedPreferencesHelper.removeData(MainActivity.this, MonAmieEnum.FULL_NAME.getValue());
                    startActivity(new Intent(MainActivity.this, SignInActivity.class));
                } else if (itemType.equals(AppConstants.GOOGLE_MAPS)) {
                    if (isServicesOK()) {
                        startActivity(new Intent(MainActivity.this, MapsActivity.class));
                    }
                }
            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Toast.makeText(this, "Karzinka", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public boolean isServicesOK() {
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if (available == ConnectionResult.SUCCESS) {
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
