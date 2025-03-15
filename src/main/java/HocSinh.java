import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class HocSinh {
    private String studentId;
    private String studentName;
    private int studentAge;
    private double studentMark;
    public HocSinh(){

    }
// random number
    private int randomId()  {
        return (int) (Math.random() * 1000);
    }
    public HocSinh(String studentId,String studentName, int studentAge, double studentMark) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentMark = studentMark;
    }
    public List<HocSinh> createListStudent(){
        //Tao 1 danh sach hoc sinh voi 10 ban
//random score from 1.0 to 10.0 in double
//random age from 18 to 25 in int
//random name from array {"Huong", "Anh", "Mai", "Doti", "Hoa", "Nhu","Hong","Khanh", "Tien", "Phuong"}
        List<HocSinh> listStudent = new ArrayList<>();
        String[] names = {"Huong", "Anh", "Mai", "Doti", "Hoa", "Nhu","Hong","Khanh", "Tien", "Phuong"};
        for(int i=0;i<10;i++){
            HocSinh hocSinh = new HocSinh("TVN-AK48-"+ UUID.randomUUID().toString().substring(3,7),names[(int)(Math.random()*10)],(int)(Math.random()*7+18),Math.round((Math.random()*9+1)*10)/10.0);
            listStudent.add(hocSinh);
        }
        listStudent.forEach(HocSinh::printStudent);
        return listStudent;
    }
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }

    public double getStudentMark() {
        return studentMark;
    }

    public void setStudentMark(double studentMark) {
        this.studentMark = studentMark;
    }

    public void printStudent(){
        System.out.println("Student ID: "+studentId);
        System.out.println("Student Name: "+studentName);
        System.out.println("Student Age: "+studentAge);
        System.out.println("Student Mark: "+studentMark);
    }
    public void getStudentsGte20(List<HocSinh> list){
        if(list.isEmpty()||list==null) {
            throw new IllegalArgumentException("List is empty");
        }
        List<HocSinh> studentsGte20 = new ArrayList<>();
        enum Sort{
            GTE20,
            LT20
        }
        list.stream().filter(hs->hs.studentAge>=20).collect(Collectors.toList()).forEach(HocSinh::printStudent);
//        for(HocSinh hs:list){
//            String filter = this.studentAge>=20?Sort.GTE20.toString():Sort.LT20.toString();
//            if(filter.equals(Sort.GTE20.toString())){
//                studentsGte20.add(this);
//            }
//        }
//        studentsGte20.forEach(HocSinh::printStudent);
    }
    public void getStudentMaxMark(List<HocSinh> list){
        if(list.isEmpty()||list==null) {
            throw new IllegalArgumentException("List is empty");
        }
         list.stream().max(Comparator.comparing(HocSinh::getStudentMark)).get().printStudent();

    }
    public List<HocSinh> getExcellentStudents(List<HocSinh> list){
        List<HocSinh> hsXuatSac = new ArrayList<>();
        enum XepLoai{
            YEU,
            TRUNGBINH,
            KHA,
            GIOI,
            XUATSAC
        }
        for(HocSinh hs:list){
            String rate = this.studentMark<5?XepLoai.YEU.toString():this.studentMark<6.5?XepLoai.TRUNGBINH.toString():this.studentMark<8?XepLoai.KHA.toString():this.studentMark<9?XepLoai.GIOI.toString():XepLoai.XUATSAC.toString();
            if(rate.equals(XepLoai.XUATSAC.toString())){
                hsXuatSac.add(this);
            }
        }
        return hsXuatSac;
    }
    public  String xepLoaiHocLuc(){

        if(this.studentMark<0||this.studentMark>10){
            throw new IllegalArgumentException("Invalid mark");
        }

        if(this.studentMark<5){
            return "Yeu";
        }else if(this.studentMark<6.5){
            return "Trung Binh";
        }else if(this.studentMark<8){
            return "Kha";
        }else if(this.studentMark<9){
            return "Gioi";
        }else{
            return "Xuat Sac";
        }
    }
}
