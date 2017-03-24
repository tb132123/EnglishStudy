package app.leiyu.com.english;

import android.content.Intent;
import android.os.Bundle;
import android.os.AsyncTask;
import app.leiyu.com.english.utill.*;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.event.NotificationClickEvent;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TranslateActivity extends AppCompatActivity {
    private  String word="",str="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        JMessageClient.registerEventReceiver(this);
        Button trans=(Button)findViewById(R.id.button6);
        EditText input=(EditText)findViewById(R.id.editText3);
        TextView tt=(TextView)findViewById(R.id.textView2);
        Button friend=(Button)findViewById(R.id.button4);
        Button message=(Button)findViewById(R.id.button5);

        //go to friend view
        friend.setOnClickListener(v->{
            Intent intent=new Intent(this,FriendActivity.class);
            this.startActivity(intent);
            finish();
        });
        //translate words
        trans.setOnClickListener(v->{
            str=input.getText().toString().trim();
            if(str.length()==0){
                Toast.makeText(this,"please input words!",Toast.LENGTH_SHORT).show();
                tt.setText("");
                return;
            }else{
                Mytask task=new Mytask();
                String []num={str};
                task.execute(num);
                //wait for asyn task to complete
                try{
                    Thread.sleep(500);
                }catch (Exception e){}

                if(word.equals("error")){
                    Toast.makeText(this,"please check words ",Toast.LENGTH_LONG).show();
                }else{
                    tt.setText(word);
                }
            }
        });


        //go to message view
        message.setOnClickListener(v->{
            Intent intent=new Intent(this,messageActivity.class);
            this.startActivity(intent);
            finish();
        });

    }

    private  class  Mytask extends AsyncTask<String,Object,String>{


        @Override
        protected String doInBackground(String... params) {
            word =Translate.get_trans(params[0].toString()).toString();
            return  null;
        }
    }

    public void onEventMainThread(NotificationClickEvent eve){
        String name=eve.getMessage().getFromUser().getUserName();
        TextContent con=(TextContent)eve.getMessage().getContent();
        String data=con.getText();
        Intent inten=new Intent(TranslateActivity.this,ChatActivity.class);
        inten.putExtra("user",name);
        inten.putExtra("data",data);
        startActivity(inten);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        JMessageClient.unRegisterEventReceiver(this);
    }

}
