plugins {
    java
    kotlin("jvm") version "1.6.20"
}

repositories { mavenLocal(); mavenCentral() }

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    val reactor: String by project
    implementation(enforcedPlatform("io.projectreactor:reactor-bom:$reactor"))
    implementation("io.projectreactor", "reactor-core")

    val lombok: String by project
    compileOnly("org.projectlombok:lombok:$lombok")
    testCompileOnly("org.projectlombok:lombok:$lombok")
    annotationProcessor("org.projectlombok:lombok:$lombok")

    implementation("com.github.javafaker:javafaker:1.0.2")
    testImplementation("io.projectreactor", "reactor-test")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

java{ sourceCompatibility = JavaVersion.VERSION_17 }

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> { kotlinOptions { jvmTarget = "17" } }
    test { useJUnitPlatform() }
}
