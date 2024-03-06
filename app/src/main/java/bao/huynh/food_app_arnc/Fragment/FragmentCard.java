package bao.huynh.food_app_arnc.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bao.huynh.food_app_arnc.Adapter.CARTADAPTER;
import bao.huynh.food_app_arnc.MODEL.FOOD;
import bao.huynh.food_app_arnc.R;

public class FragmentCard extends Fragment {
    private TextView tvCartHeader;
    private RecyclerView recyclerView;

    private List<FOOD> cartItemList;
    private CARTADAPTER cartAdapter;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card, container, false);
        tvCartHeader = view.findViewById(R.id.tvCartHeader);
        recyclerView = view.findViewById(R.id.recyclerView);


        cartItemList = new ArrayList<>();

        // Lấy dữ liệu từ Bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            int quantity = bundle.getInt("quantity", 0);
            FOOD food = (FOOD) bundle.getSerializable("food");

//            updateCartItemQuantity(food, quantity);

            // Thêm sản phẩm vào danh sách giỏ hàng
            cartItemList.add(food);

            setupRecyclerView();
        }
        


        return view;
    }

//    private void updateCartItemQuantity(FOOD food, int quantity) {
//        for (FOOD item : cartItemList) {
//            if (item.getMathucan().equals(food.getMathucan())) {
//                item.setSoluongban(String.valueOf(quantity));
//                break;
//            }
//        }
//    }

    private void setupRecyclerView() {
        cartAdapter = new CARTADAPTER(cartItemList, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(cartAdapter);
    }


}
