import java.util.*;

public class A_Star {

    private RoadNetwork roadNetwork;
    private PriQueue<Node, Double> frontier;
    private Map<State, Node> reached;
    private Location goal_state;
    private boolean debug;  // To decide whether to print debugging details
    public int cntNode; // counter for the number of nodes visited

    public A_Star(RoadNetwork roadNetwork) {
        this.roadNetwork = roadNetwork;
        this.frontier = new PriQueue<>(true);
        this.reached = new HashMap<>();
        this.cntNode = 1; // Set to 1 to account for starting node
    }

    public Node aStarSearch(Location initial_state, Location goal_state, boolean debug, int times_speed) {
        this.goal_state = goal_state;
        this.debug = debug;

        Node node;
        if(times_speed > 0) {
            node = new Node(initial_state, null, null,
                    0, h(initial_state)/2, times_speed,null,"");
        }
        else {
            node = new Node(initial_state, null, null,
                    0, h(initial_state), times_speed,null,"");
        }
        frontier.add(node, node.getF());
        reached.put(new State(initial_state,times_speed), node);

        while (!frontier.isEmpty()) {
            node = frontier.remove();

            // Print the debugging details for the current node
            if (debug) {
                System.out.println("\nVisiting " + node);
            }

            if (node.getLocation().id() == goal_state.id()) {
                return node;
            }

            List<Node> children = expand(node);
            for (int i = 0; i < children.size(); i++) {
                Node child_node = children.get(i);
                State child_state = child_node.getState();

                // Print debugging details for the child node
                if (debug) {
                    // checks if the child_state which includes the child_state.id()
                    // and the times left to speed is the same as the reached node and the f value
                    // of the child node is less than the reached node
                    if (!reached.containsKey(child_state) || child_node.getF() < reached.get(child_state).getF()) {
                        System.out.println("    Adding " + child_node + " to frontier.");
                    } else {
                        System.out.println("    Skipping " + child_node + " (already on frontier with lower cost).");
                    }
                }

                if (!reached.containsKey(child_state) || child_node.getF() < reached.get(child_state).getF()) {
                    reached.put(child_state, child_node);
                    frontier.add(child_node, child_node.getF());
                }
            }
            cntNode++;
        }

        return null;  // Indicates failure
    }

    private List<Node> expand(Node node) {
        List<Node> child_node_collection = new ArrayList<>();
        List<Road> adjacentRoads = roadNetwork.getAdjacentRoads(node.getLocation());

        for (int i = 0; i < adjacentRoads.size(); i++) {
            Road action = adjacentRoads.get(i);
            String roadName = action.name();
            Location child_state = roadNetwork.getLocationForId(action.endId());
            double child_gcost;
            Node child_node;

            // checks if the child_node can speed
            if (node.getTimes_speed() > 0) {
                //adds a new non-speeding child_node
                child_gcost = node.getG() + Geometry.getDriveTimeInSeconds(action,roadNetwork);
                child_node = new Node(child_state, action, node, child_gcost, h(child_state) / 2, node.getTimes_speed(), false,roadName);
                child_node_collection.add(child_node);

                child_gcost = node.getG() + Geometry.getDriveTimeInSeconds(action,roadNetwork)/2;

                //check if the node can speed after the current child_node
                if(node.getTimes_speed() > 1) {
                    // adds a new speeding child_node with a speed heuristic function
                    child_node = new Node(child_state, action, node, child_gcost, h(child_state) / 2, node.getTimes_speed() - 1, true,roadName);
                }
                else {
                    // adds a new speeding child_node if the current node has no speeding left so the g cost is divided by 2 but the h cost is not
                    // since the heuristic is not allowed to speed.
                    child_node = new Node(child_state, action, node, child_gcost, h(child_state), node.getTimes_speed() - 1, true,roadName);
                }
                child_node_collection.add(child_node);

            }
            else {
                // adding a not speeding node if speeding is never an option
                child_gcost = node.getG() + Geometry.getDriveTimeInSeconds(action,roadNetwork);
                child_node = new Node(child_state, action, node, child_gcost, h(child_state), node.getTimes_speed(), false,roadName);
                child_node_collection.add(child_node);
            }
        }
        return child_node_collection;
    }

    private double h(Location loc) {
        return Geometry.getDriveTimeInSeconds(loc.latitude(), loc.longitude(), goal_state.latitude(), goal_state.longitude(), 65);
    }

}