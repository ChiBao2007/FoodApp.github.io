package bao.huynh.food_app_arnc.Activity;

import static android.app.PendingIntent.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.squareup.picasso.Picasso;

import java.util.List;

import bao.huynh.food_app_arnc.Fragment.FragmentCard;
import bao.huynh.food_app_arnc.Fragment.FragmentFavorite;
import bao.huynh.food_app_arnc.MODEL.CART;
import bao.huynh.food_app_arnc.MODEL.FOOD;
import bao.huynh.food_app_arnc.R;
import bao.huynh.food_app_arnc.Service.SERVER;

public class ChiTietFood extends AppCompatActivity {

    ImageView imgFood, imgFavorite;
    TextView giathucpham, mota, xuatxu, txtQuantity, tenthucpham;

    AppCompatButton btnBuy, btnMinus, btnPlus,btnAdd;
    MaterialToolbar toolbarChiTiet;
    SearchView searchView;
    private  FOOD food;
    static int quantity = 1;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_food);

        toolbarChiTiet = findViewById(R.id.toolbarChiTiet);
        searchView = findViewById(R.id.searchmainchitiet);
        imgFood = findViewById(R.id.hinhfood);
        giathucpham = findViewById(R.id.giathucpham);
        mota = findViewById(R.id.tvMoTaChiTiet);
        xuatxu = findViewById(R.id.tvXuatXu1);
        imgFavorite = findViewById(R.id.imgFavorite);
        txtQuantity = findViewById(R.id.txtQuantity);
        btnMinus = findViewById(R.id.btnMinus);
        btnPlus = findViewById(R.id.btnPlus);
        btnBuy =findViewById(R.id.btnBuy);
        btnAdd = findViewById(R.id.btnAdd);
        tenthucpham = findViewById(R.id.tenthucpham);




        setSupportActionBar(toolbarChiTiet);
        getSupportActionBar().setTitle("");



        //giam SL
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                giamSoLuong();
            }

            private void giamSoLuong() {
                int quantity = Integer.parseInt(txtQuantity.getText().toString());
                if (quantity > 1) {
                    quantity--;
                    txtQuantity.setText(String.valueOf(quantity));
                }
            }
        });

        //tăng SL
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                txtQuantity.setText(String.valueOf(quantity));
            }


        });
        //ấn mua
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy số lượng từ TextView
                int quantity = Integer.parseInt(txtQuantity.getText().toString());

                // Tăng giá tiền
                double currentPrice = Double.parseDouble(food.getDongia());
                double increasedPrice = currentPrice * quantity;

                // Cập nhật giá tiền trong đối tượng FOOD
                food.setDongia(String.valueOf(increasedPrice));

                // Cập nhật số lượng trong đối tượng FOOD
                food.setSoluongban(String.valueOf(quantity));

                // Chuẩn bị dữ liệu để truyền cho FragmentCard
                Bundle bundle = new Bundle();
                bundle.putInt("soluong", quantity);
                bundle.putSerializable("food", food);

                // Tạo FragmentCard và set dữ liệu vào
                FragmentCard fragmentCard = new FragmentCard();
                fragmentCard.setArguments(bundle);

                // Chuyển sang FragmentCard
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.framerate, fragmentCard);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                FragmentCard.list.add(new CART(
                        food.getMathucan(),
                        food.getTenthucan(),
                        food.getHinhthucan(),
                        food.getDongia(),
                        ""+quantity
                        ));


                // Reset số lượng về 1
                txtQuantity.setText("1");
                Toast.makeText(ChiTietFood.this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
            }
        });

        //Thêm vao giỏ hàng
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy số lượng từ TextView
                int quantity = Integer.parseInt(txtQuantity.getText().toString());

                // Tăng giá tiền
                double currentPrice = Double.parseDouble(food.getDongia());
                double increasedPrice = currentPrice * quantity;

                // Cập nhật giá tiền trong đối tượng FOOD
                food.setDongia(String.valueOf(increasedPrice));

                // Cập nhật số lượng trong đối tượng FOOD
                food.setSoluongban(String.valueOf(quantity));

                // Chuẩn bị dữ liệu để truyền cho FragmentCard
                Bundle bundle = new Bundle();
//                bundle.putInt("quantity", quantity);
                bundle.putSerializable("food", food);

                // Tạo FragmentCard và set dữ liệu vào
                FragmentCard fragmentCard = new FragmentCard();
                fragmentCard.setArguments(bundle);

                FragmentCard.list.add(new CART(
                        food.getMathucan(),
                        food.getTenthucan(),
                        food.getHinhthucan(),
                        food.getDongia(),
                        ""+quantity
                ));


                txtQuantity.setText("1");

                Toast.makeText(ChiTietFood.this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();

            }
        });


        final boolean[] isFavorite = {false};
        // Ấn nút yêu thích
        imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isFavorite[0]) {

                    imgFavorite.setImageResource(R.drawable.baseline_favorite_border_24);
                    isFavorite[0] = false;

                    Toast.makeText(ChiTietFood.this, "Đã xóa khỏi danh sách yêu thích", Toast.LENGTH_SHORT).show();
                } else {


                    imgFavorite.setImageResource(R.drawable.baseline_favorite_24);
                    isFavorite[0] = true;

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("favorite", food);

                    FragmentFavorite fragmentFavorite = new FragmentFavorite();
                    fragmentFavorite.setArguments(bundle);

//                    FragmentManager fragmentManager = getSupportFragmentManager();
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.framerate, fragmentFavorite);
//                    fragmentTransaction.addToBackStack(null);
//                    fragmentTransaction.commit();

                    FragmentFavorite.favorites.add(food);

                    Toast.makeText(ChiTietFood.this, "Đã thêm vào danh sách yêu thích", Toast.LENGTH_SHORT).show();
                }
            }
        });


        toolbarChiTiet.setNavigationIcon(R.drawable.baseline_arrow_back_24);
        toolbarChiTiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        food = (FOOD) intent.getSerializableExtra("FoodObject");

        // Hiển thị thông tin chi tiết
        if (food != null) {
            Picasso.get().load(SERVER.pathImages + food.getHinhthucan()).into(imgFood);
            tenthucpham.setText(food.getTenthucan());
            giathucpham.setText(food.getDongia());
            mota.setText(food.getMota());
            xuatxu.setText(food.getXuatxu());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notification_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.mnu_cart:
                handleCartIconClick();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void handleCartIconClick() {
        FragmentCard fragmentCard = new FragmentCard();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.framerate, fragmentCard)
                .addToBackStack(null)
                .commit();


        Toast.makeText(this, "Giỏ hàng của bạn", Toast.LENGTH_SHORT).show();
    }





}

