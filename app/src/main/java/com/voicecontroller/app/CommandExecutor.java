package com.voicecontroller.app;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class CommandExecutor {
    private Context context;
    
    public CommandExecutor(Context context) {
        this.context = context;
    }
    
    public void openYouTube() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.youtube.com"));
        intent.setPackage("com.google.android.youtube");
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com"));
            context.startActivity(webIntent);
        }
    }
    
    public void makeCall(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(android.Manifest.permission.CALL_PHONE) == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                context.startActivity(intent);
            }
        }
    }
    
    public void openHomeScreen() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    
    public void openMessaging() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setType("vnd.android-dir/mms-sms");
        context.startActivity(intent);
    }
    
    public void openCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        context.startActivity(intent);
    }
    
    public void openSettings() {
        Intent intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
        context.startActivity(intent);
    }
}
