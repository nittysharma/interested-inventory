package com.ibm.microservices.interested.interestedinventory;

import com.ibm.microservices.interested.interestedinventory.inventory.InventoryMasterPojo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="master-data-service")
public interface SkuMasterProxy {
    @GetMapping("/inventory")
    public List<InventoryMasterPojo> getInventory();
}
