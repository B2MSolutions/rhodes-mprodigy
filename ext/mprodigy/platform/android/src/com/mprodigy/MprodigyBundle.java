package com.mprodigy;

import android.os.*;
import android.content.*;

public class MprodigyBundle {
	public static Bundle getSessionBegin(String sessionId, String applicationId, String version, String instance, String other) {
        Bundle bundle = new Bundle();
        bundle.putString(MprodigyConstants.APIField.Operation, MprodigyConstants.Operation.SessionBegin);
        bundle.putString(MprodigyConstants.APIField.SessionId, sessionId);
        bundle.putString(MprodigyConstants.APIField.ApplicationId, applicationId);
        bundle.putString(MprodigyConstants.APIField.Version, version);
        bundle.putString(MprodigyConstants.APIField.Instance, instance);
        bundle.putString(MprodigyConstants.APIField.Other, other);
        return bundle;
    }

    public static Bundle getSessionEnd(String sessionId) {
        Bundle bundle = new Bundle();
        bundle.putString(MprodigyConstants.APIField.Operation, MprodigyConstants.Operation.SessionEnd);
        bundle.putString(MprodigyConstants.APIField.SessionId, sessionId);
        return bundle;
    }

    public static Bundle getUserLogin(String sessionId, String userId, String username) {
        Bundle bundle = new Bundle();
        bundle.putString(MprodigyConstants.APIField.Operation, MprodigyConstants.Operation.UserLogin);
        bundle.putString(MprodigyConstants.APIField.UserId, userId);
        bundle.putString(MprodigyConstants.APIField.SessionId, sessionId);
        bundle.putString(MprodigyConstants.APIField.Username, username);

        return bundle;
    }

    public static Bundle getUserLogout(String userId) {
        Bundle bundle = new Bundle();
        bundle.putString(MprodigyConstants.APIField.Operation, MprodigyConstants.Operation.UserLogout);
        bundle.putString(MprodigyConstants.APIField.UserId, userId);
        return bundle;
    }
}