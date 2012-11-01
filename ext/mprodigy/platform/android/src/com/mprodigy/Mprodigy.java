package com.mprodigy;

import android.os.*;
import android.content.*;

import com.rhomobile.rhodes.*;
import com.mprodigy.*;

public class Mprodigy {

    static MprodigyServiceConnection connection = null;

   	public static String sessionBegin(String applicationId, String version, String instance, String other, String username) {
        Logger.D("Mprodigy", "sessionBegin");

        String sessionId = java.util.UUID.randomUUID().toString();
        
        Bundle[] initialMessages = new Bundle[1];
        if(username.length() > 0) {
            initialMessages = new Bundle[2];
        }

        initialMessages[0] = MprodigyBundle.getSessionBegin(sessionId, applicationId, version, instance, other);
        if(username.length() > 0) {
            initialMessages[1] = MprodigyBundle.getUserLogin(sessionId, sessionId, username);
        }

        connection = MprodigyServiceConnection.initialSend(initialMessages);

        return sessionId;
	}

	public static void sessionEnd(String sessionId) {
        Logger.D("Mprodigy", String.format("sessionEnd: %s", sessionId));

        Bundle bundle = MprodigyBundle.getSessionEnd(sessionId);

        connection.send(bundle);
        connection.close();
	}

	public static String userLogin(String sessionId, String username) {
        Logger.D("Mprodigy", String.format("userLogin: %s", sessionId));

        String userId = java.util.UUID.randomUUID().toString();

        Bundle bundle = MprodigyBundle.getUserLogin(sessionId, userId, username);
        connection.send(bundle);

        return userId;
	}

	public static void userLogout(String userId) {
        Logger.D("Mprodigy", String.format("userLogout: %s", userId));

        connection.send(MprodigyBundle.getUserLogout(userId));
	}    
}