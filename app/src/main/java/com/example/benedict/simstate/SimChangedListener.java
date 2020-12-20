package com.example.benedict.simstate;

import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.util.Log;

public class SimChangedListener extends PhoneStateListener {

    private static String TAG = SimChangedListener.class.getSimpleName();
    public MainViewModel mainViewModel;

    public SimChangedListener(MainViewModel mainViewModel) {
        Log.d(TAG,"constructor(" + mainViewModel + ")");
        this.mainViewModel = mainViewModel;
    }

    @Override
    public void onServiceStateChanged(ServiceState serviceState) {
        super.onServiceStateChanged(serviceState);
        Log.d(TAG,"onServiceStateChanged(" + serviceState + ")");
        mainViewModel.checkSimState();
    }

    @Override
    public void onSignalStrengthsChanged(SignalStrength signalStrength) {
        super.onSignalStrengthsChanged(signalStrength);
        Log.d(TAG,"onSignalStrengthsChanged(" + signalStrength + ")");
        mainViewModel.checkSimState();
    }
}