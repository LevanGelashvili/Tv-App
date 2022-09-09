object Compose {
    const val version = "1.1.1"
    const val compilerVersion = "1.1.1"

    const val material = "androidx.compose.material:material:$version"
    const val ui = "androidx.compose.ui:ui:$version"
    const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"
    const val runtime = "androidx.compose.runtime:runtime:$version"
    const val compiler = "androidx.compose.compiler:compiler:$compilerVersion"

    private const val navigationVersion = "2.4.0-beta02"
    const val navigation = "androidx.navigation:navigation-compose:$navigationVersion"

    private const val hiltNavigationComposeVersion = "1.0.0-beta01"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:$hiltNavigationComposeVersion"

    private const val activityComposeVersion = "1.4.0"
    const val activityCompose = "androidx.activity:activity-compose:$activityComposeVersion"

    private const val lifecycleVersion = "2.4.0"
    const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion"

    private const val flowLayoutVersion = "0.26.2-beta"
    const val flowLayout = "com.google.accompanist:accompanist-flowlayout:$flowLayoutVersion"
}