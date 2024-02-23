### Simulation 👨‍💻
This project is a simulation of a 2D world that illustrates the interaction between participants in the IT labor market in 2023.
___
### Description 📝
All actions take place on a grid. There are 10 rows and 9 columns. 

The simulation contains three types of immovable entities:

- 🏠 houses 
- 🌳 trees
- 💰 venture capital funds' money

There are also three types of entities that can move:

- 🧔 employers
- 👶 juniors
- 🎓 programming courses

The empty cells are filled with a black circle: ⚫

Example of a filled grid:

<img src="https://github.com/anton-kulakov/Simulation/assets/145704392/189cfa3c-149d-417f-864d-aafe4a04ec84" alt="ыскуутырще" width="370" height="370">

___
### Mechanics of movements ⚙
- 🧔 employers are looking for money -> 💰
- 👶 juniors are looking for employers, they need a job and the resources of an employer -> 🧔
- 🎓 programming courses are looking for juniors, they need new students -> 👶

One step can be spent:
- moving towards the target 🏃‍♂️🏃‍♀️
- attacking the target in an neighboring cell 🧍🤺

Each step towards the target requires energy and reduces HP of movable entities 📉

Entity can move vertically, horizontally and diagonally. But they cannot pass through cells that are occupied by other entities. 
___
### Attack mechanics 🔫
There can be three situations where an entity finds a target in an adjacent cell:

- an employer (🧔) finds money (💰): the money disappears from the grid. The employer's HP is rising up 🔋

- a junior (👶) finds an employer (🧔): the employer's HP is decreasing

- a programming cours (🎓) finds a junior (👶): the junior's HP is decreasing. If the junior's HP is above a certain value, then the course creates another new junior in a randomly selected cell. And the programming course's HP is rising up
___
### Disappearing and adding entities ☠
When the HP of an entity reaches zero, it disappears from the grid.

On each new turn, the simulation can add a random number of entities from 0 to 3 inclusive for each type of entity to randomly selected cells. This applies to the following entities:
- 💰 money
- 🧔 employers
- 👶 juniors
- 🎓 programming courses
___
### Route finding
Movable entities use the A* (A star) algorithm to find a route to the nearest target.

The algorithm in this project was implemented based on the description that you can find [here](https://vitalissius.github.io/A-Star-Pathfinding-for-Beginners/).
___
### GIF-demonstration 📺
The program has a console view. Pressing the Enter key causes the simulation to pause/resume. Pressing the e + Enter keys ends the program
![3](https://github.com/anton-kulakov/Simulation/assets/145704392/be40202f-80d5-4cd6-af21-5ad5842464a9)
