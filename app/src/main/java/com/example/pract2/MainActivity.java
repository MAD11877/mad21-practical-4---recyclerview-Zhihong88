package com.example.pract2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Receiving data from ListActivity
        int position = getIntent().getIntExtra("userPosition", 0);
        User user = ListActivity.userList.get(position);

        TextView nameTxt = findViewById(R.id.txtName);
        TextView descTxt = findViewById(R.id.txtDescription);

        nameTxt.setText(String.format("%s", user.name));
        descTxt.setText(String.format("%s", user.description));

        Button btn_message = findViewById(R.id.btnMsg);
        btn_message.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent activity = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(activity);
            }
        });

        Button btn_follow = findViewById(R.id.btnFollow);
        //User user = new User("username", "desc", 1, false);
        follow_condition(user, btn_follow);

        btn_follow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // Perform action on click
                if (!user.followed)
                {
                    user.followed = true;
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    user.followed = false;
                    Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_SHORT).show();
                }
                follow_condition(user, btn_follow);
            }
        });
    }

    public void follow_condition(User user, Button btn)
    {
        if (!user.followed)
        {
            btn.setText("FOLLOW");
        }
        else
        {
            btn.setText("UNFOLLOW");
        }
    }
}
