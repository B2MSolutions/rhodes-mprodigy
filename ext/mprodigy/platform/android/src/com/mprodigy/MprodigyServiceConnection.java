package com.mprodigy;

import android.os.*;
import android.content.*;

import com.rhomobile.rhodes.*;

public class MprodigyServiceConnection implements ServiceConnection {
    
    private boolean bound;

    private Messenger service;

    private Bundle[] initialMessages;

    private MprodigyServiceConnection(Bundle[] initialMessages) {
        Logger.D("MprodigyServiceConnection", "construction");
        this.service = null;
        this.bound = false;
        this.initialMessages = initialMessages;
        this.bind();
    }

    public static MprodigyServiceConnection initialSend(Bundle[] initialMessages) {
        Logger.D("MprodigyServiceConnection", "initialSend");
        return new MprodigyServiceConnection(initialMessages);
    } 

    public void close() {
        Logger.D("MprodigyServiceConnection", "close");
        this.unbind();
    }

    public void onServiceConnected(ComponentName className, IBinder binder) {
        Logger.D("MprodigyServiceConnection", "onServiceConnected");
        this.service = new Messenger(binder);
        this.bound = true;
        
        for(Bundle b: initialMessages) {
            this.send(b);    
        }
        
        this.initialMessages = new Bundle[0];
    }

    public void onServiceDisconnected(ComponentName className) {
        Logger.D("MprodigyServiceConnection", "onServiceDisconnected");
        this.service = null;
        this.bound = false;
    }

    public void send(Bundle bundle) {
        Logger.D("MprodigyServiceConnection", "send");

        if(!this.bound) {
            Logger.D("MprodigyServiceConnection", "send - not bound");
            return;
        }

        if(bundle == null) {
            Logger.D("MprodigyServiceConnection", "send - null bundle");
            return;
        }

        Message msg = Message.obtain();
        msg.setData(bundle);
        
        try {
            this.service.send(msg);
        } catch (RemoteException e) {
            Logger.E("MprodigyServiceConnection:send", e.toString());
        }
    }    

    private void bind() {
        Logger.D("MprodigyServiceConnection", "bind");
        Context ctx = RhodesService.getContext();
        Intent intent = new Intent("com.b2msolutions.mprodigy.android.services.ApiService");

        ctx.bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    private void unbind() {
        Logger.D("MprodigyServiceConnection", "unbind");
        Context ctx = RhodesService.getContext();
        ctx.unbindService(this);
    }
}
