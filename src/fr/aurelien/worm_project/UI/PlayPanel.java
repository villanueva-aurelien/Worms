package fr.aurelien.worm_project.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;
import fr.aurelien.worm_project.Core.Drawable;
import fr.aurelien.worm_project.Core.IUpdatable;

public class PlayPanel extends JPanel implements IUpdatable, Drawable
{
    private static int _height = 600;
    private static int _width = 800;
    private Rectangle _zone;
    private long _updateNumber = 0;
    private final long _id;
    private static long _counter = 0;
   

    private static ArrayList<Drawable> _drawList;

    public PlayPanel()
    {
        this.setPreferredSize(new Dimension(_width, _height));
        this.setOpaque(false);

        _drawList = new ArrayList<>();
        _drawList.add(this);

        //_zone = new Rectangle(400, 400);
        _id = _counter++;
        MainWindow.startTimer();
        MainWindow.addUpdatableToList(this);   
    }
    
    @Override
    public void update()
    {
        
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;     

        g2.setColor(new Color(0, 0, 0, 100));
        g2.fillRect(0, 0, getWidth(), getHeight());

        //g2.dispose();

        for(Drawable d:_drawList)
            d.draw(g2);

        
    }
    
    public static ArrayList<Drawable> getDrawList()
    {
        return _drawList;
    }
    
    public static void addDrawableToList(Drawable p)
    {
        _drawList.add(p);
    }

    public static void removeDrawableToList(Drawable p)
    {
        for(int i = 0; i < _drawList.size()+1; i++)
        {
            if(p.getIdentifiant() == _drawList.get(i).getIdentifiant())
            {
                _drawList.remove(i);
                return;
            }
        }
        
    }

    public static int getPlayWidth()
    {
        return _width;
    }

    public static int getPlayHeight()
    {
        return _height;
    }

    

    @Override
    public void draw(Graphics2D g)
    {
        //g.drawImage(null, 0, 0, null);
        //g.setColor(Color.WHITE);
        //g.fillRect(MainWindow.get_World().get_Transform().absolutTransform().get_point().x, MainWindow.get_World().get_Transform().absolutTransform().get_point().y, _width, _height);
        g.setColor(null);
        g.drawRect(MainWindow.get_World().get_Transform().absolutTransform().get_point().x, MainWindow.get_World().get_Transform().absolutTransform().get_point().y, _width, _height);
        //g.draw(_zone);
    }



    @Override
    public long getIdentifiant() 
    {
        return _id;
    }

    @Override
    public String toString()
    {
        return "playpanel" + _id;
    }
}

