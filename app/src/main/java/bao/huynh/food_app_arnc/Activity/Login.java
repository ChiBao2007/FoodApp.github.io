package bao.huynh.food_app_arnc.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONObject;

import bao.huynh.food_app_arnc.Interface.LoginCallback;
import bao.huynh.food_app_arnc.MainActivity;
import bao.huynh.food_app_arnc.R;
import bao.huynh.food_app_arnc.Service.APIService;

public class Login extends AppCompatActivity implements LoginCallback {
    private EditText emailEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Khởi tạo các EditText
        emailEditText = findViewById(R.id.edtEmail);
        passwordEditText = findViewById(R.id.edtPass);
    }

    public void back(View view) {
        Intent intent = new Intent(Login.this, Splash_Screen.class);
        startActivity(intent);
    }

    public void login(View view) {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        APIService.login(this, email, password, new LoginCallback() {
            @Override
            public void onSuccess(JSONObject response) {
                // Đăng nhập thành công
                @SuppressLint("UnsafeOptInUsageError")
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(Login.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Exception error) {
                // Xử lý lỗi đăng nhập
                Toast.makeText(Login.this, "Sai email hoặc mật khẩu. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void register(View view) {
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
    }

    @Override
    public void onSuccess(JSONObject response) {

    }

    @Override
    public void onError(Exception error) {

    }
}
