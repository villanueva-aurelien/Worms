package fr.aurelien.worm_project.Core;

import static fr.aurelien.worm_project.Core.Logger.log;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import fr.aurelien.worm_project.worm.Worm;
import fr.aurelien.worm_project.worm.Worm.Direction;

public class Keybord implements KeyListener, IPublisher
{
    private Component _comp;
    private Channel _channel;

    public Keybord(Component frame)
    {
        _channel = Observer.getInstance().createChannel(this, "direction");
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
        _comp.removeKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_LEFT)
        {
            _channel.publish(Direction.gauche);
        }
        else if(key == KeyEvent.VK_RIGHT)
        {
            _channel.publish(Direction.droite);
        }
        else if(key == KeyEvent.VK_UP)
        {
            _channel.publish(Direction.haut);
        }
        else if(key == KeyEvent.VK_DOWN)
        {
            _channel.publish(Direction.bas);
        } 
    }
    
}
