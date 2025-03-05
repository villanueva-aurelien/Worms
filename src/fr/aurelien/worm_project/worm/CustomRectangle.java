package fr.aurelien.worm_project.worm;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import fr.aurelien.worm_project.Core.Drawable;
import fr.aurelien.worm_project.Core.Transform;
import fr.aurelien.worm_project.Core.World;
import fr.aurelien.worm_project.UI.MainWindow;
import fr.aurelien.worm_project.UI.PlayPanel;

public class CustomRectangle extends Rectangle implements Drawable
{
    public final static int _defaultWidth = 20;
    public final static int _defaultHeight = 20;
    private int _width;
    private int _height;

    private Color _colorFill = Color.BLUE;
    private Color _colorOutline = Color.CYAN;
    private Rectangle _rectangle;
    private final Transform TRANSFORM;
    private Color _defaultColor = null;
    private static long _counter = 1;
    private final long _id;
    

    public CustomRectangle(int x, int y)
    {
        this(_defaultWidth, _defaultHeight, x, y, Color.BLACK, Color.BLACK);
    }

    public CustomRectangle(int x, int y, Color inside, Color outside)
    {
        this(_defaultWidth, _defaultHeight, x, y, inside, outside);
        _defaultColor = inside;
    }

    /**
     * This constructor usable for create Fruit
     * @param width Size X
     * @param height Size Y
     * @param x Point X
     * @param y Point Y
     * @param c Color
     */
    public CustomRectangle(int width, int height, int x, int y, Color colorFill, Color colorOutline)
    {
        TRANSFORM = new Transform(new Point(x, y), 0, null);
        TRANSFORM.set_parent(MainWindow.get_World().get_Transform());
        _rectangle = new Rectangle(x, y, _width, _height);
        _width = width;
        _height = height;
        _colorFill = colorFill;
        _colorOutline = colorOutline;
        _id = _counter;
        _counter++;
        PlayPanel.addDrawableToList(this);
    }

    public void deletDrawableRectangle()
    {
        PlayPanel.removeDrawableToList(this);
    }
    
    public int get_Width()
    {
        return _width;
    }

    public void set_Width(int value)
    {
        _width = value;
    }

    public int get_Height()
    {
        return _height;
    }

    public void set_Height(int value)
    {
        _height = value;
    }

    public Transform getTransform()
    {
        return TRANSFORM;
    }

    public Color getDefaultColor()
    {
        return _defaultColor;
    }

    public void setDefaultColor(Color c)
    {
        _defaultColor = c;
    }

    public Color getColor()
    {
        return _defaultColor;
    }

    @Override
    public void draw(Graphics2D g)
    {
        /* _rectangle.setLocation(new Point((int)(TRANSFORM.get_point().getX()), (int)(TRANSFORM.get_point().getY())));
        g.setColor(_colorFill);
        g.fill(_rectangle);
        g.setColor(_colorOutline);
        //g.setStroke(new BasicStroke(_outlineSize));
        g.draw(_rectangle); */

        g.setColor(_colorFill);
        g.fillRect(this.getTransform().absolutTransform().get_point().x, this.getTransform().absolutTransform().get_point().y, _width, _height);
        //g.setStroke(new BasicStroke(0)); // permet de definir la taille du contour
        g.setColor(_colorOutline);
        g.drawRect(this.getTransform().absolutTransform().get_point().x, this.getTransform().absolutTransform().get_point().y, _width, _height);
    }

    @Override
    public long getIdentifiant() 
    {
        return _id;
    }

}
