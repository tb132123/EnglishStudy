package app.leiyu.com.english;

import android.content.Intent;
import android.os.Bundle;
import cn.jpush.im.android.api.ContactManager;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoListCallback;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.event.NotificationClickEvent;
import cn.jpush.im.android.api.model.UserInfo;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.*;
import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class FriendActivity extends AppCompatActivity {
    private List<String> lists=new ArrayList<>();
    private String user_name="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        JMessageClient.registerEventReceiver(this);
        ListView view=(ListView)findViewById(R.id.listview);
        Button trans=(Button)findViewById(R.id.button7);
        Button add=(Button)findViewById(R.id.button10);
        Button message=(Button)findViewById(R.id.button9);

        //add friend
        add.setOnClickListener(v->{
            Intent intent=new Intent(this,AddFriendActivity.class);

            startActivity(intent);
        });
        //go to translate view
        trans.setOnClickListener(v->{
            Intent intent=new Intent(this,TranslateActivity.class);
            this.startActivity(intent);
            finish();
        });
        //list all friends
        ContactManager.getFriendList(new GetUserInfoListCallback() {
            @Override
            public void gotResult(int i, String s, List<UserInfo> list) {
                if(i==0){
                    for (UserInfo user:list){
                        lists.add(user.getUserName());
                    }

                    if(lists.size()==0){
                        Toast.makeText(FriendActivity.this,"You have no friends",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    view.setAdapter(new ArrayAdapter<String>(FriendActivity.this, android.R.layout.simple_list_item_1, lists));

                }else{
                    Toast.makeText(FriendActivity.this,"get friends fail!",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        //go to message view
        message.setOnClickListener(v->{
            Intent intent=new Intent(this,messageActivity.class);
            this.startActivity(intent);
            finish();
        });

        view.setOnItemClickListener((a, b, c, d) -> {
            user_name= lists.get(c);
            //警告框给用户选择
            new AlertDialog.Builder(this).setTitle("chat with "+user_name+"?")
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent=new Intent(FriendActivity.this,ChatActivity.class);

                            intent.putExtra("user",user_name);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
        });

    }

    public void onEventMainThread(NotificationClickEvent eve){
        String name=eve.getMessage().getFromUser().getUserName();
        TextContent con=(TextContent)eve.getMessage().getContent();
        String data=con.getText();
        Intent inten=new Intent(FriendActivity.this,ChatActivity.class);
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
