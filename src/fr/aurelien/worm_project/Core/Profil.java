package fr.aurelien.worm_project.Core;

public class Profil 
{
    private String _name;
    private int _score;
    
    public Profil()
    {
        this("tot", 10);
    }

    public Profil(String name, String score)
    {
        this(name, Integer.getInteger(score));
    }

    public Profil(String name, int score)
    {
        _name = name;
        _score = score;

    }

    public String get_name()
    {
        return _name;
    }
    
    public int get_score()
    {
        return _score;
    }
}
