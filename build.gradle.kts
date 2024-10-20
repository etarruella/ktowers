import xyz.jpenilla.runpaper.task.RunServer

plugins {
    id("java")
    id("xyz.jpenilla.run-paper") version "2.3.1"
    id("net.kyori.indra.git") version "3.1.3"
}

group = "com.etarruella"
version = "1.0-a"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    implementation("commons-io:commons-io:2.14.0")
    compileOnly("org.spigotmc:spigot-api:1.20.2-R0.1-SNAPSHOT")
}

java.toolchain.languageVersion = JavaLanguageVersion.of(21)

tasks.named<Jar>("jar") {
    manifest {
        attributes(
            "Implementation-Title" to project.name,
            "Implementation-Version" to getPluginVersion()
        )
    }

    val commit = indraGit.commit()
    if (commit != null) {
        manifest {
            attributes(
                "Git-Commit" to commit.name()
            )
        }
    }
}

tasks.named<ProcessResources>("processResources") {
    val props = mapOf(
        "pluginVersion" to getPluginVersion()
    )

    inputs.properties(props)

    filesMatching("plugin.yml") {
        expand(props)
    }
}

tasks.named<RunServer>("runServer") {
    val mcVersion = project.findProperty("mcVersion") ?: "1.21"
    systemProperty("minecraftVersion", mcVersion)
    jvmArgs = listOf(
        "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"
    )
    minecraftVersion(mcVersion.toString())
}

fun getPluginVersion(): String {
    val commit = indraGit.commit()
    return if (commit != null) {
        "${project.version}+${commit.abbreviate(7).name()}"
    } else {
        project.version.toString()
    }
}