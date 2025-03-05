package fr.aurelien.worm_project.Core;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import fr.aurelien.worm_project.worm.Worm.Direction;

public class Keybord implements KeyListener, IPublisher
{
    private Component _comp;
    private String _channelName = "direction";

    public Keybord(Component frame)
    {
        Observer.getInstance().createChannel(this, _channelName);
        
        _comp = frame;
        _comp.addKeyListener(this);
    }
    
    @Override
    public void keyTyped(KeyEvent e)
    {
        
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        
    }

    public void destroy()
    {
        Observer.getInstance().destryChannel(this, _channelName);
        _comp.removeKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_LEFT)
        {
            Observer.getInstance().publish(this, _channelName,  Direction.gauche);
        }
        else if(key == KeyEvent.VK_RIGHT)
        {
            Observer.getInstance().publish(this, _channelName,  Direction.droite);
        }
        else if(key == KeyEvent.VK_UP)
        {
            Observer.getInstance().publish(this, _channelName,  Direction.haut);
        }
        else if(key == KeyEvent.VK_DOWN)
        {
            Observer.getInstance().publish(this, _channelName,  Direction.bas);
        } 
    }
    
}
