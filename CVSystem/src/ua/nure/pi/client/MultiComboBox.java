package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;

import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MultiComboBox extends SimplePanel {
	private Collection<String> values;
	private Label title;
	private String h;
	private String id;

	
	public String getTitle(){
		return title.getText();
	}
	
	public void setTitle(String value) {
		title.setText(value);
	}
	
	public MultiComboBox(Collection<String> val, String title) {
		VerticalPanel root = new VerticalPanel();
		this.title = new Label(title);
		this.title.setStyleName("formTitle");
		root.add(this.title);
		id = "MultiComboBox" +  String.valueOf(this.hashCode());
		h = "<div id='" + id + "'></div>";
		values = val;
		HTML html = new HTML(h);
		root.setStyleName("fixMultiComboBox");
		root.add(html);
		add(root);
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