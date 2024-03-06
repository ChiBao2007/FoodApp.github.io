package bao.huynh.food_app_arnc.Interface;

import org.json.JSONObject;

public interface LoginCallback {
    void onSuccess(JSONObject response);
    void onError(Exception error);
}
