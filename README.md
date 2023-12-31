# User Profile Platform
User Profile Platform is a system which handles end to end user profile flow like creation, updation, 
fetching of user profile.

## Modules
This is a mono repo which has multiple services, library, domain objects. 
Different modules are created keeping in mind separation of concern and re-usability.

1. **common**: This module has the common modules which can be used across multiple submodules.
   1. **domain**: It contains domain objects which can be used across different modules.
   2. **lib**: It contains utility libraries like kafka implementation, cache implementation, locking implementation.
      1. **cache**: This is used for caching.
      2. **communication**: This is used to send message to Kafka.
      3. **lock**: Locking mechanism for exclusive access to a shared resource.
3. **services**: This module contains the application services.
    1. **user-profile**: This service takes client requests, maintains profile history, profile request status.
        1. **api**: Open API specs
            1. **client**: This generates api interface for the clients.
            2. **server-stub**: This generates api interface for server controller.
        2. **dao**: This interacts with database
        3. **common**: This contains the common code like locking, caching, config property class, exception, etc.
        4. **domain**: This contains the domain objects of the service like entity classes (db classes).
    2. **product-registry**: This service registers the list of products subscribed by the customer.
    3. **user-profile-workflow**: This service is responsible for maintaining the workflows like create, update, etc.
    4. **profile-validator**:  This service is responsible for validation of user profile. This contains business logic of the profile validation.
