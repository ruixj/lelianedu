package cn.iegss.android.lanrenchengxu;
//Download by http://www.codefans.net
import java.util.ArrayList;
import java.util.HashMap;
import cn.iegss.android.lanrenchengxu.R;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.app.ProgressDialog;
import android.graphics.Bitmap;

public class LanrenchengxuActivity extends Activity {
	GridView menuGrid, toolbarGrid;
	ProgressBar progressBar;
	WebView mainWebView;

	/** 底部菜单图片 **/
	int[] menu_toolbar_image_array = { R.drawable.main_navigation_home,
			R.drawable.main_navigation_catagory,
			R.drawable.main_navigation_car, R.drawable.main_code_buy,
			R.drawable.main_navigation_more };
	/** 底部菜单文字 **/
	String[] menu_toolbar_name_array = new String[5];

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		    setContentView(R.layout.main);
		    
		    WebView mainWebView = (WebView) findViewById(R.id.mainWebView);
	        WebSettings webSettings = mainWebView.getSettings();
	        webSettings.setJavaScriptEnabled(true);   
	        
	        //mainWebView.setWebViewClient(new MyCustomWebViewClient());
	        //mainWebView.setWebChromeClient(new WebChromeClient());    
	        //mainWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);   
	        //mainWebView.loadUrl("http://www.kuitao8.com/?ti=m"); 
	        final Activity activity = this;
	        //mainWebView = (WebView) findViewById(R.id.mainWebView);
	        
	        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
	        
	        mainWebView.setWebViewClient(new myWebClient());
	        mainWebView.getSettings().setJavaScriptEnabled(true);
	        mainWebView.loadUrl("http://www.baidu.com");
	        
			menu_toolbar_name_array[0] = getResources().getString(
					R.string.main_navigation_home);
			menu_toolbar_name_array[1] = getResources().getString(
					R.string.main_navigation_catagory);
			menu_toolbar_name_array[2] = getResources().getString(
					R.string.main_navigation_car);
			menu_toolbar_name_array[3] = getResources().getString(
					R.string.main_code_buy);
			menu_toolbar_name_array[4] = getResources().getString(
					R.string.main_navigation_more);
		

		// 创建底部菜单 Toolbar
		toolbarGrid = (GridView) findViewById(R.id.GridView_toolbar);
		toolbarGrid.setBackgroundResource(R.drawable.main_navigation_background);// 设置背景
		toolbarGrid.setNumColumns(5);// 设置每行列数
		toolbarGrid.setGravity(Gravity.CENTER);// 位置居中
		toolbarGrid.setVerticalSpacing(10);// 垂直间隔
		toolbarGrid.setHorizontalSpacing(10);// 水平间隔
		toolbarGrid.setAdapter(getMenuAdapter(menu_toolbar_name_array,
				                              menu_toolbar_image_array));// 设置菜单Adapter

		//toolbarGrid.setSelection(1);
		toolbarGrid.setSelector(new ColorDrawable(Color.TRANSPARENT));
		
		/** 监听底部菜单选项 **/
		toolbarGrid.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View v, int item, long id) {
				 switch(item) {
				 case 0:
				 // Toast.makeText(getApplicationContext(), "Clicked on item 00000000000000" , Toast.LENGTH_SHORT).show();						
				  WebView wv = (WebView)findViewById(R.id.mainWebView);  
			      wv.loadUrl("http://www.sohu.com");  //打开首页
					  break;
				 case 1:
					 // Toast.makeText(getApplicationContext(), "Clicked on item 00000000000000" , Toast.LENGTH_SHORT).show();						
					  WebView wvs = (WebView)findViewById(R.id.mainWebView);  
				      wvs.loadUrl("http://www.sina.com.cn");  //打开首页
						  break;	
				 case 2:
					 // Toast.makeText(getApplicationContext(), "Clicked on item 00000000000000" , Toast.LENGTH_SHORT).show();						
					  WebView wvs1 = (WebView)findViewById(R.id.mainWebView);  
				      wvs1.loadUrl("http://www.163.com");  //打开首页
					  break;	
				 case 3:
					 // Toast.makeText(getApplicationContext(), "Clicked on item 00000000000000" , Toast.LENGTH_SHORT).show();						
					  WebView wvs3 = (WebView)findViewById(R.id.mainWebView);  
				      wvs3.loadUrl("http://www.163.com");  //打开首页
					  break;		
				 case 4:
					 // Toast.makeText(getApplicationContext(), "Clicked on item 00000000000000" , Toast.LENGTH_SHORT).show();						
					  WebView wvs4 = (WebView)findViewById(R.id.mainWebView);  
				      wvs4.loadUrl("http://www.163.com");  //打开首页
					  break;	  
				 }
			}
		});
		
		
	}
	
	
	private void setMenuSelect(View selectView){
		for(int i=0; i < toolbarGrid.getChildCount();i++){
			View view = toolbarGrid.getChildAt(i);
			if(view.equals(selectView)){
				view.setBackgroundResource(R.drawable.main_navigation_highlight_bg);
			}else{
				view.setBackgroundResource(0);
			}
		}
	}
    
	/**
	 * 构造菜单Adapter
	 * 
	 * @param menuNameArray
	 *            名称
	 * @param imageResourceArray
	 *            图片
	 * @return SimpleAdapter
	 */
	private SimpleAdapter getMenuAdapter(String[] menuNameArray,
			int[] imageResourceArray) {
		
		ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < menuNameArray.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemImage", imageResourceArray[i]);
			map.put("itemText", menuNameArray[i]);
			data.add(map);
		}
		SimpleAdapter simperAdapter = new SimpleAdapter(this, data,
				R.layout.item_menu, new String[] { "itemImage", "itemText" },
				new int[] { R.id.item_image, R.id.item_text });
		return simperAdapter;
	}
	public class myWebClient extends WebViewClient
    {
    	@Override
    	public void onPageStarted(WebView view, String url, Bitmap favicon) {
    		// TODO Auto-generated method stub
    		super.onPageStarted(view, url, favicon);
    	}
    	
    	@Override
    	public boolean shouldOverrideUrlLoading(WebView view, String url) {
    		// TODO Auto-generated method stub
    		
    		view.loadUrl(url);
    		return true;
    		
    	}
    	
    	@Override
    	public void onPageFinished(WebView view, String url) {
    		// TODO Auto-generated method stub
    		super.onPageFinished(view, url);
    		
    		progressBar.setVisibility(View.GONE);
    	}
    	
    }
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if ((keyCode == KeyEvent.KEYCODE_BACK) && mainWebView.canGoBack()) {
			mainWebView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
    // To handle "Back" key press event for WebView to go back to previous screen.

}

