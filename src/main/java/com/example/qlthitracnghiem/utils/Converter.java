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
     public static Object[] jsonArrayToArray(String jsonArrayString) {
        try {
            JSONArray jsonArray = new JSONArray(jsonArrayString);
            Object[] array = new Object[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                array[i] = jsonArray.get(i);
            }
            return array;
        } catch (JSONException e) {
            e.printStackTrace();
            return null; // Handle error appropriately
        }
    }

     public static int[] jsonArrayToStringArray(String jsonArrayString) {
         try {
             JSONArray jsonArray = new JSONArray(jsonArrayString);
             int[] array = new int[jsonArray.length()];
             for (int i = 0; i < jsonArray.length(); i++) {
                 array[i] = jsonArray.getInt(i);
             }
             return array;
         } catch (JSONException e) {
             e.printStackTrace();
             return null; // Handle error appropriately
         }
     }

    public static void main(String[] args) {
        String jsonString = "[1, 2, \"three\", true]";

        Object[] objectArray = jsonArrayToArray(jsonString);
        if (objectArray != null) {
            System.out.println("Object Array:");
            for (Object element : objectArray) {
                System.out.println(element);
            }
        }

        String jsonString2 = "[1, 2, 2]";
        int[] stringArray = jsonArrayToStringArray(jsonString2);
        if(stringArray != null){
            System.out.println("String Array:");
            for(int element: stringArray){
                System.out.println(element);
            }
        }
    }
}
