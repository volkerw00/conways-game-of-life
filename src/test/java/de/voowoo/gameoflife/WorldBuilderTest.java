package de.voowoo.gameoflife;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class WorldBuilderTest
{
    @Test
    public void shouldCreateA1x1WorldWithACellAt1x1() throws Exception
    {
        assertThat(World.builder(0, 0).cell(0, 0).build(), is(new World(new boolean[][]{{false, false, false},
                                                                                        {false, true, false},
                                                                                        {false, false, false}})));
    }

    @Test
    public void shouldCreateA3x3WorldWithACellAt0x0And2x2() throws Exception
    {
        assertThat(World.builder(0, 0)
                        .cell(0, 0)
                        .cell(2, 2)
                        .build(), is(new World(new boolean[][]{{false, false, false, false, false},
                                                               {false, true, false, false, false},
                                                               {false, false, false, false, false},
                                                               {false, false, false, true, false},
                                                               {false, false, false, false, false}})));
    }

    @Test
    public void shouldCreateA1x3WorldWithACellAt0x0And0x2() throws Exception
    {
        assertThat(World.builder(0, 0)
                        .cell(0, 0)
                        .cell(0, 2)
                        .build(), is(new World(new boolean[][]{{false, false, false, false, false},
                                                               {false, true, false, true, false},
                                                               {false, false, false, false, false}})));
    }

    @Test
    public void shouldCreateA3x1WorldWithACellAt0x0And2x0() throws Exception
    {
        assertThat(World.builder(0, 0)
                        .cell(0, 0)
                        .cell(2, 0)
                        .build(), is(new World(new boolean[][]{{false, false, false},
                                                               {false, true, false},
                                                               {false, false, false},
                                                               {false, true, false},
                                                               {false, false, false}})));
    }

    @Test
    public void shouldCreateA3x1WorldWithACellAt2x0And0x0() throws Exception
    {
        assertThat(World.builder(0, 0)
                        .cell(2, 0)
                        .cell(0, 0)
                        .build(), is(new World(new boolean[][]{{false, false, false},
                                                               {false, true, false},
                                                               {false, false, false},
                                                               {false, true, false},
                                                               {false, false, false}})));
    }

    @Test
    public void shouldCreateA3x3WorldWithNoCells() throws Exception
    {
        assertThat(World.builder(3, 3).build(), is(new World(new boolean[5][5])));
    }
}

