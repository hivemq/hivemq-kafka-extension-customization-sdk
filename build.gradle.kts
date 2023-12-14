buildscript {
    if (gradle.includedBuilds.any { it.name == "plugins" }) {
        plugins {
            id("com.hivemq.version-updater")
        }
    }
}

plugins {
    `java-library`
    `maven-publish`
    signing
    alias(libs.plugins.nexusPublish)
    alias(libs.plugins.defaults)
    alias(libs.plugins.metadata)
    alias(libs.plugins.javadocLinks)
    alias(libs.plugins.license)
}

plugins.withId("com.hivemq.version-updater") {
    project.ext.set("versionUpdaterFiles", arrayOf("README.adoc"))
}

group = "com.hivemq"
description = "SDK for the development of HiveMQ Kafka Extension customizations"

metadata {
    readableName.set("HiveMQ Kafka Extension Customization SDK")
    organization {
        name.set("HiveMQ GmbH")
        url.set("https://www.hivemq.com/")
    }
    license {
        apache2()
    }
    developers {
        register("cschaebe") {
            fullName.set("Christoph Schaebel")
            email.set("christoph.schaebel@hivemq.com")
        }
        register("lbrandl") {
            fullName.set("Lukas Brandl")
            email.set("lukas.brandl@hivemq.com")
        }
        register("flimpoeck") {
            fullName.set("Florian Limpoeck")
            email.set("florian.limpoeck@hivemq.com")
        }
        register("sauroter") {
            fullName.set("Georg Held")
            email.set("georg.held@hivemq.com")
        }
        register("SgtSilvio") {
            fullName.set("Silvio Giebl")
            email.set("silvio.giebl@hivemq.com")
        }
    }
    github {
        issues()
    }
}

repositories {
    mavenCentral()
}

dependencies {
    api("com.hivemq:hivemq-extension-sdk:$version")
    api(libs.slf4j.api)
}

/* ******************** java ******************** */

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
    withJavadocJar()
    withSourcesJar()
}

tasks.withType<Jar>().configureEach {
    manifest.attributes(
        "Implementation-Title" to project.name,
        "Implementation-Vendor" to metadata.organization.get().name.get(),
        "Implementation-Version" to project.version,
    )
}

tasks.javadoc {
    title = "${metadata.readableName.get()} ${project.version} API"

    doLast { // javadoc search fix for jdk 11 https://bugs.openjdk.java.net/browse/JDK-8215291
        copy {
            from(destinationDir!!.resolve("search.js"))
            into(temporaryDir)
            filter { line -> line.replace("if (ui.item.p == item.l) {", "if (item.m && ui.item.p == item.l) {") }
        }
        delete(destinationDir!!.resolve("search.js"))
        copy {
            from(temporaryDir.resolve("search.js"))
            into(destinationDir!!)
        }
    }
}

/* ******************** publishing ******************** */

publishing {
    publications {
        register<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}

signing {
    val signingKey: String? by project
    val signingPassword: String? by project
    useInMemoryPgpKeys(signingKey, signingPassword)
    sign(publishing.publications["maven"])
}

nexusPublishing {
    this.repositories {
        sonatype()
    }
}

/* ******************** checks ******************** */

license {
    header = file("HEADER")
    mapping("java", "SLASHSTAR_STYLE")
}
