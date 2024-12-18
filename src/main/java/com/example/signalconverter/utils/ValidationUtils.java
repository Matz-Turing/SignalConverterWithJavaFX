package com.example.signalconverter.utils;

public class ValidationUtils {

    // Método para verificar se a sequência binária é válida
    public static boolean isValidBinary(String input) {
        return input.matches("[01]+");  // Verifica se contém apenas 0s e 1s
    }
}
