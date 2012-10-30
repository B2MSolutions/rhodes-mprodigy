package com.mprodigy;

import android.os.*;
import android.content.*;

import com.rhomobile.rhodes.*;
import com.mprodigy.*;

public class Mprodigy {

    static MprodigyServiceConnection connection = null;

   	public static String sessionBegin(String applicationId, String version, String instance, String other) {
        Logger.D("Mprodigy", "sessionBegin");

        String sessionId = java.util.UUID.randomUUID().toString();

        // Create and send a message to the service, using a supported 'what' value
        Bundle bundle = new Bundle();
        bundle.putString("Operation", "SessionBegin");
        bundle.putString("SessionId", sessionId);
        bundle.putString("ApplicationId", applicationId);
        bundle.putString("Version", version);
        bundle.putString("Instance", instance);
        bundle.putString("Other", other);

        connection = MprodigyServiceConnection.initialSend(bundle);

        return sessionId;
	}

	public static void sessionEnd(String sessionId) {
        Logger.D("Mprodigy", String.format("sessionEnd: %s", sessionId));

        // Create and send a message to the service, using a supported 'what' value
        Bundle bundle = new Bundle();
        bundle.putString("Operation", "SessionEnd");
        bundle.putString("SessionId", sessionId);

        connection.send(bundle);
        connection.close();

        return;
	}

	public static String userLogin(String sessionId, String username) {
        Logger.D("Mprodigy", String.format("userLogin: %s", sessionId));

        String userId = java.util.UUID.randomUUID().toString();

        // Create and send a message to the service, using a supported 'what' value
        Bundle bundle = new Bundle();
        bundle.putString("Operation", "UserLogin");
        bundle.putString("UserId", userId);
        bundle.putString("SessionId", sessionId);
        bundle.putString("Username", username);

        connection.send(bundle);

        return userId;
	}

	public static void userLogout(String userId) {
        Logger.D("Mprodigy", String.format("userLogout: %s", userId));

        // Create and send a message to the service, using a supported 'what' value
        Bundle bundle = new Bundle();
        bundle.putString("Operation", "UserLogout");
        bundle.putString("UserId", userId);

        connection.send(bundle);

        return;
	}
}
