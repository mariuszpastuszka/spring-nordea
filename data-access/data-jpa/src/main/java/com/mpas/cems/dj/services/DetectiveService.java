
package com.mpas.cems.dj.services;

import com.mpas.cems.dao.Detective;
import com.mpas.cems.dj.services.wrappers.DetectiveWrapper;

import java.util.List;


public interface DetectiveService {
    List<Detective> findAll();

    DetectiveWrapper findById(Long id);

    Detective save(Detective detective);
}
