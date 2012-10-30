/* mprodigy.i */
%module Mprodigy
%{
#include "ruby/ext/rho/rhoruby.h"

extern VALUE mprodigy_native_sessionBegin(const char* applicationId, const char* version, const char* instance, const char* other);
#define native_sessionBegin mprodigy_native_sessionBegin 

extern void mprodigy_native_sessionEnd(const char* sessionId);
#define native_sessionEnd mprodigy_native_sessionEnd 

extern VALUE mprodigy_native_userLogin(const char* sessionId, const char* username);
#define native_userLogin mprodigy_native_userLogin 

extern void mprodigy_native_userLogout(const char* userId);
#define native_userLogout mprodigy_native_userLogout 

%}

extern VALUE native_sessionBegin(const char* applicationId, const char* version, const char* instance, const char* other);
extern void native_sessionEnd(const char* sessionId);
extern VALUE native_userLogin(const char* sessionId, const char* username);
extern void native_userLogout(const char* userId);
