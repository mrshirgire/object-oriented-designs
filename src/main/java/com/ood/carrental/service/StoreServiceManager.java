package com.ood.carrental.service;

import com.ood.carrental.Store;

import java.util.List;
import java.util.UUID;

public class StoreServiceManager implements StoreService{

    List<Store> stores;
    public StoreServiceManager(List<Store> stores) {
        this.stores = stores;
    }

    @Override
    public List<Store> getAllStores() {
        return List.of();
    }

    @Override
    public Store getStoreById(UUID uuid) {
        return null;
    }

    @Override
    public Store addStore(Store store) {
        return null;
    }

    @Override
    public Store updateStore(Store store) {
        return null;
    }

    @Override
    public void deleteStore(UUID uuid) {

    }
}
