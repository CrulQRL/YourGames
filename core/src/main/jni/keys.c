#include <jni.h>
JNIEXPORT jstring JNICALL
Java_com_faqrulans_core_utils_KeysManager_getPassphrase(JNIEnv *env, jobject instance) {
 return (*env)->  NewStringUTF(env, "faqrulansdb");
}

JNIEXPORT jstring JNICALL
Java_com_faqrulans_core_utils_KeysManager_getApiKey(JNIEnv *env, jobject instance) {
 return (*env)->NewStringUTF(env, "64e70919141248c98f903792d44b621b");
}
