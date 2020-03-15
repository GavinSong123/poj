import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.lang.Math;

// 类名提交时一定要改成main！！！！！！
// 1009
// 不能直接创建二位数组，会导致mle，因此未发生变化的点不编码，只对发生变化的点编码
// 发生变化的点包括原始数据发生变化的位置及其周围8个点
// 把这些点的编码和位置保存并根据位置升序排列，可计算出最终结果
public class EdgeDetection {
    public static int getMaxAbs(int width, int total, int pos, ArrayList<InputPair> data) {
        int max = 0;
        int center = getPixel(pos, data);
        int row = pos / width;
        int column =  pos % width;
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = column - 1; c <= column + 1; c ++) {
                int curPos = r * width + c;
                if (r < 0 || c < 0 || curPos >= total || c >= width || pos == curPos) {
                    continue;
                }
                int arround = getPixel(curPos, data);
                if (Math.abs(center - arround) > max) {
                    max = Math.abs(center - arround);
                }
            }
        }
        return max;
    }
    public static int getPixel(int pos, ArrayList<InputPair> data) {
        int counts = 0;
        for (int i = 0; i < data.size(); i ++) {
            counts += data.get(i).count;
            if (counts >= pos + 1) {
                return data.get(i).pixel;
            }
        }
        return 0;
    }
    public static List<Pair> edgeDetect(int width, int total, ArrayList<InputPair> data) {
        List<Pair> res = new ArrayList<Pair>();
        int pos = 0;
        for (int i = 0; i < data.size(); i ++) {
            int row = pos / width;
            int column =  pos % width;
            for (int r = row - 1; r <= row + 1; r++) {
                for (int c = column - 1; c <= column + 1; c ++) {
                    int curPos = r * width + c;
                    if (r < 0 || c < 0 || curPos >= total || c > width) {
                        continue;
                    }
                    res.add(new Pair(curPos, getMaxAbs(width, total, curPos, data)));
                }
            }
            pos += data.get(i).count;
        }
        res.add(new Pair(total - width, getMaxAbs(width, total, total - width, data)));
        return res;
    }
    public static int getTotal(ArrayList<InputPair> data) {
        int total = 0;
        for (int i = 0; i < data.size(); i ++) {
            total += data.get(i).count;
        }
        return total;
    }
    public static void outPut(int width, int total, List<Pair> pair) {
        System.out.println(width);
        Collections.sort(pair, new EdgeComparator());
        Pair temp = pair.get(0);
        for (int i = 0; i < pair.size(); i ++) {
            if (pair.get(i).pixel == temp.pixel) {
                continue;
            }
            System.out.println(temp.pixel + " " + (pair.get(i).pos - temp.pos));
            temp = pair.get(i);
        }
        System.out.println(temp.pixel + " " + (total - temp.pos));
        System.out.println("0 0");
    }
    public static ArrayList<InputPair> initData(String[] data) {
        ArrayList<InputPair> pairs = new ArrayList<InputPair>();
        for (int i = 0; i < data.length; i ++) {
            String[] pixels = data[i].split(" ");
            int pixel = Integer.parseInt(pixels[0]);
            int count = Integer.parseInt(pixels[1]);
            pairs.add(new InputPair(pixel, count));
        }
        return pairs;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String inputData = "";
        while (sc.hasNext()) {
            String number = sc.nextLine();
            if (number.equals("0")) {
                break;
            } else {
                inputData += number + ",";
            }
        }

        String[] initData = inputData.split(",");

        int i = 0, width = 0, start = 0, end = 0;
        while(i < initData.length) {
            if (initData[i].indexOf(" ") == -1) {
                width = Integer.parseInt(initData[i]);
                start = i + 1;
            }
            if (initData[i].equals("0 0")) {
                end = i;
                ArrayList<InputPair> data = initData(Arrays.copyOfRange(initData, start, end));
                int total = getTotal(data);
                List<Pair> res = edgeDetect(width, total, data);
                outPut(width, total, res);
            }
            i ++;
        }
        System.out.println(0);
    }
}

class Pair {
    public int pos;
    public int pixel;
    Pair(int pos, int pixel) {
        this.pos = pos;
        this.pixel = pixel;
    }
}

class InputPair {
    public int pixel;
    public int count;
    InputPair(int pixel, int count) {
        this.pixel = pixel;
        this.count = count;
    }
}

class EdgeComparator implements Comparator<Pair> {
    @Override
    public int compare(Pair d1, Pair d2) {
        if (d1.pos < d2.pos) {
            return -1;
        } else if (d1.pos > d2.pos) {
            return 1;
        } else {
            return 0;
        }
    }
}
