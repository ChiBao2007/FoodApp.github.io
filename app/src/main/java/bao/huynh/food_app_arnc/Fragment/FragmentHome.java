package bao.huynh.food_app_arnc.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;

import bao.huynh.food_app_arnc.Adapter.CATEGORIESADAPTER;
import bao.huynh.food_app_arnc.Adapter.FASTFOODADATER;
import bao.huynh.food_app_arnc.Adapter.FOODADAPTER;
import bao.huynh.food_app_arnc.Adapter.SLIDEADAPTER;
import bao.huynh.food_app_arnc.MODEL.CATEGORIES;
import bao.huynh.food_app_arnc.MODEL.FOOD;
import bao.huynh.food_app_arnc.MODEL.SLIDE;
import bao.huynh.food_app_arnc.R;
import bao.huynh.food_app_arnc.Service.SERVER;

public class FragmentHome extends Fragment {
    ViewPager viewPager;
    RecyclerView rvLoaiThucAn, rvFoodphobien, rvFastfood, rvFood;
    ArrayList<CATEGORIES> loaithucan_data = new ArrayList<>();
    CATEGORIESADAPTER categoriesadapter;
    FOODADAPTER foodadapter;
    FOODADAPTER allfood;
    FASTFOODADATER fastfoodadater;
    SLIDEADAPTER slideadapter;
    ArrayList<FOOD> food_data = new ArrayList<>();
    ArrayList<FOOD> fastfood_data = new ArrayList<>();
    ArrayList<FOOD> foods_data = new ArrayList<>();
    ArrayList<SLIDE> slide_data = new ArrayList<>();

    SearchView searchView;

    private Timer timer;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.home_fragment_layout, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhxa(view);
        foodadapter = new FOODADAPTER(getContext(),food_data);

        fastfood_data = new ArrayList<>();

        fastfoodadater = new FASTFOODADATER(getContext(),fastfood_data);


        allfood = new FOODADAPTER(getContext(),foods_data);


        categoriesadapter = new CATEGORIESADAPTER(getContext(),loaithucan_data);


        rvLoaiThucAn.setAdapter(categoriesadapter);
        rvLoaiThucAn.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));

        rvFoodphobien.setAdapter(foodadapter);
        rvFoodphobien.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));

        rvFastfood.setAdapter(fastfoodadater);
        rvFastfood.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));

        rvFood.setAdapter(allfood);
        rvFood.setLayoutManager(new GridLayoutManager(getContext(),2));

        loadallfood();
        loadfood1();
        loadfood();
        loadchude();
        loadslide();
    }

    private void loadallfood() {
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject foodobject = response.getJSONObject(i);
                        FOOD food = new FOOD(foodobject.getString("Mathucan"),
                                foodobject.getString("Tenthucan"),
                                foodobject.getString("Dongia"),
                                foodobject.getString("Mota"),
                                foodobject.getString("Hinhminhhoa"),
                                foodobject.getString("Maloaithucan"),
                                foodobject.getString("Xuatxu"),
                                foodobject.getString("Danhgia"),
                                foodobject.getString("Soluongdanhgia"),
                                foodobject.getString("Thoigian"),
                                foodobject.getString("Soluongban"),
                                foodobject.getString("Hinh")


                        );

                        foods_data.add(food);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "loi pt thanhcong: "+e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                allfood.notifyDataSetChanged();
            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "loi thatbai: "+ error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SERVER.urlfoodall,thanhcong, thatbai);

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }

    private void loadfood() {
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject foodobject = response.getJSONObject(i);
                        FOOD food = new FOOD(foodobject.getString("Mathucan"),
                                foodobject.getString("Tenthucan"),
                                foodobject.getString("Dongia"),
                                foodobject.getString("Mota"),
                                foodobject.getString("Hinhminhhoa"),
                                foodobject.getString("Maloaithucan"),
                                foodobject.getString("Xuatxu"),
                                foodobject.getString("Danhgia"),
                                foodobject.getString("Soluongdanhgia"),
                                foodobject.getString("Thoigian"),
                                foodobject.getString("Soluongban"),
                                foodobject.getString("Hinh")


                                );

                       food_data.add(food);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "loi pt thanhcong: "+e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                    foodadapter.notifyDataSetChanged();
            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "loi thatbai: "+ error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SERVER.urlfood,thanhcong, thatbai);

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }
    private void loadfood1() {
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject foodobject = response.getJSONObject(i);
                        FOOD food = new FOOD(foodobject.getString("Mathucan"),
                                foodobject.getString("Tenthucan"),
                                foodobject.getString("Dongia"),
                                foodobject.getString("Mota"),
                                foodobject.getString("Hinhminhhoa"),
                                foodobject.getString("Maloaithucan"),
                                foodobject.getString("Xuatxu"),
                                foodobject.getString("Danhgia"),
                                foodobject.getString("Soluongdanhgia"),
                                foodobject.getString("Thoigian"),
                                foodobject.getString("Soluongban"),
                                foodobject.getString("Hinh")


                        );

                        fastfood_data.add(food);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "loi pt thanhcong: "+e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                fastfoodadater.notifyDataSetChanged();
            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "loi thatbai: "+ error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SERVER.urlfood1,thanhcong, thatbai);

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }
    private void loadslide() {
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject slideobject = response.getJSONObject(i);
                        SLIDE slide = new SLIDE(slideobject.getString("HinhSlide"));

                        slide_data.add(slide);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "loi pt thanhcong: "+e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                slideadapter = new SLIDEADAPTER(getContext(), slide_data, viewPager);
                viewPager.setAdapter(slideadapter);
                slideadapter.startSlideShow();
            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "loi thatbai: "+ error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SERVER.urlslide,thanhcong, thatbai);

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }


    private void loadchude() {

        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject chudeobject = response.getJSONObject(i);
                        CATEGORIES cd = new CATEGORIES(chudeobject.getString("MaLoaiThucAn"),
                                chudeobject.getString("TenLoaiThucAn"),
                                chudeobject.getString("HinhLoaiThucAn"),
                                chudeobject.getString("Hinh"));
                        loaithucan_data.add(cd);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "loi pt thanhcong: "+e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                categoriesadapter.notifyDataSetChanged();
            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "loi thatbai: "+ error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SERVER.urllaychude,thanhcong, thatbai);

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }





    private void anhxa(View view) {
        viewPager = view.findViewById(R.id.viewpager);
        rvLoaiThucAn = view.findViewById(R.id.rvLoaiThucAn);
        rvFoodphobien = view.findViewById(R.id.rvFoodphobien);
        rvFastfood = view.findViewById(R.id.rvFastfood);
        rvFood = view.findViewById(R.id.rvFood);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loaithucan_data.clear();
        food_data.clear();
    }
}
