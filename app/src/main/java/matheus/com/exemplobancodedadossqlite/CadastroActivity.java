package matheus.com.exemplobancodedadossqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class CadastroActivity extends AppCompatActivity {

    EditText nome;
    EditText email;
    private MeuBanco db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nome = (EditText) findViewById(R.id.edtNome);
        email = (EditText) findViewById(R.id.edtEmail);

        db= new MeuBanco(this);
    }


    public void salvar(View view) {
        long dataCadastro = System.currentTimeMillis() / 1000L;

        Cliente c = new Cliente();
        c.setNome(nome.getText().toString());
        c.setEmail(email.getText().toString());
        c.setDataCadastro(dataCadastro);

        db.inserir(c);

        Toast.makeText(this, R.string.inserido, Toast.LENGTH_LONG).show();

        finish();
    }
}
