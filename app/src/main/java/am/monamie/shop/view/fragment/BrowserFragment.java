package am.monamie.shop.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import am.monamie.shop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BrowserFragment extends Fragment {
    private static final String TAG = BrowserFragment.class.getName();
    // Views
    private View view;
    // Object
    private Context context;

    public BrowserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_browser, container, false);
        context = getContext();
        return view;
    }

}
