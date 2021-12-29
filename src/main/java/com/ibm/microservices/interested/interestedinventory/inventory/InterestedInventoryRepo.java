package com.ibm.microservices.interested.interestedinventory.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestedInventoryRepo extends JpaRepository<InterestedInventoryEntity,Integer> {
}
