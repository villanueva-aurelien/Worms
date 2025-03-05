package fr.aurelien.worm_project.Core;

import java.awt.Point;

public class Transform
{
    private Point _point;
    private float _rotate;
    private Transform _parent;  // transforme du parent
    

    public Transform(Point p, float f, Transform t)
    {
        _rotate = f;
        _parent = t;
        _point = p;
    }

    public Transform(Point p)
    {
        this(p, 0, null);
    }

    public Transform absolutTransform()
    {
        Transform t = new Transform(_point, _rotate, _parent);
        while(t.get_parent() != null)
        {
            t.set_point(t.get_point().getX() + t.get_parent().get_point().getX(), t.get_point().getY() + t.get_parent().get_point().getY());
            t.set_rotate(t.get_parent().get_rotate() + t.get_rotate());
            t.set_parent(t.get_parent().get_parent());
        }
        return t;
    }

    public Boolean checkPoint(Point p)
    {
        if(p.x == _point.x && p.y == _point.y)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    //----------------------------------------------------------------

    public Point get_point()
    {
        return _point;
    }

    public void set_point(Point p)
    {
        _point = p;
    }

    public void set_point(double a, double b)
    {
        _point = new Point((int)(a),(int) (b));
    }

    //-------------------------------------

    public float get_rotate()
    {
        return _rotate;
    }

    public void set_rotate(float f)
    {
        _rotate = f;
    }

    //----------------------------------------

    public Transform get_parent()
    {
        return _parent;
    }

    public void set_parent(Transform t)
    {
        _parent = t;
    }
}
