# Tazpvp

Tazpvp plugin for all plugin functionality

## Installation

Download this github repo and open with intellji using java 8

```bash
git clone https://github.com/FalseCodee/Tazpvp.git
```

## Usage

This project was made in maven. The Pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>net.ntdi</groupId>
    <artifactId>Tazpvp</artifactId>
    <version>6.9</version>
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>8</source>
                    <target>8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
    <name>Tazpvp</name>

    <repositories>
        <repository>
            <id>spigotmc-repo</id>
            <url>https://hub.spigotmc.org/nexus/content/repositories/snapshots/</url>
        </repository>
        <repository>
            <id>sonatype</id>
            <url>https://oss.sonatype.org/content/groups/public/</url>
        </repository>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
        <repository>
            <id>dmulloy2-repo</id>
            <url>https://repo.dmulloy2.net/repository/public/</url>
        </repository>
        <repository>
            <id>everything</id>
            <url>https://repo.citizensnpcs.co/</url>
        </repository>
        <repository>
            <id>placeholderapi</id>
            <url>https://repo.extendedclip.com/content/repositories/placeholderapi/</url>
        </repository>
    </repositories>

    <dependencies>
        <dependency>
            <groupId>org.spigotmc</groupId>
            <artifactId>spigot-api</artifactId>
            <version>1.8.8-R0.1-SNAPSHOT</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.8.1</version>
        </dependency>
        <dependency>
            <groupId>com.github.MilkBowl</groupId>
            <artifactId>VaultAPI</artifactId>
            <version>1.7</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>com.comphenix.protocol</groupId>
            <artifactId>ProtocolLib</artifactId>
            <version>4.7.0</version>
        </dependency>
        <dependency>
            <groupId>net.citizensnpcs</groupId>
            <artifactId>citizens-main</artifactId>
            <version>2.0.28-SNAPSHOT</version>
            <type>jar</type>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>me.clip</groupId>
            <artifactId>placeholderapi</artifactId>
            <version>2.10.10</version>
            <scope>provided</scope>
        </dependency>
    </dependencies>
</project>

```

## Depdencies
This plugin depends on Spigot api, Maven, Protocol lib, citizens npc and papi.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Make sure your project build works before committing.

## License
[BSD 3.0](https://opensource.org/licenses/BSD-3-Clause)