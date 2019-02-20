package controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class FileController {

    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public String teste(@RequestParam String assunto){
        return UploadObject.getUrlS3(assunto);
    }
}
