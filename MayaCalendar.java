import java.util.Scanner;

// 类名提交时一定要改成main！！！！！！
// 1008
public class MayaCalendar {
    public static int indexOf(String[] s, String key) {
      for (int i = 0; i < s.length; i ++) {
        if (s[i].equals(key)) {
          return i;
        }
      }
      return -1;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String[] haab = new String[n];
        for (int i = 0; i < n; i ++) {
          haab[i] = sc.nextLine();
        }

        String[] haabMonth = new String[]{"pop", "no", "zip", "zotz", "tzec", "xul", "yoxkin",
          "mol", "chen", "yax", "zac", "ceh", "mac", "kankin", "muan", "pax", "koyab", "cumhu", "uayet"};
        String[] tzolkinMonth = new String[]{"imix", "ik", "akbal", "kan", "chicchan", "cimi",
          "manik", "lamat", "muluk", "ok", "chuen", "eb", "ben", "ix", "mem", "cib", "caban", "eznab", "canac", "ahau"};
        
        String[] res = new String[n];
        for (int j = 0; j < n; j ++) {
          String[] data = haab[j].split(" ");
          int day = Integer.parseInt(data[0].substring(0, data[0].length() - 1)); // 4
          String month = data[1];
          int year = Integer.parseInt(data[2]); // 0

          int monthIndex = indexOf(haabMonth, month); // 18

          int days = year * 365 + monthIndex * 20 + day + 1;

          int resYear = days / 260;
          int lastDays = days % 260;
          int resMonth, resDay;
          // 如果天数可被260整除，则在tzolkin历中应该是一年的最后一天
          if (lastDays == 0) {
            resYear --;
            resMonth = 19;
            resDay = 13;
          } else {
            resMonth = (lastDays - 1) % 20;
            // 考虑到对13取余为0的情况应该显示13
            resDay = (lastDays - 1) % 13 + 1;
          }
          res[j] = Integer.toString(resDay) + ' ' + tzolkinMonth[resMonth] + ' ' + Integer.toString(resYear);
        }

        System.out.println(n);
        for (int k = 0; k < n; k ++) {
          System.out.println(res[k]);
        }
    }
}
