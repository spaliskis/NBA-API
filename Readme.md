# Basketball API
Basketball API is a tool that can be used to see and retrieve the data about this year's NBA (National Basketball Association). It stores data about all teams and players. This data can also be sorted by specified categories.

## Group members
The API was created by two people: Simonas Šaltenis and Šarūnas Pališkis.

## Build and run
To build and run the project, you need to run these 2 commands in the project's root folder:
```
> mvn compile
```
Compiles the java source classes of the maven project.
```
> mvn test
```
Runs all of the tests 
```
> mvn exec:java
```
Executes the main method of the project (starts the program process).
## Usage
To start using the API, first you need to run the two commands mentioned in the "Build and run" section. After that, you can start navigating to different routes of the API. These are all the routes you can navigate to and their meanings:

------------


- http://localhost:8080/teams - displays every team in the league with their data.

------------


- http://localhost:8080/teams/{confname} - enter your desired conference name instead of the {confName} (either eastTeams or westTeams). Displays every team in the selected conference.

------------


- http://localhost:8080/teams/{confname}/{name} - enter your desired conference name (either eastTeams or westTeams) and desired team name that belongs to that conference (name must be entered in lowercase letters without spaces) instead of {confName} and {name}.

------------


- http://localhost:8080/teams/wins - displays the teams sorted by the amount of won games.

------------


- http://localhost:8080/teams/losses -displays the teams sorted by the amount of lost games.

------------


- http://localhost:8080/teams/ppg - displays the teams sorted by their average amount of points scored per game.

------------


- http://localhost:8080/players - displays one list of all players in the league.

------------


- http://localhost:8080/players/pts - displays the players sorted by the average amount of points scored per game.

------------


- http://localhost:8080/players/ast - displays the players sorted by the average amount of assists recorded per game.

------------


- http://localhost:8080/players/trb - displays the players sorted by the average amount of rebounds recorded per game.

------------


- http://localhost:8080/players/stl - displays the players sorted by the average amount of steals recorded per game.

------------


- http://localhost:8080/players/blk - displays the players sorted by the average amount of blocks recorded per game.

------------


- http://localhost:8080/players/tov - displays the players sorted by the average amount of turnovers recorded per game.

------------


- http://localhost:8080/players/pf - displays the players sorted by the average amount of personal fouls recorded per game.

------------


- http://localhost:8080/players/mpg - displays the players sorted by the average amount of minutes played.

------------


- http://localhost:8080/players/fgPerc - displays the players sorted by their field goal percentage.

------------


- http://localhost:8080/players/threePerc - displays the players sorted by their three point percentage.

------------


- http://localhost:8080/players/twoPerc - displays the players sorted by their two point percentage.

------------


- http://localhost:8080/players/freePerc - displays the players sorted by their free throw percentage.

------------


## Implementation
Fot implementing our idea we had to use many different third party libraries and programming techniques.
### Third party libraries
For the implementation of this project we used these third party libraries:

------------

- **Gson** - for serializing JSONObjects into our classes' objects.

------------

- **org.json** - for creating JSONObjects out of the scraped data.

------------

- **Jsoup** - for scraping the wanted data from sports websites.

------------

- **Opencsv** - for reading and writing CSV files.

------------

- **Spring Boot** framework - for making it easier to create and run API applications.

------------


### Programming techniques
In our development process we used these programming techniques:

------------

- **Collections** - for storing and manipulating groups of objects.

------------

- **Custom exceptions** - to be thrown when a specific basketball website cannot be reached or successfuly scraped.

------------

- **Exception handling** - to continue the flow of the program or provide feedback about some problem that occured in the program.

------------

- **Method overriding** - for implementing interfaces.

------------

- **Method overloading** - to use the same method, but with different parameters for different use cases.

------------

- **Lambdas** - to make code shorter and easier to read.

------------

- **Streams** - to easily and understandibly process data in a linear way.

------------

- **File I/O** - for writing data into files and reading data from files.

------------

- **Serialization** - for taking data from one object and putting it into another one.

------------

- **Deserialization** - for converting output of a file into a JSONObject.

------------

- **Multithreading** - for web scarping elements in an asynchronous ways, thus saving some time.

------------


## Organization
Since the project was developed by two people, organizing was not a big issue - it was quite easy to share the work load. We would usually have a call once in maybe two weeks and also use messaging apps to discuss the project.
### Git
We used Git for concurrent development. Usually, one member of the group would be working on some feature for a couple of days and push his commit to the main branch. Then the other member would use git pull for updating his local repository and continue the work from where the other member stopped.
### Challenges
The main challenges we faced were time management (since we started working on the project quite late) and deciding which features should be implemented and which ones should be left out.