
if "%RHO_PLATFORM%" == "android" (

cd mprodigy\platform\android
rake --trace

)

if "%RHO_PLATFORM%" == "iphone" (

cd mprodigy\platform\phone
rake --trace

)

if "%RHO_PLATFORM%" == "wm" (

cd mprodigy\platform\wm
rake --trace

)

if "%RHO_PLATFORM%" == "win32" (

cd mprodigy\platform\wm
rake --trace

)

if "%RHO_PLATFORM%" == "bb" (

cd mprodigy\platform\bb
rake --trace

)

