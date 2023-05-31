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
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    private EditText txtNome, txtIdade;
    private Button btSalvar;
    private BDsqlite bd;
    Pessoa p = new Pessoa();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        getSupportActionBar().setTitle("Atualizar Usuário");

        bd = new BDsqlite(this);
        Intent it = getIntent();
        int id = it.getIntExtra("id",0);
        String nome = it.getStringExtra("nome");
        int idade = it.getIntExtra("idade",0);

        txtNome = findViewById(R.id.upNome);
        txtIdade = findViewById(R.id.upIdade);
        btSalvar = findViewById(R.id.upSalvar);
        txtNome.setText(nome);
        txtIdade.setText(String.valueOf(idade));

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p = criaPessoa();
                p.setNome(txtNome.getText().toString());
                p.setIdade(Integer.parseInt(txtIdade.getText().toString()));
                String idade = String.valueOf(p.getIdade());
                bd.update(id, "NOME", p.getNome());
                bd.update(id, "IDADE", idade);
                Toast.makeText(getActivity(), "Usuario atualizado", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(getActivity(), ListActivity.class);
                startActivity(it);
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