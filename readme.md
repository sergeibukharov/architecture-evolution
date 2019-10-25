1. MVC as an architecture of the whole project.
controllers contains business logic
model - entity for hibernate and DTO for API
view - is really complicated

2. MV Service
business logic moved from controllers to services

3. MVC -> Service
MVC is separated layer which responsible only for presentation
Service is still responsible for all the business logic