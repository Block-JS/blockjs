import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

project.ext["packageTitle"] = "BlockJS"
project.description = "A Paper plugin to use JavaScript to interact with your Minecraft world"
project.ext["mainClass"] = "io.github.blockjs.plugin.Main"

plugins {
    kotlin("jvm") version "1.9.20"
    `java-library`

    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    mavenCentral()
    gradlePluginPortal()

    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.2-R0.1-SNAPSHOT")

    implementation("com.caoccao.javet:javet:3.0.1")
    implementation("com.caoccao.javet:javet-linux-arm64:3.0.1")
    implementation("com.caoccao.javet:javet-macos:3.0.1")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

val shadowArchiveBaseName = "${project.ext["packageTitle"]}-${rootProject.version}"

tasks.processResources {
    expand(
        project.properties,
    )
}

/** All-in-one JAR */
tasks.shadowJar {
    archiveClassifier = ""
    archiveBaseName = shadowArchiveBaseName

    include("libjavet-v8*")
}

tasks.create("linuxShadowJar", type = ShadowJar::class) {
    archiveBaseName = "$shadowArchiveBaseName-linux-only"

    exclude {
        it.name.startsWith("libjavet-v8") && !it.name.startsWith("libjavet-v8-linux")
    }
}

//tasks.create("linuxArmShadowJar", type = ShadowJar::class) {
//    archiveBaseName = "$shadowArchiveBaseName-linux-arm-only"
//    configurations = listOf(project.configurations.runtimeClasspath.get())
//
//    exclude {
//        it.name.startsWith("libjavet-v8") && !it.name.startsWith("libjavet-v8-linux-arm")
//    }
//}

tasks.create("windowsShadowJar", type = ShadowJar::class) {
    archiveBaseName = "$shadowArchiveBaseName-windows-only"

    exclude {
        it.name.startsWith("libjavet-v8") && !it.name.startsWith("libjavet-v8-windows")
    }
}

tasks.create("macosShadowJar", type = ShadowJar::class) {
    archiveBaseName = "$shadowArchiveBaseName-macos-only"

    exclude {
        it.name.startsWith("libjavet-v8") && !it.name.startsWith("libjavet-v8-macos")
    }
}

tasks.withType(ShadowJar::class) {
    group = "shadow"

    manifest {
        attributes["Main-Class"] = project.ext["mainClass"]
        attributes["Package-Title"] = project.ext["packageTitle"]
    }

    from(sourceSets.main.get().output)

    configurations = listOf(
        project.configurations.implementation.get(),
//        project.configurations.testImplementation.get()
    ).onEach { it.isCanBeResolved = true }

    include("**")
    /** Not used */
    exclude("libjavet-node*")

    if (project.hasProperty("distPath")) {
        destinationDirectory = file(project.properties["distPath"]!!)
    }
}

kotlin {
    jvmToolchain(17)
}