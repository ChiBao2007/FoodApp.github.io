package bao.huynh.food_app_arnc.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import bao.huynh.food_app_arnc.Activity.ChiTietFood;
import bao.huynh.food_app_arnc.MODEL.FOOD;
import bao.huynh.food_app_arnc.R;
import bao.huynh.food_app_arnc.Service.SERVER;

public class FASTFOODADATER extends RecyclerView.Adapter<FASTFOODADATER.FASTVIEWHOLDER>{
    Context context;
    ArrayList<FOOD> data;

    public FASTFOODADATER(Context context, ArrayList<FOOD> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public FASTFOODADATER.FASTVIEWHOLDER onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_layout, null);
        return new FASTVIEWHOLDER(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FASTFOODADATER.FASTVIEWHOLDER holder, int position) {
        FOOD fastfood = data.get(position);
        holder.hinhfood.setImageResource(R.drawable.no_image);
        Picasso.get().load(SERVER.pathImages + fastfood.hinhthucan).into(holder.hinhfood);
        holder.tenfood.setText(fastfood.getTenthucan());
        holder.dongia.setText(fastfood.getDongia() + " đồng");
        holder.danhgia.setText(fastfood.getDanhgia());
        holder.soluongdanhgia.setText(fastfood.getSoluongdanhgia());
        holder.thoigian.setText(fastfood.getThoigian());

        //xử lý sự kiện khi được ấn
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(context, ChiTietFood.class);
                intent.putExtra("FoodObject", fastfood);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class FASTVIEWHOLDER extends RecyclerView.ViewHolder {
        ImageView hinhfood;
        TextView tenfood, dongia, danhgia,soluongdanhgia,thoigian;
        public FASTVIEWHOLDER(@NonNull View itemView) {
            super(itemView);
            hinhfood = itemView.findViewById(R.id.hinhfood);
            tenfood = itemView.findViewById(R.id.tenthucpham);
            dongia = itemView.findViewById(R.id.giathucpham);
            danhgia = itemView.findViewById(R.id.danhgia);
            soluongdanhgia = itemView.findViewById(R.id.soluong);
            thoigian = itemView.findViewById(R.id.thoigian);
        }
    }
}
