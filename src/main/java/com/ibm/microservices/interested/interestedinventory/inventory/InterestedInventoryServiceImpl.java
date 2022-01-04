package com.ibm.microservices.interested.interestedinventory.inventory;

import com.ibm.microservices.interested.interestedinventory.SkuMasterProxy;
import io.github.resilience4j.retry.annotation.Retry;
import org.hibernate.event.internal.MergeContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class InterestedInventoryServiceImpl implements  InterestedInventoryService{
    @Autowired
    InterestedInventoryRepo interestedInventoryRepo;
    @Autowired
    SkuMasterProxy skuMasterProxy;
    @Override
    @Transactional
    public void addInterestedInventory(InterestedInventoryPojo interestedInventoryPojo) {
        interestedInventoryPojo.setAddDate(new Date());
         this.interestedInventoryRepo.save(beanToEntity(interestedInventoryPojo));
    }

    @Override
    public List<InterestedInventoryPojo> getAllInterestedInventory() {
        List<InventoryMasterPojo> inventoryMasterPojos=skuMasterProxy.getInventory();
        Map<String, String> inventoryMap=new HashMap<>();
        inventoryMasterPojos.forEach(e->inventoryMap.put(e.getSkuId(),e.getSkuDesc()));
        List<InterestedInventoryPojo> interestedInventoryPojos=new ArrayList<>();
        interestedInventoryRepo.findAll().forEach(e-> interestedInventoryPojos.add(entityToBean(e,inventoryMap)));
        return interestedInventoryPojos;
    }
    public static InterestedInventoryEntity beanToEntity(InterestedInventoryPojo interestedInventoryPojo){
        InterestedInventoryEntity interestedInventoryEntity=new InterestedInventoryEntity();
        BeanUtils.copyProperties(interestedInventoryPojo,interestedInventoryEntity);
        return interestedInventoryEntity;
    }
    public static InterestedInventoryPojo entityToBean(InterestedInventoryEntity interestedInventoryEntity, Map<String, String> inventoryMap){
        InterestedInventoryPojo interestedInventoryPojo=new InterestedInventoryPojo();
        BeanUtils.copyProperties(interestedInventoryEntity,interestedInventoryPojo);
        interestedInventoryPojo.setSkuDesc(inventoryMap.get(interestedInventoryEntity.getId()));
        return interestedInventoryPojo;
    }
}
