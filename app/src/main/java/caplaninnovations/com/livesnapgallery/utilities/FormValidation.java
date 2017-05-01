package caplaninnovations.com.livesnapgallery.utilities;

import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;

import caplaninnovations.com.livesnapgallery.R;
import caplaninnovations.com.livesnapgallery.activities.BaseActivity;


/**
 * A utility class that contains methods for ensuring a form was entered properly
 * by the user.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public final class FormValidation {

    private FormValidation() {
    }

    /**
     * Checks if a given field contains valid text by checking to see if it is not empty, has
     * enough text in it, or if it is too long. If it is invalid, the proper "error" text is set
     * on the {@link TextInputLayout} so that the user can respond and act upon the error.
     *
     * @param field           The field that should be validated
     * @param minLength       The minimum length the field can be after trimming off white space.
     * @param maxLength       The max length the field can be after trimming white off space.
     * @param textInputLayout The text input layout that will receive the error and update the UI
     *                        if the message is invalid.
     * @return True if the field is valid or false if it's not.
     */
    public static boolean isFieldValid(String field, int minLength, int maxLength,
                                       TextInputLayout textInputLayout) {
        if (FormValidation.isEmpty(field)) {
            textInputLayout.setError(
                    BaseActivity.getGlobalContext().getString(R.string.error_required)
            );
            return false;
        } else if (FormValidation.isTooShort(minLength, field)) {
            textInputLayout.setError(
                    BaseActivity.getGlobalContext().getString(R.string.error_too_short)
            );
            return false;
        } else if (FormValidation.isTooLong(maxLength, field)) {
            textInputLayout.setError(
                    BaseActivity.getGlobalContext().getString(R.string.error_too_long)
            );
            return false;
        } else {
            // Set the error to null.
            textInputLayout.setError(null);
            return true;
        }
    }

    /**
     * Checks if a given field contains valid text by checking to see if it is not empty, has
     * enough text in it, or if it is too long. If it is invalid, the proper "error" text is set
     * on the {@link TextInputLayout} so that the user can respond and act upon the error.
     *
     * @param field           The field that should be validated
     * @param textInputLayout The text input layout that will receive the error and update the UI
     *                        if the message is invalid.
     * @return True if the field is valid or false if it's not.
     */
    public static boolean isFieldValid(String field, int maxLength, TextInputLayout textInputLayout) {
        return isFieldValid(field, 3, maxLength, textInputLayout);
    }

    /**
     * Checks if a field is empty (after trimming it).
     *
     * @param field The field that should be checked
     * @return True if the field is empty or false if it is not.
     */
    public static boolean isEmpty(@Nullable String field) {
        return field == null || field.trim().length() == 0;
    }

    /**
     * Checks if a text field is too short (after trimming it).
     *
     * @param minimumLength The minimum length of the field.
     * @param field         The field that should be checked.
     * @return True if the field is too short or false if its length is okay.
     */
    public static boolean isTooShort(int minimumLength, @Nullable String field) {
        return field == null || field.trim().length() < minimumLength;
    }

    /**
     * Checks if a text field is too long (after trimming it).
     *
     * @param maximumLength The minimum length of the field.
     * @param field         The field that should be checked.
     * @return True if the field is too long or false if its length is okay.
     */
    public static boolean isTooLong(int maximumLength, @Nullable String field) {
        return field == null || field.trim().length() > maximumLength;
    }

}
