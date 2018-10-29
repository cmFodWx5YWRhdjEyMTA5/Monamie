package am.monamie.shop.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import am.monamie.shop.R;
import am.monamie.shop.model.ContactUsModel;
import am.monamie.shop.view.adapter.ContactUsAdapter;
import am.monamie.shop.view.constants.AppConstants;
import am.monamie.shop.view.util.MonamieAnimation;

public class ContactUsActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = ContactUsActivity.class.getName();
    // Views
    private ImageView call, back;
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView recyclerView;
    // Object
    private ContactUsAdapter adapter;
    private GridLayoutManager gridLayoutManager;
    private List<ContactUsModel> listContactUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        initViews();
        configurationCollapsingToolbarLayout(appBarLayout, toolbar, getString(R.string.contact_us_title), call);
        setSupportActionBar(toolbar);
        gridLayoutManager = new GridLayoutManager(this, 2);
        initContactUsAdapter(gridLayoutManager, recyclerView);

    }

    private void initViews() {
        coordinatorLayout = findViewById(R.id.cccc);
        appBarLayout = coordinatorLayout.findViewById(R.id.contact_us_app_barID);
        call = (ImageView) findViewById(R.id.contact_us_fabID);
        call.setOnClickListener(this);
        toolbar = (Toolbar) findViewById(R.id.contact_us_toolbarID);
        back = toolbar.findViewById(R.id.contact_us_backID);
        back.setOnClickListener(this);
        recyclerView = findViewById(R.id.contact_us_recyclerViewID);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.contact_us_fabID:
                callToMonamie(AppConstants.PHONE_MONAMIE);
                break;
            case R.id.contact_us_backID:
                startActivity(new Intent(ContactUsActivity.this, MainActivity.class));
                break;
        }
    }

    private void callToMonamie(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:".concat(phoneNumber)));
        startActivity(intent);
    }

    private void configurationCollapsingToolbarLayout(AppBarLayout appBarLayout, Toolbar toolbar, String title, ImageView call) {
        toolbar.setTitle(title);
        appBarLayout.addOnOffsetChangedListener((appBarLayout1, verticalOffset) -> {
            if (Math.abs(verticalOffset) == appBarLayout1.getTotalScrollRange()) {
                // If collapsed, then do this
                call.setVisibility(View.GONE);
            } else if (verticalOffset == 0) {
                // If expanded, then do this
                call.setVisibility(View.VISIBLE);
                // image view visible with animation
                MonamieAnimation.imageViewVisibilityAnimation(this, call, R.anim.button_visibility_animation);
            }
        });
    }

    private void initContactUsAdapter(GridLayoutManager gridLayoutManager, RecyclerView recyclerView) {
        recyclerView.setLayoutManager(gridLayoutManager);
        listContactUs = new ArrayList<>();
        listContactUs.add(new ContactUsModel(R.drawable.about_us, getString(R.string.contact_us_about_us)));
        listContactUs.add(new ContactUsModel(R.drawable.email, getString(R.string.contact_us_email)));
        listContactUs.add(new ContactUsModel(R.drawable.map, getString(R.string.contact_us_map)));
        listContactUs.add(new ContactUsModel(R.drawable.website, getString(R.string.contact_us_website)));
        adapter = new ContactUsAdapter(this, listContactUs);
        recyclerView.setAdapter(adapter);
    }
}
