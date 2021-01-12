package model;

import java.util.ArrayList;

public class Node {

    private String line;
    private String fromTo;
    private String toFrom;
    private int time;

    public Node(String line, String fromTo, String toFrom, int time)
    {
        this.line = line;
        this.fromTo = fromTo;
        this.toFrom = toFrom;
        this.time = time;
    }


    public String getTrainLineName()
    {
        return line;
    }


    public String getFromTo(){
        return fromTo;
    }


    public String getToFrom(){return toFrom;}

    public int getTime(){return time;}



    @Override
    public boolean equals(Object obj)
    {
        if(obj == this)
            return true;
        if(!(obj instanceof Node)){
            return false;
        }
        Node line = (Node) obj;
        return line.getTrainLineName().equals(this.line);
    }

    //avoid collusion
    @Override
    public int hashCode()
    {
        int result = 27;
        result = 31 * result + line.hashCode();
        return result;
    }


    @Override
    public String toString() {
        return String.format("%s:\n", getTrainLineName()) + String.format("%s  -> %s  - %s \n", getFromTo(), getToFrom(), getTime());
    }

}
