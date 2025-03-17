package fr.aurelien.worm_project.UI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import fr.aurelien.worm_project.LoadProfilJson;
import fr.aurelien.worm_project.Core.IUpdatable;
import fr.aurelien.worm_project.Core.Keybord;
import fr.aurelien.worm_project.Core.ProfilManager;
import fr.aurelien.worm_project.Core.SoundMusic;
import fr.aurelien.worm_project.Core.World;

public class MainWindow
{
    private static CopyOnWriteArrayList<IUpdatable> _updatList = new CopyOnWriteArrayList<>();
    private static JFrame _jframe;
    private static JPanel _currentPanel;
    private static Timer _timer = new Timer();
    private static int _periodnumber = 1000;
    private static World _world;
    private static ProfilManager _PM = new ProfilManager();
    private static Keybord _key;
    

    public MainWindow()
    {
        configJFrame();       
        addImage();
        addMenuPanel();     
        loadJSonfile();
    }

    private void configJFrame()
    {
        _jframe = new JFrame();
        _jframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        _jframe.setAlwaysOnTop(false);
        _jframe.setExtendedState(_jframe.MAXIMIZED_BOTH);
        _jframe.setUndecorated(false);
        _jframe.setLayout(new BorderLayout());

        _jframe.addWindowListener(new WindowAdapter()
       {
           @Override
           public void windowClosing(WindowEvent e)
           {
               int option = JOptionPane.showConfirmDialog(
                       _jframe,
                       "Êtes-vous sûr de vouloir fermer la fenêtre ?",
                       "Confirmation",
                       JOptionPane.YES_NO_OPTION);

               if(option == JOptionPane.YES_OPTION)
               {
                    // mettre ici la commande a executé avant fermeture!
                    //_jf.dispose();
                   
                    System.exit(0);
               }
           }
       }); 

       // Ajout d'un listener pour recentrer en cas de redimensionnement
       _jframe.addComponentListener(new ComponentAdapter() 
       {
           @Override
           public void componentResized(ComponentEvent e) 
           {
               centerFrame();
           }
       });

       _jframe.setFocusable(true);
       _jframe.setLocationRelativeTo(null);
       _jframe.setVisible(true);
       SoundMusic music = new SoundMusic();
       music.playMusic("G:\\prog\\Java\\projet\\Worms\\Enterin.The.Skies.mp3");
    }

    private void addMenuPanel()
    {
        MenuPanel mp = new MenuPanel();
       _currentPanel = mp;
       _jframe.add(_currentPanel, BorderLayout.CENTER);
       _jframe.pack();
    }

    private void addImage()
    {
        _jframe.setContentPane(new BackGround("chat.png"));
        _jframe.pack();
    }

    private void loadJSonfile()
    {
        LoadProfilJson l = new LoadProfilJson();
        l.chargerContenuJSON();
    }

    private static void centerFrame() 
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - _jframe.getWidth()) / 2;
        int y = (screenSize.height - _jframe.getHeight()) / 2;
        _jframe.setLocation(x, y);
    }

    public static void stopTimer()
    {
        PanelScore ps = new PanelScore(World.get_FinalScore());
        updateJFrame(ps);
        _timer.cancel();
        _world.finish();
        _key.destroy();
    }
    
    public static void startGame()
    {
        _world = null;
        _key = new Keybord(_jframe);
       _updatList.clear();
       PlayPanel pp = new PlayPanel();   
       pp.setOpaque(false);     
       updateJFrame(pp);
       _world = new World();
       _world.init();
    }

    public static void startTimer()
    {
        Timer t = new Timer();
        t.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                for(IUpdatable i : _updatList)
                {
                    i.update();
                }
                _jframe.repaint();
            }
        }, 0, _periodnumber);
        _timer = t;
    }

    public static void updateJFrame(JPanel j) {
        if (j == null) 
        {
            return;
        }
    
        if (_currentPanel != null)
        {
            _jframe.getContentPane().remove(_currentPanel);
        }
    
        _jframe.getContentPane().add(j);
        _currentPanel = j;
    
        _jframe.getContentPane().revalidate(); 
        _jframe.getContentPane().repaint();
        _jframe.pack();       // Ajuste la taille de la fenêtre si nécessaire
        _jframe.setVisible(true); // S'assure que la fenêtre est affichée
    }

    public static JFrame getJFrame()
    {
        return _jframe;
    }
    
    public static void addUpdatableToList(IUpdatable o)
    {
        _updatList.add(o);
    }

    public static void removeUpdableList(IUpdatable o)
    {
        _updatList.remove(o);
    }

    public static int getMainWindowWidth()
    {
        return _jframe.getWidth();
    }

    public static int getMainWindowHeight()
    {
        return _jframe.getHeight();
    }

    public static World get_World()
    {
        return _world;
    }

    public static ProfilManager getProfilManager()
    {
        return _PM;
    }

    public static void setProfilManager(ArrayList<ProfilManager> list)
    {
        _PM.setListProfil(list);
    }

    public static void setPeriudNumber(int i)
    {
        _periodnumber = i;
    }
}
