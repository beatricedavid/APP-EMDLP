package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,43);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_create", _firsttime);}
RemoteObject _ruta = RemoteObject.createImmutable("");
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 43;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(1024);
 BA.debugLineNum = 44;BA.debugLine="Activity.LoadLayout(\"PantallaPrincipal\")";
Debug.ShouldStop(2048);
main.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("PantallaPrincipal")),main.mostCurrent.activityBA);
 BA.debugLineNum = 45;BA.debugLine="webservice.Initialize(\"webservice\", Me)";
Debug.ShouldStop(4096);
main.mostCurrent._webservice.runVoidMethod ("_initialize",main.processBA,(Object)(BA.ObjectToString("webservice")),(Object)(main.getObject()));
 BA.debugLineNum = 46;BA.debugLine="lvNombre.TwoLinesLayout.Label.TextSize = 10";
Debug.ShouldStop(8192);
main.mostCurrent._lvnombre.runMethod(false,"getTwoLinesLayout").getField(false,"Label").runMethod(true,"setTextSize",BA.numberCast(float.class, 10));
 BA.debugLineNum = 47;BA.debugLine="lvNombre.TwoLinesLayout.ItemHeight = 80dip";
Debug.ShouldStop(16384);
main.mostCurrent._lvnombre.runMethod(false,"getTwoLinesLayout").runMethod(true,"setItemHeight",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 80))));
 BA.debugLineNum = 48;BA.debugLine="lvNombre.TwoLinesLayout.Label.SetLayout(5dip,2dip";
Debug.ShouldStop(32768);
main.mostCurrent._lvnombre.runMethod(false,"getTwoLinesLayout").getField(false,"Label").runVoidMethod ("SetLayout",(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 2)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 300)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 50)))));
 BA.debugLineNum = 49;BA.debugLine="lvNombre.TwoLinesLayout.SecondLabel.SetLayout(5di";
Debug.ShouldStop(65536);
main.mostCurrent._lvnombre.runMethod(false,"getTwoLinesLayout").getField(false,"SecondLabel").runVoidMethod ("SetLayout",(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 55)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 80)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30)))));
 BA.debugLineNum = 50;BA.debugLine="lvNombre.TwoLinesLayout.Label.TextColor = Colors.";
Debug.ShouldStop(131072);
main.mostCurrent._lvnombre.runMethod(false,"getTwoLinesLayout").getField(false,"Label").runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 53;BA.debugLine="Activity.AddMenuItem(\"Sincronizar Datos\",\"xMenuSi";
Debug.ShouldStop(1048576);
main.mostCurrent._activity.runVoidMethod ("AddMenuItem",(Object)(BA.ObjectToCharSequence("Sincronizar Datos")),(Object)(RemoteObject.createImmutable("xMenuSincronizarDatos")));
 BA.debugLineNum = 54;BA.debugLine="Activity.AddMenuItem(\"Nuevo Producto\",\"xMenuNuevo";
Debug.ShouldStop(2097152);
main.mostCurrent._activity.runVoidMethod ("AddMenuItem",(Object)(BA.ObjectToCharSequence("Nuevo Producto")),(Object)(RemoteObject.createImmutable("xMenuNuevoProducto")));
 BA.debugLineNum = 55;BA.debugLine="Activity.AddMenuItem(\"Descargar DB\",\"xMenuDescarg";
Debug.ShouldStop(4194304);
main.mostCurrent._activity.runVoidMethod ("AddMenuItem",(Object)(BA.ObjectToCharSequence("Descargar DB")),(Object)(RemoteObject.createImmutable("xMenuDescargarDB")));
 BA.debugLineNum = 57;BA.debugLine="Dim ruta As String";
Debug.ShouldStop(16777216);
_ruta = RemoteObject.createImmutable("");Debug.locals.put("ruta", _ruta);
 BA.debugLineNum = 61;BA.debugLine="ruta = File.DirInternal";
Debug.ShouldStop(268435456);
_ruta = main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal");Debug.locals.put("ruta", _ruta);
 BA.debugLineNum = 63;BA.debugLine="If File.Exists(ruta,\"basedatos.db\")=False Then";
Debug.ShouldStop(1073741824);
if (RemoteObject.solveBoolean("=",main.mostCurrent.__c.getField(false,"File").runMethod(true,"Exists",(Object)(_ruta),(Object)(RemoteObject.createImmutable("basedatos.db"))),main.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 64;BA.debugLine="File.Copy(File.DirAssets,\"basedatos.db\",ruta,\"ba";
Debug.ShouldStop(-2147483648);
main.mostCurrent.__c.getField(false,"File").runVoidMethod ("Copy",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("basedatos.db")),(Object)(_ruta),(Object)(RemoteObject.createImmutable("basedatos.db")));
 };
 BA.debugLineNum = 66;BA.debugLine="SQL1.Initialize(ruta,\"basedatos.db\",True)";
