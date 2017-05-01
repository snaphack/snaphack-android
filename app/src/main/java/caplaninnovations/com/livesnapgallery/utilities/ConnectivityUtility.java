package caplaninnovations.com.livesnapgallery.utilities;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import caplaninnovations.com.livesnapgallery.activities.BaseActivity;

/**
 * To easily check different connection types such as GPS settings and  regular
 * device connectivity.
 */
@SuppressWarnings("unused")
public final class ConnectivityUtility {

    private ConnectivityUtility() {
    }

    /**
     * Checks if the given device is connected to any form of wireless network. This method only
     * works if you use the {@link BaseActivity} class, since this method hooks into the class's
     * {@link BaseActivity#onCreate(Bundle)} to method retrieve the global application context.
     *
     * @return True if it's connected to a network or false if it is not.
     */
    public static boolean hasConnection() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) BaseActivity.getGlobalContext()
                        .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

}
