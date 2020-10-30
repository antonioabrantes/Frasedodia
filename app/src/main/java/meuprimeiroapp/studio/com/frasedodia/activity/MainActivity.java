package meuprimeiroapp.studio.com.frasedodia.activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import meuprimeiroapp.studio.com.frasedodia.R;
import meuprimeiroapp.studio.com.frasedodia.config.ConfiguracaoFirebase;
import meuprimeiroapp.studio.com.frasedodia.model.DadosBib;
import meuprimeiroapp.studio.com.frasedodia.model.Frases;
import meuprimeiroapp.studio.com.frasedodia.model.Votacoes;

public class MainActivity extends AppCompatActivity {

    private int count;
    private String autor;
    private String imagem;
    private String biografia;
    private String texto;
    private int id;
    private String email;
    private Date data;;
    private int curtir;
    private Frases frase;
    private String[] buffer = new String[5000];
    private DadosBib dadosBib;
    private Votacoes votacoes;
    DatabaseReference ref2;

    private TextView textoNovaFrase;
    private ImageView imgNext;
    private ImageView imgLogin;
    private ImageView imgLike;
    private DatabaseReference firebaseReferencia = FirebaseDatabase.getInstance().getReference();
    DatabaseReference ref;
    private ValueEventListener valueEventListenerMensagem;

