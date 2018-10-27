package am.monamie.shop.view.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import am.monamie.shop.R;
import am.monamie.shop.model.get.ProductCategoriesResponse;
import am.monamie.shop.view.activity.MainActivity;
import am.monamie.shop.view.adapter.ProductCategoriesAdapter;
import am.monamie.shop.viewmodel.ProductCategoriesViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductCategoriesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = ProductCategoriesFragment.class.getName();
    // Views
    private View view;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private ProductCategoriesAdapter adapter;
    // Objects
    private Context context;
    private ProductCategoriesViewModel viewModel;

    public ProductCategoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        manageToolbar(getString(R.string.general_screen_action_bar_title));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_product_categories, container, false);
        context = getContext();
        initViews();
        getProductCategoriesData();
        return view;
    }

    private void getProductCategoriesData() {
        viewModel = ViewModelProviders.of(this).get(ProductCategoriesViewModel.class);
        final Observer<ProductCategoriesResponse> nameObserve = productCategoriesResponse -> {
            if (productCategoriesResponse != null && productCategoriesResponse.getSuccess()) {
                gridLayoutManager = new GridLayoutManager(context, 1);
                recyclerView.setLayoutManager(gridLayoutManager);
                adapter = new ProductCategoriesAdapter(context, productCategoriesResponse);
                recyclerView.setAdapter(adapter);
            } else {
                Log.i(TAG, "onChanged: product categories null");
                //FIXME: need animate loading recycler view...
            }
        };
        viewModel.getLiveData().observe(this, nameObserve);

    }

    private void initViews() {
        recyclerView = view.findViewById(R.id.recyclerViewProductCategoriesID);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayoutProductCategoriesID);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.general_screen_action_bar_background_color);
    }


    private void manageToolbar(String title) {
        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null)
            mainActivity.toolbarTitle.setText(title);
    }


    @Override
    public void onRefresh() {
        // get product data
        swipeRefreshLayout.setRefreshing(false);
        getProductCategoriesData();
    }
}
