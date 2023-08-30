package apiTest.day06;

import apiPOJOTemplates.C01_PetStoreUser;
import com.google.gson.Gson;
import org.testng.annotations.Test;

import java.util.Map;

public class C04_GSON_Test {
    /**
     {
     "id": 9223372036854776000,
     "username": "Miky",
     "firstName": "mike",
     "lastName": "masters",
     "email": "mike@gmail.com",
     "password": "Test1234",
     "phone": "55512345",
     "userStatus": 0
     }
     */

    @Test
    public void jsonToMap() {
        Gson gson = new Gson();

        String myJsonBody = "{\n" +
                "  \"id\": 9223372036854776000,\n" +
                "  \"username\": \"Miky\",\n" +
                "  \"firstName\": \"mike\",\n" +
                "  \"lastName\": \"masters\",\n" +
                "  \"email\": \"mike@gmail.com\",\n" +
                "  \"password\": \"Test1234\",\n" +
                "  \"phone\": \"55512345\",\n" +
                "  \"userStatus\": 0\n" +
                "}";

        System.out.println(myJsonBody);

        //gson to converting to map
        Map<String, Object> dataMap = gson.fromJson(myJsonBody,Map.class);
        System.out.println("dataMap = " + dataMap);

        //gson converting to object class
        C01_PetStoreUser oneUser = gson.fromJson(myJsonBody,C01_PetStoreUser.class);
        System.out.println("oneUser = " + oneUser);

        //Serialization
        //Java collection or POJO to JSON

        C01_PetStoreUser petStoreUser = new C01_PetStoreUser(12,"Miky","mike","good","Test123","1234","555",55);
        String jsonUser = gson.toJson(petStoreUser);
        System.out.println("jsonUser = " + jsonUser);

    }
}
