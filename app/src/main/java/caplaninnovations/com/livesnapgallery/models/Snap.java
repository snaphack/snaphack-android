package caplaninnovations.com.livesnapgallery.models;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Corey on 4/30/2017.
 * Project: LiveSnapGallery
 * <p></p>
 * Purpose of Class:
 */
public class Snap extends RealmObject {

    @PrimaryKey
    private String mUrl;
    private long mDateAdded;

    public static Snap getDummy() {
        String url = "https://images.pexels.com/photos/59523/pexels-photo-59523.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb";
        return new Snap(url, new Date().getTime());
    }

    public Snap() {
    }

    private Snap(String url, long dateAdded) {
        mUrl = url;
        mDateAdded = dateAdded;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public long getDateAdded() {
        return mDateAdded;
    }

    public void setDateAdded(long mDateAdded) {
        this.mDateAdded = mDateAdded;
    }
}
