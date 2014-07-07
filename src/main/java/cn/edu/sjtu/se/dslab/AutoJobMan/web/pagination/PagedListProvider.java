package cn.edu.sjtu.se.dslab.AutoJobMan.web.pagination;

import java.util.List;

public interface PagedListProvider {
    public List getRecordsSubset(int firstResult, int maxResults);
}