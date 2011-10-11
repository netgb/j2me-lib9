-injars F:\work\j2me-lib9\j2me\_process\_lib9.jar
-outjar F:\work\j2me-lib9\j2me\_process\lib9.jar
-libraryjars F:\work\j2me-lib9\j2me\tools\lib\cldcapi11.jar
-libraryjars F:\work\j2me-lib9\j2me\tools\lib\midpapi20.jar
-libraryjars F:\work\j2me-lib9\j2me\tools\lib\wma20.jar
-libraryjars F:\work\j2me-lib9\j2me\tools\lib\jsr75.jar
-libraryjars F:\work\j2me-lib9\j2me\tools\lib\mmapi.jar
-printmapping Obfuscate_mapping.log
-printusage Obfuscate_usage.log
-keep public class * {
    public protected *;
}
-keepclassmembernames class * {
    java.lang.Class class$(java.lang.String);
    java.lang.Class class$(java.lang.String, boolean);
}