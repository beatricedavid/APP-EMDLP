package b4a.example;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = true;
	public static final boolean includeTitle = true;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
				p.finish();
			}
		}
        processBA.setActivityPaused(true);
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(this, processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return main.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null)
            return;
        if (this != mostCurrent)
			return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
            main mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
            if (mc != mostCurrent)
                return;
		    processBA.raiseEvent(mc._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }



public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        anywheresoftware.b4a.samples.httputils2.httputils2service._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}
public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
vis = vis | (edicion.mostCurrent != null);
vis = vis | (altaproducto.mostCurrent != null);
return vis;}

private static BA killProgramHelper(BA ba) {
    if (ba == null)
        return null;
    anywheresoftware.b4a.BA.SharedProcessBA sharedProcessBA = ba.sharedProcessBA;
    if (sharedProcessBA == null || sharedProcessBA.activityBA == null)
        return null;
    return sharedProcessBA.activityBA.get();
}
public static void killProgram() {
     {
            Activity __a = null;
            if (main.previousOne != null) {
				__a = main.previousOne.get();
			}
            else {
                BA ba = killProgramHelper(main.mostCurrent == null ? null : main.mostCurrent.processBA);
                if (ba != null) __a = ba.activity;
            }
            if (__a != null)
				__a.finish();}

BA.applicationContext.stopService(new android.content.Intent(BA.applicationContext, starter.class));
 {
            Activity __a = null;
            if (edicion.previousOne != null) {
				__a = edicion.previousOne.get();
			}
            else {
                BA ba = killProgramHelper(edicion.mostCurrent == null ? null : edicion.mostCurrent.processBA);
                if (ba != null) __a = ba.activity;
            }
            if (__a != null)
				__a.finish();}

 {
            Activity __a = null;
            if (altaproducto.previousOne != null) {
				__a = altaproducto.previousOne.get();
			}
            else {
                BA ba = killProgramHelper(altaproducto.mostCurrent == null ? null : altaproducto.mostCurrent.processBA);
                if (ba != null) __a = ba.activity;
            }
            if (__a != null)
				__a.finish();}

}
public anywheresoftware.b4a.keywords.Common __c = null;
public static int _idproducto = 0;
public static String _nombreproducto = "";
public static String _modeloproducto = "";
public static String _tipoproducto = "";
public static String _precioproducto = "";
public static anywheresoftware.b4a.sql.SQL _sql1 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtbuscar = null;
public anywheresoftware.b4a.objects.ListViewWrapper _lvnombre = null;
public anywheresoftware.b4a.samples.httputils2.httpjob _webservice = null;
public anywheresoftware.b4a.objects.collections.JSONParser _json = null;
public anywheresoftware.b4a.sql.SQL.CursorWrapper _registro = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _colorfondo = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _colorbusqueda = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _colorlista = null;
public static boolean _esdescargaexcel = false;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public b4a.example.starter _starter = null;
public b4a.example.edicion _edicion = null;
public b4a.example.altaproducto _altaproducto = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
String _ruta = "";
RDebugUtils.currentLine=131072;
 //BA.debugLineNum = 131072;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=131073;
 //BA.debugLineNum = 131073;BA.debugLine="Activity.LoadLayout(\"PantallaPrincipal\")";
mostCurrent._activity.LoadLayout("PantallaPrincipal",mostCurrent.activityBA);
RDebugUtils.currentLine=131074;
 //BA.debugLineNum = 131074;BA.debugLine="webservice.Initialize(\"webservice\", Me)";
mostCurrent._webservice._initialize(processBA,"webservice",main.getObject());
RDebugUtils.currentLine=131075;
 //BA.debugLineNum = 131075;BA.debugLine="lvNombre.TwoLinesLayout.Label.TextSize = 10";
mostCurrent._lvnombre.getTwoLinesLayout().Label.setTextSize((float) (10));
RDebugUtils.currentLine=131076;
 //BA.debugLineNum = 131076;BA.debugLine="lvNombre.TwoLinesLayout.ItemHeight = 80dip";
mostCurrent._lvnombre.getTwoLinesLayout().setItemHeight(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80)));
RDebugUtils.currentLine=131077;
 //BA.debugLineNum = 131077;BA.debugLine="lvNombre.TwoLinesLayout.Label.SetLayout(5dip,2dip";
