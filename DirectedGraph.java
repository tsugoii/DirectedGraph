/*  
Name: Tsugoii
Date: 10/10/2021
Description: Generic
*/

// Generic Class, parameter specifies the type of the labels that are associated with the vertices of the graph(not weighted)
// contain a method that allows edges to be added to the graph, which is how the main method will initially build the graph
// contain a method that performs a depth-first search of that graph
/* When the method in the DirectedGraph class that initiates the depth first search is called, 
    first initialize all vertices to the undiscovered state and begin the search at the vertex that corresponds to the first name in the input file */
// allow the main method to display any unreachable classes by examining all the vertices of the graph to see which remain undiscovered

import java.lang.*;
import java.util.*;

class DirectedGraph<T> {

    LinkedList<Vertex<T>> vertices;
    HashMap<T, Vertex<T>> map;
    HashSet<Vertex<T>> seen;
    HashSet<Vertex<T>> notSeen;
    DFSActions actions;

    public DirectedGraph(List<String[]> lines) {
        vertices = new LinkedList<Vertex<T>>();
        if (lines == null) {
            throw new IllegalArgumentException();
        }
        map = new HashMap<T, Vertex<T>>();

        for (String[] s : lines) {
            if (s.length > 0) {

                Vertex<T> current = map.get((T) s[0]);

                if (current == null) {
                    current = add((T) s[0]);
                }

                for (int i = 1; i < s.length; i++) {
                    Vertex<T> edge = map.get((T) s[i]);
                    if (edge == null) {
                        edge = add((T) s[i]);
                    }
                    current.addEdge(edge);
                }
            }
        }
        map = null;// free memory
    }

    private Vertex<T> add(T s) {
        T key = s;
        Vertex<T> value = new Vertex<T>(key);
        vertices.add(value);
        map.put(key, value);
        return value;
    }

    public void depthFirstSearch(DFSActions act) {
        notSeen = new HashSet<Vertex<T>>();
        for (Vertex<T> v : vertices)
            notSeen.add(v);
        seen = new HashSet<Vertex<T>>();
        actions = act;
        depthFirstSearch(vertices.getFirst());
    }

    private void depthFirstSearch(Vertex<T> v) {
        System.out.println(v.getData());

        if (seen.contains(v)) {
            // https://www.geeksforgeeks.org/detect-cycle-in-a-graph/
            actions.cycleDetected();
            return;
        }

        actions.processVertex(v.getData());

        seen.add(v);

        actions.descend();

        for (Vertex<T> vert : v.edges) {
            depthFirstSearch(vert);
        }

        actions.ascend();

        seen.remove(v);
        notSeen.remove(v);
    }

    public String displayUnreachable() {
        depthFirstSearch(new Hierarchy<T>());
        StringBuilder result = new StringBuilder();
        for (Vertex<T> v : notSeen) {
            result.append(v.getData().toString() + "\n");
        }
        return result.toString();
    }

    public class Vertex<T> {
        private LinkedList<Vertex<T>> edges;
        private T data;

        public Vertex(T t) {
            edges = new LinkedList<Vertex<T>>();
            setData(t);
        }

        public void setData(T d) {
            data = d;
        }

        public T getData() {
            return data;
        }

        public void addEdge(Vertex<T> vertex) {
            edges.add(vertex);
        }

        @Override
        public String toString() {
            return "";
        }
    }
}