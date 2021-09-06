[//]: # (title: Gradle Application plugin)

<microformat>
<p>
<control>Sample project</control>: <a href="https://github.com/ktorio/ktor-gradle-sample/tree/main">ktor-gradle-sample</a>
</p>
</microformat>

The Gradle [Application plugin](https://docs.gradle.org/current/userguide/application_plugin.html) provides the ability to package applications, including code dependencies and generated start scripts. In this topic, we'll show you how to package and run a Ktor application created in the [](Gradle.xml) topic.

## Prerequisites {id="prerequisites"}
Before starting this tutorial, clone the [ktor-gradle-sample](https://github.com/ktorio/ktor-gradle-sample) repository.


## Apply the Application plugin and configure the main class {id="apply-plugin"}
To package an application, you need to apply the Application plugin first:
1. Open the [ktor-gradle-sample](https://github.com/ktorio/ktor-gradle-sample) project.
1. Make sure that the `build.gradle` file contains the following code:
   ```groovy
   ```
   {src="https://raw.githubusercontent.com/ktorio/ktor-gradle-sample/main/build.gradle" lines="1,3-4,8,9-11"}
    * The `id 'application'` line applies the Application plugin. 
    * The `mainClass` property is used to configure the main class of the application.
      > If you use [EngineMain](create_server.xml#engine-main) without the explicit `main` function, your `mainClass` depends on the used engine and might look as follows: `io.ktor.server.netty.EngineMain`.


## Package the application {id="package"}
The Application plugin provides various ways for packaging the application, for example, the `installDist` task installs the application with all runtime dependencies and start scripts. To create full distribution archives, you can use the `distZip` and `distTar` tasks.

In this topic, we'll use `installDist`:
1. Open a terminal.
1. Run the `installDist` task in one of the following ways depending on your operating system:
   
   <tabs group="os">
   <tab title="Linux/MacOS" group-key="unix">
   <code style="block" lang="Bash">./gradlew installDist</code>
   </tab>
   <tab title="Windows" group-key="windows">
   <code style="block" lang="CMD">gradlew.bat installDist</code>
   </tab>
   </tabs>
   
   Gradle will create an image of the application in the `build/install/ktor-gradle-sample` folder. 

> To learn how to use the resulting package to deploy your application using Docker, see the [](docker.md) help topic.

## Run the application {id="run"}
To run the [packaged application](#package):
1. Go to the `build/install/ktor-gradle-sample` folder in a terminal.
1. Depending on your operating system, run the `ktor-gradle-sample` or `ktor-gradle-sample.bat` executable:

   <tabs group="os">
   <tab title="Linux/MacOS" group-key="unix">
   <code style="block" lang="Bash">./ktor-gradle-sample</code>
   </tab>
   <tab title="Windows" group-key="windows">
   <code style="block" lang="CMD">ktor-gradle-sample.bat</code>
   </tab>
   </tabs>
   
1. Wait until the following message is shown:
   ```
   [main] INFO  Application - Responding at http://0.0.0.0:8080
   ```
   You can click the link to open the application in a default browser:
   <img src="ktor_idea_new_project_browser.png" alt="Ktor app in a browser" width="430"/>