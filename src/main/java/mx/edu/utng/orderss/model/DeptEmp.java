package mx.edu.utng.orderss.model;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Marili Arevalo on 23/02/2017.
 */

public class DeptEmp {
    private  String idDep;
    private String fromDate;
    private String toDate;
    private String empNo;

    public DeptEmp(String idDep, String fromDate, String toDate, String empNo) {
        this.idDep = idDep;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.empNo = empNo;
    }
    public DeptEmp(){this("","","","");
    }

    public String getIdDep() {
        return idDep;
    }

    public void setIdDep(String idDep) {
        this.idDep = idDep;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    @Override
    public String toString() {
        return "DeptEmpActivity{" +
                "idDep='" + idDep + '\'' +
                ", fromDate='" + fromDate + '\'' +
                ", toDate=" + toDate +
                ", empNo=" + empNo +
                '}';
    }

}
