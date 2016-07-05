package androidgames.fishcollection;

/**
 * Created by 鈴木 貴大 on 2016/05/30.
 */
public class States {
    static long cashe;
    static int[] supporter;
    static boolean[] shopItem;
    static long frameNanoTime;
    static boolean[] Activement;
    static int[] fishGuide;

    States(){
        cashe = 0;
        supporter = new int[10];
        shopItem = new boolean[4];
        frameNanoTime = System.nanoTime();
        Activement = new boolean[64];
        fishGuide = new int[16];
    }

    public long getCashe(){
        return cashe;
    }

    public int[] getSupporter(){
        return supporter;
    }

    public void addCashe(long add){
        cashe += add;
    }

    public void setSupporter(int num, int add){
        supporter[num] += add;
    }

    public void setFrameNanoTime(){
        frameNanoTime = System.nanoTime();
    }

    public void addActivement(int ActiveNum){
        Activement[ActiveNum] = true;
    }

    public void addFishGuide(int FishNum, int addNum){
        if(fishGuide[FishNum] + addNum > 2147483647)
            addNum = 2147483647 - fishGuide[FishNum];
        fishGuide[FishNum] += addNum;
    }

    public int getFishGuide(int FishNum){
        return fishGuide[FishNum];
    }
}
