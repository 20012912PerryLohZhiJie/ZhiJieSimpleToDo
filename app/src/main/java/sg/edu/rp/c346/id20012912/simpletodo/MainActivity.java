package sg.edu.rp.c346.id20012912.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.net.MailTo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    EditText etElement, etElementindex;

    Spinner spinner;

    Button btnadd;
    Button btnremove;
    Button btnclear;

    ListView ViewTask;
    ArrayList<String> alTask;
    ArrayAdapter<String> aaTask;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etElement = findViewById(R.id.editTask);
        etElementindex = findViewById(R.id.editTaskIndex);

        spinner= findViewById(R.id.Spinner);

        btnadd = findViewById(R.id.btnAdd);
        btnremove = findViewById(R.id.btnRemove);
        btnclear = findViewById(R.id.btnClear);

        ViewTask = findViewById(R.id.listViewTask);

        alTask = new ArrayList<>();


        aaTask = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,alTask);
        ViewTask.setAdapter(aaTask);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                switch (position)
                {
                    case 0:
                        etElement.setHint("Type in new Task Here");
                        btnadd.setEnabled(true);
                        btnremove.setEnabled(false);
                        break;

                    case 1:
                        etElement.setHint("Type in index of Task to be removed");
                        btnadd.setEnabled(false);
                        btnremove.setEnabled(true);
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        btnadd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String task = etElement.getText().toString();
                alTask.add(task);
                aaTask.notifyDataSetChanged();
                etElement.setText(" ");

            }
        });

        btnremove.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int pos = Integer.parseInt(etElementindex.getText().toString());

                if(alTask.size()==0)
                {
                    Toast.makeText(MainActivity.this, "Nothing to remove",Toast.LENGTH_LONG).show();
                    return;
                }

                if(pos > alTask.size()-1)
                {
                    Toast.makeText(MainActivity.this, "Nothing at that index to remove",Toast.LENGTH_LONG).show();
                    return;
                }

                alTask.remove(pos);
                aaTask.notifyDataSetChanged();
                etElement.setText(" ");
                etElementindex.setText(null);
            }
        });

        btnclear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                alTask.clear();
                aaTask.notifyDataSetChanged();
                etElement.setText(" ");
                etElementindex.setText(" ");
            }
        });

        ViewTask.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Toast.makeText(MainActivity.this, " " + alTask.get(position), Toast.LENGTH_LONG).show();
                String seetask = ViewTask.getSelectedItem().toString();
                etElement.setText(" " + position);
            }
        });


    }
}