package com.wizeline.cryptoconverter.conversionList;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wizeline.cryptoconverter.R;
import com.wizeline.cryptoconverter.data.model.Conversion;

/**
 * Created by Nicole Terc on 7/27/17.
 */

public class ListViewHolder extends RecyclerView.ViewHolder {

    ImageView imageFrom;
    TextView textFrom;
    ImageView imageTo;
    TextView textTo;
    TextView textChange;


    public ListViewHolder(View itemView) {
        super(itemView);
        imageFrom = itemView.findViewById(R.id.image_from);
        textFrom = itemView.findViewById(R.id.text_from);
        imageTo = itemView.findViewById(R.id.image_to);
        textTo = itemView.findViewById(R.id.text_to);
        textChange = itemView.findViewById(R.id.text_change);
    }

    public void bindItem(Conversion item) {
        //Bind Text
        textFrom.setText(item.getFromSymbol());
        textTo.setText(item.getToSymbol());
        textChange.setText(getFormattedChange(item.getChange()));

        //Bind Images
        Picasso.with(itemView.getContext())
                .load(item.getFromImageUrl())
                .into(imageFrom);

        Picasso.with(itemView.getContext())
                .load(item.getToImageUrl())
                .into(imageTo);
    }

    private SpannableString getFormattedChange(Double change) {
        String message = String.format("%.2f", change);
        int iconRes;
        int colorRes;

        if (change > 0) { //positive change
            iconRes = R.drawable.ic_arrow_up;
            colorRes = R.color.colorChangeUp;
        } else if (change < 0) { //negative change
            iconRes = R.drawable.ic_arrow_down;
            colorRes = R.color.colorChangeDown;
        } else { //no change
            iconRes = R.drawable.ic_no_change;
            colorRes = R.color.colorText;
        }

        Drawable iconDrawable = itemView.getContext().getResources().getDrawable(iconRes);
        iconDrawable.setBounds(0, 0, iconDrawable.getIntrinsicWidth(), iconDrawable.getIntrinsicHeight());
        int changeColor = itemView.getContext().getResources().getColor(colorRes);

        SpannableString formattedChange = new SpannableString(" " + message);
        formattedChange.setSpan(new ForegroundColorSpan(changeColor), 0, message.length() + 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        formattedChange.setSpan(new ImageSpan(iconDrawable, DynamicDrawableSpan.ALIGN_BOTTOM), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return formattedChange;
    }


}