mostCurrent._lvnombre.getTwoLinesLayout().Label.SetLayout(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (300)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
RDebugUtils.currentLine=131078;
 //BA.debugLineNum = 131078;BA.debugLine="lvNombre.TwoLinesLayout.SecondLabel.SetLayout(5di";
mostCurrent._lvnombre.getTwoLinesLayout().SecondLabel.SetLayout(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (55)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
RDebugUtils.currentLine=131079;
 //BA.debugLineNum = 131079;BA.debugLine="lvNombre.TwoLinesLayout.Label.TextColor = Colors.";
mostCurrent._lvnombre.getTwoLinesLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=131082;
 //BA.debugLineNum = 131082;BA.debugLine="Activity.AddMenuItem(\"Sincronizar Datos\",\"xMenuSi";
mostCurrent._activity.AddMenuItem(BA.ObjectToCharSequence("Sincronizar Datos"),"xMenuSincronizarDatos");
RDebugUtils.currentLine=131083;
 //BA.debugLineNum = 131083;BA.debugLine="Activity.AddMenuItem(\"Nuevo Producto\",\"xMenuNuevo";
mostCurrent._activity.AddMenuItem(BA.ObjectToCharSequence("Nuevo Producto"),"xMenuNuevoProducto");
RDebugUtils.currentLine=131084;
 //BA.debugLineNum = 131084;BA.debugLine="Activity.AddMenuItem(\"Descargar DB\",\"xMenuDescarg";
mostCurrent._activity.AddMenuItem(BA.ObjectToCharSequence("Descargar DB"),"xMenuDescargarDB");
RDebugUtils.currentLine=131086;
 //BA.debugLineNum = 131086;BA.debugLine="Dim ruta As String";
_ruta = "";
RDebugUtils.currentLine=131090;
 //BA.debugLineNum = 131090;BA.debugLine="ruta = File.DirInternal";
_ruta = anywheresoftware.b4a.keywords.Common.File.getDirInternal();
RDebugUtils.currentLine=131092;
 //BA.debugLineNum = 131092;BA.debugLine="If File.Exists(ruta,\"basedatos.db\")=False Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(_ruta,"basedatos.db")==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=131093;
 //BA.debugLineNum = 131093;BA.debugLine="File.Copy(File.DirAssets,\"basedatos.db\",ruta,\"ba";
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"basedatos.db",_ruta,"basedatos.db");
 };
RDebugUtils.currentLine=131095;
 //BA.debugLineNum = 131095;BA.debugLine="SQL1.Initialize(ruta,\"basedatos.db\",True)";
_sql1.Initialize(_ruta,"basedatos.db",anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=131096;
 //BA.debugLineNum = 131096;BA.debugLine="SetDivider(lvNombre, Colors.LightGray,20)";
_setdivider(mostCurrent._lvnombre,anywheresoftware.b4a.keywords.Common.Colors.LightGray,(int) (20));
RDebugUtils.currentLine=131099;
 //BA.debugLineNum = 131099;BA.debugLine="ColorBusqueda.Initialize2(Colors.RGB(248,248,255)";
mostCurrent._colorbusqueda.Initialize2(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (248),(int) (248),(int) (255)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (80),(int) (80),(int) (80)));
RDebugUtils.currentLine=131100;
 //BA.debugLineNum = 131100;BA.debugLine="ColorLista.Initialize(Colors.RGB(248,248,255), 5d";
mostCurrent._colorlista.Initialize(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (248),(int) (248),(int) (255)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)));
RDebugUtils.currentLine=131101;
 //BA.debugLineNum = 131101;BA.debugLine="ColorFondo.Initialize(Colors.RGB(248,248,255), 5d";
mostCurrent._colorfondo.Initialize(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (248),(int) (248),(int) (255)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)));
RDebugUtils.currentLine=131102;
 //BA.debugLineNum = 131102;BA.debugLine="txtBuscar.Background=ColorBusqueda";
mostCurrent._txtbuscar.setBackground((android.graphics.drawable.Drawable)(mostCurrent._colorbusqueda.getObject()));
RDebugUtils.currentLine=131103;
 //BA.debugLineNum = 131103;BA.debugLine="lvNombre.Background = ColorLista";
mostCurrent._lvnombre.setBackground((android.graphics.drawable.Drawable)(mostCurrent._colorlista.getObject()));
RDebugUtils.currentLine=131104;
 //BA.debugLineNum = 131104;BA.debugLine="Activity.Background = ColorFondo";
