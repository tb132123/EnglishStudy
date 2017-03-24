package app.leiyu.com.english;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.leiyu.com.english.utill.Translate;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;

public class PasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        EditText pass1=(EditText)findViewById(R.id.editText5);
        EditText pass2=(EditText)findViewById(R.id.editText6);
        EditText pass_old=(EditText)findViewById(R.id.editText7);
        Button change=(Button)findViewById(R.id.button12);

        change.setOnClickListener(v->{
            String Str1=pass1.getText().toString().trim();
            String Str2=pass2.getText().toString().trim();
            String old=pass_old.getText().toString().trim();
            if(Str1.length()<7){
                Toast.makeText(this,"At least 7 Length",Toast.LENGTH_SHORT).show();
            }else if(!Str1.equals(Str2)){
                Toast.makeText(this,"Two input inconsistency",Toast.LENGTH_SHORT).show();
            }else{
                JMessageClient.updateUserPassword(old, Str1, new BasicCallback() {
                    @Override
                    public void gotResult(int responseCode, String updadePasswordDesc) {
                        if (responseCode == 0) {
                            Toast.makeText(getApplicationContext(), "Update success", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(PasswordActivity.this, TranslateActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                          //  Log.i("UpdatePassword", "JMessageClient.updateUserPassword" + ", responseCode = " + responseCode + " ; desc = " + updadePasswordDesc);
                            Toast.makeText(getApplicationContext(), "Update fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