Debug.ShouldStop(2);
main._sql1.runVoidMethod ("Initialize",(Object)(_ruta),(Object)(BA.ObjectToString("basedatos.db")),(Object)(main.mostCurrent.__c.getField(true,"True")));
 BA.debugLineNum = 67;BA.debugLine="SetDivider(lvNombre, Colors.LightGray,20)";
Debug.ShouldStop(4);
_setdivider(main.mostCurrent._lvnombre,main.mostCurrent.__c.getField(false,"Colors").getField(true,"LightGray"),BA.numberCast(int.class, 20));
 BA.debugLineNum = 70;BA.debugLine="ColorBusqueda.Initialize2(Colors.RGB(248,248,255)";
Debug.ShouldStop(32);
main.mostCurrent._colorbusqueda.runVoidMethod ("Initialize2",(Object)(main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 248)),(Object)(BA.numberCast(int.class, 248)),(Object)(BA.numberCast(int.class, 255)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 2)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 2)))),(Object)(main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 80)),(Object)(BA.numberCast(int.class, 80)),(Object)(BA.numberCast(int.class, 80)))));
 BA.debugLineNum = 71;BA.debugLine="ColorLista.Initialize(Colors.RGB(248,248,255), 5d";
Debug.ShouldStop(64);
main.mostCurrent._colorlista.runVoidMethod ("Initialize",(Object)(main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 248)),(Object)(BA.numberCast(int.class, 248)),(Object)(BA.numberCast(int.class, 255)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5)))));
 BA.debugLineNum = 72;BA.debugLine="ColorFondo.Initialize(Colors.RGB(248,248,255), 5d";
Debug.ShouldStop(128);
main.mostCurrent._colorfondo.runVoidMethod ("Initialize",(Object)(main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 248)),(Object)(BA.numberCast(int.class, 248)),(Object)(BA.numberCast(int.class, 255)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5)))));
 BA.debugLineNum = 73;BA.debugLine="txtBuscar.Background=ColorBusqueda";
Debug.ShouldStop(256);
main.mostCurrent._txtbuscar.runMethod(false,"setBackground",(main.mostCurrent._colorbusqueda.getObject()));
 BA.debugLineNum = 74;BA.debugLine="lvNombre.Background = ColorLista";
Debug.ShouldStop(512);
main.mostCurrent._lvnombre.runMethod(false,"setBackground",(main.mostCurrent._colorlista.getObject()));
 BA.debugLineNum = 75;BA.debugLine="Activity.Background = ColorFondo";
Debug.ShouldStop(1024);
main.mostCurrent._activity.runMethod(false,"setBackground",(main.mostCurrent._colorfondo.getObject()));
 BA.debugLineNum = 76;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
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
		Debug.PushSubsStack("Activity_KeyPress (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,191);
if (RapidSub.canDelegate("activity_keypress")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_keypress", _keycode);}
RemoteObject _mensaje = RemoteObject.createImmutable(0);
Debug.locals.put("KeyCode", _keycode);
 BA.debugLineNum = 191;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 192;BA.debugLine="Dim mensaje As Int";
Debug.ShouldStop(-2147483648);
_mensaje = RemoteObject.createImmutable(0);Debug.locals.put("mensaje", _mensaje);
 BA.debugLineNum = 193;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
Debug.ShouldStop(1);
if (RemoteObject.solveBoolean("=",_keycode,BA.numberCast(double.class, main.mostCurrent.__c.getField(false,"KeyCodes").getField(true,"KEYCODE_BACK")))) { 
 BA.debugLineNum = 194;BA.debugLine="mensaje = Msgbox2 (\"¿Cerrar el programa?\",\"\",\"Si";
Debug.ShouldStop(2);
_mensaje = main.mostCurrent.__c.runMethodAndSync(true,"Msgbox2",(Object)(BA.ObjectToCharSequence("¿Cerrar el programa?")),(Object)(BA.ObjectToCharSequence("")),(Object)(BA.ObjectToString("Si")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),(Object)((main.mostCurrent.__c.getField(false,"Null"))),main.mostCurrent.activityBA);Debug.locals.put("mensaje", _mensaje);
 BA.debugLineNum = 195;BA.debugLine="If mensaje = -1 Then";
Debug.ShouldStop(4);
if (RemoteObject.solveBoolean("=",_mensaje,BA.numberCast(double.class, -(double) (0 + 1)))) { 
 BA.debugLineNum = 196;BA.debugLine="Activity.Finish";
Debug.ShouldStop(8);
main.mostCurrent._activity.runVoidMethod ("Finish");
 }else {
 BA.debugLineNum = 198;BA.debugLine="Return True";
Debug.ShouldStop(32);
if (true) return main.mostCurrent.__c.getField(true,"True");
 };
 };
 BA.debugLineNum = 201;BA.debugLine="End Sub";
Debug.ShouldStop(256);
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
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,111);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 111;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(16384);
 BA.debugLineNum = 113;BA.debugLine="End Sub";
Debug.ShouldStop(65536);
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
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,107);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_resume");}
 BA.debugLineNum = 107;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(1024);
 BA.debugLineNum = 109;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 29;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 31;BA.debugLine="Private txtBuscar As EditText";
