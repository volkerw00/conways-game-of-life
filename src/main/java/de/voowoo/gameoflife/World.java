package de.voowoo.gameoflife;

import java.util.Arrays;

class World
{
    static class WorldBuilder
    {
        private boolean[][] field = new boolean[2][2];

        WorldBuilder cell(int posX, int posY)
        {
            boolean[][] newField = new boolean[posX + 3 > field.length ? posX + 3 : field.length][posY + 3 > field[0].length ? posY + 3 : field[0].length];
            copyValues(field, newField);
            newField[posX + 1][posY + 1] = true;
            field = newField;
            return this;
        }

        private static void copyValues(boolean[][] srcField, boolean[][] destField)
        {
            for (int x = 1; x < srcField.length - 1; x++)
            {
                System.arraycopy(srcField[x], 1, destField[x], 1, srcField[x].length - 1);
            }
        }

        World build()
        {
            return new World(field);
        }

        WorldBuilder(int x, int y)
        {
            this.field = new boolean[x + 2][y + 2];
        }
    }

    boolean[][] field;

    World(boolean[][] field)
    {
        this.field = field;
    }

    static boolean nextCellState(boolean cell, int neighbors)
    {
        return neighbors == 3 || (cell && (neighbors >= 2 && neighbors < 4));
    }

    static int countNeighbors(int posX, int posY, boolean[][] field)
    {
        int count = 0;
        for (int x = posX - 1; x < posX + 2; x++)
        {
            for (int y = posY - 1; y < posY + 2; y++)
            {
                if (field[x][y])
                {
                    count++;
                }
            }
        }
        return field[posX][posY] ? count - 1 : count;
    }

    void cycle()
    {
        boolean[][] next = new boolean[field.length][field[0].length];
        for (int x = 1; x < field.length - 1; x++)
        {
            for (int y = 1; y < field[0].length - 1; y++)
            {
                next[x][y] = nextCellState(field[x][y], countNeighbors(x, y, field));
            }
        }
        field = next;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        World world = (World) o;
        return Arrays.deepEquals(field, world.field);
    }

    @Override
    public int hashCode()
    {
        return Arrays.hashCode(field);
    }

    @Override
    public String toString()
    {
        return Arrays.deepToString(field);
    }

    static WorldBuilder builder(int x, int y)
    {
        return new WorldBuilder(x, y);
    }
}