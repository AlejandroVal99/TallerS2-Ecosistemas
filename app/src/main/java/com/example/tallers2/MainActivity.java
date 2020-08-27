package com.example.tallers2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList <Pregunta> preguntas;
    private int contador;
    private int nQuestion;
    private Button bt_answer;
    private Button bt_tryAgain;
    private TextView question;
    private EditText answer;
    private TextView contadorText;
    private TextView timer;
    private int nPartidas;
    private int goTime;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        bt_answer = findViewById(R.id.bt_Answer);
        bt_tryAgain = findViewById(R.id.bt_tryAgain);
        question = findViewById(R.id.question);
        answer = findViewById(R.id.answer);
        contadorText = findViewById(R.id.contador_text);
        timer = findViewById(R.id.timer);

        preguntas = new ArrayList<Pregunta>();
        contador = 0;
        nPartidas = 0;

        bt_tryAgain.setVisibility(View.GONE);

        makeQuestions();

        chooseQuesions();

        bt_answer.setOnClickListener(
                (v) -> {
                    if(nPartidas == 0){
                        goTime = 30;
                        new Thread (
                                ()->{
                                    while(goTime > 0){
                                        goTime--;

                                        runOnUiThread(
                                                ()-> {
                                                    timer.setText("Tiempo: " + goTime);
                                                if(goTime == 0){
                                                    bt_tryAgain.setVisibility(View.VISIBLE);
                                                    bt_answer.setVisibility(View.GONE);
                                                    chooseQuesions();
                                                    answer.setText("");
                                                }
                                                }
                                        );

                                        try {
                                            Thread.sleep(1000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                }
                        ).start();
                        nPartidas = 1;
                    }
                    if(Integer.parseInt(answer.getText().toString()) == preguntas.get(nQuestion).getRespuesta()){
                        contador++;
                        answer.setText("");
                        chooseQuesions();
                    }else{
                        answer.setText("");
                        chooseQuesions();
                    }
                    contadorText.setText("Respuestas correctas: "+contador);
                }
        );

        bt_tryAgain.setOnClickListener(
                (v) -> {
                    bt_answer.setVisibility(View.VISIBLE);
                    bt_tryAgain.setVisibility(View.GONE);
                    contador = 0;
                    contadorText.setText("Respuestas correctas: "+contador);
                    goTime = 30;
                    new Thread (
                            ()->{
                                while(goTime > 0){
                                    goTime--;

                                    runOnUiThread(
                                            ()-> {
                                                timer.setText("Tiempo: " + goTime);
                                                if(goTime == 0){
                                                    bt_tryAgain.setVisibility(View.VISIBLE);
                                                    bt_answer.setVisibility(View.GONE);
                                                    chooseQuesions();
                                                    answer.setText("");
                                                }
                                            }
                                    );
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                    ).start();
                }
        );
    }

   public void makeQuestions(){
        for(int i = 0; i < 50; i++){
            int numero1 = (int) (Math.random()*10 + 1);
            int numero2 = (int) (Math.random()*10 + 1);
            preguntas.add(new Pregunta(numero1,numero2));

        }
    }

    public void chooseQuesions(){
        nQuestion = (int) (Math.random()*preguntas.size());
        question.setText(""+preguntas.get(nQuestion).getEnunciado1()+"x"+preguntas.get(nQuestion).getEnunciado2());
    }
}