package fr.aurelien.worm_project.Core;

public interface ISubscriber 
{
    public void receiveNotification(String channelName, Object ... payload);
}
