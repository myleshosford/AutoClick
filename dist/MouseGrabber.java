/* Decompiled MouseGrabber */
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingWorker;

public class MouseGrabber extends SwingWorker
{

    public MouseGrabber()
    {
        progressListeners = new ArrayList();
    }

    public void notifyMouseMove(int theX, int theY)
    {
        SwingListener listener = (SwingListener)progressListeners.get(0);
        listener.notifyMouseMove(theX, theY);
    }

    public void addListener(SwingListener theListener)
    {
        progressListeners.add(theListener);
    }

    protected Void doInBackground()
    {
        do
        {
            java.awt.PointerInfo a = java.awt.MouseInfo.getPointerInfo();
            java.awt.Point b = a.getLocation();
            int x = (int)b.getX();
            int y = (int)b.getY();
            try
            {
                notifyMouseMove(x, y);
            }
            catch(Exception e)
            {
                System.out.println((new StringBuilder()).append("Error: ").append(e).toString());
                e.printStackTrace();
            }
        } while(true);
    }

    protected volatile Object doInBackground()
        throws Exception
    {
        return doInBackground();
    }

    private List progressListeners;
}