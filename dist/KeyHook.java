/* Decompiled KeyHook */
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingWorker;

public class KeyHook extends SwingWorker
{

    public KeyHook()
    {
        progressListeners = new ArrayList();
    }

    public void addListener(SwingListener theListener)
    {
        progressListeners.add(theListener);
    }

    public Void doInBackground()
    {
        return null;
    }

    public volatile Object doInBackground()
        throws Exception
    {
        return doInBackground();
    }

    private List progressListeners;
}