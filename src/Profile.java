import javax.swing.DefaultListModel;

public class Profile implements java.io.Serializable {
    private String name;
    private int frequency;
    private int delay;
    private DefaultListModel listModelActions;

    public void setName(String theName) {
        name = theName;
    }

    public void setModel (DefaultListModel theModel) {
        listModelActions = theModel;
    }

    public DefaultListModel getModelActions() {
        return listModelActions;
    }

    public void setFreq(int theFreq) {
        frequency = theFreq;
    }

    public void setDelay(int theDelay) {
        delay = theDelay;
    }

    public String getName(){
        return name;
    }

    public int getFreq(){
        return frequency;
    }

    public int getDelay() {
        return delay;
    }
    
}
