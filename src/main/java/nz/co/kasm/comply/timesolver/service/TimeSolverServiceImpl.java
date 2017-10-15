package nz.co.kasm.comply.timesolver.service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import nz.co.kasm.comply.timesolver.domain.Edge;
import nz.co.kasm.comply.timesolver.domain.Graph;
import nz.co.kasm.comply.timesolver.domain.Timeline;
import nz.co.kasm.comply.timesolver.domain.TimelineItem;
import nz.co.kasm.comply.timesolver.domain.Vertex;

@Service
public class TimeSolverServiceImpl implements TimeSolverService {
  private List<Edge> completedEdges = new ArrayList<>();
  private List<TimelineItem> timeline = new ArrayList<>(); 
  private Map<Vertex,List<Edge>> sourceEdges;
  private static final BigDecimal EIGHT = new BigDecimal("8");
  private final Comparator<ZonedDateTime> startDateComparator = Comparator.comparing(zdt -> zdt.truncatedTo(ChronoUnit.HOURS));
  
  /* (non-Javadoc)
   * @see nz.co.kasm.comply.timesolver.service.TimeSolverService#solve(nz.co.kasm.comply.timesolver.domain.Graph)
   */
  @Override
  public Timeline solve(Graph graph){
    sourceEdges = getSourceEdges(graph.getEdges()); 
    validateTopLevelNodes(graph.getVertexes());
    List<Edge> edges = graph.getEdges();
    int edgeCount = edges.size();
    while(completedEdges.size() < edgeCount){
      processEdges(edges);
    }
    return new Timeline(getTimeline());
  }
  
  private void validateTopLevelNodes(List<Vertex> nodes){
    List<Vertex> invalidOrphans = new ArrayList<>();
    for(Vertex node: nodes){
      if(node.getActionDate() == null &&
          !sourceEdges.containsKey(node)){
        invalidOrphans.add(node);
      }
    }
    if(invalidOrphans.size() > 0){
      StringBuilder sb = new StringBuilder("The Following nodes are orphans without an ActionDate:");
      invalidOrphans.forEach(v -> sb.append(v.getName() + ","));
      throw new IllegalStateException(sb.toString());
    }
  }
  
  private void processEdges(List<Edge> edges) {
    edges.stream()
         .filter(e -> !completedEdges.contains(e))
         .forEach(e -> processEdge(e));
    
    
    // for(Edge edge: edges){
    // if(!completedEdges.contains(edge)){
    // processEdge(edge);
    // }
    // }
  }
  
  private void processEdge(Edge edge){
    if(edge.getSource().getActionDate() == null){
      return;
    }
    if(edge.getNominalDestinationStartDate() == null){
      calculateNominalDestinationStartDate(edge);
    }
    //get the edges for the destination node
    List<Edge> vertexSourceEdges = sourceEdges.get(edge.getDestination());
    //we must have the durations for all parent edges in order to calculate the
    //action date
    ZonedDateTime earliestDate = edge.getNominalDestinationStartDate();
    for(Edge sourceEdge:vertexSourceEdges){
      //ignore the edge we are working on
      if(sourceEdge.getId() == edge.getId() ){
        continue;
      }
      //we can't continue unless all parent vertexes have a calculated Action Date and duration
      if(sourceEdge.getNominalDestinationStartDate() == null){
        return;
      }
      if(startDateComparator.compare(earliestDate, sourceEdge.getNominalDestinationStartDate()) > 0){
        earliestDate = sourceEdge.getNominalDestinationStartDate();
      }
    }
    edge.getDestination().setActionDate(earliestDate);    
    completedEdges.add(edge);
    timeline.add(new TimelineItem(edge.getDestination().getName(),edge.getDestination().getActionDate()));
  }
  
  private void calculateNominalDestinationStartDate(Edge edge){
    //TODO - make this smart ie deal with holidays and support 'must include weekend'

    //need to be complete the day before the Action Date
    edge.setNominalDestinationStartDate(edge.getSource().getActionDate().minus(1,ChronoUnit.DAYS).minus(calculateDuration(edge.getWeight()), ChronoUnit.DAYS));
  }
  
  /**
   * Assuming 8 hour days, Rounding down and adding one to ensure
   * @param weight
   * @return
   */
  private int calculateDuration(int weight){
    BigDecimal weightBD = new BigDecimal(weight);
    weightBD.divide(EIGHT,BigDecimal.ROUND_DOWN);
    return weightBD.intValue();
  }
  
  private Map<Vertex,List<Edge>> getSourceEdges(List<Edge> edges){
    Map<Vertex,List<Edge>> sourceEdges = new HashMap<>();
    for(Edge edge: edges){
      if(!sourceEdges.containsKey(edge.getDestination())){
        sourceEdges.put(edge.getDestination(),new ArrayList<>());
      }
      sourceEdges.get(edge.getDestination()).add(edge);
    }
    return sourceEdges;
  }
  
  private List<TimelineItem> getTimeline(){
    return timeline;
  }
}
