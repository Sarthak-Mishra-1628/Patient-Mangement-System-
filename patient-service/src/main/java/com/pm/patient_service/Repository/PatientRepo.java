package com.pm.patient_service.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pm.patient_service.Entity.*;
import java.util.*;

@Repository
public interface PatientRepo extends JpaRepository< Patient , UUID > {

    boolean existsByEmail(String email);
    
}