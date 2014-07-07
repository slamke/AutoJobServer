package cn.edu.sjtu.se.dslab.AutoJobMan.web.pagination;

import java.io.Serializable;
import java.util.List;

public interface PagedListHolder extends Serializable {
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int DEFAULT_MAX_LINKED_PAGES = 10;
    
    public void setRecordsSubst(List recordsSubset);
    public void setRealRecordCount(long realRecordCount);
    
    /**
     * 设置每页应有多少条记录。
     */
    public void setPageSize(int pageSize);
    
    /**
     * 返回每页共有多少条记录
     */
    public int getPageSize();
    
    /**
     * 根据pageSize，返回共有多少页
     */
    public int getPageCount();
    
    /**
     * 返回当前页码。
     * 首页为0
     */
    public int getPage();
    
    /**
     * 设置当前页码。
     * 首页为0
     */
    public void setPage(int page);
    
    /**
     * 设置围绕当前页最多可以显示多少链接的页数。
     * 此方法<strong>会</strong>影响getFirstLinkedPage()及getLastLinkedPage()
     */
    public void setMaxLinkedPages(int maxLinkedPages);
    
    /**
     * 返回围绕当前页最多可以显示多少链接的页数
     */
    public int getMaxLinkedPages();
    
    /**
     * 返回首页的页码
     */
    public int getFirstLinkedPage();
    
    /**
     * 返回最后一页的页码
     */
    public int getLastLinkedPage();
    
    
    /**
     * 转至前一页。
     * 如果已经是首页，则停在该页。
     */
    public void previousPage();
    
    /**
     * 转至下一页。
     * 如果已经是最后一页，则停在该页。
     */
    public void nextPage();
    
    /**
     * 转至首页。
     */
    public void firstPage();
    
    /**
     * 转至最后一页
     */
    public void lastPage();
    
    /**
     * 返回总的记录数
     */
    public long getNrOfElements();
    
    /**
     * 返回在当前页面上的第一个记录在所有记录(从0开始)中的编号
     */
    public int getFirstElementOnPage();
    
    /**
     * 返回在当前页面上的最后一个记录在所有记录(从0开始)中的编号
     */
    public int getLastElementOnPage();
    
    /**
     * 返回在当前页面上的所有记录
     */
    public List getPageList();
}

