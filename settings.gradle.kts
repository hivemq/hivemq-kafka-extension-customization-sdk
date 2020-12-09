pluginManagement {
    plugins {
        id("com.github.hierynomus.license") version "${extra["plugin.license.version"]}"
        id("com.jfrog.bintray") version "${extra["plugin.bintray.version"]}"
        id("com.github.sgtsilvio.gradle.utf8") version "${extra["plugin.utf8.version"]}"
        id("com.github.sgtsilvio.gradle.metadata") version "${extra["plugin.metadata.version"]}"
        id("com.github.sgtsilvio.gradle.javadoc-links") version "${extra["plugin.javadoc-links.version"]}"
    }
}

rootProject.name = "hivemq-kafka-extension-customization-sdk"

if (file("../hivemq-extension-sdk").exists()) {
    if (gradle.parent == null) { // not part of a composite build
        includeBuild("../hivemq-extension-sdk")
    }
}