main.mostCurrent._txtbuscar = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 32;BA.debugLine="Private lvNombre As ListView";
main.mostCurrent._lvnombre = RemoteObject.createNew ("anywheresoftware.b4a.objects.ListViewWrapper");
 //BA.debugLineNum = 33;BA.debugLine="Dim webservice As HttpJob";
main.mostCurrent._webservice = RemoteObject.createNew ("anywheresoftware.b4a.samples.httputils2.httpjob");
 //BA.debugLineNum = 34;BA.debugLine="Dim json As JSONParser";
main.mostCurrent._json = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.JSONParser");
 //BA.debugLineNum = 37;BA.debugLine="Dim registro As Cursor";
main.mostCurrent._registro = RemoteObject.createNew ("anywheresoftware.b4a.sql.SQL.CursorWrapper");
 //BA.debugLineNum = 38;BA.debugLine="Dim ColorFondo, ColorBusqueda, ColorLista As Colo";
main.mostCurrent._colorfondo = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.ColorDrawable");
main.mostCurrent._colorbusqueda = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.ColorDrawable");
main.mostCurrent._colorlista = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.ColorDrawable");
 //BA.debugLineNum = 39;BA.debugLine="Dim EsDescargaExcel As Boolean";
main._esdescargaexcel = RemoteObject.createImmutable(false);
 //BA.debugLineNum = 40;BA.debugLine="EsDescargaExcel = False";
main._esdescargaexcel = main.mostCurrent.__c.getField(true,"False");
 //BA.debugLineNum = 41;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _jobdone(RemoteObject _job) throws Exception{
try {
		Debug.PushSubsStack("JobDone (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,115);
if (RapidSub.canDelegate("jobdone")) { return b4a.example.main.remoteMe.runUserSub(false, "main","jobdone", _job);}
RemoteObject _map1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _menuitems = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _m = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _contienejob = RemoteObject.createImmutable("");
RemoteObject _registroexcel = RemoteObject.createImmutable("");
RemoteObject _listaexcel = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
int _i = 0;
Debug.locals.put("Job", _job);
 BA.debugLineNum = 115;BA.debugLine="Sub JobDone (Job As HttpJob)";
Debug.ShouldStop(262144);
 BA.debugLineNum = 116;BA.debugLine="Dim Map1 As Map";
Debug.ShouldStop(524288);
_map1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("Map1", _map1);
 BA.debugLineNum = 117;BA.debugLine="Dim MenuItems As List";
Debug.ShouldStop(1048576);
_menuitems = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("MenuItems", _menuitems);
 BA.debugLineNum = 118;BA.debugLine="Dim m As Map";
Debug.ShouldStop(2097152);
_m = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("m", _m);
 BA.debugLineNum = 119;BA.debugLine="Dim contieneJob As String";
Debug.ShouldStop(4194304);
_contienejob = RemoteObject.createImmutable("");Debug.locals.put("contieneJob", _contienejob);
 BA.debugLineNum = 120;BA.debugLine="Dim registroExcel As String";
Debug.ShouldStop(8388608);
_registroexcel = RemoteObject.createImmutable("");Debug.locals.put("registroExcel", _registroexcel);
 BA.debugLineNum = 121;BA.debugLine="Dim listaExcel As List";
Debug.ShouldStop(16777216);
_listaexcel = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("listaExcel", _listaexcel);
 BA.debugLineNum = 125;BA.debugLine="If Job.Success = True Then";
Debug.ShouldStop(268435456);
if (RemoteObject.solveBoolean("=",_job.getField(true,"_success"),main.mostCurrent.__c.getField(true,"True"))) { 
 BA.debugLineNum = 126;BA.debugLine="json.Initialize(Job.GetString)";
Debug.ShouldStop(536870912);
main.mostCurrent._json.runVoidMethod ("Initialize",(Object)(_job.runMethod(true,"_getstring")));
 BA.debugLineNum = 127;BA.debugLine="contieneJob = Job.GetString";
Debug.ShouldStop(1073741824);
_contienejob = _job.runMethod(true,"_getstring");Debug.locals.put("contieneJob", _contienejob);
 BA.debugLineNum = 128;BA.debugLine="If contieneJob = \"[]\" Then";
Debug.ShouldStop(-2147483648);
if (RemoteObject.solveBoolean("=",_contienejob,BA.ObjectToString("[]"))) { 
 BA.debugLineNum = 129;BA.debugLine="Msgbox (\"no hay datos\", \"\")";
Debug.ShouldStop(1);
main.mostCurrent.__c.runVoidMethodAndSync ("Msgbox",(Object)(BA.ObjectToCharSequence("no hay datos")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable(""))),main.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 131;BA.debugLine="If EsDescargaExcel Then	'ES DESCARGA DE DATOS P";
Debug.ShouldStop(4);
if (main._esdescargaexcel.<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 138;BA.debugLine="listaExcel.Initialize";
Debug.ShouldStop(512);
_listaexcel.runVoidMethod ("Initialize");
 BA.debugLineNum = 139;BA.debugLine="Map1 = json.NextObject";
Debug.ShouldStop(1024);
_map1 = main.mostCurrent._json.runMethod(false,"NextObject");Debug.locals.put("Map1", _map1);
 BA.debugLineNum = 140;BA.debugLine="MenuItems = Map1.Get(\"productos\")";
Debug.ShouldStop(2048);
_menuitems.setObject(_map1.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("productos")))));
 BA.debugLineNum = 141;BA.debugLine="ToastMessageShow (File.DirDefaultExternal, Tru";
Debug.ShouldStop(4096);
main.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirDefaultExternal"))),(Object)(main.mostCurrent.__c.getField(true,"True")));
 BA.debugLineNum = 142;BA.debugLine="Log(File.DirDefaultExternal)";
Debug.ShouldStop(8192);
main.mostCurrent.__c.runVoidMethod ("LogImpl","0983067",main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirDefaultExternal"),0);
 BA.debugLineNum = 143;BA.debugLine="For i=0 To MenuItems.Size -1";
Debug.ShouldStop(16384);
{
final int step19 = 1;
final int limit19 = RemoteObject.solve(new RemoteObject[] {_menuitems.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step19 > 0 && _i <= limit19) || (step19 < 0 && _i >= limit19) ;_i = ((int)(0 + _i + step19))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 144;BA.debugLine="m = MenuItems.Get(i)";
Debug.ShouldStop(32768);
_m.setObject(_menuitems.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));
 BA.debugLineNum = 145;BA.debugLine="registroExcel = m.Get(\"id\") & \";\" & m.Get(\"no";
Debug.ShouldStop(65536);
_registroexcel = RemoteObject.concat(_m.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("id")))),RemoteObject.createImmutable(";"),_m.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("nombre")))),RemoteObject.createImmutable(";"),_m.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("modelo")))),RemoteObject.createImmutable(";"),_m.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("tipo")))),RemoteObject.createImmutable(";"),_m.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("precio")))));Debug.locals.put("registroExcel", _registroexcel);
 BA.debugLineNum = 146;BA.debugLine="File.WriteString(File.DirRootExternal,\"baseda";
