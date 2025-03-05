package fr.aurelien.worm_project;

import java.io.File;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.reflect.Field;
import java.util.ArrayList;
import static fr.aurelien.worm_project.Core.Logger.log;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.aurelien.worm_project.Core.Profil;
import fr.aurelien.worm_project.UI.MainWindow;
import fr.aurelien.worm_project.UI.PanelScore;

public class SaveProfilJson
{
    ObjectMapper _objectMapper = new ObjectMapper();
    private ArrayList<Profil> _listeObjets;
  

    /**
     * This method save the content to ArrayList<Profil> of class PhoneBook
     * To file.JSON
     */
    public void saveJson()
    {
        try
        {
            //objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
            _objectMapper.writeValue(new File("TableScore.json"), MainWindow.getProfilManager().getListProfil());
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
