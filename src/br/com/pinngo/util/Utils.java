package br.com.pinngo.util;

import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Utils {

	private ProgressDialog dialog;
	
	public static void setPref(final Context c, final String pref, final String val) {
		Editor e = PreferenceManager.getDefaultSharedPreferences(c).edit();
		e.putString(pref, val);
		e.commit();
	}

	public static String getPref(final Context c, final String pref, final String val) {
		return PreferenceManager.getDefaultSharedPreferences(c).getString(pref, val);
	}

	public static void setPref(final Context c, final String pref, final boolean val) {
		Editor e = PreferenceManager.getDefaultSharedPreferences(c).edit();
		e.putBoolean(pref, val);
		e.commit();
	}

	public static boolean getPref(final Context c, final String pref, final boolean val) {
		return PreferenceManager.getDefaultSharedPreferences(c).getBoolean(pref, val);
	}

	public static void delPref(final Context c, final String pref) {
		Editor e = PreferenceManager.getDefaultSharedPreferences(c).edit();
		e.remove(pref);
		e.commit();
	}

	public static void setPref(final Context c, final String pref, final int val) {
		Editor e = PreferenceManager.getDefaultSharedPreferences(c).edit();
		e.putInt(pref, val);
		e.commit();

	}

	public static int getPref(final Context c, final String pref, final int val) {
		return PreferenceManager.getDefaultSharedPreferences(c).getInt(pref, val);
	}

	public static void setPref(final Context c, final String pref, final long val) {
		Editor e = PreferenceManager.getDefaultSharedPreferences(c).edit();
		e.putLong(pref, val);
		e.commit();
	}

	public static long getPref(final Context c, final String pref, final long val) {
		return PreferenceManager.getDefaultSharedPreferences(c).getLong(pref, val);
	}

	public final static boolean isValidEmail(final CharSequence target) {
		if (target == null) {
			return false;
		} else {
			return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
		}
	}

	public static View GetCustomView(final Activity c, final int res) {
		View v = c.getLayoutInflater().inflate(res, null);
		return v;
	}

	public static Bitmap scaleDownBitmap(Bitmap photo, final int newHeight, final Context context) {
		final float densityMultiplier = context.getResources().getDisplayMetrics().density;
		int h = (int) (newHeight * densityMultiplier);
		int w = (int) (h * photo.getWidth() / ((double) photo.getHeight()));
		photo = Bitmap.createScaledBitmap(photo, w, h, true);
		return photo;
	}

	public static void setListViewHeightBasedOnChildren(final ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}
		int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.UNSPECIFIED);
		int totalHeight = 0;
		View view = null;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			view = listAdapter.getView(i, view, listView);
			if (i == 0) {
				view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
			}
			view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
			totalHeight += view.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
		listView.requestLayout();
	}

	public static boolean isInternetConnected(final Context mContext) {
		boolean outcome = false;

		try {
			if (mContext != null) {
				ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

				NetworkInfo[] networkInfos = cm.getAllNetworkInfo();

				for (NetworkInfo tempNetworkInfo : networkInfos) {

					if (tempNetworkInfo.isConnected()) {
						outcome = true;
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return outcome;
	}

	public static float random(final float min, final float max) {
		return (float) (min + (Math.random() * ((max - min) + 1)));
	}

	public static int random(final int min, final int max) {
		return Math.round((float) (min + (Math.random() * ((max - min) + 1))));
	}

	public static void hideKeyBoard(final Context c, final View v) {
		InputMethodManager imm = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
	}

	public static int getDeviceWidth(final Context context) {
		try {
			DisplayMetrics metrics = context.getResources().getDisplayMetrics();
			return metrics.widthPixels;
		} catch (Exception e) {
			Utils.sendExceptionReport(e);
		}

		return 480;
	}

	public static int getDeviceWidth(final Context context, final int percentage) {
		int width = getDeviceWidth(context);
		float actualWidth = (width * percentage) / 100;

		return (int) actualWidth;
	}

	public static int getDeviceHeight(final Context context) {
		try {
			DisplayMetrics metrics = context.getResources().getDisplayMetrics();
			return metrics.heightPixels;
		} catch (Exception e) {
			Utils.sendExceptionReport(e);
		}

		return 800;
	}

	public static void sendExceptionReport(final Exception e) {
		e.printStackTrace();

		try {
			// Writer result = new StringWriter();
			// PrintWriter printWriter = new PrintWriter(result);
			// e.printStackTrace(printWriter);
			// String stacktrace = result.toString();
			// new CustomExceptionHandler(c, URLs.URL_STACKTRACE)
			// .sendToServer(stacktrace);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	public static String getAndroidId(final Context c) {
		String aid;
		try {
			aid = "";
			aid = Settings.Secure.getString(c.getContentResolver(), "android_id");

			if (aid == null) {
				aid = "No DeviceId";
			} else if (aid.length() <= 0) {
				aid = "No DeviceId";
			}
		} catch (Exception e) {
			e.printStackTrace();
			aid = "No DeviceId";
		}

		// Debug.e("", "aid : " + aid);

		return aid;
	}

	public static String getAppVersion(final Context c) {
		try {
			return c.getPackageManager().getPackageInfo(c.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return "Undefined";
	}

	public static int getAppVersionCode(final Context c) {
		try {
			return c.getPackageManager().getPackageInfo(c.getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static String getPhoneModel(final Context c) {
		try {
			return android.os.Build.MODEL;
		} catch (Exception e) {
			Utils.sendExceptionReport(e);
		}

		return "";
	}

	public static String getPhoneBrand(final Context c) {
		try {
			return android.os.Build.BRAND;
		} catch (Exception e) {
			Utils.sendExceptionReport(e);
		}

		return "";
	}

	public static String getPhoneBrandModel(final Context c) {
		try {
			return getPhoneBrand(c) + " " + getPhoneModel(c);
		} catch (Exception e) {
			Utils.sendExceptionReport(e);
		}

		return "";
	}

	public static String getOsVersion(final Context c) {
		try {
			return android.os.Build.VERSION.RELEASE;
		} catch (Exception e) {
			Utils.sendExceptionReport(e);
		}

		return "";
	}

	public static int getOsAPIlevel(final Context c) {
		try {
			return android.os.Build.VERSION.SDK_INT;
		} catch (Exception e) {
			Utils.sendExceptionReport(e);
		}

		return 0;
	}

	public static String parseCalendarFormat(final Calendar c, final String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
		return sdf.format(c.getTime());
	}

	public static String parseTime(final long time, final String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
		return sdf.format(new Date(time));
	}

	public static Date parseTime(final String time, final String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
		try {
			return sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return new Date();
	}

	public static String parseTime(final String time, final String fromPattern, final String toPattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(fromPattern, Locale.getDefault());
		try {
			Date d = sdf.parse(time);
			sdf = new SimpleDateFormat(toPattern, Locale.getDefault());
			return sdf.format(d);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	public static String nullSafe(final String content) {
		if (content == null) {
			return "";
		}

		return content;
	}

	public static boolean isSDcardMounted() {
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED) && !state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
			return true;
		}

		return false;
	}

	public void ShowProgressDialog(final Activity c, final String title, final String message) {
		dialog = new ProgressDialog(c);
		dialog.setTitle(title);
		dialog.setMessage(message);
		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		dialog.show();
	}

	public void closeProgressDialog() {
		dialog.dismiss();
	}

	public static void ShowToast(final Context c, final String Message) {
		Toast.makeText(c, Message, Toast.LENGTH_LONG).show();
	}

	public static String toMD5(final String string) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(string.getBytes("UTF-8"));
			byte[] md5bytes = md5.digest();
			StringBuffer hexmd5 = new StringBuffer();
			// for (int i = 0; i < md5bytes.length; i++)
			// hexmd5.append(Integer.toHexString(0xFF & md5bytes[i]));

			for (byte b : md5bytes) {
				if ((b & 0xFF) < 0x10) {
					hexmd5.append("0");
				}
				hexmd5.append(Integer.toHexString(b & 0xFF));
			}
			return hexmd5.toString();
		} catch (Exception e) {
			Utils.sendExceptionReport(e);
		}
		return "";
	}

	public static String implode(final ArrayList<String> data) {
		try {
			String devices = "";
			for (String iterable_element : data) {
				devices = devices + "," + iterable_element;
			}

			if (devices.length() > 0 && devices.startsWith(",")) {
				devices = devices.substring(1);
			}

			return devices;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}
}
