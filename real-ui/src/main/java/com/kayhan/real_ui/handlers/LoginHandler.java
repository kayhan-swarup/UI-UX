package com.kayhan.real_ui.handlers;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.kayhan.real_ui.interfaces.LoginListener;
import com.kayhan.real_ui.logs.L;
import com.kayhan.real_ui.ui.LoginActivity;

public class LoginHandler {
    private static LoginHandler instance=new LoginHandler();

    private LoginHandler(){

    }

    public static LoginHandler getInstance() {
        return instance;
    }
    private LoginListener listener;
    public void startLogin(Context context , LoginListener listener){
        if (FirebaseAuth.getInstance().getCurrentUser()==null) {
            this.listener = listener;
            context.startActivity(new Intent(context, LoginActivity.class));
        }else{
            L.i("Logged in already");
            Toast.makeText(context,"Logged in already",Toast.LENGTH_LONG).show();
        }

    }

    public LoginListener getListener() {
        return listener;
    }
}
