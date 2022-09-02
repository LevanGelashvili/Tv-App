object DaggerHilt {
    const val hiltVersion = "2.40"
    const val daggerHiltAndroid = "com.google.dagger:hilt-android:$hiltVersion"
    const val daggerHiltCompiler = "com.google.dagger:hilt-compiler:$hiltVersion"

    private const val hiltViewModelVersion = "1.0.0-alpha03"
    const val androidXHiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:$hiltViewModelVersion"

    private const val hiltCompilerVersion = "1.0.0"
    const val androidXHiltCompiler = "androidx.hilt:hilt-compiler:$hiltCompilerVersion"

    private const val hiltWorkerVersion = "1.0.0"
    const val androidXHiltWorker = "androidx.hilt:hilt-work:$hiltWorkerVersion"
}