package com.example.demo;

import com.example.demo.domain.Device;
import com.example.demo.repos.DeviceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private DeviceRepo deviceRepo;

    @GetMapping
    public String main(Map<String, Object> model) {
        setDevices(model);
        return "main";
    }

    @PostMapping
    public String mainData(@RequestParam String name, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date, Map<String, Object> model) {
        Device device = new Device();
        device.setName(name);
        device.setDate(date);
        deviceRepo.save(device);
        setDevices(model);
        return "main";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Integer id, Map<String, Object> model) {
        deviceRepo.deleteById(id);
        setDevices(model);
        return "main";
    }

    private void setDevices(Map<String, Object> model) {
        Iterable<Device> device = deviceRepo.findAll();
        model.put("devices", device);
    }
}