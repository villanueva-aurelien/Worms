package fr.aurelien.worm_project.Core;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import java.io.FileInputStream;
import java.io.IOException;

public class SoundMusic
{

    private Boolean _playing = true;
    private float _volume = 1.0f;

    public SoundMusic()
    {

    }

    public void playMusic(String filePath) 
    {
        new Thread(() -> 
        {
            while (_playing) 
            { // Boucle infinie
                try (FileInputStream fileStream = new FileInputStream(filePath)) 
                {
                    AdvancedPlayer player = new AdvancedPlayer(fileStream);
                    player.play(); // Joue la musique jusqu'à la fin
                }
                catch (JavaLayerException | IOException e) 
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void stopMusic() 
    {
        _playing = false;
    }

}
