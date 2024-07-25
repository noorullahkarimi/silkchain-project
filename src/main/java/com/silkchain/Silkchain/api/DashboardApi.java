package com.silkchain.Silkchain.api;

import com.silkchain.Silkchain.dto.Flowmeter;
import com.silkchain.Silkchain.dto.User;
import com.silkchain.Silkchain.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard/api/v1")
public class DashboardApi {

    private static final String ADMIN_NAVBAR =
            "<li class=\"nav-item\">" +
                    "<a class=\"nav-link\" href=\"/admin\">" +
                    "<i class=\"nc-icon nc-chart-pie-35\"></i>" +
                    "<p>Dashboard</p>" +
                    "</a>" +
                    "</li>" +
                    "<li class=\"nav-item\">" +
                    "<a class=\"nav-link\" href=\"/profile\">" +
                    "<i class=\"nc-icon nc-circle-09\"></i>" +
                    "<p>User Profile</p>" +
                    "</a>" +
                    "</li>" +
                    "<li class=\"nav-item\">" +
                    "<a class=\"nav-link\" href=\"/flometer\">" +
                    "<i class=\"nc-icon nc-notes\"></i>" +
                    "<p>Flow Meter Records</p>" +
                    "</a>" +
                    "</li>";

    private static final String USER_NAVBAR =
            "<li class=\"nav-item\">" +
                    "<a class=\"nav-link\" href=\"/flometer\">" +
                    "<i class=\"nc-icon nc-notes\"></i>" +
                    "<p>Flow Meter Records</p>" +
                    "</a>" +
                    "</li>";

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private UserService userService;

    @Autowired
    private FlowmeterService flowmeterService;

    //showing the navbar by roles
    @GetMapping("/navbar")
    public String getNavbar(HttpSession session) {
        if ("ROLE_ADMIN".equals(session.getAttribute("role"))) {
            return ADMIN_NAVBAR;
        } else {
            return USER_NAVBAR;
        }
    }

    //show all record of flow-meter data for admin panel
    @GetMapping("/flowmeters")
    public Page<Flowmeter> getAllRecordOfFlowMeter(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size) {
        System.out.println("********** show table flowmeter **********");
        return dashboardService.getAllRecords(
                PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id")
        ));
    }

    //show all record of flow-meter data for admin panel
    @GetMapping("/changerow/{id}/{batchId}")
    public Map<String, Object> changeRow(@PathVariable int id,
                                         @PathVariable String batchId) {
        System.out.println("********** " + id + " **********" + batchId);
        List<String> wallets = userService.getWallets();
        Flowmeter flowmeter = flowmeterService.getRecordById(id, batchId);

        Map<String, Object> response = new HashMap<>();
        response.put("flowmeter", flowmeter);
        response.put("wallets", wallets);

        return response;
    }

    //form user show
    @GetMapping("/showuser")
    public User showUser(HttpSession session) {
        System.out.println("********** showing the profile **********");
        String userWallet = (String) session.getAttribute("wallet");
        System.out.println("********** wallet : " +session.getAttribute("wallet")+ "**********");
        return userService.findUser(userWallet);
    }

    //showing the user profile
    @GetMapping("/showuserdata")
    public UserProfileWithFlowmeters showUserData(HttpSession session) {
        String userWallet = (String) session.getAttribute("wallet");
        User user = userService.findUser(userWallet);
        List<String> flowmeters = flowmeterService.getInputsByWalletAddress(userWallet);
        return new UserProfileWithFlowmeters(user, flowmeters);
    }

    //update profile
    @PostMapping("/update")
    public ResponseEntity<?> updateProfile(@RequestBody UpdateRequest updateRequest) {
        User user = userService.updateUser(updateRequest.getWalletAddress(), updateRequest.getEmail(), updateRequest.getName(),
                updateRequest.getLastname(), updateRequest.getAddress(), updateRequest.getCity(),
                updateRequest.getCountry());
        return ResponseEntity.ok().body("saved out successfully");
    }
}
