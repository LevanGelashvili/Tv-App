object Testing {
    private const val junitVersion = "4.13.2"
    const val junit4 = "junit:junit:$junitVersion"

    private const val junitAndroidExtVersion = "1.1.3"
    const val junitAndroidExt = "androidx.test.ext:junit:$junitAndroidExtVersion"

    private const val coroutinesTestVersion = "1.5.1"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesTestVersion"

    const val composeUiTest = "androidx.compose.ui:ui-test-junit4:${Compose.version}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Compose.version}"
    const val composeUiManifest = "androidx.compose.ui:ui-test-manifest:${Compose.version}"
}