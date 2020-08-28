Application Name: Assignment

URL : http://localhost:8080

Request URL:

	1. http://localhost:8080/getAllUser  - To retrive all the user
	2. http://localhost:8080/getAddressById/2  - To retrieve address of user by passing user ID.
	3. http://localhost:8080/getUser/2  - To retrive user by user ID.
	4. http://localhost:8080/delete/3  - To delete User by ID.
	5. http://localhost:8080/update  - For updating User.
	6. http://localhost:8080/createUser  - For Creating User.
	7. http://localhost:8080/graphQuery  - For GraphQL


User Graph: file under resource folder : 'user.graphqls'


Performance Optimization:

- App uses caching to reduce DB hits.
- App uses Lazy loading
- @Index, an index with the given name is applied on the User column
- App also uses properties
   spring.jpa.properties.hibernate.order_inserts=true
   spring.jpa.properties.hibernate.jdbc.batch_size=15
   spring.jpa.properties.hibernate.order_updates=true
   spring.jpa.properties.hibernate.jdbc.batch_versioned_data=true


Random User: Generation on startup present in 'AssignmentApplication' class


Database Stats:
 First Request: getAllUser()
    45100 nanoseconds spent acquiring 1 JDBC connections;
    0 nanoseconds spent releasing 0 JDBC connections;
    13531200 nanoseconds spent preparing 3999 JDBC statements;
    146730400 nanoseconds spent executing 3999 JDBC statements;
    0 nanoseconds spent executing 0 JDBC batches;
    0 nanoseconds spent performing 0 L2C puts;
    0 nanoseconds spent performing 0 L2C hits;
    0 nanoseconds spent performing 0 L2C misses;
    0 nanoseconds spent executing 0 flushes (flushing a total of 0 entities and 0 collections);
    4500 nanoseconds spent executing 1 partial-flushes (flushing a total of 0 entities and 0 collections)

Secound Request: getAllUser()
    0 nanoseconds spent acquiring 0 JDBC connections;
    0 nanoseconds spent releasing 0 JDBC connections;
    0 nanoseconds spent preparing 0 JDBC statements;
    0 nanoseconds spent executing 0 JDBC statements;
    0 nanoseconds spent executing 0 JDBC batches;
    0 nanoseconds spent performing 0 L2C puts;
    0 nanoseconds spent performing 0 L2C hits;
    0 nanoseconds spent performing 0 L2C misses;
    0 nanoseconds spent executing 0 flushes (flushing a total of 0 entities and 0 collections);
    0 nanoseconds spent executing 0 partial-flushes (flushing a total of 0 entities and 0 collections)