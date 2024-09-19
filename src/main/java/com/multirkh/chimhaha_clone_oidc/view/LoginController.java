package com.multirkh.chimhaha_clone_oidc.view;

import jakarta.annotation.security.PermitAll;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class LoginController {
    @GetMapping("login")
    public String a()
    {return "login.html";}

    @GetMapping("image/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            Path imagePath = Paths.get("src/main/resources/static/image/" + filename);
            Resource image = new UrlResource(imagePath.toUri());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                    .body(image);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
