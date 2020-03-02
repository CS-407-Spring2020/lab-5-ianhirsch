package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class S1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String usernameKey = "username";
        SharedPreferences sharedPreferences =  getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);

        if (!sharedPreferences.getString(usernameKey, "").equals("")){
            String usr = sharedPreferences.getString(usernameKey, "");
            goToActivity2(usr);
        }else{
            setContentView(R.layout.activity_s1);
        }
    }

    public void onButtonClick(View view){
        EditText userField = findViewById(R.id.userName);
        String username = userField.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", username).apply();
        goToActivity2(username);
    }

    public void goToActivity2(String username){
        Intent intent = new Intent(this, S2a.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}
