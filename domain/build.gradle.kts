plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    alias(libs.plugins.ksp)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies{

}