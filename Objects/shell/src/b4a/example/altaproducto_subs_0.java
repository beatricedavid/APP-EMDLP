package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class altaproducto_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (altaproducto) ","altaproducto",3,altaproducto.mostCurrent.activityBA,altaproducto.mostCurrent,27);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.altaproducto.remoteMe.runUserSub(false, "altaproducto","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 27;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 29;BA.debugLine="Activity.LoadLayout(\"PantallaAlta\")";
Debug.ShouldStop(268435456);
altaproducto.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("PantallaAlta")),altaproducto.mostCurrent.activityBA);
 BA.debugLineNum = 30;BA.debugLine="webservice3.Initialize(\"webservice2\", Me)";
Debug.ShouldStop(536870912);
altaproducto.mostCurrent._webservice3.runVoidMethod ("_initialize",altaproducto.processBA,(Object)(BA.ObjectToString("webservice2")),(Object)(altaproducto.getObject()));
 BA.debugLineNum = 31;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_pause(RemoteObject _userclosed) throws Exception{
try {
		Debug.PushSubsStack("Activity_Pause (altaproducto) ","altaproducto",3,altaproducto.mostCurrent.activityBA,altaproducto.mostCurrent,37);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.altaproducto.remoteMe.runUserSub(false, "altaproducto","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 37;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(16);
 BA.debugLineNum = 39;BA.debugLine="End Sub";
Debug.ShouldStop(64);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (altaproducto) ","altaproducto",3,altaproducto.mostCurrent.activityBA,altaproducto.mostCurrent,33);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.altaproducto.remoteMe.runUserSub(false, "altaproducto","activity_resume");}
 BA.debugLineNum = 33;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(1);
 BA.debugLineNum = 35;BA.debugLine="End Sub";
Debug.ShouldStop(4);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnguardar_click() throws Exception{
try {
		Debug.PushSubsStack("btnGuardar_Click (altaproducto) ","altaproducto",3,altaproducto.mostCurrent.activityBA,altaproducto.mostCurrent,47);
if (RapidSub.canDelegate("btnguardar_click")) { return b4a.example.altaproducto.remoteMe.runUserSub(false, "altaproducto","btnguardar_click");}
RemoteObject _cur = RemoteObject.declareNull("anywheresoftware.b4a.sql.SQL.CursorWrapper");
RemoteObject _maxid = RemoteObject.createImmutable(0);
RemoteObject _nombresinespacio = RemoteObject.createImmutable("");
RemoteObject _modelosinespacio = RemoteObject.createImmutable("");
RemoteObject _tiposinespacio = RemoteObject.createImmutable("");
RemoteObject _preciosinespacio = RemoteObject.createImmutable("");
RemoteObject _valotros = RemoteObject.createImmutable("");
 BA.debugLineNum = 47;BA.debugLine="Sub btnGuardar_Click";
Debug.ShouldStop(16384);
 BA.debugLineNum = 48;BA.debugLine="Dim cur As Cursor";
Debug.ShouldStop(32768);
_cur = RemoteObject.createNew ("anywheresoftware.b4a.sql.SQL.CursorWrapper");Debug.locals.put("cur", _cur);
 BA.debugLineNum = 49;BA.debugLine="Dim maxId As Int";
Debug.ShouldStop(65536);
_maxid = RemoteObject.createImmutable(0);Debug.locals.put("maxId", _maxid);
 BA.debugLineNum = 50;BA.debugLine="Dim nombreSinEspacio As String";
Debug.ShouldStop(131072);
_nombresinespacio = RemoteObject.createImmutable("");Debug.locals.put("nombreSinEspacio", _nombresinespacio);
 BA.debugLineNum = 51;BA.debugLine="Dim modeloSinEspacio As String";
Debug.ShouldStop(262144);
_modelosinespacio = RemoteObject.createImmutable("");Debug.locals.put("modeloSinEspacio", _modelosinespacio);
 BA.debugLineNum = 52;BA.debugLine="Dim tipoSinEspacio As String";
Debug.ShouldStop(524288);
_tiposinespacio = RemoteObject.createImmutable("");Debug.locals.put("tipoSinEspacio", _tiposinespacio);
 BA.debugLineNum = 53;BA.debugLine="Dim precioSinEspacio As String";
Debug.ShouldStop(1048576);
_preciosinespacio = RemoteObject.createImmutable("");Debug.locals.put("precioSinEspacio", _preciosinespacio);
 BA.debugLineNum = 54;BA.debugLine="Dim valOtros As String";
Debug.ShouldStop(2097152);
_valotros = RemoteObject.createImmutable("");Debug.locals.put("valOtros", _valotros);
 BA.debugLineNum = 55;BA.debugLine="ProgressDialogShow(\"Guardando datos...\")";
Debug.ShouldStop(4194304);
altaproducto.mostCurrent.__c.runVoidMethod ("ProgressDialogShow",altaproducto.mostCurrent.activityBA,(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Guardando datos..."))));
 BA.debugLineNum = 56;BA.debugLine="cur = Main.SQL1.ExecQuery(\"SELECT MAX(id) as mayo";
Debug.ShouldStop(8388608);
_cur.setObject(altaproducto.mostCurrent._main._sql1 /*RemoteObject*/ .runMethod(false,"ExecQuery",(Object)(RemoteObject.createImmutable("SELECT MAX(id) as mayor FROM productos"))));
 BA.debugLineNum = 57;BA.debugLine="cur.Position = 0";
Debug.ShouldStop(16777216);
_cur.runMethod(true,"setPosition",BA.numberCast(int.class, 0));
 BA.debugLineNum = 58;BA.debugLine="maxId = cur.GetInt(\"mayor\") + 1";
Debug.ShouldStop(33554432);
_maxid = RemoteObject.solve(new RemoteObject[] {_cur.runMethod(true,"GetInt",(Object)(RemoteObject.createImmutable("mayor"))),RemoteObject.createImmutable(1)}, "+",1, 1);Debug.locals.put("maxId", _maxid);
 BA.debugLineNum = 59;BA.debugLine="nombreSinEspacio = etNombre.Text.Replace(\" \",\"%20";
Debug.ShouldStop(67108864);
_nombresinespacio = altaproducto.mostCurrent._etnombre.runMethod(true,"getText").runMethod(true,"replace",(Object)(BA.ObjectToString(" ")),(Object)(RemoteObject.createImmutable("%20")));Debug.locals.put("nombreSinEspacio", _nombresinespacio);
 BA.debugLineNum = 60;BA.debugLine="modeloSinEspacio = etModelo.Text.Replace(\" \",\"%20";
Debug.ShouldStop(134217728);
_modelosinespacio = altaproducto.mostCurrent._etmodelo.runMethod(true,"getText").runMethod(true,"replace",(Object)(BA.ObjectToString(" ")),(Object)(RemoteObject.createImmutable("%20")));Debug.locals.put("modeloSinEspacio", _modelosinespacio);
 BA.debugLineNum = 61;BA.debugLine="tipoSinEspacio = etTipo.Text.Replace(\" \",\"%20\")";
Debug.ShouldStop(268435456);
_tiposinespacio = altaproducto.mostCurrent._ettipo.runMethod(true,"getText").runMethod(true,"replace",(Object)(BA.ObjectToString(" ")),(Object)(RemoteObject.createImmutable("%20")));Debug.locals.put("tipoSinEspacio", _tiposinespacio);
 BA.debugLineNum = 62;BA.debugLine="precioSinEspacio = etPrecio.Text.Replace(\" \",\"%20";
Debug.ShouldStop(536870912);
_preciosinespacio = altaproducto.mostCurrent._etprecio.runMethod(true,"getText").runMethod(true,"replace",(Object)(BA.ObjectToString(" ")),(Object)(RemoteObject.createImmutable("%20")));Debug.locals.put("precioSinEspacio", _preciosinespacio);
 BA.debugLineNum = 63;BA.debugLine="valOtros = \"otros\"";
Debug.ShouldStop(1073741824);
_valotros = BA.ObjectToString("otros");Debug.locals.put("valOtros", _valotros);
 BA.debugLineNum = 65;BA.debugLine="If etNombre.Text = \"\" Then";
Debug.ShouldStop(1);
if (RemoteObject.solveBoolean("=",altaproducto.mostCurrent._etnombre.runMethod(true,"getText"),BA.ObjectToString(""))) { 
 BA.debugLineNum = 66;BA.debugLine="ProgressDialogHide";
Debug.ShouldStop(2);
altaproducto.mostCurrent.__c.runVoidMethod ("ProgressDialogHide");
 BA.debugLineNum = 67;BA.debugLine="ToastMessageShow(\"El nombre no puede estar vacio";
Debug.ShouldStop(4);
altaproducto.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("El nombre no puede estar vacio")),(Object)(altaproducto.mostCurrent.__c.getField(true,"False")));
 }else {
 BA.debugLineNum = 69;BA.debugLine="Main.SQL1.BeginTransaction";
Debug.ShouldStop(16);
altaproducto.mostCurrent._main._sql1 /*RemoteObject*/ .runVoidMethod ("BeginTransaction");
 BA.debugLineNum = 76;BA.debugLine="Main.SQL1.ExecNonQuery(\"INSERT INTO productos (i";
Debug.ShouldStop(2048);
altaproducto.mostCurrent._main._sql1 /*RemoteObject*/ .runVoidMethod ("ExecNonQuery",(Object)(RemoteObject.concat(RemoteObject.createImmutable("INSERT INTO productos (id, nombre, modelo, tipo, precio, categoria) VALUES ("),_maxid,RemoteObject.createImmutable(", '"),altaproducto.mostCurrent._etnombre.runMethod(true,"getText"),RemoteObject.createImmutable("','"),altaproducto.mostCurrent._etmodelo.runMethod(true,"getText"),RemoteObject.createImmutable("','"),altaproducto.mostCurrent._ettipo.runMethod(true,"getText"),RemoteObject.createImmutable("','"),altaproducto.mostCurrent._etprecio.runMethod(true,"getText"),RemoteObject.createImmutable("','otros')"))));
 BA.debugLineNum = 79;BA.debugLine="Log(\"https://elmundodelasplantas.000webhostapp.c";
Debug.ShouldStop(16384);
altaproducto.mostCurrent.__c.runVoidMethod ("LogImpl","72228256",RemoteObject.concat(RemoteObject.createImmutable("https://elmundodelasplantas.000webhostapp.com/insertar.php?id="),_maxid,RemoteObject.createImmutable("&nombre="),_nombresinespacio,RemoteObject.createImmutable("&modelo="),_modelosinespacio,RemoteObject.createImmutable("&tipo="),_tiposinespacio,RemoteObject.createImmutable("&precio="),_preciosinespacio,RemoteObject.createImmutable("&categoria="),_valotros),0);
 BA.debugLineNum = 82;BA.debugLine="webservice3.Download(\"https://elmundodelasplanta";
Debug.ShouldStop(131072);
altaproducto.mostCurrent._webservice3.runVoidMethod ("_download",(Object)(RemoteObject.concat(RemoteObject.createImmutable("https://elmundodelasplantas.000webhostapp.com/insertar.php?id="),_maxid,RemoteObject.createImmutable("&nombre="),_nombresinespacio,RemoteObject.createImmutable("&modelo="),_modelosinespacio,RemoteObject.createImmutable("&tipo="),_tiposinespacio,RemoteObject.createImmutable("&precio="),_preciosinespacio,RemoteObject.createImmutable("&categoria="),_valotros)));
 };
 BA.debugLineNum = 84;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnvolver_click() throws Exception{
try {
		Debug.PushSubsStack("btnVolver_Click (altaproducto) ","altaproducto",3,altaproducto.mostCurrent.activityBA,altaproducto.mostCurrent,42);
if (RapidSub.canDelegate("btnvolver_click")) { return b4a.example.altaproducto.remoteMe.runUserSub(false, "altaproducto","btnvolver_click");}
 BA.debugLineNum = 42;BA.debugLine="Sub btnVolver_Click";
Debug.ShouldStop(512);
 BA.debugLineNum = 43;BA.debugLine="StartActivity(\"Main\")";
Debug.ShouldStop(1024);
altaproducto.mostCurrent.__c.runVoidMethod ("StartActivity",altaproducto.processBA,(Object)((RemoteObject.createImmutable("Main"))));
 BA.debugLineNum = 44;BA.debugLine="Activity.Finish";
Debug.ShouldStop(2048);
altaproducto.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 45;BA.debugLine="End Sub";
Debug.ShouldStop(4096);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 16;BA.debugLine="Private btnVolver As Button";
altaproducto.mostCurrent._btnvolver = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 17;BA.debugLine="Private ButtonGuardar As Button";
altaproducto.mostCurrent._buttonguardar = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 18;BA.debugLine="Private etNombre As EditText";
altaproducto.mostCurrent._etnombre = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 19;BA.debugLine="Private etTipo As EditText";
altaproducto.mostCurrent._ettipo = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 20;BA.debugLine="Private etPrecio As EditText";
altaproducto.mostCurrent._etprecio = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 23;BA.debugLine="Dim webservice3 As HttpJob";
altaproducto.mostCurrent._webservice3 = RemoteObject.createNew ("anywheresoftware.b4a.samples.httputils2.httpjob");
 //BA.debugLineNum = 24;BA.debugLine="Private etModelo As EditText";
altaproducto.mostCurrent._etmodelo = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 25;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _jobdone(RemoteObject _job) throws Exception{
try {
		Debug.PushSubsStack("JobDone (altaproducto) ","altaproducto",3,altaproducto.mostCurrent.activityBA,altaproducto.mostCurrent,86);
if (RapidSub.canDelegate("jobdone")) { return b4a.example.altaproducto.remoteMe.runUserSub(false, "altaproducto","jobdone", _job);}
RemoteObject _result = RemoteObject.createImmutable("");
Debug.locals.put("Job", _job);
 BA.debugLineNum = 86;BA.debugLine="Sub JobDone(Job As HttpJob)";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 87;BA.debugLine="Dim result As String";
Debug.ShouldStop(4194304);
_result = RemoteObject.createImmutable("");Debug.locals.put("result", _result);
 BA.debugLineNum = 88;BA.debugLine="result = Job.GetString";
Debug.ShouldStop(8388608);
_result = _job.runMethod(true,"_getstring");Debug.locals.put("result", _result);
 BA.debugLineNum = 89;BA.debugLine="ProgressDialogHide";
Debug.ShouldStop(16777216);
altaproducto.mostCurrent.__c.runVoidMethod ("ProgressDialogHide");
 BA.debugLineNum = 90;BA.debugLine="If result = \"ok\" Then";
Debug.ShouldStop(33554432);
if (RemoteObject.solveBoolean("=",_result,BA.ObjectToString("ok"))) { 
 BA.debugLineNum = 91;BA.debugLine="Main.SQL1.TransactionSuccessful";
Debug.ShouldStop(67108864);
altaproducto.mostCurrent._main._sql1 /*RemoteObject*/ .runVoidMethod ("TransactionSuccessful");
 BA.debugLineNum = 92;BA.debugLine="Main.SQL1.EndTransaction";
Debug.ShouldStop(134217728);
altaproducto.mostCurrent._main._sql1 /*RemoteObject*/ .runVoidMethod ("EndTransaction");
 BA.debugLineNum = 93;BA.debugLine="Msgbox (\"El registro se guardo correctamente\",\"\"";
Debug.ShouldStop(268435456);
altaproducto.mostCurrent.__c.runVoidMethodAndSync ("Msgbox",(Object)(BA.ObjectToCharSequence("El registro se guardo correctamente")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable(""))),altaproducto.mostCurrent.activityBA);
 BA.debugLineNum = 94;BA.debugLine="etNombre.Text = \"\"";
Debug.ShouldStop(536870912);
altaproducto.mostCurrent._etnombre.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 95;BA.debugLine="etModelo.Text = \"\"";
Debug.ShouldStop(1073741824);
altaproducto.mostCurrent._etmodelo.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 96;BA.debugLine="etTipo.Text = \"\"";
Debug.ShouldStop(-2147483648);
altaproducto.mostCurrent._ettipo.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 97;BA.debugLine="etPrecio.Text = \"\"";
Debug.ShouldStop(1);
altaproducto.mostCurrent._etprecio.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 }else {
 BA.debugLineNum = 100;BA.debugLine="Msgbox (\"Se encontro un error al guardar los dat";
Debug.ShouldStop(8);
altaproducto.mostCurrent.__c.runVoidMethodAndSync ("Msgbox",(Object)(BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Se encontro un error al guardar los datos: "),_result))),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable(". Recuerde Sincronizar los datos"))),altaproducto.mostCurrent.activityBA);
 };
 BA.debugLineNum = 103;BA.debugLine="End Sub";
Debug.ShouldStop(64);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
}