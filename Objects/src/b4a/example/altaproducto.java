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

public class altaproducto extends Activity implements B4AActivity{
	public static altaproducto mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = true;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.altaproducto");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (altaproducto).");
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
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.altaproducto");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.altaproducto", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (altaproducto) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (altaproducto) Resume **");
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
		return altaproducto.class;
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
        BA.LogInfo("** Activity (altaproducto) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            altaproducto mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (altaproducto) Resume **");
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
public anywheresoftware.b4a.objects.ButtonWrapper _btnvolver = null;
public anywheresoftware.b4a.objects.ButtonWrapper _buttonguardar = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etnombre = null;
public anywheresoftware.b4a.objects.EditTextWrapper _ettipo = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etprecio = null;
public anywheresoftware.b4a.samples.httputils2.httpjob _webservice3 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etmodelo = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public b4a.example.main _main = null;
public b4a.example.starter _starter = null;
public b4a.example.edicion _edicion = null;

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 27;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 29;BA.debugLine="Activity.LoadLayout(\"PantallaAlta\")";
mostCurrent._activity.LoadLayout("PantallaAlta",mostCurrent.activityBA);
 //BA.debugLineNum = 30;BA.debugLine="webservice3.Initialize(\"webservice2\", Me)";
mostCurrent._webservice3._initialize(processBA,"webservice2",altaproducto.getObject());
 //BA.debugLineNum = 31;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 37;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 39;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 33;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 35;BA.debugLine="End Sub";
return "";
}
public static String  _btnguardar_click() throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _cur = null;
int _maxid = 0;
String _nombresinespacio = "";
String _modelosinespacio = "";
String _tiposinespacio = "";
String _preciosinespacio = "";
String _valotros = "";
 //BA.debugLineNum = 47;BA.debugLine="Sub btnGuardar_Click";
 //BA.debugLineNum = 48;BA.debugLine="Dim cur As Cursor";
_cur = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 49;BA.debugLine="Dim maxId As Int";
_maxid = 0;
 //BA.debugLineNum = 50;BA.debugLine="Dim nombreSinEspacio As String";
_nombresinespacio = "";
 //BA.debugLineNum = 51;BA.debugLine="Dim modeloSinEspacio As String";
_modelosinespacio = "";
 //BA.debugLineNum = 52;BA.debugLine="Dim tipoSinEspacio As String";
_tiposinespacio = "";
 //BA.debugLineNum = 53;BA.debugLine="Dim precioSinEspacio As String";
_preciosinespacio = "";
 //BA.debugLineNum = 54;BA.debugLine="Dim valOtros As String";
_valotros = "";
 //BA.debugLineNum = 55;BA.debugLine="ProgressDialogShow(\"Guardando datos...\")";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,BA.ObjectToCharSequence("Guardando datos..."));
 //BA.debugLineNum = 56;BA.debugLine="cur = Main.SQL1.ExecQuery(\"SELECT MAX(id) as mayo";
_cur.setObject((android.database.Cursor)(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("SELECT MAX(id) as mayor FROM productos")));
 //BA.debugLineNum = 57;BA.debugLine="cur.Position = 0";
_cur.setPosition((int) (0));
 //BA.debugLineNum = 58;BA.debugLine="maxId = cur.GetInt(\"mayor\") + 1";
_maxid = (int) (_cur.GetInt("mayor")+1);
 //BA.debugLineNum = 59;BA.debugLine="nombreSinEspacio = etNombre.Text.Replace(\" \",\"%20";
_nombresinespacio = mostCurrent._etnombre.getText().replace(" ","%20");
 //BA.debugLineNum = 60;BA.debugLine="modeloSinEspacio = etModelo.Text.Replace(\" \",\"%20";
_modelosinespacio = mostCurrent._etmodelo.getText().replace(" ","%20");
 //BA.debugLineNum = 61;BA.debugLine="tipoSinEspacio = etTipo.Text.Replace(\" \",\"%20\")";
_tiposinespacio = mostCurrent._ettipo.getText().replace(" ","%20");
 //BA.debugLineNum = 62;BA.debugLine="precioSinEspacio = etPrecio.Text.Replace(\" \",\"%20";
_preciosinespacio = mostCurrent._etprecio.getText().replace(" ","%20");
 //BA.debugLineNum = 63;BA.debugLine="valOtros = \"otros\"";
_valotros = "otros";
 //BA.debugLineNum = 65;BA.debugLine="If etNombre.Text = \"\" Then";
