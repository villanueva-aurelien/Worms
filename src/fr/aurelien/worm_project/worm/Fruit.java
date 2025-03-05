package fr.aurelien.worm_project.worm;

import java.awt.Color;
import java.awt.Point;
import fr.aurelien.worm_project.Core.IUpdatable;
import fr.aurelien.worm_project.Core.Transform;
import fr.aurelien.worm_project.UI.MainWindow;

public class Fruit implements IUpdatable
{
    private Color[] _color = {new Color(0, 0, 0, 0), Color.BLACK, Color.WHITE, Color.YELLOW, Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.PINK, Color.LIGHT_GRAY, new Color(102, 0, 102) };
    private final Transform TRANSFORM;
    private final static int _lenght = 1;

    private int[][][] _tabFruit;
    private int _selectedFruit;

    private static int _lenghFruit = 20;

    private CustomRectangle[][] _tabRectangle;

    public Fruit()
    {
        this(0, 0);
    }

    public Fruit(int x, int y)
    {
        this(new Point(x, y));
    }

    public Fruit(Point p)
    {
        TRANSFORM = new Transform(p, 0, null);
        TRANSFORM.set_parent(MainWindow.get_World().get_Transform());
        _tabRectangle = new CustomRectangle[_raisin.length][_raisin.length];

        createFruit();
        MainWindow.addUpdatableToList(this);
    }

    private void addedTabFruit()
    {
        _tabFruit = new int[3][][];
        _tabFruit[0] = _apple;
        _tabFruit[1] = _poire;
        _tabFruit[2] = _raisin;
    }

    private int selectFruitSpawn()
    {
        int a = (int)(Math.random()*3);
        return a;
    }

    private void createFruit()
    {
        if(_tabFruit == null)
            addedTabFruit();
        
        _selectedFruit = selectFruitSpawn();
        
        for(int i = 0; i < _tabFruit[_selectedFruit].length; i++)
        {
            for(int j = 0; j < _tabFruit[_selectedFruit][i].length; j++)
            {
                CustomRectangle r = new CustomRectangle(_lenght, _lenght, j, i, _color[_tabFruit[_selectedFruit][i][j]], _color[_tabFruit[_selectedFruit][i][j]]);
                r.getTransform().set_parent(TRANSFORM);
                _tabRectangle[i][j] = r;
            }
        }
    }

    public Point getLocationFruit()
    {
        return _tabRectangle[0][0].getTransform().get_point();   //_listFruit.get(index)._tabRectangle[0][0].getLocation();
    }
    
    public void destroyFruit()
    {
        for(int i = 0; i < _tabRectangle.length; i ++)
        {
            for(int j = 0; j < _tabRectangle[i].length; j++)
            {
                if(_tabRectangle[i][j] == null)
                    continue;

                _tabRectangle[i][j].deletDrawableRectangle();
            }
        }
        MainWindow.removeUpdableList(this);
    }

    public Transform get_Transform()
    {
        return TRANSFORM;
    }

    public static int getlenghFruit()
    {
        return _lenghFruit * _lenght;
    }

    // ---------------------------------------------------------------------------------------------
    
