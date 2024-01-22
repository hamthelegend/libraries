// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.library") version "8.2.1" apply false

    val kotlinVersion = "1.9.20"
    kotlin("android") version kotlinVersion apply false
}