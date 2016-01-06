# Snake - Lanterna Game

A small snake game to showcase MVC, Multithreading and the Lanterna Library

This showcases many of the topics covered in my tutorial. I will later on delete this repo 
and add it to https://github.com/mathiasquintero/RandomStuff

Here's the UML Model:

![Modell Not available](https://github.com/mathiasquintero/Snake/blob/master/UML-Modell.png "UML-Model")

In case you're wondering what is part of the MVC-Pattern:

![Modell Not available](https://github.com/mathiasquintero/Snake/blob/master/MVC.png "UML-Model")

By the UML Model we see that the Field inherits from both the GameObject and the GameObjectObserver. That's because it acts as an adapter between the other Model Classes and the Controller. The Field listens for changes in the other Model Classes and re-routes them to the it's own observers, i.e. the Controller. And the other way around, it receives Commands from the controller, to change the model and re-routes those to the Snake, i.e. to change the Trajectory of the Snake.

![Modell Not available](https://github.com/mathiasquintero/Snake/blob/master/Relationships.png "Non-UML Model")