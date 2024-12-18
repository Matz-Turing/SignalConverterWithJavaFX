package com.example.signalconverter.visualization;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SignalVisualizer {

    private final Canvas canvas;
    private final GraphicsContext gc;

    public SignalVisualizer(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
    }

    public void visualizeSignal(String binarySequence, String encodingMethod) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());  // Limpar canvas antes de desenhar

        double bitWidth = canvas.getWidth() / binarySequence.length();  // Largura de cada bit no gráfico
        double currentX = 0;

        // Desenha os sinais conforme a sequência binária
        for (int i = 0; i < binarySequence.length(); i++) {
            char bit = binarySequence.charAt(i);
            drawBit(currentX, bitWidth, bit, encodingMethod);
            currentX += bitWidth;
        }
    }

    private void drawBit(double x, double bitWidth, char bit, String encodingMethod) {
        if ("NRZ".equals(encodingMethod)) {
            drawNRZ(x, bitWidth, bit);
        } else if ("RZ".equals(encodingMethod)) {
            drawRZ(x, bitWidth, bit);
        } else if ("Manchester".equals(encodingMethod)) {
            drawManchester(x, bitWidth, bit);
        }
    }

    private void drawNRZ(double x, double width, char bit) {
        // NRZ: Se o bit é 1, desenha linha no topo, se for 0, linha na parte inferior
        if (bit == '1') {
            gc.setStroke(Color.BLUE);  // Cor para 1
        } else {
            gc.setStroke(Color.RED);   // Cor para 0
        }
        gc.setLineWidth(4);
        gc.strokeLine(x, canvas.getHeight() / 2, x + width, canvas.getHeight() / 2);
    }

    private void drawRZ(double x, double width, char bit) {
        // RZ: Mostra uma linha de 1 que volta para 0 no meio
        if (bit == '1') {
            gc.setStroke(Color.BLUE);
            gc.setLineWidth(4);
            gc.strokeLine(x, canvas.getHeight() / 2, x + width / 2, canvas.getHeight() / 2);  // Linha até o meio
            gc.strokeLine(x + width / 2, canvas.getHeight() / 2, x + width / 2, canvas.getHeight() / 2 - 30);  // Linha voltando para 0
        } else {
            gc.setStroke(Color.RED);
            gc.setLineWidth(4);
            gc.strokeLine(x, canvas.getHeight() / 2, x + width, canvas.getHeight() / 2);  // Linha reta para 0
        }
    }

    private void drawManchester(double x, double width, char bit) {
        // Manchester: Transição no meio do bit
        if (bit == '1') {
            gc.setStroke(Color.GREEN);
            gc.setLineWidth(4);
            gc.strokeLine(x, canvas.getHeight() / 2, x + width / 2, canvas.getHeight() / 2 + 30);  // Transição para cima
            gc.strokeLine(x + width / 2, canvas.getHeight() / 2 + 30, x + width, canvas.getHeight() / 2);  // Transição para baixo
        } else {
            gc.setStroke(Color.ORANGE);
            gc.setLineWidth(4);
            gc.strokeLine(x, canvas.getHeight() / 2, x + width / 2, canvas.getHeight() / 2 - 30);  // Transição para baixo
            gc.strokeLine(x + width / 2, canvas.getHeight() / 2 - 30, x + width, canvas.getHeight() / 2);  // Transição para cima
        }
    }
}