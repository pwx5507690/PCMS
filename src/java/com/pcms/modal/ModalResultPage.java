package com.pcms.modal;

import java.util.List;
import java.util.Map;

public class ModalResultPage extends ModalResult {

    private List<Map<String, String>> _result;

    private int _currentPage;

    private int _pageSize;

    private int _pageCount;

    /**
     * @return the _result
     */
    public List<Map<String, String>> getResult() {
        return _result;
    }

    /**
     * @param _result the _result to set
     */
    public void setResult(List<Map<String, String>> _result) {
        this._result = _result;
    }

    /**
     * @return the _currentPage
     */
    public int getCurrentPage() {
        return _currentPage;
    }

    /**
     * @param _currentPage the _currentPage to set
     */
    public void setCurrentPage(int _currentPage) {
        this._currentPage = _currentPage;
    }

    /**
     * @return the _pageSize
     */
    public int getPageSize() {
        return _pageSize;
    }

    /**
     * @param _pageSize the _pageSize to set
     */
    public void setPageSize(int _pageSize) {
        this._pageSize = _pageSize;
    }

    /**
     * @return the _count
     */
    public int getCount() {
        return _pageCount;
    }

    /**
     * @param _count the _count to set
     */
    public void setCount(int _count) {
        this._pageCount = _count;
    }

    /**
     * @return the _howManyPages
     */
    public int getHowManyPages() {
        int _howManyPages = this._pageCount / this._pageSize;
        if (this._pageCount % this._pageSize > 0) {
            return ++_howManyPages;
        }
        return _howManyPages;
    }

}
