#include <jni.h>
#include <string>
#include "art.h"
#include "common.h"

static int apilevel;
static bool isart;

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_liuzhi20181026_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jboolean JNICALL
Java_app20181205_luis_com_hotfix_AndFix_setup__ZI(JNIEnv *env, jclass type, jboolean isArt,
                                                  jint apiLevel) {

    // TODO
    isart = isArt;
    apilevel = apiLevel;
    LOGD("vm is: %s , apilevel is: %i", (isart ? "art" : "dalvik"),
         (int )apilevel);
}

extern "C"
JNIEXPORT void JNICALL
Java_app20181205_luis_com_hotfix_AndFix_replaceMethod(JNIEnv *env, jclass type, jobject src,
                                                      jobject dest) {

    // TODO
    replace_7_0(env, src, dest);

}

extern "C"
JNIEXPORT void JNICALL
Java_app20181205_luis_com_hotfix_AndFix_setFieldFlag(JNIEnv *env, jclass type, jobject field) {

    // TODO
    setFieldFlag_7_0(env, field);

}

extern "C"
JNIEXPORT jstring JNICALL
Java_app20181205_luis_com_hotfix_AndFix_stringFromJNI(JNIEnv *env, jclass type) {

    // TODO
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}