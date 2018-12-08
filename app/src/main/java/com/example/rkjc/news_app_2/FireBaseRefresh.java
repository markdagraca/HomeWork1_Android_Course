package com.example.rkjc.news_app_2;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import static com.example.rkjc.news_app_2.Database.NewsRepository.TAG;

/**
 * Helper class for showing and canceling fire base refresh
 * notifications.
 * <p>
 * This class makes heavy use of the {@link NotificationCompat.Builder} helper
 * class to create notifications in a backward-compatible way.
 */
public class FireBaseRefresh {
    /**
     * The unique identifier for this type of notification.
     */
    private static final String NOTIFICATION_TAG = "FireBaseRefresh";

    /**
     * Shows the notification, or updates a previously shown notification of
     * this type, with the given parameters.
     * <p>
     * TODO: Customize this method's arguments to present relevant content in
     * the notification.
     * <p>
     * TODO: Customize the contents of this method to tweak the behavior and
     * presentation of fire base refresh notifications. Make
     * sure to follow the
     * <a href="https://developer.android.com/design/patterns/notifications.html">
     * Notification design guidelines</a> when doing so.
     *
     * @see #cancel(Context)
     */
    public static void notify(final Context context,
                              final String exampleString, final int number) {
        final Resources res = context.getResources();

        // This image is used as the notification's large icon (thumbnail).
        // TODO: Remove this if your notification has no relevant thumbnail.
        final Bitmap picture = BitmapFactory.decodeResource(res, R.drawable.example_picture);
        Intent ignoreReminderIntent = new Intent(context,FireBaseRefresh.class);
        ignoreReminderIntent.setAction(ACTION_DISMISS_NOTIFICATION);
        PendingIntent pending=PendingIntent.getService(context,0,ignoreReminderIntent,0);


        final String ticker = exampleString;
        final String title = res.getString(
                R.string.fire_base_refresh_notification_title_template, exampleString);
        final String text = res.getString(
                R.string.fire_base_refresh_notification_placeholder_text_template, exampleString);

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context)

                // Set appropriate defaults for the notification light, sound,
                // and vibration.
                .setDefaults(Notification.DEFAULT_ALL)

                // Set required fields, including the small icon, the
                // notification title, and text.
                .setSmallIcon(R.drawable.ic_stat_fire_base_refresh)
                .setContentTitle("FireBase refresh was called")
                .setContentText("Content was refreshed with firebase job service")

                // All fields below this line are optional.

                // Use a default priority (recognized on devices running Android
                // 4.1 or later)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)


                // Set ticker text (preview) information for this notification.
                .setTicker(ticker)

                // Show a number. This is useful when stacking notifications of
                // a single type.
                .setNumber(number)

                // If this notification relates to a past or upcoming event, you
                // should set the relevant time information using the setWhen
                // method below. If this call is omitted, the notification's
                // timestamp will by set to the time at which it was shown.
                // TODO: Call setWhen if this notification relates to a past or
                // upcoming event. The sole argument to this method should be
                // the notification timestamp in milliseconds.
                //.setWhen(...)

                // Set the pending intent to be initiated when the user touches
                // the notification.
                .setContentIntent(
                        PendingIntent.getActivity(
                                context,
                                0,
                               ignoreReminderIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT))



                // Example additional actions for this notification. These will
                // only show on devices running Android 4.1 or later, so you
                // should ensure that the activity in this notification's
                // content intent provides access to the same actions in
                // another way.
                .addAction(new NotificationCompat.Action(R.drawable.ic_action_stat_reply,"Cancel",pending)).setAutoCancel(true);


                builder.setChannelId(String.valueOf(NOTIFICATION_TAG.hashCode()));

        notify(context, builder.build());
    }



    private static void clearAllNotifications(Context context) {
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR)
    private static void notify(final Context context, final Notification notification) {
        final NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            NotificationChannel mChannel = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                mChannel = new NotificationChannel(String.valueOf(NOTIFICATION_TAG.hashCode()), "fb", NotificationManager.IMPORTANCE_DEFAULT);
                mChannel.setDescription("");
                mChannel.enableLights(true);
                mChannel.setLightColor(Color.RED);
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                mChannel.setShowBadge(false);
                notification.getChannelId();
                nm.createNotificationChannel(mChannel);
            }

            nm.notify(NOTIFICATION_TAG, 0, notification);
        } else {
            nm.notify(NOTIFICATION_TAG.hashCode(), notification);
        }
    }

    /**
     * Cancels any notifications of this type previously shown using
     * {@link #notify(Context, String, int)}.
     */
    @TargetApi(Build.VERSION_CODES.ECLAIR)
    public static void cancel(final Context context) {
        final NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            nm.cancel(NOTIFICATION_TAG, 0);
        } else {
            nm.cancel(NOTIFICATION_TAG.hashCode());
        }
    }

    public static final String ACTION_INCREMENT_WATER_COUNT = "increment-water-count";
    public static final String ACTION_DISMISS_NOTIFICATION = "dismiss-notification";
    static final String ACTION_CHARGING_REMINDER = "charging-reminder";

    public static void executeTask(Context context, String action) {
        {
            Log.d(TAG, "executeTask: it was called");
            clearAllNotifications(context);
        }
    }


}
