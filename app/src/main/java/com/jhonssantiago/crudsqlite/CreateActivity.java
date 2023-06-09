package com.jhonssantiago.crudsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateActivity extends AppCompatActivity {
    private EditText txtNome, txtIdade;
    private Button btSalvar;
    private BDsqlite bd;
    Pessoa p = new Pessoa();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        getSupportActionBar().setTitle("Criar Usuário");

        bd = new BDsqlite(this);

        txtNome = findViewById(R.id.nome);
        txtIdade = findViewById(R.id.idade);
        btSalvar = findViewById(R.id.salvar);

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p = criaPessoa();
                bd.inserirDados(p.getNome(),p.getIdade());
                AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
                dialog.setTitle("Usuário Adicionado!");
                dialog.setMessage("Nome: "+p.getNome()+"\nIdade: "+p.getIdade());
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Listar", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent it = new Intent(getActivity(), ListActivity.class);
                        startActivity(it);
                    }
                });
                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });
    }

    public Context getActivity(){ return this; };

    @Override
    protected void onDestroy() {
        bd.close();
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //identificar a ação de voltar a tela
            case android.R.id.home:
                //encerra a activity
                finish();
                break;
        }

        return super.onOptionsItemSelected( item );
    }

    private Pessoa criaPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(txtNome.getText().toString());
        pessoa.setIdade(Integer.parseInt(txtIdade.getText().toString()));
        return pessoa;
    }
}