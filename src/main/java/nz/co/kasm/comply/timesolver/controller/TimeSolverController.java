package nz.co.kasm.comply.timesolver.controller;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import nz.co.kasm.comply.timesolver.domain.Edge;
import nz.co.kasm.comply.timesolver.domain.Graph;
import nz.co.kasm.comply.timesolver.domain.Timeline;
import nz.co.kasm.comply.timesolver.domain.Vertex;
import nz.co.kasm.comply.timesolver.service.TimeSolverService;

@RestController
@RequestMapping("/timesolver")
public class TimeSolverController {
  
  @Autowired TimeSolverService timeSolverService;  
	
  public TimeSolverController(TimeSolverService timeSolverService) {
    this.timeSolverService = timeSolverService;
  }

  @PostMapping("/timeline")
  public Timeline timeline(@RequestBody Graph graph){
    return timeSolverService.solve(graph);
  }
  
  @GetMapping("/graph")
  public @ResponseBody Graph timeline() {
    List<Vertex> vertexes = new ArrayList<>();
    List<Edge> edges = new ArrayList<>();
    Vertex v1 = new Vertex(1,
                           "c task",
                           ZonedDateTime.parse("2007-12-03T10:15:30+01:00[Pacific/Auckland]"));
    Vertex v2 = new Vertex(2,
                           "b task");
    Vertex v3 = new Vertex(3,
                           "a task");
    Edge e1 = new Edge(1,
                       v1,
                       v2,
                       2);
    Edge e2 = new Edge(2,
                       v2,
                       v3,
                       5);
    vertexes.add(v1);
    vertexes.add(v2);
    vertexes.add(v3);
    edges.add(e1);
    edges.add(e2);

    return new Graph(vertexes, edges);
  }

}
