 /*  
Name: Tsugoii
Date: 10/10/2021
Description: Interface
*/

// generic parameter again specifies the type of the labels that are associated with the vertices of the graph
// contain four method signatures that correspond to the four actions performed in the depth first search: cycle detected, process vertex, descend and ascend


interface DFSActions<T> {
    public void cycleDetected();
    public void processVertex(T v);
    public void descend();
    public void ascend();
}  