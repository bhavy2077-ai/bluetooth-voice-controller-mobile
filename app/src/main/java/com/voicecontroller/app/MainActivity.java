package com.voicecontroller.app;
import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private static final String BT_NAME = "BluetoothVoiceController";
    private static final UUID BT_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static final int PERMISSION_REQUEST = 100;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothServerSocket serverSocket;
    private TextView statusText;
    private Button listenBtn;
    private CommandExecutor commandExecutor;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        statusText = findViewById(R.id.status_text);
        listenBtn = findViewById(R.id.listen_btn);
        commandExecutor = new CommandExecutor(this);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        requestPermissions();
        listenBtn.setOnClickListener(v -> startListening());
    }
    
    private void requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_CONNECT, Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.CALL_PHONE}, PERMISSION_REQUEST);
            }
        }
    }
    
    private void startListening() {
        new Thread(() -> {
            try {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions();
                    return;
                }
                serverSocket = bluetoothAdapter.listenUsingRfcommWithServiceRecord(BT_NAME, BT_UUID);
                updateUI("🔄 Waiting for connection...");
                BluetoothSocket socket = serverSocket.accept();
                updateUI("✓ Connected to Laptop");
                handleConnection(socket);
            } catch (Exception e) {
                updateUI("❌ Error: " + e.getMessage());
            }
        }).start();
    }
    
    private void handleConnection(BluetoothSocket socket) {
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            byte[] buffer = new byte[1024];
            int bytes;
            while (true) {
                bytes = inputStream.read(buffer);
                String data = new String(buffer, 0, bytes, StandardCharsets.UTF_8);
                JSONObject json = new JSONObject(data);
                String encryptedCmd = json.getString("cmd");
                String command = SecurityUtils.decrypt(encryptedCmd);
                executeCommand(command);
                String response = SecurityUtils.encrypt("Command executed");
                outputStream.write(new JSONObject().put("response", response).toString().getBytes());
            }
        } catch (Exception e) {
            updateUI("❌ Connection error: " + e.getMessage());
        }
    }
    
    private void executeCommand(String command) {
        command = command.toLowerCase().trim();
        if (command.contains("youtube")) {
            commandExecutor.openYouTube();
            updateUI("✓ Opening YouTube");
        } else if (command.contains("call")) {
            String contact = extractContact(command);
            commandExecutor.makeCall(contact);
            updateUI("✓ Calling " + contact);
        } else if (command.contains("home") || command.contains("home screen")) {
            commandExecutor.openHomeScreen();
            updateUI("✓ Opening Home Screen");
        } else if (command.contains("message") || command.contains("sms")) {
            commandExecutor.openMessaging();
            updateUI("✓ Opening Messages");
        } else if (command.contains("camera")) {
            commandExecutor.openCamera();
            updateUI("✓ Opening Camera");
        } else if (command.contains("settings")) {
            commandExecutor.openSettings();
            updateUI("✓ Opening Settings");
        } else {
            updateUI("❓ Unknown command: " + command);
        }
    }
    
    private String extractContact(String command) {
        String[] words = command.split(" ");
        return words.length > 1 ? words[1] : "Unknown";
    }
    
    private void updateUI(String message) {
        runOnUiThread(() -> statusText.setText(message));
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (serverSocket != null) serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
