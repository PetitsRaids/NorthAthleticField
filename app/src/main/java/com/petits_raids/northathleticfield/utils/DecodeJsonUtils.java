package com.petits_raids.northathleticfield.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.petits_raids.northathleticfield.gson.Employee;

import java.util.List;

public class DecodeJsonUtils {

    public static List<Employee> decodeJson(String jsonStr){
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, new TypeToken<List<Employee>>(){}.getType());
    }
}
