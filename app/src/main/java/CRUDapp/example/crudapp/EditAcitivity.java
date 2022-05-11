package CRUDapp.example.crudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.crudapp.R;

import CRUDapp.model.AlunoModel;
import CRUDapp.repository.AlunoRepository;
import CRUDapp.uteis.Uteis;

public class EditAcitivity extends AppCompatActivity {

    EditText nome;
    EditText email;
    EditText matricula;
    EditText id;
    AlunoModel aluno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_acitivity);
        nome = findViewById(R.id.nome);
        email = findViewById(R.id.email);
        matricula = findViewById(R.id.matricula);
        CarregaValoresCampos();



    }

    protected void CarregaValoresCampos(){
        AlunoRepository alunoRepo = new AlunoRepository(this);
        Bundle extra = this.getIntent().getExtras();
        int id = extra.getInt("id_aluno");
        AlunoModel alunoModel = new AlunoRepository(this).GetAluno(id);
        nome.setText(alunoModel.getMatricula());
        email.setText(alunoModel.getEmail());
        matricula.setText(alunoModel.getMatricula());
    }

    protected void Alertar_onClick(){
        AlunoModel alunoModel = new AlunoModel();
        alunoModel.setId(Integer.valueOf(Integer.parseInt(this.id.getText().toString())));
        alunoModel.setNome(this.id.getText().toString());
        alunoModel.setEmail(this.id.getText().toString());
        alunoModel.setMatricula(this.id.getText().toString());
        new AlunoRepository(this).editar(alunoModel);
        //Uteis.Alert(AlunoRepository, "Editado");
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Registro Alterado");
        alertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(getApplicationContext(), ListarAluno.class);
                startActivity(intent);
                finish();
            }
        });
        alertDialog.show();
    }
}