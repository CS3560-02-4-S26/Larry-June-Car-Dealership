# Larry-June-Car-Dealership

An inventory-subsystem app for a dealership.

## Prerequisites

Install JDK 21

- Windows
  1. [Install JDK 21](https://adoptium.net/)
  2. Run the following to double check
     `java -version`
     `javac -version`

- macOS

```zsh
    brew install --cask temurin
    java -version
    javac -version
```

- Ubuntu/Debian Linux

```Bash
    sudo apt update
    sudo apt install -y openjdk-21-jdk
    java -version
    javac -version
```

Install Maven:

- Windows:
  - Option A
    - Run this in administrative powershell:

```Bash
        choco install maven -y
        mvn -v
```

- Option B
  1. [Download Maven ZIP File](https://maven.apache.org/download.cgi)
  2. Unzip to `C:\Program Files\Apache\Maven`
  3. Add Maven bin to PATH (in your environmental variables)
     `C:\Program Files\Apache\Maven\apache-maven-3.x.x\bin`
  4. Refresh terminal and double check by running `mvn -v`

- MacOS

```zsh
    brew install maven
    mvn -v
```

- Ubuntu/Debian Linux

```Bash
    sudo apt update
    sudo apt install -y maven
    mvn -v
```

## Running the App

First, change into proper directory

```Bash
cd larry-june-dealership
```

Then,

```Bash
mvn clean javafx:run
```

## Structure
- `~com/larryjune/dealership/model`
  - Contains all the classes that we'll use. This is where all logic goes
- `~com/larryjune/dealership/controller`
  - Contains all UI files.
- `~com/larryjune/dealership/service`
  - Maintains DB connections and SQL files. 
