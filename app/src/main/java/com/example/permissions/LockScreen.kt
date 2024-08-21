package com.example.permissions

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent

public class LockScreen {

    constructor(context : Context) {
        this.context = context
        this.devicePolicyManager = context.getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        this.compName = ComponentName(context, TrackDeviceAdminReceiver::class.java)
    }
    private val context : Context
    private val devicePolicyManager : DevicePolicyManager
    private val compName : ComponentName
    public suspend fun lockScreen(startActivityForResult : (Intent?) -> Unit = {}) {
        if (devicePolicyManager.isAdminActive(compName)) {
            devicePolicyManager.lockNow()
        } else if (devicePolicyManager.isAdminActive(compName).not()) {
            enableLock(startActivityForResult)
        }
    }

    private suspend fun enableLock(startActivityForResult : (Intent?) -> Unit = {}) {
        val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, compName)
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Additional text explaining why we need this permission")
        startActivityForResult(intent)
    }

    public suspend fun disableLock() {
        devicePolicyManager.removeActiveAdmin(compName);
    }
}