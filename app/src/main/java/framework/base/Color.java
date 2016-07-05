package framework.base;

/**
 * red,green,blue,alphaの４つの値を16進数の８桁の数字に変換するclassです。
 * ＡＲＧＢの４つの情報を１つのint型として渡すときに使われます。
 * @Solaliyah on 2016/05/16.
 * @Version 1.00
 */
public class Color {
    /**
     * red,green,blue,alphaの４つの値を16進数の８桁の数字に変換をする。
     * ４つの引数を下の8bit(0~255)だけ取り出し、alpha,red,green,blueの順に
     * 8bitずつintに入れて返す。
     * 255以上の値の場合下8bitだけを見る
     * @param r 赤ＲＥＤ
     * @param g 緑ＧＲＥＥＮ
     * @param b 青ＢＬＵＥ
     * @param a 透明度alpha    255で完全に透明
     * @return  a,r,g,bを一つのint型にして返す
     *      TODO マイナスの値は入れるべきでない？
     */
    public static int convert(int r, int g, int b, int a){
        return((a & 0xff) << 24) |
                ((r & 0xff) << 16) |
                ((g & 0xff) << 8) |
                ((b & 0xff)) ;
    }
}
