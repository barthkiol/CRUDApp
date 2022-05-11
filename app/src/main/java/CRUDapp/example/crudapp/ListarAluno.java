package CRUDapp.example.crudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import CRUDapp.uteis.Uteis;


import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.crudapp.R;

import java.util.List;

import CRUDapp.model.AlunoModel;
import CRUDapp.repository.AlunoRepository;
import CRUDapp.uteis.LinhaConsultarAdapter;


public class ListarAluno extends AppCompatActivity {

    ListView lista_alunos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_aluno);
        lista_alunos = (ListView) findViewById(R.id.lista_aluno);
        this.CarregarAlunos();

    }

    protected void CarregarAlunos(){
        AlunoRepository alunoRepository = new AlunoRepository(this);
        List<AlunoModel> alunos = alunoRepository.ListarAlunos();
        lista_alunos.setAdapter((ListAdapter) new LinhaConsultarAdapter(this, alunos));

    }
}