/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.qlthitracnghiem.utils;

import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author light
 */
public class Converter {
     public static JSONArray stringArrayToJsonArray(String[] stringArray) {

         JSONArray jsonArray = new JSONArray();
        if (stringArray != null) {
            for (String str : stringArray) {
                jsonArray.put(str);
            }
        }
        return jsonArray;
    }

    // Function to convert a JSONArray to a String array
    public static String[] jsonArrayToStringArray(JSONArray jsonArray) throws JSONException {
        if (jsonArray == null) {
            return null; // or return new String[0];
        }

        String[] stringArray = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            if (!jsonArray.isNull(i)) {
                stringArray[i] = jsonArray.getString(i);
            } else {
                stringArray[i] = null; // or handle null values as needed
            }
        }
        return stringArray;
    }
}
