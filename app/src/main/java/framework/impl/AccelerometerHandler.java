package framework.impl;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

/** 3軸加速度センサーの管理用classです。
※ @auther Solaliyah
 * Created by SolarisD on 2016/03/19.
 */
public class AccelerometerHandler implements SensorEventListener {
    float accelX;
    float accelY;
    float accelZ;

    public AccelerometerHandler(Context context){
        Log.d("Creat","AccelerometerHandler");
        SensorManager manager = (SensorManager) context
                                .getSystemService(Context.SENSOR_SERVICE);
        if(manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0){
            Sensor accelerometer = manager
                                    .getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            manager.registerListener(this, accelerometer,
                                        SensorManager.SENSOR_DELAY_GAME);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){
        //　何もしない
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        accelX = event.values[0];
        accelY = event.values[1];
        accelZ = event.values[2];
    }

    public float getAccelX(){
        return accelX;
    }

    public float getAccelY(){
        return accelY;
    }

    public float getAccelZ(){
        return accelZ;
    }
}
