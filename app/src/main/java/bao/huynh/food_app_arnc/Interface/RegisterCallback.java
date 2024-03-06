package bao.huynh.food_app_arnc.Interface;

import org.json.JSONObject;

public interface RegisterCallback {
    void onSuccess(JSONObject response);
    void onError(Exception error);
}
