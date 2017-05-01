package caplaninnovations.com.livesnapgallery.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import butterknife.BindView;
import butterknife.OnClick;
import caplaninnovations.com.livesnapgallery.R;
import caplaninnovations.com.livesnapgallery.models.Snap;
import caplaninnovations.com.livesnapgallery.recyclers.PictureRecyclerAdapter;
import io.realm.RealmList;

/**
 * Created by Corey on 4/30/2017.
 * Project: LiveSnapGallery
 * <p></p>
 * Purpose of Class:
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RealmList<Snap> snaps = new RealmList<>();
        snaps.add(Snap.getDummy());
        snaps.add(Snap.getDummy());
        snaps.add(Snap.getDummy());
        snaps.add(Snap.getDummy());
        snaps.add(Snap.getDummy());

        RecyclerView.Adapter adapter = new PictureRecyclerAdapter(snaps);
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    int getContentView() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.fab)
    public void onClickStartSlideshow() {
        // TODO
    }

}
