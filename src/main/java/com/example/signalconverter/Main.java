package com.example.signalconverter;

import com.example.signalconverter.visualization.SignalVisualizer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Cria o Canvas para a visualização
        Canvas canvas = new Canvas(600, 200);
        SignalVisualizer visualizer = new SignalVisualizer(canvas);

        // Entrada de dados
        TextField binaryInput = new TextField();
        binaryInput.setPromptText("Digite a sequência binária (ex: 101010)");

        // ComboBox para selecionar o método de codificação
        ComboBox<String> encodingMethods = new ComboBox<>();
        encodingMethods.getItems().addAll("NRZ", "RZ", "Manchester");
        encodingMethods.setValue("NRZ");

        // Botão para gerar o gráfico
        encodingMethods.setOnAction(e -> {
            String binarySequence = binaryInput.getText();
            String encodingMethod = encodingMethods.getValue();
            visualizer.visualizeSignal(binarySequence, encodingMethod);
        });

        // Layout
        VBox layout = new VBox(10, binaryInput, encodingMethods, canvas);
        StackPane root = new StackPane(layout);

        // Cena e palco
        Scene scene = new Scene(root, 600, 300, Color.WHITE);
        primaryStage.setTitle("Visualizador de Sinal");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}