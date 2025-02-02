# Code Challenge

## Approach overview / assumptions

1. Make features as little as possible coupled. To be easily ready to deploy each one separately.
2. Don't care about hibernate performance - use Entities as direct mapping to database.
3. Separate layers in context of each feature individually.
4. Expected layers:
   * rest - exposition for REST access
   * entity - stores persistence model and data-access operations
   * domain - stores domain objects and related business logic. Layer domain and entity are not expected to fit 1:1. For example domain User class have no direct mapping on entity layer. Transformation responsibility lies on service side.
   * config - beans definition and app connfigurations. 
   * service - can be considered to be added but I would keep logic close to related classes to achieve separation on functionality/object level (user, post, wall, others) not responsibility (dao, service, dto, etc). Thanks to that approach I would expect code should be less coupled and separation will be more visible from packages perspective.
5. Converters provides functionality to map between layers. Each layer is expected to "talk" only with one above and one below.

Ideal solution may have:
* Features deployed separately as microservices
* ActiveMQ - communication between features
* Spring security and OpenID for authentication
* Docker images for each microservice
* Liquibase - for database schema changes tracking
* Consider MongoDB documents instead of SQL DB. At least for posts storage or other heavily accessing parts of data.

Unfortunately due to lack of time I didn't have an occasion to realize all my ideas.

## Objective

You have received this challenge as part of the recruiting process for HSBC. The contents of this exercise are confidential, so please do not distribute.

You have 7 days to complete this challenge but it shouldn't take you longer than a few hours. Please send it back as soon as you're done.

For developer code-challenge see: [README-DEVELOPERS.md](README-DEVELOPERS.md)

For frontend code-challenge see: [README-FRONTEND.md](README-FRONTEND.md)

For tester code-challenge see: [README-TESTERS.md](README-TESTERS.md)

For mobile code-challenge see: [README-MOBILE.md](README-MOBILE.md)
