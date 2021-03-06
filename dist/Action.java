/* Decompiled Action */
import java.io.Serializable;

public class Action
    implements Serializable
{

    public Action()
    {
    }

    public void setDisplayName(String theName)
    {
        displayName = theName;
    }

    public void setType(String theType)
    {
        type = theType;
    }

    public void setX(int theX)
    {
        xValue = theX;
    }

    public void setY(int theY)
    {
        yValue = theY;
    }

    public String toString()
    {
        return displayName;
    }

    public String getType()
    {
        return type;
    }

    public int getX()
    {
        return xValue;
    }

    public int getY()
    {
        return yValue;
    }

    private String displayName;
    private String type;
    private int xValue;
    private int yValue;
}