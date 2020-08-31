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
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.main");
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

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
vis = vis | (edicion.mostCurrent != null);
vis = vis | (altaproducto.mostCurrent != null);
return vis;}
public static String  _activity_create(boolean _firsttime) throws Exception{
String _ruta = "";
 //BA.debugLineNum = 43;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 44;BA.debugLine="Activity.LoadLayout(\"PantallaPrincipal\")";
mostCurrent._activity.LoadLayout("PantallaPrincipal",mostCurrent.activityBA);
 //BA.debugLineNum = 45;BA.debugLine="webservice.Initialize(\"webservice\", Me)";
mostCurrent._webservice._initialize(processBA,"webservice",main.getObject());
 //BA.debugLineNum = 46;BA.debugLine="lvNombre.TwoLinesLayout.Label.TextSize = 10";
mostCurrent._lvnombre.getTwoLinesLayout().Label.setTextSize((float) (10));
 //BA.debugLineNum = 47;BA.debugLine="lvNombre.TwoLinesLayout.ItemHeight = 80dip";
mostCurrent._lvnombre.getTwoLinesLayout().setItemHeight(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80)));
 //BA.debugLineNum = 49;BA.debugLine="lvNombre.TwoLinesLayout.Label.SetLayout(5dip,2dip";
mostCurrent._lvnombre.getTwoLinesLayout().Label.SetLayout(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (370)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 50;BA.debugLine="lvNombre.TwoLinesLayout.SecondLabel.SetLayout(5di";
mostCurrent._lvnombre.getTwoLinesLayout().SecondLabel.SetLayout(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (55)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
 //BA.debugLineNum = 51;BA.debugLine="lvNombre.TwoLinesLayout.Label.TextColor = Colors.";
mostCurrent._lvnombre.getTwoLinesLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 54;BA.debugLine="Activity.AddMenuItem(\"Sincronizar Datos\",\"xMenuSi";
mostCurrent._activity.AddMenuItem(BA.ObjectToCharSequence("Sincronizar Datos"),"xMenuSincronizarDatos");
 //BA.debugLineNum = 55;BA.debugLine="Activity.AddMenuItem(\"Nuevo Producto\",\"xMenuNuevo";
mostCurrent._activity.AddMenuItem(BA.ObjectToCharSequence("Nuevo Producto"),"xMenuNuevoProducto");
 //BA.debugLineNum = 56;BA.debugLine="Activity.AddMenuItem(\"Descargar DB\",\"xMenuDescarg";
mostCurrent._activity.AddMenuItem(BA.ObjectToCharSequence("Descargar DB"),"xMenuDescargarDB");
 //BA.debugLineNum = 58;BA.debugLine="Dim ruta As String";
_ruta = "";
 //BA.debugLineNum = 62;BA.debugLine="ruta = File.DirInternal";
_ruta = anywheresoftware.b4a.keywords.Common.File.getDirInternal();
 //BA.debugLineNum = 64;BA.debugLine="If File.Exists(ruta,\"basedatos.db\")=False Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(_ruta,"basedatos.db")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 65;BA.debugLine="File.Copy(File.DirAssets,\"basedatos.db\",ruta,\"ba";
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"basedatos.db",_ruta,"basedatos.db");
 };
 //BA.debugLineNum = 67;BA.debugLine="SQL1.Initialize(ruta,\"basedatos.db\",True)";
_sql1.Initialize(_ruta,"basedatos.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 68;BA.debugLine="SetDivider(lvNombre, Colors.LightGray,15)";
_setdivider(mostCurrent._lvnombre,anywheresoftware.b4a.keywords.Common.Colors.LightGray,(int) (15));
 //BA.debugLineNum = 71;BA.debugLine="ColorBusqueda.Initialize2(Colors.RGB(248,248,255)";
mostCurrent._colorbusqueda.Initialize2(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (248),(int) (248),(int) (255)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (80),(int) (80),(int) (80)));
 //BA.debugLineNum = 72;BA.debugLine="ColorLista.Initialize(Colors.RGB(248,248,255), 5d";
mostCurrent._colorlista.Initialize(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (248),(int) (248),(int) (255)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)));
 //BA.debugLineNum = 74;BA.debugLine="ColorFondo.Initialize(Colors.RGB(248,248,255), 5d";
mostCurrent._colorfondo.Initialize(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (248),(int) (248),(int) (255)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)));
 //BA.debugLineNum = 75;BA.debugLine="txtBuscar.Background=ColorBusqueda";
