package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;

import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MultiComboBox extends SimplePanel {
	private Collection<String> values;
	private String title;
	private String h;
	private String id;
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String value) {
		title = value;
	}
	
	public MultiComboBox(Collection<String> val, String title) {
		id = "MultiComboBox" +  String.valueOf(this.hashCode());
		h = "<div class='formTitle'> ".concat(title)
				+ "</div><div id='" + id + "'></div>";
		values = val;
		setTitle(title);
		HTML html = new HTML(h);
		html.setStyleName("fixMultiComboBox");
		add(html);
	}
	
	public MultiComboBox() {
		this(new ArrayList<String>(), "");
	}
	
	public MultiComboBox(String s) {
		this(new ArrayList<String>(), s);
	}

	
	public void addValues(Collection<String> val){
		values.addAll(val);
	}
	
	public void draw(){
		runChoose(toJsArray(values), id);
	}
	
	public Collection<String> getValues(){
		return toJCollection(getJSValues(id));
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
	
	private native void runChoose(JsArrayString value, String id)/*-{
		var val = []
		for(var i = 0; i<value.length; i++){
			val[i] = {id:value[i], text:value[i]};
		}
		$wnd.$("#"+id).select2({
			width:'290px',
		    createSearchChoice:function(term, data) { if ($wnd.$(data).filter(function() { return this.text.localeCompare(term)===0; }).length===0) {return {id:term, text:term};} },
		    multiple: true,
		    data: val,
			placeholder: '-Технологии-'
		});
	}-*/;
	
	private static native JsArrayString getJSValues(String id)/*-{
	var s = $wnd.$("#"+id).select2('data');
	var res = [];
	for(var i = 0; i < s.length; i++)
		res[i]=s[i].text;
	return res;
	}-*/;
}