if ((mostCurrent._etnombre.getText()).equals("")) { 
 //BA.debugLineNum = 66;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 67;BA.debugLine="ToastMessageShow(\"El nombre no puede estar vacio";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("El nombre no puede estar vacio"),anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 69;BA.debugLine="Main.SQL1.BeginTransaction";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 76;BA.debugLine="Main.SQL1.ExecNonQuery(\"INSERT INTO productos (i";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery("INSERT INTO productos (id, nombre, modelo, tipo, precio, categoria) VALUES ("+BA.NumberToString(_maxid)+", '"+mostCurrent._etnombre.getText()+"','"+mostCurrent._etmodelo.getText()+"','"+mostCurrent._ettipo.getText()+"','"+mostCurrent._etprecio.getText()+"','otros')");
 //BA.debugLineNum = 79;BA.debugLine="Log(\"https://elmundodelasplantas.000webhostapp.c";
anywheresoftware.b4a.keywords.Common.LogImpl("02228256","https://elmundodelasplantas.000webhostapp.com/insertar.php?id="+BA.NumberToString(_maxid)+"&nombre="+_nombresinespacio+"&modelo="+_modelosinespacio+"&tipo="+_tiposinespacio+"&precio="+_preciosinespacio+"&categoria="+_valotros,0);
 //BA.debugLineNum = 82;BA.debugLine="webservice3.Download(\"https://elmundodelasplanta";
mostCurrent._webservice3._download("https://elmundodelasplantas.000webhostapp.com/insertar.php?id="+BA.NumberToString(_maxid)+"&nombre="+_nombresinespacio+"&modelo="+_modelosinespacio+"&tipo="+_tiposinespacio+"&precio="+_preciosinespacio+"&categoria="+_valotros);
 };
 //BA.debugLineNum = 84;BA.debugLine="End Sub";
return "";
}
public static String  _btnvolver_click() throws Exception{
 //BA.debugLineNum = 42;BA.debugLine="Sub btnVolver_Click";
 //BA.debugLineNum = 43;BA.debugLine="StartActivity(\"Main\")";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)("Main"));
 //BA.debugLineNum = 44;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 45;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 16;BA.debugLine="Private btnVolver As Button";
mostCurrent._btnvolver = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Private ButtonGuardar As Button";
mostCurrent._buttonguardar = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private etNombre As EditText";
mostCurrent._etnombre = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private etTipo As EditText";
mostCurrent._ettipo = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private etPrecio As EditText";
mostCurrent._etprecio = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Dim webservice3 As HttpJob";
mostCurrent._webservice3 = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 24;BA.debugLine="Private etModelo As EditText";
mostCurrent._etmodelo = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 25;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(anywheresoftware.b4a.samples.httputils2.httpjob _job) throws Exception{
String _result = "";
 //BA.debugLineNum = 86;BA.debugLine="Sub JobDone(Job As HttpJob)";
 //BA.debugLineNum = 87;BA.debugLine="Dim result As String";
_result = "";
 //BA.debugLineNum = 88;BA.debugLine="result = Job.GetString";
_result = _job._getstring();
 //BA.debugLineNum = 89;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 90;BA.debugLine="If result = \"ok\" Then";
if ((_result).equals("ok")) { 
 //BA.debugLineNum = 91;BA.debugLine="Main.SQL1.TransactionSuccessful";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 92;BA.debugLine="Main.SQL1.EndTransaction";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 93;BA.debugLine="Msgbox (\"El registro se guardo correctamente\",\"\"";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("El registro se guardo correctamente"),BA.ObjectToCharSequence(""),mostCurrent.activityBA);
 //BA.debugLineNum = 94;BA.debugLine="etNombre.Text = \"\"";
mostCurrent._etnombre.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 95;BA.debugLine="etModelo.Text = \"\"";
mostCurrent._etmodelo.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 96;BA.debugLine="etTipo.Text = \"\"";
mostCurrent._ettipo.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 97;BA.debugLine="etPrecio.Text = \"\"";
mostCurrent._etprecio.setText(BA.ObjectToCharSequence(""));
 }else {
 //BA.debugLineNum = 100;BA.debugLine="Msgbox (\"Se encontro un error al guardar los dat";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Se encontro un error al guardar los datos: "+_result),BA.ObjectToCharSequence(". Recuerde Sincronizar los datos"),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 103;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
}
