# Sainsbury's Berry Scraper

A lightweight Java Maven application which scrapes items from a Sainsbury's product page and returns relevant data in JSON format

## Requirements

Full requirements for project [here](https://jsainsburyplc.github.io/serverside-test/)

## Dependencies

* JSoup: webscraper tooling
* Lombok: to reduce boilerplate POJO code
* Jackson: to easily map POJO to JSON
* Junit: execute unit tests

## Running code

### Running outside an IDE
The easiest way to execute the code is to download and save the [GroceryScraper.jar](https://github.com/louise652/grocery-scraper/blob/main/LaunchGroceryScraper.jar) file. Open a command prompt window and navigate to the directory where the file is saved. Type the command 
```git
java -jar GroceryScraper.jar
```
and the code will execute and produce the JSON output. A file named result.json will also be created in the same directory with the output.

### Running within an IDE
Clone this repository and import into Eclipse IDE running Java 1.8 or above as a Maven project. The Lombok plugin will be needed to compile the code. Instructions to add the plugin can be found [here.](https://projectlombok.org/setup/eclipse)


```git
git clone git@github.com:louise652/grocery-scraper.git

```
After building, Navigate to ScraperApplication.java, right click and run as Java Application. The scraper results will appear in the console.


## Running Tests

If running through the IDE, navigate to the src/test/java directory. Right click and run as JUnit Test.

## Limitations
* Ideally, this application would take in the url as an argument and return JSON results regardless of the website section. However, it seems that the html is different for different sections so this was not feasible in the given time frame.

## Future ideas
* As stated above, future enhancements could include extending the functionality for other areas of the website. 
* Results could be written to external storage (e.g. AWS S3 bucket) for further analysis
* A more user friendly GUI instead of just printing to the terminal

## Example output
![JSON output](https://github.com/louise652/grocery-scraper/blob/main/src/main/resources/results.PNG)

