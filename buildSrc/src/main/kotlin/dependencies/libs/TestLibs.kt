package dependencies.libs

import dependencies.Dependency
import dependencies.provider.KaptTestDependencyProvider
import dependencies.provider.TestDependencyProvider
import dependencies.values

object TestLibs : TestDependencyProvider, KaptTestDependencyProvider {

    const val VERSION_JUNIT = "4.13.2"
    const val VERSION_ABP_TEST = "1.3.1"
    const val VERSION_MOCKITO = "4.3.1"
    const val VERSION_TEST_CORE = "1.1.1"
    const val VERSION_TEST_COROUTINES = "1.6.0"
    const val VERSION_TEST_DAGGER_HILT = "2.39"

    override fun testDependencies() = listOf(
        Dependency("org.threeten", "threetenbp", VERSION_ABP_TEST),
        Dependency("junit", "junit", VERSION_JUNIT),
        Dependency("android.arch.core", "core-testing", VERSION_TEST_CORE),
        Dependency("org.jetbrains.kotlinx", "kotlinx-coroutines-test", VERSION_TEST_COROUTINES),
        Dependency("org.mockito", "mockito-core", VERSION_MOCKITO),
        Dependency("org.mockito", "mockito-inline", VERSION_MOCKITO),
        Dependency("com.google.dagger", "hilt-android-testing", VERSION_TEST_DAGGER_HILT),
    ).values()

    override fun kaptTestDependencies() = listOf(
        Dependency("com.google.dagger", "hilt-android-compiler", VERSION_TEST_DAGGER_HILT)
    ).values()
}