rootProject.name = "hivemq-kafka-extension-customization-sdk"

pluginManagement {
    plugins {
        id("io.github.gradle-nexus.publish-plugin") version "${extra["plugin.nexus-publish.version"]}"
        id("com.github.hierynomus.license") version "${extra["plugin.license.version"]}"
        id("com.github.sgtsilvio.gradle.utf8") version "${extra["plugin.utf8.version"]}"
        id("com.github.sgtsilvio.gradle.metadata") version "${extra["plugin.metadata.version"]}"
        id("com.github.sgtsilvio.gradle.javadoc-links") version "${extra["plugin.javadoc-links.version"]}"
    }

    if (file("../hivemq/plugins").exists()) {
        includeBuild("../hivemq/plugins")
    }
}

includeBuild("../hivemq-platform")
if (file("../hivemq-extension-sdk").exists()) {
    includeBuild("../hivemq-extension-sdk")
}
