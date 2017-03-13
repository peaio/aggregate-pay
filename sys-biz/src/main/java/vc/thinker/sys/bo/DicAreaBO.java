package vc.thinker.sys.bo;


import java.util.Collection;
import java.util.List;

import com.sinco.dic.client.model.DicParentBase;

import vc.thinker.sys.model.DicArea;
/**
 * 
 * BO 用于返回数据
 *
 */
public class DicAreaBO extends DicArea implements DicParentBase<DicAreaBO>,Comparable<DicAreaBO>{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -5645028277639400916L;

	private DicAreaBO parent;
    
    private Collection<DicAreaBO> children;
	
	public static void sortList(List<DicAreaBO> list, List<DicAreaBO> sourcelist, String parendCode){
		for (int i=0; i<sourcelist.size(); i++){
			DicAreaBO e = sourcelist.get(i);
			if (e.getParentCode() !=null
					&& e.getParentCode().equals(parendCode)){
				list.add(e);
				// 判断是否还有子节点, 有则继续获取子节点
				for (int j=0; j<sourcelist.size(); j++){
					DicArea childe = sourcelist.get(j);
					if (childe.getParentCode()!=null
							&& childe.getParentCode().equals(e.getCode())){
						sortList(list, sourcelist, e.getCode());
						break;
					}
				}
			}
		}
	}

	public DicAreaBO getParent() {
		return parent;
	}

	@Override
	public Collection<DicAreaBO> getChildren() {
		return this.children;
	}

	@Override
	public void setChildren(Collection<DicAreaBO> children) {
		this.children=children;
	}

	public void setParent(DicAreaBO parent) {
		this.parent=parent;
	}

	@Override
	public int compareTo(DicAreaBO o) {
		return this.getSequence().compareTo(o.getSequence());
	}
	
}