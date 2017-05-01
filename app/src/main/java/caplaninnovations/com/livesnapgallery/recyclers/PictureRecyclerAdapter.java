package caplaninnovations.com.livesnapgallery.recyclers;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import caplaninnovations.com.livesnapgallery.R;
import caplaninnovations.com.livesnapgallery.models.Snap;
import io.realm.RealmList;

/**
 * Created by Corey on 4/30/2017.
 * Project: LiveSnapGallery
 * <p></p>
 * Purpose of Class:
 */

public class PictureRecyclerAdapter extends RecyclerView.Adapter<PictureCardViewHolder> {

    @NonNull
    private final RealmList<Snap> mSnapRealmList;

    public PictureRecyclerAdapter(@NonNull RealmList<Snap> snapRealmList) {
        mSnapRealmList = snapRealmList;
    }

    @Override
    public PictureCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.picture_view_holder, parent, false);
        return new PictureCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PictureCardViewHolder holder, int position) {
        holder.bind(mSnapRealmList.get(position));
    }

    @Override
    public int getItemCount() {
        return mSnapRealmList.size();
    }
}
