package com.ibm.microservices.interested.interestedinventory.inventory;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InterestedInventoryController {
    Logger logger= LoggerFactory.getLogger(InterestedInventoryServiceImpl.class);
    @Autowired
    InterestedInventoryService inventoryService;

    @PutMapping("/inventory")
    public ResponseEntity<InterestedInventoryPojo> addInterestedInventory(@RequestBody InterestedInventoryPojo interestedInventoryPojo){
    inventoryService.addInterestedInventory(interestedInventoryPojo);
    return new ResponseEntity(null, HttpStatus.OK);
    }

    @GetMapping("/interested-inventory")
//    @Retry(name="sample-api", fallbackMethod = "hardcodedMessage")
    @CircuitBreaker(name="default",fallbackMethod = "hardcodedMessage")
    @RateLimiter(name="default")
    public ResponseEntity<List<InterestedInventoryPojo>> getAllInterestedInventory(){
        logger.info("calling api");
        List<InterestedInventoryPojo> interestedInventoryPojos=inventoryService.getAllInterestedInventory();
        return new ResponseEntity<>(interestedInventoryPojos, HttpStatus.OK);
    }

    public ResponseEntity<List<InterestedInventoryPojo>> hardcodedMessage(Exception e){
        return new ResponseEntity<List<InterestedInventoryPojo>>(new ArrayList<>(),HttpStatus.OK);
    }
}