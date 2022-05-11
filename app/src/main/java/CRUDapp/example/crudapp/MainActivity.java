package CRUDapp.example.crudapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import CRUDapp.*;
import CRUDapp.model.AlunoModel;
import CRUDapp.repository.AlunoRepository;

import com.example.crudapp.R;

public class MainActivity extends AppCompatActivity {

    EditText nome;
    EditText matricula;
    EditText email;
    Button btnSalvar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nome = findViewById(R.id.nome);
        matricula = findViewById(R.id.matricula);
        email = findViewById(R.id.email);
        btnSalvar = findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewC) {
                salvarAluno();
            }
        });

    }
    protected void salvarAluno(){
        AlunoModel aluno = new AlunoModel();
        aluno.setEmail(email.getText().toString());
        aluno.setNome(nome.getText().toString());
        aluno.setMatricula(matricula.getText().toString());

        if(boAluno(aluno)){
            AlunoRepository repo = new AlunoRepository(getApplicationContext());
            repo.salvar(aluno);

            Toast.makeText(MainActivity.this, "Aluno " + aluno.getNome() + " Registrado com sucesso", Toast.LENGTH_SHORT).show();
            limparCampos();
        }
        else{
            Toast.makeText(MainActivity.this, "Preencha todos os campos" , Toast.LENGTH_SHORT).show();
        }

    }
    protected boolean boAluno(AlunoModel aluno){
        if (aluno.getNome().toString().isEmpty() || aluno.getEmail().toString().isEmpty() || aluno.getMatricula().toString().isEmpty()){
            return false;

        }else{
            return true;
        }

    }

    protected void limparCampos(){
        email.setText(null);
        nome.setText(null);
        matricula.setText(null);
    }
}