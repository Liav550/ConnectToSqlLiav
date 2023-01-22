package org.example;

import java.util.ArrayList;

/**
 * @author liavb
 * name: Liav Bengayev
 * id: 325364537
 * date: 22/01/2023
 */
public class Main {
    public static void main(String[] args) {
        MyConnection c = new MyConnection();
        ArrayList<User> a = c.fillListWithData();
        User user = new User("NaveYadi",
                "Navua@gmail.com","0589471692","Nave","Temani");

    }
}