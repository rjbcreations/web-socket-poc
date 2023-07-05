plugins {
    `java-library`
    `maven-publish`
    signing
    id("org.jetbrains.dokka") version "1.4.20"
}

tasks.withType<ProcessResources> {
    filesMatching("version.properties") {
        expand("projectVersion" to project.version)
    }
}

val mockkVersion = "1.4.1"
val kotlinLoggingVersion = "1.7.9"
val junitPlatformVersion = "1.0.2"
val junitJupiterVersion = "5.8.2" // https://mvnrepository.com/search?q=org.junit.jupiter%3Ajunit-jupiter%20
val assertJVersion = "3.24.2" // https://mvnrepository.com/artifact/org.assertj/assertj-core/
val jacksonModuleKotlin = "2.12.3" // https://mvnrepository.com/artifact/com.fasterxml.jackson.module/jackson-module-kotlin
val retrofitJackson = "2.9.0" // https://mvnrepository.com/artifact/com.squareup.retrofit2/converter-jackson
val okhttp3 = "4.11.0" // https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp
val awaitilityVersion = "3.1.6" // https://mvnrepository.com/artifact/org.awaitility/awaitility
val kotlinCoroutinesCoreVersion = "1.6.3" // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-core
dependencies {

    // Logging
    implementation("io.github.microutils:kotlin-logging:$kotlinLoggingVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutinesCoreVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactive:$kotlinCoroutinesCoreVersion")

    // Http Client
    implementation("com.squareup.okhttp3:okhttp:$okhttp3")
    implementation("com.squareup.okhttp3:logging-interceptor:$okhttp3")

    // Testing
    testImplementation("com.squareup.okhttp3:mockwebserver:$okhttp3")
    testImplementation("commons-io:commons-io:2.8.0")
    testImplementation ("io.mockk:mockk:$mockkVersion")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.7.2")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:$junitJupiterVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitJupiterVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
    // End-to-End Testing
    testImplementation("com.squareup.retrofit2:retrofit:$retrofitJackson")
    testImplementation("com.squareup.retrofit2:converter-jackson:$retrofitJackson")
    testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonModuleKotlin")
    testImplementation("org.assertj:assertj-core:$assertJVersion")
    testImplementation("org.awaitility:awaitility:$awaitilityVersion")
}

java {
    withJavadocJar()
    withSourcesJar()
}

tasks.withType<Test> {
    useJUnitPlatform()
}
//publishing {
//
//    // See: https://docs.github.com/en/actions/guides/publishing-java-packages-with-gradle
//    repositories {
//        maven {
//            name = "GitHubPackages"
//            setUrl("https://maven.pkg.github.com/westwinglabs/coinbase-kotlin")
//            credentials {
//                username = System.getenv("GITHUB_ACTOR")
//                password = System.getenv("GITHUB_TOKEN")
//            }
//        }
//    }
//
//    publications {
//        create<MavenPublication>("maven") {
//            from(components["java"])
//
//            groupId = "com.westwinglabs"
//            artifactId = "coinbase-kotlin"
//            version = project.version.toString()
//
//            pom {
//                name.set("coinbase-kotlin")
//                description.set("Kotlin/Java client for Coinbase Pro.")
//                url.set("https://github.com/westwinglabs/coinbase-kotlin")
//
//                licenses {
//                    license {
//                        name.set("The Apache License, Version 2.0")
//                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
//                    }
//                }
//
//                developers {
//                    developer {
//                        id.set("zugaldia")
//                        name.set("Antonio Zugaldia")
//                        email.set("antonio@westwinglabs.com")
//                    }
//                }
//
//                scm {
//                    connection.set("scm:git:git://github.com/westwinglabs/coinbase-kotlin.git")
//                    developerConnection.set("scm:git:ssh://github.com/westwinglabs/coinbase-kotlin.git")
//                    url.set("https://github.com/westwinglabs/coinbase-kotlin")
//                }
//            }
//        }
//    }
//}
