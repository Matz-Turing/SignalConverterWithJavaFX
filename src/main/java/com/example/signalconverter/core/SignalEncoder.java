package com.example.signalconverter.core;

import java.util.ArrayList;
import java.util.List;

public class SignalEncoder {

    // Método NRZ
    public static List<Integer> encodeNRZ(String binarySequence) {
        List<Integer> signal = new ArrayList<>();
        for (char bit : binarySequence.toCharArray()) {
            if (bit == '1') {
                signal.add(1); // Tensão positiva
            } else {
                signal.add(-1); // Tensão negativa
            }
        }
        return signal;
    }

    // Método RZ
    public static List<Integer> encodeRZ(String binarySequence) {
        List<Integer> signal = new ArrayList<>();
        for (char bit : binarySequence.toCharArray()) {
            if (bit == '1') {
                signal.add(1); // Pulso para 1
                signal.add(0); // Retorno a 0
            } else {
                signal.add(0); // Sem sinal para 0
                signal.add(0); // Retorno a 0
            }
        }
        return signal;
    }

    // Método Manchester
    public static List<Integer> encodeManchester(String binarySequence) {
        List<Integer> signal = new ArrayList<>();
        for (char bit : binarySequence.toCharArray()) {
            if (bit == '1') {
                signal.add(1); // Transição de baixo para cima
                signal.add(-1); // Transição de cima para baixo
            } else {
                signal.add(-1); // Transição de cima para baixo
                signal.add(1); // Transição de baixo para cima
            }
        }
        return signal;
    }
}
