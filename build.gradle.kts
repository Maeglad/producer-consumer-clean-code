plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // h2
    implementation("com.h2database:h2:2.2.224")
    // hibernate
    implementation("org.hibernate:hibernate-core:6.3.1.Final")
    implementation("org.hibernate:hibernate-entitymanager:5.6.15.Final")
    implementation("org.slf4j:slf4j-api:1.7.32")
    implementation("org.slf4j:slf4j-simple:1.7.32")

    // dependency injection
    implementation("com.google.inject:guice:7.0.0")
}
tasks.test {
    useJUnitPlatform()
}