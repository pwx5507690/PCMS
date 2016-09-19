/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcms.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import com.pcms.core.util.UrlUtil;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author wx.pan
 */
public class XssFilter implements Filter {

    private String _filterChar;
    private String _replaceChar;
    private String _splitChar;
    private String _excludeUrls;
    FilterConfig _filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this._filterChar = filterConfig.getInitParameter("FilterChar");
        this._replaceChar = filterConfig.getInitParameter("ReplaceChar");
        this._splitChar = filterConfig.getInitParameter("SplitChar");
        this._excludeUrls = filterConfig.getInitParameter("excludeUrls");
        this._filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
        this._filterConfig = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        if (isExcludeUrl(request)) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request, _filterChar, _replaceChar, _splitChar), response);
        }
    }

    private boolean isExcludeUrl(ServletRequest request) {
        boolean exclude = false;
        if (StringUtils.isNotBlank(_excludeUrls)) {
            String[] excludeUrl = _excludeUrls.split(_splitChar);
            if (excludeUrl != null && excludeUrl.length > 0) {
                for (String url : excludeUrl) {
                    if (UrlUtil.getURI((HttpServletRequest) request).startsWith(url)) {
                        exclude = true;
                    }
                }
            }
        }
        return exclude;
    }
}