Debug.ShouldStop(131072);
main.mostCurrent.__c.getField(false,"File").runVoidMethod ("WriteString",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirRootExternal")),(Object)(BA.ObjectToString("basedatos.csv")),(Object)(_registroexcel));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 153;BA.debugLine="EsDescargaExcel=False";
Debug.ShouldStop(16777216);
main._esdescargaexcel = main.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 154;BA.debugLine="ToastMessageShow(\"Descarga completa\",False)";
Debug.ShouldStop(33554432);
main.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Descarga completa")),(Object)(main.mostCurrent.__c.getField(true,"False")));
 }else {
 BA.debugLineNum = 157;BA.debugLine="SQL1.ExecNonQuery(\"DELETE FROM productos\")";
Debug.ShouldStop(268435456);
main._sql1.runVoidMethod ("ExecNonQuery",(Object)(RemoteObject.createImmutable("DELETE FROM productos")));
 BA.debugLineNum = 158;BA.debugLine="SQL1.ExecNonQuery(\"VACUUM\")";
Debug.ShouldStop(536870912);
main._sql1.runVoidMethod ("ExecNonQuery",(Object)(RemoteObject.createImmutable("VACUUM")));
 BA.debugLineNum = 159;BA.debugLine="Map1 = json.NextObject";
Debug.ShouldStop(1073741824);
_map1 = main.mostCurrent._json.runMethod(false,"NextObject");Debug.locals.put("Map1", _map1);
 BA.debugLineNum = 160;BA.debugLine="MenuItems = Map1.Get(\"productos\")";
Debug.ShouldStop(-2147483648);
_menuitems.setObject(_map1.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("productos")))));
 BA.debugLineNum = 161;BA.debugLine="For i=0 To MenuItems.Size -1";
