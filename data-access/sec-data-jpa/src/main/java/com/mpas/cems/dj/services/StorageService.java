
package com.mpas.cems.dj.services;

import com.mpas.cems.dao.Storage;

import java.util.List;


public interface StorageService {

    List<Storage> findAll();

    Storage findById(Long id);

    Storage save(Storage storage);
}
