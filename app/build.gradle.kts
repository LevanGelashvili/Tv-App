plugins {
    id("com.android.application")
    kotlin("android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp") version ("1.6.10-1.0.2")
}

android {
    applicationVariants.all {
        kotlin.sourceSets {
            getByName(name) {
                kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
        }
    }

    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = ProjectConfig.appId
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.compilerVersion
    }
    packagingOptions {
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
        exclude("**/attach_hotspot_windows.dll")
        exclude("META-INF/licenses/ASM")
    }
}

dependencies {

    implementation(Compose.compiler)
    implementation(Compose.ui)
    implementation(Compose.uiToolingPreview)
    implementation(Compose.hiltNavigationCompose)
    implementation(Compose.material)
    implementation(Compose.runtime)
    implementation(Compose.navigation)
    implementation(Compose.viewModelCompose)
    implementation(Compose.activityCompose)
    implementation(Compose.flowLayout)

    implementation(DaggerHilt.daggerHiltAndroid)
    kapt(DaggerHilt.daggerHiltCompiler)
    implementation(DaggerHilt.androidXHiltViewModel)
    kapt(DaggerHilt.androidXHiltCompiler)
    implementation(DaggerHilt.androidXHiltWorker)

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.work)
    implementation(AndroidX.security)

    implementation(Retrofit.retrofit)
    implementation(Retrofit.moshi)
    implementation(Retrofit.moshiConverter)
    implementation(Retrofit.okHttp)
    implementation(Retrofit.okHttpLoggingInterceptor)

    implementation(Google.material)
    implementation(Google.accompanistMaterial)
    implementation(Google.accompanistAnimation)
    implementation(Google.accompanistSystemUI)

    implementation(ThirdParty.destinations)
    implementation(ThirdParty.destinationsAnimations)
    ksp(ThirdParty.destinationsKsp)

    implementation(Coil.coil)

    testImplementation(Testing.junit4)
    androidTestImplementation(Testing.junitAndroidExt)

    androidTestImplementation(Testing.composeUiTest)
    debugImplementation(Testing.composeUiTooling)
    debugImplementation(Testing.composeUiManifest)
}