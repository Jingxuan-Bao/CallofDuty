## Call-of-Duty-Game
### Introduction
In the game, you serve as a soldier and your mission is destroying a 10x10 enemy base. You need to destroy all Targets in the base.
![image](https://user-images.githubusercontent.com/93689757/196526686-17e13543-eca5-400b-ae5b-883b3bc46b97.png)

### How to Play Call of Duty
Before the game begins, the program places all targets randomly in such a way that no buildings are immediately adjacent to each other, either horizontally, vertically, or diagonally. Take a look at the following diagrams for examples of legal and illegal placements. (Notes: Tanks and oil drums are not buildings, so they could be placed directly adjacent to each other, or to buildings.)
![image](https://user-images.githubusercontent.com/93689757/196527027-c5e8b200-8f1e-4100-916b-2ae302527753.png)
The player doesn’t know where the targets are. The base is initially covered by mist, and the initial display of the base to be printed to the console therefore shows a 10x10 array with a ‘.’ (a period) in every location.

**The player tries to hit the targets, by indicating a specific row and column number (r,c)**. The player has two weapons, a “rocket launcher” and a “missile”. An input of “q” will switch the weapon. You can shoot a “rocket launcher” 20 times and shoot a “missile” 3 times. The “rocket launcher” will hit one square at the specific coordinate. However, the “missile” will hit a 3x3 area with the coordinate as its center.

A target is “destroyed” when every square of the target has been hit. But tanks can withstand two hits! Thus, it takes 6 hits to destroy a headquarter and an armory, 3 for barracks, 2 for tanks and 1 for oil drums. When an oil drum or a tank is destroyed, it will explode and hit a 5x5 area around it. When an armory is destroyed, it will explode and hit a 6x7 (or 7x6) area around it. And the explosion can spread, i.e., an explosion can trigger another explosion.
![image](https://user-images.githubusercontent.com/93689757/196527138-747d242c-2a67-44dc-9de7-c66d612844a8.png)

**The object is to destroy all targets with as few shots as possible. If all targets are destroyed before you run out of ammunition, you WIN. Otherwise, you LOSE.**

### The topics covered in Java Programming
● Inheritance & overriding

● Access modifiers

● Abstract classes

● 2-dimensional arrays
