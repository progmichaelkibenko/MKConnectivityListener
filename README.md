# MKConnectivityListener
Connectivity listener package for android

You have to add this => <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
permission to your project manifest.

To use the announcer component have to register to the MKConnectivityAnnouncer with the MKConnectivityListener

Example: 

MKConnectivityAnnouncer.getInstance(this).register(new MKConnectivityListener() {
            @Override
            public void onConnectivityChanged(boolean is) {
                Toast.makeText(MainActivity.this, is+"", Toast.LENGTH_LONG).show();
            }
});


And when your component stops unregister from the MKConnectivityAnnouncer.



