package bao.huynh.food_app_arnc.Adapter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import java.util.List;

import bao.huynh.food_app_arnc.MODEL.SLIDE;
import bao.huynh.food_app_arnc.R;
import bao.huynh.food_app_arnc.Service.SERVER;

public class SLIDEADAPTER extends PagerAdapter {
    private Context context;
    private List<SLIDE> data;
    private ViewPager viewPager;
    private Handler handler = new Handler();
    private Runnable slideRunnable;

    public SLIDEADAPTER(Context context, List<SLIDE> data, ViewPager viewPager) {
        this.context = context;
        this.data = data;
        this.viewPager = viewPager;
        initSlideShow();
    }

    private void initSlideShow() {
        slideRunnable = new Runnable() {
            @Override
            public void run() {
                int nextSlidePosition = (viewPager.getCurrentItem() + 1) % getCount();
                viewPager.setCurrentItem(nextSlidePosition, true);
                handler.postDelayed(this, 3000); // Điều chỉnh khoảng thời gian làm mới ở đây nếu cần
            }
        };
    }

    public void startSlideShow() {
        handler.postDelayed(slideRunnable, 3000); // Điều chỉnh khoảng thời gian làm mới ở đây nếu cần
    }

    public void stopSlideShow() {
        handler.removeCallbacks(slideRunnable);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.slide_layout, container, false);
        ImageView slide_1 = view.findViewById(R.id.slide_1);

        SLIDE slide = data.get(position);
        if (slide != null) {
            Log.d("SLIDEADAPTER", "Đang tải ảnh: " + slide.getHinhslide());
            String imageUrl = SERVER.pathImages + slide.getHinhslide();
            Glide.with(context).load(imageUrl).into(slide_1);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
