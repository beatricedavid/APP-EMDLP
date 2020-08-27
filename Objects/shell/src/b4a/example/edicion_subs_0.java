package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class edicion_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (edicion) ","edicion",2,edicion.mostCurrent.activityBA,edicion.mostCurrent,26);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.edicion.remoteMe.runUserSub(false, "edicion","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 26;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 28;BA.debugLine="Activity.LoadLayout(\"PantallaEdicion\")";
Debug.ShouldStop(134217728);
edicion.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("PantallaEdicion")),edicion.mostCurrent.activityBA);
 BA.debugLineNum = 29;BA.debugLine="webservice2.Initialize(\"webservice2\", Me)";
Debug.ShouldStop(268435456);
edicion.mostCurrent._webservice2.runVoidMethod ("_initialize",edicion.processBA,(Object)(BA.ObjectToString("webservice2")),(Object)(edicion.getObject()));
 BA.debugLineNum = 30;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_keypress(RemoteObject _keycode) throws Exception{
try {
		Debug.PushSubsStack("Activity_KeyPress (edicion) ","edicion",2,edicion.mostCurrent.activityBA,edicion.mostCurrent,83);
if (RapidSub.canDelegate("activity_keypress")) { return b4a.example.edicion.remoteMe.runUserSub(false, "edicion","activity_keypress", _keycode);}
RemoteObject _mensaje = RemoteObject.createImmutable(0);
Debug.locals.put("KeyCode", _keycode);
 BA.debugLineNum = 83;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
Debug.ShouldStop(262144);
 BA.debugLineNum = 84;BA.debugLine="Dim mensaje As Int";
Debug.ShouldStop(524288);
_mensaje = RemoteObject.createImmutable(0);Debug.locals.put("mensaje", _mensaje);
 BA.debugLineNum = 85;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
Debug.ShouldStop(1048576);
if (RemoteObject.solveBoolean("=",_keycode,BA.numberCast(double.class, edicion.mostCurrent.__c.getField(false,"KeyCodes").getField(true,"KEYCODE_BACK")))) { 
 BA.debugLineNum = 86;BA.debugLine="mensaje = Msgbox2 (\"¿Cerrar el programa?\",\"\",\"Si";
Debug.ShouldStop(2097152);
_mensaje = edicion.mostCurrent.__c.runMethodAndSync(true,"Msgbox2",(Object)(BA.ObjectToCharSequence("¿Cerrar el programa?")),(Object)(BA.ObjectToCharSequence("")),(Object)(BA.ObjectToString("Si")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),(Object)((edicion.mostCurrent.__c.getField(false,"Null"))),edicion.mostCurrent.activityBA);Debug.locals.put("mensaje", _mensaje);
 BA.debugLineNum = 87;BA.debugLine="If mensaje = -1 Then";
Debug.ShouldStop(4194304);
if (RemoteObject.solveBoolean("=",_mensaje,BA.numberCast(double.class, -(double) (0 + 1)))) { 
 BA.debugLineNum = 88;BA.debugLine="ExitApplication";
Debug.ShouldStop(8388608);
edicion.mostCurrent.__c.runVoidMethod ("ExitApplication");
 }else {
 BA.debugLineNum = 90;BA.debugLine="Return True";
Debug.ShouldStop(33554432);
if (true) return edicion.mostCurrent.__c.getField(true,"True");
 };
 };
 BA.debugLineNum = 93;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
return RemoteObject.createImmutable(false);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_pause(RemoteObject _userclosed) throws Exception{
try {
		Debug.PushSubsStack("Activity_Pause (edicion) ","edicion",2,edicion.mostCurrent.activityBA,edicion.mostCurrent,39);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.edicion.remoteMe.runUserSub(false, "edicion","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 39;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(64);
 BA.debugLineNum = 41;BA.debugLine="End Sub";
Debug.ShouldStop(256);
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
		Debug.PushSubsStack("Activity_Resume (edicion) ","edicion",2,edicion.mostCurrent.activityBA,edicion.mostCurrent,32);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.edicion.remoteMe.runUserSub(false, "edicion","activity_resume");}
 BA.debugLineNum = 32;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 33;BA.debugLine="etNombre.Text = Main.nombreProducto";
Debug.ShouldStop(1);
edicion.mostCurrent._etnombre.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(edicion.mostCurrent._main._nombreproducto /*RemoteObject*/ ));
 BA.debugLineNum = 34;BA.debugLine="etModelo.Text = Main.modeloProducto";
Debug.ShouldStop(2);
edicion.mostCurrent._etmodelo.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(edicion.mostCurrent._main._modeloproducto /*RemoteObject*/ ));
 BA.debugLineNum = 35;BA.debugLine="etTipo.Text = Main.tipoProducto";
Debug.ShouldStop(4);
edicion.mostCurrent._ettipo.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(edicion.mostCurrent._main._tipoproducto /*RemoteObject*/ ));
 BA.debugLineNum = 36;BA.debugLine="etPrecio.Text = Main.precioProducto";
Debug.ShouldStop(8);
edicion.mostCurrent._etprecio.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(edicion.mostCurrent._main._precioproducto /*RemoteObject*/ ));
 BA.debugLineNum = 37;BA.debugLine="End Sub";
