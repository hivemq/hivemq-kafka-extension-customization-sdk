rootProject.name = "hivemq-kafka-extension-customization-sdk"

pluginManagement {
    plugins {
        id("com.github.hierynomus.license") version "${extra["plugin.license.version"]}"
        id("com.github.sgtsilvio.gradle.utf8") version "${extra["plugin.utf8.version"]}"
        id("com.github.sgtsilvio.gradle.metadata") version "${extra["plugin.metadata.version"]}"
        id("com.github.sgtsilvio.gradle.javadoc-links") version "${extra["plugin.javadoc-links.version"]}"
        id("io.github.gradle-nexus.publish-plugin") version "${extra["plugin.nexus-publish.version"]}"
    }
}

if (file("../hivemq-extension-sdk").exists()) {
    includeBuild("../hivemq-extension-sdk")
}