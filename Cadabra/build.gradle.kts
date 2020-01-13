import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTargetPreset
import org.jetbrains.kotlin.gradle.tasks.FatFrameworkTask

plugins {
    kotlin("multiplatform") version "1.3.61"
}

repositories {
    mavenCentral()
}

buildscript {
    dependencies {
        classpath(kotlin("gradle-plugin", version = "1.3.61"))
    }
}

group = "org.nedron.kotlin.multiplatform"
version = "1.0-SNAPSHOT"

val frameworkName = "KTMulti"

kotlin {
    /* Targets configuration omitted.
    *  To find out how to configure the targets, please follow the link:
    *  https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#setting-up-targets */

    targets {
        androidNativeArm32()
        androidNativeArm64()
        iosArm64()
        iosX64()

        println(targets.names)

        presets.withType<KotlinNativeTargetPreset>().forEach {
            targetFromPreset(it) {
                /* Configure each of the created targets */
            }
        }

        iosArm64 {
            binaries.framework {
                baseName = frameworkName
            }
        }

        iosX64 {
            binaries.framework {
                baseName = frameworkName
            }
        }

    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
    }

    tasks.register("releaseFatFramework", FatFrameworkTask::class) {
        baseName = frameworkName
        from(
            kotlin.iosArm64().binaries.getFramework("RELEASE"),
            kotlin.iosX64().binaries.getFramework("RELEASE")
        )
    }
}
