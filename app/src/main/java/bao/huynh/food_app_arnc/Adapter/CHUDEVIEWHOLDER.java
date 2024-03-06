package bao.huynh.food_app_arnc.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import bao.huynh.food_app_arnc.R;

public class CHUDEVIEWHOLDER extends RecyclerView.ViewHolder {
    ImageView hinhcd;
    TextView tencd;
    public CHUDEVIEWHOLDER(@NonNull View itemView) {
        super(itemView);
        hinhcd = itemView.findViewById(R.id.imgHinhcd);
        tencd = itemView.findViewById(R.id.tvTencd);
    }
}
