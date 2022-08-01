
package com.mpas.cems.dj.services.impl;

import com.mpas.cems.dao.Detective;
import com.mpas.cems.dj.repos.DetectiveRepo;
import com.mpas.cems.dj.services.DetectiveService;
import com.mpas.cems.dj.services.wrappers.DetectiveWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class DetectiveServiceImpl implements DetectiveService {

    private Logger logger = LoggerFactory.getLogger(DetectiveServiceImpl.class);

    private DetectiveRepo detectiveRepo;

    public DetectiveServiceImpl(DetectiveRepo detectiveRepo) {
        this.detectiveRepo = detectiveRepo;
    }

    @Override
    public List<Detective> findAll() {
        return detectiveRepo.findAll();
    }

    @Override
    public DetectiveWrapper findById(Long id) {
       /* Optional<Detective> detectiveOpt = detectiveRepo.findById(id);
        if(detectiveOpt.isPresent()) {
            Detective detective = detectiveOpt.get();
            logger.debug("Retrieved {} cases.", detective.getCriminalCases().size());
            return new DetectiveWrapper(detective);
        }
        return new DetectiveWrapper();
*/
        Optional<Detective> detectiveOpt = detectiveRepo.findByIdWithCriminalCases(id);
        return detectiveOpt.map(DetectiveWrapper::new).orElseGet(DetectiveWrapper::new);

    }

    @Override
    public Detective save(Detective detective) {
        return detectiveRepo.save(detective);
    }
}
