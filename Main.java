//Nickolaus Jackoski
//I have neither given nor received unauthorized aid on this program.
import java.io.InputStream;
import java.util.*;

public class Main {
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        String filename = "memphis-medium.txt";
        RoadNetwork graph = readGraph(filename);


        System.out.print("Enter starting location ID: ");
        long IdStart = Long.parseLong(scan.nextLine());
        System.out.print("Enter ending location ID: ");
        long IdEnd = Long.parseLong(scan.nextLine());
        Location locIdStart = graph.getLocationForId(IdStart);
        Location locIdEnd = graph.getLocationForId(IdEnd);
        System.out.print("How many times are you allowed to speed? ");
        int times_speed = Integer.parseInt(scan.nextLine());
        System.out.print("Do you want debugging information (y/n)? ");
        String debug_info = scan.nextLine();
        boolean debug = false;

        if(debug_info.equals("y")) {
            debug= true;
        }

        A_Star A = new A_Star(graph);
        Node node = A.aStarSearch(locIdStart,locIdEnd,debug,times_speed);

        System.out.println("\nTotal travel time in seconds: " + node.getG());
        System.out.println("Number of nodes visited: " + A.cntNode);
        Stack<Node> stack = new Stack<>();

        while(node!=null){
            stack.push(node);
            node = node.getParent();
        }
        System.out.println("\nRoute found is: ");
        while(!stack.isEmpty()){
            node = stack.pop();
            System.out.println(node.routePrinting());
        }


    }

    public static RoadNetwork readGraph(String filename)
    {
        InputStream is = Main.class.getResourceAsStream(filename);
        if (is == null) {
            System.err.println("Bad filename: " + filename);
            System.exit(1);
        }
        Scanner scan = new Scanner(is);

        RoadNetwork graph = new RoadNetwork();

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] pieces = line.split("\\|");

            if (pieces[0].equals("location")) {
                long id = Long.parseLong(pieces[1]);
                double lat = Double.parseDouble(pieces[2]);
                double longi = Double.parseDouble(pieces[3]);
                Location loc = new Location(id, lat, longi);
                graph.addLocation(loc);
            } else if (pieces[0].equals("road")) {
                long startId = Long.parseLong(pieces[1]);
                long endId = Long.parseLong(pieces[2]);
                int speed = Integer.parseInt(pieces[3]);
                String name = pieces[4];
                Road r1 = new Road(startId, endId, speed, name);
                Road r2 = new Road(endId, startId, speed, name);
                graph.addRoad(r1);
                graph.addRoad(r2);
            }
        }
        scan.close();

        return graph;
    }
}
