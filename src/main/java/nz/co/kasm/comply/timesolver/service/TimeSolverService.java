package nz.co.kasm.comply.timesolver.service;

import nz.co.kasm.comply.timesolver.domain.Graph;
import nz.co.kasm.comply.timesolver.domain.Timeline;

public interface TimeSolverService {

  Timeline solve(Graph graph);

}