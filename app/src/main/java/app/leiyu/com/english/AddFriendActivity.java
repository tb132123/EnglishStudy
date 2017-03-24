package app.leiyu.com.english;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.jpush.im.android.api.ContactManager;
import cn.jpush.im.api.BasicCallback;

public class AddFriendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        Button add=(Button)findViewById(R.id.button9);
        EditText email=(EditText)findViewById(R.id.editText);
        EditText reason=(EditText)findViewById(R.id.editText2);
/*
        add.setOnClickListener(v->{
            String name=email.getText().toString().trim();
            String str=reason.getText().toString().trim();
            if(name.length()==0){
                Toast.makeText(this,"please enter email",Toast.LENGTH_SHORT).show();
                email.setText("");
                return;
            }else {
                ContactManager.sendInvitationRequest(name, "", str, new BasicCallback() {
                    @Override
                    public void gotResult(int i, String s) {
                        if (i == 0) {
                            Toast.makeText(getApplicationContext(), "Successful application！", Toast.LENGTH_SHORT).show();
                        }else {

                            Toast.makeText(getApplicationContext(), "Application failure", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
 */
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=email.getText().toString().trim();
                String str=reason.getText().toString().trim();
                if(name.length()==0){
                    Toast.makeText(AddFriendActivity.this,"please enter email",Toast.LENGTH_SHORT).show();
                    email.setText("");
                    return;
                }else {
                    ContactManager.sendInvitationRequest(name, "", str, new BasicCallback() {
                        @Override
                        public void gotResult(int i, String s) {
                            if (i == 0) {
                                Toast.makeText(getApplicationContext(), "Successful application！", Toast.LENGTH_SHORT).show();
                            }else {

                                Toast.makeText(getApplicationContext(), "Application failure", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
