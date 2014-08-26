package com.wildsmith.material.list.expandable;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

public class ExpandableListDummyView extends View {

    private List<View> views = new ArrayList<View>(8);

    private Drawable divider;

    private int dividerWidth;

    private int dividerHeight;

    public ExpandableListDummyView(Context context) {
        super(context);
    }

    public void setDivider(Drawable divider, int dividerWidth, int dividerHeight) {
        this.divider = divider;
        this.dividerWidth = dividerWidth;
        this.dividerHeight = dividerHeight;

        divider.setBounds(0, 0, dividerWidth, dividerHeight);
    }

    /**
     * Add a view for the DummyView to draw.
     * 
     * @param childView View to draw
     */
    public void addFakeView(View childView) {
        childView.layout(0, 0, getWidth(), getHeight());
        views.add(childView);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        final int len = views.size();
        for (int i = 0; i < len; i++) {
            View v = views.get(i);
            v.layout(left, top, right, bottom);
        }
    }

    public void clearViews() {
        views.clear();
    }

    @Override
    public void dispatchDraw(Canvas canvas) {
        canvas.save();

        divider.setBounds(0, 0, dividerWidth, dividerHeight);

        final int len = views.size();
        for (int i = 0; i < len; i++) {
            View v = views.get(i);
            v.draw(canvas);
            canvas.translate(0, v.getMeasuredHeight());
            divider.draw(canvas);
            canvas.translate(0, dividerHeight);
        }

        canvas.restore();
    }
}