mostCurrent._activity.setBackground((android.graphics.drawable.Drawable)(mostCurrent._colorfondo.getObject()));
RDebugUtils.currentLine=131105;
 //BA.debugLineNum = 131105;BA.debugLine="End Sub";
return "";
}
public static String  _setdivider(anywheresoftware.b4a.objects.ListViewWrapper _lv,int _color,int _height) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "setdivider", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "setdivider", new Object[] {_lv,_color,_height}));}
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
RDebugUtils.currentLine=720896;
 //BA.debugLineNum = 720896;BA.debugLine="Sub SetDivider(lv As ListView, Color As Int, Heigh";
RDebugUtils.currentLine=720897;
 //BA.debugLineNum = 720897;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
RDebugUtils.currentLine=720898;
 //BA.debugLineNum = 720898;BA.debugLine="r.Target = lv";
_r.Target = (Object)(_lv.getObject());
RDebugUtils.currentLine=720899;
 //BA.debugLineNum = 720899;BA.debugLine="Dim CD As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
RDebugUtils.currentLine=720900;
 //BA.debugLineNum = 720900;BA.debugLine="CD.Initialize(Color, 0)";
_cd.Initialize(_color,(int) (0));
RDebugUtils.currentLine=720901;
 //BA.debugLineNum = 720901;BA.debugLine="r.RunMethod4(\"setDivider\", Array As Object(CD), A";
_r.RunMethod4("setDivider",new Object[]{(Object)(_cd.getObject())},new String[]{"android.graphics.drawable.Drawable"});
RDebugUtils.currentLine=720902;
 //BA.debugLineNum = 720902;BA.debugLine="r.RunMethod2(\"setDividerHeight\", Height, \"java.la";
_r.RunMethod2("setDividerHeight",BA.NumberToString(_height),"java.lang.int");
RDebugUtils.currentLine=720903;
 //BA.debugLineNum = 720903;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_keypress", false))
	 {return ((Boolean) Debug.delegate(mostCurrent.activityBA, "activity_keypress", new Object[] {_keycode}));}
int _mensaje = 0;
RDebugUtils.currentLine=1114112;
 //BA.debugLineNum = 1114112;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
RDebugUtils.currentLine=1114113;
 //BA.debugLineNum = 1114113;BA.debugLine="Dim mensaje As Int";
_mensaje = 0;
RDebugUtils.currentLine=1114114;
 //BA.debugLineNum = 1114114;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
RDebugUtils.currentLine=1114115;
 //BA.debugLineNum = 1114115;BA.debugLine="mensaje = Msgbox2 (\"¿Cerrar el programa?\",\"\",\"Si";
_mensaje = anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToCharSequence("¿Cerrar el programa?"),BA.ObjectToCharSequence(""),"Si","","No",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA);
RDebugUtils.currentLine=1114116;
 //BA.debugLineNum = 1114116;BA.debugLine="If mensaje = -1 Then";
