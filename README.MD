To run the application use following steps:

1. cd in cloned directory
2. Build jar using command: `./mvnw clean package`
3. Start backend `java -jar target/boundlessCrunchPizzaApp-0.0.1-SNAPSHOT.jar`
4. `cd frontend`
5. `npm install`
6. `npm start`

App will be available on localhost port 3000.

A few notes to add:
1. Pizza sizes, bases and toppings are separate objects in the database for easier management,
   ie can be added to or removed from the database according to stock balances.
2. H2 in memory database is used for testing purposes as most straightforward option.
3. I did not try to make a beautiful interface due to lack of time, I mainly worked on the 
   application logic.
4. If I had more time, I would also like to improve a few things:
- Sizes, bases and fillings could have a status column, so if they are not available, you can change 
  the status and add filtering in the service function to display only available ones.
- The user and the courier should definitely have different roles so that user never has an 
option to view the list of orders and update delivery statuses.
- Unit tests could also be improved
  
However, with the given time frame I hope this should meet the minimum requirements. 
