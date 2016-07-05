package framework.base;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * ファイル名からAssetの読みとファイルの読み書きをするinterfaceです。
 * @author Solaliyah
 */
public interface FileIO {
    /**
     * Assetsフォルダからファイルを読み込みます。
     * @param fileName  読み込みたいファイル名
     * @return  読み込んだassetファイルを返す
     * @throws IOException
     */
    public InputStream readAsset(String fileName) throws IOException;
    /**
     * ファイルフォルダからファイルを読み込みます。
     * @param fileName  読み込みたいファイル名
     * @return  読み込んだファイルを返す
     * @throws IOException
     */
    public InputStream readFile(String fileName) throws IOException;
    /**
     * ファイルフォルダにファイルを書き込む。
     * @param fileName  書き込みたいファイル名
     * @return 書き込むファイルを返す
     * @throws IOException
     */
    public OutputStream writeFile(String fileName) throws IOException;
}
