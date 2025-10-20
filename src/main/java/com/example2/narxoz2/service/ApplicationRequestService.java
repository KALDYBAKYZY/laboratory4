package com.example2.narxoz2.service;
import com.example2.narxoz2.class4.ApplicationRequest;
import com.example2.narxoz2.class4.Courses;
import com.example2.narxoz2.class4.Operators;
import com.example2.narxoz2.repository.ApplicationRequestRepository;
import com.example2.narxoz2.repository.CoursesRepository;
import com.example2.narxoz2.repository.OperatorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ApplicationRequestService {

    @Autowired
    private ApplicationRequestRepository requestRepository;
    @Autowired
    private CoursesRepository courseRepository;
    @Autowired
    private OperatorsRepository operatorRepository;

    public List<ApplicationRequest> getAllRequests() {
        return requestRepository.findAll();
    }

    public ApplicationRequest getRequestById(Long id) {
        return requestRepository.findById(id).orElse(null);
    }

    public void deleteRequest(Long id) {
        requestRepository.deleteById(id);
    }

    public List<ApplicationRequest> getPendingRequests() {
        return requestRepository.findByHandled(false);
    }

    public List<ApplicationRequest> getProcessedRequests() {
        return requestRepository.findByHandled(true);
    }

    public List<Courses> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Operators> getAllOperators() {
        return operatorRepository.findAll();
    }

    public void createRequest(String userName, String commentary, String phone,
                              Long courseId, List<Long> operatorIds) {
        Courses course = courseRepository.findById(courseId).orElse(null);
        if (course == null) return;

        ApplicationRequest req = new ApplicationRequest();
        req.setUserName(userName);
        req.setCommentary(commentary);
        req.setPhone(phone);
        req.setCourse(course);

        if (operatorIds != null && !operatorIds.isEmpty()) {
            List<Operators> operators = operatorRepository.findAllById(operatorIds);
            req.setOperators(operators);
        }
        req.setHandled(false);
        requestRepository.save(req);
    }

    public void markAsProcessed(Long id) {
        ApplicationRequest req = requestRepository.findById(id).orElse(null);
        if (req != null && !req.isHandled()) {
            req.setHandled(true);
            requestRepository.save(req);
        }
    }
}