package am.monamie.shop.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import am.monamie.shop.R;
import am.monamie.shop.view.activity.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactUsFragment extends Fragment {

    private Context context;
    private View view;

    public ContactUsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        manageToolbar(getString(R.string.contact_us));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        context = getContext();
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager !=null)
        System.out.println("Count BackStack===>  "+fragmentManager.getBackStackEntryCount());
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void manageToolbar(String title) {
        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null)
            mainActivity.toolbarTitle.setText(title);
    }
}
