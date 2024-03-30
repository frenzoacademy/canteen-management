//package com.example.CanteenManagementSystem.Controller;
//
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.security.access.prepost.PreAuthorize;
//
//@RestController
//@RequestMapping("/logins")
//public class LoginController {
//
////    @PostMapping("/user/login")
////    @PreAuthorize("hasRole('USER')")
////    public String userLogin(@RequestBody LoginRequest loginRequest) {
////        return "User login successful for user: " + loginRequest.getUsername();
////    }
////
////    @PostMapping("/admin/login")
////    @PreAuthorize("hasRole('ADMIN')")
////    public String adminLogin(@RequestBody LoginRequest loginRequest) {
////        return "Admin login successful for user: " + loginRequest.getUsername();
////    }
//    
////    @GetMapping("/user/login")
////    public 
//    
//    
//    static class LoginRequest {
//        private String username;
//        private String password;
//
//        // Getters and setters
//
//        public String getUsername() {
//            return username;
//        }
//
//        public void setUsername(String username) {
//            this.username = username;
//        }
//
//        public String getPassword() {
//            return password;
//        }
//
//        public void setPassword(String password) {
//            this.password = password;
//        }
//    }
//}
