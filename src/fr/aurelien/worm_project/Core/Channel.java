package fr.aurelien.worm_project.Core;

import java.util.ArrayList;

public class Channel 
{
    private IPublisher _owner;
    private String _name;
    private ArrayList<ISubscriber> _objectList = new ArrayList<>();

    public Channel(IPublisher owner, String name)
    {
        _owner = owner;
        _name = name;
    }

    public void publish(Object ... args)
    {
        for(ISubscriber e: _objectList)
        {
            e.receiveNotification(_name, args);
        }
    }

    public void addSubscriber(ISubscriber sub)
    {
        _objectList.add(sub);
    }

    public String getName()
    {
        return _name;
    }

    public Object getObject()
    {
        return _owner;
    }
}
