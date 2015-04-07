package com.example.kadai10;





import java.util.ArrayList;

import android.R.integer;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	ListView lst;
	
	ArrayList<String> ari = new ArrayList<String>();
	ArrayAdapter<String> ariadp;
	
	

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lst = (ListView)findViewById(R.id.listView1);
		ariadp = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ari);
		lst.setAdapter(ariadp);
		
		try{
			SQLiteDatabase db = this.openOrCreateDatabase("dai10",MODE_PRIVATE, null);
			Cursor c =	db.rawQuery("select * from dai10", null);
			String re = "名前\t\t電話\n";
			while(c.moveToNext())
			{
				//int n = c.getInt(0); //カラムの番号で取得
				String n = c.getString(c.getColumnIndex("name")); //カラムの名前で取得
				String p = c.getString(c.getColumnIndex("num"));
				re += n + p+"\n" ;
				ari.add(n+"\t\t"+p) ;
				ariadp.notifyDataSetChanged();
			}
			TextView tex = (TextView)findViewById(R.id.textView1);
			tex.setText(re);
			
			//lst = (ListView)findViewById(R.id.listView1);
			//ariadp = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ari);
			//lst.setAdapter(ariadp);
		}
		catch(Exception ex){
			TextView tex = (TextView)findViewById(R.id.textView1);
			tex.setText("\t");
		}
	}
	
	//インサート文　insert into kadai10(name,num)values("わっぱ",100);
	
	
	
	//登録ボタン
public void tr(View v) {
		
		//
		try{
			EditText edname = (EditText)findViewById(R.id.editTextname);
			EditText ednum = (EditText)findViewById(R.id.editTextphon);
			String name = edname.getText().toString();
			String num = ednum.getText().toString();
			
			SQLiteDatabase db = this.openOrCreateDatabase("dai10",MODE_PRIVATE, null);
			db.execSQL("insert into dai10(name,num)values(\""+name+"\","+num+")");
				ari.add(name+"\t\t"+num) ;
				ariadp.notifyDataSetChanged();
				
			//	TextView tex = (TextView)findViewById(R.id.textView1);
			//	tex.setText("登録完了");	
				
			
		}
		catch(Exception ex){
			try{
				SQLiteDatabase db = this.openOrCreateDatabase("dai10",MODE_PRIVATE, null);
				db.execSQL("create table dai10(name text,num text)");						
			//	TextView tex = (TextView)findViewById(R.id.textView1);
			//	tex.setText("テーブル作成功");
				
				EditText edname = (EditText)findViewById(R.id.editTextname);
				EditText ednum = (EditText)findViewById(R.id.editTextphon);
				String name = edname.getText().toString();
				String num = ednum.getText().toString(); 
				
				
			//	SQLiteDatabase db = this.openOrCreateDatabase("dai10",MODE_PRIVATE, null);
				db.execSQL("insert into dai10(name,num)values(\""+name+"\","+num+")");
					ari.add(name+"\t\t"+num) ;
					ariadp.notifyDataSetChanged();
				
				
			}
			catch(Exception ee){
				
				TextView tex = (TextView)findViewById(R.id.textView1);
				tex.setText(ee.toString());
			}
		}
		
	}
	
	
	

	public void er(View v) {
		
		
		try{
			SQLiteDatabase db = this.openOrCreateDatabase("dai10",MODE_PRIVATE, null);
			db.execSQL("drop table dai10");				
			ari.clear();
            ariadp.notifyDataSetChanged();
			//TextView tex = (TextView)findViewById(R.id.textView1);
			//tex.setText("テーブルは削除されました");
		
		}
		catch(Exception e){
			//TextView tex = (TextView)findViewById(R.id.textView1);
			//tex.setText("テーブル既にありません");
		}
	}
	
	
}
