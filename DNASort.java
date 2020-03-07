import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

// 类名提交时一定要改成main！！！！！！
// 1007
public class DNASort {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        String[] dna = new String[m];
        for (int i = 0; i < m; i ++) {
          String seq = sc.nextLine();
          if (seq.length() == n) {
            dna[i] = seq;
          }
        }

        Data[] res = new Data[m];
        for (int j = 0; j < m; j ++) {
          int measure = 0;
          // 根据题意，一种较为取巧的办法，因为只有ACGT四种字符
          // 所以倒序循环，比常规两次循环快了100ms左右
          // 跟C/C++比，java还是要慢很多。。。。
          int A = 0, C = 0, G = 0;
          for (int k = n - 1; k >= 0; k --) {
            switch (dna[j].charAt(k)) {
              case 'A':
                A ++;
                break;
              case 'C':
                C ++;
                measure += A;
                break;
              case 'G':
                G ++;
                measure += A + C;
                break;
              case 'T':
                measure += A + C + G;
                break;
              default:
                break;
            }
          }
          // 常规两次循环
          // for (int k = 0; k < n - 1; k ++) {
          //   for (int a = k + 1; a < n; a ++) {
          //     if (dna[j].charAt(k) > dna[j].charAt(a)) {
          //       measure ++;
          //     }
          //   }
          // }
          res[j] = new Data(measure, dna[j]);
        }
        Arrays.sort(res, new MyComparator());
        for (int b = 0; b < res.length; b ++) {
          if (res[b] != null) {
            System.out.println(res[b].getName());
          }
        }
    }
}

class MyComparator implements Comparator<Data> {
  @Override
  public int compare(Data d1, Data d2) {
    if (d1.getMeasure() < d2.getMeasure()) {
      return -1;
    } else if (d1.getMeasure() > d2.getMeasure()) {
      return 1;
    } else {
      return 0;
    }
  }
}

class Data {
  private int measure;
  private String name;

  Data(int measure, String name) {
    this.measure = measure;
    this.name = name;
  }

  /**
   * @return the measure
   */
  public int getMeasure() {
    return measure;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }
}