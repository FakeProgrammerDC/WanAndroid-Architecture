// Top-level build file where you can add configuration options common to all sub-projects/modules.


buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Plugins.gradlePlugin)
        classpath(Plugins.kotlinGradlePlugin)
        classpath(Plugins.kotlinSerializationPlugin)
        classpath(Plugins.hiltPlugin)
    }
}

task("clean", Delete::class) {
    delete = setOf(rootProject.buildDir)
}