if (_mensaje==-1) { 
RDebugUtils.currentLine=1114117;
 //BA.debugLineNum = 1114117;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 }else {
RDebugUtils.currentLine=1114119;
 //BA.debugLineNum = 1114119;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 };
RDebugUtils.currentLine=1114122;
 //BA.debugLineNum = 1114122;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="main";
RDebugUtils.currentLine=262144;
 //BA.debugLineNum = 262144;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=262146;
 //BA.debugLineNum = 262146;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=196608;
 //BA.debugLineNum = 196608;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=196610;
 //BA.debugLineNum = 196610;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(anywheresoftware.b4a.samples.httputils2.httpjob _job) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "jobdone", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "jobdone", new Object[] {_job}));}
anywheresoftware.b4a.objects.collections.Map _map1 = null;
anywheresoftware.b4a.objects.collections.List _menuitems = null;
anywheresoftware.b4a.objects.collections.Map _m = null;
String _contienejob = "";
String _registroexcel = "";
anywheresoftware.b4a.objects.collections.List _listaexcel = null;
int _i = 0;
RDebugUtils.currentLine=983040;
 //BA.debugLineNum = 983040;BA.debugLine="Sub JobDone (Job As HttpJob)";
RDebugUtils.currentLine=983041;
 //BA.debugLineNum = 983041;BA.debugLine="Dim Map1 As Map";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=983042;
 //BA.debugLineNum = 983042;BA.debugLine="Dim MenuItems As List";
_menuitems = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=983043;
 //BA.debugLineNum = 983043;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=983044;
 //BA.debugLineNum = 983044;BA.debugLine="Dim contieneJob As String";
_contienejob = "";
RDebugUtils.currentLine=983045;
 //BA.debugLineNum = 983045;BA.debugLine="Dim registroExcel As String";
_registroexcel = "";
RDebugUtils.currentLine=983046;
 //BA.debugLineNum = 983046;BA.debugLine="Dim listaExcel As List";
_listaexcel = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=983050;
 //BA.debugLineNum = 983050;BA.debugLine="If Job.Success = True Then";
if (_job._success==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=983051;
 //BA.debugLineNum = 983051;BA.debugLine="json.Initialize(Job.GetString)";
mostCurrent._json.Initialize(_job._getstring());
RDebugUtils.currentLine=983052;
 //BA.debugLineNum = 983052;BA.debugLine="contieneJob = Job.GetString";
_contienejob = _job._getstring();
RDebugUtils.currentLine=983053;
 //BA.debugLineNum = 983053;BA.debugLine="If contieneJob = \"[]\" Then";
if ((_contienejob).equals("[]")) { 
RDebugUtils.currentLine=983054;
 //BA.debugLineNum = 983054;BA.debugLine="Msgbox (\"no hay datos\", \"\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("no hay datos"),BA.ObjectToCharSequence(""),mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=983056;
 //BA.debugLineNum = 983056;BA.debugLine="If EsDescargaExcel Then	'ES DESCARGA DE DATOS P";
if (_esdescargaexcel) { 
RDebugUtils.currentLine=983063;
 //BA.debugLineNum = 983063;BA.debugLine="listaExcel.Initialize";
_listaexcel.Initialize();
RDebugUtils.currentLine=983064;
 //BA.debugLineNum = 983064;BA.debugLine="Map1 = json.NextObject";
_map1 = mostCurrent._json.NextObject();
RDebugUtils.currentLine=983065;
 //BA.debugLineNum = 983065;BA.debugLine="MenuItems = Map1.Get(\"productos\")";
_menuitems.setObject((java.util.List)(_map1.Get((Object)("productos"))));
RDebugUtils.currentLine=983066;
 //BA.debugLineNum = 983066;BA.debugLine="ToastMessageShow (File.DirDefaultExternal, Tru";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal()),anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=983067;
 //BA.debugLineNum = 983067;BA.debugLine="Log(File.DirDefaultExternal)";
anywheresoftware.b4a.keywords.Common.LogImpl("0983067",anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal(),0);
RDebugUtils.currentLine=983068;
 //BA.debugLineNum = 983068;BA.debugLine="For i=0 To MenuItems.Size -1";
{
final int step19 = 1;
final int limit19 = (int) (_menuitems.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit19 ;_i = _i + step19 ) {
RDebugUtils.currentLine=983069;
 //BA.debugLineNum = 983069;BA.debugLine="m = MenuItems.Get(i)";
_m.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_menuitems.Get(_i)));
RDebugUtils.currentLine=983070;
 //BA.debugLineNum = 983070;BA.debugLine="registroExcel = m.Get(\"id\") & \";\" & m.Get(\"no";
_registroexcel = BA.ObjectToString(_m.Get((Object)("id")))+";"+BA.ObjectToString(_m.Get((Object)("nombre")))+";"+BA.ObjectToString(_m.Get((Object)("modelo")))+";"+BA.ObjectToString(_m.Get((Object)("tipo")))+";"+BA.ObjectToString(_m.Get((Object)("precio")));
RDebugUtils.currentLine=983071;
 //BA.debugLineNum = 983071;BA.debugLine="File.WriteString(File.DirRootExternal,\"baseda";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"basedatos.csv",_registroexcel);
 }
};
RDebugUtils.currentLine=983078;
 //BA.debugLineNum = 983078;BA.debugLine="EsDescargaExcel=False";
_esdescargaexcel = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=983079;
 //BA.debugLineNum = 983079;BA.debugLine="ToastMessageShow(\"Descarga completa\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Descarga completa"),anywheresoftware.b4a.keywords.Common.False);
 }else {
RDebugUtils.currentLine=983082;
 //BA.debugLineNum = 983082;BA.debugLine="SQL1.ExecNonQuery(\"DELETE FROM productos\")";
_sql1.ExecNonQuery("DELETE FROM productos");
RDebugUtils.currentLine=983083;
 //BA.debugLineNum = 983083;BA.debugLine="SQL1.ExecNonQuery(\"VACUUM\")";
_sql1.ExecNonQuery("VACUUM");
RDebugUtils.currentLine=983084;
 //BA.debugLineNum = 983084;BA.debugLine="Map1 = json.NextObject";
_map1 = mostCurrent._json.NextObject();
RDebugUtils.currentLine=983085;
 //BA.debugLineNum = 983085;BA.debugLine="MenuItems = Map1.Get(\"productos\")";
_menuitems.setObject((java.util.List)(_map1.Get((Object)("productos"))));
RDebugUtils.currentLine=983086;
 //BA.debugLineNum = 983086;BA.debugLine="For i=0 To MenuItems.Size -1";
{
final int step31 = 1;
final int limit31 = (int) (_menuitems.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit31 ;_i = _i + step31 ) {
RDebugUtils.currentLine=983087;
 //BA.debugLineNum = 983087;BA.debugLine="m = MenuItems.Get(i)";
_m.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_menuitems.Get(_i)));
RDebugUtils.currentLine=983088;
 //BA.debugLineNum = 983088;BA.debugLine="SQL1.ExecNonQuery(\"INSERT INTO productos (id,";
_sql1.ExecNonQuery("INSERT INTO productos (id,nombre,modelo,tipo,precio,categoria) VALUES('"+BA.ObjectToString(_m.Get((Object)("id")))+"','"+BA.ObjectToString(_m.Get((Object)("nombre")))+"','"+BA.ObjectToString(_m.Get((Object)("modelo")))+"','"+BA.ObjectToString(_m.Get((Object)("tipo")))+"','"+BA.ObjectToString(_m.Get((Object)("precio")))+"','"+BA.ObjectToString(_m.Get((Object)("categoria")))+"')");
 }
};
 };
 };
 }else {
RDebugUtils.currentLine=983093;
 //BA.debugLineNum = 983093;BA.debugLine="Msgbox (\"error en el jobdone: \" & Job.ErrorMessa";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("error en el jobdone: "+_job._errormessage),BA.ObjectToCharSequence("EMDLP"),mostCurrent.activityBA);
 };
