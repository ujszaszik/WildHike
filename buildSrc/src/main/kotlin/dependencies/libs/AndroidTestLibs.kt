package dependencies.libs

import dependencies.Dependency
import dependencies.provider.AndroidTestDependencyProvider
import dependencies.provider.KaptAndroidTestDependencyProvider
import dependencies.values

object AndroidTestLibs : AndroidTestDependencyProvider, KaptAndroidTestDependencyProvider {

    const val VERSION_ABP_TEST = "1.3.1"
    const val VERSION_COMPOSE_TEST = "1.1.0"
    const val VERSION_ESPRESSO = "3.4.0"
    const val VERSION_JUNIT_EXT = "1.1.2"
    const val VERSION_MOCKITO = "4.3.1"
    const val VERSION_NAVIGATION_TEST = "2.4.1"
    const val VERSION_TEST_CORE_KTX = "1.4.0"
    const val VERSION_TEST_DAGGER_HILT = "2.39"
    const val VERSION_TEST_RULES = "1.4.0"
    const val VERSION_UI_AUTOMATOR = "2.2.0"

    override fun androidTestDependencies() = listOf(
        Dependency("androidx.navigation", "navigation-testing", VERSION_NAVIGATION_TEST),
        Dependency("androidx.test", "core-ktx", VERSION_TEST_CORE_KTX),
        Dependency("androidx.test", "rules", VERSION_TEST_RULES),
        Dependency("androidx.test.espresso", "espresso-core", VERSION_ESPRESSO),
        Dependency("androidx.test.espresso", "espresso-web", VERSION_ESPRESSO),
        Dependency("androidx.test.ext", "junit", VERSION_JUNIT_EXT),
        Dependency("androidx.test.uiautomator", "uiautomator", VERSION_UI_AUTOMATOR),
        Dependency("com.google.dagger", "hilt-android-testing", VERSION_TEST_DAGGER_HILT),
        Dependency("org.mockito", "mockito-android", VERSION_MOCKITO),
        Dependency("org.threeten", "threetenbp", VERSION_ABP_TEST),
        Dependency("androidx.compose.ui", "ui-test-junit4", VERSION_COMPOSE_TEST),
    ).values()

    override fun kaptAndroidTestDependencies() = listOf(
        Dependency("com.google.dagger", "hilt-android-compiler", VERSION_TEST_DAGGER_HILT)
    ).values()
}