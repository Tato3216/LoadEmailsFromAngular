package laboratorio.devweb.controllers;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/emails")
public class emailController {

    @PostMapping("/load")
    public List<String> uploadFile(@RequestParam("file") MultipartFile file) {
        List<String> emailList = new ArrayList<>();
        Pattern emailPattern = Pattern.compile("[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher matcher = emailPattern.matcher(line);
                while (matcher.find()) {
                    emailList.add(matcher.group());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emailList;
    }
}