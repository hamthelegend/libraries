import java.io.FileInputStream
import java.util.Properties

val secrets = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "secrets.properties")))
}
val USERNAME = secrets.getProperty("USERNAME")
val TOKEN = secrets.getProperty("TOKEN")

plugins {
    `maven-publish`
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.thebrownfoxx.components"
    compileSdk = 34

    defaultConfig {
        minSdk = 27

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
}

publishing {
    repositories {
        maven {
            name = "TheBrownFoxxComponents"
            url = uri("https://maven.pkg.github.com/hamthelegend/thebrownfoxx-components")
            credentials {
                username = USERNAME
                password = TOKEN
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            groupId = "com.thebrownfoxx"
            artifactId = "components"
            version = "1.0.0"
            artifact("$buildDir/outputs/aar/components-release.aar")
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Compose
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}