Debug.ShouldStop(1);
{
final int step31 = 1;
final int limit31 = RemoteObject.solve(new RemoteObject[] {_menuitems.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step31 > 0 && _i <= limit31) || (step31 < 0 && _i >= limit31) ;_i = ((int)(0 + _i + step31))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 162;BA.debugLine="m = MenuItems.Get(i)";
Debug.ShouldStop(2);
_m.setObject(_menuitems.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));
 BA.debugLineNum = 163;BA.debugLine="SQL1.ExecNonQuery(\"INSERT INTO productos (id,";
Debug.ShouldStop(4);
main._sql1.runVoidMethod ("ExecNonQuery",(Object)(RemoteObject.concat(RemoteObject.createImmutable("INSERT INTO productos (id,nombre,modelo,tipo,precio,categoria) VALUES('"),_m.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("id")))),RemoteObject.createImmutable("','"),_m.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("nombre")))),RemoteObject.createImmutable("','"),_m.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("modelo")))),RemoteObject.createImmutable("','"),_m.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("tipo")))),RemoteObject.createImmutable("','"),_m.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("precio")))),RemoteObject.createImmutable("','"),_m.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("categoria")))),RemoteObject.createImmutable("')"))));
 }
}Debug.locals.put("i", _i);
;
 };
 };
 }else {
 BA.debugLineNum = 168;BA.debugLine="Msgbox (\"error en el jobdone: \" & Job.ErrorMessa";
Debug.ShouldStop(128);
main.mostCurrent.__c.runVoidMethodAndSync ("Msgbox",(Object)(BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("error en el jobdone: "),_job.getField(true,"_errormessage")))),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("EMDLP"))),main.mostCurrent.activityBA);
 };
 BA.debugLineNum = 170;BA.debugLine="ProgressDialogHide";
