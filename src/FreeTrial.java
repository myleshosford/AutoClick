import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

public class FreeTrial extends SwingWorker<Void,Integer> {


    private List progressListeners = new ArrayList<SwingListener>();

    public void addListener(SwingListener theListener) {
        progressListeners.add(theListener);
    }

    public void freeTrialUp() {
        SwingListener listener = (SwingListener) progressListeners.get(0);
        listener.freeTrialUp();
    }

    public Void doInBackground()  {
        long start = System.currentTimeMillis();
        boolean run = true;
        while (run) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                System.out.println("Error: " + ex);
            }
        long elapsed = System.currentTimeMillis() - start;
        if (elapsed>300000) {//300000
            freeTrialUp();
            run = false;
        }
        }
        return null;
    }

}
