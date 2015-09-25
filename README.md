##Snakes And Ladders

Simple Snakes and Ladders Game Implemented in Java and Maven and perforemd Junit testing.


##Snakes and Ladders Game - Rules

* Two or more players can play the game.
* Typical board size is 10 x10, but user can specify different board size.
* Game takes the number of the players and auto generates the players names.

##Getting started

Include this maven dependency in your pom:
``` javascript
<dependency>
	<groupId>commons-dbcp</groupId>
	<artifactId>commons-dbcp</artifactId>
	<version>1.2.2</version>
	</dependency>
```

In DBConnection.java set the following parameters.
``` javascript
setUsername("");
setPassword("");
setUrl("jdbc:mysql://localhost/(DB schema name)");
```

The following database tables are auto-generated when the project is executed for first time.
``` javascript
CREATE  TABLE IF NOT EXISTS `snakesandladders`.`game_results` (`GameId` VARCHAR(20) NOT NULL ,`Date` VARCHAR(20) NULL ,`Winner` VARCHAR(20) NULL ,PRIMARY KEY (`GameId`) )
```

``` javascript
CREATE  TABLE IF NOT EXISTS `snakesandladders`.`game_details` ( `GameId` VARCHAR(20) NOT NULL ,`PlayerName` VARCHAR(20) NULL ,`Position` VARCHAR(20) NULL ,INDEX `GameId` (`GameId` ASC) , CONSTRAINT `GameId` FOREIGN KEY (`GameId` ) REFERENCES `snakesandladders`.`game_results` (`GameId` ) ON DELETE CASCADE ON UPDATE CASCADE)
```
	
		
Game is verified on *Java version 7 and JDK1.7*.

Run the SnakeAndLadder.java file to start.