Debug.ShouldStop(512);
main.mostCurrent.__c.runVoidMethod ("ProgressDialogHide");
 BA.debugLineNum = 172;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _lvnombre_itemlongclick(RemoteObject _position,RemoteObject _value) throws Exception{
try {
		Debug.PushSubsStack("lvNombre_ItemLongClick (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,174);
if (RapidSub.canDelegate("lvnombre_itemlongclick")) { return b4a.example.main.remoteMe.runUserSub(false, "main","lvnombre_itemlongclick", _position, _value);}
RemoteObject _item = RemoteObject.createImmutable(0);
RemoteObject _reg = RemoteObject.declareNull("anywheresoftware.b4a.sql.SQL.CursorWrapper");
Debug.locals.put("Position", _position);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 174;BA.debugLine="Sub lvNombre_ItemLongClick (Position As Int, Value";
Debug.ShouldStop(8192);
 BA.debugLineNum = 175;BA.debugLine="Dim item As Int";
Debug.ShouldStop(16384);
_item = RemoteObject.createImmutable(0);Debug.locals.put("item", _item);
 BA.debugLineNum = 176;BA.debugLine="item = Value";
Debug.ShouldStop(32768);
_item = BA.numberCast(int.class, _value);Debug.locals.put("item", _item);
 BA.debugLineNum = 177;BA.debugLine="Dim reg As Cursor";
Debug.ShouldStop(65536);
_reg = RemoteObject.createNew ("anywheresoftware.b4a.sql.SQL.CursorWrapper");Debug.locals.put("reg", _reg);
 BA.debugLineNum = 178;BA.debugLine="reg = SQL1.ExecQuery(\"select * from productos whe";
Debug.ShouldStop(131072);
_reg.setObject(main._sql1.runMethod(false,"ExecQuery",(Object)(RemoteObject.concat(RemoteObject.createImmutable("select * from productos where id = '"),_item,RemoteObject.createImmutable("'")))));
 BA.debugLineNum = 180;BA.debugLine="reg.Position = 0";
Debug.ShouldStop(524288);
_reg.runMethod(true,"setPosition",BA.numberCast(int.class, 0));
 BA.debugLineNum = 181;BA.debugLine="idProducto = reg.GetInt(\"id\")";
Debug.ShouldStop(1048576);
main._idproducto = _reg.runMethod(true,"GetInt",(Object)(RemoteObject.createImmutable("id")));
 BA.debugLineNum = 182;BA.debugLine="nombreProducto = reg.GetString(\"nombre\")";
Debug.ShouldStop(2097152);
main._nombreproducto = _reg.runMethod(true,"GetString",(Object)(RemoteObject.createImmutable("nombre")));
 BA.debugLineNum = 183;BA.debugLine="modeloProducto = reg.GetString(\"modelo\")";
Debug.ShouldStop(4194304);
main._modeloproducto = _reg.runMethod(true,"GetString",(Object)(RemoteObject.createImmutable("modelo")));
 BA.debugLineNum = 184;BA.debugLine="tipoProducto = reg.Getstring(\"tipo\")";
Debug.ShouldStop(8388608);
main._tipoproducto = _reg.runMethod(true,"GetString",(Object)(RemoteObject.createImmutable("tipo")));
 BA.debugLineNum = 185;BA.debugLine="precioProducto = reg.Getstring(\"precio\")";
Debug.ShouldStop(16777216);
main._precioproducto = _reg.runMethod(true,"GetString",(Object)(RemoteObject.createImmutable("precio")));
 BA.debugLineNum = 187;BA.debugLine="StartActivity(\"Edicion\")";
Debug.ShouldStop(67108864);
main.mostCurrent.__c.runVoidMethod ("StartActivity",main.processBA,(Object)((RemoteObject.createImmutable("Edicion"))));
 BA.debugLineNum = 188;BA.debugLine="Activity.Finish";
Debug.ShouldStop(134217728);
main.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 189;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main_subs_0._process_globals();
starter_subs_0._process_globals();
edicion_subs_0._process_globals();
altaproducto_subs_0._process_globals();
main.myClass = BA.getDeviceClass ("b4a.example.main");
starter.myClass = BA.getDeviceClass ("b4a.example.starter");
edicion.myClass = BA.getDeviceClass ("b4a.example.edicion");
altaproducto.myClass = BA.getDeviceClass ("b4a.example.altaproducto");
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 19;BA.debugLine="Dim idProducto As Int";
main._idproducto = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 20;BA.debugLine="Dim nombreProducto As String";
main._nombreproducto = RemoteObject.createImmutable("");
 //BA.debugLineNum = 21;BA.debugLine="Dim modeloProducto As String";
main._modeloproducto = RemoteObject.createImmutable("");
 //BA.debugLineNum = 22;BA.debugLine="Dim tipoProducto As String";
main._tipoproducto = RemoteObject.createImmutable("");
 //BA.debugLineNum = 23;BA.debugLine="Dim precioProducto As String";
main._precioproducto = RemoteObject.createImmutable("");
 //BA.debugLineNum = 26;BA.debugLine="Dim SQL1 As SQL";
main._sql1 = RemoteObject.createNew ("anywheresoftware.b4a.sql.SQL");
 //BA.debugLineNum = 27;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _setdivider(RemoteObject _lv,RemoteObject _color,RemoteObject _height) throws Exception{
try {
		Debug.PushSubsStack("SetDivider (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,78);
if (RapidSub.canDelegate("setdivider")) { return b4a.example.main.remoteMe.runUserSub(false, "main","setdivider", _lv, _color, _height);}
RemoteObject _r = RemoteObject.declareNull("anywheresoftware.b4a.agraham.reflection.Reflection");
RemoteObject _cd = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.ColorDrawable");
Debug.locals.put("lv", _lv);
Debug.locals.put("Color", _color);
Debug.locals.put("Height", _height);
 BA.debugLineNum = 78;BA.debugLine="Sub SetDivider(lv As ListView, Color As Int, Heigh";
Debug.ShouldStop(8192);
 BA.debugLineNum = 79;BA.debugLine="Dim r As Reflector";
Debug.ShouldStop(16384);
_r = RemoteObject.createNew ("anywheresoftware.b4a.agraham.reflection.Reflection");Debug.locals.put("r", _r);
 BA.debugLineNum = 80;BA.debugLine="r.Target = lv";
Debug.ShouldStop(32768);
_r.setField ("Target",(_lv.getObject()));
 BA.debugLineNum = 81;BA.debugLine="Dim CD As ColorDrawable";
Debug.ShouldStop(65536);
_cd = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.ColorDrawable");Debug.locals.put("CD", _cd);
 BA.debugLineNum = 82;BA.debugLine="CD.Initialize(Color, 0)";
Debug.ShouldStop(131072);
_cd.runVoidMethod ("Initialize",(Object)(_color),(Object)(BA.numberCast(int.class, 0)));
 BA.debugLineNum = 83;BA.debugLine="r.RunMethod4(\"setDivider\", Array As Object(CD), A";
Debug.ShouldStop(262144);
_r.runVoidMethod ("RunMethod4",(Object)(BA.ObjectToString("setDivider")),(Object)(RemoteObject.createNewArray("Object",new int[] {1},new Object[] {(_cd.getObject())})),(Object)(RemoteObject.createNewArray("String",new int[] {1},new Object[] {RemoteObject.createImmutable("android.graphics.drawable.Drawable")})));
 BA.debugLineNum = 84;BA.debugLine="r.RunMethod2(\"setDividerHeight\", Height, \"java.la";
Debug.ShouldStop(524288);
_r.runVoidMethod ("RunMethod2",(Object)(BA.ObjectToString("setDividerHeight")),(Object)(BA.NumberToString(_height)),(Object)(RemoteObject.createImmutable("java.lang.int")));
 BA.debugLineNum = 85;BA.debugLine="End Sub";
Debug.ShouldStop(1048576);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _txtbuscar_textchanged(RemoteObject _old,RemoteObject _new) throws Exception{
try {
		Debug.PushSubsStack("txtBuscar_TextChanged (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,203);
if (RapidSub.canDelegate("txtbuscar_textchanged")) { return b4a.example.main.remoteMe.runUserSub(false, "main","txtbuscar_textchanged", _old, _new);}
RemoteObject _color1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.ColorDrawable");
RemoteObject _color2 = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.ColorDrawable");
RemoteObject _color3 = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.ColorDrawable");
RemoteObject _tamanioletra = RemoteObject.createImmutable(0f);
int _i = 0;
Debug.locals.put("Old", _old);
Debug.locals.put("New", _new);
 BA.debugLineNum = 203;BA.debugLine="Sub txtBuscar_TextChanged (Old As String, New As S";
Debug.ShouldStop(1024);
 BA.debugLineNum = 204;BA.debugLine="Dim Color1, Color2,Color3 As ColorDrawable";
Debug.ShouldStop(2048);
_color1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.ColorDrawable");Debug.locals.put("Color1", _color1);
_color2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.ColorDrawable");Debug.locals.put("Color2", _color2);
_color3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.ColorDrawable");Debug.locals.put("Color3", _color3);
 BA.debugLineNum = 205;BA.debugLine="Dim TamanioLetra As Float";
Debug.ShouldStop(4096);
_tamanioletra = RemoteObject.createImmutable(0f);Debug.locals.put("TamanioLetra", _tamanioletra);
 BA.debugLineNum = 207;BA.debugLine="TamanioLetra=16";
Debug.ShouldStop(16384);
_tamanioletra = BA.numberCast(float.class, 16);Debug.locals.put("TamanioLetra", _tamanioletra);
 BA.debugLineNum = 209;BA.debugLine="lvNombre.Clear";
Debug.ShouldStop(65536);
main.mostCurrent._lvnombre.runVoidMethod ("Clear");
 BA.debugLineNum = 210;BA.debugLine="Color1.Initialize(Colors.Red,5dip)";
Debug.ShouldStop(131072);
_color1.runVoidMethod ("Initialize",(Object)(main.mostCurrent.__c.getField(false,"Colors").getField(true,"Red")),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5)))));
 BA.debugLineNum = 211;BA.debugLine="Color2.Initialize(Colors.Blue,5dip)";
Debug.ShouldStop(262144);
_color2.runVoidMethod ("Initialize",(Object)(main.mostCurrent.__c.getField(false,"Colors").getField(true,"Blue")),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5)))));
 BA.debugLineNum = 212;BA.debugLine="Color3.Initialize(Colors.Cyan, 5dip)";
