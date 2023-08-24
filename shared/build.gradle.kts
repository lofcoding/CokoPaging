plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("maven-publish")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "com.loc.cokopaging"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}

publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = "com.loc.cokopaging"
            artifactId = "coko-paging"
            version = "1.0"
        }
    }
}