Debug.ShouldStop(16);
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
		Debug.PushSubsStack("btnGuardar_Click (edicion) ","edicion",2,edicion.mostCurrent.activityBA,edicion.mostCurrent,49);
if (RapidSub.canDelegate("btnguardar_click")) { return b4a.example.edicion.remoteMe.runUserSub(false, "edicion","btnguardar_click");}
RemoteObject _nombresinespacio = RemoteObject.createImmutable("");
RemoteObject _tiposinespacio = RemoteObject.createImmutable("");
RemoteObject _preciosinespacio = RemoteObject.createImmutable("");
RemoteObject _modelosinespacio = RemoteObject.createImmutable("");
 BA.debugLineNum = 49;BA.debugLine="Sub btnGuardar_Click";
Debug.ShouldStop(65536);
 BA.debugLineNum = 50;BA.debugLine="Dim nombreSinEspacio As String";
Debug.ShouldStop(131072);
_nombresinespacio = RemoteObject.createImmutable("");Debug.locals.put("nombreSinEspacio", _nombresinespacio);
 BA.debugLineNum = 51;BA.debugLine="Dim tipoSinEspacio As String";
Debug.ShouldStop(262144);
_tiposinespacio = RemoteObject.createImmutable("");Debug.locals.put("tipoSinEspacio", _tiposinespacio);
 BA.debugLineNum = 52;BA.debugLine="Dim precioSinEspacio As String";
Debug.ShouldStop(524288);
_preciosinespacio = RemoteObject.createImmutable("");Debug.locals.put("precioSinEspacio", _preciosinespacio);
 BA.debugLineNum = 53;BA.debugLine="Dim modeloSinEspacio As String";
Debug.ShouldStop(1048576);
_modelosinespacio = RemoteObject.createImmutable("");Debug.locals.put("modeloSinEspacio", _modelosinespacio);
 BA.debugLineNum = 55;BA.debugLine="If etNombre.Text <> \"\" Then";
