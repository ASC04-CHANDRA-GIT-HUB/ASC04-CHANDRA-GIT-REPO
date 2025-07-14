package com.demo.LABS.lab1;

class Student{
    private int sid,m1,m2,m3;
    private String sname,city;
    private float feePerMonth;
    private boolean isEligibleForScholarship;

    public void set_sid(int id){
        this.sid=id;
    }
    public int get_sid(){
        return sid;
    }

    public void setStudentName(String name){
        this.sname=name;
    }
    public String getStudentName(){
        return sname;
    }

    public void set_city(String City){
        this.city=City;
    }
    public String get_city(){
        return city;
    }

    public void setMarks(int M1,int M2, int M3){
        this.m1=M1;
        this.m2=M2;
        this.m3=M3;
    }
    public int getTotalMarks(){
        return m1+m2+m3;
    }
    public double getAverage(){
        return getTotalMarks()/3.0;
    }
    public String getResult(){
        return(m1>60 && m2>60 && m3>60)?"pass":"fail";
    }

    public void setFeePerMonth(float fee) { this.feePerMonth = fee; }
    public float getFeePerMonth() { return feePerMonth; }

    public float getAnnualFee() {
        return feePerMonth * 12;
    }

    public void setScholarshipStatus(boolean status) {
        isEligibleForScholarship = status;
    }

    public boolean getScholarshipStatus() {
        return isEligibleForScholarship;
    }
}

class TestMain {
    public static void main(String[] args) {
        Student s1 = new Student();
        Student s2 = new Student();
        Student s3 = new Student();

        s1.setStudentName("Alice");
        s1.setMarks(90, 85, 95);
        s1.setFeePerMonth(3000);
        s1.setScholarshipStatus(true);

        s2.setStudentName("Bob");
        s2.setMarks(70, 75, 80);
        s2.setFeePerMonth(2000);
        s2.setScholarshipStatus(false);

        s3.setStudentName("Charlie");
        s3.setMarks(88, 92, 90);
        s3.setFeePerMonth(2500);
        s3.setScholarshipStatus(true);

        Student[] students = {s1, s2, s3};

        Student top = s1;
        for (Student s : students)
            if (s.getTotalMarks() > top.getTotalMarks())
                top = s;
        System.out.println("Topper: " + top.getStudentName());


        Student leastFee = s1;
        for (Student s : students)
            if (s.getFeePerMonth() < leastFee.getFeePerMonth())
                leastFee = s;
        System.out.println("Least fee: " + leastFee.getStudentName() + " - ₹" + leastFee.getFeePerMonth());


        for (Student s : students) {
            System.out.println("\nName: " + s.getStudentName());
            System.out.println("Total Marks: " + s.getTotalMarks());
            System.out.println("Average: " + s.getAverage());
            System.out.println("Result: " + s.getResult());
            System.out.println("Scholarship: " + s.getScholarshipStatus());
            System.out.println("Annual fee: " + s.getAnnualFee());
        }
    }
}