mostCurrent._txtbuscar.setBackground((android.graphics.drawable.Drawable)(mostCurrent._colorbusqueda.getObject()));
 //BA.debugLineNum = 76;BA.debugLine="lvNombre.Background = ColorLista";
mostCurrent._lvnombre.setBackground((android.graphics.drawable.Drawable)(mostCurrent._colorlista.getObject()));
 //BA.debugLineNum = 77;BA.debugLine="Activity.Background = ColorFondo";
mostCurrent._activity.setBackground((android.graphics.drawable.Drawable)(mostCurrent._colorfondo.getObject()));
 //BA.debugLineNum = 78;BA.debugLine="xMenuSincronizarDatos_Click";
_xmenusincronizardatos_click();
 //BA.debugLineNum = 79;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
int _mensaje = 0;
 //BA.debugLineNum = 200;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 201;BA.debugLine="Dim mensaje As Int";
_mensaje = 0;
 //BA.debugLineNum = 202;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 203;BA.debugLine="mensaje = Msgbox2 (\"¿Cerrar el programa?\",\"\",\"Si";
_mensaje = anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToCharSequence("¿Cerrar el programa?"),BA.ObjectToCharSequence(""),"Si","","No",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA);
 //BA.debugLineNum = 204;BA.debugLine="If mensaje = -1 Then";
