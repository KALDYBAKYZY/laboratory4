package com.example2.narxoz2.service;
import com.example2.narxoz2.class4.ApplicationRequest;
import com.example2.narxoz2.repository.ApplicationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ApplicationRequestService {

    @Autowired
    private ApplicationRequestRepository applicationRequestRepository;

    public List<ApplicationRequest> getAllRequests() {
        return applicationRequestRepository.findAll();
    }

    public ApplicationRequest getRequestById(Long id) {
        return applicationRequestRepository.findById(id).orElse(null);
    }

    public void saveRequest(ApplicationRequest request) {
        applicationRequestRepository.save(request);
    }

    public void deleteRequest(Long id) {
        applicationRequestRepository.deleteById(id);
    }

    public List<ApplicationRequest> getPendingRequests() {
        return applicationRequestRepository.findByHandled(false);
    }

    public List<ApplicationRequest> getProcessedRequests() {
        return applicationRequestRepository.findByHandled(true);
    }

    public void markAsProcessed(Long id) {
        ApplicationRequest req = applicationRequestRepository.findById(id).orElse(null);
        if (req != null) {
            req.setHandled(true);
            applicationRequestRepository.save(req);
        }
    }
}
