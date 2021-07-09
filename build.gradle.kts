buildscript {
    dependencies {
        if (gradle.includedBuilds.find { it.name == "plugins" } != null) {
            classpath("com.hivemq:plugins")
        }
    }
}

plugins {
    `java-library`
    `maven-publish`
    signing
    id("io.github.gradle-nexus.publish-plugin")
    id("com.github.hierynomus.license")
    id("com.github.sgtsilvio.gradle.utf8")
    id("com.github.sgtsilvio.gradle.metadata")
    id("com.github.sgtsilvio.gradle.javadoc-links")
}

if (gradle.includedBuilds.find { it.name == "plugins" } != null) {
    apply(plugin = "com.hivemq.version-updater")
    project.ext.set("versionUpdaterFiles", arrayOf("README.adoc"))
}

/* ******************** metadata ******************** */

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
        org.set("hivemq")
        repo.set("hivemq-kafka-extension-customization-sdk")
        issues()
    }
}

/* ******************** dependencies ******************** */

val internalPlatform by configurations.creating {
    isVisible = false
    isCanBeConsumed = false
    isCanBeResolved = false
}

configurations {
    compileClasspath.get().extendsFrom(internalPlatform)
    runtimeClasspath.get().extendsFrom(internalPlatform)
    testCompileClasspath.get().extendsFrom(internalPlatform)
    testRuntimeClasspath.get().extendsFrom(internalPlatform)
    javadocLinks.get().shouldResolveConsistentlyWith(runtimeClasspath.get())
}

repositories {
    mavenCentral()
}

dependencies {

    /* HiveMQ platform constraints */
    internalPlatform(platform("com.hivemq:hivemq-main-platform"))

    api("com.hivemq:hivemq-extension-sdk:$version")
    api("org.slf4j:slf4j-api")
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
        "Implementation-Version" to project.version
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

            versionMapping {
                usage("java-api") {
                    fromResolutionResult()
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
        }
    }
}

signing {
    val signKey: String? by project
    val signKeyPass: String? by project
    useInMemoryPgpKeys(signKey, signKeyPass)
    sign(publishing.publications["maven"])
}

nexusPublishing {
    repositories {
        sonatype()
    }
}

/* ******************** checks ******************** */

license {
    header = projectDir.resolve("HEADER")
    mapping("java", "SLASHSTAR_STYLE")
}