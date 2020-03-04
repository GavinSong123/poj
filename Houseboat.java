import java.util.Scanner;
import java.lang.Math;

// 类名提交时一定要改成main！！！！！！
// 1005
class Houseboat {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int number = sc.nextInt();
        double[][] coors = new double[number][2];
        for (int i = 0; i < number; i ++) {
            coors[i][0] = sc.nextDouble();
            coors[i][1] = sc.nextDouble();
        }

        double area = 50.0;
        for (int j = 0; j < number; j ++) {
            double x = coors[j][0];
            double y = coors[j][1];

            int year = 1;
            while (true) {
                double squareR = area * year * 2 / Math.PI;
                if (Math.pow(x, 2) + Math.pow(y, 2) <= squareR) {
                    break;
                }
                year ++;
            }
            
            System.out.println("Property " + (j + 1) + ": This property will begin eroding in year " + year + ".");
        }
        System.out.println("END OF OUTPUT.");
    }
}