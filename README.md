# Interactivity Mining for the Web
#### Research & Innovation projet
###Telecom Saint-Etienne - FI3 © 2014/2015 :

![structure du projet](/images/schema.jpg)

Members :
* RIAHI Chedy
* BOUHERROU Nacer
* AHMED BACHA Abdelkrim

## Presentation
---
The main goal of this research and innovation project is to crawl websites with different profiles. At the beginning, the program functions as a crawler, then stores, in a NoSQL Graph Database, characteristics related to Javascript such as AJAX requests or Javascript events. We use thereafter the data via an API to classify these websites according to their characteristics and other metrics.

Keywords :
Java8 — Data Mining — Selenium — NoSQL — ChromeDriver

## How to use it ?
---
The project uses somme dependencies, fortunately we have managed this with **Maven** .

There is still some configurations to do, like the installation of the Neo4J database, and chrome driver (the Mac version is provided in the project under "src/main/resources").

Instructions for mac users :

***step 00 :***
Set up a folder for the crawler, "Users/PRI".

```shell
cd /Users
sudo mkdir PRI
sudo chmod 777 PRI
```

***step 01 :***
Install Neo4J database with the server using brew

```shell
brew install neo4j
```
After that, start the Neo4J server, and check the URL "http://localhost:7474/"

```shell
 neo4j (start/stop/console)
```

***step 02 :***
Move the chrome driver from the projet folder "src/main/resources" to your "/bin"

***step 03 :***
Launch a maven update of the project and excecute the "Controller.class".

***Note :*** The URL of the crawled domain is setted in "Constants.class".

## Structure
---
* Packages (Constants , Controller)
* Folder "src/main/resources" , contains project resources
    * javascript processing function (seeDomEvents.js)
    * configuration.txt
* Folder "src/result", contains generated profils
    * profil1.csv
    * profil2.csv

## Database structure
---
![Database structure](/images/graph-colored.png)

## Generated profils  
---
######Profil 01 (Page - Links)

![profil 01](/images/profil1.jpg)

######Profil 02 (Page - Links - Events)

![profil 02](/images/profil2.jpg)
