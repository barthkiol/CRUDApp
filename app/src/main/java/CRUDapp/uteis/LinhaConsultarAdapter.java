package CRUDapp.uteis;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crudapp.R;

import java.util.ArrayList;
import java.util.List;

import CRUDapp.example.crudapp.EditAcitivity;
import CRUDapp.example.crudapp.ListarAluno;
import CRUDapp.model.AlunoModel;
import CRUDapp.repository.AlunoRepository;


public class LinhaConsultarAdapter extends BaseAdapter{

    private static LayoutInflater layoutInflater = null;
    private ListarAluno lista;
    List<AlunoModel> alunoModels = new ArrayList();

    AlunoRepository alunoRepository;

    public LinhaConsultarAdapter(ListarAluno lista, List<AlunoModel> alunoModels){
        this.alunoModels = alunoModels;
        this.lista = lista;
        layoutInflater = (LayoutInflater) this.lista.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.alunoRepository = new AlunoRepository(lista);
    }


    public void AtualizarLista(){
        this.alunoModels.clear();
        this.alunoModels = alunoRepository.ListarAlunos();
        this.notifyDataSetChanged();
    }


    @Override
    public int getCount(){
        return alunoModels.size();
    }

    @Override
    public Object getItem (int position){
        return position;
    }

    @Override
    public long getItemId (int position){
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        final View viewLinhaLista = layoutInflater.inflate(R.layout.acitivity_linha_consultar, null);
        TextView textViewId_aluno = (TextView) viewLinhaLista.findViewById(R.id.txtIdAluno);
        TextView textViewNome = (TextView) viewLinhaLista.findViewById(R.id.txtNome);
        TextView textViewMatricula = (TextView) viewLinhaLista.findViewById(R.id.txtMatricula);
        TextView textViewEmail = (TextView) viewLinhaLista.findViewById(R.id.txtEmail);
        Button btnExcluir = (Button) viewLinhaLista.findViewById(R.id.btnExcluir);
        Button btnEditar = (Button) viewLinhaLista.findViewById(R.id.btnEditar);

        textViewId_aluno.setText(String.valueOf(alunoModels.get(position).getId()));
        textViewNome.setText(alunoModels.get(position).getNome());
        textViewMatricula.setText(alunoModels.get(position).getMatricula());
        textViewEmail.setText(alunoModels.get(position).getEmail());

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alunoRepository.excluir(alunoModels.get(position).getId());
                Toast.makeText(lista, "Registro excluido com sucesso!", Toast.LENGTH_SHORT).show();
                AtualizarLista();

            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(lista, EditAcitivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("id_aluno", alunoModels.get(position).getId());

                lista.startActivity(intent);

            }
        });
        return viewLinhaLista;

    }




}
