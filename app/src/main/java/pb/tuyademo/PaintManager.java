package pb.tuyademo;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;

/**
 * Created by PB on 2016/4/23.
 */
public class PaintManager {
    private static final String PREFERENCES_NAME = ".paint";
    private static final String COLOR = ".color";
    private static final String STROKE = ".stroke";

    private static SharedPreferences preferences;


    private static SharedPreferences getSharedPreferences(Context context) {
        if (preferences == null) {
            preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        }
        return preferences;
    }

    public static void savePaint(Context context, int color, float stroke) {
        getSharedPreferences(context).edit()
                .putInt(COLOR, color)
                .putFloat(STROKE, stroke)
                .apply();
    }

    public static void saveColor(Context context, int color) {
        getSharedPreferences(context).edit()
                .putInt(COLOR, color)
                .apply();
    }

    public static void saveStroke(Context context, float stroke) {
        getSharedPreferences(context).edit()
                .putFloat(STROKE, stroke)
                .apply();
    }

    public static void clean(Context context) {
        getSharedPreferences(context)
                .edit()
                .clear()
                .apply();
    }

    public static float getStroke(Context context) {
        return getSharedPreferences(context).getFloat(STROKE, 5.0f);
    }

    public static int getColor(Context context) {
        return getSharedPreferences(context).getInt(COLOR, Color.RED);
    }
}
