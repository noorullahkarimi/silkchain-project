package com.silkchain.Silkchain.api;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.silkchain.Silkchain.service.LoginRequest;
import com.silkchain.Silkchain.service.RegisterRequest;
import com.silkchain.Silkchain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.silkchain.Silkchain.dto.User;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {



    private static final String API_URL = "https://gateway-arbitrum.network.thegraph.com/api/b744b816d350454cf3f3449405dd0f51/subgraphs/id/3k93SNY5Y1r4YYWEuPY9mpCm2wnGoYDKRtk82QZJ3Kvw";
    private static final String DAO = "0x4dcc3bc33af8ab00f15664a234d90a18258f33af";

    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RestTemplate restTemplate;

    //logout api
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok().body("Logged out successfully");
    }
    //register api
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        User user = userService.registerUser(registerRequest.getWalletAddress(), registerRequest.getPassword(), registerRequest.getEmail());
        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl", "/re-login");

        return ResponseEntity.ok(response);
    }
    //login api
    @PostMapping("/login")
    public Object authenticateUser(@RequestBody LoginRequest loginRequest, HttpSession session) {
        User user = userService.authenticateUser(loginRequest.getWalletAddress(), loginRequest.getPassword());
        if (user == null) {
            System.out.println("<<<<<<<<< user tried to login without wallet address >>>>>>>>>");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid wallet address or password");
        }

        // Check user role via external API
        boolean isAdmin = checkUserRole(user.getWalletAddress());

        if (isAdmin) {
            System.out.println("<<<<<<<<< user is entered with Admin account : " + user.getWalletAddress() + ">>>>>>>>>");
            userService.updateUserRole(user.getWalletAddress(), "ROLE_ADMIN");
            session.setAttribute("role", "ROLE_ADMIN");  // ذخیره نقش در نشست
            System.out.println("Role set in session: ROLE_ADMIN");
        } else {
            session.setAttribute("role", "ROLE_USER");  // نقش پیش‌فرض
            System.out.println("Role set in session: ROLE_USER");
        }

        // Redirect based on role
        String redirectUrl = isAdmin ? "/admin" : "/user";
        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl", redirectUrl);

        return ResponseEntity.ok(response);
    }

    private boolean checkUserRole(String walletAddress) {
        // Call external API and check if the walletAddress is an admin
        // This is a simplified example, replace with actual API call and logic

        try {
            // Create the query payload
            String query = "{\"query\": \"query GetMembers($dao: String!) { members(where: { dao: $dao }) { id createdAt memberAddress } }\", \"variables\": { \"dao\": \"" + DAO + "\" }}";

            // Set the headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Create the request entity
            HttpEntity<String> entity = new HttpEntity<>(query, headers);

            // Send the POST request
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(API_URL, entity, String.class);

            // Check if the response is successful
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                // Parse the response
                JsonNode root = objectMapper.readTree(responseEntity.getBody());
                JsonNode members = root.path("data").path("members");

                // Check if the wallet address is in the members list
                for (JsonNode member : members) {
                    if (member.path("memberAddress").asText().equalsIgnoreCase(walletAddress)) {
                        return true; // User is an admin
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false; // Replace with actual logic
    }
}
