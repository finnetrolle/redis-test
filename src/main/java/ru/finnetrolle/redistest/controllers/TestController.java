package ru.finnetrolle.redistest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.finnetrolle.redistest.redis.RedisTestService;

import java.util.UUID;

@Controller
@RequestMapping
public class TestController {

    @Autowired
    private RedisTestService service;

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Response> save(
            @RequestParam(name = "key", defaultValue = "") String tryKey,
            @RequestParam(name = "value", defaultValue = "") String value) {
        String key = (StringUtils.isEmpty(tryKey))
                ? UUID.randomUUID().toString()
                : tryKey;
        service.save(key, value);
        return ResponseEntity.ok(new Response(key, value));
    }

    @RequestMapping(value = "/load", method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<Response> load(@RequestParam(name = "key", required = true) String key) {
        return ResponseEntity.ok(new Response(key, service.load(key)));
    }

    public static class Response {
        public String key;
        public String value;

        public Response(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public Response() {
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