if (_mensaje==-1) { 
 //BA.debugLineNum = 205;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 }else {
 //BA.debugLineNum = 207;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 };
 //BA.debugLineNum = 210;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 116;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 118;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 112;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 114;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 29;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 31;BA.debugLine="Private txtBuscar As EditText";
mostCurrent._txtbuscar = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private lvNombre As ListView";
mostCurrent._lvnombre = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Dim webservice As HttpJob";
mostCurrent._webservice = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 34;BA.debugLine="Dim json As JSONParser";
mostCurrent._json = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 37;BA.debugLine="Dim registro As Cursor";
mostCurrent._registro = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Dim ColorFondo, ColorBusqueda, ColorLista As Colo";
mostCurrent._colorfondo = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
mostCurrent._colorbusqueda = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
mostCurrent._colorlista = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 39;BA.debugLine="Dim EsDescargaExcel As Boolean";
_esdescargaexcel = false;
 //BA.debugLineNum = 40;BA.debugLine="EsDescargaExcel = False";
_esdescargaexcel = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 41;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(anywheresoftware.b4a.samples.httputils2.httpjob _job) throws Exception{
anywheresoftware.b4a.objects.collections.Map _map1 = null;
anywheresoftware.b4a.objects.collections.List _menuitems = null;
anywheresoftware.b4a.objects.collections.Map _m = null;
String _contienejob = "";
String _registroexcel = "";
anywheresoftware.b4a.objects.collections.List _listaexcel = null;
int _i = 0;
 //BA.debugLineNum = 120;BA.debugLine="Sub JobDone (Job As HttpJob)";
 //BA.debugLineNum = 121;BA.debugLine="Dim Map1 As Map";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 122;BA.debugLine="Dim MenuItems As List";
_menuitems = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 123;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 124;BA.debugLine="Dim contieneJob As String";
_contienejob = "";
 //BA.debugLineNum = 125;BA.debugLine="Dim registroExcel As String";
_registroexcel = "";
 //BA.debugLineNum = 126;BA.debugLine="Dim listaExcel As List";
_listaexcel = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 130;BA.debugLine="If Job.Success = True Then";
if (_job._success==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 131;BA.debugLine="json.Initialize(Job.GetString)";
mostCurrent._json.Initialize(_job._getstring());
 //BA.debugLineNum = 132;BA.debugLine="contieneJob = Job.GetString";
_contienejob = _job._getstring();
 //BA.debugLineNum = 133;BA.debugLine="If contieneJob = \"[]\" Then";
if ((_contienejob).equals("[]")) { 
 //BA.debugLineNum = 134;BA.debugLine="Msgbox (\"no hay datos\", \"\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("no hay datos"),BA.ObjectToCharSequence(""),mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 136;BA.debugLine="If EsDescargaExcel Then	'ES DESCARGA DE DATOS P";
if (_esdescargaexcel) { 
 //BA.debugLineNum = 143;BA.debugLine="listaExcel.Initialize";
_listaexcel.Initialize();
 //BA.debugLineNum = 144;BA.debugLine="Map1 = json.NextObject";
_map1 = mostCurrent._json.NextObject();
 //BA.debugLineNum = 145;BA.debugLine="MenuItems = Map1.Get(\"productos\")";
_menuitems.setObject((java.util.List)(_map1.Get((Object)("productos"))));
 //BA.debugLineNum = 146;BA.debugLine="ToastMessageShow (File.DirDefaultExternal, Tru";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal()),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 147;BA.debugLine="Log(File.DirDefaultExternal)";
anywheresoftware.b4a.keywords.Common.LogImpl("0589851",anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal(),0);
 //BA.debugLineNum = 148;BA.debugLine="For i=0 To MenuItems.Size -1";
{
final int step19 = 1;
final int limit19 = (int) (_menuitems.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit19 ;_i = _i + step19 ) {
 //BA.debugLineNum = 149;BA.debugLine="m = MenuItems.Get(i)";
_m.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_menuitems.Get(_i)));
 //BA.debugLineNum = 150;BA.debugLine="registroExcel = m.Get(\"id\") & \";\" & m.Get(\"no";
_registroexcel = BA.ObjectToString(_m.Get((Object)("id")))+";"+BA.ObjectToString(_m.Get((Object)("nombre")))+";"+BA.ObjectToString(_m.Get((Object)("modelo")))+";"+BA.ObjectToString(_m.Get((Object)("tipo")))+";"+BA.ObjectToString(_m.Get((Object)("precio")));
 //BA.debugLineNum = 151;BA.debugLine="File.WriteString(File.DirRootExternal,\"baseda";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"basedatos.csv",_registroexcel);
 }
};
 //BA.debugLineNum = 158;BA.debugLine="EsDescargaExcel=False";
_esdescargaexcel = anywheresoftware.b4a.keywords.Common.False;
 }else {
 //BA.debugLineNum = 163;BA.debugLine="SQL1.ExecNonQuery(\"DELETE FROM productos\")";
_sql1.ExecNonQuery("DELETE FROM productos");
 //BA.debugLineNum = 164;BA.debugLine="SQL1.ExecNonQuery(\"VACUUM\")";
_sql1.ExecNonQuery("VACUUM");
 //BA.debugLineNum = 165;BA.debugLine="Map1 = json.NextObject";
_map1 = mostCurrent._json.NextObject();
 //BA.debugLineNum = 166;BA.debugLine="MenuItems = Map1.Get(\"productos\")";
_menuitems.setObject((java.util.List)(_map1.Get((Object)("productos"))));
 //BA.debugLineNum = 167;BA.debugLine="For i=0 To MenuItems.Size -1";
{
final int step30 = 1;
final int limit30 = (int) (_menuitems.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit30 ;_i = _i + step30 ) {
 //BA.debugLineNum = 170;BA.debugLine="m = MenuItems.Get(i)";
_m.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_menuitems.Get(_i)));
 //BA.debugLineNum = 171;BA.debugLine="SQL1.ExecNonQuery(\"INSERT INTO productos (id,";
_sql1.ExecNonQuery("INSERT INTO productos (id,nombre,modelo,tipo,precio,categoria) VALUES('"+BA.ObjectToString(_m.Get((Object)("id")))+"','"+BA.ObjectToString(_m.Get((Object)("nombre")))+"','"+BA.ObjectToString(_m.Get((Object)("modelo")))+"','"+BA.ObjectToString(_m.Get((Object)("tipo")))+"','"+BA.ObjectToString(_m.Get((Object)("precio")))+"','"+BA.ObjectToString(_m.Get((Object)("categoria")))+"')");
 }
};
 };
 };
 }else {
 //BA.debugLineNum = 176;BA.debugLine="Msgbox (\"error en el jobdone: \" & Job.ErrorMessa";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("error en el jobdone: "+_job._errormessage),BA.ObjectToCharSequence("EMDLP"),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 178;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 179;BA.debugLine="ToastMessageShow (\"Descarga finalizada\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Descarga finalizada"),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 181;BA.debugLine="End Sub";
return "";
}
public static String  _lvnombre_itemlongclick(int _position,Object _value) throws Exception{
int _item = 0;
anywheresoftware.b4a.sql.SQL.CursorWrapper _reg = null;
 //BA.debugLineNum = 183;BA.debugLine="Sub lvNombre_ItemLongClick (Position As Int, Value";
 //BA.debugLineNum = 184;BA.debugLine="Dim item As Int";
_item = 0;
 //BA.debugLineNum = 185;BA.debugLine="item = Value";
_item = (int)(BA.ObjectToNumber(_value));
 //BA.debugLineNum = 186;BA.debugLine="Dim reg As Cursor";
_reg = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 187;BA.debugLine="reg = SQL1.ExecQuery(\"select * from productos whe";
_reg.setObject((android.database.Cursor)(_sql1.ExecQuery("select * from productos where id = '"+BA.NumberToString(_item)+"'")));
 //BA.debugLineNum = 189;BA.debugLine="reg.Position = 0";
_reg.setPosition((int) (0));
 //BA.debugLineNum = 190;BA.debugLine="idProducto = reg.GetInt(\"id\")";
_idproducto = _reg.GetInt("id");
 //BA.debugLineNum = 191;BA.debugLine="nombreProducto = reg.GetString(\"nombre\")";
_nombreproducto = _reg.GetString("nombre");
 //BA.debugLineNum = 192;BA.debugLine="modeloProducto = reg.GetString(\"modelo\")";
_modeloproducto = _reg.GetString("modelo");
 //BA.debugLineNum = 193;BA.debugLine="tipoProducto = reg.Getstring(\"tipo\")";
_tipoproducto = _reg.GetString("tipo");
 //BA.debugLineNum = 194;BA.debugLine="precioProducto = reg.Getstring(\"precio\")";
_precioproducto = _reg.GetString("precio");
 //BA.debugLineNum = 196;BA.debugLine="StartActivity(\"Edicion\")";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)("Edicion"));
 //BA.debugLineNum = 197;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 198;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        anywheresoftware.b4a.samples.httputils2.httputils2service._process_globals();
main._process_globals();
starter._process_globals();
edicion._process_globals();
altaproducto._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 19;BA.debugLine="Dim idProducto As Int";
_idproducto = 0;
 //BA.debugLineNum = 20;BA.debugLine="Dim nombreProducto As String";
_nombreproducto = "";
 //BA.debugLineNum = 21;BA.debugLine="Dim modeloProducto As String";
_modeloproducto = "";
 //BA.debugLineNum = 22;BA.debugLine="Dim tipoProducto As String";
_tipoproducto = "";
 //BA.debugLineNum = 23;BA.debugLine="Dim precioProducto As String";
_precioproducto = "";
 //BA.debugLineNum = 26;BA.debugLine="Dim SQL1 As SQL";
_sql1 = new anywheresoftware.b4a.sql.SQL();
 //BA.debugLineNum = 27;BA.debugLine="End Sub";
return "";
}
public static String  _setdivider(anywheresoftware.b4a.objects.ListViewWrapper _lv,int _color,int _height) throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
 //BA.debugLineNum = 81;BA.debugLine="Sub SetDivider(lv As ListView, Color As Int, Heigh";
 //BA.debugLineNum = 82;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 83;BA.debugLine="r.Target = lv";
