package fr.aurelien.worm_project.Core;

import java.util.ArrayList;

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

    public void createChannel(IPublisher owner, String name)
    {
        for(Channel c: _channelList)
        {
            if(c.getName() == name)
                throw new RuntimeException("Name already exist !!");
        }
        _channelList.add(new Channel(owner, name));
    }

    public void destryChannel(Object owner, String chan)
    {
        for(int i = 0; i < _channelList.size(); i++)
        {
            if(_channelList.get(i).getName() == chan && _channelList.get(i).getObject() == owner)
                _channelList.remove(i);
        }
    }

    public void subscribe(ISubscriber sub, String name)
    {
        for(Channel c: _channelList)
        {
            if(c.getName() == name)
                c.addSubscriber(sub);
        }
    }

    public void publish(Object owner, String name, Object ... arg)
    {
        for(int i = 0; i < _channelList.size(); i++)
        {
            if(_channelList.get(i).getName() == name && _channelList.get(i).getObject() == owner)
                _channelList.get(i).publish(arg);
        }
    }
}
