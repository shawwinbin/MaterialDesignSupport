package com.wildsmith.material.drawer.rows;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wildsmith.material.R;
import com.wildsmith.material.core.CoreMaterialApplication;
import com.wildsmith.material.list.expandable.AnimatedExpandableListView;
import com.wildsmith.material.list.expandable.ExpandableListRow;
import com.wildsmith.material.utils.ResourceHelper;
import com.wildsmith.material.views.ViewHelper;

public class ExpandableDrawerRow extends ExpandableListRow {

    private AnimatedExpandableListView mListView;

    private String mExpandableRowText;

    private int mExpandableRowImageId;

    public ExpandableDrawerRow(AnimatedExpandableListView listView, String expandableRowText, String expandableRowImage) {
        mListView = listView;
        mExpandableRowText = expandableRowText;
        mExpandableRowImageId = ResourceHelper.getResourceIdentifier(expandableRowImage, "drawable");
    }

    @Override
    public View getView(LayoutInflater inflater, View contentView, Context context, int position) {
        ViewHolder holder = null;
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.drawer_expandable_row, new LinearLayout(CoreMaterialApplication.getContext()), false);
            holder = createViewHolder(contentView);
        } else {
            Object contentViewHolder = contentView.getTag();
            if (contentViewHolder instanceof ViewHolder == false) {
                contentView =
                        inflater.inflate(R.layout.drawer_expandable_row, new LinearLayout(CoreMaterialApplication.getContext()), false);
                holder = createViewHolder(contentView);
            } else {
                holder = (ViewHolder) contentViewHolder;
            }
        }

        if (holder == null) {
            return contentView;
        }

        if (holder.text != null && mExpandableRowText != null) {
            holder.text.setText(mExpandableRowText);
        }

        if (holder.image != null && holder.image.getTag() == null) {
            final int color = ResourceHelper.getColor(R.color.drawer_icon_not_selected_color);
            Drawable drawable = ViewHelper.changeDrawableColor(mExpandableRowImageId, color);
            holder.image.setImageDrawable(drawable);
            holder.image.setTag(0);
        }

        if (holder.expandCollapseImage != null && holder.expandCollapseImage.getTag() == null) {
            final int color = ResourceHelper.getColor(R.color.drawer_chevron_color);
            Drawable drawable = ViewHelper.changeDrawableColor(R.drawable.ic_action_expand_small, color);
            holder.expandCollapseImage.setImageDrawable(drawable);
            holder.expandCollapseImage.setTag(0);
        }

        setupRowClickListener(holder, position);

        return contentView;
    }

    private void setupRowClickListener(final ViewHolder holder, final int position) {
        if (holder.row == null) {
            return;
        }

        holder.row.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (holder.expandCollapseImage.getTag() == null || (Integer) holder.expandCollapseImage.getTag() == 0) {
                    mListView.expandGroupWithAnimation(position);

                    final int color = ResourceHelper.getColor(R.color.drawer_chevron_color);
                    Drawable drawable = ViewHelper.changeDrawableColor(R.drawable.ic_action_collapse_small, color);
                    holder.expandCollapseImage.setImageDrawable(drawable);
                    holder.expandCollapseImage.setTag(1);

                    holder.text.setTextColor(ResourceHelper.getColor(R.color.drawer_item_selected_color));

                    final int newColor = ResourceHelper.getColor(R.color.drawer_icon_selected_color);
                    Drawable newDrawable = ViewHelper.changeDrawableColor(mExpandableRowImageId, newColor);
                    holder.image.setImageDrawable(newDrawable);
                } else {
                    mListView.collapseGroupWithAnimation(position);

                    final int newColor = ResourceHelper.getColor(R.color.drawer_chevron_color);
                    Drawable drawable = ViewHelper.changeDrawableColor(R.drawable.ic_action_expand_small, newColor);
                    holder.expandCollapseImage.setImageDrawable(drawable);
                    holder.expandCollapseImage.setTag(0);

                    holder.text.setTextColor(ResourceHelper.getColor(R.color.drawer_item_not_selected_color));

                    final int color = ResourceHelper.getColor(R.color.drawer_icon_not_selected_color);
                    Drawable newDrawable = ViewHelper.changeDrawableColor(mExpandableRowImageId, color);
                    holder.image.setImageDrawable(newDrawable);
                }
            }
        });
    }

    private ViewHolder createViewHolder(View contentView) {
        ViewHolder viewHolder = new ViewHolder();
        if (contentView == null) {
            return viewHolder;
        }

        viewHolder.row = (LinearLayout) contentView.findViewById(R.id.expandable_row);
        viewHolder.text = (TextView) contentView.findViewById(R.id.text);
        viewHolder.image = (ImageView) contentView.findViewById(R.id.image);
        viewHolder.expandCollapseImage = (ImageView) contentView.findViewById(R.id.expand_collapse_image);

        contentView.setTag(viewHolder);

        return viewHolder;
    }

    private class ViewHolder {

        LinearLayout row;

        TextView text;

        ImageView image;

        ImageView expandCollapseImage;
    }
}