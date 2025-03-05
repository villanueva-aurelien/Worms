package fr.aurelien.worm_project.UI;
import java.lang.System.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.aurelien.worm_project.SaveProfilJson;

import static fr.aurelien.worm_project.Core.Logger.log;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class MenuPanel extends JPanel
{
    private static int _height = 600;
    private static int _width = 800;
    private final int _easy = 1000;
    private final int _normal = 500;
    private final int _hard = 250;
    private GridBagConstraints _constraints = new GridBagConstraints();
    private static int _indexDifficulty = 0;
    
    public MenuPanel()
    {
        this.setLayout(new GridLayout(4, 1));
        this.setPreferredSize(new Dimension(_width, _height));

        createAllButton();

    }

    private void createAllButton()
    {
        JButton jbEasy = new JButton("EASY");
            
            _constraints.fill = GridBagConstraints.HORIZONTAL;
            _constraints.ipady = 0;       //reset to default
            _constraints.weighty = 1.0;   //request any extra vertical space
            //c.anchor = GridBagConstraints.PAGE_END; //bottom of space
            _constraints.insets = new Insets(10,0,0,0);  //top padding
            _constraints.gridx = 1;       //aligned with button 2
            //c.gridwidth = 2;   //2 columns wide
            _constraints.gridy = 1;       //third row
            this.add(jbEasy, BorderLayout.CENTER);
            
            jbEasy.addActionListener(new ActionListener()
            {
    
                @Override
                public void actionPerformed(ActionEvent e)
                {    
                    _indexDifficulty = 1;           
                    MainWindow.setPeriudNumber(_easy);
                    MainWindow.startGame();
                }    
            });

            

            JButton jbNormal = new JButton("NORMAL");
            
            _constraints.fill = GridBagConstraints.HORIZONTAL;
            _constraints.ipady = 0;       //reset to default
            _constraints.weighty = 1.0;   //request any extra vertical space
            //c.anchor = GridBagConstraints.PAGE_END; //bottom of space
            _constraints.insets = new Insets(10,0,0,0);  //top padding
            _constraints.gridx = 1;       //aligned with button 2
            //c.gridwidth = 2;   //2 columns wide
            _constraints.gridy = 1;       //third row
            this.add(jbNormal, BorderLayout.CENTER);
            
            jbNormal.addActionListener(new ActionListener()
            {
    
                @Override
                public void actionPerformed(ActionEvent e)
                {    
                    _indexDifficulty = 2;           
                    MainWindow.setPeriudNumber(_normal);
                    MainWindow.startGame();
                    
                }    
            });

            

            JButton jbHard = new JButton("HARD");
            
            _constraints.fill = GridBagConstraints.HORIZONTAL;
            _constraints.ipady = 0;       //reset to default
            _constraints.weighty = 1.0;   //request any extra vertical space
            //c.anchor = GridBagConstraints.PAGE_END; //bottom of space
            _constraints.insets = new Insets(10,0,0,0);  //top padding
            _constraints.gridx = 1;       //aligned with button 2
            //c.gridwidth = 2;   //2 columns wide
            _constraints.gridy = 1;       //third row
            this.add(jbHard, BorderLayout.CENTER);
            
            jbHard.addActionListener(new ActionListener()
            {
    
                @Override
                public void actionPerformed(ActionEvent e)
                {               
                    _indexDifficulty = 3;
                    MainWindow.setPeriudNumber(_hard);
                    MainWindow.startGame();
                }    
            });

            


            JButton jbExit = new JButton("EXIT");
            
            _constraints.fill = GridBagConstraints.HORIZONTAL;
            _constraints.ipady = 0;       //reset to default
            _constraints.weighty = 1.0;   //request any extra vertical space
            //c.anchor = GridBagConstraints.PAGE_END; //bottom of space
            _constraints.insets = new Insets(10,0,0,0);  //top padding
            _constraints.gridx = 1;       //aligned with button 2
            //c.gridwidth = 2;   //2 columns wide
            _constraints.gridy = 1;       //third row
            this.add(jbExit, BorderLayout.CENTER);
            
            jbExit.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    int reponse = JOptionPane.showConfirmDialog(
                    MenuPanel.this,
                    "Voulez-vous vraiment quitter ?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION
                    );

                if(reponse == JOptionPane.YES_OPTION)
                    {
                        SaveProfilJson s = new SaveProfilJson();
                        s.saveJson();
                        System.exit(0);
                    }
                }                 
                   
            });

            

            this.repaint();
            this.updateUI();
    }

    public static int getIndexDifficulty()
    {
        return _indexDifficulty;
    }
}
