package com.example.beresin_uas_10119120.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.beresin_uas_10119120.R;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){

        this.context = context;

    }

    // Arrays
    public int[] slide_images = {
            R.drawable.image_note,
            R.drawable.image_astronout,
            R.drawable.image_time
    };

    public String[] slide_header = {
            "Fast",
            "Simple",
            "Productivity"
    };

    public String[] slide_desc = {
            "Write your notes in no times!",
            "We got a simple yet beautiful UI",
            "Come let's get started!"
    };

    @Override
    public int getCount() {
        return slide_header.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView imageView = view.findViewById(R.id.img_slider);
        TextView header = view.findViewById(R.id.img_header);
        TextView desc = view.findViewById(R.id.img_description);

        imageView.setImageResource(slide_images[position]);
        header.setText(slide_header[position]);
        desc.setText(slide_desc[position]);

        container.addView(view);

        return view;
    };

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);

    }
}
