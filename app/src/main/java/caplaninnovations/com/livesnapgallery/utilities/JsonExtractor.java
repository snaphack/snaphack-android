package caplaninnovations.com.livesnapgallery.utilities;

import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The ease the process and minimize the code necessary to extract JSON, using the
 * Volley Library's JSON parser.
 */
@SuppressWarnings("unused")
public final class JsonExtractor {

    private JSONObject mJsonObject;

    /**
     * @param jsonObject The JSON object that will be safely extracted using this utility.
     */
    public JsonExtractor(JSONObject jsonObject) {
        mJsonObject = jsonObject;
    }

    /**
     * @param fieldName The name of the field to extract from the JSON object.
     * @return The {@link JSONObject} that maps to the given value or null
     */
    @Nullable
    public JSONObject getJsonObject(String fieldName) {
        try {
            return mJsonObject.getJSONObject(fieldName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param fieldName The name of the field to extract from the JSON array.
     * @return The {@link JSONArray} that maps to the given value or null
     */
    @Nullable
    public JSONArray getJsonArray(String fieldName) {
        try {
            return mJsonObject.getJSONArray(fieldName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param fieldName The name of the field to extract from the JSON object.
     * @return The string that maps to the given value or null
     */
    @Nullable
    public String getString(String fieldName) {
        try {
            return mJsonObject.getString(fieldName);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param fieldName The name of the field to extract from the JSON object.
     * @return The int that maps to the given value or -1 if it cannot be extracted.
     */
    public int getInt(String fieldName) {
        try {
            return mJsonObject.getInt(fieldName);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * @param fieldName The name of the field to extract from the JSON object.
     * @return The long that maps to the given value or -1 if it cannot be extracted
     */
    public long getLong(String fieldName) {
        try {
            return mJsonObject.getLong(fieldName);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * @param fieldName The name of the field to extract from the JSON object.
     * @return The boolean that maps to the given value or false if it cannot be extracted.
     */
    public boolean getBoolean(String fieldName) {
        try {
            return mJsonObject.getBoolean(fieldName);
        } catch (Exception e) {
            return false;
        }
    }

}
