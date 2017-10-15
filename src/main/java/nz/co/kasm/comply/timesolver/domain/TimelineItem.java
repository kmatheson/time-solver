package nz.co.kasm.comply.timesolver.domain;

import java.time.ZonedDateTime;

public class TimelineItem {
  private String Task;
  private ZonedDateTime start; 
  
  public TimelineItem(){
    super();
  }
  
  public TimelineItem(String task,
                      ZonedDateTime start) {
    super();
    Task = task;
    this.start = start;
  }
  
  public String getTask() {
    return Task;
  }
  public void setTask(String task) {
    Task = task;
  }
  public ZonedDateTime getStart() {
    return start;
  }
  public void setStart(ZonedDateTime start) {
    this.start = start;
  }

  @Override
  public String toString() {
    return "TimelineItem [Task=" + Task + ", start=" + start + "]";
  }  
}
