package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;

public class MultiSelect extends SimplePanel {
	private HashMap<String, String> values;
	private Collection<String> options;
	private String h;
	private String id;
	private int width;
	private int height;
	
	public void setWidth(int width){
		this.width=width;
		setWidth(id, width);
	}
	
	public void setHeight(int height){
		this.height = height;
		setHeight(id, height);
	}
	
	public MultiSelect(HashMap<String, String> val, Collection<String> option) {
		id = "MultiSelect" +  String.valueOf(this.hashCode());
		values = val;
		options = option;
		h = "<div id='" + id + "'></div>";
		HTML html = new HTML(h);
		setWidget(html);
	}

	/*
	public void addValues(Collection<String> val){
		values.addAll(val);
	}*/
	
	public void draw(){
		String jsOpt = "$wnd.loadOpt = function(node, level, id){";
		int index= 0;
		for(String i : options)
			jsOpt+= "node.children.push({id:id +'_'+"+(index++)+",title:'"+i
				+"',has_children:false, level: node.level + 1,children:[]});";
		jsOpt+="return node;};";
		String jsValue = "$wnd.loadChildren = function(node, level) { var id;";
		index = 0;
		for(String i : values.keySet()){
			jsValue+="id=" +i + "; node.children.push({id:id,title:'"+values.get(i) 
					+"',has_children:true, level: node.level + 1,children:[]});"
					+ "$wnd.loadOpt(node.children["+(index++)+"],(level+1), id);";
		}
		jsValue +="return node;};";
		runSelect(jsOpt, jsValue, id);
	}
	
	public HashMap<Integer, Collection<String>> getValues(){
		Collection<String> temp = toJCollection(getJSValues(id));
		HashMap<Integer, Collection<String>> res = new HashMap<Integer, Collection<String>>();
		for(String i : temp){
			String[] t = i.split("_");
			if(t.length!=3)
				continue;
			int opt = Integer.parseInt(t[2]);
			if(res.containsKey(opt)){
				res.get(opt).add(t[1]);
			}
			else{
				ArrayList<String> item = new ArrayList<String>();
				item.add(t[1]);
				res.put(opt, item);
			}
		}
		return res;
	}
	
	private Collection<String> toJCollection(JsArrayString jsValues) {
		Collection<String> res = new ArrayList<String>();
		for(int i = 0; i<jsValues.length(); i++){
			res.add(jsValues.get(i));
		}
		return res;
	}

	private JsArrayString toJsArray(Collection<String> col){
		JsArrayString arr =JsArrayString.createArray().cast(); 
		for(String s : col)
			arr.push(s);
		return arr;
	}
	
	private native void add_js(String url) /*-{
		var script = $wnd.document.createElement('script');
        script.src = url;
        $wnd.document.getElementsByTagName('head')[0].appendChild(script);
	}-*/;
	
	private native void add_css(String url) /*-{
	    var head  = $wnd.document.getElementsByTagName('head')[0];
	    var link  = $wnd.document.createElement('link');
	    link.rel  = 'stylesheet';
	    link.type = 'text/css';
	    link.href = url;
	    link.media = 'all';
	}-*/;
	
	private native void setWidth(String id, int width)/*-{
		$wnd.$('#'+id).css('width',width);
	}-*/;
	
	private native void setHeight(String id, int height)/*-{
		$wnd.$('#'+id+' .treeselect').css('height', height);
	}-*/;
	
	private native void runSelect(String jsOpt,String jsVal, String id)/*-{
		
		eval(jsOpt);
		eval(jsVal);
		$wnd.$(function() {
    		$wnd.$('#'+id).chosentree({
		    deepLoad: true,
      		showtree: true,
			load: function(node, callback) {
		        setTimeout(function() {
		          callback($wnd.loadChildren(node, 0));
		        }, 1000);
		      }
			});
		});	
	}-*/;	
	
	private static native JsArrayString getJSValues(String id)/*-{
	var s = $wnd.$("#"+id+' .search-choice');
	var res = [];
	for(var i = 0; i < s.length; i++)
		res[i]=s[i].id;
	return res;
	}-*/;
}
