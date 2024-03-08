package bao.huynh.food_app_arnc.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONObject;

import bao.huynh.food_app_arnc.Interface.RegisterCallback;
import bao.huynh.food_app_arnc.R;
import bao.huynh.food_app_arnc.Service.APIService;

public class Register extends AppCompatActivity implements RegisterCallback {
    private EditText usernameEditText, emailEditText, passwordEditText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Khởi tạo các EditText
        usernameEditText = findViewById(R.id.edtName);
        emailEditText = findViewById(R.id.edtEmailR);
        passwordEditText = findViewById(R.id.edtPassR);
    }

    public void register(View view) {
        String username = usernameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Gọi API đăng ký
        APIService.register(this, username, email, password, new RegisterCallback() {
            @Override
            public void onSuccess(JSONObject response) {
                // Xử lý khi đăng ký thành công
                Toast.makeText(Register.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }

            @Override
            public void onError(Exception error) {
                // Xử lý khi đăng ký thất bại
                Toast.makeText(Register.this, "Đăng ký thất bại. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void login(View view) {
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
    }

    public void back(View view) {
        Intent intent = new Intent(Register.this, Splash_Screen.class);
        startActivity(intent);
    }

    @Override
    public void onSuccess(JSONObject response) {

    }

    @Override
    public void onError(Exception error) {

    }
}
