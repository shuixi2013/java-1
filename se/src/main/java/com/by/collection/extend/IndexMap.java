package com.by.collection.extend;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * @author baoyang
 */
public class IndexMap extends HashMap<Object, Object>{
	
	private static final long serialVersionUID = 1L;   
	  
    private List<Object> list=new ArrayList<Object>();   
       
    public Object put(Object key, Object value) {   
        if (!containsKey(key)){   
            list.add(key);   
        }   
        return super.put(key, value);   
    }   
  
       
    public Object get(int idx){   
        return super.get(getKey(idx));   
    }
    
    public int getIndex(Object key){   
        return list.indexOf(key);   
    }
    
    public Object getKey(int idx){   
        if (idx>=list.size()) return null;   
        return list.get(idx);   
    }   
       
    public void remove(int idx){   
        Object key=getKey(idx);   
        removeFromList(getIndex(key));   
        super.remove(key);   
    }   
       
    public Object remove(Object key) {   
        removeFromList(getIndex(key));   
        return super.remove(key);   
    }   
       
    public void clear() {   
        this.list = new ArrayList<Object>();   
        super.clear();   
    }
    
    private void removeFromList(int idx){   
    	if (idx<list.size() && idx>=0) {   
            list.remove(idx);   
        }   
    }   
}
