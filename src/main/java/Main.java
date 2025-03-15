import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
//        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
//        System.out.print("Enter a km: ");
//        float km = Float.parseFloat(reader.readLine());
//        System.out.println("Total: "+getSum(km));
//        System.out.printf("So nguyen lon nhat la: %d",getMax(10,3,-5));
//        System.out.println(capitalizeFirstLetter("hello world"));
        //System.out.println(alwaysLowerThan30(new int[]{34,1,23,4,3,12,45,33,6,22,30}));
        HocSinh hs = new HocSinh();
        System.out.println(hs.createListStudent());
    }
    // given array =[34,1,23,4,3,12,45,33,6,22]
    // find the max value in the array
    public static int findMax(int[] arr){
        Optional<Integer> max = Optional.of(Arrays.stream(arr).max().getAsInt());
        if(max.isEmpty()||max.get()==null){
            throw new IllegalArgumentException("Invalid input");
        }
        return max.get();
    }
    public static int totalArray(int[] arr){
        Optional<Integer> total = Optional.of(Arrays.stream(arr).sum());
        if(total.isEmpty()||total.get()==null){
            throw new IllegalArgumentException("Invalid input");
        }
        return total.get();
    }

    public static List<Integer> alwaysLowerThan30(int[]arr){
        List list = new ArrayList<Integer>();
        Arrays.stream(arr).filter(num->num<=30).mapToObj(num1->list.add(num1)).collect(Collectors.toList());
        return list;
    }
//    viet chuong trinh viet hoa cac ky tu dau tien moi tu trong chuoi
    public static String capitalizeFirstLetter(String input){
        if(input==null){
            throw new IllegalArgumentException("Invalid input");
        }
        if(input.isBlank()){
            throw new IllegalArgumentException("Empty string");
        }
        String[] words = input.trim().split("\\s+");
        String a ="";
        for(String word:words){
            a=a.concat(word.substring(0,1).toUpperCase()+word.substring(1).toLowerCase()+" ");
        }
        return a.toString().trim();
    }
//    viet 1 chuong trinh tim so lon nhat tu 3 so nguyen a,b,c
    public static int getMax(int a,int b, int c){
        int assumeMax = a;
        if(b>assumeMax){
            assumeMax=b;
        }
        if(c>assumeMax){
            assumeMax=c;
        }
        return assumeMax;
    }
    public static void inBangCuuChuongTu1Toi10(){
        for(int i=1;i<=10;i++){
            System.out.printf("Bang cuu chuong cua %d\n",i);
            System.out.println("____________________");
            System.out.println();
            for(int j=1;j<=10;j++){
                System.out.printf("%d * %d = %d\n",i,j,i*j);
            }
            System.out.println();
        }
    }
    public static Float getSum(float km){
        if(km<0){
            throw new IllegalArgumentException("Invalid km");
        }
        float total=0;
       int firstKm=13000;
       int between2KmTo10Km=10000;
       int after10Km=8000;
       if(km<=1){
           total+=firstKm;
       }else if(km<=10){
           total+=firstKm+(km-1)*between2KmTo10Km;}
       else{
           total+=firstKm+9*between2KmTo10Km+(km-10)*after10Km;
       }
       return total;
    }
    public static String getIntThenCheckNamNhuan(int input){

        if(input<0){
            throw new IllegalArgumentException("Invalid input");
        }
        if((input % 4 == 0 && input % 100 != 0)|| input % 400 == 0){

            return "Nam Nhuan";
        }else{

            return "Khong Nam Nhuan";
        }


    }
    public static String checkOddEven(int input){
        if(input%2==0){
            return "Even";
        }else{
            return "Odd";
        }
    }
}
