import com.vanniktech.maven.publish.SonatypeHost

plugins {
    kotlin("jvm") version "2.0.21"

    id("com.vanniktech.maven.publish") version "0.30.0"
    id("io.gitlab.arturbosch.detekt") version "1.23.7"

    id("org.jetbrains.kotlinx.kover") version "0.9.0"
    id("org.jetbrains.dokka") version "2.0.0"

    id("org.sonarqube") version "6.0.1.5171"

    `java-library`
}

group = "org.javafreedom"
project.version = project.findProperty("version") as String? ?: "0.0.1-SNAPSHOT"

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.1")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
    testLogging.showStandardStreams = true
}

detekt {
    ignoreFailures = true
}

val version: String by lazy {
    project.findProperty("version")?.toString() ?: "0.0.0-SNAPSHOT"
}

mavenPublishing {
    println("Using Version: ${version}")

    coordinates(group.toString(), rootProject.name, version)

    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    pom {
        name.set("KHol")
        description.set("Kotlin Holidays - A library to provide a list of holidays.")
        inceptionYear.set("2024")
        url.set("https://github.com/triplem/khol/")
        licenses {
            license {
                name.set("MIT License")
                url.set("http://www.opensource.org/licenses/mit-license.php")
            }
        }
        developers {
            developer {
                id.set("triplem")
                name.set("Markus M. May")
                url.set("https://github.com/triplem/")
            }
        }
        scm {
            url.set("https://github.com/triplem/khol/")
            connection.set("scm:git:git://github.com/triplem/khol.git")
            developerConnection.set("scm:git:ssh://git@github.com/triplem/khol.git")
        }
    }

    signAllPublications()
}

kover.reports.verify.rule {
    minBound(50)
}

sonarqube {
    properties {
        property("sonar.projectName", "khol")
        property("sonar.projectKey", "triplem_khol")
        property("sonar.organization", "triplem")
        property("sonar.host.url", "https://sonarcloud.io")

        property("sonar.kotlin.detekt.reportPaths",
            project.layout.buildDirectory.file("reports/detekt/detekt.xml").get().asFile)
        property("sonar.coverage.jacoco.xmlReportPaths",
            project.layout.buildDirectory.file("reports/kover/report.xml").get().asFile)
    }
}

tasks.register("prepareDocs") {
    group = "documentation"
    description = "Prepare GitHub Pages documentation."

    dependsOn(tasks.dokkaGeneratePublicationHtml, tasks.koverHtmlReport, tasks.test)

    val outputDir = project.layout.projectDirectory.dir("docs")
    doLast {
        copy {
            from(tasks.dokkaGeneratePublicationHtml.get().outputDirectory)
            into(outputDir.dir("dokka"))
        }
        copy {
            from(project.layout.buildDirectory.dir("reports/kover/html"))
            into(outputDir.dir("kover"))
        }
        copy {
            from(project.layout.buildDirectory.dir("reports/detekt"))
            into(outputDir.dir("detekt"))
        }
        copy {
            from(project.layout.buildDirectory.dir("reports/tests/test"))
            into(outputDir.dir("tests"))
        }
    }
}