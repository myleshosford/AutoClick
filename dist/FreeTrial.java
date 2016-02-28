/* Decompiled FreeTrial */
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingWorker;

public class FreeTrial extends SwingWorker
{

    public FreeTrial()
    {
        progressListeners = new ArrayList();
    }

    public void addListener(SwingListener theListener)
    {
        progressListeners.add(theListener);
    }

    public void freeTrialUp()
    {
        SwingListener listener = (SwingListener)progressListeners.get(0);
        listener.freeTrialUp();
    }

    public Void doInBackground()
    {
        long start = System.currentTimeMillis();
        boolean run = true;
        do
        {
            if(!run)
                break;
            try
            {
                Thread.sleep(5000L);
            }
            catch(InterruptedException ex)
            {
                System.out.println((new StringBuilder()).append("Error: ").append(ex).toString());
            }
            long elapsed = System.currentTimeMillis() - start;
            if(elapsed > 0x493e0L)
            {
                freeTrialUp();
                run = false;
            }
        } while(true);
        return null;
    }

    public volatile Object doInBackground()
        throws Exception
    {
        return doInBackground();
    }

    private List progressListeners;
}