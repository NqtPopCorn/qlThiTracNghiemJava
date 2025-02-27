/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.qlthitracnghiem.utils;

import java.util.stream.IntStream;
import org.json.JSONArray;

/**
 *
 * @author Asus
 */
public class ConvertUtil {
    public static String[] convertJSONArrayToArrayString(JSONArray jsonArray) {
        return IntStream.range(0, jsonArray.length())
                .mapToObj(jsonArray::optString)
                .toArray(String[]::new);
    }
}
