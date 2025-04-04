package com.example.calculadora1;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;



public class MainActivity extends Activity {
    private EditText editTextValor1;
    private EditText editTextValor2;
    private TextView TextViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextValor1=findViewById(R.id.EditTextValor1);
        editTextValor2=findViewById(R.id.EditTextValor2);
        TextViewResult =findViewById(R.id.textViewResult);



        Button ButtonSoma = findViewById(R.id.buttonSoma);
        ButtonSoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Operacao("soma");
            }
        });

        Button ButtonSub = findViewById(R.id.buttonSub);
        ButtonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Operacao("sub");
            }
        });

        Button ButtonMul = findViewById(R.id.buttonMul);
        ButtonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Operacao("mul");
            }
        });


        Button ButtonDiv = findViewById(R.id.buttonDiv);
        ButtonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Operacao("div");
            }
        });
    }
    public void Operacao(String operacao) {
        try {
            int valor1 = Integer.parseInt(editTextValor1.getText().toString());
            int valor2 = Integer.parseInt(editTextValor2.getText().toString());
            double resultado = 0.0;

            switch (operacao) {
                case "soma":
                    resultado = valor1 + valor2;
                    break;
                case "sub":
                    resultado = valor1 - valor2;
                    break;
                case "mul":
                    resultado = valor1 * valor2;
                    break;
                case "div":
                    if (valor2 != 0) {
                        resultado = (double) valor1 / valor2;
                    } else {
                        TextViewResult.setText("Erro:Voce esta fazendo divisão por zero esta operação não é válida!");
                        return;
                    }
                    break;
            }
            TextViewResult.setText("Resultado: " + resultado);
        } catch (NumberFormatException e) {
            TextViewResult.setText("Erro: A entrada não é válida ");
        }
    }
}
