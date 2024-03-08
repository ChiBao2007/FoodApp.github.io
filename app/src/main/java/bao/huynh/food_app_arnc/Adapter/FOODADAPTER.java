package bao.huynh.food_app_arnc.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import bao.huynh.food_app_arnc.Activity.ChiTietFood;
import bao.huynh.food_app_arnc.MODEL.FOOD;
import bao.huynh.food_app_arnc.R;
import bao.huynh.food_app_arnc.Service.SERVER;

public class FOODADAPTER extends RecyclerView.Adapter<FOODADAPTER.FOODVIEWHOLDER> implements Filterable {

    Context context;
    ArrayList<FOOD> data;
    ArrayList<FOOD> filterdata;

    public FOODADAPTER(Context context, ArrayList<FOOD> data) {
        this.context = context;
        this.data = data;
        this.filterdata = new ArrayList<>(data);

    }

    @NonNull
    @Override
    public FOODVIEWHOLDER onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_layout,null);
        return new FOODVIEWHOLDER(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FOODVIEWHOLDER holder, int position) {
        FOOD food = data.get(position);

        holder.hinhfood.setImageResource(R.drawable.no_image);
        Picasso.get().load(SERVER.pathImages + food.hinhthucan).into(holder.hinhfood);
        holder.tenfood.setText(food.getTenthucan());
        holder.dongia.setText(food.getDongia() + " đồng");
        holder.danhgia.setText(food.getDanhgia());
        holder.soluongdanhgia.setText(food.getSoluongdanhgia());
        holder.thoigian.setText(food.getThoigian());

        //xử lý sự kiện khi được ấn
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(context, ChiTietFood.class);
                intent.putExtra("FoodObject", food);
                context.startActivity(intent);
            }
        });


    }



    @Override
    public int getItemCount() {
//        return filterdata.size();
        return data.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<FOOD> datatemp = new ArrayList<>();
            String text = constraint.toString();
            if (text == null || text.length() == 0) {
                datatemp.addAll(data);
            }else {
                for (FOOD f : data) {
                    if (f.tenthucan.toLowerCase().contains(text.trim().toLowerCase()))

                        datatemp.add(f);
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = datatemp;


            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filterdata.clear();
            filterdata.addAll((ArrayList) results.values);
            notifyDataSetChanged();

        }
    };


    public class FOODVIEWHOLDER extends RecyclerView.ViewHolder {
        ImageView hinhfood;
        TextView tenfood, dongia, danhgia,soluongdanhgia,thoigian;
        public FOODVIEWHOLDER(@NonNull View itemView) {
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
