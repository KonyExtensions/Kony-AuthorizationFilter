Kony-AuthorizationFilter
========================

Template Utility

This filter should be applied next to XSS Filter as below:
 <filter>
    <description>Filter to prevent unauthorized users from accessing role specific services</description>
    <filter-name>AuthorizationFilter</filter-name>
    <filter-class>com.kony.prateek.filters.AuthorizationFilter</filter-class>
 </filter>
 <filter-mapping>
    <filter-name>AuthorizationFilter</filter-name>
    <servlet-name>MWServlet</servlet-name>
 </filter-mapping>
 
user-role should be set in session as 'Authorized' as part of post-logon code (post-processor class). In case of multiple user roles, set user-role in session based on business logic
