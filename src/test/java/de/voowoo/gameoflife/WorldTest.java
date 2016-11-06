package de.voowoo.gameoflife;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class WorldTest
{
    @Test
    public void aliveCellWith2NeighborsShouldSurvive() throws Exception
    {
        assertThat(World.nextCellState(true, 2), is(true));
    }

    @Test
    public void aliveCellWith3NeighborsShouldSurvive() throws Exception
    {
        assertThat(World.nextCellState(true, 3), is(true));
    }

    @Test
    public void aliveCellWithMoreThan3NeighborsShouldDie() throws Exception
    {
        assertThat(World.nextCellState(true, 4), is(false));
    }

    @Test
    public void deadCellWith2NeighborsShouldStayDead() throws Exception
    {
        assertThat(World.nextCellState(false, 2), is(false));
    }

    @Test
    public void deadCellWith3NeighborsShouldBeRevived() throws Exception
    {
        assertThat(World.nextCellState(false, 3), is(true));
    }

    @Test
    public void shouldCountNeighborsOfAliveCellInA3x3World() throws Exception
    {
        boolean[][] neighborhood = {{true, true, true},
                                    {true, true, true},
                                    {true, true, true}};
        assertThat(World.countNeighbors(1, 1, neighborhood), is(8));
    }

    @Test
    public void shouldCountNeighborsOfDeadCellInA3x3World() throws Exception
    {
        boolean[][] neighborhood = {{true, true, true},
                                    {true, false, true},
                                    {true, true, true}};
        assertThat(World.countNeighbors(1, 1, neighborhood), is(8));
    }

    @Test
    public void shouldCount0NeighborsInDead3x3World() throws Exception
    {
        boolean[][] neighborhood = {{false, false, false},
                                    {false, false, false},
                                    {false, false, false}};
        assertThat(World.countNeighbors(1, 1, neighborhood), is(0));
    }

    @Test
    public void shouldCount0NeighborsOnTheLeftSideInA4x3World() throws Exception
    {
        boolean[][] neighborhood = {{false, false, false},
                                    {false, false, false},
                                    {false, false, false},
                                    {true, true, true}};
        assertThat(World.countNeighbors(1, 1, neighborhood), is(0));
    }

    @Test
    public void shouldCount0NeighborsOnTheRightSideInA4x3World() throws Exception
    {
        boolean[][] neighborhood = {{true, true, true},
                                    {false, false, false},
                                    {false, false, false},
                                    {false, false, false}};
        assertThat(World.countNeighbors(2, 1, neighborhood), is(0));
    }

    @Test
    public void shouldCount8NeighborsOnTheRightSideInA6x3World() throws Exception
    {
        boolean[][] neighborhood = {{false, false, false},
                                    {false, false, false},
                                    {false, false, false},
                                    {true, true, true},
                                    {true, true, true},
                                    {true, true, true}};
        assertThat(World.countNeighbors(4, 1, neighborhood), is(8));
    }

    @Test
    public void shouldCount0NeighborsInTheMiddleInA5x3World() throws Exception
    {
        boolean[][] neighborhood = {{true, true, true},
                                    {false, false, false},
                                    {false, false, false},
                                    {false, false, false},
                                    {true, true, true}};
        assertThat(World.countNeighbors(2, 1, neighborhood), is(0));
    }

    @Test
    public void shouldCount0NeighborsOnTheTopInA3x4World() throws Exception
    {
        boolean[][] neighborhood = {{false, false, false, true},
                                    {false, false, false, true},
                                    {false, false, false, true}};
        assertThat(World.countNeighbors(1, 1, neighborhood), is(0));
    }

    @Test
    public void shouldCount0NeighborsOnTheBottomInA3x4World() throws Exception
    {
        boolean[][] neighborhood = {{true, false, false, false},
                                    {true, false, false, false},
                                    {true, false, false, false}};
        assertThat(World.countNeighbors(1, 2, neighborhood), is(0));
    }

    @Test
    public void shouldCount8NeighborsOnTheBottomInA3x6World() throws Exception
    {
        boolean[][] neighborhood = {{false, false, false, true, true, true},
                                    {false, false, false, true, true, true},
                                    {false, false, false, true, true, true}};
        assertThat(World.countNeighbors(1, 4, neighborhood), is(8));
    }

    @Test
    public void shouldCount0NeighborsInTheMiddleOfA5x5World() throws Exception
    {
        boolean[][] neighborhood = {{true, true, true, true, true},
                                    {true, false, false, false, true},
                                    {true, false, false, false, true},
                                    {true, false, false, false, true},
                                    {true, true, true, true, true}};
        assertThat(World.countNeighbors(2, 2, neighborhood), is(0));
    }

    @Test
    public void shouldApplyRulesToA5x5World() throws Exception
    {
        World world = new World(new boolean[][]{{false, false, false, false, false},
                                                {false, false, true, false, false},
                                                {false, false, false, true, false},
                                                {false, false, true, false, false},
                                                {false, false, false, false, false}});

        world.cycle();

        assertThat(world, is(new World(new boolean[][]{{false, false, false, false, false},
                                                       {false, false, true, false, false},
                                                       {false, false, true, true, false},
                                                       {false, false, true, false, false},
                                                       {false, false, false, false, false}})));
    }

    @Test
    public void worldShouldBlink() throws Exception
    {
        boolean[][] neighborhood = new boolean[][]{{false, false, false, false, false},
                                                   {false, false, false, false, false},
                                                   {false, true, true, true, false},
                                                   {false, false, false, false, false},
                                                   {false, false, false, false, false}};

        assertThat(World.countNeighbors(2, 1, neighborhood), is(1));

        World world = new World(new boolean[][]{{false, false, false, false, false},
                                                {false, false, true, false, false},
                                                {false, false, true, false, false},
                                                {false, false, true, false, false},
                                                {false, false, false, false, false}});

        world.cycle();

        assertThat(world, is(new World(new boolean[][]{{false, false, false, false, false},
                                                       {false, false, false, false, false},
                                                       {false, true, true, true, false},
                                                       {false, false, false, false, false},
                                                       {false, false, false, false, false}})));
        world.cycle();

        world = new World(new boolean[][]{{false, false, false, false, false},
                                                {false, false, true, false, false},
                                                {false, false, true, false, false},
                                                {false, false, true, false, false},
                                                {false, false, false, false, false}});


    }
}
