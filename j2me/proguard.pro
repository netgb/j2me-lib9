-injars  F:\work\9Lib\_process\_lib9.jar
-outjar F:\work\9Lib\_process\lib9.jar
-libraryjars F:\work\9Lib\tools\lib\cldcapi11.jar
-libraryjars F:\work\9Lib\tools\lib\midpapi20.jar
-libraryjars F:\work\9Lib\tools\lib\wma20.jar
-libraryjars F:\work\9Lib\tools\lib\jsr75.jar
-libraryjars F:\work\9Lib\tools\lib\mmapi.jar
-printmapping Obfuscate_mapping.log
-printusage Obfuscate_usage.log
-keep public class * {
    public protected *;
}
-keepclassmembernames class * {
    java.lang.Class class$(java.lang.String);
    java.lang.Class class$(java.lang.String, boolean);
}