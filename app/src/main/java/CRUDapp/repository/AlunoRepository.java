package CRUDapp.repository;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import CRUDapp.model.*;
import CRUDapp.uteis.*;

import java.util.ArrayList;
import java.util.List;

import CRUDapp.model.AlunoModel;
import CRUDapp.uteis.DataBaseUtil;


public class AlunoRepository {

    DataBaseUtil dataBaseUtil;

    public AlunoRepository(Context context){
        dataBaseUtil = new DataBaseUtil(context);
    }

    //salvar insert
    public void salvar(AlunoModel alunoModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", alunoModel.getNome());
        contentValues.put("email", alunoModel.getEmail());
        contentValues.put("matricula", alunoModel.getMatricula());

        dataBaseUtil.GetConexaoDataBase().insert("ALUNO", null, contentValues);

    }

    @SuppressLint("Range")
    public List<AlunoModel> ListarAlunos(){

        ArrayList palavras = new ArrayList();
        StringBuilder stringBuilderListAluno = new StringBuilder();
        stringBuilderListAluno.append("Select * from ALUNO ");
        stringBuilderListAluno.append("order by nome");


        Cursor cursor = dataBaseUtil.GetConexaoDataBase().rawQuery(stringBuilderListAluno.toString(), null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){ //tentar utilizar o is last
            AlunoModel alunoModel = new AlunoModel();
            alunoModel.setId(cursor.getInt(cursor.getColumnIndex("id_aluno")));
            alunoModel.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            alunoModel.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            alunoModel.setMatricula(cursor.getString(cursor.getColumnIndex("matricula")));
            palavras.add(alunoModel);
            cursor.moveToNext();
        }
        return palavras;
    }

    public Integer excluir(int codigo){

        return dataBaseUtil.GetConexaoDataBase().delete("ALUNO", "id_aluno = ? ",
        new String[]{Integer.toString(codigo)});
    }

    @SuppressLint("Range")
    public AlunoModel GetAluno(int num){
        Cursor cursor = dataBaseUtil.GetConexaoDataBase().rawQuery(
                "select * FROM ALUNO where id_aluno = " + num, null );
        cursor.moveToFirst();
        AlunoModel alunoModel = new AlunoModel();
        alunoModel.setId(cursor.getInt(cursor.getColumnIndex("id_aluno")));
        alunoModel.setNome(cursor.getString(cursor.getColumnIndex("nome")));
        alunoModel.setEmail(cursor.getString(cursor.getColumnIndex("email")));
        alunoModel.setMatricula(cursor.getString(cursor.getColumnIndex("matricula")));

        return alunoModel;
    }

    public void editar(AlunoModel aluno){

        ContentValues content = new ContentValues();
        content.put("nome", aluno.getNome());
        content.put("matricula", aluno.getMatricula());
        content.put("email", aluno.getEmail());

        dataBaseUtil.GetConexaoDataBase().update("ALUNO", content, "id_aluno = ?",
                new String[]{Integer.toString(aluno.getId())});

    }


}
