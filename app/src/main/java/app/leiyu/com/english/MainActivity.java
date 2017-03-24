package app.leiyu.com.english;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.leiyu.com.english.utill.Translate;
import cn.jpush.im.android.api.event.NotificationClickEvent;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        JMessageClient.init(this.getApplicationContext());
        JMessageClient.setDebugMode(true);


        Button login=(Button)findViewById(R.id.button);
        Button register=(Button)findViewById(R.id.button2);
        EditText email=(EditText)findViewById(R.id.editText);
        EditText password=(EditText)findViewById(R.id.editText2);


        register.setOnClickListener(v->{
            Intent intent=new Intent(this,RegisterActivity.class);
            this.startActivity(intent);
        });

        login.setOnClickListener(v->{
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
            } else{
                UserInfo user=JMessageClient.getMyInfo();
                if(user!=null){
                    Intent intent=new Intent(MainActivity.this, TranslateActivity.class);
                    startActivity(intent);

                }
                dialog =ProgressDialog.show(MainActivity.this, "Message：", "Being loaded。。。。。。。");
                JMessageClient.login(em_address, pwd, new BasicCallback() {
                    @Override
                    public void gotResult(int i, String s) {
                        if(i==0){
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(), "login successful！", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(MainActivity.this, TranslateActivity.class);
                            startActivity(intent);
                            finish();

                        }else if(i==871304){
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(), "wrong password!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(), "login fail！\ncheck it", Toast.LENGTH_SHORT).show();
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
