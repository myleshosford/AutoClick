/* Decompiled License */
import java.io.Serializable;

public class License
    implements Serializable
{

    public License()
    {
    }

    public int getRunAmount()
    {
        return runAmount;
    }

    public void incrementRunAmount()
    {
        runAmount++;
    }

    public void setRunAmount(int amounts)
    {
        runAmount = 0;
    }

    public void setType(int theType)
    {
        type = theType;
    }

    public int getType()
    {
        return type;
    }

    int runAmount;
    int type;
}