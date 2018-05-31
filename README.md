# Turcu Lucian Andrei

# Architectural style: Layers + Client/Server

# Design Patterns: 
     Facade - A single controller class in the communication layer.
     More implemented by Spring in the background.
     Builder - DTO classes in the client implement an inner Builder class.
                 
# Relationships: 
     Message -> User: ManyToOne
     Prescription -> Medication: OneToMany
     Other OneToOne relationships between various classes.
               
# Frameworks: Spring Boot
# Maven
# Unit tests - for login and makeAppointment
# Input validation
    Done by the Android UI and the checks in the client and server implementations.