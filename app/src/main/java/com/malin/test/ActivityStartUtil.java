package com.malin.test;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.widget.Toast;

import java.util.List;

/**
 * 类描述: 启动制定包名的其他应用
 * 创建人:malin.myemail@163.com
 * 创建时间:2017/11/29 17:01
 * 备注:{@link }
 * 修改人:
 * 修改时间:
 * 修改备注:
 * 版本:
 * http://blog.csdn.net/qq_28695619/article/details/53869594
 */

public class ActivityStartUtil {

    /**
     * 启动制定包名的其他应用的LAUNCHER Activity,有问题.请使用startThirdApp2()
     * http://blog.csdn.net/qq_28695619/article/details/53869594
     *
     * @param packageNameStr packageName
     */
    public static boolean startThirdApp(Activity activity, String packageNameStr) {
        boolean isSuccess;
        if (activity == null || activity.isFinishing()) return false;
        try {
            PackageManager packageManager = activity.getApplicationContext().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(packageNameStr, 0);
            Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
            resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            resolveIntent.setPackage(packageInfo.packageName);
            List<ResolveInfo> apps = packageManager.queryIntentActivities(resolveIntent, 0);
            ResolveInfo resolveInfo = apps.iterator().next();
            if (resolveInfo != null) {
                String packageName = resolveInfo.activityInfo.packageName;
                String className = resolveInfo.activityInfo.name;
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                ComponentName cn = new ComponentName(packageName, className);
                intent.setComponent(cn);
                activity.startActivity(intent);
            }
            isSuccess = true;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            isSuccess = false;
        }
        return isSuccess;
    }


    /**
     * 启动制定包名的其他应用
     * http://www.jianshu.com/p/42ae7066f8f3
     *
     * @param packageNameStr packageName
     */
    public static boolean startThirdApp2(Activity activity, String packageNameStr) {
        boolean isSuccess;
        if (activity == null || activity.isFinishing()) return false;
        try {
            PackageManager packageManager = activity.getApplicationContext().getPackageManager();
            if (checkPackInfo(packageManager, packageNameStr)) {
                Intent intent = packageManager.getLaunchIntentForPackage(packageNameStr);
                activity.startActivity(intent);
                Toast.makeText(activity, "启动" + packageNameStr, Toast.LENGTH_SHORT).show();
                isSuccess = true;
            } else {
                isSuccess = false;
                Toast.makeText(activity, "没有安装" + packageNameStr, Toast.LENGTH_SHORT).show();
            }


        } catch (Throwable throwable) {
            throwable.printStackTrace();
            isSuccess = false;
        }
        return isSuccess;
    }

    /**
     * 检查包是否存在
     *
     * @param packageName packageName
     * @return is exit
     */
    private static boolean checkPackInfo(PackageManager packageManager, String packageName) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo != null;
    }

}
