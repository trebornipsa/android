package raa.android.widget.raastocks;

import java.util.Timer;
import java.util.TimerTask;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MainActivity extends AppWidgetProvider  
{
	private int m_iAngle=0;
	
	private Timer m_Timer;
	private int m_iTimeCounter=0;
	private Context m_Context;
	private int m_iAppWidgetID=0;
	private AppWidgetManager m_AppWidgetManager;
	
	private void draw(int iAngle)
	{
		m_Timer.purge();
        Paint p = new Paint(); 
        p.setAntiAlias(true);
        p.setStyle(Style.STROKE);
        p.setStrokeWidth(5);
        p.setColor(0xFFFF00FF);

        Bitmap bitmap = Bitmap.createBitmap(320, 100, Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawArc(new RectF(10, 10, 90, 90), 0, iAngle, false, p);

        p.setColor(0xFF0000FF);
        canvas.drawArc(new RectF(14, 14, 86, 86), iAngle/2-100, -iAngle-100, false, p);

        p.setColor(0xFF00F800);
        canvas.drawArc(new RectF(18, 18, 82, 82), -iAngle/2+100, iAngle+100, false, p);
        
        m_iAngle++;
        RemoteViews views = new RemoteViews(m_Context.getPackageName(), R.layout.activity_main);
        views.setImageViewBitmap(R.id.canvas, bitmap);

        m_AppWidgetManager.updateAppWidget(m_iAppWidgetID,views);
		
	}
	
	
	@Override
	public void onUpdate(Context context,AppWidgetManager appWidgetManager,int[] appWidgetIds)
	{
	      for(int i=0; i<appWidgetIds.length; i++)
	      {
	    	  	m_iAppWidgetID = appWidgetIds[i];
		    	m_Context=context;
		    	m_AppWidgetManager=appWidgetManager;
	
		        Toast.makeText(context, "widget added", Toast.LENGTH_SHORT).show();
	      
	          if(m_Timer==null)
	          {
		          m_Timer = new Timer(true);
		          m_Timer.scheduleAtFixedRate(new TimerTask() 
		          {
		              @Override
		              public void run() 
		              {
		            	  draw(m_iTimeCounter++);
		            	  if(m_iTimeCounter==360) m_iTimeCounter=0;
		              }
		          }, 0, 100);	          
	          }
	          
	      }
	}
	
	@Override
	public void onDeleted(Context context,int[] appWidgetIds)
	{
		// TODO Auto-generated method stub
		super.onDeleted(context,appWidgetIds);
	}

	@Override
	public void onEnabled(Context context)
	{
		super.onEnabled(context);	
	
//		m_Timer.
	}

	@Override
	public void onDisabled(Context context)
	{
		// TODO Auto-generated method stub
		super.onDisabled(context);
	}


	@Override
	public void onReceive(Context context,Intent intent)
	{
		// TODO Auto-generated method stub
		super.onReceive(context,intent);
	
//		draw(m_iAngle++);
	}
}
