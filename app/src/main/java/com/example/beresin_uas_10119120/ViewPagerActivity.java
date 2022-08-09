package com.example.beresin_uas_10119120;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.beresin_uas_10119120.Adapter.SliderAdapter;

public class ViewPagerActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private LinearLayoutCompat linearLayout;
    private SliderAdapter sliderAdapter;
    private TextView[] dots;
    Button btn_skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        viewPager = findViewById(R.id.sliderViewPager);
        linearLayout = findViewById(R.id.dot_layout);

        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);
        viewPager.addOnPageChangeListener(viewListener);
        addDotsIndicator(0);

        btn_skip = findViewById(R.id.btn_skip);
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewPagerActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void addDotsIndicator(int position)
    {
        dots = new TextView[3];
        linearLayout.removeAllViews();

        for(int i = 0; i < dots.length; i++){

            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(32);
            dots[i].setTextColor(getResources().getColor(R.color.black_fancy));

            linearLayout.addView(dots[i]);

        }

        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.cloud));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}