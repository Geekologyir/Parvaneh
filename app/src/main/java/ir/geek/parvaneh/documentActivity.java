package ir.geek.parvaneh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class documentActivity extends AppCompatActivity {

    TextView changPersonalInfo;
    TextView txtpersonalinfo;
    TextView changcorporealInfo;
    TextView txtcorporealInfo;
    EditText Fname;
    EditText Lname;
    EditText size;
    EditText weight;
    EditText place;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document);

        TextView changPersonalInfo=(TextView)findViewById(R.id.changPersonalInfo);
        changPersonalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(documentActivity.this,Chang_Document.class));
                finish();
            }
        });
        TextView txtpersonalinfo=(TextView)findViewById(R.id.txtcorporealInfo);
        TextView changcorporealInfo=(TextView)findViewById(R.id.changcorporealInfo);
        changcorporealInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(documentActivity.this,Chang_Document.class));
                finish();
            }
        });
        TextView txtcorporealInfo=(TextView)findViewById(R.id.txtcorporealInfo);

        EditText Fname=(EditText)findViewById(R.id.Fname);
        EditText Lname=(EditText)findViewById(R.id.Lname);
        EditText size=(EditText)findViewById(R.id.size);
        EditText weight=(EditText)findViewById(R.id.weight);
        EditText place=(EditText)findViewById(R.id.place);
    }
}
