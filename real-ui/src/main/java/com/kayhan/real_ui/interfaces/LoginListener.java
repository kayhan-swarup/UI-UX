package com.kayhan.real_ui.interfaces;

import com.google.firebase.auth.FirebaseUser;

public interface LoginListener {

    void onLoggedIn(FirebaseUser firebaseUser);

}
