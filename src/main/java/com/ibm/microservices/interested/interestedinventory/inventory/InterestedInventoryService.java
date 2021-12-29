package com.ibm.microservices.interested.interestedinventory.inventory;

import org.springframework.stereotype.Service;

import java.util.List;

public interface InterestedInventoryService {
    void addInterestedInventory(InterestedInventoryPojo interestedInventoryPojo);

    List<InterestedInventoryPojo> getAllInterestedInventory();
}
