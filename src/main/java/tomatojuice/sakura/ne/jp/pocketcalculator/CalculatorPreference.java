package tomatojuice.sakura.ne.jp.pocketcalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

public class CalculatorPreference extends PreferenceActivity
{
	private EditTextPreference editpref;
	private ListPreference themepref;
	private SharedPreferences sharedPreferences;
	private String sharedPreferences_str;
	private Intent intent;
	private int sp;

	// ChangeListenerのオブジェクト
	private OnPreferenceChangeListener onPref = new OnPreferenceChangeListener() {
		@Override
		public boolean onPreferenceChange(Preference preference, Object newValue) {
			Log.i("onPref", "PreferenceChangeのリスナー");
			intent = new Intent(getApplicationContext(), CalculatorPreference.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			finish();
			return onPref(preference,newValue);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i("onCreateの呼び出し", "onCreateの呼び出し");
		super.onCreate(savedInstanceState);

		setTheme();
		addPreferencesFromResource(R.xml.calpref);

		editpref = (EditTextPreference)findPreference("taxkey");
		editpref.setOnPreferenceChangeListener(onPref);

		themepref = (ListPreference)findPreference("theme");
		themepref.setOnPreferenceChangeListener(onPref);

	} // onCreate

	private void setTheme(){
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences_str = sharedPreferences.getString("theme", "0");
        Log.i("sharedPreferencesの値", sharedPreferences_str);
        sp = Integer.parseInt(sharedPreferences_str);

		switch(sp){
			case 0:
			  if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				  getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.indigo_900));
				  getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.indigo_900));
			  }
				setTheme(R.style.IndigoTheme);
				break;
			case 1:
			  if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				  getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.pink_900));
				  getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.pink_900));
			  }
				setTheme(R.style.PinkTheme);
				break;
			case 2:
			  if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				  getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.teal_900));
				  getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.teal_900));
			  }
				setTheme(R.style.TealTheme);
				break;
			case 3:
			  if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				  getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.orange_900));
				  getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.orange_900));
			  }
				setTheme(R.style.OrangeTheme);
				break;
			case 4:
			  if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				  getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.brown_900));
				  getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.brown_900));
			  }
				setTheme(R.style.BrownTheme);
				break;
			case 5:
			  if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				  getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.green_900));
				  getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.green_900));
			  }
				setTheme(R.style.GreenTheme);
				break;
			case 6:
			  if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				  getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.grey_900));
				  getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.grey_900));
			  }
				setTheme(R.style.MikuTheme);
				setContentView(R.layout.list_miku);
				break;
		} // switch
	}

	/** editprefの入力値を100以下に制限。 ※入力を数字のみに制限する方法はxmlに記述するだけで可能 **/
	public boolean onPref(Preference preference, Object newValue) {
		String input = newValue.toString(); // 入力値をstring型に代入
		int values = Integer.parseInt(input); // int型へ変更
		// 100以上だとエラーメッセージをToastで表示する
		if(values <=100){
			return true;
		}
		else{
			Toast.makeText(this,R.string.toastmessege,Toast.LENGTH_SHORT).show();
			return false;
		}
	} // onPref

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	public void onBackPressed() {
		Log.i("onBackPressed", "戻るボタンが押されました");
		intent = new Intent(getApplicationContext(), TomaCalculatorActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		finish();
		super.onBackPressed();
	}

} // 終了
