
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

public class altaproducto implements IRemote{
	public static altaproducto mostCurrent;
	public static RemoteObject processBA;
    public static boolean processGlobalsRun;
    public static RemoteObject myClass;
    public static RemoteObject remoteMe;
	public altaproducto() {
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
        anywheresoftware.b4a.pc.RapidSub.moduleToObject.put(new B4XClass("altaproducto"), "b4a.example.altaproducto");
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
		pcBA = new PCBA(this, altaproducto.class);
        main_subs_0.initializeProcessGlobals();
		return pcBA;
	}
public static RemoteObject __c = RemoteObject.declareNull("anywheresoftware.b4a.keywords.Common");
public static RemoteObject _btnvolver = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
public static RemoteObject _buttonguardar = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
public static RemoteObject _etnombre = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _ettipo = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _etprecio = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _webservice3 = RemoteObject.declareNull("anywheresoftware.b4a.samples.httputils2.httpjob");
public static RemoteObject _etmodelo = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _httputils2service = RemoteObject.declareNull("anywheresoftware.b4a.samples.httputils2.httputils2service");
public static b4a.example.main _main = null;
public static b4a.example.starter _starter = null;
public static b4a.example.edicion _edicion = null;
  public Object[] GetGlobals() {
		return new Object[] {"Activity",altaproducto.mostCurrent._activity,"btnVolver",altaproducto.mostCurrent._btnvolver,"ButtonGuardar",altaproducto.mostCurrent._buttonguardar,"Edicion",Debug.moduleToString(b4a.example.edicion.class),"etModelo",altaproducto.mostCurrent._etmodelo,"etNombre",altaproducto.mostCurrent._etnombre,"etPrecio",altaproducto.mostCurrent._etprecio,"etTipo",altaproducto.mostCurrent._ettipo,"HttpUtils2Service",altaproducto.mostCurrent._httputils2service,"Main",Debug.moduleToString(b4a.example.main.class),"Starter",Debug.moduleToString(b4a.example.starter.class),"webservice3",altaproducto.mostCurrent._webservice3};
}
}