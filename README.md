# SDM-Project-Minesweeper
A Java implementation of the classic Minesweeper game, featuring both a Command Line Interface (CLI) and a Graphical User Interface (GUI).

## Features

- Play through CLI or GUI
- Three predefined difficulty levels:
    - Easy
    - Medium
    - Hard
- Customizable grid size and mine count

## Requirements

- Java 25 or newer

## Build and Run

Clone the repository:

```bash
git clone https://github.com/matteocrosariol/SDM-Project-Minesweeper.git
cd SDM-Project-Minesweeper
```

Build the project with Gradle:

```bash
./gradlew build
```

Run the application:

```bash
./gradlew run
```



## How to Play

The goal of Minesweeper is to reveal all safe cells without opening a mine.

- Each cell may either contain a mine or be empty.
- Opening a mine immediately ends the game.
- Revealed cells display a number indicating how many adjacent cells contain mines.
- Empty cells with no adjacent mines automatically reveal neighboring cells.
- Players can place flags on cells suspected to contain mines.
- The game is won when all non-mine cells are revealed.

## CLI Commands

- `open row column`
- `o row column`
- `flag row column`
- `f row column`
- `help`
- `quit`
- `q`

## GUI Controls

- Left click: open a cell
- Right click: place/remove a flag
- Restart button: start a new game

## Difficulty Levels

| Difficulty | Rows | Columns | Mines |
|------------|------|---------|-------|
| Easy       | 9    | 9       | 10    |
| Medium     | 16   | 16      | 40    |
| Hard       | 30   | 16      | 99    |