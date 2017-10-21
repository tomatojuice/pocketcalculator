package tomatojuice.sakura.ne.jp.pocketcalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.util.Locale;

public class AboutTomaCalc extends AppCompatActivity {
	private String url;
	private TextView links;
	private CharSequence sequence;
	private AdView adView;
	private LinearLayout linear,about;
	private SharedPreferences listsp;
	private String listsp_str;
	private Toolbar toolbar;
	private Button[] buttons = new Button[11];
	private int sp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTheme();
		setContentView(R.layout.about);

		toolbar = (Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		getSupportActionBar().setDisplayShowHomeEnabled(false);
		getSupportActionBar().setTitle(""); // タイトル文字を消す
		
		setButton();

		links = (TextView)findViewById(R.id.weblink);
		Log.i("ロケール",Locale.getDefault().toString());

		if(Locale.CHINA.equals(Locale.getDefault())){	// 言語によってリンクするリンクの文字列を変更する
			url = "QQ空间：　   Go to <a href=\"http://64668838.qzone.qq.com/\">TomatoJuice的QQ空间</a>";
			Log.i("リンク","中国語OK");
		}
		else{
			url = "Web：　　<a href=\"https://plus.google.com/108302809836943777030?prsrc=3\">Google+</a>";
			Log.i("リンク","その他OK");
		}

		if(Build.VERSION.SDK_INT < Build.VERSION_CODES.N){ // Android 7.0以下
			sequence = Html.fromHtml(url);
		}
		else{
			sequence = Html.fromHtml(url,Html.FROM_HTML_MODE_COMPACT);
		}
		MovementMethod movement = LinkMovementMethod.getInstance();
		links.setMovementMethod(movement);
		links.setText(sequence);

// この４行だけでもリンクが張れる。これは「Pattern」に合致した文字列にリンクを自動で張るパターン。どちらもxmlのautLink属性は削除しなければダメ
//		url = "http://64668838.qzone.qq.com/";
//		links.setText("web：　google+");
//		Pattern pattern = Pattern.compile("google+");
//		Linkify.addLinks(links, pattern, url);

		createAdMob();

	} // onCreate()

	private void setTheme(){
		listsp = PreferenceManager.getDefaultSharedPreferences(this);
        listsp_str = listsp.getString("theme", "0");
        Log.i("listspの値", listsp_str);
        sp = Integer.parseInt(listsp_str);

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
				break;
		} // switch
	} // setTheme

	private void setButton(){
        buttons[0] = (Button)findViewById(R.id.button_c);
        buttons[1] = (Button)findViewById(R.id.button_ca);
        buttons[2] = (Button)findViewById(R.id.button_ce);
        buttons[3] = (Button)findViewById(R.id.button_cm);
        buttons[4] = (Button)findViewById(R.id.button_includtaxes);
        buttons[5] = (Button)findViewById(R.id.button_mminus);
        buttons[6] = (Button)findViewById(R.id.button_mplus);
        buttons[7] = (Button)findViewById(R.id.button_plusminus);
        buttons[8] = (Button)findViewById(R.id.button_rm);
        buttons[9] = (Button)findViewById(R.id.button_withouttaxes);
        buttons[10] = (Button)findViewById(R.id.button_percent);

		about = (LinearLayout)findViewById(R.id.about_back);

		switch(sp){ // Theme毎のボタン、toolbarの色をセット
		case 0:
			for(int i=0; i<11; i++){
				buttons[i].setTextColor(ContextCompat.getColor(this,R.color.indigo_50));
				toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.indigo_50));
			} // for
			about.setBackgroundResource(R.drawable.back_indigo);
			break;
		case 1:
			for(int i=0; i<11; i++){
				buttons[i].setTextColor(ContextCompat.getColor(this,R.color.pink_50));
				toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.pink_50));
			} // for
			about.setBackgroundResource(R.drawable.back_pink);
			break;
		case 2:
			for(int i=0; i<11; i++){
				buttons[i].setTextColor(ContextCompat.getColor(this,R.color.teal_50));
				toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.teal_50));
			} // for
			about.setBackgroundResource(R.drawable.back_teal);
			break;
		case 3:
			for(int i=0; i<11; i++){
				buttons[i].setTextColor(ContextCompat.getColor(this,R.color.orange_50));
				toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.orange_50));
			} // for
			about.setBackgroundResource(R.drawable.back_orange);
			break;
		case 4:
			for(int i=0; i<11; i++){
				buttons[i].setTextColor(ContextCompat.getColor(this,R.color.brown_50));
				toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.brown_50));
			} // for
			about.setBackgroundResource(R.drawable.back_brown);
			break;
		case 5:
			for(int i=0; i<11; i++){
//				buttons[i].setBackgroundResource(R.color.green_500);
				buttons[i].setTextColor(ContextCompat.getColor(this,R.color.green_50));
				toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.green_50));
			} // for
			about.setBackgroundResource(R.drawable.back_green);
			break;
		case 6:
			for(int i=0; i<11; i++){
				buttons[i].setTextColor(ContextCompat.getColor(this,R.color.miku_3));
				toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.miku_3));
			} // for
			about.setBackgroundResource(R.drawable.back_miku);
			break;	
		} // switch
	} // setButton

	private void createAdMob(){

		// ここで現在のネットワーク状態をチェック
		ConnectivityManager connect = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
		NetworkInfo netinfo = connect.getActiveNetworkInfo();

		/** ネットに接続していたらAdmobを表示する **/
		if(netinfo != null){
			adView = new AdView(this);
			adView.setAdUnitId("ca-app-pub-6891896770413839/4340258132");
			adView.setAdSize(AdSize.SMART_BANNER);

			linear = (LinearLayout)findViewById(R.id.admoblinear);
			linear.addView(adView);
			AdRequest adRequest = new AdRequest.Builder().build();
			adView.loadAd(adRequest);
			Log.i("NetWorkInfo","Net接続ステータス： " + netinfo.toString());
		}
		else{ // 接続されていない時
			Log.i("NetWorkInfo","未接続です ");
		}

	} // createAdMob


	@Override
	protected void onDestroy() {
		if(adView!=null){
			adView.destroy();
		}
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		if(adView!=null){
			adView.pause();
		}
		super.onPause();
	}

	@Override
	protected void onResume() {
		if(adView!=null){
			adView.resume();
		}
		super.onResume();
	}

	/** メニューの作成 **/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu,menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId = item.getItemId();

		switch(itemId){
			case R.id.setting:
				Intent intent_pref = new Intent(this,CalculatorPreference.class);
				startActivity(intent_pref);
				finish();
				break;
			case R.id.about:
				Intent intent_about = new Intent(this,AboutTomaCalc.class);
				startActivity(intent_about);
				finish();
				break;
		}
		
		return super.onOptionsItemSelected(item);
	} // onOptionsItemSelected

} // 終了
