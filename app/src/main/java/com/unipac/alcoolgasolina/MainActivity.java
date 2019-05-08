package com.unipac.alcoolgasolina;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.unipac.alcoolgasolina.domain.Combustivel;
import com.unipac.alcoolgasolina.util.SecurityPreferences;

public class MainActivity extends AppCompatActivity {
    private Button calcular;
    private Double valorAlcool;
    private Double valorGasolina;
    private Double resultado;

    private ViewHolder viewHolder = new ViewHolder();
    private SecurityPreferences securityPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        securityPreferences = new SecurityPreferences(this);

        this.viewHolder.valorAlcoolEdt = (EditText) findViewById(R.id.edtValorAlcoll);
        this.viewHolder.valorGasolinaEdt = (EditText)findViewById(R.id.edtValorGasolina);
        this.viewHolder.calcularBtn = (Button)findViewById(R.id.btnCalcular);

//        valorAlcool = Double.parseDouble()




//        calcular = (Button)findViewById(R.id.btnCalcular);
//        valorAlcool = Double.parseDouble(findViewById(R.id.edtValorAlcoll).toString());
//        valorGasolina = Double.parseDouble(findViewById(R.id.edtValorGasolina).toString());

        this.viewHolder.calcularBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double precoAlcool = Double.parseDouble(MainActivity.this.viewHolder.valorAlcoolEdt.getText().toString());
                Double precoGasolina = Double.parseDouble(MainActivity.this.viewHolder.valorAlcoolEdt.getText().toString());

                Combustivel combustivel = criaCombustivel(precoAlcool, precoGasolina);
                insereInformacao(combustivel);

                if(precoAlcool <= precoGasolina * 0.70) {
                        Toast.makeText(MainActivity.this, "Alcool é melhor.", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                        Toast.makeText(MainActivity.this, "Gasolina é melhor.", Toast.LENGTH_SHORT).show();
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
        Double precoAlcool = securityPreferences.recuperaFloat("PRECO_ALCOOL");
        Double precoGasolina = securityPreferences.recuperaFloat("PRECO_GASOLINA");

        Combustivel combustivel = criaCombustivel(precoAlcool, precoGasolina);
        imprime(combustivel);
    }

    private Combustivel criaCombustivel(Double precoAlcool, Double precoGasolina) {
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
