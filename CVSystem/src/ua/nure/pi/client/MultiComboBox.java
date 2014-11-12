package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;

import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;

public class MultiComboBox extends SimplePanel {
	private Collection<String> values;
	
	public MultiComboBox(Collection<String> val) {
		String h = "<div class='formTitle'>Профессиональные навыки"
				+ "</div><div class='MultiComboBox'></div>";
		values = val;
		HTML html = new HTML(h);
		html.setStyleName("fixMultiComboBox");
		add(html);
	}
	
	public MultiComboBox() {
		this(new ArrayList<String>());
	}
	
	public void addValues(Collection<String> val){
		values.addAll(val);
	}
	
	public void draw(){
		runChoose(toJsArray(values));
	}
	
	public Collection<String> getValues(){
		return toJCollection(getJSValues());
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
	
	private native void runChoose(JsArrayString value)/*-{
		var val = []
		for(var i = 0; i<value.length; i++){
			val[i] = {id:value[i], text:value[i]};
		}
		$wnd.$('.MultiComboBox').select2({
			width:'290px',
		    createSearchChoice:function(term, data) { if ($wnd.$(data).filter(function() { return this.text.localeCompare(term)===0; }).length===0) {return {id:term, text:term};} },
		    multiple: true,
		    data: val,
			placeholder: '-Технологии-'
		});
	}-*/;
	
	private static native JsArrayString getJSValues()/*-{
	var s = $wnd.$('.MultiComboBox').select2('data');
	var res = [];
	for(var i = 0; i < s.length; i++)
		res[i]=s[i].text;
	return res;
	}-*/;
}