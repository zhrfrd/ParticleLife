# ParticleLife
ParticleLife is my revisitation in Java of [Particle Life Simulation](https://github.com/hunar4321/particle-life?tab=readme-ov-file) by Hunar Ahmad.
## TODO
- [ ] Improve simulation rules. Find out the code is currently using 2 colors when the total colors are 5.
- [ ] Reduce overall speed of particles.
- [ ] SimulationPanel and ControlsPanel both have the `setSimulation(simulation)` method. Refactor code in order to have only 1 method declaration somewhere. Maybe create a superclass for the panels. 
- [ ] Assign the correct access modifier to class fields.
- [X] Create getter and setter methods rather than accessing class fields directly.
- [x] Delete unused ActionListener from ControlsPanel or SimulationPanel.
- [ ] Improve JPanel layouts.
- [x] Create a proper "game loop" to run the simulation.
- [ ] Improve input handling.
- [ ] Improve particles movement precision.
- [ ] Fix particles exiting the panel border.
- [ ] Customizable number of particles through user input.
- [ ] Customizable number of rules through user input.
- [ ] Find better way to update JFrame title without having to hard-code the title string in the game-loop.