RDebugUtils.currentLine=983095;
 //BA.debugLineNum = 983095;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
RDebugUtils.currentLine=983097;
 //BA.debugLineNum = 983097;BA.debugLine="End Sub";
return "";
}
public static String  _lvnombre_itemlongclick(int _position,Object _value) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "lvnombre_itemlongclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "lvnombre_itemlongclick", new Object[] {_position,_value}));}
int _item = 0;
anywheresoftware.b4a.sql.SQL.CursorWrapper _reg = null;
RDebugUtils.currentLine=1048576;
 //BA.debugLineNum = 1048576;BA.debugLine="Sub lvNombre_ItemLongClick (Position As Int, Value";
RDebugUtils.currentLine=1048577;
 //BA.debugLineNum = 1048577;BA.debugLine="Dim item As Int";
_item = 0;
RDebugUtils.currentLine=1048578;
 //BA.debugLineNum = 1048578;BA.debugLine="item = Value";
_item = (int)(BA.ObjectToNumber(_value));
RDebugUtils.currentLine=1048579;
 //BA.debugLineNum = 1048579;BA.debugLine="Dim reg As Cursor";
_reg = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
RDebugUtils.currentLine=1048580;
 //BA.debugLineNum = 1048580;BA.debugLine="reg = SQL1.ExecQuery(\"select * from productos whe";
_reg.setObject((android.database.Cursor)(_sql1.ExecQuery("select * from productos where id = '"+BA.NumberToString(_item)+"'")));
RDebugUtils.currentLine=1048582;
 //BA.debugLineNum = 1048582;BA.debugLine="reg.Position = 0";
_reg.setPosition((int) (0));
RDebugUtils.currentLine=1048583;
 //BA.debugLineNum = 1048583;BA.debugLine="idProducto = reg.GetInt(\"id\")";
_idproducto = _reg.GetInt("id");
RDebugUtils.currentLine=1048584;
 //BA.debugLineNum = 1048584;BA.debugLine="nombreProducto = reg.GetString(\"nombre\")";
