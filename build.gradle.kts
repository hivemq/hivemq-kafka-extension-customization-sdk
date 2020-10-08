import java.util.*

plugins {
    id("java-library")
    id("maven-publish")
    id("com.jfrog.bintray")
    id("com.github.hierynomus.license")
    id("com.github.sgtsilvio.gradle.utf8")
    id("com.github.sgtsilvio.gradle.metadata")
    id("com.github.sgtsilvio.gradle.javadoc-links")
}


/* ******************** metadata ******************** */

group = "com.hivemq"
description = "SDK for the development of HiveMQ Kafka Extension customizations"

metadata {
    readableName = "HiveMQ Kafka Extension Customization SDK"
    organization {
        name = "HiveMQ GmbH"
        url = "https://www.hivemq.com/"
    }
    license {
        apache2()
    }
    developers {
        developer {
            id = "cschaebe"
            name = "Christoph Schaebel"
            email = "christoph.schaebel@hivemq.com"
        }
        developer {
            id = "lbrandl"
            name = "Lukas Brandl"
            email = "lukas.brandl@hivemq.com"
        }
        developer {
            id = "flimpoeck"
            name = "Florian Limpoeck"
            email = "florian.limpoeck@hivemq.com"
        }
        developer {
            id = "sauroter"
            name = "Georg Held"
            email = "georg.held@hivemq.com"
        }
        developer {
            id = "SgtSilvio"
            name = "Silvio Giebl"
            email = "silvio.giebl@hivemq.com"
        }
    }
    github {
        org = "hivemq"
        repo = "hivemq-kafka-extension-customization-sdk"
        issues()
    }
}


/* ******************** dependencies ******************** */

repositories {
    mavenCentral()
}

dependencies {
    api("com.hivemq:hivemq-extension-sdk:$version")
    api("org.slf4j:slf4j-api:${property("slf4j.version")}")
}


/* ******************** java ******************** */

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11

    withJavadocJar()
    withSourcesJar()
}

tasks.withType<Jar>().configureEach {
    manifest.attributes(
            "Implementation-Title" to project.name,
            "Implementation-Vendor" to metadata.organization.name,
            "Implementation-Version" to project.version)
}

tasks.javadoc {
    title = "${metadata.readableName} ${project.version} API"

    doLast { // javadoc search fix for jdk 11 https://bugs.openjdk.java.net/browse/JDK-8215291
        copy {
            from("${buildDir}/docs/javadoc/search.js")
            into("${buildDir}/tmp/javadoc/")
            filter { line -> line.replace("if (ui.item.p == item.l) {", "if (item.m && ui.item.p == item.l) {") }
        }
        delete("${buildDir}/docs/javadoc/search.js")
        copy {
            from("${buildDir}/tmp/javadoc/search.js")
            into("${buildDir}/docs/javadoc/")
        }
    }
}


/* ******************** publishing ******************** */

publishing.publications.register<MavenPublication>("kafkaExtensionCustomizationSdk") {
    from(components["java"])
}

bintray {
    user = "${project.findProperty("bintrayUser") ?: System.getenv("BINTRAY_USER")}"
    key = "${project.findProperty("bintrayApiKey") ?: System.getenv("BINTRAY_API_KEY")}"
    dryRun = false
    publish = false
    override = false
    pkg.apply {
        userOrg = "hivemq"
        repo = "HiveMQ"
        name = project.name
        desc = project.description
        websiteUrl = metadata.url
        issueTrackerUrl = metadata.issueManagement.url
        vcsUrl = metadata.scm.url
        setLicenses(metadata.license.shortName)
        setLabels("hivemq-kafka-extension", "sdk", "mqtt", "kafka")
        publicDownloadNumbers = false
        version.apply {
            released = Date().toString()
            gpg.apply {
                sign = true
            }
        }
    }
}
afterEvaluate {
    bintray.setPublications(*publishing.publications.withType<MavenPublication>().names.toTypedArray())
}


/* ******************** checks ******************** */

license {
    header = file("${projectDir}/HEADER")
    mapping("java", "SLASHSTAR_STYLE")
}
