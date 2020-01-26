# Simulation Design Plan
### Team Number 13
### Names:

1. Franklin Boampong (fsb10)
2. Michelle Tai (mrt36)
3. Lucy Gu (wg74)

## Introduction

The goal of this program is to create a simulation animation capable of performing 
at least 4 different types of simulations; the user of the program should be able 
to interact with the program through keyboard or mouse input to stop, start, fast
forward through, or load new simulations. New types of simulations should be easy 
to add, so our code design must be flexible. Overall, our code should be most 
flexible in accepting new Cellular Automata (CA) simulations and in the playback 
of the simulation - i.e our code should consider each system as somewhat of a grid 
of cells such that the conditions of one cell is dependent on the surrounding cells 
in the system. 

The project should be organized in a way that has separate Model, 
View and Controllers to separate the data structures and applications from the 
visual display of information. Thus, we can encapsulate any front-end work or 
back-end work and keep them separate from each other. Things that should be open 
to extension and closed to modification are abstract classes like an overarching 
simulation type that has a way to display, a way to hold the data and cells information 
without having specific data structures tied to it, and encapsulate general behaviors 
of the CA simulation (like making new generations/simulations, etc). This helps us 
adhere to the open/close principle since any new types of simulations can extend 
this class without having to make changes to the original class itself. Also, in an 
effort to follow the open/close principle, we plan to have major subclasses which 
would be extended by subclasses. These main classes would have some simple method 
functions which all the subclasses might need. Pertaining to reading an XML file 
to layout contents of each simulation, a class that reads a general XML file would 
be used. In this case, we plan to implement class such that any File passed in an XML 
format could be read in by the class regardless of the type of simulation under consideration.

## Overview

![](CellImage.jpg "Cell class") 
![](GameViewClassImage.jpg "Game View class") 
![](SimulationImage.jpg "Simulation class") 
![](XML.jpg "XML class") 


## User Interface

“Load new File” allows user to load new XML file  
“Start” begins the animation (including initial begin)  
“Stop” pauses animation  
“Step” only activated when stopped? Step through each step  
“-” and “+” slow down/speed up the animation (only when simulation is started)  

An image showing the basic UI of the simulation view is shown below:

![](GUIPage.jpg "GUI") 



## Design Details


## Design Considerations

We decided to have one abstract Simulation class, and have all five different kinds of 
simulations (GoL, Percolate, Segregation, Wator, Fire) be separate classes that extend Simulation.  
Reading the XML file should be its own class, not a part of GameView.  
GameView should only take care of displaying animation, nothing related to the simulation logic  
Buttons shall have their own class  
We decide to have the buttons on the bottom of the screen and the error message on the top 
instead of the other way around  
No spacing between cells when displaying  


#### Components

The main components of the Simulation assignment are:
1. GameView Class - This is the basic UI screen for the simulation.
2. Simulation Class - Main engine of program 
    - We will have 5 more subclasses extending this abstract Simulation class.
3. Cell Class - Cells to Fill in Grid.
4. XML Class - Class to obtain data from XML file provided by user.


#### Use Cases
* Apply the rules to a middle cell: set the next state of a cell to dead by counting its number of neighbors using the Game of Life rules for a cell in the middle (i.e., with all its neighbors)
    * updateGrid() (simulation)  ⇒  checkNeighbourAndUpdate(cell, neighbourList) (sim) ⇒ updateCell (sim) ⇒ changeNext (cell)
* Apply the rules to an edge cell: set the next state of a cell to live by counting its number of neighbors using the Game of Life rules for a cell on the edge (i.e., with some of its neighbors missing)
    * updateGrid() (simulation)  ⇒  checkNeighbourAndUpdate(cell, neighbourList) (sim) ⇒ updateCell (sim) ⇒ changeNext (cell)  
* Move to the next generation: update all cells in a simulation from their current state to their next state and display the result graphically
    * updateState() (cell)
* Set a simulation parameter: set the value of a global configuration parameter, probCatch, for a simulation, Fire, based on the value given in an XML fire
    * getParameters() (xmlParser)
* Switch simulations: load a new simulation from an XML file, stopping the current running simulation, Segregation, and starting the newly loaded simulation, Wator
    * ifLoadFile() (gameView) && ifValidFile (xml) ⇒ new xmlParser(“newFileName”) ⇒ simulation = new Wator() (simulation)


## Team Responsibilities

 * Team Member #1 (Franklin Boampong)
	- Primary Role: Game View Class
	- Secondary Role: Fire

* Team Member #2 (Michelle Tai)
   -  Primary Role: XML class
   -  Secondary Role: Percolation

* Team Member #3 (Lucy Gu)
   - Primary Role: Cell, Simulation
   - Secondary Role: Game of Life, Segregation

Team Altogether: Work on Predator - Prey simulation together.



