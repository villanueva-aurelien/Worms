package fr.aurelien.worm_project.UI;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class BackGround extends JPanel
{
    private Image _icon;

    public BackGround(String imagePath)
    {
        _icon = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(_icon, 0, 0, getWidth(), getHeight(), this); // Ajuste l'image à la taille du panel
    }
}
