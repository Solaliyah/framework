package framework.base;

/**
※ 短い音の操作用interfaceです。
※ @auther Solaliyah
 */
public interface Sound {
/※※
※ 短い音を再生する
※@param volume 0.0-1.0の範囲で左右両方の音量を設定する
※/
    public void play(float volume);
/※※
※ 短い音を再生する
※@param leftVolume 0.0-1.0の範囲で左両方の音量を設定する
※@param rightVolume 0.0-1.0の範囲で右両方の音量を設定する
※/
    public void play(float leftVolume, float rightVolume);
/※※
※ Soundを削除する
※/
    public void dispose();
}
