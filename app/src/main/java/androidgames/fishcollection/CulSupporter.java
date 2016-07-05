package androidgames.fishcollection;

/**
 * Created by SolarisD on 2016/05/21.
 */
public class CulSupporter {
    public final int supporterPPS[] = {1, 7, 17, 37, 40, 50, 50, 20, 30, 20};
    // PPS production per second    10^9を割り切れる数が望ましい
    public long supporterLastTime[] = new long[10];
    //前回時間
    public int supporterBaseCost[] = {10, 50, 100, 200};
    //前回時間

    public CulSupporter(){
        for(int i = 0; i < 10; i ++)
            supporterLastTime[i] = System.nanoTime();
    }

    public int update(States states){
        int resultCul = 0;
        int resultCashe = 0;

        for(int i = 0; i < states.getSupporter().length; i++) {
            resultCul = (int) (System.nanoTime() - supporterLastTime[i]) / (1000000000 / supporterPPS[i]);

            if (resultCul > 0) {
                supporterLastTime[i] = System.nanoTime();
                if(States.shopItem[0] == true)
                    resultCul += resultCul * 1.1f;
                resultCashe += states.getSupporter()[i] * resultCul;
            }
        }
        return resultCashe;
    }

    public long culCost(int Num, int[] suppoter){
        return (long)(supporterBaseCost[Num] * Math.pow( 1.1, suppoter[Num]));
    }

}
