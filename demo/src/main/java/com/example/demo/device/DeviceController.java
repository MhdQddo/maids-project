package com.example.demo.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/devices")
public class DeviceController {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceController(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @GetMapping
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    @PostMapping
    public Device addDevice(@RequestBody Device device) {
        return deviceRepository.save(device);
    }

    @GetMapping("/{id}")
    public Device getDeviceById(@PathVariable Long id) {
        return deviceRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Device updateDevice(@PathVariable Long id, @RequestBody Device updatedDevice) {
        if (deviceRepository.existsById(id)) {
            updatedDevice.setId(id);
            return deviceRepository.save(updatedDevice);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteDevice(@PathVariable Long id) {
        deviceRepository.deleteById(id);
    }

    @PostMapping("/predict/{id}")
    public String getPredict(@PathVariable Long id) {
        System.out.println(id);
        if (deviceRepository.existsById(id)) {
            Device device = deviceRepository.findById(id).get();
            // Assuming the prediction endpoint is /make-prediction
            String predictionEndpoint = "http://127.0.0.1:5000/predict";

            // Create a RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();

            // Make a POST request to the prediction endpoint with the device data
            ResponseEntity<String> response = restTemplate.postForEntity(predictionEndpoint, device, String.class);

            // Return the prediction result
            return response.getBody();
        }
        return "-1";
    }
}
