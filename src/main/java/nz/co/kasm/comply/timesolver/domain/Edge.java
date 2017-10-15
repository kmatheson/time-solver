package nz.co.kasm.comply.timesolver.domain;

import java.time.ZonedDateTime;

public class Edge {

  private long id;
  private Vertex source;
  private Vertex destination;
  private int weight;
  private ZonedDateTime nominalDestinationStartDate;

  public Edge(){
    super();
  }
  
  public Edge(long id,
              Vertex source,
              Vertex destination,
              int weight) {
    super();
    this.id = id;
    this.source = source;
    this.destination = destination;
    this.weight = weight;
  }

  public long getId() {
    return id;
  }

  public Vertex getSource() {
    return source;
  }

  public Vertex getDestination() {
    return destination;
  }

  public int getWeight() {
    return weight;
  }

  public ZonedDateTime getNominalDestinationStartDate() {
    return nominalDestinationStartDate;
  }

  public void setNominalDestinationStartDate(ZonedDateTime nominalDestinationStartDate) {
    this.nominalDestinationStartDate = nominalDestinationStartDate;
  }   

}
