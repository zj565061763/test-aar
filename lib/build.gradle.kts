plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    `maven-publish`
}

val libGroupId = "com.sd.lib.android"
val libArtifactId = "template"
val libVersion = "1.0.0-alpha01"

android {
    namespace = "com.sd.lib.template"
    compileSdk = libs.versions.androidCompileSdk.get().toInt()
    defaultConfig {
        minSdk = 21
    }

    kotlinOptions {
        freeCompilerArgs += "-module-name=$libGroupId.$libArtifactId"
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

kotlin {
    jvmToolchain(8)
}

dependencies {
    api(fileTree("dir" to "libs", "include" to listOf("*.aar")))
}

publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = libGroupId
            artifactId = libArtifactId
            version = libVersion

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}