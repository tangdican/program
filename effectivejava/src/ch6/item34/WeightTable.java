package ch6.item34;

// Takes earth-weight and prints table of weights on all planets (Page 160)
public class WeightTable {
   public static void main(String[] args) {
      double earthWeight = Double.parseDouble("2.2");
      double mass = earthWeight / Planet.EARTH.surfaceGravity();
      for (Planet p : Planet.values())
          // 重新计算修改枚举默认值
         System.out.printf("Weight on %s is %f%n",
                 p, p.surfaceWeight(mass));
   }
}
