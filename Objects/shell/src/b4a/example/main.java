
package b4a.example;

import java.io.IOException;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.PCBA;
import anywheresoftware.b4a.pc.RDebug;
import anywheresoftware.b4a.pc.RemoteObject;
import anywheresoftware.b4a.pc.RDebug.IRemote;
import anywheresoftware.b4a.pc.Debug;
import anywheresoftware.b4a.pc.B4XTypes.B4XClass;
import anywheresoftware.b4a.pc.B4XTypes.DeviceClass;

public class main implements IRemote{
	public static main mostCurrent;
	public static RemoteObject processBA;
    public static boolean processGlobalsRun;
    public static RemoteObject myClass;
    public static RemoteObject remoteMe;
	public main() {
		mostCurrent = this;
	}
    public RemoteObject getRemoteMe() {
        return remoteMe;    
    }
    
	public static void main (String[] args) throws Exception {
		new RDebug(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3]);
		RDebug.INSTANCE.waitForTask();

	}
    static {
        anywheresoftware.b4a.pc.RapidSub.moduleToObject.put(new B4XClass("main"), "b4a.example.main");
	}

public boolean isSingleton() {
		return true;
	}
     public static RemoteObject getObject() {
		return myClass;
	 }

	public RemoteObject activityBA;
	public RemoteObject _activity;
    private PCBA pcBA;

	public PCBA create(Object[] args) throws ClassNotFoundException{
		processBA = (RemoteObject) args[1];
		activityBA = (RemoteObject) args[2];
		_activity = (RemoteObject) args[3];
        anywheresoftware.b4a.keywords.Common.Density = (Float)args[4];
        remoteMe = (RemoteObject) args[5];
		pcBA = new PCBA(this, main.class);
        main_subs_0.initializeProcessGlobals();
		return pcBA;
	}
public static RemoteObject __c = RemoteObject.declareNull("anywheresoftware.b4a.keywords.Common");
public static RemoteObject _idproducto = RemoteObject.createImmutable(0);
public static RemoteObject _nombreproducto = RemoteObject.createImmutable("");
public static RemoteObject _modeloproducto = RemoteObject.createImmutable("");
public static RemoteObject _tipoproducto = RemoteObject.createImmutable("");
public static RemoteObject _precioproducto = RemoteObject.createImmutable("");
public static RemoteObject _sql1 = RemoteObject.declareNull("anywheresoftware.b4a.sql.SQL");
public static RemoteObject _txtbuscar = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _lvnombre = RemoteObject.declareNull("anywheresoftware.b4a.objects.ListViewWrapper");
public static RemoteObject _webservice = RemoteObject.declareNull("anywheresoftware.b4a.samples.httputils2.httpjob");
public static RemoteObject _json = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.JSONParser");
public static RemoteObject _registro = RemoteObject.declareNull("anywheresoftware.b4a.sql.SQL.CursorWrapper");
public static RemoteObject _colorfondo = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.ColorDrawable");
public static RemoteObject _colorbusqueda = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.ColorDrawable");
public static RemoteObject _colorlista = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.ColorDrawable");
public static RemoteObject _esdescargaexcel = RemoteObject.createImmutable(false);
public static RemoteObject _httputils2service = RemoteObject.declareNull("anywheresoftware.b4a.samples.httputils2.httputils2service");
public static b4a.example.starter _starter = null;
public static b4a.example.edicion _edicion = null;
public static b4a.example.altaproducto _altaproducto = null;
  public Object[] GetGlobals() {
		return new Object[] {"Activity",main.mostCurrent._activity,"AltaProducto",Debug.moduleToString(b4a.example.altaproducto.class),"ColorBusqueda",main.mostCurrent._colorbusqueda,"ColorFondo",main.mostCurrent._colorfondo,"ColorLista",main.mostCurrent._colorlista,"Edicion",Debug.moduleToString(b4a.example.edicion.class),"EsDescargaExcel",main._esdescargaexcel,"HttpUtils2Service",main.mostCurrent._httputils2service,"idProducto",main._idproducto,"json",main.mostCurrent._json,"lvNombre",main.mostCurrent._lvnombre,"modeloProducto",main._modeloproducto,"nombreProducto",main._nombreproducto,"precioProducto",main._precioproducto,"registro",main.mostCurrent._registro,"SQL1",main._sql1,"Starter",Debug.moduleToString(b4a.example.starter.class),"tipoProducto",main._tipoproducto,"txtBuscar",main.mostCurrent._txtbuscar,"webservice",main.mostCurrent._webservice};
}
}