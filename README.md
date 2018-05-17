# Turcu Lucian Andrei

# Architectural style: Layers + Client/Server

# Design Patterns: 
     Facade - A single controller class in the communication layer.
     More implemented by Spring in the background.
     TODO - more DPs in the client app.
                 
# Relationships: 
     Article -> Author: ManyToOne
     Message -> User: ManyToOne
     Prescription -> Medication: OneToMany
     Other OneToOne relationships between various classes.
               
# Frameworks: Spring Boot
# Maven
# Unit tests - TODO
# Input validation - TODO in client app.