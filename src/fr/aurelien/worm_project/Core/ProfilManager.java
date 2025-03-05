package fr.aurelien.worm_project.Core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ProfilManager
{
    private ArrayList<Profil> _listProfils = new ArrayList<>();

    public ProfilManager()
    {

    }

    public void createProfil(String name, int score)
    {
        Profil p = new Profil(name, score);
        addProfil(p);
    }

    private void sortListProfil()
    {
        Collections.sort(_listProfils, new Comparator<Profil>()
        {
            @Override
            public int compare(Profil p1, Profil p2)
            {
                if(p1.get_score() < p2.get_score()) return 1;
                else if(p2.get_score() < p1.get_score()) return -1;
                else return 0;
            }       
        });
    }
    
    /**
     * This method add Profil to ArrayList<Profil>
     * @param p     is Profil
     */
    public void addProfil(Profil p)
    {
        _listProfils.add(p);
        if(_listProfils.size() >= 2)
            sortListProfil();
    }

    /**
     * This method add to ArrayList<Profil> this ArrayList<Profil>
     * @param list     is ArrayList<Profil>
     */
    public void transfertList(ArrayList<Profil> list)
    {
        if(list.isEmpty())
        {
            return;
        }
        
        _listProfils.addAll(list);
    }
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public ArrayList<Profil> getListProfil()
    {
        return _listProfils;
    }

    public void setListProfil(ArrayList list)
    {
        _listProfils.addAll(list);
    }
}
