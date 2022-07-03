# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.10.2)

# Declares and names the project.

project("me")

# 需要引入我们头文件,以这个配置的目录为基准
include_directories(../../../libs/include)

# 添加共享库搜索路径 (已经不推荐使用了)
#link_directories(../../../libs/armeabi-v7a/)

# 指定源文件目录
#AUX_SOURCE_DIRECTORY(. SRC_LIST)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

#add_library( # Sets the name of the library.
#        bright_player
#        # Sets the library as a shared library.
#        SHARED
#        # Provides a relative path to your source file(s).
#        ${SRC_LIST})

add_library(avcodec SHARED IMPORTED)
set_target_properties(avcodec PROPERTIES IMPORTED_LOCATION ../../../libs/armeabi-v7a/libavcodec.so)

add_library(avfilter SHARED IMPORTED)
set_target_properties(avfilter PROPERTIES IMPORTED_LOCATION ../../../libs/armeabi-v7a/libavfilter.so)
add_library(avformat SHARED IMPORTED)
set_target_properties(avformat PROPERTIES IMPORTED_LOCATION ../../../libs/armeabi-v7a/libavformat.so)
add_library(avutil SHARED IMPORTED)
set_target_properties(avutil PROPERTIES IMPORTED_LOCATION ../../../libs/armeabi-v7a/libavutil.so)
add_library(swresample SHARED IMPORTED)
set_target_properties(swresample PROPERTIES IMPORTED_LOCATION ../../../libs/armeabi-v7a/libswresample.so)
add_library(swscale SHARED IMPORTED)
set_target_properties(swscale PROPERTIES IMPORTED_LOCATION ../../../libs/armeabi-v7a/libswscale.so)

add_library( # Sets the name of the library.
        me

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        native-lib.cpp)

target_include_directories(me PRIVATE ../../../libs/include)


# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
        # 链接额外的 ffmpeg 的编译
#        bright_player
        # 编解码(最重要的库)
#        ${avcodec}
        avcodec
        # 滤镜特效处理库
#        ${avfilter}
        avfilter
        # 封装格式处理库
#        ${avformat}
        avformat
        # 工具库(大部分库都需要这个库的支持)
#        ${avutil}
        avutil
        # 音频采样数据格式转换库
#        ${swresample}
        swresample
        # 视频像素数据格式转换
#        ${swscale}
        swscale
#         链接 android ndk 自带的一些库
#        ${android}
#        android

        me
        # Links the target library to the log library
        # included in the NDK.
#        ${log-lib}
        log-lib
        )
