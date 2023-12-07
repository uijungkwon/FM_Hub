// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.4" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    // 프로젝트 수준에 파이어베이스 라이브러리 추가
    id("com.google.gms.google-services") version "4.3.14" apply false
}



buildscript{

    dependencies {
        classpath("com.android.tools.build:gradle:8.1.4")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
        classpath ("com.google.gms:google-services:4.3.10")
    }
}


