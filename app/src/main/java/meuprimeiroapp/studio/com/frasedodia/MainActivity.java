package meuprimeiroapp.studio.com.frasedodia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textoNovaFrase;
    private Button botaoNovaFrase;

    private String[] frases = {
            "Muita gente traduz experiência como tempo de função, ou tempo de casa, o que não é bem verdade. A experiência é medida por resultados. Experiência é a transformação do conhecimento em resultados práticos para a organização",
            "Pássaros criados em gaiolas acreditam que voar é uma doença",
            "É melhor ser criticado pelos sábios do que ser elogiado pelos insensatos",
            "Nunca peça exatamente  mesma coisa a duas pessoas. Por mais claro que você seja, cada uma vai entender de um jeito",
            "'Onde não puderes amar, não te demores",
            "Nunca tente explicar aos colegas alguma coisa que o chefe disse. Chefes não apreciam o subordinado porta-voz. Se alguém tenm dúvida, perguntar diretamente ao chefe",
            "Não faça inimizades no trabalho. seu inimigo de hoje pode ser seu chefe amanhã"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoNovaFrase = findViewById(R.id.textoNovaFraseId);
        botaoNovaFrase = findViewById(R.id.botaoNovaFraseId);

        botaoNovaFrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random randomico = new Random();
                int numeroAleatorio = randomico.nextInt(frases.length);
                textoNovaFrase.setText(frases[numeroAleatorio]);
            }
        });

    }
}
