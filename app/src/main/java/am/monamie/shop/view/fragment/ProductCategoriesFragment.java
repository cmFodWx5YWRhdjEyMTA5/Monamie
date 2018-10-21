package am.monamie.shop.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import am.monamie.shop.R;
import am.monamie.shop.view.activity.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductCategoriesFragment extends Fragment {

    private Context context;
    private View view;
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
        view =  inflater.inflate(R.layout.fragment_product_categories, container, false);
        context = getContext();
        FragmentManager fragmentManager = getFragmentManager();

        return view;
    }

    private void manageToolbar(String title) {
        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null)
            mainActivity.toolbarTitle.setText(title);
    }


}