_nombreproducto = _reg.GetString("nombre");
RDebugUtils.currentLine=1048585;
 //BA.debugLineNum = 1048585;BA.debugLine="modeloProducto = reg.GetString(\"modelo\")";
_modeloproducto = _reg.GetString("modelo");
RDebugUtils.currentLine=1048586;
 //BA.debugLineNum = 1048586;BA.debugLine="tipoProducto = reg.Getstring(\"tipo\")";
_tipoproducto = _reg.GetString("tipo");
RDebugUtils.currentLine=1048587;
 //BA.debugLineNum = 1048587;BA.debugLine="precioProducto = reg.Getstring(\"precio\")";
_precioproducto = _reg.GetString("precio");
RDebugUtils.currentLine=1048589;
 //BA.debugLineNum = 1048589;BA.debugLine="StartActivity(\"Edicion\")";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)("Edicion"));
RDebugUtils.currentLine=1048590;
 //BA.debugLineNum = 1048590;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=1048591;
 //BA.debugLineNum = 1048591;BA.debugLine="End Sub";
return "";
}
public static String  _txtbuscar_textchanged(String _old,String _new) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "txtbuscar_textchanged", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "txtbuscar_textchanged", new Object[] {_old,_new}));}
anywheresoftware.b4a.objects.drawable.ColorDrawable _color1 = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _color2 = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _color3 = null;
float _tamanioletra = 0f;
int _i = 0;
RDebugUtils.currentLine=1179648;
 //BA.debugLineNum = 1179648;BA.debugLine="Sub txtBuscar_TextChanged (Old As String, New As S";
RDebugUtils.currentLine=1179649;
 //BA.debugLineNum = 1179649;BA.debugLine="Dim Color1, Color2,Color3 As ColorDrawable";
_color1 = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
_color2 = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
_color3 = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
RDebugUtils.currentLine=1179650;
 //BA.debugLineNum = 1179650;BA.debugLine="Dim TamanioLetra As Float";
_tamanioletra = 0f;
RDebugUtils.currentLine=1179652;
 //BA.debugLineNum = 1179652;BA.debugLine="TamanioLetra=16";
_tamanioletra = (float) (16);
RDebugUtils.currentLine=1179654;
 //BA.debugLineNum = 1179654;BA.debugLine="lvNombre.Clear";
mostCurrent._lvnombre.Clear();
RDebugUtils.currentLine=1179655;
 //BA.debugLineNum = 1179655;BA.debugLine="Color1.Initialize(Colors.Red,5dip)";
_color1.Initialize(anywheresoftware.b4a.keywords.Common.Colors.Red,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)));
RDebugUtils.currentLine=1179656;
 //BA.debugLineNum = 1179656;BA.debugLine="Color2.Initialize(Colors.Blue,5dip)";
_color2.Initialize(anywheresoftware.b4a.keywords.Common.Colors.Blue,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)));
RDebugUtils.currentLine=1179657;
 //BA.debugLineNum = 1179657;BA.debugLine="Color3.Initialize(Colors.Cyan, 5dip)";
_color3.Initialize(anywheresoftware.b4a.keywords.Common.Colors.Cyan,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)));
RDebugUtils.currentLine=1179659;
 //BA.debugLineNum = 1179659;BA.debugLine="registro = SQL1.ExecQuery(\"select * from (select";
mostCurrent._registro.setObject((android.database.Cursor)(_sql1.ExecQuery("select * from (select * from productos where nombre like '%"+mostCurrent._txtbuscar.getText()+"%' UNION select * from productos where modelo like '%"+mostCurrent._txtbuscar.getText()+"%') order by nombre, modelo, tipo, precio asc")));
RDebugUtils.currentLine=1179660;
 //BA.debugLineNum = 1179660;BA.debugLine="lvNombre.TwoLinesLayout.Label.Gravity = Gravity.C";
mostCurrent._lvnombre.getTwoLinesLayout().Label.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL);
RDebugUtils.currentLine=1179661;
 //BA.debugLineNum = 1179661;BA.debugLine="lvNombre.TwoLinesLayout.Label.TextSize = TamanioL";
mostCurrent._lvnombre.getTwoLinesLayout().Label.setTextSize(_tamanioletra);
RDebugUtils.currentLine=1179662;
 //BA.debugLineNum = 1179662;BA.debugLine="lvNombre.TwoLinesLayout.SecondLabel.TextSize=18";
