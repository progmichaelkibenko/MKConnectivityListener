package com.example.mkconnectivitylistener;

/**
 * Created by michaelkibenko on 18/02/2018.
 */

/**
 * This is the connectivity listener you have to register to the announcer with this listener
 *
 * @author  Michael kibenko
 * @version 1.0
 * @since   18/02/2018
 */
public interface MKConnectivityListener {
    void onConnectivityChanged(boolean is);
}
