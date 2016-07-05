package framework.base;

/**
 * ファイルから新しいMusic,Sound型インスタンスを返すinterfaceです。
 * @Solaliyah on 2016/05/16.
 * @version 1.00
 */
public interface Audio {
    /**
     * filenameにファイル名を渡すことで、ファイルの入った新しいMusic型
     * インスタンスを返します。
     * @param filename  取得したいファイル名
     * @return  新しいMusic型インスタンス
     */
    public Music newMusic(String filename);
    /**
     * filenameにファイル名を渡すことで、ファイルの入った新しいSound型
     * インスタンスを返します。
     * @param filename
     * @return
     */
    public Sound newSound(String filename);
}
