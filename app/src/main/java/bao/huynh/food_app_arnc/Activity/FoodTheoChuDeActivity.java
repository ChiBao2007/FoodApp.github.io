package bao.huynh.food_app_arnc.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.MaterialToolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bao.huynh.food_app_arnc.Adapter.FOODADAPTER;
import bao.huynh.food_app_arnc.MODEL.CATEGORIES;
import bao.huynh.food_app_arnc.MODEL.FOOD;
import bao.huynh.food_app_arnc.R;
import bao.huynh.food_app_arnc.Service.SERVER;

public class FoodTheoChuDeActivity extends AppCompatActivity {

    MaterialToolbar toolbarFoodtheochude;
    SearchView searchView;
    RecyclerView rvFoodtheochude;
    ArrayList<FOOD> food_data = new ArrayList<>();

    FOODADAPTER foodadapter;

    CATEGORIES categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_theo_chu_de);
     

        toolbarFoodtheochude = findViewById(R.id.toolbarFoodtheochude);
        searchView = findViewById(R.id.searchmaintheochude);
        rvFoodtheochude = findViewById(R.id.rvFoodtheochude);

        Intent intent = getIntent();
        categories = (CATEGORIES) intent.getSerializableExtra("ChudeObject");

        foodadapter = new FOODADAPTER(this,food_data);
        rvFoodtheochude.setAdapter(foodadapter);
        rvFoodtheochude.setLayoutManager(new GridLayoutManager(this, 2));

        setSupportActionBar(toolbarFoodtheochude);
        getSupportActionBar().setTitle("");


        toolbarFoodtheochude.setNavigationIcon(R.drawable.baseline_arrow_back_24);
        toolbarFoodtheochude.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        loadfood();
    }


    private void loadfood() {
        food_data.clear();
        Response.Listener<String> thanhcong = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject chudeobject = jsonArray.getJSONObject(i);
                        FOOD s = new FOOD(chudeobject.getString("Mathucan"),
                                new String(chudeobject.getString("Tenthucan").getBytes("ISO-8859-1"), "UTF-8"),
                                chudeobject.getString("Dongia"),
                                chudeobject.getString("Mota"),
                                chudeobject.getString("Hinhminhhoa"),
                                chudeobject.getString("Maloaithucan"),
                                chudeobject.getString("Xuatxu"),
                                chudeobject.getString("Danhgia"),
                                chudeobject.getString("Soluongdanhgia"),
                                chudeobject.getString("Thoigian"),
                                chudeobject.getString("Soluongban"),
                                chudeobject.getString("Hinh")

                        );
                        food_data.add(s);
                    }
                } catch (JSONException | UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
                foodadapter.notifyDataSetChanged();

            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "lỗi thất bại" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        StringRequest stringRequest = new StringRequest(Request.Method.POST, SERVER.urlfoodtheochude, thanhcong, thatbai) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap params = new HashMap<>();
                params.put("maloaithucan", categories.mathucan);



                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

