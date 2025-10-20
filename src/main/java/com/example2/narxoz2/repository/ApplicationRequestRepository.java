package com.example2.narxoz2.repository;

import com.example2.narxoz2.class4.ApplicationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicationRequestRepository extends JpaRepository<ApplicationRequest, Long> {
    List<ApplicationRequest> findByHandled(boolean handled);
}

