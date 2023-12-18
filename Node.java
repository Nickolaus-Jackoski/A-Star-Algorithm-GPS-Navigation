public class Node
{
    private Location Location;
    private State state;
    private Road action;
    private Node parent;
    private double g;
    private double h;
    private double f;
    private Boolean isSpeeding;
    private String road;

    public Node(Location location, Road action, Node parent, double g, double h, int times_speed, Boolean isSpeeding, String road) {
        this.state = new State(location,times_speed);
        this.action = action;
        this.parent = parent;
        this.g = g;
        this.h = h;
        this.isSpeeding = isSpeeding;
        this.road = road;
    }

    public Location getLocation() {
        return state.loc();
    }

    public double getG() {
        return g;
    }

    public Boolean getIsSpeeding() {
        return isSpeeding;
    }

    public Node getParent(){ return parent;}

    public double getF(){
        f = g + h;
        return f;
    }
    public State getState(){
        return state;
    }

    public int getTimes_speed() {
        return state.times_speed();
    }

    public String getRoadName() {
        return road;
    }

    @Override
    public String toString() {
        // checks if the parent ID is not null
        long parentId;
        String parentOutput;
        if(parent !=null)  {
            parentId = parent.getLocation().id();
            parentOutput = String.valueOf(parentId);
        }
        else {
            parentOutput = "null";
        }

        return  "[State=(" + state.loc().id() +
                ", " + state.times_speed() +
                "), parent="
                + parentOutput +
                ", speeding=" + getIsSpeeding() +
                ", g=" + g +
                ", h=" + h +
                ", f=" + getF() +
                "]";
    }

    public String routePrinting(){
        String isSpeeding;
        if(parent == null){
            return state.loc().id() + " (starting location)";
        }
        else{
            if(getIsSpeeding()){
                isSpeeding = ", speeding";
            }
            else{
                isSpeeding = ", not speeding";
            }
            return state.loc().id() + " (" + getRoadName() + isSpeeding + ")";
        }
    }

}