    private int[][] _raisin = {
        {0,0,0,0,0,6,1,1,10,10,10,1,1,1,1,1,0,0,0,0},
        {0,0,0,0,0,6,1,10,10,10,10,10,1,10,10,10,1,0,0},
        {0,0,0,0,0,6,1,10,10,10,10,10,1,10,10,10,10,1,0,0},
        {0,0,0,1,1,1,1,10,10,10,10,10,1,10,10,10,10,1,0,0},
        {0,0,1,10,10,10,1,10,10,10,10,1,1,10,10,1,1,1,0,0},
        {0,1,10,10,10,10,10,1,1,1,1,10,1,1,1,10,10,10,1,0},
        {0,1,10,10,10,10,10,1,10,10,10,10,10,1,10,10,10,10,10,1},
        {0,1,10,10,10,10,10,1,10,10,10,10,10,1,10,10,10,10,10,1},
        {0,0,1,10,10,10,1,1,10,10,10,10,10,1,10,10,10,10,10,1},
        {0,0,0,1,1,1,1,1,1,10,10,10,1,1,1,10,10,10,1,0},
        {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
        {0,0,0,0,1,10,10,10,1,1,10,10,10,10,1,1,0,0,0,0},
        {0,0,0,1,10,10,10,10,10,1,10,10,10,10,10,1,0,0,0,0},
        {0,0,0,1,10,10,10,10,10,1,10,10,10,10,10,1,0,0,0,0},
        {0,0,0,1,10,10,10,10,10,1,10,10,10,10,10,1,0,0,0,0,},
        {0,0,0,0,1,10,10,10,10,1,1,10,10,1,1,0,0,0,0,0},
        {0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,1,10,10,10,1,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,1,10,10,10,10,10,1,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,1,10,10,10,10,10,1,0,0,0,0,0,0,0}
        };

        private int[][] _poire = {
            {0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,1,7,7,7,7,1,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,1,7,7,7,7,7,7,1,0,0,0,0,0,0},
            {0,0,0,0,0,0,1,7,7,7,7,7,7,1,0,0,0,0,0,0},
            {0,0,0,0,0,0,1,7,7,7,7,7,7,1,1,0,0,0,0,0},
            {0,0,0,0,0,1,7,7,7,7,7,7,7,7,1,0,0,0,0,0},
            {0,0,0,0,0,1,7,7,7,7,7,7,7,7,1,0,0,0,0,0},
            {0,0,0,0,1,7,7,7,7,7,7,7,7,7,7,1,0,0,0,0},
            {0,0,0,1,7,7,7,7,7,7,7,7,7,7,7,7,1,0,0,0},
            {0,0,1,7,7,7,7,7,7,7,7,7,7,7,7,7,7,1,0,0},
            {0,0,1,7,7,7,7,7,7,7,7,7,7,7,7,7,7,1,0,0},
            {0,1,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,1,0},
            {0,1,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,1,0},
            {0,1,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,1,0},
            {0,0,1,7,7,7,7,7,7,7,7,7,7,7,7,7,7,1,0,0},
            {0,0,1,7,7,7,7,7,7,7,7,7,7,7,7,7,7,1,0,0},
            {0,0,0,1,7,7,7,7,7,7,7,7,7,7,7,7,1,0,0,0},
            {0,0,0,0,1,7,7,7,7,7,7,7,7,7,7,1,0,0,0,0},
            {0,0,0,0,0,1,7,7,7,7,7,7,7,7,1,0,0,0,0,0},
            {0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0}
            };

        private int[][] _apple = {
            {0,1,6,6,6,6,6,6,6,6,1,0,0,0,0,0,0,0,0,0},
            {0,1,6,6,6,6,6,6,6,6,6,1,0,0,0,0,0,0,0,0},
            {0,0,1,1,6,6,6,6,6,6,6,1,0,0,0,0,0,0,0,0},
            {0,0,0,0,1,1,6,6,6,6,6,1,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,1,1,1,1,6,1,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,1,1,1,1,6,1,1,1,0,0,0,0,0,0},
            {0,0,0,0,0,1,4,4,4,4,1,4,4,4,1,1,0,0,0,0},
            {0,0,0,1,1,4,4,4,4,4,4,4,4,4,4,4,1,0,0,0},
            {0,0,1,4,4,4,4,4,4,4,4,4,4,4,4,4,4,1,0,0},
            {0,1,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,1,0},
            {0,1,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,1,0},
            {0,1,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,1},
            {0,1,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,1},
            {0,1,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,1},
            {0,1,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,1},
            {0,0,1,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,1},
            {0,0,1,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,1,0},
            {0,0,0,1,4,4,4,4,4,4,4,4,4,4,4,4,4,1,0,0},
            {0,0,0,0,1,4,4,4,4,4,4,4,4,4,4,4,1,0,0,0},
            {0,0,0,0,0,1,1,4,4,4,4,4,4,4,4,1,0,0,0,0}
            };

        @Override
        public void update() 
        {
            
        }
}
