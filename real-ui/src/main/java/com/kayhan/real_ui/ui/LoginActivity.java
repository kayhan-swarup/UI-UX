package com.kayhan.real_ui.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;
import com.kayhan.real_ui.R;
import com.kayhan.real_ui.dialogs.DialogCustom;
import com.kayhan.real_ui.logs.L;
import com.kayhan.real_ui.models.MyTime;

import java.util.concurrent.TimeUnit;


public class LoginActivity extends AppCompatActivity {


    private static final int RC_SIGN_IN = 1221;
    CountryCodePicker ccp;
    boolean clickedOnce=false;
    LinearLayout codeLayout;
    TextInputEditText phoneCode;
    TextView n1,n2,n3,n4;
    String codeReceived;
    PhoneAuthProvider.ForceResendingToken forceResendingToken;
    RelativeLayout verificationLayout;
    InputMethodManager imm;
    TextView []ns;
    CountDownTimer timer;
    TextWatcher codeWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int length = s.length();
            try {
                L.i(String.format("%s, %d, %d, %d",s,start,before,count));
                if(length>0&&length<=6){
                    if(ns[length-1]!=null){
//                        ns[length-1].setBackgroundColor(Color.TRANSPARENT);
                        ns[length-1].setTextColor(getResources().getColor(R.color.accentDarker));
                        ns[length-1].setText(s.charAt(s.length()-1)+"");
                    }
                    if(length == 6){
                        imm.hideSoftInputFromWindow(phoneCode.getWindowToken(), 0);
                        try {
                            timer.cancel();
                        } catch (Exception e) {
                            L.e(e);
                        }
                        matchCode();



                    }
                }

            } catch (Exception e) {
                L.e(e);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            L.i("Auto Verified...");

             onSuccess(phoneAuthCredential);
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            try {
                DialogCustom.dismissUpload();
                DialogCustom.getStandardDialog(verificationLayout.getContext(),"Error","Something went wrong","Try again",null,null).show();
            } catch (Exception exception) {
                L.e(e);
            }

        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            try {
                L.i(String.format("On code sent %s, token: %s",s,forceResendingToken.toString()));
            } catch (Exception e) {
                L.e(e);
            }
            DialogCustom.dismissUpload();
            LoginActivity.this.codeReceived = s;
            LoginActivity.this.forceResendingToken = forceResendingToken;
            initCodeLayout();
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    MaterialButton verify;
    TextInputLayout inputLayout;
    TextInputEditText phone;
    TextWatcher watcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(clickedOnce&&phone.getText().length()!=0&&phone.getText().toString().startsWith("01")&&phone.getText().length()==11){
                inputLayout.setError(null);
            }else{
                inputLayout.setError("Invalid phone number");
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_login);

//        ccp.setTextView_selectedCountry(findViewById(R.id.ideal));
//        login();

        init();

    }

    private void init() {
        phone = findViewById(R.id.phone);
        verificationLayout = findViewById(R.id.verificationLayout);
        inputLayout = findViewById(R.id.inputLayout);
        codeLayout = findViewById(R.id.codeLayout);
        phoneCode = findViewById(R.id.phoneCode);
        ns =new TextView[6];
        ns[0] = findViewById(R.id.n1);
        ns[1] = findViewById(R.id.n2);
        ns[2] = findViewById(R.id.n3);
        ns[3] = findViewById(R.id.n4);
        ns[4] = findViewById(R.id.n5);
        ns[5] = findViewById(R.id.n6);


        verify =findViewById(R.id.verify);
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                L.i("phone: "+phone.getText());
                if(!validate()){
                    phone.setError("Invalid phone number");
                    clickedOnce = true;


                }else{
                    String phoneNumber = "+88"+phone.getText().toString();
                    PhoneAuthOptions options =
                            PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
                                    .setPhoneNumber(phoneNumber)       // Phone number to verify
                                    .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                                    .setActivity( LoginActivity.this)                 // Activity (for callback binding)
                                    .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                                    .build();

//                    DialogCustom.getProgressDialog(v.getContext()).show();
                    try {
                        DialogCustom.getProgressDialog(verificationLayout.getContext()).show();
                    } catch (Exception e) {
                        L.e(e);
                    }
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(options);
                }



            }
        });



    }

    private void initCodeLayout() {
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.slide_up_anim);
        animation.setFillBefore(true);
        verificationLayout.setVisibility(View.VISIBLE);
        verificationLayout.startAnimation(animation);
        phoneCode.addTextChangedListener(codeWatcher);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startTimer();
                phoneCode.setFocusable(true);
                phoneCode.setShowSoftInputOnFocus(true);
                phoneCode.requestFocus();
                imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(phoneCode, InputMethodManager.SHOW_IMPLICIT);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void matchCode(){
        try {
            L.i(phoneCode.getText().toString());
            L.i(codeReceived);
        } catch (Exception e) {
            L.e(e);
        }
//        DialogCustom.getProgressDialog(verificationLayout.getContext(),"Verifying","Trying to log in").show();

        onSuccess(PhoneAuthProvider.getCredential(codeReceived,phoneCode.getText().toString().trim()));
        if(phoneCode.getText().toString().trim().equals(codeReceived)){
            L.i("Matched!");

        }

    }

    private void onSuccess(PhoneAuthCredential phoneAuthCredential) {
        try {
            DialogCustom.getProgressDialog(verificationLayout.getContext(),"Verifying!","Signing you in. Please wait")
                    .show();
        } catch (Exception e) {
            L.e(e);
        }

        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            FirebaseUser user=task.getResult().getUser();





                        }else{
                            DialogCustom.getStandardDialog(verificationLayout.getContext(),
                                    "Error","The code you entered is invalid. Please check your SMS, and try again",
                                    "Try again",null,null).show();
                        }
                    }
                });

    }
    public void closePage(){
        DialogCustom.dismissUpload();

//        UserDataHandler.getInstance().listenUser();
        removeCodeLayout();
//        startActivity(new Intent())
        finish();
    }
    private void getAccount(FirebaseUser user) {
        DialogCustom.setProgressMessage("Please wait","Loading account info");

    }

    private void removeCodeLayout() {
        Animation animation = AnimationUtils.loadAnimation(phoneCode.getContext(),R.anim.slide_down_anim);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                verificationLayout.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        verificationLayout.startAnimation(animation);
    }

    private void startTimer() {
        TextView center =findViewById(R.id.center);
        center.setText(String.valueOf(60));
        timer =new CountDownTimer(MyTime.minutes(1),MyTime.seconds(1)) {
            @Override
            public void onTick(long millisUntilFinished) {

                double inSec = (double)millisUntilFinished/1000;
                long valInt = Math.round(inSec);
                if(Integer.parseInt(center.getText().toString())!=inSec){
                    center.setText(String.valueOf(valInt));
                }
            }

            @Override
            public void onFinish() {
                timer.cancel();
            }
        }.start();
    }

    public boolean validate(){
        boolean validation = true;
        if(phone.getText().length()==0||!phone.getText().toString().startsWith("01")||phone.getText().length()!=11){
            validation = false;

        }


        return validation;
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){

        }
    }
}
