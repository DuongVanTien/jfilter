package com.json.ignore.mock;


import com.json.ignore.filter.field.FieldFilterSetting;
import com.json.ignore.filter.file.FileFilterSetting;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class MockWebService {

    @FieldFilterSetting(fields = {"id", "password"})
    @RequestMapping(value = "/customers/signInSingleAnnotation",
            params = {"email", "password"}, method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public MockUser signInSingleAnnotation(@RequestParam("email") String email, @RequestParam("password") String password) {
       return MockClasses.getUserMock();
    }

    @FileFilterSetting(fileName = "configMockWebService.xml")
    @RequestMapping(value = "/customers/signInFileAnnotation",
            params = {"email", "password"}, method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public MockUser signInFileAnnotation(@RequestParam("email") String email, @RequestParam("password") String password) {
        return MockClasses.getUserMock();
    }



}