package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MultiSelect extends SimplePanel {
	private HashMap<String, String> values;
	private Collection<String> options;
	private String h;
	private String id;
	private int width;
	private int height;
	private Label title;
	private boolean withOptions = true;
	
	
	public void setWidth(int width){
		this.width=width;
		setWidth(id, width);
	}
	
	public void setHeight(int height){
		this.height = height;
		setHeight(id, height);
	}
	
	public void setTitle(String t){
		title.setText(t);
	}
	
	public String getTitle(){
		return title.getText();
	}
	
	public MultiSelect(HashMap<String, String> val, String title) {
		this(val, new ArrayList<String>(), title);
		withOptions = false;
	}
	
	public MultiSelect(HashMap<String, String> val) {
		this(val, "");
	}
	
	public MultiSelect(HashMap<String, String> val, Collection<String> option) {
		this(val, option, "");
	}
	
	public MultiSelect(HashMap<String, String> val, Collection<String> option, String title) {
		VerticalPanel root = new VerticalPanel();
		id = "MultiSelect" +  String.valueOf(this.hashCode());
		values = val;
		options = option;
		h = "<div id='" + id + "'></div>";
		HTML html = new HTML(h);
		this.title = new Label();
		setTitle(title);
		root.add(this.title);
		root.add(html);
		root.setSpacing(5);
		setWidget(root);
	}
	
	
	
	public void draw(){
		String jsOpt = "$wnd.loadOpt."+id+" = function(node, level, id){";
		int index= 0;
		for(String i : options)
			jsOpt+= "node.children.push({id:id +'_'+"+(index++)+",title:'"+i
				+"',has_children:false, level: node.level + 1,children:[]});";
		jsOpt+="return node;};";
		String jsValue = "$wnd.loadChildren."+id+" = function(node, level) { var id;";
		index = 0;
		for(String i : values.keySet()){
			jsValue+="id=" +i + "; node.children.push({id:id,title:'"+values.get(i) 
					+"',has_children:"+withOptions+", level: node.level + 1,children:[]});"
					+"if("+withOptions+")"
					+ "$wnd.loadOpt."+id+"(node.children["+(index++)+"],(level+1), id);";
		}
		jsValue +="return node;};";
		runSelect(jsOpt, jsValue, id);
	}
	
	public HashMap<Integer, Collection<String>> getValues(){
		Collection<String> temp = toJCollection(getJSValues(id));
		HashMap<Integer, Collection<String>> res = new HashMap<Integer, Collection<String>>();
		for(String i : temp){
			String[] t = i.split("_");
			if(t.length!=3 && withOptions)
				continue;
			int opt;
			if(withOptions)
				opt = Integer.parseInt(t[2]);
			else
				opt = 0;
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
	
	@Override
	public void setStyleName(String style) {
		setStyle(id, style);
	};
	
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
	
	private native void setStyle(String id, String style)/*-{
		$wnd.$('#'+id).addClass(style);
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
		          callback($wnd.loadChildren[id](node, 0));
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
