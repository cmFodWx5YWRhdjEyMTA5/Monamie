package am.monamie.shop.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import am.monamie.shop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentSystemFragment extends Fragment {
    private static final String TAG = PaymentSystemFragment.class.getName();

    public PaymentSystemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment_system, container, false);
    }

}
