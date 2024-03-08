package bao.huynh.food_app_arnc.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bao.huynh.food_app_arnc.Adapter.FOODADAPTER;
import bao.huynh.food_app_arnc.MODEL.FOOD;
import bao.huynh.food_app_arnc.R;


public class FragmentFavorite extends Fragment {

    public static List<FOOD> favorites = new ArrayList<>();

    private TextView tvFavorite;
    private RecyclerView recyclerView;
    private FOODADAPTER foodadapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite,container,false);
        tvFavorite = view.findViewById(R.id.tvFavoriteTitle);
        recyclerView = view.findViewById(R.id.recyclerViewFavorite);

        Bundle bundle = getArguments();
        if (bundle != null) {
            FOOD favorite = (FOOD) bundle.getSerializable("favorite");
        }
        setupRecyclerView();

        return  view;
    }

    private void setupRecyclerView() {
        foodadapter = new FOODADAPTER(getContext(), (ArrayList<FOOD>) favorites);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(foodadapter);
    }


}