import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

// 类名提交时一定要改成main！！！！！！
// 1006
// 余数定理，[]代表最小公倍数
// n1 = [28, 33]k1, n1 % 23 = 1
// n2 = [23, 33]k2, n2 % 28 = 1
// n3 = [23, 28]k3, n3 % 33 = 1
// res = (n1 * p + n2 * e + n3 * i) % [n1, n2, n3] - d
class Biorhythms {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        List<Integer> p = new ArrayList<Integer>();
        List<Integer> e = new ArrayList<Integer>();
        List<Integer> i = new ArrayList<Integer>();
        List<Integer> d = new ArrayList<Integer>();
        while (true) {
          p.add(sc.nextInt());
          e.add(sc.nextInt());
          i.add(sc.nextInt());
          d.add(sc.nextInt());
          if (p.get(p.size() - 1) == e.get(e.size() - 1)
            && e.get(e.size() - 1) == i.get(i.size() - 1)
            && i.get(i.size() - 1) == d.get(d.size() - 1)
            && d.get(d.size() - 1) == -1) {
            break;
          }
        }

        for (int j = 0; j < p.size() - 1; j ++) {
          int res = (5544 * p.get(j) + 14421 * e.get(j) + 1288 * i.get(j)) % 21252 - d.get(j);
          if (res <= 0) res = 21252 + res;
          System.out.println("Case " + (j + 1) + ": the next triple peak occurs in " + res + " days.");
        }
    }
}