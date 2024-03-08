package bao.huynh.food_app_arnc.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import bao.huynh.food_app_arnc.Activity.Cart;
import bao.huynh.food_app_arnc.MODEL.CART;
import bao.huynh.food_app_arnc.MODEL.FOOD;
import bao.huynh.food_app_arnc.R;
import bao.huynh.food_app_arnc.Service.SERVER;

public class CARTADAPTER extends RecyclerView.Adapter<CARTADAPTER.ViewHolder> {
    private List<CART> data;
    Context context;

    public CARTADAPTER(List<CART> data, Context context) {
        this.data = data;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CARTADAPTER.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CARTADAPTER.ViewHolder holder, int position) {
        CART food = data.get(position);

        // Hiển thị thông tin của mỗi mục trong danh sách giỏ hàng
        holder.imgFood.setImageResource(R.drawable.no_image);
        Picasso.get().load(SERVER.pathImages + food.getHinhthucan()).into(holder.imgFood);
        holder.tvCartItemName.setText(food.getTenthucan());
        holder.tvCartItemPrice.setText(food.getDongia() + " đồng");
        holder.tvCartItemQuantity.setText(food.getSoluong());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Cart.class);
                intent.putExtra("cart", food);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCartItemName, tvCartItemPrice, tvCartItemQuantity;
        AppCompatButton btnDel;
        ImageView imgFood;
        public ViewHolder(View view) {
            super(view);
            tvCartItemName = itemView.findViewById(R.id.cartItemName);
            tvCartItemPrice = itemView.findViewById(R.id.cartItemPrice);
            tvCartItemQuantity = itemView.findViewById(R.id.cartItemQuantity);
            btnDel = itemView.findViewById(R.id.cartItemBtnRemove);
            imgFood = itemView.findViewById(R.id.cartItemImage);
        }
    }
    public void updateQuantity(int position, String newQuantity) {
        CART food = data.get(position);
        food.setSoluong(newQuantity);
        notifyItemChanged(position);
    }
}
