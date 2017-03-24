package app.leiyu.com.english;

import android.os.Bundle;
import android.content.Intent;
import java.util.regex.*;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.ProgressDialog;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;

public class RegisterActivity extends AppCompatActivity {
    private ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button register=(Button)findViewById(R.id.button);
        EditText email=(EditText)findViewById(R.id.editText);
        EditText password=(EditText)findViewById(R.id.editText2);

        register.setOnClickListener(v->{
            String em_address = email.getText().toString().trim();
            String pwd = password.getText().toString().trim();

            if (em_address.length() == 0) {
                Toast.makeText(this, "Please enter email address!", Toast.LENGTH_LONG).show();
                return;
            } else if (!this.isValid(em_address)) {
                Toast.makeText(this, "Please check email address!", Toast.LENGTH_LONG).show();
                return;
            } else if (pwd.length() < 7) {
                Toast.makeText(this, "the password length at least 7!", Toast.LENGTH_LONG).show();
                return;
            } else {
                mProgressDialog = ProgressDialog.show(RegisterActivity.this, "Message：", "Being loaded。。。。。。。");
                JMessageClient.register(em_address, pwd, new BasicCallback() {
                    @Override
                    public void gotResult(int i, String s) {
                        if(i==0){
                            mProgressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "register successful！", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            mProgressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "register fail！", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }

        });
    }

    public boolean isValid(String str){
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(str);
        boolean isMatched = matcher.matches();
        return  isMatched;
    }



}
