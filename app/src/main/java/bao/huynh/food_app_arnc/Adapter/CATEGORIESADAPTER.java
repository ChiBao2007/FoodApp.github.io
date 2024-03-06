package bao.huynh.food_app_arnc.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import bao.huynh.food_app_arnc.Activity.FoodTheoChuDeActivity;
import bao.huynh.food_app_arnc.MODEL.CATEGORIES;
import bao.huynh.food_app_arnc.R;
import bao.huynh.food_app_arnc.Service.SERVER;

public class CATEGORIESADAPTER extends RecyclerView.Adapter<CHUDEVIEWHOLDER> {
    Context context;
    ArrayList<CATEGORIES> data;

    public CATEGORIESADAPTER(Context context, ArrayList<CATEGORIES> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public CHUDEVIEWHOLDER onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chude_item_layout, null);

        return new CHUDEVIEWHOLDER(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CHUDEVIEWHOLDER holder, int position) {
        CATEGORIES cate =data.get(position);
        //hình chờ load từ sv
        holder.hinhcd.setImageResource(R.drawable.no_image);
        Picasso.get().load(SERVER.pathImages+cate.hinhthucan).into(holder.hinhcd);
        holder.tencd.setText(cate.tenthucan);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FoodTheoChuDeActivity.class);
                intent.putExtra("ChudeObject",cate);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
