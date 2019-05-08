package com.unipac.alcoolgasolina;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.unipac.alcoolgasolina.domain.Combustivel;
import com.unipac.alcoolgasolina.util.SecurityPreferences;

import static android.content.Context.MODE_PRIVATE;

public class MainActivity extends AppCompatActivity {

    private ViewHolder viewHolder = new ViewHolder();
    private SecurityPreferences securityPreferences;
    public static final String MyPreferences = "MyPrefs";

    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        securityPreferences = new SecurityPreferences(this);
        sharedPreferences = getSharedPreferences(MyPreferences, MODE_PRIVATE);

        this.viewHolder.valorAlcoolEdt = (EditText) findViewById(R.id.edtValorAlcoll);
        this.viewHolder.valorGasolinaEdt = (EditText)findViewById(R.id.edtValorGasolina);
        this.viewHolder.calcularBtn = (Button)findViewById(R.id.btnCalcular);

//        calcular = (Button)findViewById(R.id.btnCalcular);
//        valorAlcool = Double.parseDouble(findViewById(R.id.edtValorAlcoll).toString());
//        valorGasolina = Double.parseDouble(findViewById(R.id.edtValorGasolina).toString());

        this.viewHolder.calcularBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(MainActivity.this.viewHolder.valorAlcoolEdt.getText().length() == 0){
                    Toast.makeText(MainActivity.this, "Campo de valor do Etanol está vazio!", Toast.LENGTH_LONG).show();
                    MainActivity.this.viewHolder.valorAlcoolEdt.requestFocus();

                }else
                    if(MainActivity.this.viewHolder.valorGasolinaEdt.getText().length() == 0){
                    Toast.makeText(MainActivity.this, "Campo de valor da Gasolina está vazio!", Toast.LENGTH_LONG).show();
                    MainActivity.this.viewHolder.valorGasolinaEdt.requestFocus();

                }else {
                    Float precoAlcool = Float.parseFloat(MainActivity.this.viewHolder.valorAlcoolEdt.getText().toString());
                    Float precoGasolina = Float.parseFloat(MainActivity.this.viewHolder.valorAlcoolEdt.getText().toString());

                    SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putFloat("VALOR_ALCOOL", precoAlcool);
                        editor.putFloat("VALOR_GASOLINA",precoGasolina);

                        editor.commit();

                        Combustivel combustivel = criaCombustivel(precoAlcool, precoGasolina);
                        insereInformacao(combustivel);


                    if (precoAlcool <= precoGasolina * 0.70) {
                        Toast.makeText(MainActivity.this, "Alcool é melhor.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Gasolina é melhor.", Toast.LENGTH_SHORT).show();
                    }


                }

            }
        });

    }
    public static class ViewHolder{
        Button calcularBtn;
        EditText valorAlcoolEdt;
        EditText valorGasolinaEdt;
    }

    public void insereInformacao(Combustivel combustivel){
        securityPreferences.guardaFloat("PRECO_ALCOOL", combustivel.getPrecoGasolina());
        securityPreferences.guardaFloat("PRECO_GASOLINA", combustivel.getPrecoGasolina());
    }

    public void lerDados(){
        Float precoAlcool = securityPreferences.recuperaFloat("PRECO_ALCOOL");
        Float precoGasolina = securityPreferences.recuperaFloat("PRECO_GASOLINA");

        Combustivel combustivel = criaCombustivel(precoAlcool, precoGasolina);
        imprime(combustivel);
    }

    private Combustivel criaCombustivel(Float precoAlcool, Float precoGasolina) {
        Combustivel combustivel = new Combustivel();
        combustivel.setPrecoAlcool(precoAlcool);
        combustivel.setPrecoGasolina(precoGasolina);
        return combustivel;
    }
    public void imprime(Combustivel combustivel){
        StringBuilder sb = new StringBuilder(1200);
        sb.append("Os dados cadastrados foram: ");
        sb.append("Preço Alcool: "+combustivel.getPrecoAlcool());
        sb.append("Preço Gasolina: "+combustivel.getPrecoGasolina());

        Toast.makeText(this, "Imprimir", Toast.LENGTH_SHORT).show();
    }

}
