package com.ognev.eslreader.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

public class PermissionManager {

  private Context context;

  public PermissionManager(Context context) {
    this.context = context;
  }

  public boolean canAccessLocation() {
    return (hasPermission(Manifest.permission.ACCESS_FINE_LOCATION));
  }

  public boolean canOpenCamera() {
    return (hasPermission(Manifest.permission.CAMERA));
  }

  public boolean canReadData() {
    return (hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE));
  }
  public boolean canWriteData() {
    return (hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE));
  }

  public boolean canAccessCamera() {
    return (hasPermission(Manifest.permission.CAMERA));
  }

  public boolean canAccessContacts() {
    return (hasPermission(Manifest.permission.READ_CONTACTS));
  }

  private boolean hasPermission(String perm) {
    return (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(context, perm));
  }
}