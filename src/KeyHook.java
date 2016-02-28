import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingWorker;

/*
 * KeyHook class used to capture keystrokes from the user
 * and set these as key actions or HotKeys to end the program
 */
public class KeyHook extends SwingWorker<Void,Integer> {
    
    private List progressListeners = new ArrayList<SwingListener>();

    public void addListener(SwingListener theListener) {
        progressListeners.add(theListener);
    }

    public Void doInBackground()  {
        return null;
        
    }


}
