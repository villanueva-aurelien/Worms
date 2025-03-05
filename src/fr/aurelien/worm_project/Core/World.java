package fr.aurelien.worm_project.Core;

import java.awt.Point;

import fr.aurelien.worm_project.UI.MainWindow;
import fr.aurelien.worm_project.UI.MenuPanel;
import fr.aurelien.worm_project.UI.PlayPanel;
import fr.aurelien.worm_project.worm.Fruit;
import fr.aurelien.worm_project.worm.Worm;
import fr.aurelien.worm_project.worm.Worm.Direction;

public class World implements IUpdatable
{
    private final Transform TRANSFORM = new Transform(new Point(0, 0));
    //private Fruit_1 _fruit;
    private Worm _worm;
    private Fruit _fruit;
    private int _frame;
    private static int _score = 0;
    private static int _addPoint = 30;
    private int _tempScore = 0;
    private int _indexSpauwnFruit = 0;

    public World()
    {
        MainWindow.addUpdatableToList(this);
    }

    public void init()
    {
        _worm = new Worm();

        _fruit = new Fruit(selectSpawn());
    }

    public void finish()
    {
        _worm = null;
        _fruit = null;
    }

    public void createFruit()
    {
        Point p = selectSpawn();
        while(checkPositionToSpawnFruit(p) == false)
        {
            p = selectSpawn();
        }
        _fruit = new Fruit(p.x, p.y);
    }

    public void addPointWorm()
    {
        _score = _score + _addPoint;
    }

    public void calculateFinalScore()
    {
        _score = _score + _tempScore;
    }

    public Boolean checkPositionToSpawnFruit(Point p)
    {
        for(int i = 0; i < _worm.getListPoint().size(); i++)
        {
            if(_worm.getListPoint().get(i) == p)
            return false;
        }
        return true;
    }

    private void calculateIndexSpauwnFruit()
    {
        if(MenuPanel.getIndexDifficulty() == 1)
        {
            _indexSpauwnFruit = (int)(Math.random()*25+1);
        }
        else if(MenuPanel.getIndexDifficulty() == 2)
        {
            _indexSpauwnFruit = (int)((25+(Math.random())*(50-25)));
        }
        else
        {
            _indexSpauwnFruit = (int)((50+(Math.random()*(100 - 50))));
        }   
    }

    private Point selectSpawn()
    {
        int _spawnX = (int)(Math.random()*(PlayPanel.getPlayWidth()/20));

        if(_spawnX <= 0)
        {
            _spawnX = 0;
        }

        else if(_spawnX!=0 && _spawnX< PlayPanel.getPlayWidth())
        {
            _spawnX = _spawnX*20;
        }

        else if(_spawnX > PlayPanel.getPlayWidth() || _spawnX + Fruit.getlenghFruit() > PlayPanel.getPlayWidth())
            _spawnX = _spawnX - Fruit.getlenghFruit();

        int _spawnY = (int)(Math.random()*(PlayPanel.getPlayHeight()/20));
        
        if(_spawnY <= 0)
            _spawnY = 0;
        else if(_spawnY!=0 && _spawnY < PlayPanel.getPlayHeight())
        {
            _spawnY = _spawnY*20;
        }
        else if(_spawnY > PlayPanel.getPlayHeight() || _spawnY + Fruit.getlenghFruit() > PlayPanel.getPlayHeight())
        {
            _spawnY = _spawnY - Fruit.getlenghFruit();
        }
    
            return new Point(_spawnX, _spawnY);
        }
    
        public Transform get_Transform()
        {
            return TRANSFORM;
        }
    
        public Fruit get_Fruit()
        {
            return _fruit;
        }
    
        public void set_Fruit(Fruit f)
        {
            _fruit = f;
        }
    
        public static int get_FinalScore()
        {
            return _score;
        }

    @Override
    public void update() 
    {
        _frame++;
        if(_score > _addPoint*3 && _score % 2 == 0)
        {
            _worm.addWormCellule();
            _tempScore = _score;
            _score = 0;
        }

        if(_fruit == null && _frame >= _indexSpauwnFruit)
        {
            _fruit = new Fruit(selectSpawn());
            calculateIndexSpauwnFruit();
        }
    }

}
