package com.tbl.pumblr.utils;

import android.content.Context;
import android.content.SharedPreferences;

import org.apache.commons.lang.StringUtils;

/**
 * Created by 201503105229 on 2015/5/3.
 */
public class SessionManager {

    private static Context context;

    public static final String SP_KEY_ACCESS_TOKEN = "SP_KEY_ACCESS_TOKEN";//ACCESS_TOKEN

    private static SharedPreferences preferences;
    /**
     * 初始化
     *
     * @param context
     */
    public static void init(Context context) {
        SessionManager.context = context;
        preferences = context.getSharedPreferences(AppInfo.APP_NAME, Context.MODE_PRIVATE);
    }

    public static boolean isUserLogin() {
        return StringUtils.isNotBlank(preferences.getString(SP_KEY_ACCESS_TOKEN, null));
    }

    public static boolean saveUserToken(String token){
        return preferences.edit().putString(SP_KEY_ACCESS_TOKEN,token).commit();
    }

    public static boolean clearUserToken(String token){
        return preferences.edit().remove(SP_KEY_ACCESS_TOKEN).commit();
    }

}
