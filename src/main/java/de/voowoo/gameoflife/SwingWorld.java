package de.voowoo.gameoflife;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class SwingWorld extends JFrame
{
    private transient World world;

    SwingWorld(World world)
    {
        this.world = world;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(world.field.length * 20 + 20, world.field[0].length * 20 + 40);
        setVisible(true);
        setResizable(false);
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() ->
                                            {
                                                if(isDead(world))
                                                {
                                                    executorService.shutdownNow();
                                                }
                                                repaint();
                                                world.cycle();
                                            }, 0, 1, TimeUnit.SECONDS);
    }

    private boolean isDead(World world) {return Arrays.deepEquals(world.field, new boolean[world.field.length][world.field[0].length]);}

    @Override
    public void paint(Graphics g)
    {
        g.drawRect(30, 50, world.field.length * 20 - 40, world.field[0].length * 20 - 40);
        g.clearRect(31, 51, world.field.length * 20 - 41, world.field[0].length * 20 - 41);
        for (int x = 1; x < world.field.length - 1; x++)
        {
            for (int y = 1; y < world.field[0].length - 1; y++)
            {
                if (world.field[x][y])
                {
                    g.fillRect(10 + x * 20, 30 + y * 20, 20, 20);
                }
            }
        }
    }
}