    private String[] frases = {
            "Muita gente traduz experiência como tempo de função, ou tempo de casa, o que não é bem verdade. A experiência é medida por resultados. Experiência é a transformação do conhecimento em resultados práticos para a organização",
            "Pássaros criados em gaiolas acreditam que voar é uma doença",
            "É melhor ser criticado pelos sábios do que ser elogiado pelos insensatos",
            "Nunca peça exatamente  mesma coisa a duas pessoas. Por mais claro que você seja, cada uma vai entender de um jeito",
            "Onde não puderes amar, não te demores",
            "Nunca tente explicar aos colegas alguma coisa que o chefe disse. Chefes não apreciam o subordinado porta-voz. Se alguém tem dúvida, pergunte diretamente ao chefe",
            "Não faça inimizades no trabalho. seu inimigo de hoje pode ser seu chefe amanhã"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoNovaFrase = (TextView) findViewById(R.id.textoNovaFraseId);
        imgNext = (ImageView) findViewById(R.id.img_next);
        imgLogin = (ImageView) findViewById(R.id.img_login);
        imgLike = (ImageView) findViewById(R.id.img_like);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/AMAZR.ttf");
        textoNovaFrase.setTypeface(font);

        if (1==0) {

            try {
                AssetManager mngr = this.getAssets();
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(mngr.open("xml/citacoes_votou.xml"));
                //Document doc = dBuilder.parse(mngr.open("xml/citacoes.xml"));
                //Document doc = dBuilder.parse(mngr.open("xml/citacoes_bib.xml"));
                doc.getDocumentElement().normalize();
                //SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                NodeList nodeContatos = doc.getElementsByTagName("row");
                int counter = nodeContatos.getLength();

                count = 0;
                for (int i = 0; i < counter; i++) {
                    Node item = nodeContatos.item(i);
                    String str = Integer.toString(count);
                    count = count + 1;

                    if (item.getNodeType() == Node.ELEMENT_NODE) {
                    //if (1 == 0) {
                        Element element = (Element) item; //Document doc = dBuilder.parse(mngr.open("xml/citacoes_votou.xml"));
                        Node nodeNome = element.getElementsByTagName("field").item(0).getChildNodes().item(0);
                        id = Integer.parseInt(nodeNome.getNodeValue());
                        nodeNome = element.getElementsByTagName("field").item(1).getChildNodes().item(0);
                        email = nodeNome.getNodeValue().toString();

                        votacoes = new Votacoes();
                        votacoes.setId(id);
                        votacoes.setEmail(email);

                        ref2 = ConfiguracaoFirebase.getFirebase();

                        try{
                            ref2.child("citacoes_votou").child(str).setValue(votacoes);
                            //firebaseReferencia.child("citacoes_votou").child(str).child("id").setValue(id);
                            //firebaseReferencia.child("citacoes_bib").child(str).child("email").setValue(email);
                            //firebaseReferencia.child("pontos").setValue("500");
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    //if (item.getNodeType() == Node.ELEMENT_NODE) {
                    if (1 == 0) {
                        //if (1==0) {
                        Element element = (Element) item; // Document doc = dBuilder.parse(mngr.open("xml/citacoes_bib.xml"));
                        Node nodeNome = element.getElementsByTagName("field").item(0).getChildNodes().item(0);
                        autor = nodeNome.getNodeValue().toString();
                        nodeNome = element.getElementsByTagName("field").item(1).getChildNodes().item(0);
                        imagem = nodeNome.getNodeValue().toString();
                        nodeNome = element.getElementsByTagName("field").item(2).getChildNodes().item(0);
                        biografia = nodeNome.getNodeValue().toString();

                        dadosBib = new DadosBib();
                        dadosBib.setAutor(autor);
                        dadosBib.setImagem(imagem);
                        dadosBib.setBiografia(biografia);

                        try{
                            firebaseReferencia.child("citacoes_bib").child(str).setValue(dadosBib);
                            //firebaseReferencia.child("citacoes_bib").child(str).child("autor").setValue(autor);
                            //firebaseReferencia.child("citacoes_bib").child(str).child("imagem").setValue(imagem);
                            //firebaseReferencia.child("citacoes_bib").child(str).child("biografia").setValue(biografia);
                            firebaseReferencia.child("pontos").setValue("200");
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    //if (item.getNodeType() == Node.ELEMENT_NODE) {
                    if (1 == 0) {
                        Element element = (Element) item; //Document doc = dBuilder.parse(mngr.open("xml/citacoes.xml"));
                        Node nodeNome = element.getElementsByTagName("field").item(0).getChildNodes().item(0);
                        id = Integer.parseInt(nodeNome.getNodeValue().toString());
                        nodeNome = element.getElementsByTagName("field").item(1).getChildNodes().item(0);
                        autor = nodeNome.getNodeValue().toString();
                        nodeNome = element.getElementsByTagName("field").item(2).getChildNodes().item(0);
                        texto = nodeNome.getNodeValue().toString();
                        nodeNome = element.getElementsByTagName("field").item(3).getChildNodes().item(0);
                        curtir = Integer.parseInt(nodeNome.getNodeValue().toString());

                        frase = new Frases();
                        frase.setAutor(autor);
                        frase.setCurtir(curtir);
                        frase.setTexto(texto);

                        try {
                            firebaseReferencia.child("pontos").setValue("300");
                            firebaseReferencia.child("frases").child(str).child("autor").setValue(autor);
                            firebaseReferencia.child("frases").child(str).child("texto").setValue(texto);
                            firebaseReferencia.child("frases").child(str).child("curtir").setValue(curtir);
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                        //nodeNome = element.getElementsByTagName("field").item(3).getChildNodes().item(0);
                        //data = formato.parse(nodeNome.getNodeValue().toString());
                        //nodeNome = element.getElementsByTagName("field").item(4).getChildNodes().item(0);
                        //curtir = Integer.parseInt(nodeNome.getNodeValue().toString());
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        int counter = 2084; // https://medium.com/android-dev-moz/firebasesql-4ee3d26a3d15
        counter = 0; // ajuste 1 para entrar ao menos uma vez no loop seguinte e ler firebase

        //Query query1 = firebaseReferencia.child("frases").orderByChild("id").equalTo("1").limitToFirst(1);

        ref = ConfiguracaoFirebase.getFirebase().child("frases");

        valueEventListenerMensagem = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 if( dataSnapshot.getValue() != null ){
                        int count2 = 1;
                        for (DataSnapshot ds:dataSnapshot.getChildren()) {
                            if (count2<=2500) {
                                Frases citacao = ds.getValue(Frases.class);
                                int id = citacao.getId();
                                String autor = citacao.getAutor().toString();
                                String texto = citacao.getTexto().toString();
                                int curtir = citacao.getCurtir();

                                Frases citacao2 = new Frases();
                                citacao2.setId(id);
                                citacao2.setAutor(autor);
                                citacao2.setTexto(texto);
                                citacao2.setCurtir(curtir);
                                //firebase.setValue( citacao2 );

                                buffer[count2] = texto;
                                count2 = count2 + 1;
                            }
                        }
                 }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
               Log.w("loadPost:onCancelled", databaseError.toException());
            }
        };

        ref.addValueEventListener(valueEventListenerMensagem);

        imgLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random randomico = new Random();
                //int numeroAleatorio = randomico.nextInt(buffer.length);
                int numeroAleatorio = randomico.nextInt(2084);
                textoNovaFrase.setText(buffer[numeroAleatorio]);
            }
        });

    }

    @Override
    protected void onStop(){
        super.onStop();
        ref.removeEventListener(valueEventListenerMensagem);
    }

}
