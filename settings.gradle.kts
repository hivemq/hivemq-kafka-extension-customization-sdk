pluginManagement {
    if (file("../hivemq/plugins").exists()) {
        includeBuild("../hivemq/plugins")
    }
}

rootProject.name = "hivemq-kafka-extension-customization-sdk"

if (file("../hivemq-extension-sdk").exists()) {
    includeBuild("../hivemq-extension-sdk")
}
