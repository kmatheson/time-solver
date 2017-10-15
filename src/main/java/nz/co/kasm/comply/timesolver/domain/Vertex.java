package nz.co.kasm.comply.timesolver.domain;

import java.time.ZonedDateTime;

public class Vertex {

  private long id;
  private String name;
  
  private ZonedDateTime actionDate;
  
  public Vertex(){
    super();
  }

  public Vertex(long id,
                String name,
                ZonedDateTime actionDate) {
    this.id = id;
    this.name = name;
    this.actionDate = actionDate;
  }
  
  public Vertex(long id,
                String name) {
    this.id = id;
    this.name = name;
  }
  
//  public Vertex(long id,
//                String name,
//                String actionDate) {
//    this.id = id;
//    this.name = name;
//    this.actionDate = ZonedDateTime.parse(actionDate);
//  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setActionDate(ZonedDateTime actionDate) {
    this.actionDate = actionDate;
  }
  
//  public void setActionDate(String actionDate) {
//    this.actionDate = ZonedDateTime.parse(actionDate);
//  }
  
  public ZonedDateTime getActionDate() {
    return actionDate;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((actionDate == null) ? 0 : actionDate.hashCode());
    result = prime * result + (int) (id ^ (id >>> 32));
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Vertex other = (Vertex) obj;
    if (actionDate == null) {
      if (other.actionDate != null)
        return false;
    } else if (!actionDate.equals(other.actionDate))
      return false;
    if (id != other.id)
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }
  
  

}
