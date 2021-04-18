package com.company;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

interface Employee{
    abstract void monthly_salary();
}

class part_time_employee implements Employee,Serializable{
    private String emp_name;
    private LocalDate dob;
    private int working_hours;
    private final int hourly_payment=100;
    private int salary_day;
    private int salary_week;
    private int emp_id;
    private static int id=2000;

    part_time_employee(String emp_name,LocalDate dob,int working_hours){
        this.emp_name=emp_name;
        this.dob=dob;
        this.working_hours=working_hours;
        emp_id=id++;
    }

    public String get_empname(){
        return emp_name;
    }

    public LocalDate get_empdob(){
        return dob;
    }

    public int get_empid(){
        return emp_id;
    }

    @Override
    public void monthly_salary() {
        // this method calculates the weekly wages of the part time employee
        salary_day=working_hours*hourly_payment;
        salary_week=salary_day*6;
        System.out.println("Weekly wage: "+salary_week);
    }
}


class regular_employee implements Employee,Serializable{
    private String emp_name;
    private LocalDate dob;
    private int salary_month;
    private int working_days;
    private int emp_id;
    private static int id=1000;

    regular_employee(String emp_name,LocalDate dob){
        this.emp_name=emp_name;
        this.dob=dob;
        emp_id=id++;
    }

    regular_employee(String emp_name,LocalDate dob,int working_days,int salary_month){
        this.emp_name=emp_name;
        this.dob=dob;
        this.working_days=working_days;
        this.salary_month=salary_month;
        emp_id=id++;
    }
    public String get_empname(){
        return emp_name;
    }

    public LocalDate get_empdob(){
        return dob;
    }

    public int get_empid(){
        return emp_id;
    }

    @Override
    public void monthly_salary() {
        // In 1 month 30 days and in that 4 weekends(8 days) and 2 days leave will be accepted and from then each day salary will be deducted
        if(working_days<21)
            salary_month-=(21-working_days)*(salary_month/30);
        System.out.println("Monthly Salary: "+salary_month);
    }
}

public class Main {
    int pe=2;//number of part time employees in the company
    int re=2;//number of regular employees in the company
    Scanner sc=new Scanner(System.in);
    part_time_employee[] info1=new part_time_employee[10];//array for storing parttime employee objects
    regular_employee[] info2=new regular_employee[10];//array for storing regular employee objects

    //predefined employee's details
    public void inputdetails(){
        info1[0]=new part_time_employee("Ravi",LocalDate.of(1995,5,12),12);
        info1[1]=new part_time_employee("Kiran",LocalDate.of(1993,12,29),10);
        info1[2]=new part_time_employee("Swathi",LocalDate.of(1996,3,15),8);
        info2[0]=new regular_employee("Dushyanth",LocalDate.of(1990,2,1),24,50000);
        info2[1]=new regular_employee("Sandeep",LocalDate.of(1993,1,31),17,25000);
        info2[2]=new regular_employee("Manisha",LocalDate.of(1994,9,12),26,32000);
    }

    public void savedata(){
        ArrayList<Object> data=new ArrayList<Object>();
        data.add(info1);
        data.add(info2);
        try{
            FileOutputStream fileout=new FileOutputStream("details.txt");
            ObjectOutputStream out=new ObjectOutputStream(fileout);
            out.writeObject(data);
            out.close();
            fileout.close();
            System.out.println("Serialized data is stored in details.txt");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loaddata(boolean diff){
        int de_id;//employee id which will be entered by the user for details
        ArrayList<Object> de_data = new ArrayList<Object>();
        try{
            FileInputStream filein=new FileInputStream("details.txt");
            ObjectInputStream in=new ObjectInputStream(filein);
            de_data=(ArrayList<Object>) in.readObject();
            in.close();
            filein.close();
            //Serialized data in details.txt is deserialized
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
            return;
        }
        part_time_employee [] de_info1=(part_time_employee[]) de_data.get(0);
        regular_employee [] de_info2=(regular_employee[]) de_data.get(1);
        System.out.print("Enter the employee id to get details: ");
        de_id=sc.nextInt();
        boolean flag=false;
        for(int i=0;i<re||i<pe;i++){
            if(de_info1[i].get_empid()==de_id){
                System.out.println("\nPart time Employee");
                System.out.println("Employee Id: "+de_info1[i].get_empid());
                if(diff==false)
                    System.out.println("Employee Name: "+de_info1[i].get_empname()+"\nEmployee DOB: "+de_info1[i].get_empdob());
                de_info1[i].monthly_salary();
                flag=true;
                break;
            }
            else if(de_info2[i].get_empid()==de_id){
                System.out.println("\nRegular Employee");
                System.out.println("Employee Id: "+de_info2[i].get_empid());
                if(diff==false)
                    System.out.println("Employee Name: "+de_info2[i].get_empname()+"\nEmployee DOB: "+de_info2[i].get_empdob());
                de_info2[i].monthly_salary();
                flag=true;
                break;
            }
        }
        if(flag==false)
            System.out.println("There is no employee with that employee id :(...");
        else
            flag=false;
    }
}

