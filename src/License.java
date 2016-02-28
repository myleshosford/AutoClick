
public class License implements java.io.Serializable {
    int runAmount;
    int type; //65=full 66=free

    public License() {

    }

    public int getRunAmount() {
        return runAmount;
    }

    public void incrementRunAmount() {
        runAmount++;
    }
    
    public void setRunAmount(int amounts) {
        runAmount = 0;
    }

    public void setType(int theType) {
        type = theType;
    }

    public int getType() {
        return type;
    }

}