_r.Target = (Object)(_lv.getObject());
 //BA.debugLineNum = 84;BA.debugLine="Dim CD As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 85;BA.debugLine="CD.Initialize(Color, 0)";
_cd.Initialize(_color,(int) (0));
 //BA.debugLineNum = 86;BA.debugLine="r.RunMethod4(\"setDivider\", Array As Object(CD), A";
_r.RunMethod4("setDivider",new Object[]{(Object)(_cd.getObject())},new String[]{"android.graphics.drawable.Drawable"});
 //BA.debugLineNum = 87;BA.debugLine="r.RunMethod2(\"setDividerHeight\", Height, \"java.la";
_r.RunMethod2("setDividerHeight",BA.NumberToString(_height),"java.lang.int");
 //BA.debugLineNum = 88;BA.debugLine="End Sub";
return "";
}
public static String  _txtbuscar_textchanged(String _old,String _new) throws Exception{
anywheresoftware.b4a.objects.drawable.ColorDrawable _color1 = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _color2 = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _color3 = null;
float _tamanioletra = 0f;
int _i = 0;
 //BA.debugLineNum = 212;BA.debugLine="Sub txtBuscar_TextChanged (Old As String, New As S";
 //BA.debugLineNum = 213;BA.debugLine="Dim Color1, Color2,Color3 As ColorDrawable";
_color1 = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
_color2 = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
_color3 = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 214;BA.debugLine="Dim TamanioLetra As Float";
_tamanioletra = 0f;
 //BA.debugLineNum = 216;BA.debugLine="TamanioLetra=16";
_tamanioletra = (float) (16);
 //BA.debugLineNum = 218;BA.debugLine="lvNombre.Clear";
mostCurrent._lvnombre.Clear();
 //BA.debugLineNum = 219;BA.debugLine="Color1.Initialize(Colors.Red,5dip)";
_color1.Initialize(anywheresoftware.b4a.keywords.Common.Colors.Red,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)));
 //BA.debugLineNum = 220;BA.debugLine="Color2.Initialize(Colors.Blue,5dip)";
_color2.Initialize(anywheresoftware.b4a.keywords.Common.Colors.Blue,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)));
 //BA.debugLineNum = 221;BA.debugLine="Color3.Initialize(Colors.Cyan, 5dip)";
