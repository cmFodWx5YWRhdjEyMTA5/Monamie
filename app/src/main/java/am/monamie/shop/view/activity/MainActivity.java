package am.monamie.shop.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import am.monamie.shop.R;
import am.monamie.shop.view.fragment.ContactUsFragment;
import am.monamie.shop.view.fragment.ProductCategoriesFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MainActivity.class.getName();
    // Views
    private FragmentTransaction fragmentTransaction;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    public TextView toolbarTitle;

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
        createFragment(R.id.fragment_Container, productCategoriesFragment);
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = toolbar.findViewById(R.id.toolbarTitleID);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.product_categories_menu_id:
                createFragment(R.id.fragment_Container, productCategoriesFragment);
                break;
            case R.id.contact_us_menu_id:
                createFragment(R.id.fragment_Container, contactUsFragment);
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
}
