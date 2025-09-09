package com.arunesh.irctc.irctc_backend.repositories;

import com.arunesh.irctc.irctc_backend.entities.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train,String> {
}