_color3.Initialize(anywheresoftware.b4a.keywords.Common.Colors.Cyan,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)));
 //BA.debugLineNum = 223;BA.debugLine="registro = SQL1.ExecQuery(\"select * from (select";
mostCurrent._registro.setObject((android.database.Cursor)(_sql1.ExecQuery("select * from (select * from productos where nombre like '%"+mostCurrent._txtbuscar.getText()+"%' UNION select * from productos where modelo like '%"+mostCurrent._txtbuscar.getText()+"%') order by nombre, modelo, tipo, precio asc")));
 //BA.debugLineNum = 224;BA.debugLine="lvNombre.TwoLinesLayout.Label.Gravity = Gravity.C";
mostCurrent._lvnombre.getTwoLinesLayout().Label.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL);
 //BA.debugLineNum = 225;BA.debugLine="lvNombre.TwoLinesLayout.Label.TextSize = TamanioL";
mostCurrent._lvnombre.getTwoLinesLayout().Label.setTextSize(_tamanioletra);
 //BA.debugLineNum = 226;BA.debugLine="lvNombre.TwoLinesLayout.SecondLabel.TextSize=20";
mostCurrent._lvnombre.getTwoLinesLayout().SecondLabel.setTextSize((float) (20));
 //BA.debugLineNum = 227;BA.debugLine="lvNombre.TwoLinesLayout.SecondLabel.TextColor=Col";
mostCurrent._lvnombre.getTwoLinesLayout().SecondLabel.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (0),(int) (255)));
 //BA.debugLineNum = 230;BA.debugLine="If registro.RowCount > 0 Then";
if (mostCurrent._registro.getRowCount()>0) { 
 //BA.debugLineNum = 231;BA.debugLine="For i = 0 To registro.RowCount - 1";
{
final int step14 = 1;
final int limit14 = (int) (mostCurrent._registro.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit14 ;_i = _i + step14 ) {
 //BA.debugLineNum = 232;BA.debugLine="registro.Position = i";
mostCurrent._registro.setPosition(_i);
 //BA.debugLineNum = 234;BA.debugLine="lvNombre.AddTwoLines2(registro.GetString(\"nombr";
mostCurrent._lvnombre.AddTwoLines2(BA.ObjectToCharSequence(mostCurrent._registro.GetString("nombre")+" | "+mostCurrent._registro.GetString("modelo")+" | "+mostCurrent._registro.GetString("tipo")),BA.ObjectToCharSequence(mostCurrent._registro.GetString("precio")),(Object)(mostCurrent._registro.GetInt("id")));
 }
};
 };
 //BA.debugLineNum = 248;BA.debugLine="End Sub";
return "";
}
public static String  _xmenudescargardb_click() throws Exception{
 //BA.debugLineNum = 103;BA.debugLine="Sub xMenuDescargarDB_click";
 //BA.debugLineNum = 104;BA.debugLine="ProgressDialogShow(\"Generando archivo...\")";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,BA.ObjectToCharSequence("Generando archivo..."));
 //BA.debugLineNum = 106;BA.debugLine="EsDescargaExcel=True";
_esdescargaexcel = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 107;BA.debugLine="webservice.Download(\"https://elmundodelasplantas.";
mostCurrent._webservice._download("https://elmundodelasplantas.000webhostapp.com/consultarTodos.php");
 //BA.debugLineNum = 110;BA.debugLine="End Sub";
return "";
}
public static String  _xmenunuevoproducto_click() throws Exception{
 //BA.debugLineNum = 98;BA.debugLine="Sub xMenuNuevoProducto_click";
 //BA.debugLineNum = 99;BA.debugLine="StartActivity(\"AltaProducto\")";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)("AltaProducto"));
 //BA.debugLineNum = 100;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 101;BA.debugLine="End Sub";
return "";
}
public static String  _xmenusincronizardatos_click() throws Exception{
 //BA.debugLineNum = 90;BA.debugLine="Sub xMenuSincronizarDatos_Click";
 //BA.debugLineNum = 91;BA.debugLine="ProgressDialogShow(\"Descargando datos, por favor";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,BA.ObjectToCharSequence("Descargando datos, por favor espere"));
 //BA.debugLineNum = 95;BA.debugLine="webservice.Download(\"https://elmundodelasplantas.";
mostCurrent._webservice._download("https://elmundodelasplantas.000webhostapp.com/consultarTodos.php");
 //BA.debugLineNum = 96;BA.debugLine="End Sub";
return "";
}
}
