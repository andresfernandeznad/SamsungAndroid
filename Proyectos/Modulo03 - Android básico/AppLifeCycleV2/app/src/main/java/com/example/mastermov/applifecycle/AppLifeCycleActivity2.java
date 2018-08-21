package com.example.mastermov.applifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class AppLifeCycleActivity2 extends Activity implements View.OnClickListener {
    // Make strings for logging
    private final String TAG = "APDM_"+this.getClass().getSimpleName();

    AppLifeCycleApp mapp;

    @Override
    public void onClick(View v) {
        mapp.incCount();
        TextView tv = (TextView) findViewById(R.id.textview1);
        tv.setText("CountApp: "+mapp.getCount()+"\nPID: "+android.os.Process.myPid());
    }

    @Override
    public void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.activity_app_life_cycle2);
        Log.i(TAG, "onCreate");

        mapp = (AppLifeCycleApp) getApplication();

        TextView tv = (TextView) findViewById(R.id.textview1);
        tv.setOnClickListener(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // Notification that the activity will be started
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Notification that the activity is starting
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Notification that the activity will interact with the user
        Log.i(TAG, "onResume");
        TextView tv = (TextView) findViewById(R.id.textview1);
        tv.setText("CountApp: "+mapp.getCount()+"\nPID: "+android.os.Process.myPid());
    }

    protected void onPause() {
        super.onPause();
        // Notification that the activity will stop interacting with the user
        Log.i(TAG, "onPause" + (isFinishing() ? " Finishing" : ""));
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Notification that the activity is no longer visible
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Notification the activity will be destroyed
        Log.i(TAG,
                "onDestroy ");
    }

    // ////////////////////////////////////////////////////////////////////////////
    // Called during the lifecycle, when instance state should be saved/restored
    // ////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Save instance-specific state
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedState) {
        super.onRestoreInstanceState(savedState);
        // Restore state; we know savedState is not null
        String answer = null != savedState ? savedState.getString("answer") : "";
        Log.i(TAG, "onRestoreInstanceState");
    }

    // ////////////////////////////////////////////////////////////////////////////
    // These are the minor lifecycle methods, you probably won't need these
    // ////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.i(TAG, "onPostResume");
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        Log.i(TAG, "onUserLeaveHint");
    }
}
