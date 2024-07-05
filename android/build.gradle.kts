plugins {
    id("org.jetbrains.compose")
    alias(libs.plugins.jetbrains.compose.compiler)
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 34
    namespace= "com.webapp.kmp"
    buildFeatures {
        buildConfig=true
        compose =true
    }
    defaultConfig {
        applicationId = "com.webapp.kmp"  //TODO here change applicationId
        minSdk = 26 //upgrade for "io.github.skolson KmpIO"
        targetSdk = 34
        versionCode = 1
        versionName = "1.0-SNAPSHOT"
        buildConfigField("String", "HostName", "\"https://vaibhav3011.medium.com/effortless-multimodule-configuration-for-kotlin-multiplatform-projects-with-gradle-convention-8e6593dff1d9\"" )//TODO here change  站點域名
        buildConfigField("String", "stage", "\"1234\"" )//TODO here change
        buildConfigField("String", "ADJUST_TOKEN", "\"dsgfdfgdfgfdg0\"")//TODO here change
        manifestPlaceholders["app_name_suffix"] =  "whatever" //TODO here change app name
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation( libs.koin.core)
    implementation(libs.koin.android)
    implementation(project(":common"))
}

tasks.register("BuildAndRun") {
    doFirst {
        exec {
            workingDir(projectDir.parentFile)
            commandLine("./gradlew", "android:build")
            commandLine("./gradlew", "android:installDebug")
        }
    }
}