package fr.aurelien.worm_project.worm;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import fr.aurelien.worm_project.Core.ISubscriber;
import fr.aurelien.worm_project.Core.IUpdatable;
import fr.aurelien.worm_project.Core.Observer;
import fr.aurelien.worm_project.Core.Transform;
import fr.aurelien.worm_project.UI.MainWindow;
import fr.aurelien.worm_project.UI.PlayPanel;

public class Worm implements IUpdatable, ISubscriber
{
    private int _defaultSizeWorm = 8;
    private ArrayList<CustomRectangle> _listCustomR = new ArrayList<>();

    private Point _temporaryPoint = null;
    private int _addValueX = 0;
    private int _addValueY = 0;
    private Direction _direction = null;
    private Direction _previousDirection = null;
    private int _moveValue = 20;
    private final Transform TRANSFORM;
    private ArrayList<Point> _listPointWorm = new ArrayList<>();
    private int _valueColor = 0;
    private int cc = 0;
    private String _name = "direction";


    public Worm()
    {
        TRANSFORM = new Transform(new Point(PlayPanel.getPlayWidth()/2, PlayPanel.getPlayHeight()/2), 0, null);
        TRANSFORM.set_parent(MainWindow.get_World().get_Transform());
        createWorm();
        Observer.getInstance().subscribe(this, _name);

        MainWindow.addUpdatableToList(this);
    }

    private Color changeColor()
    {
         if(_listCustomR.isEmpty())
        {
            Color c = new Color(255, 0, 0,255);
            return c;
        }
        else
        {
            Color c = _listCustomR.getLast().getColor();
            changeValueColor(c);
            c = new Color(c.getRed()+_valueColor,0 ,0, 255);
            return c;
        }
    }

    private void changeValueColor(Color c)
    {
        if(_listCustomR.getLast().getColor().getRed() <= 30)
        {
            _valueColor = 30;
        }
        else if(_listCustomR.getLast().getColor().getRed() >= 240)
        {
            _valueColor = -30;
        }
    }

    private void createWorm()
    {
        int d = 0;
        for(int i = 0; i < _defaultSizeWorm; i++)
        {
            Color color = changeColor();
            CustomRectangle c = new CustomRectangle(PlayPanel.getPlayWidth()/2+d, PlayPanel.getPlayHeight()/2, color, color);
            d = d + c.get_Height();
            
            _listCustomR.add(c);
            _listPointWorm.addFirst(c.getTransform().get_point());
        }
    }

    public void checkPrevDirAndDirection()
    {
        if(_previousDirection != Direction.droite)
        {
            setDirection(Direction.gauche);
        }
        if(getPreviousDirection() != Direction.gauche)
        {
            setDirection(Direction.droite);
        }
        if(getPreviousDirection() != Direction.bas)
        {
            setDirection(Direction.haut);
        }
        if(getPreviousDirection() != Direction.haut)
        {
            setDirection(Direction.bas);
        } 
    }

    public enum Direction
    {
        gauche, droite, haut, bas
    }

    private void selectDirection()
    {
        if(_direction == null)
            return;
        
        _previousDirection = _direction;

        switch(_direction)
        {
            case gauche:
                _addValueX = - _moveValue;
                _addValueY = 0;
                break;

            case droite:
                _addValueX = _moveValue;
                _addValueY = 0;
                break;

            case haut:
                _addValueX = 0;
                _addValueY = - _moveValue;
                break;

            case bas:
                _addValueX = 0;
                _addValueY = _moveValue;
                break;
        }    
    }

    private Boolean checkBorder(int a, int b)
    {
        if(_addValueX == 0 && _addValueY == 0)
            return false;

        if(_listCustomR.get(0).getTransform().get_point().getX() + _listCustomR.get(0).get_Width() > a)
        {
            return true;
        }
        else if(_listCustomR.get(0).getTransform().get_point().getX() < 0)
        {
            return true;
        }
        else if(_listCustomR.get(0).getTransform().get_point().getY() + _listCustomR.get(0).get_Height() > b)
        {
            return true;
        }
        else if(_listCustomR.get(0).getTransform().get_point().getY() < 0)
        {
            return true;
        }

        return false;
    }

