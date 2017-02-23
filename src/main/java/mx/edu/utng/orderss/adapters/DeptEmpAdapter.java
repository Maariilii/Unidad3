package mx.edu.utng.orderss.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mx.edu.utng.orderss.R;
import mx.edu.utng.orderss.model.DeptEmp;

/**
 * Created by Marili Arevalo on 23/02/2017.
 */

public class DeptEmpAdapter extends RecyclerView.Adapter<DeptEmpAdapter.DeptEmpViewHolder>
        implements View.OnClickListener {


    List<DeptEmp> deptEmps;
    View.OnClickListener listener;
    //Constructor
    public DeptEmpAdapter(List<DeptEmp> deptEmps){
        this.deptEmps=deptEmps;
    }
    //getter and setter de listener
    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public DeptEmpAdapter.DeptEmpViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //se inflael cardview al reciclerview
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_deptemp,parent,false);
        DeptEmpAdapter.DeptEmpViewHolder holder=new DeptEmpAdapter.DeptEmpViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(DeptEmpAdapter.DeptEmpViewHolder holder, int position) {
        holder.tvFromDate.setText(deptEmps.get(position).getFromDate());
        holder.tvToDate.setText(String.valueOf(deptEmps.get(position).getToDate()));
        holder.tvEmpNo.setText(String.valueOf(deptEmps.get(position).getEmpNo()));
        holder.ivDepTemp.setImageResource(R.mipmap.ic_launcher);
        holder.setListener(this);

    }

    @Override
    public int getItemCount() {
        return deptEmps.size();
    }
    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }

    }

    public static class DeptEmpViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        CardView cvDepTemp;
        TextView tvFromDate;
        TextView tvToDate;
        TextView tvEmpNo;
        ImageView ivDepTemp;
        ImageButton btEditDeptEmp;
        ImageButton btDeleteDeptEmp;
        View.OnClickListener listener;




        public DeptEmpViewHolder(View itemView) {
            super(itemView);
            cvDepTemp=(CardView)itemView.findViewById(R.id.cv_deptemp);
            ivDepTemp=(ImageView)itemView.findViewById(R.id.iv_deptemp);
            tvFromDate=(TextView)itemView.findViewById(R.id.tv_from_date);
            tvToDate=(TextView)itemView.findViewById(R.id.tv_toDate);
            tvEmpNo=(TextView)itemView.findViewById(R.id.tv_empNo);
            btEditDeptEmp=(ImageButton) itemView.findViewById(R.id.bt_edit_deptEmp);
            btDeleteDeptEmp=(ImageButton) itemView.findViewById(R.id.bt_delete_deptEmp);
            btEditDeptEmp.setOnClickListener(this);
            btDeleteDeptEmp.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            if (listener!=null){
                listener.onClick(v);
            }
        }

        public void setListener(View.OnClickListener listener){
            this.listener=listener;

        }
    }

}
