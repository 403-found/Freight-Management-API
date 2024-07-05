package com.springrest.springrest.services;

import java.util.List;
import com.springrest.springrest.entities.Load;

public interface LoadService {
    List<Load> getLoad();
    Load getLoads(long loadId);
    Load addLoad(Load load);
    Load updateLoad(Long loadId, Load load);
    void deleteLoad(long loadId);
}
