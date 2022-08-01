package com.mpas.cems.beans.scalars;

import com.mpas.cems.beans.ci.SimpleBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;


@Component
public class EmptyCollectionHolder {

    private List<SimpleBean> simpleBeanList;

    private Set<SimpleBean> simpleBeanSet;

    private Map<String, SimpleBean> simpleBeanMap;

    @Autowired
    @Qualifier("simpleBeanList")
    public void setSimpleBeanList(List<SimpleBean> simpleBeanList) {
        this.simpleBeanList = simpleBeanList;
    }

    @Autowired
    @Qualifier("simpleBeanSet")
    public void setSimpleBeanSet(Set<SimpleBean> simpleBeanSet) {
        this.simpleBeanSet = simpleBeanSet;
    }

    @Autowired
    @Qualifier("simpleBeanMap")
    public void setSimpleBeanMap(Map<String, SimpleBean> simpleBeanMap) {
        this.simpleBeanMap = simpleBeanMap;
    }

    /**
     * This method was implemented just to verify the collections injected into beans of this type
     *
     * @return
     */
    @Override
    public String toString() {
        return "EmptyCollectionHolder{" +
                "simpleBeanList=" + simpleBeanList.size() +
                ", simpleBeanSet=" + simpleBeanSet.size() +
                ", simpleBeanMap=" + simpleBeanMap.size() +
                '}';
    }
}
