package com.geniusvjr.unicornhttp;

/**
 * Created by Stay on 4/7/15.
 * Powered by www.stay4it.com
 */
public class User {
    //    {"ret":200,"data":{"id":"2","account":"stay4it","email":"stay4it@163.com","username":"stay","password":"123456","avatar":"","token":"lI7oi96+8Q\/TIib9dSpy3mj7maC\/6ZEfI3HdwT\/ZwQI="},"msg":""}
    public String id;
    public String account;
    public String email;
    public String username;
    public String token;

    @Override
    public String toString() {
        return username + " : " + email;
    }
}
