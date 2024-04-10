package com.example.demo.device;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    public List<Device> getDevices() {
        return List.of(new Device(
                1L,
                5000,
                true, // blue
                2.3, // clock_speed
                true, // dual_sim
                16, // fc
                true, // four_g
                64, // int_memory
                0.3, // m_dep
                150, // mobile_wt
                8, // n_cores
                12, // pc
                1920, // px_height
                1080, // px_width
                4096, // ram
                15, // sc_h
                10, // sc_w
                20, // talk_time
                true, // three_g
                false, // touch_screen
                true // wifi
        ));

    }
}
