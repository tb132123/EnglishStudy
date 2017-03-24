package app.leiyu.com.english;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;


public class messageActivity extends AppCompatActivity {
    private List<String>data=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button friend=(Button)findViewById(R.id.button17);
        Button trans=(Button)findViewById(R.id.button18);
        Button pass=(Button)findViewById(R.id.button13);



        trans.setOnClickListener(v->{
            Intent intent=new Intent(this,TranslateActivity.class);
            this.startActivity(intent);
            finish();
        });

        //go to friend view
        friend.setOnClickListener(v->{
            Intent intent=new Intent(this,FriendActivity.class);

            this.startActivity(intent);
            finish();
        });

        pass.setOnClickListener(v->{
            Intent intent =new Intent(this,PasswordActivity.class);
            this.startActivity(intent);
        });


    }


}
