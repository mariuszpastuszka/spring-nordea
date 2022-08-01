package com.mpas.cems.pojos.services;

import com.mpas.cems.dao.Storage;


public interface StorageService extends AbstractService<Storage> {

    Storage createStorage(String name, String location);
}
