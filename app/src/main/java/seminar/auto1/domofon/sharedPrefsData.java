package seminar.auto1.domofon;

import android.content.Context;
import android.content.SharedPreferences;

public class sharedPrefsData {
    private static String PREF_NAME = "sharedPrefsData";

    static String getNextCode(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getString("nextCode", "1333444573");
    }

    static void saveNextCode(Context context, String nextCode) {
        SharedPreferences mPrefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("nextCode", nextCode);
        mEditor.apply();
    }

    static String getArrayPosition(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getString("arrayPosition", "1");
    }

    static void saveArrayPosition(Context context, String arrayPosition) {
        SharedPreferences mPrefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("arrayPosition", arrayPosition);
        mEditor.apply();
    }

    static int getLastm_z (Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getInt("m_z", 874268747);
    }

    static int getLastm_w (Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getInt("m_w", 156871264);
    }

    static void savem_z (Context context, int newm_z) {
        SharedPreferences mPrefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putInt("m_z", newm_z);
        mEditor.apply();
    }

    static void savem_w (Context context, int newm_w) {
        SharedPreferences mPrefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putInt("m_w", newm_w);
        mEditor.apply();
    }
}
