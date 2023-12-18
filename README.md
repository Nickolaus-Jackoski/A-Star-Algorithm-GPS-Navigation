# A Star Algorithm GPS Navigation

## Overview
This project is a Java-based application that uses the A* algorithm to determine the fastest driving route between two locations in Memphis. The program calculates the most efficient path given a map of Memphis, considering speed limits on each road segment. It features two main functionalities: a standard A* algorithm implementation and an extended version that incorporates speeding under certain conditions.

## Problem Setup
- **User Interaction:** Users input the start and end location IDs for their desired route.
- **Route Calculation:** The A* algorithm is employed to compute the fastest route based on road speed limits.
- **Extended Functionality:** An advanced mode allows the vehicle to travel at twice the speed limit for a set number of segments.

## Implementation Details
- **Language:** The program is developed in Java.
- **Key Classes:**
  - `Location`: Represents a location with a unique identifier.
  - `Node`: Used in the A* algorithm, representing a state in the search tree.
  - `Road`: Describes a road segment, including its speed limit.
  - `RoadNetwork`: Manages the network of roads and locations.
  - `State`: Encapsulates a node's state, including location and speeding allowances.
  - `PriQueue`: A custom priority queue implementation for the A* algorithm.
  - `Main`: The main driver class, handling user inputs and initiating the route calculation.
  - `Geometry`: Provides geometric calculations, likely for heuristic estimations.

## How to Run The Program
1. **Compile Java Files:** Compile all the Java class files in the project.
2. **Execute Main Class:** Run the `Main` class to start the program.
3. **Enter Location IDs:** Input the start and end location IDs as prompted.
4. **Debugging Option:** Choose to view debugging information during execution.
5. **Result Display:** The program outputs the fastest route, number of nodes visited, and total travel time.
For an example run click below OR click on the Example_Run.txt file in the repository:
[Download Example_Run.txt](https://github.com/Nick-Jacko/A-Star-Algorithm-GPS-Navigation/blob/main/Example_Run.txt)

## Description of the Code

### Key Classes

### 1. Location
- **Purpose:** Represents a specific place within the Memphis road network.
- **Attributes:**
  - `Identifier`: Unique ID or name for each location.
  - `Geographic Data`: Coordinates or other geographic information.
- **Usage:** Nodes in the road network, serving as points of intersection or destinations.

### 2. Node
- **Purpose:** Fundamental element in the A* search algorithm, representing a search state.
- **Attributes:**
  - `State Information`: Contains the current state data, including location and path cost.
  - `Parent Node`: Reference to the preceding node in the path.
  - `Path Cost Metrics`: Values for `f(n)`, `g(n)`, and `h(n)` crucial for A* algorithm decision-making.
- **Usage:** Tracks the search progress and guides the next steps in pathfinding.

### 3. Road
- **Purpose:** Represents a road segment in the map, the path between `Location` nodes.
- **Attributes:**
  - `Connecting Locations`: Start and end locations that the road connects.
  - `Speed Limit`: Road's speed limit for travel time calculation.
  - `Distance`: Length of the road, used with the speed limit to compute travel time.
- **Usage:** Calculates travel times and constructs the road network graph for the A* algorithm.

### 4. State
- **Purpose:** Encapsulates the current state of a `Node` in the A* algorithm.
- **Attributes:**
  - `Location`: The geographical point represented by the state.
  - `Speeding Allowance`: Data on remaining speeding opportunities (for extended algorithm version).
- **Usage:** Allows the algorithm to consider both geographical position and dynamic aspects like speeding.

- `PriQueue` is a priority queue class tailored for the specific needs of this A* implementation.
- `RoadNetwork` functions as the central system managing roads and locations for the search.
- `Main` is the entry point, managing user input and result display.
- `Geometry` calculates distance which is essential for the A* heuristic function.

This program effectively combines these classes to provide a solution for optimal driving route finding in a road network, showcasing a practical use of the A* algorithm. This project was part of my COMP 372 A.I. class at Rhodes College.
