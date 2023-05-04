package vn.ansv.management.entity;

public class PaginatedEntity {

    private int pageSize;
    private int pageNumber;
    private int totalPage;
    private Object listPageNumbers;

    public PaginatedEntity() {
    }

    public PaginatedEntity(int pageSize, int pageNumber, int totalPage, Object listPageNumbers) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.totalPage = totalPage;
        this.listPageNumbers = listPageNumbers;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return this.pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public Object getListPageNumbers() {
        return this.listPageNumbers;
    }

    public void setListPageNumbers(Object listPageNumbers) {
        this.listPageNumbers = listPageNumbers;
    }

}
