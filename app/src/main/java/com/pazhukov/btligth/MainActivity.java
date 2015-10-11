// Работа с Bluetooth (HC-06)
// Автор Pazhukov Max
// Дата создание 10/10/2015
//
package com.pazhukov.btligth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends ActionBarActivity {

    BluetoothSocket clientSocket;
    // RU: mac адрес HC-06
    // EN: mac address HC-06
    String DEVICE_UID = "98:D3:33:80:5B:0A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String enableBT = BluetoothAdapter.ACTION_REQUEST_ENABLE;
        startActivityForResult(new Intent(enableBT), 0);

        BluetoothAdapter bluetooth = BluetoothAdapter.getDefaultAdapter();

        try{
            BluetoothDevice device = bluetooth.getRemoteDevice(DEVICE_UID);
            Method m = device.getClass().getMethod("createRfcommSocket", new Class[] {int.class});

            clientSocket = (BluetoothSocket)m.invoke(device,1);
            clientSocket.connect();

            Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_LONG).show();

        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnOn(View v){

        // RU: отправляем "1" как байты на блютуз девайс
        // EN: send "1" as bytes on BT device

        try{
            OutputStream outputStream = clientSocket.getOutputStream();

            String value = "1";

            outputStream.write(value.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void btnOff(View v){

        // RU: отправляем "0" как байты на блютуз девайс
        // EN: send "0" as bytes on BT device

        try{
            OutputStream outputStream = clientSocket.getOutputStream();

            String value = "0";

            outputStream.write(value.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
