# PetClinic Phase 5

#### Overview
In this phase of the project, we convert the code from phase 4 to a microservice design by breaking our phase 4 code into 5 different projects. 

[PetClinic Reference Application](http://petclinic.cognizantacademy.com)

#### What You Are Given
* REST code from phase 4.

#### What You Need To Complete
* Break each of the controller sequences into separate REST services.
* Copy the model classes to the model package in each of the projects.

###### Frameworks Used
* Spring
* Spring Boot
* Spring Data JPA
* Hibernate
* Spring REST

###### Activity 1
##### Building a REST Client
* Modify your REST client from phase 4 to consume all of the different endpoints from each of your new REST services.
* Use existing models as templates.

###### Bonus Activity 1 (hard)
##### Building a Library
* Create a Java library for all of your models.
* Name your library 'petclinic-models'.
* Add a dependency in each of your microservices to your new library.
* Remove all models from your microservices.

#### Notes
* Dependencies have already been added for you in the pom.xml file for this phase.  You shouldn't have to modify or add anything to your build file (pom.xml), but feel free to as needed.

