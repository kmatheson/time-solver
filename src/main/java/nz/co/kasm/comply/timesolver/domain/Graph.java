package nz.co.kasm.comply.timesolver.domain;

import java.util.List;

public class Graph {

  private List<Vertex> vertexes;
  private List<Edge> edges;
  
  public Graph() {
    super();
  }

  public Graph(List<Vertex> vertexes,
               List<Edge> edges) {
    super();
    this.vertexes = vertexes;
    this.edges = edges;
  }

  public List<Vertex> getVertexes() {
    return vertexes;
  }

  public List<Edge> getEdges() {
    return edges;
  }

}
