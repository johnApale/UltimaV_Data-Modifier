# UltimaV_Data-Modifier
This program will manipulate the data of the Ultima V game by overwriting the hex of saved game files.

Opening the program will send the user to a GUI. 
The user will then be required to upload their local SAVED.GAM file to make changes to the game.
The user has full control over the amount of changes they want to add/subtract per character of the game. 
The max value button sets the values of all fields to their max value.
The user will be required to restart the game everytime they make changes with the program.

The user will then be able to change the following stats/attributes:
- Strength
- Dexterity
- Intelligence
- Current & Max HP
- Experience Points

The user will also be able to add the following items to their inventory:
- Gold
- Skull Keys
- Keys
- Gems
- Black Badge
- Magic Carpets
- Magic Axes

##Update (03/20/21):

- added a listener to read file and print existing values to the correlating fields per character.
- When the character changes, the fields changes as well
- added catches to prevent user from selecting files other than .GAM files
- requires user to select file before selecting character
- modified the GUI to separate inventory and attributes
- used grids instead of free placement, for cleaner look
- restricted resizability of window
