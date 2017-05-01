package caplaninnovations.com.livesnapgallery.recyclers;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import caplaninnovations.com.livesnapgallery.R;
import caplaninnovations.com.livesnapgallery.models.Snap;

/**
 * Created by Corey on 4/30/2017.
 * Project: LiveSnapGallery
 * <p></p>
 * Purpose of Class:
 */

class PictureCardViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.picture_view_holder_image)
    ImageView mImageView;

    @BindView(R.id.picture_view_holder_text)
    TextView mTextView;

    PictureCardViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void bind(Snap snap) {
        Log.d(PictureCardViewHolder.class.getSimpleName(), "bind: " + snap.getUrl());
        Glide.with(itemView.getContext())
                .load(snap.getUrl())
                .fitCenter()
                .error(R.drawable.ic_cloud_off_black_48dp)
                .crossFade()
                .into(mImageView);

        DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String date = format.format(new Date(snap.getDateAdded()));
        mTextView.setText(date);
    }

}
