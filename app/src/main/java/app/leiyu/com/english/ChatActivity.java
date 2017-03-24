package app.leiyu.com.english;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.*;

import app.leiyu.com.english.utill.*;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.NotificationClickEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;


public class ChatActivity extends AppCompatActivity implements View.OnClickListener{
    private String name="",mmsg;
    private List<Chat>chatList=new ArrayList<>();
    private Conversation con;
    private  ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        JMessageClient.registerEventReceiver(this);
        name=this.getIntent().getStringExtra("user").toString();


        if(name!=null){

            JMessageClient.enterSingleConversation(name);
            Chat ct=new Chat();
            ct.setType(ChatAdapter.ITEM_LEFT);
            ct.setValue(name);
            chatList.add(ct);
            //ChatAdapter adapter=new ChatAdapter(getApplicationContext(),chatList);
            //list.setAdapter(adapter);
        }
        list=(ListView)findViewById(R.id.listview);
        EditText input=(EditText)findViewById(R.id.editText4);
        Button send=(Button) findViewById(R.id.button11);
/*
        send.setOnClickListener(v->{
            String str=input.getText().toString();
            Chat ct=new Chat();
            ct.setType(ChatAdapter.ITEM_RIGHT);
            ct.setValue(str);
            chatList.add(ct);
            ChatAdapter adapter=new ChatAdapter(getApplicationContext(),chatList);
            list.setAdapter(adapter);
            Message msg=JMessageClient.createSingleTextMessage(name,str);
            JMessageClient.sendMessage(msg);
            input.setText("");

        });
 */
        send.setOnClickListener(v->{
            String str=input.getText().toString();
            Chat ct=new Chat();
            ct.setType(ChatAdapter.ITEM_RIGHT);
            ct.setValue(str);
            chatList.add(ct);
            ChatAdapter adapter=new ChatAdapter(getApplicationContext(),chatList);
            list.setAdapter(adapter);
            con=JMessageClient.getSingleConversation(name);
            if(con==null){
                con=Conversation.createSingleConversation(name);

            }
            Message msg=con.createSendTextMessage(str);
            JMessageClient.sendMessage(msg);
            input.setText("");

        });

    }


    public void onEventMainThread(MessageEvent event) {
        Message msg = event.getMessage();
        switch (msg.getContentType()) {
            case text:
                // 处理文字消息
                TextContent textContent = (TextContent) msg.getContent();
                Chat ct=new Chat();
                ct.setType(ChatAdapter.ITEM_LEFT);
                ct.setValue(textContent.getText());

                chatList.add(ct);
                ChatAdapter adapter=new ChatAdapter(getApplicationContext(),chatList);
                runOnUiThread(()->{
                    list.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                });


                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        JMessageClient.unRegisterEventReceiver(this);
    }

    @Override
    public void onClick(View v) {
        JMessageClient.getUserInfo(name, new GetUserInfoCallback() {
            @Override
            public void gotResult(int i, String s, UserInfo userInfo) {
                if(i==0){
                    JMessageClient.enterSingleConversation(name);
                }else{
                    Toast.makeText(ChatActivity.this,"Something wrong !",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
