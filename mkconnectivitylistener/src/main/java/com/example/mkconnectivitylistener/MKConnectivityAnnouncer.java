package com.example.mkconnectivitylistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import java.util.ArrayList;

/**
 * Created by michaelkibenko on 18/02/2018.
 */

public class MKConnectivityAnnouncer {
    public static MKConnectivityAnnouncer instance;

    private boolean status;

    private Context context;

    private ArrayList<MKConnectivityListener> clients;

    public static MKConnectivityAnnouncer getInstance(Context context) {
        if(instance == null){
            instance = new MKConnectivityAnnouncer(context);
        }
        return instance;
    }

    private MKConnectivityAnnouncer(Context context){
        clients = new ArrayList<>();
        this.context = context;
        context.registerReceiver(new NetworkChangeReceiver(), new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public boolean getStatus(){
        return this.status;
    }

    public void register(MKConnectivityListener client){
        if(clients != null) {
            clients.add(client);
        }
    }

    public void unRegister(MKConnectivityListener client){
        if(clients != null){
            if(clients.contains(client)){
                clients.remove(client);
            }
        }
    }

    private void onConnectivityChange(boolean status){
        for (MKConnectivityListener client : clients) {
            client.onConnectivityChanged(status);
        }
    }

    class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, final Intent intent) {
            final ConnectivityManager connMgr = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            final android.net.NetworkInfo wifi = connMgr
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            final android.net.NetworkInfo mobile = connMgr
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if (wifi.isAvailable() || mobile.isAvailable()) {
                status = true;
            }else{
                status = false;
            }

            onConnectivityChange(status);
        }
    }

}
