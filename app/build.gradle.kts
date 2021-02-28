/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java application project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/6.8.2/userguide/building_java_projects.html
 */

 import org.gradle.jvm.tasks.Jar

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

repositories {
    // Use JCenter for resolving dependencies.
    jcenter()
}

dependencies {
    // Use JUnit test framework.
    testImplementation("junit:junit:4.13")

    // This dependency is used by the application.
    implementation("com.google.guava:guava:29.0-jre")
}

application {
    // Define the main class for the application.
    mainClass.set("Uranaishi.App")
}

val run by tasks.getting(JavaExec::class) {
    standardInput = System.`in`
}


val fatJar = task("fatJar", type = Jar::class) {
    baseName = "${project.name}"
    // manifest Main-Class attribute is optional.
    // (Used only to provide default main class for executable jar)
    manifest {
        attributes["Main-Class"] = "Uranaishi.App" // fully qualified class name of default main class
    }
from(configurations.runtime.get().map({ if (it.isDirectory) it else zipTree(it) }))
    with(tasks["jar"] as CopySpec)
}

tasks {
    "jar" {
        dependsOn(fatJar)
    }
}
