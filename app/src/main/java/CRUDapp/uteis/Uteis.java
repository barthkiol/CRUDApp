package CRUDapp.uteis;

import android.app.AlertDialog;
import android.content.Context;

public class Uteis {

    public static void Alert(Context context, String mensagem){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setMessage(mensagem);
        alertDialog.setPositiveButton("ok", null);
        alertDialog.show();
    }
}
