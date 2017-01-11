package com.stream.fastdevelop.fragment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.stream.fastdevelop.R;
import com.stream.fastdevelop.utils.DimenUtils;

/**
 * description：
 * ===============================
 * creator：ZhangChuanchuan
 * create time：2017/1/3 18:14
 * ===============================
 * reasons for modification：
 * Modifier：
 * Modify time：
 */

public class DrawDemoFragment extends CommonFragment {

    ImageView imageView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_draw_layout, container, false);
        imageView = (ImageView) view.findViewById(R.id.draw_image);
        initView();
        return view;
    }
    float startX = 0;
    float startY = 0;

    Canvas canvas;
    Paint paint;
    Bitmap bitmap;
    Bitmap cacheBitmap;
    private void initView() {
        bitmap = Bitmap.createBitmap(DimenUtils.getDisplayWidth(getActivity()),
                DimenUtils.getDisplayHeight(getActivity()), Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(7);
        paint.setStyle(Paint.Style.STROKE);
        imageView.setImageBitmap(bitmap);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        cacheBitmap = Bitmap.createBitmap(bitmap);
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                        canvas.drawBitmap(cacheBitmap, new Matrix(), paint);
                        canvas.drawRect(startX, startY, event.getX(), event.getY(), paint);
                        imageView.invalidate();
                        break;
                    case MotionEvent.ACTION_UP:


                        break;
                }
                return true;
            }
        });
    }

}
