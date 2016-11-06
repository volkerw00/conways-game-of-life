package de.voowoo.gameoflife;

public class GameOfLife
{
    public static void main(String[] args)
    {
        new SwingWorld(pulsator());
    }

    private static World blinker() {return World.builder(3, 3).cell(1, 1).cell(1, 2).cell(1, 3).build();}

    private static World pulsator()
    {
        return World.builder(16, 11)
                    .cell(3, 5)
                    .cell(4, 5)
                    .cell(5, 4)
                    .cell(5, 6)
                    .cell(6, 5)
                    .cell(7, 5)
                    .cell(8, 5)
                    .cell(9, 5)
                    .cell(10, 4)
                    .cell(10, 6)
                    .cell(11, 5)
                    .cell(12, 5)
                    .build();
    }
}
