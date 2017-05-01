package caplaninnovations.com.livesnapgallery.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import caplaninnovations.com.livesnapgallery.R;
import io.realm.Realm;
import io.realm.RealmConfiguration;

import static android.support.design.widget.AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL;

/**
 * A base class of {@link AppCompatActivity} used to do a standardized setup that should be
 * universal amongst all activities.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();

    private static final String KEY_PROGRESS_SHOWING = "LIBRARY_PROGRESS_SHOWING";
    private static final String KEY_PROGRESS_TEXT = "LIBRARY_PROGRESS_TEXT";

    private static final String RATIONALE_NO_APP_BAR_XML = "You should have \"app_bar.xml\" " +
            "included somewhere in this activity's view hierarchy so the toolbar may be setup " +
            "properly!";

    private static Context sApplicationContext;

    private ProgressDialog progressDialog;
    private String mProgressMessage;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBarLayout;

    private Realm mRealm;
    private boolean isProgressShowing = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (sApplicationContext == null) {
            sApplicationContext = getApplicationContext();
            Realm.init(sApplicationContext);
        }

        int layoutResourceId = getContentView();
        if (layoutResourceId == -1 || layoutResourceId == 0) {
            setContentView(R.layout.base_activity_template);
        } else {
            setContentView(layoutResourceId);
        }

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        mRealm = Realm.getInstance(configuration);

        setupProgressDialogInternal(savedInstanceState);

        // Setup the views for the activity
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        setupBackButton();
    }

    private void setupProgressDialogInternal(Bundle savedInstanceState) {
        progressDialog = new ProgressDialog(this) {
            @Override
            public void setMessage(CharSequence message) {
                super.setMessage(message);
                // Cache the value of message since the progress dialog doesn't have a getter for
                // the message field.
                mProgressMessage = (String) message;
            }

            @Override
            public void onStart() {
                super.onStart();
                isProgressShowing = true;
            }

            @Override
            protected void onStop() {
                super.onStop();
                isProgressShowing = false;
            }
        };
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);

        if (savedInstanceState != null) {
            progressDialog.setMessage(savedInstanceState.getString(KEY_PROGRESS_TEXT));
        }

        setupProgressDialog();

        isProgressShowing = savedInstanceState != null &&
                savedInstanceState.getBoolean(KEY_PROGRESS_SHOWING);
    }

    /**
     * Called after performing the default setup for the BaseActivity's progress dialog. The
     * default setup instantiates it, resets the message field to its most recently saved value
     * (after the call to {@link #onSaveInstanceState(Bundle)}, sets its indeterminate status to
     * true, and sets it to be <b>non</b>cancelable.
     * <br>
     * To use this method and change properties of the progress dialog, use the getter method.
     *
     * @see #getProgressDialog()
     */
    protected void setupProgressDialog() {
        // Stub code, replace with your own implementation
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (isProgressShowing) {
            progressDialog.show();
        }
    }

    /**
     * @return The layout resource ({@link LayoutRes}) that points to the content view for this
     * activity. This layout resource is used in {@link #onCreate(Bundle)} to set the activity's
     * content view. This method is imperative for the sake of automating activity setup.
     */
    @LayoutRes
    abstract int getContentView();

    /**
     * Sets up the back button to be shown in this activity
     */
    protected void setupBackButton() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        } else {
            throw new NullPointerException("SupportActionBar is null. " + RATIONALE_NO_APP_BAR_XML);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Default to going to the home screen
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        getWindow().setEnterTransition(null);
        getWindow().setExitTransition(null);
        getWindow().setReturnTransition(null);
        getWindow().setReenterTransition(null);

        startActivity(intent);
        return false;
    }

    @Override
    public void onBackPressed() {
        onSupportNavigateUp();
    }

    /**
     * @param title The title that should be set on the {@link ActionBar}.
     */
    @SuppressWarnings({"unused", "ConstantConditions"})
    protected final void updateTitle(@StringRes int title) {
        getSupportActionBar().setTitle(title);
    }

    /**
     * Disables scroll flags on the {@link AppBarLayout}.
     */
    @SuppressWarnings("unused")
    protected final void disableScrollFlags() {
        if (mAppBarLayout != null) {
            for (int i = 0; i < mAppBarLayout.getChildCount(); i++) {
                View view = mAppBarLayout.getChildAt(i);
                ((AppBarLayout.LayoutParams) view.getLayoutParams()).setScrollFlags(0);
            }
        } else {
            Log.e(TAG, "disableScrollFlags: AppBarLayout was null. " + RATIONALE_NO_APP_BAR_XML);
        }
    }

    /**
     * @param scrollFlags The scroll flags that should be enabled for all of the
     *                    {@link AppBarLayout}'s children. The scroll flags can be anything other
     *                    than {@link AppBarLayout.LayoutParams#SCROLL_FLAG_SCROLL} since that is
     *                    added by default.
     */
    @SuppressWarnings("unused")
    protected final void enableScrollFlags(int scrollFlags) {
        if (mAppBarLayout != null) {
            for (int i = 0; i < mAppBarLayout.getChildCount(); i++) {
                View view = mAppBarLayout.getChildAt(i);
                ((AppBarLayout.LayoutParams) view.getLayoutParams())
                        .setScrollFlags(SCROLL_FLAG_SCROLL | scrollFlags);
            }
        } else {
            Log.e(TAG, "enableScrollFlags: AppBarLayout was null. " + RATIONALE_NO_APP_BAR_XML);
        }
    }

    /**
     * @return The root view of this activity after doing a {@code findViewById} on
     * {@link R.id#activity_container}
     */
    @SuppressWarnings("unused")
    public final View getRootView() {
        return ButterKnife.findById(this, R.id.activity_container);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (isProgressShowing) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_PROGRESS_SHOWING, isProgressShowing);
        outState.putString(KEY_PROGRESS_TEXT, mProgressMessage);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!mRealm.isClosed()) {
            mRealm.close();
        }
        mRealm = null;
    }

//    MARK - Getters

    /**
     * @return The global application context for the application. This can be accessed after the
     * BaseActivity's first call to {@link #onCreate(Bundle)}.
     * @throws NullPointerException When this method is called before the first call to
     *                              {@link #onCreate(Bundle)}.
     */
    public static synchronized Context getGlobalContext() {
        if (sApplicationContext == null) {
            throw new NullPointerException("The application context has not been setup yet. " +
                    "This is done after the BaseActivity's initial call to onCreate");
        }
        return sApplicationContext;
    }

    /**
     * @return The realm instance used for this activity. This instance is safe to access after
     * this class's call to {@link #onCreate(Bundle)}}.
     */
    @SuppressWarnings("unused")
    protected Realm getRealm() {
        return mRealm;
    }

    /**
     * @return The {@link ProgressDialog} used for this activity's instance. This progress dialog
     * is safe to access after this class's call to {@link #onCreate(Bundle)}.
     */
    @SuppressWarnings("unused")
    protected ProgressDialog getProgressDialog() {
        return progressDialog;
    }

}