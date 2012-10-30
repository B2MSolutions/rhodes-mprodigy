#include <rhodes.h>
#include "rubyext/WebView.h"
#include <stdlib.h>
#include "ruby/ext/rho/rhoruby.h"

extern "C" VALUE mprodigy_native_sessionBegin(const char* applicationId, const char* version, const char* instance, const char* other) {

    JNIEnv *env = jnienv();

    jclass cls = rho_find_class(env, "com/mprodigy/Mprodigy");
    if (!cls) {
        return rho_ruby_get_NIL();
    }

    jmethodID mid = env->GetStaticMethodID(cls, "sessionBegin", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;");
    if (!mid) {
        return rho_ruby_get_NIL();
    }

    jstring objApplicationId = env->NewStringUTF(applicationId);
    jstring objVersion = env->NewStringUTF(version);
    jstring objInstance = env->NewStringUTF(instance);
    jstring objOther = env->NewStringUTF(other);
    jstring objSession = (jstring)env->CallStaticObjectMethod(cls, mid, objApplicationId, objVersion, objInstance, objOther);

    env->DeleteLocalRef(objApplicationId);
    env->DeleteLocalRef(objVersion);
    env->DeleteLocalRef(objInstance);
    env->DeleteLocalRef(objOther);

    const char* buf = env->GetStringUTFChars(objSession,0);
    VALUE result = rho_ruby_create_string(buf);
    env->ReleaseStringUTFChars(objSession, buf);

    return result;
}

extern "C" void mprodigy_native_sessionEnd(const char* sessionId) {

    JNIEnv *env = jnienv();

    jclass cls = rho_find_class(env, "com/mprodigy/Mprodigy");
    if (!cls) {
        return;
    }

    jmethodID mid = env->GetStaticMethodID(cls, "sessionEnd", "(Ljava/lang/String;)V");
    if (!mid) {
        return;
    }

    jstring objSessionId = env->NewStringUTF(sessionId);
    env->CallStaticVoidMethod(cls, mid, objSessionId);

    env->DeleteLocalRef(objSessionId);
    return;
}

extern "C" VALUE mprodigy_native_userLogin(const char* sessionId, const char* username) {

    JNIEnv *env = jnienv();

    jclass cls = rho_find_class(env, "com/mprodigy/Mprodigy");
    if (!cls) {
        return rho_ruby_get_NIL();
    }

    jmethodID mid = env->GetStaticMethodID(cls, "userLogin", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;");
    if (!mid) {
        return rho_ruby_get_NIL();
    }

    jstring objSessionId = env->NewStringUTF(sessionId);
    jstring objUsername = env->NewStringUTF(username);
    jstring objUserId = (jstring)env->CallStaticObjectMethod(cls, mid, objSessionId, objUsername);

    env->DeleteLocalRef(objSessionId);
    env->DeleteLocalRef(objUsername);

    const char* buf = env->GetStringUTFChars(objUserId,0);
    VALUE result = rho_ruby_create_string(buf);
    env->ReleaseStringUTFChars(objUserId, buf);
    
    return result;
}

extern "C" void mprodigy_native_userLogout(const char* userId) {

    JNIEnv *env = jnienv();

    jclass cls = rho_find_class(env, "com/mprodigy/Mprodigy");
    if (!cls) {
        return;
    }

    jmethodID mid = env->GetStaticMethodID(cls, "userLogout", "(Ljava/lang/String;)V");
    if (!mid) {
        return;
    }

    jstring objUserId = env->NewStringUTF(userId);
    env->CallStaticVoidMethod(cls, mid, objUserId);

    env->DeleteLocalRef(objUserId);
    return;
}
