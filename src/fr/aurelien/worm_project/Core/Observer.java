package fr.aurelien.worm_project.Core;

import static fr.aurelien.worm_project.Core.Logger.log;

import java.util.ArrayList;

import javax.management.RuntimeErrorException;

public class Observer 
{
    private static Observer _obs;
    private ArrayList<Channel> _channelList = new ArrayList<>();
    private static int _id = 0;

    public static Observer getInstance()
    {
        if(_obs == null)
        {
            _obs =new Observer();
        }

        return _obs;
    }
    
    private Observer()
    {
        
    }

    public Channel createChannel(IPublisher owner, String name)
    {
        for(Channel c: _channelList)
        {
            if(c.getName() == name)
                throw new RuntimeException("Name already exist !!");
        }
        _channelList.add(new Channel(owner, name));
        return _channelList.getLast();
    }

    public void destryChannel()
    {
        _channelList.removeLast();
    }

    public void subscribe(ISubscriber sub, String name)
    {
        for(Channel c: _channelList)
        {
            if(c.getName() == name)
                c.addSubscriber(sub);
        }
    }
}
