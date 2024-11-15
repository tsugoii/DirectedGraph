/*  
Name: Tsugoii
Date: 10/10/2021
Description: Produces Hierarchical dependency representation
*/

// Hierarchy class should override the toString method, which should return a string that contains the above, after having performed the depth-first search
// produce a hierarchical representation of the class dependencies. Circular dependencies should be flagged

public class Hierarchy<T> implements DFSActions<T> {

  StringBuilder sb = new StringBuilder();
  int spaceCounter = 0;

  public void cycleDetected() {
    sb.append(" *");
  }

  public void processVertex(T v) {
    if (sb.toString().length() != 0) {
      sb.append("\n");
    }

    for (int i = 0; i < spaceCounter; i++) {
      sb.append("    ");
    }

    sb.append(v.toString());
  }

  public void descend() {
    spaceCounter++;
  }

  public void ascend() {
    spaceCounter--;
  }

  @Override
  public String toString() {
    return sb.toString();
  }
}