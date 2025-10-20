package com.example2.narxoz2.controller;
import com.example2.narxoz2.class4.ApplicationRequest;
import com.example2.narxoz2.service.ApplicationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ApplicationRequestController {

    @Autowired
    private ApplicationRequestService applicationRequestService;

    @GetMapping("/")
    public String getAll(Model model) {
        model.addAttribute("requests", applicationRequestService.getAllRequests());
        return "list";
    }

    @GetMapping("/new")
    public String addForm(Model model) {
        model.addAttribute("request", new ApplicationRequest());
        return "add";
    }

    @PostMapping("/new")
    public String createRequest(@RequestParam String userName,
                                @RequestParam String courseName,
                                @RequestParam String commentary,
                                @RequestParam String phone) {
        ApplicationRequest request = new ApplicationRequest();
        request.setUserName(userName);
        request.setCourseName(courseName);
        request.setCommentary(commentary);
        request.setPhone(phone);
        applicationRequestService.saveRequest(request);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        ApplicationRequest req = applicationRequestService.getRequestById(id);
        if (req == null) {
            return "redirect:/";
        }
        model.addAttribute("request", req);
        return "details";
    }
    @PostMapping("/{id}/update")
    public String updateRequest(@PathVariable Long id,
                                @RequestParam String userName,
                                @RequestParam String courseName,
                                @RequestParam String phone,
                                @RequestParam String commentary,
                                @RequestParam(required = false) boolean handled) {
        ApplicationRequest req = applicationRequestService.getRequestById(id);
        if (req != null) {
            req.setUserName(userName);
            req.setCourseName(courseName);
            req.setPhone(phone);
            req.setCommentary(commentary);
            req.setHandled(handled);
            applicationRequestService.saveRequest(req);
        }
        return "redirect:/" + id;
    }

    @PostMapping("/{id}/process")
    public String process(@PathVariable Long id) {
        applicationRequestService.markAsProcessed(id);
        return "redirect:/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        applicationRequestService.deleteRequest(id);
        return "redirect:/";
    }

    @GetMapping("/pending")
    public String pending(Model model) {
        model.addAttribute("requests", applicationRequestService.getPendingRequests());
        return "pending";
    }

    @GetMapping("/processed")
    public String processed(Model model) {
        model.addAttribute("requests", applicationRequestService.getProcessedRequests());
        return "processed";
    }
}