mostCurrent._lvnombre.getTwoLinesLayout().SecondLabel.setTextSize((float) (18));
RDebugUtils.currentLine=1179665;
 //BA.debugLineNum = 1179665;BA.debugLine="If registro.RowCount > 0 Then";
if (mostCurrent._registro.getRowCount()>0) { 
RDebugUtils.currentLine=1179666;
 //BA.debugLineNum = 1179666;BA.debugLine="For i = 0 To registro.RowCount - 1";
{
final int step13 = 1;
final int limit13 = (int) (mostCurrent._registro.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit13 ;_i = _i + step13 ) {
RDebugUtils.currentLine=1179667;
 //BA.debugLineNum = 1179667;BA.debugLine="registro.Position = i";
mostCurrent._registro.setPosition(_i);
RDebugUtils.currentLine=1179669;
 //BA.debugLineNum = 1179669;BA.debugLine="lvNombre.AddTwoLines2(registro.GetString(\"nombr";
mostCurrent._lvnombre.AddTwoLines2(BA.ObjectToCharSequence(mostCurrent._registro.GetString("nombre")+" | "+mostCurrent._registro.GetString("modelo")+" | "+mostCurrent._registro.GetString("tipo")),BA.ObjectToCharSequence(mostCurrent._registro.GetString("precio")),(Object)(mostCurrent._registro.GetInt("id")));
RDebugUtils.currentLine=1179670;
 //BA.debugLineNum = 1179670;BA.debugLine="lvNombre.TwoLinesLayout.SecondLabel.TextColor=C";
mostCurrent._lvnombre.getTwoLinesLayout().SecondLabel.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (100),(int) (0)));
 }
};
 };
RDebugUtils.currentLine=1179684;
 //BA.debugLineNum = 1179684;BA.debugLine="End Sub";
return "";
}
public static String  _xmenudescargardb_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "xmenudescargardb_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "xmenudescargardb_click", null));}
RDebugUtils.currentLine=917504;
 //BA.debugLineNum = 917504;BA.debugLine="Sub xMenuDescargarDB_click";
RDebugUtils.currentLine=917505;
 //BA.debugLineNum = 917505;BA.debugLine="ProgressDialogShow(\"Generando archivo...\")";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,BA.ObjectToCharSequence("Generando archivo..."));
RDebugUtils.currentLine=917507;
 //BA.debugLineNum = 917507;BA.debugLine="EsDescargaExcel=True";
_esdescargaexcel = anywheresoftware.b4a.keywords.Common.True;
RDebugUtils.currentLine=917508;
 //BA.debugLineNum = 917508;BA.debugLine="webservice.Download(\"https://elmundodelasplantas.";
mostCurrent._webservice._download("https://elmundodelasplantas.000webhostapp.com/consultarTodos.php");
RDebugUtils.currentLine=917511;
 //BA.debugLineNum = 917511;BA.debugLine="End Sub";
return "";
}
public static String  _xmenunuevoproducto_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "xmenunuevoproducto_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "xmenunuevoproducto_click", null));}
RDebugUtils.currentLine=851968;
 //BA.debugLineNum = 851968;BA.debugLine="Sub xMenuNuevoProducto_click";
RDebugUtils.currentLine=851969;
 //BA.debugLineNum = 851969;BA.debugLine="StartActivity(\"AltaProducto\")";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)("AltaProducto"));
RDebugUtils.currentLine=851970;
 //BA.debugLineNum = 851970;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=851971;
 //BA.debugLineNum = 851971;BA.debugLine="End Sub";
return "";
}
public static String  _xmenusincronizardatos_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "xmenusincronizardatos_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "xmenusincronizardatos_click", null));}
RDebugUtils.currentLine=786432;
 //BA.debugLineNum = 786432;BA.debugLine="Sub xMenuSincronizarDatos_Click";
RDebugUtils.currentLine=786433;
 //BA.debugLineNum = 786433;BA.debugLine="ProgressDialogShow(\"Descargando datos...\")";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,BA.ObjectToCharSequence("Descargando datos..."));
RDebugUtils.currentLine=786435;
 //BA.debugLineNum = 786435;BA.debugLine="webservice.Download(\"https://elmundodelasplantas.";
mostCurrent._webservice._download("https://elmundodelasplantas.000webhostapp.com/consultarTodos.php");
RDebugUtils.currentLine=786436;
 //BA.debugLineNum = 786436;BA.debugLine="End Sub";
return "";
}
}