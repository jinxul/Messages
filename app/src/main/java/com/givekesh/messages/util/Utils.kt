package com.givekesh.messages.util

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.core.content.ContextCompat

class Utils(private val context: Context) {

    fun getSettingsIntent(): Intent {
        return Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        ).apply {
            data = Uri.fromParts("package", context.packageName, null)
        }
    }

    fun isPermissionsGranted(): Boolean =
        isGranted(Manifest.permission.READ_SMS) &&
                isGranted(Manifest.permission.READ_CONTACTS)


    private fun isGranted(permission: String): Boolean =
        ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
}
