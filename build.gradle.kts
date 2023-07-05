import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java

    // Matches the version in jackson-module-kotlin to avoid warnings
    // around unmatching versions of Kotlin reflection
    kotlin("jvm") version "1.6.0"

    // Determines which dependencies have updates
    id("com.github.ben-manes.versions") version "0.38.0"
}

allprojects {
    group = "websocket.poc"
    version = "1.0.0"

    repositories {
        mavenCentral()
    }
}

val projectJVMVersion = "13"
val mockkVersion = "1.4.1"
val kotlinVersion = "1.6.0"
val jacksonModuleKotlinVersion = "2.12.0-rc2"

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    dependencies {
        implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonModuleKotlinVersion")

        // Kotlin
        implementation(kotlin("stdlib"))
        implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion") // Required for Kotlin integration
        implementation("org.jetbrains.kotlin:kotlin-allopen:$kotlinVersion")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")

        // Retrofit
        implementation("com.squareup.retrofit2:retrofit:2.9.0")
        implementation("com.squareup.retrofit2:converter-jackson:2.9.0")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.3")

        // Client
        api("com.squareup.okhttp3:okhttp:3.14.9")

        // Request signing
        implementation("commons-codec:commons-codec:1.15")

        // Testing
        testImplementation("junit:junit:4.13.2")
        testImplementation ("io.mockk:mockk:$mockkVersion")
    }

    configurations.register("customConfiguration") {

    }

    /*
    Set project JVM Version
 */
    val projectJVMVersion = "13"
    val compileKotlin: KotlinCompile by tasks
    compileKotlin.kotlinOptions {
        jvmTarget = projectJVMVersion
    }
    val compileTestKotlin: KotlinCompile by tasks
    compileTestKotlin.kotlinOptions {
        jvmTarget = projectJVMVersion
    }

    tasks.withType<JavaCompile> {
        options.compilerArgs.addAll(listOf("-source", projectJVMVersion, "-target", projectJVMVersion))
    }
}
