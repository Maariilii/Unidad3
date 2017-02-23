package mx.edu.utng.orderss;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mx.edu.utng.orderss.adapters.DeptEmpAdapter;
import mx.edu.utng.orderss.model.DeptEmp;
import mx.edu.utng.orderss.sqlite.DBOperations;

/**
 * Created by Marili Arevalo on 23/02/2017.
 */

public class DeptEmpActivity extends AppCompatActivity {
    private EditText etFromDate;
    private EditText etToDate;
    private EditText etEmpNo;
    private Button btSave;
    private DBOperations operations;
    private DeptEmp deptEmp = new DeptEmp();
    private RecyclerView rvDeptEmps;
    private List<DeptEmp> deptEmps=new ArrayList<DeptEmp>();
    private DeptEmpAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deptemp);

        //iniciacion de la isntancia
        operations=DBOperations.getDBOperations(getApplicationContext());
        deptEmp.setIdDep("");


        initComponents();
    }
    private void initComponents(){
        rvDeptEmps=(RecyclerView)findViewById(R.id.rv_deptEmp_list);
        rvDeptEmps.setHasFixedSize(true);
        LinearLayoutManager layoutManeger=new LinearLayoutManager(this);
        rvDeptEmps.setLayoutManager(layoutManeger);
        //
        getDeptEmps();
        adapter=new DeptEmpAdapter(deptEmps);

        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.bt_delete_deptEmp:
                        operations.deleteDeptEmp(deptEmps.get(rvDeptEmps.getChildPosition((View)v.getParent().getParent())).getIdDep());
                        getDeptEmps();
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.bt_edit_deptEmp:

                        Cursor c = operations.getDeptEmpById(deptEmps.get(
                                rvDeptEmps.getChildPosition(
                                        (View)v.getParent().getParent())).getIdDep());
                        if (c!=null){
                            if (c.moveToFirst()){
                                deptEmp = new DeptEmp(c.getString(1),c.getString(2),c.getString(3),c.getString(4));
                            }
                            etFromDate.setText(deptEmp.getFromDate());
                            etToDate.setText(deptEmp.getToDate());
                            etEmpNo.setText(deptEmp.getEmpNo());
                        }else{
                            Toast.makeText(getApplicationContext(),"Registro no encontrado",Toast.LENGTH_SHORT).show();
                        }
                        break;
                }

            }
        });
        rvDeptEmps.setAdapter(adapter);

        etFromDate=(EditText)findViewById(R.id.et_fromDate);
        etToDate=(EditText)findViewById(R.id.et_toDate);
        etEmpNo=(EditText)findViewById(R.id.et_empNo);

        btSave=(Button)findViewById(R.id.bt_save);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!deptEmp.getIdDep().equals("")){
                    deptEmp.setFromDate(etFromDate.getText().toString());
                    deptEmp.setToDate(etToDate.getText().toString());
                    deptEmp.setEmpNo(etEmpNo.getText().toString());
                    operations.updateDeptEmp(deptEmp);

                }else {
                    deptEmp = new DeptEmp("", etFromDate.getText().toString(),etToDate.getText().toString(),etEmpNo.getText().toString());
                    operations.insertDeptEmp(deptEmp);
                }
                getDeptEmps();
                clearData();
                adapter.notifyDataSetChanged();
            }
        });

    }
    private void getDeptEmps(){
        Cursor c =operations.getDeptEmps();
        deptEmps.clear();
        if(c!=null){
            while (c.moveToNext()){
                deptEmps.add(new DeptEmp(c.getString(1),c.getString(2),c.getString(3),c.getString(4)));
            }
        }

    }

    private void clearData(){
        etFromDate.setText("");
        etToDate.setText("");
        etEmpNo.setText("");
        deptEmp=null;
        deptEmp= new DeptEmp();
        deptEmp.setIdDep("");
    }
}
