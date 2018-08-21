package com.example.mastermov.applifecycle;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class AppLifeCycleActivity1 extends Activity implements View.OnClickListener {
    // Make strings for logging
    private final String TAG = "APDM_"+this.getClass().getSimpleName();

    private int count = 0;
    private int countSavedState = 0;
    static private int countStatic = 0;

    AppLifeCycleApp mapp;
    SharedPreferences spref;

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.textview2) {
            Intent intent = new Intent(this, AppLifeCycleActivity2.class); //Para cambiar de activdad
            startActivity(intent);
        } else if (v.getId() == R.id.textview1) {
            count++;
            countSavedState++;
            countStatic++;
            mapp.incCount(); //Contador de la aplicacion.
            TextView tv = (TextView) findViewById(R.id.textview1);
            tv.setText("Count: "+count+"\nCountSavedSate: "+countSavedState+"\nCountStatic: "+countStatic
                    +"\nCountApp: "+mapp.getCount()+"\nPID: "+android.os.Process.myPid());
        }
    }

    @Override
    public void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.activity_app_life_cycle1);
        // get app
        mapp = (AppLifeCycleApp) getApplication();

        //Log.i(TAG, "onCreate");

        // savedState could be null
        if (null != savedState) {
            countSavedState = savedState.getInt("countSaved");
            Log.i(TAG, "onCreate -> restoring countSavedState to "+countSavedState);
        } else {
            Log.i(TAG, "onCreate -> null Bundle");
        }

        TextView tv = (TextView) findViewById(R.id.textview1);
        tv.setOnClickListener(this);
        tv = (TextView) findViewById(R.id.textview2);
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
        tv.setText("Count: "+count+"\nCountSavedSate: "+countSavedState+"\nCountStatic: "+countStatic
                +"\nCountApp: "+mapp.getCount()+"\nPID: "+android.os.Process.myPid());
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
        Log.i(TAG, "onStop" + (isFinishing() ? " Finishing" : ""));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Notification the activity will be destroyed
        Log.i(TAG,
                "onDestroy" + (isFinishing() ? " Finishing" : ""));
    }

    // ////////////////////////////////////////////////////////////////////////////
    // Called during the lifecycle, when instance state should be saved/restored
    // ////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Save instance-specific state
        outState.putInt("countSaved", countSavedState);
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState -> saving countSavedSate = "+countSavedState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedState) {
        super.onRestoreInstanceState(savedState);
        // Restore state; we know savedState is not null
        countSavedState = savedState.getInt("countSaved", 0);
        Log.i(TAG, "onRestoreInstanceState -> restoring countSavedState to "+countSavedState);
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
