# Deck Of Cards API Automation

This project contains a list of API tests of following Deck of Cards API.
 
  Create a new deck of cards 
  
    https://deckofcardsapi.com/api/deck/new/
    https://deckofcardsapi.com/api/deck/new/shuffle

  
   Support adding Jokers
    
      https://deckofcardsapi.com/api/deck/new?jokers_enabled=true
      https://deckofcardsapi.com/api/deck/new?jokers_enabled=true
   
  Draw one or more cards from a deck 
  
    https://deckofcardsapi.com/api/deck/<<deck_id>>/draw//?count=N
  
  

## Prerequisites

What things you need to install the software and how to install them

```
JDK 1.8
Maven
```

## Running the tests

Just run following command to run the all test cases
```
mvn test
```