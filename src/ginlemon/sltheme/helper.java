package ginlemon.sltheme;

import ginlemon.sltheme.example.R;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class helper extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.helper);

		TextView mainText = (TextView) findViewById(R.id.mainText);

		if(isSmartLauncherInstalled()){ 
			mainText.setText("This is a Smart Launcher Theme. You need Smart Launcher installed on your phone to use it.\n" +
					"To apply it, open smart launcher, enter in the preferences, select personalizzation, then Global theme."); 
			findViewById(R.id.disableActivity).setVisibility(View.VISIBLE);
			findViewById(R.id.download).setVisibility(View.GONE);
			findViewById(R.id.applyTheme).setVisibility(View.VISIBLE);
		}else{
			mainText.setText("This is a Smart Launcher Theme but the installer didn't found it on your device. You need Smart Launcher installed on your phone to use this theme."); 
			findViewById(R.id.disableActivity).setVisibility(View.GONE);
			findViewById(R.id.download).setVisibility(View.VISIBLE);
			findViewById(R.id.applyTheme).setVisibility(View.VISIBLE);
		}
	}   

	boolean isSmartLauncherInstalled(){
		String[] packagenames = {"ginlemon.flowerfree", "ginlemon.flowerpro","ginlemon.flowerpro.special"};
		for(String pname : packagenames){
			Intent i = new Intent();
			i.setPackage(pname);
			PackageManager pm = getPackageManager();
			if(pm.queryIntentActivities(i, 0).size()>0){
				return true;
			};
		}
		return false;
	}

	public void onClick(View v){
		if(v.getId()==R.id.applyTheme){
			setTheme();
		}
		if(v.getId()==R.id.disableActivity){
			Toast.makeText(this, "Lo faccio davvero!", 0).show();
		}
		if(v.getId()==R.id.download){
			Intent i = new Intent().setData(Uri.parse("market://details?id=ginlemon.flowerfree")); 
			startActivity(i);
		}

	}


	//	YOU DON'T NEED TO EDIT THIS CODE ↓ (It doesn't work with the version of SL < 1.7)
	public void setTheme(){
		try{
			startActivity(new Intent().setAction("ginlemon.smartlauncher.setGSLTHEME").putExtra("package", getPackageName()));
			return;
		}catch(Exception e){};
	}
	//	YOU DON'T NEED TO EDIT THIS CODE ↑ (It doesn't work with the version of SL < 1.7)

}
