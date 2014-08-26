package com.wildsmith.material.presentation.rows;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.wildsmith.material.R;
import com.wildsmith.material.utils.ResourceHelper;

public class PresentationImageRow extends PresentationListRow {

    private int imageResourceId;

    @Override
    public void setArguments(Object[] objects) {
        final String resourceName = (String) objects[0];
        imageResourceId = ResourceHelper.getResourceIdentifier(resourceName, "drawable");
    }

    @Override
    public View getView(LayoutInflater inflater, View contentView, Context context, int position) {
        ViewHolder holder = null;
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.presentation_image_row, null);
            holder = createViewHolder(contentView);
        } else {
            Object contentViewHolder = contentView.getTag();
            if (contentViewHolder instanceof ViewHolder == false) {
                contentView = inflater.inflate(R.layout.presentation_image_row, null);
                holder = createViewHolder(contentView);
            } else {
                holder = (ViewHolder) contentViewHolder;
            }
        }

        if (holder == null) {
            return contentView;
        }

        setupImage(holder);
        setupSlideTopSize(position, holder);
        setupFABButtonPosition(holder);

        return contentView;
    }

    private void setupImage(ViewHolder holder) {
        if (holder.presentationImage == null) {
            return;
        }

        Drawable drawable = ResourceHelper.getDrawable(imageResourceId);
        holder.presentationImage.setImageDrawable(drawable);
    }

    @Override
    protected ViewHolder createViewHolder(View contentView) {
        ViewHolder viewHolder = new ViewHolder();
        if (contentView == null) {
            return viewHolder;
        }

        createPresentationViewHolder(viewHolder, contentView);

        viewHolder.presentationImage = (ImageView) contentView.findViewById(R.id.presentation_image);

        contentView.setTag(viewHolder);

        return viewHolder;
    }

    private class ViewHolder extends PresentationViewHolder {

        ImageView presentationImage;
    }
}