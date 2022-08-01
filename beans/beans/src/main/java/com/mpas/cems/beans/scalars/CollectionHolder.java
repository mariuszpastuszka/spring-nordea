package com.mpas.cems.beans.scalars;

import com.mpas.cems.beans.ci.SimpleBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;


@Component
public class CollectionHolder {
    private List<SimpleBean> simpleBeanList2;

    private Set<SimpleBean> simpleBeanSet2;

    private Map<String, SimpleBean> simpleBeanMap2;

    @Autowired
    @Qualifier("simpleBeanList2")
    public void setSimpleBeanList2(List<SimpleBean> simpleBeanList2) {
        this.simpleBeanList2 = simpleBeanList2;
    }

    @Autowired
    @Qualifier("simpleBeanSet2")
    public void setSimpleBeanSet2(Set<SimpleBean> simpleBeanSet2) {
        this.simpleBeanSet2 = simpleBeanSet2;
    }

    @Autowired
    @Qualifier("simpleBeanMap2")
    public void setSimpleBeanMap2(Map<String, SimpleBean> simpleBeanMap2) {
        this.simpleBeanMap2 = simpleBeanMap2;
    }

    /**
     * This method was implemented just to verify the collections injected into beans of this type
     *
     * @return
     */
    @Override
    public String toString() {
        return "CollectionHolder{" +
                "simpleBeanList=" + simpleBeanList2.size() +
                ", simpleBeanSet=" + simpleBeanSet2.size() +
                ", simpleBeanMap=" + simpleBeanMap2.size() +
                '}';
    }
}
