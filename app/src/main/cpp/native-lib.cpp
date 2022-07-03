#include <jni.h>
#include <string>

extern "C" {
#include <libavcodec/avcodec.h>
#include <libavutil/avutil.h>
}

extern "C" JNIEXPORT jstring
Java_bright_me_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT jstring
Java_bright_me_MainActivity_ffmpegConfigureInfo(JNIEnv *env, jobject thiz) {
    std::string info = "FfmpegConfigureInfo";
    const char *content = avcodec_configuration();
    return env->NewStringUTF(content);
//    return env->NewStringUTF(info.c_str());
}