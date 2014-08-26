package com.wildsmith.material.drawer.rows;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wildsmith.material.R;
import com.wildsmith.material.core.CoreMaterialActivity;
import com.wildsmith.material.core.CoreMaterialApplication;
import com.wildsmith.material.drawer.DrawerOnClickListener;
import com.wildsmith.material.list.ListRow;
import com.wildsmith.material.utils.ResourceHelper;
import com.wildsmith.material.views.ViewHelper;

public class NavigationDrawerRow extends ListRow {

    private CoreMaterialActivity mActivity;

    private String mNavigationText;

    private DrawerOnClickListener mDrawerOnClickListener;

    private int mNavigationImageId;

    public NavigationDrawerRow(CoreMaterialActivity activity, String navigationText, String navigationImage) {
        mActivity = activity;
        mNavigationText = navigationText;
        mNavigationImageId = ResourceHelper.getResourceIdentifier(navigationImage, "drawable");
    }

    @Override
    public View getView(LayoutInflater inflater, View contentView, Context context, int position) {
        ViewHolder holder = null;
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.drawer_navigation_row, new LinearLayout(CoreMaterialApplication.getContext()), false);
            holder = createViewHolder(contentView);
        } else {
            Object contentViewHolder = contentView.getTag();
            if (contentViewHolder instanceof ViewHolder == false) {
                contentView =
                        inflater.inflate(R.layout.drawer_navigation_row, new LinearLayout(CoreMaterialApplication.getContext()), false);
                holder = createViewHolder(contentView);
            } else {
                holder = (ViewHolder) contentViewHolder;
            }
        }

        if (holder == null) {
            return contentView;
        }

        if (holder.text != null) {
            holder.text.setText(mNavigationText);
        }

        if (holder.image != null && holder.image.getTag() == null) {
            final int color = ResourceHelper.getColor(R.color.drawer_icon_not_selected_color);
            Drawable drawable = ViewHelper.changeDrawableColor(mNavigationImageId, color);
            holder.image.setImageDrawable(drawable);
            holder.image.setTag(0);
        }

        setupRowClickListener(holder, position);

        return contentView;
    }

    private void setupRowClickListener(final ViewHolder holder, int position) {
        if (holder.row == null) {
            return;
        }

        if (mDrawerOnClickListener == null) {
            mDrawerOnClickListener = mActivity.createNewDrawerOnClickListener(position);
        }

        holder.row.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                holder.text.setTextColor(ResourceHelper.getColor(R.color.drawer_item_selected_color));

                final int newColor = ResourceHelper.getColor(R.color.drawer_icon_selected_color);
                Drawable newDrawable = ViewHelper.changeDrawableColor(mNavigationImageId, newColor);
                holder.image.setImageDrawable(newDrawable);

                mDrawerOnClickListener.onClick(v);
            }
        });
    }

    private ViewHolder createViewHolder(View contentView) {
        ViewHolder viewHolder = new ViewHolder();
        if (contentView == null) {
            return viewHolder;
        }

        viewHolder.row = (LinearLayout) contentView.findViewById(R.id.navigation_row);
        viewHolder.text = (TextView) contentView.findViewById(R.id.text);
        viewHolder.image = (ImageView) contentView.findViewById(R.id.image);

        contentView.setTag(viewHolder);

        return viewHolder;
    }

    private class ViewHolder {

        LinearLayout row;

        TextView text;

        ImageView image;
    }
}