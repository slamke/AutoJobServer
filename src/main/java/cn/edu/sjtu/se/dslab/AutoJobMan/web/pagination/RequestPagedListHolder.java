package cn.edu.sjtu.se.dslab.AutoJobMan.web.pagination;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestDataBinder;

public class RequestPagedListHolder implements PagedListHolder {
    private static int pageSize = DEFAULT_PAGE_SIZE;
    private static int maxLinkedPages = DEFAULT_MAX_LINKED_PAGES;
    
    private int page = 0;
    private List recordsSubset;
    
    private long realRecordCount;
    
    /** Creates a new instance of RequestPagedListHolder */
    public RequestPagedListHolder(HttpServletRequest request, long realRecordCount, PagedListProvider pagedListProvider) {
        setRealRecordCount(realRecordCount);
        
        ServletRequestDataBinder binder = new ServletRequestDataBinder(this);
        binder.bind(request);
        
        checkPageNavigation(request);
        
        setRecordsSubst(pagedListProvider.getRecordsSubset(getPageSize() * getPage(), getPageSize()));
    }

    private void checkPageNavigation(final HttpServletRequest request) {
        String pageNavAction = request.getParameter("pageNavAction");
        if (pageNavAction != null) {
            if (pageNavAction.equals("firstPage")) {
                firstPage();
            } else if (pageNavAction.equals("previousPage")) {
                previousPage();
            } else if (pageNavAction.equals("nextPage")) {
                nextPage();
            } else if (pageNavAction.equals("lastPage")) {
                lastPage();
            }
        }
    }
    
    public void setRecordsSubst(List recordsSubset) {
        this.recordsSubset = recordsSubset;
    }
    
    public void setRealRecordCount(long realRecordCount) {
        this.realRecordCount = realRecordCount;
    }
    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    public int getPageSize() {
        return pageSize;
    }
    
    public int getPageCount() {
        float nrOfPages = (float) getNrOfElements() / getPageSize();
        return (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages);
    }
    
    public int getPage() {
        if (page >= getPageCount()) {
            page = getPageCount() - 1;
        }
        return page;
    }
    
    public void setPage(int page) {
        this.page = page;
    }
    
    public void setMaxLinkedPages(int maxLinkedPages) {
        this.maxLinkedPages = maxLinkedPages;
    }
    
    public int getMaxLinkedPages() {
        return maxLinkedPages;
    }
    
    public int getFirstLinkedPage() {
        return Math.max(0, getPage() - (getMaxLinkedPages() / 2));
    }
    
    public int getLastLinkedPage() {
        return Math.min(getFirstLinkedPage() + getMaxLinkedPages() - 1, getPageCount() - 1);
    }
    
    public void previousPage() {
        if (!isAtFirstPage()) {
            page--;
        }
    }
    
    public void nextPage() {
        if (!isAtLastPage()) {
            page++;
        }
    }
    
    public void firstPage() {
        setPage(0);
    }
    
    public void lastPage() {
        setPage(getPageCount() - 1);
    }
    
    public long getNrOfElements() {
        return realRecordCount;
    }
    
    public int getFirstElementOnPage() {
        return (getPageSize() * getPage());
    }
    
    public int getLastElementOnPage() {
        int endIndex = getPageSize() * (getPage() + 1);
        return (endIndex > getNrOfElements() ? (int)getNrOfElements() : endIndex) - 1;
    }
    
    public List getPageList() {
        return recordsSubset;
    }
    
    public boolean isAtFirstPage() {
        return getPage() == 0;
    }
    
    public boolean isAtLastPage() {
        return getPage() == getPageCount() - 1;
    }
}
