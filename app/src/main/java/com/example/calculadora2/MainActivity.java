package com.example.calculadora2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//Teste

public class MainActivity extends Activity implements View.OnClickListener {

    private Button Num0, Num1, Num2, Num3, Num4, Num5, Num6, Num7, Num8, Num9;
    private Button Soma, Div, Mul, Sub, Ponto, Igual, backspace, Apaga;
    private TextView textViewResult;

    private String operador = "";
    private boolean novoCalculo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InstanciarComponentes();
        setListeners();
    }

    private void InstanciarComponentes() {
        textViewResult = findViewById(R.id.textViewResult);

        Num0 = findViewById(R.id.Num0);
        Num1 = findViewById(R.id.Num1);
        Num2 = findViewById(R.id.Num2);
        Num3 = findViewById(R.id.Num3);
        Num4 = findViewById(R.id.Num4);
        Num5 = findViewById(R.id.Num5);
        Num6 = findViewById(R.id.Num6);
        Num7 = findViewById(R.id.Num7);
        Num8 = findViewById(R.id.Num8);
        Num9 = findViewById(R.id.Num9);
        Soma = findViewById(R.id.Soma);
        Sub = findViewById(R.id.Sub);
        Div = findViewById(R.id.Div);
        Mul = findViewById(R.id.Mul);
        Ponto = findViewById(R.id.Ponto);
        Apaga = findViewById(R.id.Apaga);
        Igual = findViewById(R.id.Igual);
        backspace = findViewById(R.id.backspace);
    }

    private void setListeners() {
        Button[] buttons = { Num0, Num1, Num2, Num3, Num4, Num5, Num6, Num7, Num8, Num9,
                Soma, Sub, Div, Mul, Ponto, Igual, Apaga, backspace };

        for (Button button : buttons) {
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        String atual = textViewResult.getText().toString();

        if (v instanceof Button) {
            Button b = (Button) v;
            int id = v.getId();

            if (id == R.id.Apaga) {
                textViewResult.setText("");
                return;
            }

            if (id == R.id.backspace && !atual.isEmpty()) {
                textViewResult.setText(atual.substring(0, atual.length() - 1));
                return;
            }

            if (id == R.id.Igual) {
                calcularResultado();
                return;
            }

            if (id == R.id.Ponto && podeAdicionarPonto(atual)) {
                textViewResult.setText(atual + ".");
                return;
            }

            if (id == R.id.Soma || id == R.id.Sub || id == R.id.Mul || id == R.id.Div) {
                if (!atual.isEmpty() && !temOperador(atual)) {
                    operador = b.getText().toString();
                    textViewResult.setText(atual + " " + operador + " ");
                }
                return;
            }

            if (novoCalculo) {
                atual = "";
                novoCalculo = false;
            }

            textViewResult.setText(atual + b.getText().toString());
        }
    }

    private boolean temOperador(String texto) {
        return texto.contains("+") || texto.contains("-") || texto.contains("*") || texto.contains("/");
    }

    private boolean podeAdicionarPonto(String texto) {
        String[] partes = texto.split(" ");
        String ultimaParte = partes[partes.length - 1];
        return !ultimaParte.contains(".");
    }

    private void calcularResultado() {
        try {
            String[] partes = textViewResult.getText().toString().split(" ");
            if (partes.length < 3) return;

            double num1 = Double.parseDouble(partes[0]);
            double num2 = Double.parseDouble(partes[2]);
            String op = partes[1];

            double resultado = 0;
            switch (op) {
                case "+": resultado = num1 + num2; break;
                case "-": resultado = num1 - num2; break;
                case "*": resultado = num1 * num2; break;
                case "/":
                    if (num2 == 0) {
                        textViewResult.setText("ERROR");
                        return;
                    }
                    resultado = num1 / num2;
                    break;
            }

            String resultadoStr = String.valueOf(resultado);
            if (resultadoStr.endsWith(".0")) {
                resultadoStr = resultadoStr.replace(".0", "");
            }

            textViewResult.setText(resultadoStr);
            novoCalculo = true;
        } catch (Exception e) {
            textViewResult.setText("ERROR");
        }
    }
}