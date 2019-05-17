package com.qezhhnjy.sara.fund;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Random;

/**
 * @author fuzy
 * create time 19-5-16-下午3:14
 * 假设基金初始净值为100,投入100股,每天随机涨跌-5 -> 5 个点,根据涨跌的点数购买或卖出 点数 * 100 的金额.
 * 到净值回到1时停止,计算最终的金额.
 */
public class FixedThrow {
    public static void main(String[] args) {
        Money  money  = new Money(100, 100);
        Random random = new Random();
        float  out    = 0;
        int    count  = 0;
        do {
            count++;
            int   a      = random.nextInt(1001) - 500;
            float change = a / 100.0F;
            money.setValue(money.getValue() * (1 + change / 100.0F));
            float temp = money.total() * change / 100.0F;
            money.flush(money.total() - temp);
            out += temp;
            System.out.println(money);
            System.out.println(money.total());
            System.out.println(change);
            System.out.println(out);
//            TimeUnit.MILLISECONDS.sleep(1);
        } while (money.getCount() > 1 && (money.getValue() > 99.95 || money.getValue() < 100.05));
        System.out.println("count:" + count);
    }
}

@Data
@AllArgsConstructor
class Money {
    private float count;
    private float value;

    public float total() {
        return count * value;
    }

    public void flush(float total) {
        count = total / value;
    }
}