Debug.ShouldStop(4194304);
if (RemoteObject.solveBoolean("!",edicion.mostCurrent._etnombre.runMethod(true,"getText"),BA.ObjectToString(""))) { 
 BA.debugLineNum = 56;BA.debugLine="ProgressDialogShow(\"Guardando datos...\")";
Debug.ShouldStop(8388608);
edicion.mostCurrent.__c.runVoidMethod ("ProgressDialogShow",edicion.mostCurrent.activityBA,(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Guardando datos..."))));
 BA.debugLineNum = 57;BA.debugLine="nombreSinEspacio = etNombre.Text.Replace(\" \",\"%2";
Debug.ShouldStop(16777216);
_nombresinespacio = edicion.mostCurrent._etnombre.runMethod(true,"getText").runMethod(true,"replace",(Object)(BA.ObjectToString(" ")),(Object)(RemoteObject.createImmutable("%20")));Debug.locals.put("nombreSinEspacio", _nombresinespacio);
 BA.debugLineNum = 58;BA.debugLine="modeloSinEspacio = etModelo.Text.Replace(\" \",\"%2";
Debug.ShouldStop(33554432);
_modelosinespacio = edicion.mostCurrent._etmodelo.runMethod(true,"getText").runMethod(true,"replace",(Object)(BA.ObjectToString(" ")),(Object)(RemoteObject.createImmutable("%20")));Debug.locals.put("modeloSinEspacio", _modelosinespacio);
 BA.debugLineNum = 59;BA.debugLine="tipoSinEspacio = etTipo.Text.Replace(\" \",\"%20\")";
Debug.ShouldStop(67108864);
_tiposinespacio = edicion.mostCurrent._ettipo.runMethod(true,"getText").runMethod(true,"replace",(Object)(BA.ObjectToString(" ")),(Object)(RemoteObject.createImmutable("%20")));Debug.locals.put("tipoSinEspacio", _tiposinespacio);
 BA.debugLineNum = 60;BA.debugLine="precioSinEspacio = etPrecio.Text.Replace(\" \",\"%2";
Debug.ShouldStop(134217728);
_preciosinespacio = edicion.mostCurrent._etprecio.runMethod(true,"getText").runMethod(true,"replace",(Object)(BA.ObjectToString(" ")),(Object)(RemoteObject.createImmutable("%20")));Debug.locals.put("precioSinEspacio", _preciosinespacio);
 BA.debugLineNum = 62;BA.debugLine="webservice2.Download(\"https://elmundodelasplanta";
Debug.ShouldStop(536870912);
edicion.mostCurrent._webservice2.runVoidMethod ("_download",(Object)(RemoteObject.concat(RemoteObject.createImmutable("https://elmundodelasplantas.000webhostapp.com/modificar.php?id="),edicion.mostCurrent._main._idproducto /*RemoteObject*/ ,RemoteObject.createImmutable("&nombre="),_nombresinespacio,RemoteObject.createImmutable("&modelo="),_modelosinespacio,RemoteObject.createImmutable("&tipo="),_tiposinespacio,RemoteObject.createImmutable("&precio="),_preciosinespacio)));
 BA.debugLineNum = 64;BA.debugLine="Main.SQL1.ExecNonQuery(\"update productos set nom";
Debug.ShouldStop(-2147483648);
edicion.mostCurrent._main._sql1 /*RemoteObject*/ .runVoidMethod ("ExecNonQuery",(Object)(RemoteObject.concat(RemoteObject.createImmutable("update productos set nombre='"),edicion.mostCurrent._etnombre.runMethod(true,"getText"),RemoteObject.createImmutable("' , modelo='"),edicion.mostCurrent._etmodelo.runMethod(true,"getText"),RemoteObject.createImmutable("' , tipo='"),edicion.mostCurrent._ettipo.runMethod(true,"getText"),RemoteObject.createImmutable("', precio='"),edicion.mostCurrent._etprecio.runMethod(true,"getText"),RemoteObject.createImmutable("' where id="),edicion.mostCurrent._main._idproducto /*RemoteObject*/ )));
 };
 BA.debugLineNum = 68;BA.debugLine="End Sub";
Debug.ShouldStop(8);
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
		Debug.PushSubsStack("btnVolver_Click (edicion) ","edicion",2,edicion.mostCurrent.activityBA,edicion.mostCurrent,44);
if (RapidSub.canDelegate("btnvolver_click")) { return b4a.example.edicion.remoteMe.runUserSub(false, "edicion","btnvolver_click");}
 BA.debugLineNum = 44;BA.debugLine="Sub btnVolver_Click";
Debug.ShouldStop(2048);
 BA.debugLineNum = 45;BA.debugLine="StartActivity(\"Main\")";
Debug.ShouldStop(4096);
edicion.mostCurrent.__c.runVoidMethod ("StartActivity",edicion.processBA,(Object)((RemoteObject.createImmutable("Main"))));
 BA.debugLineNum = 46;BA.debugLine="Activity.Finish";
Debug.ShouldStop(8192);
edicion.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 47;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
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
 //BA.debugLineNum = 15;BA.debugLine="Dim webservice2 As HttpJob";
edicion.mostCurrent._webservice2 = RemoteObject.createNew ("anywheresoftware.b4a.samples.httputils2.httpjob");
 //BA.debugLineNum = 18;BA.debugLine="Private btnVolver As Button";
edicion.mostCurrent._btnvolver = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 19;BA.debugLine="Private ButtonGuardar As Button";
edicion.mostCurrent._buttonguardar = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 20;BA.debugLine="Private etNombre As EditText";
edicion.mostCurrent._etnombre = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 21;BA.debugLine="Private etModelo As EditText";
edicion.mostCurrent._etmodelo = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 22;BA.debugLine="Private etTipo As EditText";
edicion.mostCurrent._ettipo = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 23;BA.debugLine="Private etPrecio As EditText";
edicion.mostCurrent._etprecio = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 24;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _jobdone(RemoteObject _job) throws Exception{
try {
		Debug.PushSubsStack("JobDone (edicion) ","edicion",2,edicion.mostCurrent.activityBA,edicion.mostCurrent,70);
if (RapidSub.canDelegate("jobdone")) { return b4a.example.edicion.remoteMe.runUserSub(false, "edicion","jobdone", _job);}
RemoteObject _result = RemoteObject.createImmutable("");
Debug.locals.put("Job", _job);
 BA.debugLineNum = 70;BA.debugLine="Sub JobDone(Job As HttpJob)";
Debug.ShouldStop(32);
 BA.debugLineNum = 71;BA.debugLine="Dim result As String";
Debug.ShouldStop(64);
_result = RemoteObject.createImmutable("");Debug.locals.put("result", _result);
 BA.debugLineNum = 72;BA.debugLine="result = Job.GetString";
Debug.ShouldStop(128);
_result = _job.runMethod(true,"_getstring");Debug.locals.put("result", _result);
 BA.debugLineNum = 73;BA.debugLine="ProgressDialogHide";
Debug.ShouldStop(256);
edicion.mostCurrent.__c.runVoidMethod ("ProgressDialogHide");
 BA.debugLineNum = 74;BA.debugLine="If result = \"ok\" Then";
Debug.ShouldStop(512);
if (RemoteObject.solveBoolean("=",_result,BA.ObjectToString("ok"))) { 
 BA.debugLineNum = 75;BA.debugLine="Msgbox (\"El registro se guardo correctamente\",\"\"";
Debug.ShouldStop(1024);
edicion.mostCurrent.__c.runVoidMethodAndSync ("Msgbox",(Object)(BA.ObjectToCharSequence("El registro se guardo correctamente")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable(""))),edicion.mostCurrent.activityBA);
 BA.debugLineNum = 77;BA.debugLine="StartActivity(\"Main\")";
Debug.ShouldStop(4096);
edicion.mostCurrent.__c.runVoidMethod ("StartActivity",edicion.processBA,(Object)((RemoteObject.createImmutable("Main"))));
 BA.debugLineNum = 78;BA.debugLine="Activity.Finish";
Debug.ShouldStop(8192);
edicion.mostCurrent._activity.runVoidMethod ("Finish");
 };
 BA.debugLineNum = 81;BA.debugLine="End Sub";
Debug.ShouldStop(65536);
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