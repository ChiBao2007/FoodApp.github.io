package bao.huynh.food_app_arnc.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bao.huynh.food_app_arnc.R;

public class Fragment_Notification extends Fragment {

    public Fragment_Notification() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment__notification, container, false);
    }
}