Debug.ShouldStop(524288);
_color3.runVoidMethod ("Initialize",(Object)(main.mostCurrent.__c.getField(false,"Colors").getField(true,"Cyan")),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5)))));
 BA.debugLineNum = 214;BA.debugLine="registro = SQL1.ExecQuery(\"select * from (select";
Debug.ShouldStop(2097152);
main.mostCurrent._registro.setObject(main._sql1.runMethod(false,"ExecQuery",(Object)(RemoteObject.concat(RemoteObject.createImmutable("select * from (select * from productos where nombre like '%"),main.mostCurrent._txtbuscar.runMethod(true,"getText"),RemoteObject.createImmutable("%' UNION select * from productos where modelo like '%"),main.mostCurrent._txtbuscar.runMethod(true,"getText"),RemoteObject.createImmutable("%') order by nombre, modelo, tipo, precio asc")))));
 BA.debugLineNum = 215;BA.debugLine="lvNombre.TwoLinesLayout.Label.Gravity = Gravity.C";
Debug.ShouldStop(4194304);
main.mostCurrent._lvnombre.runMethod(false,"getTwoLinesLayout").getField(false,"Label").runMethod(true,"setGravity",main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_VERTICAL"));
 BA.debugLineNum = 216;BA.debugLine="lvNombre.TwoLinesLayout.Label.TextSize = TamanioL";
Debug.ShouldStop(8388608);
main.mostCurrent._lvnombre.runMethod(false,"getTwoLinesLayout").getField(false,"Label").runMethod(true,"setTextSize",_tamanioletra);
 BA.debugLineNum = 217;BA.debugLine="lvNombre.TwoLinesLayout.SecondLabel.TextSize=18";
Debug.ShouldStop(16777216);
main.mostCurrent._lvnombre.runMethod(false,"getTwoLinesLayout").getField(false,"SecondLabel").runMethod(true,"setTextSize",BA.numberCast(float.class, 18));
 BA.debugLineNum = 220;BA.debugLine="If registro.RowCount > 0 Then";
Debug.ShouldStop(134217728);
if (RemoteObject.solveBoolean(">",main.mostCurrent._registro.runMethod(true,"getRowCount"),BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 221;BA.debugLine="For i = 0 To registro.RowCount - 1";
Debug.ShouldStop(268435456);
{
final int step13 = 1;
final int limit13 = RemoteObject.solve(new RemoteObject[] {main.mostCurrent._registro.runMethod(true,"getRowCount"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step13 > 0 && _i <= limit13) || (step13 < 0 && _i >= limit13) ;_i = ((int)(0 + _i + step13))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 222;BA.debugLine="registro.Position = i";
Debug.ShouldStop(536870912);
main.mostCurrent._registro.runMethod(true,"setPosition",BA.numberCast(int.class, _i));
 BA.debugLineNum = 224;BA.debugLine="lvNombre.AddTwoLines2(registro.GetString(\"nombr";
Debug.ShouldStop(-2147483648);
main.mostCurrent._lvnombre.runVoidMethod ("AddTwoLines2",(Object)(BA.ObjectToCharSequence(RemoteObject.concat(main.mostCurrent._registro.runMethod(true,"GetString",(Object)(RemoteObject.createImmutable("nombre"))),RemoteObject.createImmutable(" | "),main.mostCurrent._registro.runMethod(true,"GetString",(Object)(RemoteObject.createImmutable("modelo"))),RemoteObject.createImmutable(" | "),main.mostCurrent._registro.runMethod(true,"GetString",(Object)(RemoteObject.createImmutable("tipo")))))),(Object)(BA.ObjectToCharSequence(main.mostCurrent._registro.runMethod(true,"GetString",(Object)(RemoteObject.createImmutable("precio"))))),(Object)((main.mostCurrent._registro.runMethod(true,"GetInt",(Object)(RemoteObject.createImmutable("id"))))));
 BA.debugLineNum = 225;BA.debugLine="lvNombre.TwoLinesLayout.SecondLabel.TextColor=C";
Debug.ShouldStop(1);
main.mostCurrent._lvnombre.runMethod(false,"getTwoLinesLayout").getField(false,"SecondLabel").runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 100)),(Object)(BA.numberCast(int.class, 0))));
 }
}Debug.locals.put("i", _i);
;
 };
 BA.debugLineNum = 239;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _xmenudescargardb_click() throws Exception{
try {
		Debug.PushSubsStack("xMenuDescargarDB_click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,98);
if (RapidSub.canDelegate("xmenudescargardb_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","xmenudescargardb_click");}
 BA.debugLineNum = 98;BA.debugLine="Sub xMenuDescargarDB_click";
Debug.ShouldStop(2);
 BA.debugLineNum = 99;BA.debugLine="ProgressDialogShow(\"Generando archivo...\")";
Debug.ShouldStop(4);
main.mostCurrent.__c.runVoidMethod ("ProgressDialogShow",main.mostCurrent.activityBA,(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Generando archivo..."))));
 BA.debugLineNum = 101;BA.debugLine="EsDescargaExcel=True";
Debug.ShouldStop(16);
main._esdescargaexcel = main.mostCurrent.__c.getField(true,"True");
 BA.debugLineNum = 102;BA.debugLine="webservice.Download(\"https://elmundodelasplantas.";
Debug.ShouldStop(32);
main.mostCurrent._webservice.runVoidMethod ("_download",(Object)(RemoteObject.createImmutable("https://elmundodelasplantas.000webhostapp.com/consultarTodos.php")));
 BA.debugLineNum = 105;BA.debugLine="End Sub";
Debug.ShouldStop(256);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _xmenunuevoproducto_click() throws Exception{
try {
		Debug.PushSubsStack("xMenuNuevoProducto_click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,93);
if (RapidSub.canDelegate("xmenunuevoproducto_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","xmenunuevoproducto_click");}
 BA.debugLineNum = 93;BA.debugLine="Sub xMenuNuevoProducto_click";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 94;BA.debugLine="StartActivity(\"AltaProducto\")";
Debug.ShouldStop(536870912);
main.mostCurrent.__c.runVoidMethod ("StartActivity",main.processBA,(Object)((RemoteObject.createImmutable("AltaProducto"))));
 BA.debugLineNum = 95;BA.debugLine="Activity.Finish";
Debug.ShouldStop(1073741824);
main.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 96;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _xmenusincronizardatos_click() throws Exception{
try {
		Debug.PushSubsStack("xMenuSincronizarDatos_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,87);
if (RapidSub.canDelegate("xmenusincronizardatos_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","xmenusincronizardatos_click");}
 BA.debugLineNum = 87;BA.debugLine="Sub xMenuSincronizarDatos_Click";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 88;BA.debugLine="ProgressDialogShow(\"Descargando datos...\")";
Debug.ShouldStop(8388608);
main.mostCurrent.__c.runVoidMethod ("ProgressDialogShow",main.mostCurrent.activityBA,(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Descargando datos..."))));
 BA.debugLineNum = 90;BA.debugLine="webservice.Download(\"https://elmundodelasplantas.";
Debug.ShouldStop(33554432);
main.mostCurrent._webservice.runVoidMethod ("_download",(Object)(RemoteObject.createImmutable("https://elmundodelasplantas.000webhostapp.com/consultarTodos.php")));
 BA.debugLineNum = 91;BA.debugLine="End Sub";
Debug.ShouldStop(67108864);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}