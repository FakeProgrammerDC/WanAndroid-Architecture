// Top-level build file where you can add configuration options common to all sub-projects/modules.


buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven(url = "https://jitpack.io")
    }

    dependencies {
        classpath(Plugins.gradlePlugin)
        classpath(Plugins.kotlinSerializationPlugin)
        classpath(Plugins.hiltPlugin)
        classpath(kotlin("gradle-plugin", Versions.kotlin))
    }
}

task("clean", Delete::class) {
    delete = setOf(rootProject.buildDir)
}