package com.good.pj_0425;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private Button jihwa_button;
    private Button suhwa_button;
    private Button list_btn;
    private CustomDialog customDialog;

    ///
    BluetoothManager btManager;
    BluetoothAdapter btAdapter=null;
    BluetoothLeScanner btScanner;
    BluetoothGatt btGatt;
    BluetoothGattService btGattservice;

    BluetoothGattCharacteristic SWITCHCharacteristic_write;
    BluetoothGattCharacteristic SWITCHCharacteristic_read;

    Button btnSwitchOn;
    Button btnSwitchOff;
    Button btnTimer;
    Button btnRead;

    TextView tvStatus;
    TextView tvValue;
    ImageSwitcher imageSwitcher;
    SeekBar seekBar;
    String strAddress;
    String strDevicename;

    // BLE.setLocalName("Demo Gyroscope") of Arduino Nano 33 IoT source Code

    private final static String TEST_BLE_DEVICE_NAME = "Demo Gyroscope";
    private final static int REQUEST_ENABLE_BT = 1;
    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;

    MenuItem scanitem;
    MenuItem stopscanitem;
    MenuItem connecteitem;
    MenuItem deconnectitem;
    ///

    Switch bluetooth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jihwa_button = (Button)findViewById(R.id.jihwa_button);
        suhwa_button = (Button)findViewById(R.id.suhwa_button);
        list_btn = (Button)findViewById(R.id.list_btn);

        jihwa_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, jihwa_chat.class);
                startActivity(intent);
            }
        });

        suhwa_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, suhwa_chat.class);
                startActivity(intent);
            }
        });

        list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

    }
}