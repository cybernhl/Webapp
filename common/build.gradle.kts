import com.codingfeline.buildkonfig.compiler.FieldSpec
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
plugins {
//    alias(libs.plugins.android.application)
    id("com.android.library")
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.jetbrains.compose.compiler)
    alias(libs.plugins.jetbrainsCompose)
    id("com.codingfeline.buildkonfig") version "0.15.1" 
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

group = "com.webapp"
version = "1.0-SNAPSHOT"
val podName = "cmmon"

configurations.all {
    resolutionStrategy.cacheChangingModulesFor(0, "seconds")
}

buildkonfig {
    packageName = project.group.toString()
    println("Show me  packageName $packageName ")
    objectName="BuildConfig"//default is "BuildKonfig"
    exposeObjectWithName="BuildConfig"

    defaultConfigs {
        buildConfigField(FieldSpec.Type.STRING, "hostName", "https://github.com/yshrsmz/BuildKonfig")//站點域名
        buildConfigField(FieldSpec.Type.STRING, "stage", "1234") //站點編號
        buildConfigField(FieldSpec.Type.STRING, "INIT_HOST", "\"https://dsgfgf.com/yshrsmz/BuildKonfig\"" )
    }

//    targetConfigs {
//        create("android") {
//            buildConfigField(FieldSpec.Type.STRING, "target", "me_android")
//        }
//
//        create("ios") {
//            buildConfigField(FieldSpec.Type.STRING, "target", "ios")
//        }
//    }
}


@OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
kotlin {
    applyDefaultHierarchyTemplate()
    androidTarget {
        compilations.all {
            kotlinOptions.jvmTarget = "17"
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "common"
            isStatic = true
        }
        iosTarget.compilations.getByName("main") {
            val fileName = when (iosTarget.targetName) {
                "iosArm64" -> "ios-arm64"
                "iosX64", "iosSimulatorArm64" -> "ios-arm64_x86_64-simulator"
                else -> error("Unknown target ${iosTarget.targetName}")
            }
            //frpclib
            val xcPath = file("${rootDir}/frpc_library/frameworks/Frpclib.xcframework/$fileName/").absolutePath
            println("${iosTarget.targetName} frpc xcPath: $xcPath")

            cinterops.create("Frpclib") {
                defFile("${rootDir}/frpc_library/src/iosMain/cinterop/frpc.def")
                compilerOpts("-framework", "Frpclib", "-F$xcPath/") //, "-rpath", frameworksPath
                extraOpts("-compiler-option", "-fmodules")
            }

            iosTarget.binaries.all {
                linkerOpts("-framework", "Frpclib", "-F$xcPath/")
            }
        }
    }
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    applyDefaultHierarchyTemplate {
        common {
            group("jvmCommon") {
                withAndroidTarget()
                withJvm()
            }
        }
    }

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
    }

    sourceSets {
        all {
            languageSettings {
                optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
                optIn("kotlin.RequiresOptIn")
                optIn("kotlinx.cinterop.ExperimentalForeignApi")
                optIn("kotlin.experimental.ExperimentalNativeApi")
                optIn("org.jetbrains.compose.resources.InternalResourceApi")
                optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
            }
        }
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.ui)
                api(compose.foundation)
                api(compose.materialIconsExtended)
                api(compose.material3)
                api(compose.components.resources)

                api(libs.precompose)
                api(libs.precompose.viewmodel)
                api(libs.precompose.koin)

                api(libs.ktor.core)
                api(libs.koin.core)
                api(libs.koin.jb.compose)

                api(libs.compose.webview.multiplatform)
                ///region 讀寫檔案
                api("io.github.skolson:kmp-io:0.1.5")
                ///endregion
                api(libs.stately)

                api(libs.kotlinx.coroutines)
                api(libs.kotlinx.serialization.json)

                api(libs.log)
            }
        }

//        val commonTest by getting {
//            dependencies {
//                implementation(kotlin("test"))
//            }
//        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                api(libs.androidx.appcompat)
                api(libs.androidx.activity.compose)
                api(libs.androidx.core.ktx)
                api(libs.ktor.jvm)
                api(libs.adjust)
                api(libs.google.services.ads)
                api(libs.androidx.runtime.android)
                api(libs.timber)
            }
        }

        iosMain{
            dependsOn(commonMain)
            dependencies {
                api(libs.ktor.ios)
            }
        }
    }

    explicitApi()
}

android {
    compileSdk = 34
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    namespace= "com.webapp.common"
    defaultConfig {
        minSdk = 26
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}