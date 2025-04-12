package com.ood.carrental.service;

import com.ood.carrental.Store;

import java.util.List;
import java.util.UUID;

public interface StoreService {

    List<Store> getAllStores();
    Store getStoreById(UUID uuid);
    Store addStore(Store store);
    Store updateStore(Store store);
    void deleteStore(UUID uuid);

}
