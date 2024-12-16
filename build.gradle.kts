import com.vanniktech.maven.publish.SonatypeHost

plugins {
    kotlin("jvm") version "2.0.21"

    id("com.vanniktech.maven.publish") version "0.30.0"
    id("io.gitlab.arturbosch.detekt") version "1.23.7"

    id("org.jetbrains.kotlinx.kover") version "0.9.0"
    id("org.jetbrains.dokka") version "2.0.0"

    `java-library`
}

group = "org.javafreedom"
version = "1.0-SNAPSHOT"

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.1")

    testImplementation(kotlin("test"))
}

detekt {
    ignoreFailures = true
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()
}

kover.reports.verify.rule {
    minBound(50)
}
