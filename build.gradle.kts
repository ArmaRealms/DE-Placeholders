plugins {
    java
}

group = "me.realized.de"
version = "1.0.4"

repositories {
    mavenCentral()
    maven {
        name = "spigot-repo"
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots")
    }
    maven {
        name = "bungeecord-repo"
        url = uri("https://oss.sonatype.org/content/repositories/snapshots")
    }
    maven {
        name = "clip-repo"
        url = uri("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    }
    maven {
        name = "jitpack-repo"
        url = uri("https://jitpack.io")
    }
    flatDir {
        dirs("$rootDir/libs/")
    }
}

dependencies {
    implementation("org.projectlombok:lombok:1.18.30")
    implementation("org.spigotmc:spigot-api:1.20.4-R0.1-SNAPSHOT")
    implementation("me.clip:placeholderapi:2.11.5")
    implementation("com.github.Realizedd.Duels:duels-api:3.5.1") {
        isTransitive = false
    }
    implementation("org.apache.commons:commons-lang3:3.14.0")
    compileOnly(files("libs/MVdWPlaceholderAPI-3.1.1.jar")) // Assumindo que você deseja manter como compileOnly e não como implementation
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
    withType<Jar> {
        archiveFileName.set(archiveFileName.get().replace("DE-", ""))
    }
    withType<ProcessResources> {
        filesMatching("extension.yml") {
            expand("version" to project.version)
        }
    }

    withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.compilerArgs.add("-parameters")
    }
}