package com.example.workload;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    public static boolean connection;



    @Override
    public void onReceive(Context context, Intent intent) {
        if(ConnectivityManager.CONNECTIVITY_ACTION.equalsIgnoreCase(intent.getAction())){
            boolean conn=intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY,false);
            Log.d("yewala", String.valueOf(conn));
            this.connection=conn;
            if(conn){
                 //this.connection=false;
                 Toast.makeText(context,"Disconnected",Toast.LENGTH_LONG).show();
            }else{
                 //System.out.println(this.connection);
                 //this.connection=true;
                 Toast.makeText(context,"Connected",Toast.LENGTH_LONG).show();
            }
        }
        if("com.armughanaslam.broadcastsender2".equalsIgnoreCase(intent.getAction())){
            Toast.makeText(context,intent.getStringExtra("msg"),Toast.LENGTH_LONG).show();
        }
    }
}
