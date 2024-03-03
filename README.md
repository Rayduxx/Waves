# Maven plugin for JavaFX

[![Maven Central](https://img.shields.io/maven-central/v/org.openjfx/javafx-maven-plugin.svg?color=%234DC71F)](https://search.maven.org/#search|ga|1|org.openjfx.javafx-maven-plugin)
[![Travis CI](https://api.travis-ci.com/openjfx/javafx-maven-plugin.svg?branch=master)](https://travis-ci.com/openjfx/javafx-maven-plugin)
[![Apache License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)

Maven plugin to run JavaFX 17+ applications

## Install

The plugin is available via Maven Central. 

In case you want to build and install the latest snapshot, you can
clone the project, set JDK 17 and run


## Usage
Create a java project with maven and add the dependencies 

 dependencies are added as usual:
```
 <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.33</version>
        </dependency>

```

```
<dependency>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-controls</artifactId>
    <version>21.0.2</version>
</dependency>

```
```

<dependency>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-fxml</artifactId>
    <version>21.0.2</version>
</dependency>
```

Add the plugin:

```
<plugin>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-maven-plugin</artifactId>
    <version>0.0.8</version>
    <configuration>
        <mainClass>tn.esprti.MainFx</mainClass>
    </configuration>
</plugin>
```

