package fr.aurelien.worm_project.UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.aurelien.worm_project.SaveProfilJson;
import fr.aurelien.worm_project.Core.Profil;
import fr.aurelien.worm_project.Core.ProfilManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PanelScore extends JPanel
{
    private static int _height = 300;
    private static int _width = 300;
    private JLabel _nameScore1, _nameScore2, _nameScore3, _nameScore4, _nameScore5, _score1Label, _score2Label, _score3Label, _score4Label, _score5Label, _newScoreLabel;
    private JTextField _playerName;
    private int _score1 = 1000;
    private int _score2 = 800;
    private int _score3 = 600;
    private int _score4 = 400;
    private int _score5 = 200;
    
    private GridBagConstraints _constraints = new GridBagConstraints();
    //private static ArrayList<PlayerScore> _list;
        
        
        public PanelScore(int a)
        {
            this.setLayout(new GridLayout(7, 2));
            this.setPreferredSize(new Dimension(_height, _width));
            
            createDefaultPlayers();
    
            _newScoreLabel = new JLabel(String.valueOf(a));
            
            _playerName = new JTextField();
    
  
            this.add(_nameScore1);
            this.add(_score1Label);
            this.add(_nameScore2);
            this.add(_score2Label);
            this.add(_nameScore3);
            this.add(_score3Label);
            this.add(_nameScore4);
            this.add(_score4Label);
            this.add(_nameScore5);
            this.add(_score5Label);

            this.add(_newScoreLabel);
            this.add(_playerName);
    
            
    
            JButton jbValidate = new JButton("OK");
            
            _constraints.fill = GridBagConstraints.HORIZONTAL;
            _constraints.ipady = 0;       //reset to default
            _constraints.weighty = 1.0;   //request any extra vertical space
            //c.anchor = GridBagConstraints.PAGE_END; //bottom of space
            _constraints.insets = new Insets(10,0,0,0);  //top padding
            _constraints.gridx = 1;       //aligned with button 2
            //c.gridwidth = 2;   //2 columns wide
            _constraints.gridy = 1;       //third row
            this.add(jbValidate, BorderLayout.CENTER);
            
            jbValidate.addActionListener(new ActionListener()
            {
    
                @Override
                public void actionPerformed(ActionEvent e)
                {               
                    String name = _playerName.getText();
                    int error = 0;
                    if(isValideName(name) == true)
                        error = 0;
                    else
                        error = 1;
    
                    if(error == 0)
                    {
                        MainWindow.getProfilManager().createProfil(name, a);
                        updateMainApp();
                    }
                }    
            });
    
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.setLayout(new BorderLayout());
            buttonPanel.add(jbValidate, BorderLayout.CENTER);
            this.add(buttonPanel);
    
            this.repaint();
            this.updateUI();
            
        }
        
        private void addDefaultPlayers()
        {
            if(!MainWindow.getProfilManager().getListProfil().isEmpty())
            {
                return;
            }
            else
            {
                MainWindow.getProfilManager().createProfil(_nameScore1.getText(), _score1);
                MainWindow.getProfilManager().createProfil(_nameScore2.getText(), _score2);
                MainWindow.getProfilManager().createProfil(_nameScore3.getText(), _score3);
                MainWindow.getProfilManager().createProfil(_nameScore4.getText(), _score4);
                MainWindow.getProfilManager().createProfil(_nameScore5.getText(), _score5);
            }
            
            
        }
        private void createDefaultPlayers()
        {
            _nameScore1 = new JLabel("Lijo");
            _nameScore2 = new JLabel("Enzo");
            _nameScore3 = new JLabel("Shasha");
            _nameScore4 = new JLabel("Bibi");
            _nameScore5 = new JLabel("D3Ff");

            _score1Label = new JLabel(String.valueOf(_score1));
            _score2Label = new JLabel(String.valueOf(_score2));
            _score3Label = new JLabel(String.valueOf(_score3));
            _score4Label = new JLabel(String.valueOf(_score4));
            _score5Label = new JLabel(String.valueOf(_score5));
            addDefaultPlayers();
        }

        private boolean isValideName(String name)
        {
            //String regExp = "^[A-Za-z._-|\s]+[A-Za-z._-|\s]?$";                                 // Verifie le nom et prenom, prenom non obligatoire
            String regExp = "^[a-zA-Z._-]+(\s[a-zA-Z._-]+)?$";
            return name.matches(regExp);
        }

        public static void updateMainApp()
    {
        MainWindow.updateJFrame(new MenuPanel());
    }
    
            /* private void replaceJLabelList()
            {
                if( !_list.isEmpty())
                {
                    _nameScore1.setText(_list.get(0).getName());
                    _nameScore2.setText(_list.get(1).getName());
                    _nameScore3.setText(_list.get(2).getName());
                    _nameScore4.setText(_list.get(3).getName());
                    _nameScore5.setText(_list.get(4).getName());
                    _score1.setText(_list.get(0).getScore());
                    _score2.setText(_list.get(1).getScore());
                    _score3.setText(_list.get(2).getScore());
                    _score4.setText(_list.get(3).getScore());
                    _score5.setText(_list.get(4).getScore());
                    
                }
            } */ 
    
        

    
}

    
    
    
    
    
    
    
    
    
    


