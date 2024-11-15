/*  
Name: Tsugoii
Date: 10/10/2021
Description: Alt Hierarchy Representation
*/

// produce an alternate representation that is also returned by its toString method.

public class ParenthesizedList<T> implements DFSActions<T> {

  StringBuilder sb = new StringBuilder();

  public void cycleDetected() {
    sb.append(" *");
  }

  public void processVertex(T v) {
    sb.append(" " + v.toString());
  }

  public void descend() {
    sb.append(" (");
  }

  public void ascend() {
    sb.append(" )");
  }

  @Override
  public String toString() {
    return "( " + sb.toString().replace("( )", "") // fix () from vertices with no edges
        .replace("( * )", "*") + " )";// fix (*) when vertex had edges but a cycle was found
  }
}