package nz.co.kasm.comply.timesolver.domain;

import java.util.List;

public class Timeline {
  
  private List<TimelineItem> timelineItems;
  
  public Timeline(){
    super();
  }

  public Timeline(List<TimelineItem> timelineItems) {
    this.timelineItems = timelineItems;
  }

  public List<TimelineItem> getTimelineItems() {
    return timelineItems;
  }

  public void setTimelineItems(List<TimelineItem> timelineItems) {
    this.timelineItems = timelineItems;
  }  

}
