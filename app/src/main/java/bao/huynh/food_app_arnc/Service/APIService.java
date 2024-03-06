package bao.huynh.food_app_arnc.Service;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONObject;

import bao.huynh.food_app_arnc.Interface.LoginCallback;
import bao.huynh.food_app_arnc.Interface.RegisterCallback;
import bao.huynh.food_app_arnc.VolleySingleton;

public class APIService {
    private static final String BASE_URL = "http://192.168.0.157:3500/";

    public static void login(Context context, String email, String password, final LoginCallback callback) {
        String url = BASE_URL + "api/login";

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("email", email);
            jsonBody.put("password", password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Xử lý phản hồi thành công từ máy chủ
                        // Parse thông tin người dùng từ response và chuyển đến callback
                        callback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Xử lý lỗi khi gửi yêu cầu
                        callback.onError(error);
                    }
                });

        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public static void register(Context context, String username, String email, String password, final RegisterCallback callback) {
        String url = BASE_URL + "api/register";

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("username", username);
            jsonBody.put("email", email);
            jsonBody.put("password", password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Xử lý phản hồi thành công từ máy chủ
                        callback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Xử lý lỗi khi gửi yêu cầu
                        callback.onError(error);
                    }
                });

        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }
}