    private void newcheck(Transform a, Transform b)
    {
        if(_direction == Direction.gauche && a.get_point().getX() -_moveValue == b.get_point().getLocation().getX() && a.get_point().getY() + 0 == b.get_point().getLocation().getY())       
        {
            _direction = null;
            MainWindow.stopTimer();
        }
        else if(_direction == Direction.droite && a.get_point().getX() + CustomRectangle._defaultWidth == b.get_point().getLocation().getX() && a.get_point().getY() + 0 == b.get_point().getLocation().getY())       
        {
            _direction = null;
            MainWindow.stopTimer();  
        }
        else if(_direction == Direction.haut && a.get_point().getX() == b.get_point().getLocation().getX() && a.get_point().getY() - _moveValue == b.get_point().getLocation().getY())       
        {
            _direction = null;
            MainWindow.stopTimer();
        }
        else if(_direction == Direction.bas && a.get_point().getX() == b.get_point().getLocation().getX() && a.get_point().getY() + _moveValue == b.get_point().getLocation().getY())       
        {
            _direction = null;
            MainWindow.stopTimer();
        }
    }

    private void checkCollision()
    {
        for(int i = 1; i < _listCustomR.size(); i++)
        {
            newcheck(_listCustomR.get(0).getTransform(), _listCustomR.get(i).getTransform());
        }
    }

    private void moveWorm()
    {
        Point tempo = null;

        _temporaryPoint = getLocation(0);
        _listCustomR.get(0).getTransform().set_point(new Point((int)(_listCustomR.get(0).getTransform().get_point().getX()) + _addValueX, (int)(_listCustomR.get(0).getTransform().get_point().getY()) + _addValueY));
        _listPointWorm.addFirst(_listCustomR.get(0).getTransform().get_point());

        for(int i = 1; i < _listCustomR.size(); i++)
        {
            tempo = getLocation(i);
            _listCustomR.get(i).getTransform().set_point(new Point((int)(_temporaryPoint.getX()), (int) (_temporaryPoint.getY())));
            _listPointWorm.addFirst(_listCustomR.get(i).getLocation());
            _temporaryPoint = tempo;        

            while(_listPointWorm.size() > _defaultSizeWorm+1)
            {
                _listPointWorm.removeLast();
            }
        }
    }

    public void checkFruitWorm()
    {        
        if(MainWindow.get_World().get_Fruit() != null && getTransformRectangle(0).checkPoint(MainWindow.get_World().get_Fruit().get_Transform().get_point()) == true)
        {

            MainWindow.get_World().get_Fruit().destroyFruit();
            MainWindow.get_World().set_Fruit(null);
            MainWindow.get_World().addPointWorm();
        }  
    } 

    public void addWormCellule()
    {        
        Color color = changeColor();
        CustomRectangle c = new CustomRectangle((int)(_listPointWorm.getLast().getX()),(int)( _listPointWorm.getLast().getY()),color ,color);
        _listCustomR.addLast(c);
        _defaultSizeWorm =_defaultSizeWorm + 1;
    }

//---------------------------------------------------------------------------------------------------------------

    public Direction getPreviousDirection()
    {
        return _previousDirection;
    }

    public int getWitdth()
    {
        return this.getWitdth();
    }

    public int getHeight()
    {
        return this.getHeight();
    }
    
    public int getPosX(int index)
    {
        return (int)(_listCustomR.get(index).getX());
    }

    public int getPosY(int index)
    {
        return (int)(_listCustomR.get(index).getY());
    }

    public Transform getTransformRectangle(int index)
    {
        return _listCustomR.get(index).getTransform();
    }
    
    public Point getLocation(int index)
    {
        return _listCustomR.get(index).getTransform().get_point();
    }

    public void setDirection(Direction dir)
    {
        _direction = dir;
    }

    public Direction getDirection()
    {
        return _direction;
    }

    public Transform get_Transform()
    {
        return TRANSFORM;
    }

    public Point getListPointWorm(int index)
    {
        return _listPointWorm.get(index);
    }
    
    public ArrayList getListPoint()
    {
        return _listPointWorm;
    }

    

    @Override
    public void update()
    {
        
        selectDirection();

        if(_direction != null)
        {
            moveWorm();
        }

        checkCollision();
        

        if(checkBorder(PlayPanel.getPlayWidth(), PlayPanel.getPlayHeight()) == true)
        {
            MainWindow.get_World().calculateFinalScore();
            MainWindow.stopTimer();
        }
              
        checkFruitWorm();
    }

    @Override
    public void receiveNotification(String channelName, Object... payload) 
    {
        Object[] tab = payload.clone();
        switch (channelName) 
        {
            case "direction":
                      
                _direction=(Direction)tab[0];
                break;
        
            default:
                break;
        }
    }
}

